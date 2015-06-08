package packModele;

import java.util.ArrayList;

/**
 * Classe de création d'une grille de cases
 * @author François De Aveiro - Victor Giroud
 */
public abstract class Grille {
    protected int nbCases[]; // 0 > X ; 1 > Y ; 2 > Z ; 3 > ...
    protected Case[] cases;
    protected int nbBombes;
    protected int nbBombesRestantes;
    protected int derniereCase;
    protected int nbCasesDecouvertes;
    protected int nbCasesTotales;
    
    
    /**
     * Constructeur d'une grille de cases
     * @param _nbCases un tableau contenant le nombre de cases (largeur, hauteur, ..)
     * @param _nbBombes le nombre de bombes dans la grille
     */
    public Grille(int _nbCases[], int _nbBombes){
        nbCases = _nbCases;
        nbBombes = _nbBombes;
        nbBombesRestantes = _nbBombes;
        derniereCase = -1;
        nbCasesDecouvertes = 0;   
    }  
        
    /**
     * @param courante la case dont on veut connaitre les voisins
     * @return une arrayList contenant toutes les cases voisines de celle passée en paramètre
     */    
    public abstract ArrayList<Case> getCasesVoisines(Case courante);    
        
        
    /**
     * @param caseInvulnerable l'identifiant de la première case découverte qui ne pourra pas contenir de bombe
     * @param nombre le nombre de bombes à placer dans la grille
     */
    public void setBombes(int nombre, int caseInvulnerable){
        if(nombre>=getCases().length-1){
            for(int id=0; id<getNbCasesTotales(); id++){
                if(id!=caseInvulnerable)
                    getCases()[id].setPiege(true);
            }
            return;
        }
        int id;
        while(nombre>0){
            id = nombreAleatoire(0, getNbCasesTotales());
            if(!cases[id].isPiege() && id!=caseInvulnerable){
                getCases()[id].setPiege(true);
                nombre--;
            }
        }
    }  
    
    /**
     * Algorithme récursif utilisé pour découvrir toutes les cases voisines piégéees à partir de celle passée en paramètre
     * @param courante la case qui débutera l'algorithme de récursion
     */   
    public void propagationVoisins(Case courante){
        if(courante.isPiege() && !courante.isDecouverte()){
            return;
        }
        ArrayList<Case> caseVoisines = this.getCasesVoisines(courante);
        courante.setDecouverte(true);
        setNbCasesDecouvertes(getNbCasesDecouvertes() + 1);
        if(this.getNombreVoisinsPieges(courante)==0)
        for(int i=0; i<caseVoisines.size(); i++){
            if(!caseVoisines.get(i).isDecouverte())
                propagationVoisins(caseVoisines.get(i));
        }
    }    
        
    /**
     * @param courante la case dont on veut connaitre le nombre de voisins
     * @return le nombre de voisins de la case passée en paramètre
     */
    public int getNombreVoisins(Case courante){
        return this.getCasesVoisines(courante).size();
    }
    
    /**
     * @param courante la case dont on veut connaitre les voisins qui ont une bombe
     * @return le nombre de voisins qui ont une bombe de la case passée en paramètre
     */
    public ArrayList<Case> getVoisinsPieges(Case courante){
        ArrayList<Case> voisinsPieges = new ArrayList(); 
        ArrayList<Case> caseVoisines = this.getCasesVoisines(courante);
        for(int i=0; i<caseVoisines.size(); i++){
            if(caseVoisines.get(i).isPiege())
                voisinsPieges.add(caseVoisines.get(i));
        }
        return voisinsPieges;
} 
    
    /**
     * Constructeur d'une grille de cases
     * @param courante la case dont on veut connaitre le nombre de voisin
     * @return le nombre de voisins de la case passée en paramètre
     */   
    public int getNombreVoisinsPieges(Case courante){
        return this.getVoisinsPieges(courante).size();
    }  
    
    /**
     * @return le nombre de cases de chaque dimension de la grille
     */
    public int[] getNbCases() {
        return nbCases;
    }

    /**
     * @return le tableau de toutes les cases de la grille 
     */
    public Case[] getCases() {
        return cases;
    }

    /**
     * @return le nombre de bombes présentes dans la grille
     */
    public int getNbBombes() {
        return nbBombes;
    }
    
    /**
     * @return si une case a déjà été découverte dans la grille
     */
    public boolean isPremierCoup() {
        return getDerniereCase()!=-1;
    }

    /**
     * @param caseImmunise la case qui ne pourra pas contenir de bombe
     */
    public void setDerniereCase(int caseImmunise) {
        this.derniereCase = caseImmunise;
    }

    /**
     * @return le nombre de cases déjà découvertes par le joueur
     */
    public int getNbCasesDecouvertes() {
        return nbCasesDecouvertes;
    }

    /**
     * @return la dernière case découverte par le joueur
     */
    public int getDerniereCase() {
        return derniereCase;
    }

    /**
     * @return le nombre de bombes restantes en fonction du nombre de drapeaux
     */
    public int getNbBombesRestantes() {
        return nbBombesRestantes;
    }

    /**
     * @param nbBombesRestantes le nombre de bombes restantes en fonction du nombre de drapeaux
     */
    public void setNbBombesRestantes(int nbBombesRestantes) {
        this.nbBombesRestantes = nbBombesRestantes;
    }

    /**
     * @param nbCasesDecouvertes le nombre de cases découvertes par le joueur
     */
    public void setNbCasesDecouvertes(int nbCasesDecouvertes) {
        this.nbCasesDecouvertes = nbCasesDecouvertes;
    }
    
    private int nombreAleatoire(int min, int max){
        return (int) (Math.random() * (max + min)) + min;
    }

    /**
     * @return the nbCasesTotales
     */
    public int getNbCasesTotales() {
        return nbCasesTotales;
    }
        
}
