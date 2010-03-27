
public class Tool {

	/**
	 * compute the cost of a given path
	 * @param path
	 * @param distanceMatrix
	 * @return
	 */
	public int computeCost(int[] path, int[][] distanceMatrix) {
		int cost = 0;
		for(int i = 1; i < path.length; i++) {
			cost += distanceMatrix[path[i-1]][path[i]];
		}
		cost += distanceMatrix[path[path.length - 1]] [path[0]];
		return cost;
	}

	/**
	 * @param path
	 * @param srcIndex
	 * @return destination city of an edge given the source index
	 */
	public int getDestination(int[] path, int srcIndex) {
		if(srcIndex + 1 == path.length) {
			return path[0];
		}
		return path[srcIndex + 1];
	}
	
	/**
	 * @param path
	 * @param srcIndex
	 * @return destination index of an edge given the source index
	 */
	public int getIndexOfDestination(int[] path, int srcIndex) {
		if(srcIndex + 1 == path.length) {
			return 0;
		}
		return srcIndex + 1;
	}
	
	
	/**
	 * Check if the city is in the path
	 * @param city
	 * @return true: if the city is already in the path, false otherwise
	 */
	public boolean isCityInPath(int[] path, int city) {
		for(int i = 0; i < path.length; i++) {
			if(path[i] == city) {
				return true;
			}
		}
		return false;
	}
}
