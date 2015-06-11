package Controleur;

import Modele.ModeleJeu;
import Modele.ModeleJeu.etat;

public class Controleur{
    private final ModeleJeu demineur;
    
    public Controleur(ModeleJeu d){
        this.demineur = d;
    }  
        
    public boolean controlePartieTerminnee(){
        return demineur.getEtatPartie()!=ModeleJeu.etat.ENCOURS;
    }
    
    public void controleCaseClickedLeft(int numcase){
        if(controlePartieTerminnee())
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

    public void controleCaseClickedRight(int numcase){
        if(controlePartieTerminnee())
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

    public void controleNouvellePartie(int[] cases, int bombes){    
        demineur.initPartie(cases, bombes);
        demineur.setEnvoieNotifReset(true);          
    }  
 
    public void casePressed(int id){
        if(!demineur.getGrille().getCases()[id].isDecouverte() && demineur.getEtatPartie() == etat.ENCOURS){
            demineur.casePressed = true;
            demineur.setEnvoieNotif(true);
        }
    }
    
    public void caseReleased(){
        if(demineur.getEtatPartie() == etat.ENCOURS){
            demineur.casePressed = false;
            demineur.setEnvoieNotif(true);
        }
    }
}
