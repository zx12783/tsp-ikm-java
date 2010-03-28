import java.util.Random;

/**
 * Simulated annealing
 * @author Lucia Blondel
 */

public class SimulatedAnnealing {
	
	private final DistanceMatrix distanceMatrix;
	private int[] path;
	
	private final Tool tool = new Tool();

	
	public SimulatedAnnealing(final DistanceMatrix distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	public void simulatedAnnealing() {
		long start = System.nanoTime();
		
		double T = 100;
		int[] current = new NearestNeighbor(distanceMatrix.getDistanceMatrix(), 0).getPath();
		System.out.println("Start Cost");
		System.out.println(tool.computeCost(current, distanceMatrix.getDistanceMatrix()));
		int[] best = current.clone();
		int[] past = current.clone();
		
		while(T > 0.1 * Math.pow(10, -300)){ 
			System.out.println(T);
			if (((System.nanoTime()) - start) * Math.pow(10, -9) > 180.0) {
				break;
			}
			int i = 0;
			while( i < 100) {
				
				int[] next = new TwoOpt(current, distanceMatrix.getDistanceMatrix(),true).getPath();
				
				while(tool.pathEquals(next, past) == true) {
					System.out.println("Avoid Cycle");
					next = new TwoOpt(next, distanceMatrix.getDistanceMatrix(),true).getPath();
				}
				
				int deltaE = tool.computeCost(next, distanceMatrix.getDistanceMatrix()) - tool.computeCost(current, distanceMatrix.getDistanceMatrix());
				System.out.println("Delta E");
				System.out.println(deltaE);
				
				if(deltaE < 0) {
					if(tool.computeCost(next, distanceMatrix.getDistanceMatrix()) < tool.computeCost(current, distanceMatrix.getDistanceMatrix())) {
						System.out.println("UPDATE BEST");
						best = next.clone();
					}
					past = current.clone();
					current = next.clone();
					
				} else {
					Random a = new Random();
					int r = a.nextInt(1);
					if(r < Math.exp(-deltaE/T)) {
						past = current.clone();
						current = next.clone();
					}
				}
				i++;
			}
			T = 0.95 * T;
		}
		
		path = new TwoOpt(best, distanceMatrix.getDistanceMatrix(),false).getPath();
		
	}
	
	public int[] getPath() {
		return path;
	}
}
