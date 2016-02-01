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
}
