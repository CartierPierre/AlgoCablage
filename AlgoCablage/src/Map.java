import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class Map extends JPanel{
	private LinkedList<Obstacle> obstacles;
	private LinkedList<Coord> extremitesStand;
	private Coord extremitesHub;
	private LinkedList<Cable> cables;
	private LinkedList<Sommet> graphe;
	private int tailleX;
	private int tailleY;
	
	public Map(int x, int y){
		 setPreferredSize(new Dimension(x, y));
		 this.obstacles = new LinkedList<Obstacle>();
		 this.cables = new LinkedList<Cable>();
		 this.graphe = new LinkedList<Sommet>();
		 this.extremitesStand = new LinkedList<Coord>();
		 this.extremitesHub = new LinkedList<Coord>();
		 this.tailleX=x;
		 this.tailleY=y;
		 
		 JsonObject carte;
		try{
			carte = Json.createReader(new FileReader("carte.json")).readObject();
			
			
			
			Obstacle tmpObs;
			
			JsonArray listObstacles=carte.getJsonArray("obstacles");
			for(int i = 0;i<listObstacles.size() ;i++){
				JsonObject obstacle = listObstacles.getJsonObject(i);
				JsonArray listSommets = obstacle.getJsonArray("sommets");
				tmpObs=new Obstacle();
				for(int j = 0;j<listSommets.size() ;j++){
					tmpObs.addSommets( new Coord(listSommets.getJsonObject(j).getInt("horizontal"),listSommets.getJsonObject(j).getInt("vertical")));
				}
				obstacles.add(tmpObs);
			}
			
			JsonArray listStands=carte.getJsonArray("raccordStand");
			for(int i = 0;i<listStands.size() ;i++){
				extremitesStand.add(new Coord(listStands.getJsonObject(i).getInt("horizontal"),listStands.getJsonObject(i).getInt("vertical")));
			}
			
			JsonArray listHubs=carte.getJsonArray("raccordHub");
			/*for(int i = 0;i<listHubs.size() ;i++){
				extremitesHub.add(new Coord(listHubs.getJsonObject(i).getInt("horizontal"),listHubs.getJsonObject(i).getInt("vertical")));
			}*/
			extremitesHub = new Coord(listHubs.getJsonObject(i).getInt("horizontal"),listHubs.getJsonObject(i).getInt("vertical")));
		}catch(FileNotFoundException e){
			System.err.print("erreur");
			System.exit(0);
			//end program and watch the world burn
		}
		
		/*Bords*/
		LinkedList<Coord> listObsUp = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(x,0),new Coord(x,0),new Coord(0,0)));
		obstacles.add(new Obstacle(listObsUp));
		LinkedList<Coord> listObsLeft = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(0,y),new Coord(0,y),new Coord(0,0)));
		obstacles.add(new Obstacle(listObsLeft));
		LinkedList<Coord> listObsDown = new LinkedList<Coord>(Arrays.asList(new Coord(0,y),new Coord(x,y),new Coord(x,y),new Coord(0,y)));
		obstacles.add(new Obstacle(listObsDown));
		LinkedList<Coord> listObsRight = new LinkedList<Coord>(Arrays.asList(new Coord(x,0),new Coord(x,y),new Coord(x,y),new Coord(x,0)));
		obstacles.add(new Obstacle(listObsRight));
		
		
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
	
	public LinkedList<Coord> getExtremitesStand() {
		return extremitesStand;
	}

	public void setExtremitesStand(LinkedList<Coord> extremitesStand) {
		this.extremitesStand = extremitesStand;
	}
	
	public void addExtremitesStand(Coord ext){
		this.extremitesStand.add(ext);
	}

	public LinkedList<Coord> getExtremitesHub() {
		return extremitesHub;
	}

	public void setExtremitesHub(LinkedList<Coord> extremitesHub) {
		this.extremitesHub = extremitesHub;
	}

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
	
	@Override
	public String toString() {
		String chaineOb=""; 
		for(Obstacle obs:obstacles){
			chaineOb=chaineOb+"\n"+obs.toString(); 
		} 
		String chaineCb=""; 
		for(Cable cable:cables){
			chaineCb=chaineCb+"\n"+cable.toString(); 
		}
		String chaineGraphe=""; 
		for(Sommet sommet:graphe){
			chaineGraphe=chaineGraphe+"\n"+sommet.toString(); 
		}
		return "Map [obstacles=" + chaineOb + ", cables=" + chaineCb + ", graphe=" + chaineGraphe
				+ ", tailleX=" + tailleX + ", tailleY=" + tailleY + "]";
	}
	
}
