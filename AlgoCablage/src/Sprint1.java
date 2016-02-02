import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Sprint1 {
	public static void main(String args[]){
		
		int tailleX=700;
		int tailleY=700;
		
		Map map1 = new Map(tailleX, tailleY);
		
		/*Creation et ajout des obstacles sur la map*/
		
		/*Bords*/
		LinkedList<Coord> listObsUp = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(tailleX,0),new Coord(tailleX,0),new Coord(0,0)));
		Obstacle obsUp = new Obstacle(listObsUp);
		LinkedList<Coord> listObsLeft = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(0,tailleY),new Coord(0,tailleY),new Coord(0,0)));
		Obstacle obsLeft = new Obstacle(listObsLeft);
		LinkedList<Coord> listObsDown = new LinkedList<Coord>(Arrays.asList(new Coord(0,tailleY),new Coord(tailleX,tailleY),new Coord(tailleX,tailleY),new Coord(0,tailleY)));
		Obstacle obsDown = new Obstacle(listObsDown);
		LinkedList<Coord> listObsRight = new LinkedList<Coord>(Arrays.asList(new Coord(tailleX,0),new Coord(tailleX,tailleY),new Coord(tailleX,tailleY),new Coord(tailleX,0)));
		Obstacle obsRight = new Obstacle(listObsRight);
		
		/*Stands*/
		LinkedList<Coord> listObs1 = new LinkedList<Coord>(Arrays.asList(new Coord(200,200),new Coord(300,200),new Coord(300,300),new Coord(200,300)));
		Obstacle obs1 = new Obstacle(listObs1);
		LinkedList<Coord> listObs2 = new LinkedList<Coord>(Arrays.asList(new Coord(400,100),new Coord(600,100),new Coord(600,300),new Coord(400,300)));
		Obstacle obs2 = new Obstacle(listObs2);
		LinkedList<Coord> listObs3 = new LinkedList<Coord>(Arrays.asList(new Coord(100,400),new Coord(100,600),new Coord(300,600),new Coord(300,400)));
		Obstacle obs3 = new Obstacle(listObs3);
		LinkedList<Coord> listObs4 = new LinkedList<Coord>(Arrays.asList(new Coord(400,400),new Coord(400,600),new Coord(600,600),new Coord(600,400)));
		Obstacle obs4 = new Obstacle(listObs4);
		
		
		map1.addObstacle(obsUp);
		map1.addObstacle(obsLeft);
		map1.addObstacle(obsDown);
		map1.addObstacle(obsRight);
		map1.addObstacle(obs1);
		map1.addObstacle(obs2);
		map1.addObstacle(obs3);
		map1.addObstacle(obs4);
		
		/*Debut du traitement*/
		
		Algo algo=new Algo();
		
		/*On génère un graphe plus ou moins precis (et gourmand en ressources) en fonction du pas*/
		
		int pas=50;
		LinkedList<Sommet> graphe = algo.grapheGenerer(pas, map1);
		Sommet hub = graphe.getFirst(); // On défini temporairement le hub comme le point le plus en haut a gauche
		hub.setDistFromStart(0);
		
		/*On met à jour les distances au hub avec un dijkstra*/
		
		algo.dijkstra(graphe);
		
		Cable cable1 = new Cable();
		Cable cable2 = new Cable();
		
		/*On trace les meilleurs chemins pour 2 points (attention a prendre un point sur le graphe)*/
		
		algo.cheminLePLusCourt(graphe, 650, 650, cable1);
		map1.addCable(cable1);
		algo.cheminLePLusCourt(graphe, 50, 650, cable2);
		map1.addCable(cable2);
		
		/*Affichage de la map*/
		 
		JFrame fenetre = new JFrame();
		fenetre.setSize(tailleX, tailleY);
		fenetre.add(map1);
		fenetre.setVisible(true);
	}
}
