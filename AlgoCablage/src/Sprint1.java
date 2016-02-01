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
		
		LinkedList<Coord> listSommets1 = new LinkedList<Coord>(Arrays.asList(new Coord(50,50),new Coord(350,50),new Coord(650,50),new Coord(50,350),new Coord(350,350),new Coord(650,350),new Coord(50,650),new Coord(350,650),new Coord(650,650),));
		
		somA=new Sommet(50,50); 
		somB=new Sommet(350,50); 
		somC=new Sommet(650,50); 
		somD=new Sommet(50,350); 
		somE=new Sommet(350,350); 
		somF=new Sommet(650,350); 
		somG=new Sommet(50,650); 
		somH=new Sommet(350,650); 
		somI=new Sommet(650,650);
		
		LinkedList<Arc> listArcA = new LinkedList<Arc>(Arrays.asList(new Coord(somB,300),new Coord(somD,300)));
		LinkedList<Arc> listArcB = new LinkedList<Arc>(Arrays.asList(new Coord(somA,300),new Coord(somC,300),new Coord(somE,300)));
		LinkedList<Arc> listArcC = new LinkedList<Arc>(Arrays.asList(new Coord(somB,300),new Coord(somF,300)));
		LinkedList<Arc> listArcD = new LinkedList<Arc>(Arrays.asList(new Coord(somA,300),new Coord(somG,300),new Coord(somE,300)));
		LinkedList<Arc> listArcE = new LinkedList<Arc>(Arrays.asList(new Coord(somB,300),new Coord(somD,300),new Coord(somF,300),new Coord(somH,300)));
		LinkedList<Arc> listArcF = new LinkedList<Arc>(Arrays.asList(new Coord(somC,300),new Coord(somI,300),new Coord(somE,300)));
		LinkedList<Arc> listArcG = new LinkedList<Arc>(Arrays.asList(new Coord(somH,300),new Coord(somD,300)));
		LinkedList<Arc> listArcH = new LinkedList<Arc>(Arrays.asList(new Coord(somG,300),new Coord(somI,300),new Coord(somE,300)));
		LinkedList<Arc> listArcI = new LinkedList<Arc>(Arrays.asList(new Coord(somH,300),new Coord(somF,300)));
		
		som.setArc(listArc);
		som.setArc(listArc);
		som.setArc(listArc);
		
		
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
