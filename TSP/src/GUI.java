import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JApplet;
import javax.swing.JScrollPane;


/**
 * Gui class
 * @author Lucia Blondel
 */

public class GUI extends JApplet{

	private int[] path;
	private DistanceMatrix distanceMatrix;
	
	public GUI(final int[] path, final DistanceMatrix distanceMatrix){
		this.path = path;
		this.distanceMatrix = distanceMatrix;
	}
	
	 public void init() {
		 setBackground(Color.white);
		 setForeground(Color.white);
	 }
	 
	/**
	 * Draw all lines (between one city and another one) and points (city)
	 */
	public void paint(Graphics g) {
		double[] X = distanceMatrix.getPosX();
		double[] Y = distanceMatrix.getPosY();
		
	    Graphics2D g2 = (Graphics2D) g; 
	    
	    for(int i = 0; i < X.length; i++) {
	    	g2.setPaint(Color.red);
	    	g2.fillOval((int) X[i] * 5, (int) Y[i] * 5, 4, 4);
	    }
	    
	    
	    for(int i = 0; i <= X.length - 2; i++) {
	    	g2.setPaint(Color.black);
	    	g2.draw(new Line2D.Double(X[i] * 5, Y[i] * 5, X[i+1] * 5, Y[i+1] * 5));
	    }
	  
	    g2.draw(new Line2D.Double(X[X.length - 1] * 5, Y[Y.length - 1] * 5, X[0] * 5, Y[0] * 5));

	  }
	}


		  
		    	
