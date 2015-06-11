package Modele;

import java.util.ArrayList;

public class ModelePlateau extends ModeleGrille{

    public ModelePlateau(int _nbCases[], int _nbBombes){
        super(_nbCases, _nbBombes);
        nbCasesTotales = nbCases[0]*nbCases[1];
        cases = new ModeleCase[nbCasesTotales];
        for(int id=0; id<nbCasesTotales; id++){
            cases[id] = new ModeleCase(false, id);
        }
    }  

    @Override
    public ArrayList<ModeleCase> getCasesVoisines(ModeleCase courante){
        ArrayList<ModeleCase> voisins = new ArrayList();
        boolean bordgauche = false, borddroit = false, bordhaut = false, bordbas = false;
        if(courante.getId()%this.getNbCases()[0]==0)
            bordgauche = true;
        if(courante.getId()%this.getNbCases()[0]==this.getNbCases()[0]-1)
            borddroit = true;  
        if(courante.getId()/this.getNbCases()[0]==0)
            bordhaut = true;
        if(courante.getId()/this.getNbCases()[0]==this.getNbCases()[1]-1)
            bordbas = true;      
        
        if(!bordgauche)
            voisins.add(this.getCases()[courante.getId() - 1]);
        if(!borddroit)
            voisins.add(this.getCases()[courante.getId() + 1]);  
        if(!bordhaut)
            voisins.add(this.getCases()[courante.getId() - this.getNbCases()[0]]);     
        if(!bordbas)
            voisins.add(this.getCases()[courante.getId() + this.getNbCases()[0]]);
        if(!bordhaut && !bordgauche)
            voisins.add(this.getCases()[courante.getId() - 1 - this.getNbCases()[0]]);
        if(!bordhaut && !borddroit)
            voisins.add(this.getCases()[courante.getId() + 1 - this.getNbCases()[0]]);
        if(!bordbas && !bordgauche)
            voisins.add(this.getCases()[courante.getId() - 1 + this.getNbCases()[0]]);     
        if(!bordbas && !borddroit)
            voisins.add(this.getCases()[courante.getId() + 1 + this.getNbCases()[0]]); 
        return voisins;
    }
}
