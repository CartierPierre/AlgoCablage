import java.util.LinkedList;

public class Cable {
	private LinkedList<Coord> angles;
	
	public Cable() {
		this.angles = new LinkedList<Coord>();
	}
	public LinkedList<Coord> getAngles() {
		return angles;
	}
	public void setAngles(LinkedList<Coord> angles) {
		this.angles = angles;
	}
	public void addAngle(Coord coord){
		angles.add(coord);
	}
	public void resetAngles(){
		angles.removeAll(angles);
	}
	
	public int longueur(){
		int longueur=0;
		for (int i=1; i<angles.size(); i++){
			longueur+=Math.abs((angles.get(i-1).getX()-angles.get(i).getX())+(angles.get(i-1).getY()-angles.get(i).getY()));
		}
		return longueur;
	}
	
	/**
	 *Retire les angles superflux lorsque 3 angles consécutifs ont les mêmes valeurs de x ou y
	*/
	public void epurerAngles(){
		for(int i=2;i<angles.size();i++){
			if(angles.get(i-2).getX() == angles.get(i-1).getX() && angles.get(i-2).getX() == angles.get(i).getX())
				angles.remove(i-1);
			else if(angles.get(i-2).getY() == angles.get(i-1).getY() && angles.get(i-2).getY() == angles.get(i).getY())
				angles.remove(i-1);
		}
	}
	
	@Override
	public String toString() {
		String chaine="";
		for(Coord point:angles){	
			chaine=chaine+"\n"+point.toString();
		}
		return "Cable [angles=" + chaine + "]";
	}
}
