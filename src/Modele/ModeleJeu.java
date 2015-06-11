package Modele;

import java.util.Observable;
import static Modele.ModeleJeu.etat.*;

public class ModeleJeu extends Observable implements Runnable {

    public enum etat {Gagner, Perdu, EnJeu; }
    
    private ModeleGrille grille;
    private etat etatPartie;

    public ModeleJeu()
    {    
        this.iniComponent(new int[]{12, 10}, 10);
        new Thread(this).start();
    }  

    public void iniComponent(int[] nbCases, int nbBombes)
    {
        this.grille = new ModeleGrille(nbCases, nbBombes) {};  
        this.etatPartie = EnJeu;  
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
    
    public void notifierObservateurs(boolean reset)
    {
        setChanged();
        notifyObservers((boolean)reset);
    }

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

