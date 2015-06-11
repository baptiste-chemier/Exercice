/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice;

import Modele.ModeleJeu;
import Vue.VueJeu;

/**
 *
 * @author Epulapp
 */
public class Exercice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ModeleJeu d = new ModeleJeu();
        VueJeu fpv = new VueJeu(d);
        fpv.setVisible(true);
    }
    
}
