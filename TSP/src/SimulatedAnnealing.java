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
	private long seed;
	private TwoOpt twoOpt;
	
	private String nameOfMap;
	
	private final Tool tool = new Tool();

	
	public SimulatedAnnealing(final DistanceMatrix distanceMatrix, final String nameOfMap) {
		this.nameOfMap = nameOfMap;
		this.distanceMatrix = distanceMatrix;
		r = new Random(TSP.maps.get(nameOfMap).getSeed());
		try {
			seed = tool.getCurrentSeed(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		twoOpt = new TwoOpt(distanceMatrix.getDistanceMatrix());
	}

	public void simulatedAnnealing() {
		long start = System.nanoTime();
		
		double T = TSP.maps.get(nameOfMap).getStartTemperature();
		double alpha = TSP.maps.get(nameOfMap).getAlpha();
		
		int[] current = new NearestNeighbor(distanceMatrix.getDistanceMatrix(), 0).getPath();
		twoOpt.setPath(current);
	
		int[] best = current.clone();
		int bestCost = tool.computeCost(best, distanceMatrix.getDistanceMatrix());
		int currentCost = bestCost;
		
		
		while(true){
			//System.out.println(T + " " + bestCost + " " + currentCost);
			if (((System.nanoTime()) - start) * Math.pow(10, -9) > 180.0) {
				break;
			}
			int i = 0;
			while( i < 50*current.length) {
				
				int rI = r.nextInt(current.length);
				int rJ = r.nextInt(current.length);
				
				if(rI == rJ) {
					rJ = (rJ+1) % current.length;
				}
				
				if(rJ < rI) {
					int temp = rJ;
					rJ = rI;
					rI = temp;
				}
				int delta = twoOpt.computeGain(rI, rJ);
				
				
				if(delta < 0) {
					twoOpt.exchange(rI, rJ);
					currentCost = tool.computeCost(current, distanceMatrix.getDistanceMatrix());
					if(currentCost < bestCost) {
							best = (twoOpt.getPath()).clone();
							bestCost = currentCost;
					}
				} else {
					double a = r.nextInt(101)/100.0;
					if(a < Math.exp(-delta/T)) {
						twoOpt.exchange(rI, rJ);
						currentCost = tool.computeCost(current, distanceMatrix.getDistanceMatrix());
					}
				}
				i++;
			}
			T = alpha * T;
		}
		
		twoOpt.twoOpt(best, false);

		path = twoOpt.getPath();
		
	}
	
	public int[] getPath() {
		return path;
	}
	
	public long getCurrentSeed() {
		return seed;
	}
}
