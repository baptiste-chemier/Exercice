package packVue;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import packModele.Demineur;

/**
 * Classe de la vue d'une grille
 * @author François De Aveiro - Victor Giroud
 */
public abstract class GrilleVue extends JPanel {
    protected Demineur modele;
    protected ImageIcon[] icones;
    
    /**
    * Constructeur de la vue d'une grille
     * @param m le modèle du démineur
    */
    public GrilleVue(Demineur m){
        modele = m;
        icones = new ImageIcon[2]; 
        modele.deleteObservers();
        
        initLayoutCases();
       
    }
    
    protected abstract void initLayoutCases();
    
    /*protected ImageIcon iconeBombe(int taillecase){
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/bombe2.png"));           	
        Image image = icon.getImage();
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg); 
        
        return icon;
    }*/
    
    /*protected ImageIcon iconeDrapeau(int taillecase){
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/black_flag.png"));           	
        Image image = icon.getImage();  
        int taille = taillecase/3*2;
        Image newimg = image.getScaledInstance(taille, taille, java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);         
        return icon;
    }*/
}
