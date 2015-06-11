package Modele;

/**
 * Classe de création d'une case 
 * @author François De Aveiro - Victor Giroud
 */
public class ModeleCase {
    private boolean piege;
    private boolean decouverte;
    private boolean drapeau;
    private final int id;
    
    /**
    * Constructeur d'une case pour une grille d'un démineur
    * @param _piege vrai si la case comporte une bombe 
    * @param _id l'identifiant de la case 
    */
    public ModeleCase(boolean _piege, int _id){
        piege = _piege;
        decouverte = false;
        drapeau = false;
        id = _id;
    }

    /**
     * @return vrai si la case comporte une bombe
     */
    public boolean isPiege() {
        return piege;
    }

    /**
     * Permet de définir si la case comporte ou non une bombe
     * @param piege vrai pour que la case comporte une bombe
     */
    public void setPiege(boolean piege) {
        this.piege = piege;
    }

    /**
     * @return vrai si la case a été découverte par le joueur
     */
    public boolean isDecouverte() {
        return decouverte;
    }

    /**
     * @param decouverte vrai pour considérer la case comme découverte par le joueur
     */
    public void setDecouverte(boolean decouverte) {
        this.decouverte = decouverte;
    }

    /**
     * @return vrai si le joueur a positionné un drapeau sur cette case
     */
    public boolean isDrapeau() {
        return drapeau;
    }

    /**
     * @param drapeau vrai pour positionner un drapeau sur cette case 
     */
    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    /**
     * @return l'id de la case
     */
    public int getId() {
        return id;
    }
    
    
}
