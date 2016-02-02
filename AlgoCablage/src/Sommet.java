import java.util.LinkedList;

public class Sommet extends Coord {

	private int distFromStart; //Distance du hub
	private LinkedList<Arc> arcs;
	private Sommet pere;
	
	
	public Sommet(int x, int y) {
		super(x, y);
		distFromStart = Integer.MAX_VALUE;
		this.arcs = new LinkedList<Arc>();
		this.pere = null;
	}


	public int getDistFromStart() {
		return distFromStart;
	}


	public void setDistFromStart(int distFromStart) {
		this.distFromStart = distFromStart;
	}


	public LinkedList<Arc> getArcs() {
		return arcs;
	}


	public void setArcs(LinkedList<Arc> arcs) {
		this.arcs = arcs;
	}
	
	public void addArc(Arc arc){
		arcs.add(arc);
	}


	public Sommet getPere() {
		return pere;
	}


	public void setPere(Sommet pere) {
		this.pere = pere;
	}
	
	

}
