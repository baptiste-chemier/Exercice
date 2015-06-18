package Modele;

import java.util.Observable;
import static Modele.ModeleJeu.etat.*;

/**
 * Modele du Jeu
 * @author Chemier Baptiste et Nguyen Aisi
 */
public class ModeleJeu extends Observable implements Runnable {

    public enum etat {Gagner, Perdu, EnJeu; }
    
    private ModeleGrille grille;
    private etat etatPartie;
    private Chrono chrono;

    /**
     * COnstructeur
     */
    public ModeleJeu()
    {    
        this.iniComponent(new int[]{12, 10}, 10);
        new Thread(this).start();
    }  
        
    /**
     * Initialisation des composants
     * @param nbCases
     * @param nbBombes 
     */
    public void iniComponent(int[] nbCases, int nbBombes)
    {
        this.grille = new ModeleGrille(nbCases, nbBombes) {};  
        this.etatPartie = EnJeu; 
        chrono = new Chrono();
    }
    
    public Chrono getChrono()
    {
        return chrono;
    }

    public etat getEtatPartie()
    {
        return etatPartie;
    }

    public void setEtatPartie(etat etatPartie) 
    {
        this.etatPartie = etatPartie;
    }

    public ModeleGrille getGrille() 
    {
        return grille;
    }
    
    /**
     * Notification aux observers
     * @param reset 
     */
    public void notifierObservateurs(boolean reset)
    {
        setChanged();
        notifyObservers((boolean)reset);
    }

    /**
     * Test la fin de la partie ou non
     * @param idCase
     * @return 
     */
    public etat finPartie(int idCase)
    {
        if(this.getGrille().getCases()[idCase].isPiege())
            return Perdu;  
        if(this.getGrille().getNbDecouvertes() + this.getGrille().getNbBombes() >= this.getGrille().getCases().length){
            return Gagner;
        }
        return EnJeu;
    }
    
    @Override
    public void run() 
    {
        while(true){
            notifierObservateurs(false);
        }
    }
}

