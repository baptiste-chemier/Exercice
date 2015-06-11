package exercice;

import Modele.ModeleJeu;
import Vue.VueJeu;


public class Exercice
{

    public static void main(String[] args) {
        
        ModeleJeu d = new ModeleJeu();
        VueJeu fpv = new VueJeu(d);
        fpv.setVisible(true);
    }  
}
