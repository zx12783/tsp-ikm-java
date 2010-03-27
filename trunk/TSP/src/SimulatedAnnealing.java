import java.util.Random;


public class SimulatedAnnealing {
	
	private final int[][] distanceMatrix;
	private int[] path;
	
	private final Tool tool = new Tool();

	
	public SimulatedAnnealing(int[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	public void simulatedAnnealing() {
		
		double T = 100;
		int[] current = new NearestNeighbor(distanceMatrix, 0).getPath();
		int[] best = current.clone();
		
		System.out.println(best);
		
		while(T > 0.1*Math.pow(10, -310)) {
			System.out.println(T);
			int i = 0;
			while( i < 100) {
				
				int[] next = new TwoOpt(current, distanceMatrix,true).getPath();
				
				int deltaE = tool.computeCost(next, distanceMatrix) - tool.computeCost(current, distanceMatrix);
				
				if(deltaE < 0) {
					current = next.clone();
					if(tool.computeCost(next, distanceMatrix) < tool.computeCost(current, distanceMatrix)) {
						best = next.clone();
					}
				} else {
					Random a = new Random();
					int r = a.nextInt(1);
					if(r < Math.exp(-deltaE/T)) {
						current = next.clone();
					}
				}
				i++;
			}
			T = 0.95 * T;
		}
		
		path = best.clone();
		
	}
	
	public int[] getPath() {
		return path;
	}
}
