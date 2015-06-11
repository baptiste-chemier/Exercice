package exercice;

import Modele.ModeleJeu;
import Vue.VueJeu;


public class Exercice
{

    public static void main(String[] args)
    {
        ModeleJeu jeu = new ModeleJeu();
        VueJeu fpv = new VueJeu(jeu);
        fpv.setVisible(true);
    }  
}
