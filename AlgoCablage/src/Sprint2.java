import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Sprint2 {
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
		Coord ext1 = new Coord(250,250);
		LinkedList<Coord> listObs2 = new LinkedList<Coord>(Arrays.asList(new Coord(400,100),new Coord(600,100),new Coord(600,300),new Coord(400,300)));
		Obstacle obs2 = new Obstacle(listObs2);
		Coord ext2 = new Coord(412,267);
		LinkedList<Coord> listObs3 = new LinkedList<Coord>(Arrays.asList(new Coord(100,400),new Coord(100,600),new Coord(300,600),new Coord(300,400)));
		Obstacle obs3 = new Obstacle(listObs3);
		Coord ext3 = new Coord(289,512);
		LinkedList<Coord> listObs4 = new LinkedList<Coord>(Arrays.asList(new Coord(400,400),new Coord(400,600),new Coord(600,600),new Coord(600,400)));
		Obstacle obs4 = new Obstacle(listObs4);
		Coord ext4 = new Coord(578,500);
		
		
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
		
		/*On ajoute les extremites des stands au graphe*/
		algo.relierObstacleGraphe(map1, graphe, algo.sortirObs(graphe, obs1, ext1));
		algo.relierObstacleGraphe(map1, graphe, algo.sortirObs(graphe, obs2, ext2));
		algo.relierObstacleGraphe(map1, graphe, algo.sortirObs(graphe, obs3, ext3));
		algo.relierObstacleGraphe(map1, graphe, algo.sortirObs(graphe, obs4, ext4));
		
		/*On met à jour les distances au hub avec un dijkstra*/
		
		algo.dijkstra(graphe);
		
		//System.out.println(graphe);
		
		/*On trace les meilleurs chemins pour 2 points (attention a prendre un point sur le graphe)*/
		
		Cable cable1 = algo.cheminLePLusCourt(graphe, ext1.getX(), ext1.getY());
		map1.addCable(cable1);
		Cable cable2 = algo.cheminLePLusCourt(graphe, ext2.getX(), ext2.getY());
		map1.addCable(cable2);
		Cable cable3 = algo.cheminLePLusCourt(graphe, ext3.getX(), ext3.getY());
		map1.addCable(cable3);
		Cable cable4 = algo.cheminLePLusCourt(graphe, ext4.getX(), ext4.getY());
		map1.addCable(cable4);
		
		/*Affichage de la map*/
		 
		JFrame fenetre = new JFrame();
		fenetre.setSize(tailleX, tailleY);
		fenetre.add(map1);
		fenetre.setVisible(true);

		//System.out.println(map1);
		System.out.println(cable1);
		System.out.println(cable2);
		System.out.println(cable3);
		System.out.println(cable4);
	}
}
