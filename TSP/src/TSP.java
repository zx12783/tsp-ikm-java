import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main class of the project
 * @author Lucia Blondel
 */

public class TSP {
	
	private static final String pathOfData = "/Users/lucy/Desktop/semestre4/IKM/TSP/data/";
	private static final Tool tool = new Tool();
	
	private static String nameOfMap;
	private static ArrayList<String> cities = new ArrayList<String>();
	private static int[][] distanceMatrix;
	private static int[] path;
	private static int cost;
	
	public static final HashMap<String, Map> maps = new HashMap<String,Map>();
	
	private static Map ch130 = new Map(175375829147462l, 1, 0.999);
	private static Map d198 = new Map(170240126344462l, 1, 0.999);
	private static Map eil76 = new Map(165144750863462l, 10, 0.999);
	private static Map fl1577 = new Map(179142946891462l, 0.2, 0.95); 
	private static Map kroA100 = new Map(175595919652462l, 1, 0.999);
	private static Map lin318 = new Map(170527939861462l, 1, 0.999);
	private static Map pcb442 = new Map(179566124364462l, 1, 0.999);
	private static Map pr439 = new Map(179780899710462l, 1, 0.999); 
	private static Map rat783 = new Map(160900147239462l, 0.1, 0.995);
	private static Map u1060 = new Map(161821912575462l, 0.5, 0.99);

	
	public static void main (String[] args) {	
		
		maps.put("ch130.tsp", ch130);
		maps.put("d198.tsp", d198);
		maps.put("eil76.tsp", eil76);
		maps.put("fl1577.tsp", fl1577);
		maps.put("kroA100.tsp", kroA100);
		maps.put("lin318.tsp", lin318);
		maps.put("pcb442.tsp", pcb442);
		maps.put("pr439.tsp", pr439);
		maps.put("rat783.tsp", rat783);
		maps.put("u1060.tsp", u1060);
		
		nameOfMap = args[0];
		
		long start = System.nanoTime();
		
		// read all the cities and position of the cities from the file
		ReadFile file = new ReadFile(pathOfData + args[0]);
		cities = file.getCities();
		
		// build the matrix with the distances 
		DistanceMatrix d = new DistanceMatrix(cities);
		distanceMatrix = d.getDistanceMatrix();
		
		//do simulated annealing
	
		SimulatedAnnealing annealing = new SimulatedAnnealing(d, nameOfMap);
		annealing.simulatedAnnealing();
		
		path = annealing.getPath();
		cost = tool.computeCost(path, distanceMatrix);

		long end = System.nanoTime();
	
		// print out some informations
		System.out.println("Time needed");
		System.out.println(Math.floor((end-start) * Math.pow(10, -9)));
		
		System.out.println("Path");
		String pathString = "[";
		for(int i = 0; i < path.length; i++) {
			pathString += (path[i]+1) + "; ";
		}
		pathString += "]";
		System.out.println(pathString);
		System.out.println("Cost of the solution");
		System.out.println(cost);
		System.out.println("Is the solution feasible?");
		if (tool.isFeasible(path, cities)) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}

	}
	
	
}