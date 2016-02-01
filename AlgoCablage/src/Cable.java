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
}
