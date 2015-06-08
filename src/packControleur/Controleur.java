package packControleur;

import packModele.Demineur;
import packModele.Demineur.etat;

/**
 * Controle de l'ensemble des actions effectués sur la vue par l'utilisateur, notifications du modèle
 * @author François De Aveiro - Victor Giroud
 */
public class Controleur{
    private final Demineur demineur;
    
    /**
    * Constructeur du controleur 
    * @param d le modèle du démineur
    */
    public Controleur(Demineur d){
        this.demineur = d;
    }  
        
    /** 
    * @return vrai si la partie est terminée (victoire ou défaite)
    */
    public boolean controlePartieTerminnee(){
        return demineur.getEtatPartie()!=Demineur.etat.ENCOURS;
    }
    
    /** 
    * Controle en cas de clic gauche sur une case avec la souris 
     * @param numcase identifiant de la case 
    */
    public void controleCaseClickedLeft(int numcase){
         // On controle que la partie est toujours en cours;
        if(controlePartieTerminnee())
            return;
        
        // On controle que la case n'a pas déjà été découverte et qu'il n'y a pas de drapeau 
        if(!demineur.getGrille().getCases()[numcase].isDecouverte() && !demineur.getGrille().getCases()[numcase].isDrapeau()){
            // On modifie le modèle
            demineur.getGrille().getCases()[numcase].setDecouverte(true);
            // Si c'est le premier coup, ça ne peut pas être une bombe : on les répartit aléatoirement ensuite
            if(!demineur.getGrille().isPremierCoup()){
                demineur.getGrille().setBombes(demineur.getGrille().getNbBombes(), numcase);
            }
            demineur.getGrille().setDerniereCase(numcase);
            demineur.getGrille().propagationVoisins(demineur.getGrille().getCases()[numcase]);
            // On vérifie si la partie est terminée
            if(demineur.testPartieTerminee(numcase)==Demineur.etat.DEFAITE){
                demineur.setEtatPartie(Demineur.etat.DEFAITE);
            }
            else if(demineur.testPartieTerminee(numcase)==Demineur.etat.VICTOIRE){
                demineur.setEtatPartie(Demineur.etat.VICTOIRE);            
            }
            // On notifie 
            demineur.setEnvoieNotif(true);
        }
    }
    
    /** 
    * Controle en cas de clic droit sur une case avec la souris 
     * @param numcase identifiant de la case 
    */
    public void controleCaseClickedRight(int numcase){
         // On controle que la partie est toujours en cours;
        if(controlePartieTerminnee())
            return; 
        
        if(!demineur.getGrille().getCases()[numcase].isDecouverte()){
            // On modifie le modèle
            if(!demineur.getGrille().getCases()[numcase].isDrapeau()){
                demineur.getGrille().getCases()[numcase].setDrapeau(true);
                demineur.getGrille().setNbBombesRestantes(demineur.getGrille().getNbBombesRestantes()-1);
            }
            else{
                demineur.getGrille().getCases()[numcase].setDrapeau(false); 
                demineur.getGrille().setNbBombesRestantes(demineur.getGrille().getNbBombesRestantes()+1);    
            }
            // On notifie 
            demineur.setEnvoieNotif(true);
        }
    }  

    
    /** 
    * Controle en cas de choix d'une nouvelle partie
     * @param cases tableau avec le nombre de cases de la grille (largeur, hauteur)
     * @param bombes le nombre de bombes présentes dans la grille
    */ 
    public void controleNouvellePartie(int[] cases, int bombes){    
        demineur.initPartie(cases, bombes);
        demineur.setEnvoieNotifReset(true);          
    }  
 
    /** 
    * Controle quand une case est appuyée
     * @param id identifiant de la case
    */ 
    public void casePressed(int id){
        if(!demineur.getGrille().getCases()[id].isDecouverte() && demineur.getEtatPartie() == etat.ENCOURS){
            demineur.casePressed = true;
            demineur.setEnvoieNotif(true);
        }
    }
    
    /** 
    * Controle quand une case est relachée
    */ 
    public void caseReleased(){
        if(demineur.getEtatPartie() == etat.ENCOURS){
            demineur.casePressed = false;
            demineur.setEnvoieNotif(true);
        }
    }
 
    
    
}
