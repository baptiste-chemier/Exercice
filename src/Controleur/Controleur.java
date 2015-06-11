package Controleur;

import Modele.ModeleJeu;
import javax.swing.JOptionPane;

public class Controleur{
    private final ModeleJeu game;
    
    public Controleur(ModeleJeu d){
        this.game = d;
    }  
    
    public void leftClick(int numcase){
        if(stateParty())
            return;
        
        if(!game.getGrille().getCases()[numcase].isDecouverte() && !game.getGrille().getCases()[numcase].isFlag()){
            game.getGrille().getCases()[numcase].setDecouverte(true);
            if(!game.getGrille().isPremierCoup()){
                game.getGrille().setBombes(game.getGrille().getNbBombes(), numcase);
            }
            game.getGrille().setDerniereCase(numcase);
            game.getGrille().propagationVoisins(game.getGrille().getCases()[numcase]);
            if(game.testPartieTerminee(numcase)==ModeleJeu.etat.DEFAITE){
                game.setEtatPartie(ModeleJeu.etat.DEFAITE);
                JOptionPane.showMessageDialog(null, "Veuillez avez perdu !") ;
            }
            else if(game.testPartieTerminee(numcase)==ModeleJeu.etat.VICTOIRE){
                game.setEtatPartie(ModeleJeu.etat.VICTOIRE);           
                JOptionPane.showMessageDialog(null, "Veuillez avez gagné !") ;
            }
            game.setEnvoieNotif(true);
        }
    }

    public void rightClick(int numcase){
        if(stateParty())
            return; 
        
        if(!game.getGrille().getCases()[numcase].isDecouverte()){
            if(!game.getGrille().getCases()[numcase].isFlag() && game.getGrille().getNbBombesRestantes() > 0){
                game.getGrille().getCases()[numcase].setFlag(true);
                game.getGrille().setNbBombesRestantes(game.getGrille().getNbBombesRestantes()-1);
            }
            else if (game.getGrille().getCases()[numcase].isFlag()){
                game.getGrille().getCases()[numcase].setFlag(false); 
                game.getGrille().setNbBombesRestantes(game.getGrille().getNbBombesRestantes()+1);    
            }
            game.setEnvoieNotif(true);
        }
    }  

    public void newParty(int[] cases, int bombes){    
        game.initPartie(cases, bombes);
        game.setEnvoieNotifReset(true);          
    }  
    
    public boolean stateParty(){
        return game.getEtatPartie()!=ModeleJeu.etat.ENCOURS;
    }
}
