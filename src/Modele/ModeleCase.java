package Modele;

/**
 * Modele de la Case 
 * @author Chemier Baptiste et Nguyen Aisi
 */
public class ModeleCase 
{
    private boolean piege;
    private boolean decouverte;
    private boolean flag;
    private final int id;
    
    public ModeleCase(boolean piege, int id)
    {
        this.piege = piege;
        this.decouverte = false;
        this.flag = false;
        this.id = id;
    }

    public boolean isPiege() 
    {
        return piege;
    }

    public void setPiege(boolean piege)
    {
        this.piege = piege;
    }

    public boolean isDecouverte() 
    {
        return decouverte;
    }

    public void setDecouverte(boolean decouverte)
    {
        this.decouverte = decouverte;
    }

    public boolean isFlag() 
    {
        return flag;
    }

    public void setFlag(boolean drapeau)
    {
        this.flag = drapeau;
    }

    public int getId() 
    {
        return id;
    } 
}
