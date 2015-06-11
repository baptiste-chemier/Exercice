package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import Modele.ModeleJeu;
import java.awt.Image;

public class VueGrille extends JPanel{
    int nbcasesX;
    int nbcasesY;
    protected ModeleJeu modele;
    protected ImageIcon[] icones;
    
    public VueGrille(ModeleJeu m){
        modele = m;
        icones = new ImageIcon[2]; 
        modele.deleteObservers();
         
        initLayoutCases();
    }
  
    protected void initLayoutCases() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        int taillecase = 30;
        icones = new ImageIcon[2];
        nbcasesX = modele.getGrille().getNbCases()[0];
        nbcasesY = modele.getGrille().getNbCases()[1];
	this.setLayout(new GridLayout(nbcasesY, nbcasesX)) ;    
        
        VueAction cases;
        for (int i=0; i<nbcasesY*nbcasesX; i++) {
            int r = 190-(i/nbcasesX+i%nbcasesX)*3;
            int g = 244-(i/nbcasesX+i%nbcasesX);
            int b = 255;
            if(r<0)
                r=0;
            if(g<0)
                g=0;
            if(b<0)
                b=0;
            Color c = new Color(r,g,b);
            cases = new VueAction(modele, i, taillecase, icones,c);
            this.add(cases);
            modele.addObserver(cases);     
        }
    }
    
    protected ImageIcon imgBombe(int taillecase)
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/bombe2.png"));           	
        Image image = icon.getImage();
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        
        return icon;    
    }
        
    protected ImageIcon iconeDrapeau(int taillecase){
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/black_flag.png"));           	
        Image image = icon.getImage();  
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);         
        return icon;
    }
}
