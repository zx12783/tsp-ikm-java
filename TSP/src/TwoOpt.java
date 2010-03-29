
/**
 * TwoOpt class
 * @author Lucia Blondel
 */

public class TwoOpt {

	private int[] path;
	private int[][] distanceMatrix;
	
	private Tool tool = new Tool();
	
	public TwoOpt(int[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	
	/**
	 * Constructor of a path that it's better (in cost-sense) than the given one
	 * @param path
	 * @param distanceMatrix
	 */
	public void twoOpt(int[] path, final boolean firstImprovement) {
		this.path = path;
		
		int bestGain = Integer.MAX_VALUE;
		int bestI = Integer.MAX_VALUE;
		int bestJ = Integer.MAX_VALUE;
		

		while(bestGain > 0) {
			bestGain = 0;
			

			for(int i = 0; i < path.length - 1; i++) {
				for(int j = i+1; j < path.length; j++) {
					if(i!=j) {
						int gain = computeGain(i, j);
						if(gain < bestGain) {
							bestGain = gain;
							bestI = i;
							bestJ = j;
							if(firstImprovement) {
								break;
							} else {
								exchange(bestI, bestJ);
							}
							
						}
					}
				}
				if(firstImprovement) {
					break;
				}
			}
			
			if(bestI != Integer.MAX_VALUE && bestJ != Integer.MAX_VALUE) {
				exchange(bestI, bestJ);
			}
		}
	}
	
	/**
	 * Compute the gain if we exchange edge (path[cityIndex1],path[cityIndex1]+1) and 
	 * (path[cityIndex2],path[cityIndex2]+1) with
	 * (path[cityIndex1]+1,path[cityIndex2]+1) and (path[cityIndex1],path[cityIndex2])
	 * @param cityIndex1
	 * @param cityIndex2
	 * @return the gain of the change
	 */
	public int computeGain(final int cityIndex1, final int cityIndex2) {
		
		int src1 = path[cityIndex1];
		int src2 = path[cityIndex2];
		
		
		int dest1 = tool.getDestination(path, cityIndex1);
		int dest2 = tool.getDestination(path, cityIndex2);
		
		return ((distanceMatrix[src1][src2] + distanceMatrix[dest1][dest2]) - (distanceMatrix[src1][dest1] + distanceMatrix[src2][dest2]));
	}
	
	/**
	 * Make the change (path[cityIndex1],path[cityIndex1]+1) and 
	 * (path[cityIndex2],path[cityIndex2]+1) with
	 * (path[cityIndex1]+1,path[cityIndex2]+1) and (path[cityIndex1],path[cityIndex2])
	 * @param cityIndex1
	 * @param cityIndex2
	 */
	public void exchange(final int cityIndex1, final int cityIndex2) {
		
		int indexDest1 = tool.getIndexOfDestination(path, cityIndex1);
		int indexDest2 = tool.getIndexOfDestination(path, cityIndex2);

		
		int[] pathNew = new int[path.length];
		int indexOfPathNew = 0;
		
		int i = 0;
		while(i <= cityIndex1) {
			if(tool.isCityInPath(pathNew, path[i]) == false) {
				pathNew[indexOfPathNew] = path[i];
				indexOfPathNew++;
			}
			i++;
		}
		
		i = cityIndex2;
		while(i >= indexDest1) {
			if(tool.isCityInPath(pathNew, path[i]) == false) {
				pathNew[indexOfPathNew] = path[i];
				indexOfPathNew++;
			}
			i--;
		}
		
		i = indexDest2;
		while(i < path.length) {
			if(tool.isCityInPath(pathNew, path[i]) == false) {
				pathNew[indexOfPathNew] = path[i];
				indexOfPathNew++;
			}
			i++;
		}
		
		for(int k = 0; k < pathNew.length; k++) {
			path[k] = pathNew[k];
		}
		
	}
	
	/**
	 * @return path after one TwoOpt
	 */
	public int[] getPath() {
		return path;
	}
	
	public void setPath(final int[] path) {
		this.path = path;
	}
	                                          
}
