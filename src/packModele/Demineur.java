package packModele;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static packModele.Demineur.etat.*;

/**
 * Class principale du démineur et des informations de la partie
 * @author François De Aveiro - Victor Giroud
 */

public final class Demineur extends Observable implements Runnable {

    public enum etat {VICTOIRE, DEFAITE, ENCOURS; }
    
    private Grille grille;
    private boolean envoieNotif, envoieNotifReset;   
    private etat etatPartie;
    public boolean casePressed;
    
    /**
     * Constructeur du démineur, à utiliser une seule fois
     */  
    public Demineur(){
        casePressed = false;      
        this.initPartie(new int[]{12,10}, 10);
        demarreThread();
    }  
    /**
     * Permet d'initialiser une nouvelle partie
     * @param _nbCases tableau comportant le nombre de cases de la grille (largeur, hauteur)
     * @param _nbBombes le nombre de bombes présentes dans la grille 
     */
    public void initPartie(int[] _nbCases, int _nbBombes){
        grille = new GrilleCarre(_nbCases, _nbBombes);  
        envoieNotif = false;
        envoieNotifReset = false;
        etatPartie = ENCOURS;  
    }
    
    /**
     * @return l'état de la partie (VICTOIRE, DEFAITE, ENCOURS)
     */
    public etat getEtatPartie() {
        return etatPartie;
    }

    /**
     * Change l'état de la partie (VICTOIRE, DEFAITE, ENCOURS)
     * @param etatPartie the etatPartie to set
     */
    public void setEtatPartie(etat etatPartie) {
        this.etatPartie = etatPartie;
    }
    
    
    /**
     * @return la grille du démineur
     */
    public Grille getGrille() {
        return grille;
    }
    

    /**
     * Permet de notifier les observeurs
     * @param envoieNotif vrai si la partie doit être réinitialisée
     */
    public void setEnvoieNotif(boolean envoieNotif) {
        this.envoieNotif = envoieNotif;
    }
    
    /**
     * Permet de notifier les observeurs que la partie a été réinitialisée
     * @param envoieNotifReset vrai si la partie a été réinitialisée
     */
    public void setEnvoieNotifReset(boolean envoieNotifReset) {
        this.envoieNotifReset = envoieNotifReset;
    }
          
    /**
     * Notifie les observateurs
     * @param reset vrai si la partie doit être réinitialisée
     */     
    public void notifierObservateurs(boolean reset){
        setChanged();
        notifyObservers((boolean)reset);
    }

    /**
     * Teste l'état de la partie
     * @param IDderniereCaseDecouverte l'identifiant de la dernière case à avoir été découverte par le joueur
     * @return l'état de la partie (VICTOIRE, DEFAITE, ENCOURS)
     */   
    public etat testPartieTerminee(int IDderniereCaseDecouverte){
        if(this.getGrille().getCases()[IDderniereCaseDecouverte].isPiege())
            return DEFAITE;  
        if(this.getGrille().getNbCasesDecouvertes()+this.getGrille().getNbBombes()>=this.getGrille().getCases().length){
            return VICTOIRE;
        }
        return ENCOURS;
    }
    
    
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1);           
                if(this.envoieNotif){
                    setEnvoieNotif(false);
                    notifierObservateurs(false);
                }
                else if(this.envoieNotifReset){
                    setEnvoieNotifReset(false);
                    notifierObservateurs(true);            
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Demineur.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
     
  
    private void demarreThread() {
        new Thread(this).start();
    }
}

