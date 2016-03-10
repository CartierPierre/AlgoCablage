import java.util.Iterator;
import java.util.LinkedList;

public class Sommet extends Coord {

	private int distFromStart; //Distance du hub
	private LinkedList<Arc> arcs;
	private Sommet pere;
	private Cable cable;
	
	
	public Cable getCable() {
		return cable;
	}


	public void setCable(Cable cable) {
		this.cable = cable;
	}


	public Sommet(int x, int y) {
		super(x, y);
		distFromStart = Integer.MAX_VALUE;
		this.arcs = new LinkedList<Arc>();
		this.pere = null;
		this.cable = null;
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
	
	public String stringCoord() {
		return "[x=" + this.getX() + ", y=" + this.getY() + "]";
	}
	@Override
	public String toString() {
		Iterator<Arc>itArc=arcs.iterator(); 
		String chaineArs="";
		String chainep="";
		if( pere!=null) 
		chainep=pere.stringCoord(); 
		while(itArc.hasNext())
		{
			chaineArs=chaineArs+"\n"+itArc.next().toString(); 
		}
		if(cable==null) return "Sommet [x="+this.getX() + ", y=" + this.getY() +", distFromStart=" + distFromStart + ", arcs=" + chaineArs + ", pere=" +chainep+ ", cable=" +"null"+   "]";
		return "Sommet [x="+this.getX() + ", y=" + this.getY() +", distFromStart=" + distFromStart + ", arcs=" + chaineArs + ", pere=" +chainep+ ", cable=" +cable.getNum()+   "]";
	}

}
