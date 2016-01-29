import java.util.LinkedList;
import java.util.List;

public class Sommet extends Coord {

	private int distFromStart; //Distance du hub
	private List<Arc> arcs;
	
	
	public Sommet(int x, int y) {
		super(x, y);
		distFromStart = Integer.MAX_VALUE;
		this.arcs = new LinkedList<Arc>();
	}


	public int getDistFromStart() {
		return distFromStart;
	}


	public void setDistFromStart(int distFromStart) {
		this.distFromStart = distFromStart;
	}


	public List<Arc> getArcs() {
		return arcs;
	}


	public void setArcs(List<Arc> arcs) {
		this.arcs = arcs;
	}
	
	

}
