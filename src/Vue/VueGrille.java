package Vue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Modele.ModeleJeu;

public abstract class VueGrille extends JPanel {
    protected ModeleJeu modele;
    protected ImageIcon[] icones;

    public VueGrille(ModeleJeu m){
        modele = m;
        icones = new ImageIcon[2]; 
        modele.deleteObservers();
        
        initLayoutCases();
       
    }
    
    protected abstract void initLayoutCases();
}
