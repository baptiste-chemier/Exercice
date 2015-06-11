/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Controleur.Controleur;
import Modele.ModeleJeu;
import static Modele.ModeleJeu.etat.*;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.BorderFactory;

public abstract class VueCase extends JPanel implements Observer{
    ModeleJeu modele;
    int id;
    JLabel label;
    ImageIcon[] icones;
    int taille;
    Color couleur;
    protected boolean entered;

    public VueCase(ModeleJeu m, int i, int taillecase, ImageIcon[] listeicones, Color color){
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
            public void mouseReleased(MouseEvent e) {
                if(entered)
                    caseMouseClicked(e);
            }
        });
        
        this.add(label);     
        this.setBackground(color);     
        this.setBorder(BorderFactory.createRaisedBevelBorder());   
    }
    
    public void caseMouseClicked(MouseEvent e) {
        Controleur c = new Controleur(modele);
        if(SwingUtilities.isLeftMouseButton(e))
            c.clickGauche(id);
        if(SwingUtilities.isRightMouseButton(e)) 
            c.clickDroit(id);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean && !(boolean)arg){
            if(modele.getEtatPartie()==DEFAITE || modele.getEtatPartie()==VICTOIRE){
                if(modele.getGrille().getCases()[id].isPiege())
                    afficherBombe();
                else if(id==modele.getGrille().getDerniereCase() || modele.getEtatPartie()==VICTOIRE)
                    afficherNombre();
                else
                    effaceIcone(); 
            }
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

        public void afficherNombre(){
        int nbVoisinsPieges = modele.getGrille().getNombreVoisinsPieges(modele.getGrille().getCases()[id]);
        label.setText(Integer.toString(nbVoisinsPieges));  
        label.setIcon(null);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setBackground(new Color(206,206,206));
                
        switch(nbVoisinsPieges){
            case 0 : label.setText("");
                break;
            case 1: label.setForeground(Color.blue);
                break;
            case 2: label.setForeground(Color.green);
                break;
            case 3: label.setForeground(Color.red);
                break;
            case 4: label.setForeground(Color.orange);
                break;  
            case 5: label.setForeground(Color.cyan);
                break;
            case 6: label.setForeground(Color.pink);
                break;
            case 7: label.setForeground(Color.magenta);
                break;
            case 8: label.setForeground(Color.black);
                break;  
            default: label.setForeground(Color.black);
                break;            
        }
    }
    
    public void afficherBombe(){
        if(id==modele.getGrille().getDerniereCase()){
            this.setBackground(Color.red);
        }else
            this.setBackground(Color.pink);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        label.setIcon(icones[0]);
    }
    
    public void afficheDrapeau(){
        label.setIcon(icones[1]);
    }
    
    public void effaceIcone(){
        label.setIcon(null);
    }

    public void caseMouseExited(MouseEvent e) {
        entered = false;
        if(!modele.getGrille().getCases()[id].isDecouverte() && modele.getEtatPartie()==ENCOURS)
            this.setBackground(couleur);
    }

    public void caseMouseEntered(MouseEvent e) {
        entered = true;
        if(!modele.getGrille().getCases()[id].isDecouverte() && modele.getEtatPartie()==ENCOURS)
            this.setBackground(new Color(206,206,206));
    }
}
