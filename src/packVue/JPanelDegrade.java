/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packVue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Classe qui étend JPanel pour ajouter un dégradé en fond
 * @author François De Aveiro - Victor Giroud
 */
public class JPanelDegrade extends JPanel{
    
    /**
    * Constructeur d'un JPanel comportant un fond en dégradé
    */
    public JPanelDegrade() {
        super();
    }  
    
    @Override
    public void paintComponent(Graphics g) { 
        final Graphics2D g2 = (Graphics2D) g; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle bounds = getBounds();
        Paint gradientPaint = new GradientPaint(0, bounds.y, new Color(190,244,255),
                0, bounds.y + bounds.width, new Color(160,200,255));
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, bounds.width, bounds.height);
        
    }
}
