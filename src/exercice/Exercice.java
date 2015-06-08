/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice;

import packModele.Demineur;
import packVue.FenetrePrincipaleVue;

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
        
        Demineur d = new Demineur();
        FenetrePrincipaleVue fpv = new FenetrePrincipaleVue(d);
        fpv.setVisible(true);
    }
    
}
