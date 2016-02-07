import java.util.LinkedList;

public class Algo {
	
	public LinkedList<Sommet> grapheGenerer (int pas, Map map){ //Fourni un graphe sous forme de grille avec un maillage plus ou moins fin
		LinkedList<Sommet> graphe = new LinkedList<Sommet>();
		for(int i = 0; i<map.getTailleX(); i+=pas){ //On commence par creer tous les sommets
			for(int j = 0; j<map.getTailleY(); j+=pas){
				if(map.positionLibre(i,j)){
					graphe.add(new Sommet(i, j));
				}
			}
		}
		for(Sommet som:graphe){ //Puis on les relie aux autres proches
			for(Sommet fils:graphe){
				if(som.getX() + pas == fils.getX()  && som.getY() == fils.getY()){ //Arc √† droite
					som.addArc(new Arc(fils, pas));
				}
				else if(som.getX() - pas== fils.getX()  && som.getY() == fils.getY()){ //Arc √† gauche
					som.addArc(new Arc(fils, pas));
				}
				else if(som.getY() + pas== fils.getY()  && som.getX() == fils.getX()){ //Arc en bas
					som.addArc(new Arc(fils, pas));
				}
				else if(som.getY() - pas== fils.getY()  && som.getX() == fils.getX()){ //Arc en haut
					som.addArc(new Arc(fils, pas));
				}
			}
		}
		return graphe;
	}
	
	
	public void dijkstra(LinkedList<Sommet> graphe){
		for(Sommet sommet:graphe){ //Pour chaque sommet du graphe
			for(Arc arc:sommet.getArcs()){ // On met a jour la distance de tous les sommets reliÈs
				if(arc.getSommet().getDistFromStart() - sommet.getDistFromStart() > arc.getLongueur()){ //Si elle est plus courte
					arc.getSommet().setDistFromStart(arc.getLongueur() + sommet.getDistFromStart());
					arc.getSommet().setPere(sommet); //On met a jour le pere
				}			
			}
		}
	}
	
	/*public void cheminLePLusCourt(Sommet sommet, Cable cable){ // Transforme le dijkstra en angles pour le cable
		Sommet tmpSommet=sommet;
		cable.resetAngles();
		while(tmpSommet != null){
			cable.addAngle(tmpSommet);
			tmpSommet=tmpSommet.getPere();
		}
	}*/
	
	public Cable cheminLePLusCourt(LinkedList<Sommet> graphe, int x, int y){ // Transforme le dijkstra en angles pour le cable
		Sommet tmpSommet=null;
		Cable cable = new Cable();
		for(Sommet sommet:graphe){ //On cherche le sommet du graphe aux coordonnees (x,y)
			if(sommet.getX() == x && sommet.getY() == y) tmpSommet=sommet;
		}
		while(tmpSommet != null){ 
			cable.addAngle(tmpSommet); //On ajoute les segments (angles plats) -> Il faut am√©liorer pour n'avoir que les angles
			tmpSommet=tmpSommet.getPere();
		}
		cable.epurerAngles();
		return cable;
	}
	
	public Sommet sortirObs(LinkedList<Sommet> graphe, Obstacle obs, Coord point){ //Version sortie la plus proche
		int dist=Integer.MAX_VALUE;
		int x=point.getX(); //SÈcuritÈ si x et y sont inchangÈs
		int y=point.getY();
		if(dist>Math.abs(point.getX()-obs.getMaxX())){ //Vers la droite
			dist=Math.abs(point.getX()-obs.getMaxX());
			x=obs.getMaxX();
			y=point.getY();
		}
		if(dist>Math.abs(point.getX()-obs.getMinX())){ //Vers la gauche
			dist=Math.abs(point.getX()-obs.getMinX());
			x=obs.getMinX();
			y=point.getY();
		}
		if(dist>Math.abs(point.getY()-obs.getMaxY())){ //Vers le bas
			dist=Math.abs(point.getY()-obs.getMaxY());
			y=obs.getMaxY();
			x=point.getX();
		}
		if(dist>Math.abs(point.getY()-obs.getMinY())){ //Vers le haut
			dist=Math.abs(point.getY()-obs.getMinY());
			y=obs.getMinY();
			x=point.getX();
		}
		Sommet sortie=new Sommet(x, y);
		Sommet ext=new Sommet(point.getX(), point.getY());
		sortie.addArc(new Arc(ext , dist)); //On ajoute l'arc intra-obstacle
		ext.addArc(new Arc(sortie, dist));
		graphe.add(ext);
		System.out.println(ext);
		System.out.println(sortie);
		return sortie; //On retourne un sommet avec un arc vers l'extremite du cable de l'obstacle
	}
	
