import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import sun.rmi.runtime.NewThreadAction;

/**
 * Main class of the project
 * @author Lucia Blondel
 */

public class TSP {
	
	private static final String pathOfData = "/Users/lucy/Desktop/semestre4/IKM/TSP/data/";
	private static final Tool tool = new Tool();
	
	private static ArrayList<String> cities = new ArrayList<String>();
	private static int[][] distanceMatrix;
	private static int[] path;
	private static int cost;
	
	public static void main (String[] args) {
		
		long start = System.nanoTime();
		
		// read all the cities and position of the cities from the file
		ReadFile file = new ReadFile(pathOfData + "ch130.tsp");
		cities = file.getCities();
		
		// build the matrix with the distances 
		DistanceMatrix d = new DistanceMatrix(cities);
		distanceMatrix = d.getDistanceMatrix();
		
		//do simulated annealing
		SimulatedAnnealing annealing = new SimulatedAnnealing(d);
		annealing.simulatedAnnealing();
		
		path = annealing.getPath();
		cost = tool.computeCost(path, distanceMatrix);

		long end = System.nanoTime();
		
		System.out.println((end-start) * Math.pow(10, -9));
		System.out.println("After Simulated Annealing");
		for(int i = 0; i < path.length; i++) {
			System.out.println(path[i]);
		}
		System.out.println("Cost of the solution");
		System.out.println(cost);
		System.out.println("Is the solution feasible");
		System.out.println(tool.isFeasible(path, cities));
		
		/*
		double[] extrema = tool.findMaxXAndY(d);

		JFrame f = new JFrame("TSP tour");	
	    f.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    JApplet applet = new GUI(path,d);
	    f.setContentPane(new ScrollBar());
	    f.getContentPane().add("Center", applet);
	    applet.init();
	    f.pack();
	    f.setSize(new Dimension((int) extrema[0]*6, (int) extrema[1]*6));
	    f.setVisible(true);
*/
	}
	
	
}