package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import Modele.ModeleJeu;
import java.awt.Image;

public class VueGrille extends JPanel
{
    int nbCasesX;
    int nbCasesY;
    protected ModeleJeu modele;
    protected ImageIcon[] icones;
    private int taille;
    
    public VueGrille(ModeleJeu m)
    {
        modele = m;
        icones = new ImageIcon[3]; 
        modele.deleteObservers();
        initComponent();
    }
  
    private void initComponent()
    {
        taille = 30;
        icones[0] = imgBombe();
        icones[1] = imgDrapeau();
        icones[2] = imgExplosion();
        nbCasesX = modele.getGrille().getNbCases()[0];
        nbCasesY = modele.getGrille().getNbCases()[1];
	this.setLayout(new GridLayout(nbCasesY, nbCasesX)) ;    
        VueCase cases;
        
        for (int i = 0; i < nbCasesY * nbCasesX; i++) 
        {
            cases = new VueCase(modele, i, icones) {};
            this.add(cases);
            modele.addObserver(cases);     
        }
    }
    
    private ImageIcon imgBombe()
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("../img/bombe.png"));           	
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(taille / 3 * 2, taille / 3 * 2, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        return icon;    
    }
    
    private ImageIcon imgExplosion()
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("../img/explosion.png"));           	
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(taille / 3 * 2, taille / 3 * 2, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        return icon;    
    }
        
    private ImageIcon imgDrapeau()
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/flag.png"));           	
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(taille / 3 * 2, taille / 3 * 2, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);        
        return icon;
    }
}
