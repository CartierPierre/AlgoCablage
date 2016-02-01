import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class Map extends JPanel{
	private LinkedList<Obstacle> obstacles;
	//private List<PointDep> pointsDep;
	//private List<PointArr> pointsArr;
	private LinkedList<Cable> cables;
	private Algo algo;
	private LinkedList<Sommet> graphe;
	
	public Map(){
		 setPreferredSize(new Dimension(750, 750));
		 this.obstacles = new LinkedList<Obstacle>();
		 this.cables = new LinkedList<Cable>();
		 this.graphe = new LinkedList<Sommet>();
	}
	
	public LinkedList<Obstacle> getObstacles() {
		return obstacles;
	}
	public void setObstacles(LinkedList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
	public void addObstacle(Obstacle obstacle){
		this.obstacles.add(obstacle);
	}
	
	/*
	public List<PointDep> getPointsDep() {
		return pointsDep;
	}
	public void setPointsDep(List<PointDep> pointsDep) {
		this.pointsDep = pointsDep;
	}
	public List<PointArr> getPointsArr() {
		return pointsArr;
	}
	public void setPointsArr(List<PointArr> pointsArr) {
		this.pointsArr = pointsArr;
	}*/
	
	public LinkedList<Cable> getCables() {
		return cables;
	}
	public void setCables(LinkedList<Cable> cables) {
		this.cables = cables;
	}
	public void addCable(Cable cable){
		this.cables.add(cable);
	}
	
	public Algo getAlgo() {
		return algo;
	}
	public void setAlgo(Algo algo) {
		this.algo = algo;
	}
	
	public void paintComponent(Graphics g) {
          super.paintComponent(g);
          
          for(Cable c:cables){
          	LinkedList<Coord> points = (LinkedList<Coord>) c.getAngles();
          	//g.drawLine(points.getFirst().getX() , points.getFirst().getY() , points.getLast().getX() , points.getLast().getY());
          	
          	for(int i=1;i<points.size();i++){
          		g.drawLine(points.get(i-1).getX() , points.get(i-1).getY() , points.get(i).getX() , points.get(i).getY());
          	}
          }
          
          for(Obstacle o:obstacles){
          	LinkedList<Coord> points = (LinkedList<Coord>) o.getSommets();
          	g.drawLine(points.getFirst().getX() , points.getFirst().getY() , points.getLast().getX() , points.getLast().getY());
          	
          	for(int i=1;i<points.size();i++){
          		g.drawLine(points.get(i-1).getX() , points.get(i-1).getY() , points.get(i).getX() , points.get(i).getY());
          	}
          }
     }

	public LinkedList<Sommet> getGraphe() {
		return graphe;
	}

	public void setGraphe(LinkedList<Sommet> graphe) {
		this.graphe = graphe;
	}
	
}
