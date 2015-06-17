package Controleur;

import Modele.ModeleJeu;
import javax.swing.JOptionPane;

public class Demineur
{
    private final ModeleJeu game;
    
    public Demineur(ModeleJeu d)
    {
        this.game = d;
    }  
    
    public void leftClick(int idCase)
    {
        if(stateParty())
            return;
                
        if(!game.getGrille().getCases()[idCase].isDecouverte() && !game.getGrille().getCases()[idCase].isFlag())
        {
            game.getGrille().getCases()[idCase].setDecouverte(true);
            if(!game.getGrille().isPremierCoup())
            {
                //game.getChrono().start();
                game.getGrille().setBombes(game.getGrille().getNbBombes(), idCase);
            }
            game.getGrille().setDerniereCase(idCase);
            game.getGrille().propageVoisins(game.getGrille().getCases()[idCase]);
            
            if(game.finPartie(idCase) == ModeleJeu.etat.Perdu)
            {
                game.setEtatPartie(ModeleJeu.etat.Perdu);
                //game.getChrono().stop();
                JOptionPane.showMessageDialog(null, "Vous avez perdu !") ;
            }
            
            else if(game.finPartie(idCase) == ModeleJeu.etat.Gagner)
            {
                game.setEtatPartie(ModeleJeu.etat.Gagner);           
                //game.getChrono().stop();
                JOptionPane.showMessageDialog(null, "Vous avez gagné !") ;
            }
        }
    }

    public void rightClick(int idCase)
    {
        if(stateParty())
            return; 
        
        if(!game.getGrille().getCases()[idCase].isDecouverte())
        {
            if(!game.getGrille().getCases()[idCase].isFlag() && game.getGrille().getNbBombesRestantes() > 0)
            {
                game.getGrille().getCases()[idCase].setFlag(true);
                game.getGrille().setNbBombesRestantes(game.getGrille().getNbBombesRestantes()-1);
            }
            
            else if (game.getGrille().getCases()[idCase].isFlag())
            {
                game.getGrille().getCases()[idCase].setFlag(false); 
                game.getGrille().setNbBombesRestantes(game.getGrille().getNbBombesRestantes()+1);    
            }
        }
    }  

    public void newParty(int[] cases, int bombes)
    {    
        game.iniComponent(cases, bombes);
        game.notifierObservateurs(true);
    }  
    
    public boolean stateParty()
    {
        return game.getEtatPartie() != ModeleJeu.etat.EnJeu;
    }
}
