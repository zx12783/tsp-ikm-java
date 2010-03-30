
public class Map {

	private long seed;
	private double startTemperature;
	private double alpha;
	
	public Map(final long seed, final double startTemperature, final double alpha) {
		this.seed = seed;
		this.startTemperature = startTemperature;
		this.alpha = alpha;
	}
	
	public long getSeed() {
		return seed;
	}
	
	
	public double getStartTemperature() {
		return startTemperature;
	}
	
	public double getAlpha() {
		return alpha;
	}
}
