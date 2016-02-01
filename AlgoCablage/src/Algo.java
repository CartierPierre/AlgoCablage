import java.util.LinkedList;

public class Algo {
	
	
	
	
	public void dijkstra(LinkedList<Sommet> graphe){
		Sommet tmpPere=null;
		for(Sommet sommet:graphe){ //Pour chaque sommet du graphe
			for(Arc arc:sommet.getArcs()){ // On met a jour la distance de tous les sommets reliés
				if(arc.getSommet().getDistFromStart() > arc.getLongueur() + sommet.getDistFromStart()){ //Si elle est plus courte
					arc.getSommet().setDistFromStart(arc.getLongueur() + sommet.getDistFromStart());
					tmpPere=arc.getSommet();
				}			
			}
			sommet.setPere(tmpPere); //On met a jour le pere
		}
	}
	
	public void cheminLePLusCourt(Sommet sommet, Cable cable){ // Transforme le dijkstra en angles pour le cable
		Sommet tmpSommet=sommet;
		cable.resetAngles();
		while(tmpSommet != null){
			cable.addAngle(tmpSommet);
			tmpSommet=tmpSommet.getPere();
		}
	}
	
	
}
