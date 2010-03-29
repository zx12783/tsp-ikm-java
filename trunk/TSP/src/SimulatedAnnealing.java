import java.util.Iterator;
import java.util.Random;

/**
 * Simulated annealing
 * @author Lucia Blondel
 */

public class SimulatedAnnealing {
	
	private final DistanceMatrix distanceMatrix;
	private int[] path;
	private Random r;
	private TwoOpt twoOpt;
	
	private final Tool tool = new Tool();

	
	public SimulatedAnnealing(final DistanceMatrix distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
		r = new Random();
		twoOpt = new TwoOpt(distanceMatrix.getDistanceMatrix());
	}

	public void simulatedAnnealing() {
		long start = System.nanoTime();
		
		double T = 100;
		int[] current = new NearestNeighbor(distanceMatrix.getDistanceMatrix(), 0).getPath();
		System.out.println("Start Cost");
		System.out.println(tool.computeCost(current, distanceMatrix.getDistanceMatrix()));
		int[] best = current.clone();
		
		int counter = 0;
		
		while(T > 1){ // && counter < distanceMatrix.getNumberOfCities()/2 0.1 * Math.pow(10, -300)
			System.out.println(T);
			if (((System.nanoTime()) - start) * Math.pow(10, -9) > 180.0) {
				break;
			}
			int i = 0;
			while( i < 200) {
				
				twoOpt.twoOpt(current, true);
				int[] next = twoOpt.getPath();
				
				int deltaE = tool.computeCost(next, distanceMatrix.getDistanceMatrix()) - tool.computeCost(current, distanceMatrix.getDistanceMatrix());
				
				if(deltaE < 0) {
					if(tool.computeCost(next, distanceMatrix.getDistanceMatrix()) < tool.computeCost(best, distanceMatrix.getDistanceMatrix())) {
						System.out.println("UPDATE BEST");
						best = next.clone();
					}
					current = next.clone();
					
				} else {
					int a = r.nextInt(1);
					if(a < Math.exp(-deltaE/T)) {
						current = next.clone();
					}
				}
				i++;
			}
			T = 0.95 * T;
			//counter++;
		}
		
		twoOpt.twoOpt(best, false);
		
		path = twoOpt.getPath();
		
	}
	
	public int[] getPath() {
		return path;
	}
}
