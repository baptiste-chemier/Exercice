package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import javax.swing.*;
import Modele.ModeleJeu;
import static Modele.ModeleJeu.etat.*;

public class VueAction extends VueCase {    

    public VueAction(ModeleJeu m, int i, int taillecase, ImageIcon[] listeicones, Color color){
        super(m ,i ,taillecase, listeicones, color);
        this.add(label);     
        this.setBackground(color);     
        this.setBorder(BorderFactory.createRaisedBevelBorder());     
    }

    @Override
    public void afficherNombre(){
        int nbVoisinsPieges = modele.getGrille().getNombreVoisinsPieges(modele.getGrille().getCases()[id]);
        label.setText(Integer.toString(nbVoisinsPieges));  
        Font fontlabel;
        FontMetrics metrics;
        int taillepolice = 0;
        do{
            taillepolice++;
            fontlabel = new Font(Font.SANS_SERIF, Font.BOLD, taillepolice);
            label.setFont(fontlabel);
            metrics=getFontMetrics(label.getFont());
        }while(metrics.getHeight() < taille/3*2);
        label.setIcon(null);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setBackground(new Color(230,245,255));
                
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
    
    @Override
    public void afficherBombe(){
        if(id==modele.getGrille().getDerniereCase()){
            this.setBackground(Color.red);
        }else
            this.setBackground(new Color(230,245,255));
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        label.setIcon(icones[0]);
    }
    
    @Override
    public void afficheDrapeau(){
        label.setIcon(icones[1]);
    }
    
    @Override
    public void effaceIcone(){
        label.setIcon(null);
    }

    @Override
    public void caseMouseExited(MouseEvent e) {
        entered = false;
        if(!modele.getGrille().getCases()[id].isDecouverte() && modele.getEtatPartie()==ENCOURS)
            this.setBackground(couleur);
    }

    @Override
    public void caseMouseEntered(MouseEvent e) {
        entered = true;
        if(!modele.getGrille().getCases()[id].isDecouverte() && modele.getEtatPartie()==ENCOURS)
            this.setBackground(new Color(232,251,255));
    }
}