	public void relierObstacleGraphe(Map map, LinkedList<Sommet> graphe, Sommet point){ //Manque de prÈcision
		int x=point.getX();
		int y=point.getY();
		Sommet plusProche=null;
		int distancePlusProche=Integer.MAX_VALUE;
		for (Sommet sommet:graphe){ // On cherche le point du graphe le plus proche
			if((distancePlusProche>Math.abs(sommet.getX()-x)+Math.abs(sommet.getY()-y)) && map.positionLibre(sommet.getX(), sommet.getY())){
				distancePlusProche=Math.abs(sommet.getX()-x)+Math.abs(sommet.getY()-y);
				plusProche=sommet;
			}
		}
		plusProche.addArc(new Arc(point, distancePlusProche)); //Ajoute une diagonale ....
		point.addArc(new Arc(plusProche, distancePlusProche));
		System.out.println(plusProche);
		System.out.println(point);
		graphe.add(point); //On rajoute le point sur le graphe
	}
	
	public void relierExtremiteGraphe(Map map, LinkedList<Sommet> graphe, Coord point){ //Fusion des deux prÈcedentes mÈthodes
		int x=point.getX();
		int y=point.getY();
		Sommet plusProche=null;
		Sommet ext=new Sommet(x, y);
		int distancePlusProche=Integer.MAX_VALUE;
		for (Sommet sommet:graphe){ // On cherche le point du graphe le plus proche
			if((distancePlusProche>Math.abs(sommet.getX()-x)+Math.abs(sommet.getY()-y)) && map.positionLibre(sommet.getX(), sommet.getY())){
				distancePlusProche=Math.abs(sommet.getX()-x)+Math.abs(sommet.getY()-y);
				plusProche=sommet;
			}
		}
		if(plusProche.getX()==x || plusProche.getY()==y){ //Si l'extremite est alignÈe avec le graphe
			plusProche.addArc(new Arc(ext, distancePlusProche)); //Ajoute l'arc droit
			ext.addArc(new Arc(plusProche, distancePlusProche));
		}else{ //Sinon c'est une diagonale, et on dÈcompose avec un point intermÈdiaire
			Sommet inter=null;
			if(Math.abs(plusProche.getX()-x) >= Math.abs(plusProche.getY()-y)){ //Si la distance sur x est plus grande
				inter=new Sommet(plusProche.getX(), y);
				inter.addArc(new Arc(ext, Math.abs(plusProche.getX()-x)));
				ext.addArc(new Arc(inter, Math.abs(plusProche.getX()-x)));
				inter.addArc(new Arc(plusProche, Math.abs(plusProche.getY()-y)));
				plusProche.addArc(new Arc(inter, Math.abs(plusProche.getY()-y)));
			} else {
				inter=new Sommet(x, plusProche.getY());
				inter.addArc(new Arc(ext, Math.abs(plusProche.getY()-y)));
				ext.addArc(new Arc(inter, Math.abs(plusProche.getY()-y)));
				inter.addArc(new Arc(plusProche, Math.abs(plusProche.getX()-x)));
				plusProche.addArc(new Arc(inter, Math.abs(plusProche.getX()-x)));
			}
			graphe.add(inter); //On ajoute le point au graphe
		}
		graphe.add(ext); //On ajoute le point au graphe
	}
	
	/* A FAIRE
	 * 
	 * GERER LE CAS DES PETITS ALLERS RETOUR SUR UN AXE AU MOMENT DE REJOINDRE LE GRAPHE ( voir ext1 sur la visu)
	 * 
	 * Sprint 3 : faire un autre relierExtremiteGraphe mais en minimisant la distance du cable (partir en direction du hub directement plutot que de cherche le plus proche)
	 * */
	
}
