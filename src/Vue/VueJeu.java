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
import javax.swing.JTextField;
import Controleur.Controleur;
import Modele.ModeleJeu;

public class VueJeu extends JFrame implements Observer {
    private JTextField jt ;
    private final ModeleJeu modele ;
    private VueGrille jpGrille;
    private GridBagLayout layout;
    private GridBagConstraints gc;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem4;
    private JMenuBar jMenuBar1;

    public VueJeu(ModeleJeu m){
        this.modele = m ;
        this.setTitle("Démineur");
        this.setDefaultCloseOperation( EXIT_ON_CLOSE ) ;
        this.getContentPane().setBackground(new Color(220,250,255));
        this.setResizable(false);
        initComponents();  
        modele.addObserver((Observer) this);
    }  

    private void initComponents(){
        layout = new GridBagLayout();
        gc = new GridBagConstraints();
        this.setLayout(layout);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 0;
        gc.gridy = 0;
        jpGrille = new GrilleCarreVue(modele); 
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(5, 5, 5, 5);
        this.add(jpGrille, gc);   
        // Barre de menu
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1.setText("Partie");
        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);
        jMenuItem1.setText("Nouvelle Partie");
        jMenu1.add(jMenuItem1);
        jMenuItem1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem1ActionPerformed(evt);
        });    
        jMenuItem4.setText("Quitter");
        jMenu1.add(jMenuItem4);
        jMenuItem4.addActionListener((java.awt.event.ActionEvent evt) -> {
            jMenuItem4ActionPerformed(evt);
        });      
        pack();     
    }                                

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        Controleur c = new Controleur(modele);
        c.controleNouvellePartie(modele.getGrille().getNbCases(), modele.getGrille().getNbBombes());
    }   

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) { 
    }       

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.exit(1);
    }  

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean && (boolean)arg){
            this.remove(jpGrille);
            jpGrille = new GrilleCarreVue(modele);  
            modele.addObserver(this);
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.gridx = 0;
            gc.gridy = 0; 
            gc.insets = new Insets(0, 0, 0, 0);
            gc.fill = GridBagConstraints.NONE;
            gc.gridx = 0;
            gc.gridy = 1;
            gc.insets = new Insets(5, 5, 5, 5);
            this.add(jpGrille, gc); 
            pack();      
        }
    }   
    
    public static void main( String[] args ) {
        VueJeu fpv = new VueJeu(new ModeleJeu());
        fpv.setVisible(true) ;
        fpv.setLocationRelativeTo(null);
    }   
}

