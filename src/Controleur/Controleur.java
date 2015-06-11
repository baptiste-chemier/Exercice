package Controleur;

import Modele.ModeleJeu;

public class Controleur{
    private final ModeleJeu demineur;
    
    public Controleur(ModeleJeu d){
        this.demineur = d;
    }  
    
    public void clickGauche(int numcase){
        if(etatPartie())
            return;
        
        if(!demineur.getGrille().getCases()[numcase].isDecouverte() && !demineur.getGrille().getCases()[numcase].isDrapeau()){
            demineur.getGrille().getCases()[numcase].setDecouverte(true);
            if(!demineur.getGrille().isPremierCoup()){
                demineur.getGrille().setBombes(demineur.getGrille().getNbBombes(), numcase);
            }
            demineur.getGrille().setDerniereCase(numcase);
            demineur.getGrille().propagationVoisins(demineur.getGrille().getCases()[numcase]);
            if(demineur.testPartieTerminee(numcase)==ModeleJeu.etat.DEFAITE){
                demineur.setEtatPartie(ModeleJeu.etat.DEFAITE);
            }
            else if(demineur.testPartieTerminee(numcase)==ModeleJeu.etat.VICTOIRE){
                demineur.setEtatPartie(ModeleJeu.etat.VICTOIRE);            
            }
            demineur.setEnvoieNotif(true);
        }
    }

    public void clickDroit(int numcase){
        if(etatPartie())
            return; 
        
        if(!demineur.getGrille().getCases()[numcase].isDecouverte()){
            if(!demineur.getGrille().getCases()[numcase].isDrapeau()){
                demineur.getGrille().getCases()[numcase].setDrapeau(true);
                demineur.getGrille().setNbBombesRestantes(demineur.getGrille().getNbBombesRestantes()-1);
            }
            else{
                demineur.getGrille().getCases()[numcase].setDrapeau(false); 
                demineur.getGrille().setNbBombesRestantes(demineur.getGrille().getNbBombesRestantes()+1);    
            }
            demineur.setEnvoieNotif(true);
        }
    }  

    public void nouvellePartie(int[] cases, int bombes){    
        demineur.initPartie(cases, bombes);
        demineur.setEnvoieNotifReset(true);          
    }  
    
    public boolean etatPartie(){
        return demineur.getEtatPartie()!=ModeleJeu.etat.ENCOURS;
    }
}
