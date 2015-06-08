/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packVue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import packControleur.Controleur;
import packModele.Demineur;
import static packModele.Demineur.etat.*;

/**
 * Classe de la vue d'une case 
 * @author François De Aveiro - Victor Giroud
 */
public abstract class CaseVue extends JPanel implements Observer{
    Demineur modele;
    int id;
    JLabel label;
    ImageIcon[] icones;
    int taille;
    Color couleur;
    protected boolean entered;
    
    /**
    * Constructeur de la vue d'une case
    * @param m le modèle du démineur
    * @param i l'identifiant de la case correspondante
    * @param taillecase la taille de case (taillecase*taillecase)
    * @param listeicones les icones utilisées pour le drapeau et la bombe
    * @param color la couleur de la case
    */
    public CaseVue(Demineur m, int i, int taillecase, ImageIcon[] listeicones, Color color){
        modele = m;
        id = i;
        icones = listeicones;
        taille = taillecase;
        label = new JLabel();
        couleur = color;
        this.setPreferredSize(new Dimension(taillecase,taillecase));     
        entered = false;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent evt) {
                caseMouseExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                caseMouseEntered(evt);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                caseMousePressed(e);
            }
           
           @Override
            public void mouseReleased(MouseEvent e) {
                if(entered)
                    caseMouseClicked(e);
                caseMouseReleased(e);
            }
        });
    }
    
    public abstract void caseMouseExited(MouseEvent e);
    public abstract void caseMouseEntered(MouseEvent e);
    
    public void caseMouseClicked(MouseEvent e) {
        Controleur c = new Controleur(modele);
        // On appelle le controleur
        if(SwingUtilities.isLeftMouseButton(e))
            c.controleCaseClickedLeft(id);
        if(SwingUtilities.isRightMouseButton(e)) 
            c.controleCaseClickedRight(id);
        //System.out.println("clic " + id);
    }
    
    
    public void caseMousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            Controleur c = new Controleur(modele);
            c.casePressed(id);
        }
    }

    public void caseMouseReleased(MouseEvent e) {
        Controleur c = new Controleur(modele);
        c.caseReleased();
    }
    
    
    public abstract void afficherNombre(); 
    public abstract void afficherBombe();
    public abstract void afficheDrapeau();
    public abstract void effaceIcone();
    
    @Override
    public void update(Observable o, Object arg) {
        // si la partie n'est pas réinitialisée
        if(arg instanceof Boolean && !(boolean)arg){
            // si la partie est terminée
            if(modele.getEtatPartie()==DEFAITE || modele.getEtatPartie()==VICTOIRE){
                if(modele.getGrille().getCases()[id].isPiege())
                    afficherBombe();
                else if(id==modele.getGrille().getDerniereCase() || modele.getEtatPartie()==VICTOIRE)
                    afficherNombre();
                else
                    effaceIcone(); 
            } // si la partie est toujours en cours
            else if(id<modele.getGrille().getCases().length && modele.getGrille().getCases()[id].isDecouverte()){
                if(modele.getGrille().getCases()[id].isPiege()){
                    afficherBombe();
                }else{
                    afficherNombre();                
                }          
            }else if(id<modele.getGrille().getCases().length && modele.getGrille().getCases()[id].isDrapeau()){
                afficheDrapeau();
            }
            else{
                effaceIcone();
            }  
            repaint();
        }
    }
    
}
