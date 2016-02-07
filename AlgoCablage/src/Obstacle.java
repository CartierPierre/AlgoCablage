import java.util.LinkedList;

public class Obstacle {
	private LinkedList<Coord> sommets;

	public Obstacle(LinkedList<Coord> sommets) {
		this.sommets = sommets;
	}
	public LinkedList<Coord> getSommets() {
		return sommets;
	}
	public void setSommets(LinkedList<Coord> sommets) {
		this.sommets = sommets;
	}
	/*Les 4 méthodes suivantes permettent de recuperer les coordonnées min et max d'un obstacle (attention, il est casté en rectangle)*/
	public int getMinX(){
		int min = Integer.MAX_VALUE;
		for(Coord coord:sommets){
			if(min>coord.getX()) min=coord.getX();
		}
		return min;
	}
	public int getMinY(){
		int min = Integer.MAX_VALUE;
		for(Coord coord:sommets){
			if(min>coord.getY()) min=coord.getY();
		}
		return min;
	}
	public int getMaxX(){
		int max = Integer.MIN_VALUE;
		for(Coord coord:sommets){
			if(max<coord.getX()) max=coord.getX();
		}
		return max;
	}
	public int getMaxY(){
		int max = Integer.MIN_VALUE;
		for(Coord coord:sommets){
			if(max<coord.getY()) max=coord.getY();
		}
		return max;
	}
	
	//**** 2 obstacles egaux - inutile pour le moment
	/*public boolean equals(Obstacle ob1,Obstacle ob2)
	{
		Iterator<Coord> itCordOb1=ob1.getSommets().iterator(); 
		Iterator<Coord> itCordOb2=ob2.getSommets().iterator(); 
		while(itCordOb1.hasNext())
		{
			Coord c1 =itCordOb1.next(); 
			if (c1.appartienListPoint(c1,ob2.getSommets() )==false )
				return false ;
		}
		return true; 
	}*/
	
	@Override
	public String toString() {
		String chaine=""; 
		for(Coord sommet:sommets){
			chaine=chaine+"\n"+sommet.toString();
		}
		return "Obstacle [sommets=" + chaine + "]";
	}
}
