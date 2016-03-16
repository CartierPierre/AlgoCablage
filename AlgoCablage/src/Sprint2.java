//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.Arrays;
import java.util.LinkedList;

//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
import javax.swing.JFrame;

public class Sprint2 {
	public static void main(String args[]){
					
			int tailleX=700;
			int tailleY=700;
			
			Map map1 = new Map();
			
			/*Debut du traitement*/
			
			Algo algo=new Algo();
			
			/*On trie les extremites des stands pour l'ordre d'execution*/
			algo.trierExtremitesStand(map1);
			
			/*On gï¿½nï¿½re un graphe plus ou moins precis (et gourmand en ressources) en fonction du pas*/
			
			int pas=6;
			LinkedList<Sommet> graphe = algo.grapheGenerer(pas, map1);
			algo.relierExtremiteGraphe(map1, graphe, map1.getExtremiteHub());
			Sommet hub = algo.chercherSommet(graphe, map1.getExtremiteHub().getX(), map1.getExtremiteHub().getY());
			
			LinkedList<Coord> listeExt = map1.getExtremitesStand();
			LinkedList<Obstacle> listobs=map1.getObstacles();
			int i=0;
			
			for(Coord ext:listeExt){
				
				algo.relierExtremiteGraphe(map1, graphe, ext);
				
				for(Sommet som:graphe){ //On reset toutes les distances
					som.setDistFromStart(Integer.MAX_VALUE);
					som.setPere(null);
				}
				hub.setDistFromStart(0);
				
				/*On met à jour les distances au hub avec un dijkstra*/
				algo.dijkstra(graphe, hub);
				
				algo.relierExtremiteGraphe(map1, graphe, new Sommet(hub.getX()+pas, hub.getY()));
				hub=algo.chercherSommet(graphe, hub.getX()+pas, hub.getY());//On décale le hub sur la droite pour le prochain cable
				
				
				/*On trace les meilleurs chemins pour 2 points (attention a prendre un point sur le graphe)*/
				Cable cable=algo.cheminLePLusCourt(graphe, ext.getX(), ext.getY(),pas);
				map1.addCable(cable);
				i++;
			}
			
			/*System.out.println(graphe);*/
			
			map1.exportCablesJSON();
			
			//System.out.println(graphe);
            
			/*Affichage de la map*/
			 
			JFrame fenetre = new JFrame();
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetre.setSize(tailleX, tailleY);
			fenetre.add(map1);
			fenetre.setVisible(true);
	}
}
