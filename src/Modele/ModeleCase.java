package Modele;

public class ModeleCase {
    private boolean piege;
    private boolean decouverte;
    private boolean drapeau;
    private final int id;
    
    public ModeleCase(boolean _piege, int _id){
        piege = _piege;
        decouverte = false;
        drapeau = false;
        id = _id;
    }

    public boolean isPiege() {
        return piege;
    }

    public void setPiege(boolean piege) {
        this.piege = piege;
    }

    public boolean isDecouverte() {
        return decouverte;
    }

    public void setDecouverte(boolean decouverte) {
        this.decouverte = decouverte;
    }

    public boolean isDrapeau() {
        return drapeau;
    }

    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public int getId() {
        return id;
    } 
}
