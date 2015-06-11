package Modele;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Modele.ModeleJeu.etat.*;

public final class ModeleJeu extends Observable implements Runnable {

    public enum etat {VICTOIRE, DEFAITE, ENCOURS; }
    
    private ModeleGrille grille;
    private boolean envoieNotif, envoieNotifReset;   
    private etat etatPartie;
    public boolean casePressed;

    public ModeleJeu(){
        casePressed = false;      
        this.initPartie(new int[]{12,10}, 20);
        demarreThread();
    }  

    public void initPartie(int[] _nbCases, int _nbBombes){
        grille = new ModeleGrille(_nbCases, _nbBombes) {};  
        envoieNotif = false;
        envoieNotifReset = false;
        etatPartie = ENCOURS;  
    }

    public etat getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(etat etatPartie) {
        this.etatPartie = etatPartie;
    }

    public ModeleGrille getGrille() {
        return grille;
    }

    public void setEnvoieNotif(boolean envoieNotif) {
        this.envoieNotif = envoieNotif;
    }

    public void setEnvoieNotifReset(boolean envoieNotifReset) {
        this.envoieNotifReset = envoieNotifReset;
    }
    
    public void notifierObservateurs(boolean reset){
        setChanged();
        notifyObservers((boolean)reset);
    }

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
                Logger.getLogger(ModeleJeu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
     
    private void demarreThread() {
        new Thread(this).start();
    }
}

