package Modele;

import java.util.ArrayList;

public class ModeleGrille {
    private int nbCases[];
    private ModeleCase[] cases;
    private int nbBombes;
    private int nbBombesRestantes;
    private int derniereCase;
    private int nbDecouvertes;
    private int nbTotales;
    
    public ModeleGrille(int[] nbCase, int nbBombes)
    {
        this.nbCases = nbCase;
        this.nbBombes = nbBombes;
        this.nbBombesRestantes = nbBombes;
        this.derniereCase = -1;
        this.nbDecouvertes = 0;   
        this.nbTotales = nbCases[0]*nbCases[1];
        this.cases = new ModeleCase[nbTotales];
        
        for(int id = 0 ; id < nbTotales; id++){
            cases[id] = new ModeleCase(false, id);
        }
    }  
         
    public ArrayList<ModeleCase> getVoisins(ModeleCase courante)
    {
        ArrayList<ModeleCase> voisins = new ArrayList();
        boolean gauche = false;
        boolean droite = false;
        boolean haut = false; 
        boolean bas = false;
        
        if(courante.getId()%this.getNbCases()[0] == 0)
            gauche = true;
        if(courante.getId()%this.getNbCases()[0] == this.getNbCases()[0]-1)
            droite = true;  
        if(courante.getId()/this.getNbCases()[0] == 0)
            haut = true;
        if(courante.getId()/this.getNbCases()[0] == this.getNbCases()[1]-1)
            bas = true; 
        
        if(!gauche)
            voisins.add(this.getCases()[courante.getId() - 1]);
        if(!droite)
            voisins.add(this.getCases()[courante.getId() + 1]);  
        if(!haut)
            voisins.add(this.getCases()[courante.getId() - this.getNbCases()[0]]);     
        if(!bas)
            voisins.add(this.getCases()[courante.getId() + this.getNbCases()[0]]);
        
        if(!haut && !gauche)
            voisins.add(this.getCases()[courante.getId() - 1 - this.getNbCases()[0]]);
        if(!haut && !droite)
            voisins.add(this.getCases()[courante.getId() + 1 - this.getNbCases()[0]]);
        if(!bas && !gauche)
            voisins.add(this.getCases()[courante.getId() - 1 + this.getNbCases()[0]]);     
        if(!bas && !droite)
            voisins.add(this.getCases()[courante.getId() + 1 + this.getNbCases()[0]]);
        return voisins;
    }
    
    public void setBombes(int nombre, int caseVide)
    {
        if(nombre >= getCases().length-1)
        {
            for(int id = 0; id < getNbTotales(); id++)
            {
                if(id != caseVide)
                    getCases()[id].setPiege(true);
            }
            return;
        }
        
        int id;
        
        while(nombre > 0)
        {
            id = nombreAleatoire(0, getNbTotales());
            if(!cases[id].isPiege() && id != caseVide)
            {
                getCases()[id].setPiege(true);
                nombre--;
            }
        }
    }  

    public void propageVoisins(ModeleCase courante)
    {
        if(courante.isPiege() && !courante.isDecouverte())
        {
            return;
        }
        
        ArrayList<ModeleCase> caseVoisines = this.getVoisins(courante);
        courante.setDecouverte(true);
        setNbDecouvertes(getNbDecouvertes() + 1);
        
        if(this.getNbPieges(courante) == 0)
            for(int i = 0; i < caseVoisines.size(); i++)
            {
                if(!caseVoisines.get(i).isDecouverte())
                    propageVoisins(caseVoisines.get(i));
            }
    }    

    public int getNbVoisins(ModeleCase courante)
    {
        return this.getVoisins(courante).size();
    }

    public ArrayList<ModeleCase> getVoisinsPieges(ModeleCase courante)
    {
        ArrayList<ModeleCase> voisinsPieges = new ArrayList(); 
        ArrayList<ModeleCase> caseVoisines = this.getVoisins(courante);
        
        for(int i = 0; i < caseVoisines.size(); i++)
        {
            if(caseVoisines.get(i).isPiege())
                voisinsPieges.add(caseVoisines.get(i));
        }
        
        return voisinsPieges;
    } 
 
    public int getNbPieges(ModeleCase courante)
    {
        return this.getVoisinsPieges(courante).size();
    }  

    public int[] getNbCases()
    {
        return nbCases;
    }

    public ModeleCase[] getCases()
    {
        return cases;
    }

    public int getNbBombes()
    {
        return nbBombes;
    }

    public boolean isPremierCoup()
    {
        return getDerniereCase()!=-1;
    }

    public void setDerniereCase(int caseImmunise)
    {
        this.derniereCase = caseImmunise;
    }

    public int getNbDecouvertes()
    {
        return nbDecouvertes;
    }

    public int getDerniereCase()
    {
        return derniereCase;
    }
    
    public int getNbBombesRestantes()
    {
        return nbBombesRestantes;
    }

    public void setNbBombesRestantes(int nbBombesRestantes) 
    {
        this.nbBombesRestantes = nbBombesRestantes;
    }

    public void setNbDecouvertes(int nbCasesDecouvertes) 
    {
        this.nbDecouvertes = nbCasesDecouvertes;
    }
    
    private int nombreAleatoire(int min, int max)
    {
        return (int) (Math.random() * (max + min)) + min;
    }

    public int getNbTotales() 
    {
        return nbTotales;
    }  
}
