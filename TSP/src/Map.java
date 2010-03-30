
/**
 * @author Lucia Blondel
 */

public class Map {

	private long seed;
	private double startTemperature;
	private double alpha;
	
	/**
	 * Constructor
	 * @param seed
	 * @param start temperature
	 * @param alpha
	 */
	public Map(final long seed, final double startTemperature, final double alpha) {
		this.seed = seed;
		this.startTemperature = startTemperature;
		this.alpha = alpha;
	}
	
	/**
	 * @return the seed of the random
	 */
	public long getSeed() {
		return seed;
	}
	
	/**
	 * @return the start temperature
	 */
	public double getStartTemperature() {
		return startTemperature;
	}
	
	/**
	 * @return the alpha, i.e how much the temperature decrease every
	 * time we finish the simulated annealing for the previous one
	 */
	public double getAlpha() {
		return alpha;
	}
}
