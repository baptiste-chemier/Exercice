package packVue;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import packModele.Demineur;

/**
 * Classe de la vue d'une grille carrée
 * @author François De Aveiro - Victor Giroud
 */
public class GrilleCarreVue extends GrilleVue {
    int nbcasesX, nbcasesY;
    
    /**
    * Constructeur de la vue d'une grille carrée
     * @param m le modèle du démineur
    */
    public GrilleCarreVue(Demineur m){
        super(m);
    }
  
    @Override
    protected void initLayoutCases() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        int taillecase = 30;
        icones = new ImageIcon[2];
        //icones[0] = iconeBombe(taillecase);
        //icones[1] = iconeDrapeau(taillecase);  
        
        nbcasesX = modele.getGrille().getNbCases()[0];
        nbcasesY = modele.getGrille().getNbCases()[1];
	this.setLayout(new GridLayout(nbcasesY, nbcasesX)) ;    
        

        CaseCarreVue cases;
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
            cases = new CaseCarreVue(modele, i, taillecase, icones,c);
            this.add(cases);
            // On ajoute la vue au modèle
            modele.addObserver(cases);     
        }
    }
}
