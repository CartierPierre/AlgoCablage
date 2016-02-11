import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JFrame;

public class Sprint2 {
	public static void main(String args[]){
		
		
		JsonObject carte;
					
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
			
			
			/*Debut du traitement*/
			
			Algo algo=new Algo();
			
			/*On g�n�re un graphe plus ou moins precis (et gourmand en ressources) en fonction du pas*/
			
			int pas=10;
			LinkedList<Sommet> graphe = algo.grapheGenerer(pas, map1);
			Sommet hub = graphe.getFirst(); // On d�fini temporairement le hub comme le point le plus en haut a gauche
			hub.setDistFromStart(0);
			
			/*On ajoute les extremites des stands au graphe*/
			LinkedList<Coord> stands = map1.getExtremitesStand(); 
			for(Coord c:stands){
				algo.relierExtremiteGraphe(map1, graphe, c);
			}
				
			
			/*On met � jour les distances au hub avec un dijkstra*/
			
			algo.dijkstra(graphe);
			
			//System.out.println(graphe);
			
			/*On trace les meilleurs chemins pour 2 points (attention a prendre un point sur le graphe)*/
			
			
			for(Coord c:stands){
				map1.addCable(algo.cheminLePLusCourt(graphe, c.getX(), c.getY()));
			}
			
		/*	Cable cable1 = algo.cheminLePLusCourt(graphe, ext1.getX(), ext1.getY());
			map1.addCable(cable1);
			Cable cable2 = algo.cheminLePLusCourt(graphe, ext2.getX(), ext2.getY());
			map1.addCable(cable2);
			Cable cable3 = algo.cheminLePLusCourt(graphe, ext3.getX(), ext3.getY());
			map1.addCable(cable3);
			Cable cable4 = algo.cheminLePLusCourt(graphe, ext4.getX(), ext4.getY());
			map1.addCable(cable4);*/
			
			/*Affichage de la map*/
			 
			JFrame fenetre = new JFrame();
			fenetre.setSize(tailleX, tailleY);
			fenetre.add(map1);
			fenetre.setVisible(true);
	}
}
