package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import Modele.ModeleJeu;
import java.awt.Image;

public final class VueGrille extends JPanel{
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
        icones = new ImageIcon[3];
        icones[0] = imgBombe(taillecase);
        icones[1] = imgDrapeau(taillecase);
        icones[2] = imgExplosion(taillecase);
        nbcasesX = modele.getGrille().getNbCases()[0];
        nbcasesY = modele.getGrille().getNbCases()[1];
	this.setLayout(new GridLayout(nbcasesY, nbcasesX)) ;    
        
        VueCase cases;
        for (int i=0; i<nbcasesY*nbcasesX; i++) {
            Color c = new Color(255, 255, 255);
            cases = new VueCase(modele, i, taillecase, icones, c) {};
            this.add(cases);
            modele.addObserver(cases);     
        }
    }
    
    protected ImageIcon imgBombe(int taillecase)
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("../img/bombe.png"));           	
        Image image = icon.getImage();
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        
        return icon;    
    }
    
        protected ImageIcon imgExplosion(int taillecase)
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("../img/explosion.png"));           	
        Image image = icon.getImage();
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        
        return icon;    
    }
        
    protected ImageIcon imgDrapeau(int taillecase){
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/flag.png"));           	
        Image image = icon.getImage();  
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);         
        return icon;
    }
}
