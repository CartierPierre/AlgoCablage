import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Sprint1 {
	public static void main(String args[]){
		
		Map map1 = new Map();
		
		LinkedList<Coord> listObsUp = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(700,0),new Coord(700,0),new Coord(0,0)));
		Obstacle obsUp = new Obstacle(listObsUp);
		LinkedList<Coord> listObsLeft = new LinkedList<Coord>(Arrays.asList(new Coord(0,0),new Coord(0,700),new Coord(0,700),new Coord(0,0)));
		Obstacle obsLeft = new Obstacle(listObsLeft);
		LinkedList<Coord> listObsDown = new LinkedList<Coord>(Arrays.asList(new Coord(0,700),new Coord(700,700),new Coord(700,700),new Coord(0,700)));
		Obstacle obsDown = new Obstacle(listObsDown);
		LinkedList<Coord> listObsRight = new LinkedList<Coord>(Arrays.asList(new Coord(700,0),new Coord(700,700),new Coord(700,700),new Coord(700,0)));
		Obstacle obsRight = new Obstacle(listObsRight);
		
		
		LinkedList<Coord> listObs1 = new LinkedList<Coord>(Arrays.asList(new Coord(100,100),new Coord(300,100),new Coord(300,300),new Coord(100,300)));
		Obstacle obs1 = new Obstacle(listObs1);
		LinkedList<Coord> listObs2 = new LinkedList<Coord>(Arrays.asList(new Coord(400,100),new Coord(600,100),new Coord(600,300),new Coord(400,300)));
		Obstacle obs2 = new Obstacle(listObs2);
		LinkedList<Coord> listObs3 = new LinkedList<Coord>(Arrays.asList(new Coord(100,400),new Coord(100,600),new Coord(300,600),new Coord(300,400)));
		Obstacle obs3 = new Obstacle(listObs3);
		LinkedList<Coord> listObs4 = new LinkedList<Coord>(Arrays.asList(new Coord(400,400),new Coord(400,600),new Coord(600,600),new Coord(600,400)));
		Obstacle obs4 = new Obstacle(listObs4);
		
		//LinkedList<Coord> listSommets1 = new LinkedList<Coord>(Arrays.asList(new Coord(50,50),new Coord(350,50),new Coord(650,50),new Coord(50,350),new Coord(350,350),new Coord(650,350),new Coord(50,650),new Coord(350,650),new Coord(650,650),));
		
		Sommet somA=new Sommet(50,50); 
		Sommet somB=new Sommet(350,50); 
		Sommet somC=new Sommet(650,50); 
		Sommet somD=new Sommet(50,350); 
		Sommet somE=new Sommet(350,350); 
		Sommet somF=new Sommet(650,350); 
		Sommet somG=new Sommet(50,650); 
		Sommet somH=new Sommet(350,650); 
		Sommet somI=new Sommet(650,650);
		
		LinkedList<Arc> listArcA = new LinkedList<Arc>(Arrays.asList(new Arc(somB,300),new Arc(somD,300)));
		LinkedList<Arc> listArcB = new LinkedList<Arc>(Arrays.asList(new Arc(somC,300),new Arc(somE,300)));
		LinkedList<Arc> listArcC = new LinkedList<Arc>(Arrays.asList(new Arc(somF,300)));
		LinkedList<Arc> listArcD = new LinkedList<Arc>(Arrays.asList(new Arc(somG,300),new Arc(somE,300)));
		LinkedList<Arc> listArcE = new LinkedList<Arc>(Arrays.asList(new Arc(somF,300),new Arc(somH,300)));
		LinkedList<Arc> listArcF = new LinkedList<Arc>(Arrays.asList());
		LinkedList<Arc> listArcG = new LinkedList<Arc>(Arrays.asList(new Arc(somH,300)));
		LinkedList<Arc> listArcH = new LinkedList<Arc>(Arrays.asList(new Arc(somI,300)));
		LinkedList<Arc> listArcI = new LinkedList<Arc>(Arrays.asList());
		
		somA.setArcs(listArcA);
		somA.setDistFromStart(0);
		somB.setArcs(listArcB);
		somC.setArcs(listArcC);
		somD.setArcs(listArcD);
		somE.setArcs(listArcE);
		somF.setArcs(listArcF);
		somG.setArcs(listArcG);
		somH.setArcs(listArcH);
		somI.setArcs(listArcI);
		
		LinkedList<Sommet> graphe = new LinkedList<Sommet>(Arrays.asList(somA, somB, somC, somD, somE, somF, somG, somH, somI));
		
		Algo algo=new Algo();
		
		algo.dijkstra(graphe);
		if(somA.getPere()==null) System.out.println("Racine");
		
		Cable cableI = new Cable();
		Cable cableG = new Cable();
		
		algo.cheminLePLusCourt(somI, cableI);
		map1.addCable(cableI);
		algo.cheminLePLusCourt(somG, cableG);
		map1.addCable(cableG);
		
		map1.addObstacle(obsUp);
		map1.addObstacle(obsLeft);
		map1.addObstacle(obsDown);
		map1.addObstacle(obsRight);
		map1.addObstacle(obs1);
		map1.addObstacle(obs2);
		map1.addObstacle(obs3);
		map1.addObstacle(obs4);
		 
		JFrame fenetre = new JFrame();
		fenetre.setSize(750, 750);
		fenetre.add(map1);
		fenetre.setVisible(true);
	}
}
