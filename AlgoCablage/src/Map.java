import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class Map extends JPanel{
	private LinkedList<Obstacle> obstacles;
	//private List<PointDep> pointsDep; //A supprimer ?
	//private List<PointArr> pointsArr; //A supprimer ?
	private LinkedList<Cable> cables;
	private Algo algo; //A supprimer ?
	private LinkedList<Sommet> graphe;
	private int tailleX;
	private int tailleY;
	
	public Map(int x, int y){
		 setPreferredSize(new Dimension(x, y));
		 this.obstacles = new LinkedList<Obstacle>();
		 this.cables = new LinkedList<Cable>();
		 this.graphe = new LinkedList<Sommet>();
		 this.tailleX=x;
		 this.tailleY=y;
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
	
	public int getTailleX() {
		return tailleX;
	}

	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}

	public int getTailleY() {
		return tailleY;
	}

	public void setTailleY(int tailleY) {
		this.tailleY = tailleY;
	}

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
	
	public void paintComponent(Graphics g) { //Affichage
          super.paintComponent(g);
          
          for(Cable c:cables){
          	LinkedList<Coord> points = (LinkedList<Coord>) c.getAngles();
          	
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

	public boolean positionLibre(int i, int j) { //Vrai si le point (i,j) n'est pas dans un obstacle (ou perimetre)
		for(Obstacle obs:obstacles){
			if(i<=obs.getMaxX() && i>=obs.getMinX() && j<=obs.getMaxY() && j>=obs.getMinY()){
				return false;
			}
		}
		return true;
	}
	
}
