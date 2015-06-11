package Vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Controleur.Controleur;
import Modele.ModeleJeu;
import static Modele.ModeleJeu.etat.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VueJeu extends JFrame implements Observer 
{
    private final ModeleJeu modele ;
    private VueGrille jpGrille;
    private GridBagLayout layout;
    private GridBagConstraints gc;
    private JMenu menuPartie;
    private JMenu menuDifficulte;
    private JMenuItem itemPartie;
    private JMenuItem itemExit;
    private JMenuItem itemFacile;
    private JMenuItem itemMoyen;
    private JMenuItem itemDifficile;
    private JMenuBar jMenuBar1;
    private JPanel panelHUD;


    public VueJeu(ModeleJeu m)
    {
        this.modele = m ;
        this.setTitle("Démineur");
        this.setDefaultCloseOperation( EXIT_ON_CLOSE ) ;
        this.getContentPane().setBackground(new Color(255,255,255));
        this.setResizable(false);
        initComponents();  
        modele.addObserver((Observer) this);
        modele.addObserver((Observer) panelHUD); 
    }  

    private void initComponents()
    {
        layout = new GridBagLayout();
        gc = new GridBagConstraints();
        this.setLayout(layout);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 0;
        gc.gridy = 0;
        panelHUD = new VueHUD(modele);
        this.add(panelHUD, gc); 
        jpGrille = new VueGrille(modele); 
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(5, 5, 5, 5);
        this.add(jpGrille, gc);   
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPartie = new javax.swing.JMenu();
        menuDifficulte = new javax.swing.JMenu();
        jMenuBar1.add(menuPartie);
        jMenuBar1.add(menuDifficulte);
        setJMenuBar(jMenuBar1);
        itemPartie = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();
        itemFacile = new javax.swing.JMenuItem();
        itemMoyen = new javax.swing.JMenuItem();
        itemDifficile = new javax.swing.JMenuItem();
        menuPartie.setText("Partie");
        itemPartie.setText("Recommencer");
        menuPartie.add(itemPartie);
        itemPartie.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem1ActionPerformed(evt);
        });    
        itemExit.setText("Quitter");
        menuPartie.add(itemExit);
        itemExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem2ActionPerformed(evt);
        });
        menuDifficulte.setText("Difficulte");
        itemFacile.setText("Facile");
        menuDifficulte.add(itemFacile);
        itemFacile.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem3ActionPerformed(evt);
        });  
        itemMoyen.setText("Moyen");
        menuDifficulte.add(itemMoyen);
        itemMoyen.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem4ActionPerformed(evt);
        });  
        itemDifficile.setText("Difficile");
        menuDifficulte.add(itemDifficile);
        itemDifficile.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem5ActionPerformed(evt);
        });  
        pack();     
    }                                

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)
    {                                       
        Controleur c = new Controleur(modele);
        c.newParty(modele.getGrille().getNbCases(), modele.getGrille().getNbBombes());
    }      

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)
    {                                       
        System.exit(1);
    }  
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt)
    {                                       
        if(modele.getEtatPartie()!=EnJeu)
        {
           Controleur c = new Controleur(modele);
            c.newParty(modele.getGrille().getNbCases(), 10);         
        }
        else
            JOptionPane.showMessageDialog(null, "Veuillez terminer la partie avant") ;
    }  
        
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt)
    {                                       
        if(modele.getEtatPartie()!=EnJeu)
        {
           Controleur c = new Controleur(modele);
            c.newParty(modele.getGrille().getNbCases(), 20);         
        }
        else
            JOptionPane.showMessageDialog(null, "Veuillez terminer la partie avant") ;
    }  
            
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                       
        if(modele.getEtatPartie()!=EnJeu)
        {
           Controleur c = new Controleur(modele);
            c.newParty(modele.getGrille().getNbCases(), 30);         
        }        
        else
            JOptionPane.showMessageDialog(null, "Veuillez terminer la partie avant") ;
    }  

    @Override
    public void update(Observable o, Object arg) 
    {
        if(arg instanceof Boolean && (boolean)arg)
        {
            this.remove(jpGrille);
            this.remove(panelHUD);
            panelHUD = new VueHUD(modele);  
            jpGrille = new VueGrille(modele);  
            modele.addObserver(this);
            modele.addObserver((Observer)panelHUD);      
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.gridx = 0;
            gc.gridy = 0; 
            gc.insets = new Insets(0, 0, 0, 0);
            this.add(panelHUD, gc); 
            gc.fill = GridBagConstraints.NONE;
            gc.gridx = 0;
            gc.gridy = 1;
            gc.insets = new Insets(5, 5, 5, 5);
            this.add(jpGrille, gc); 
            pack();      
        }
    }
    
    public static void main( String[] args )
    {
        VueJeu fpv = new VueJeu(new ModeleJeu());
        fpv.setVisible(true) ;
        fpv.setLocationRelativeTo(null);
    }   
}

