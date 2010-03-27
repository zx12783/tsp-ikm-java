import java.util.ArrayList;

import sun.rmi.runtime.NewThreadAction;


public class TSP {
	
	private static final String pathOfData = "/Users/lucy/Desktop/semestre4/IKM/TSP/data/";
	private static final Tool tool = new Tool();
	
	private static ArrayList<String> cities = new ArrayList<String>();
	private static int[][] distanceMatrix = null;

	private static int cost = 0;
	
	public static void main (String[] args) {
		
		int[] path;
		
		long start = System.nanoTime();

		
		// read all the cities and position of the cities from the file
		ReadFile file = new ReadFile(pathOfData + "eil76.tsp");
		cities = file.getCities();
		
		// build the matrix with the distances 
		distanceMatrix = new DistanceMatrix(cities).getDistanceMatrix();
		
		// calculate nearest neighbor
		NearestNeighbor nearestNeighborSolution = new NearestNeighbor(distanceMatrix, 0);
		path = nearestNeighborSolution.getPath();
		cost = tool.computeCost(path, distanceMatrix);
		System.out.println(cost);
		
		TwoOpt twoOpt = new TwoOpt(path, distanceMatrix);
		path = twoOpt.getPath();
		cost = tool.computeCost(path, distanceMatrix);
		System.out.println(cost);
		
		long end = System.nanoTime();
		
		System.out.println((end-start) * Math.pow(10, -9));
		System.out.println(path);
		
	}
}
