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
				if(som.getX() + pas == fils.getX()  && som.getY() == fils.getY()){ //Arc à droite
					som.addArc(new Arc(fils, pas));
				}
				else if(som.getX() - pas== fils.getX()  && som.getY() == fils.getY()){ //Arc à gauche
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
			for(Arc arc:sommet.getArcs()){ // On met a jour la distance de tous les sommets reliés
				if(arc.getSommet().getDistFromStart() > arc.getLongueur() + sommet.getDistFromStart()){ //Si elle est plus courte
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
	
	public void cheminLePLusCourt(LinkedList<Sommet> graphe, int x, int y, Cable cable){ // Transforme le dijkstra en angles pour le cable
		Sommet tmpSommet=null;
		for(Sommet sommet:graphe){ //On cherche le sommet du graphe aux coordonnees (x,y)
			if(sommet.getX() == x && sommet.getY() == y) tmpSommet=sommet;
		}
		cable.resetAngles();
		while(tmpSommet != null){ 
			cable.addAngle(tmpSommet); //On ajoute les segments (angles plats) -> Il faut améliorer pour n'avoir que les angles
			tmpSommet=tmpSommet.getPere();
		}
		cable.epurer();
	}
	
	
}
