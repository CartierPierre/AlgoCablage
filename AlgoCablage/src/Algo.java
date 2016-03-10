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
				if(som.getX() + pas == fils.getX()  && som.getY() == fils.getY()){ //Arc Ã  droite
					som.addArc(new Arc(fils, pas));
				}
				else if(som.getX() - pas== fils.getX()  && som.getY() == fils.getY()){ //Arc Ã  gauche
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
	
	
	public void dijkstra(LinkedList<Sommet> graphe, Sommet sommet){
		for(Arc arc:sommet.getArcs()){ // On met a jour la distance de tous les sommets reliés
			if(arc.getSommet().getDistFromStart() - sommet.getDistFromStart() > arc.getLongueur()){ //Si elle est plus courte
				arc.getSommet().setDistFromStart(arc.getLongueur() + sommet.getDistFromStart());
				arc.getSommet().setPere(sommet); //On met a jour le pere
				dijkstra(graphe, arc.getSommet()); //Appel recursif
			}
		}
	}
	
	public Cable cheminLePLusCourt(LinkedList<Sommet> graphe, int x, int y, int pas){ // Transforme le dijkstra en angles pour le cable
		Sommet tmpSommet=null;
		//Sommet supprSommet=null;
		Cable cable = new Cable();
		for(Sommet sommet:graphe){ //On cherche le sommet du graphe aux coordonnees (x,y) A CHANGER AVEC CHERCHERSOMMET
			if(sommet.getX() == x && sommet.getY() == y){
				tmpSommet=sommet;
				break;
			}
		}
		while(tmpSommet != null){
			//supprSommet=tmpSommet;
			if(tmpSommet.getCable()!=null){ //Si un cable est deja present //A GENERALISER POUR N CABLES
				repousserCable(graphe, tmpSommet.getX(), tmpSommet.getY(), pas);
				
				/*Sommet somHaut=chercherSommet(graphe,tmpSommet.getX()-pas, tmpSommet.getY());
				Sommet somBas=chercherSommet(graphe,tmpSommet.getX()+pas, tmpSommet.getY());
				Sommet somGauche=chercherSommet(graphe,tmpSommet.getX(), tmpSommet.getY()-pas);
				Sommet somDroite=chercherSommet(graphe,tmpSommet.getX(), tmpSommet.getY()+pas);
				Sommet tmpSommet2=null;
				if(somHaut.getCable()==somBas.getCable() && somHaut.getCable()!=null){ //droite verticale
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()-pas, tmpSommet.getY());
					if(tmpSommet2.getCable()==cable){ //Occupé à gauche
						tmpSommet2=chercherSommet(graphe, tmpSommet.getX()+pas, tmpSommet.getY());
					}
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet2.getX(),tmpSommet2.getY());
					tmpSommet2=chercherSommet(graphe, tmpSommet2.getX(), tmpSommet.getY()-pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					tmpSommet2=chercherSommet(graphe, tmpSommet2.getX(), tmpSommet.getY()+pas+pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					bornerAngle(tmpSommet.getCable(),tmpSommet2.getX(),tmpSommet2.getY()-pas,tmpSommet2.getX(),tmpSommet2.getY(),tmpSommet2.getX(),tmpSommet2.getY()-pas-pas);
				}
				else if(somGauche.getCable()==somDroite.getCable() && somGauche.getCable()!=null){ //droite horizontale
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX(), tmpSommet.getY()-pas);
					if(tmpSommet2.getCable()==cable){ //Occupé à gauche
						tmpSommet2=chercherSommet(graphe, tmpSommet.getX(), tmpSommet.getY()+pas);
					}
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet2.getX(),tmpSommet2.getY());
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()-pas, tmpSommet2.getY());
					tmpSommet2.setCable(tmpSommet.getCable());
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()+pas+pas, tmpSommet2.getY());
					tmpSommet2.setCable(tmpSommet.getCable());
					bornerAngle(tmpSommet.getCable(),tmpSommet2.getX()-pas,tmpSommet2.getY(),tmpSommet2.getX(),tmpSommet2.getY(),tmpSommet2.getX()-pas-pas,tmpSommet2.getY());
				}
				else if(somGauche.getCable()==somHaut.getCable() && somGauche.getCable()!=null){ //angle gauche-haut
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()-pas, tmpSommet.getY()-pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet.getX()-pas,tmpSommet.getY()-pas);
				}
				else if(somGauche.getCable()==somBas.getCable() && somGauche.getCable()!=null){//angle gauche-bas
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()-pas, tmpSommet.getY()+pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet.getX()-pas,tmpSommet.getY()+pas);
				}
				else if(somDroite.getCable()==somHaut.getCable() && somDroite.getCable()!=null){//angle droite-haut
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()+pas, tmpSommet.getY()-pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet.getX()+pas,tmpSommet.getY()-pas);
				}
				else if(somDroite.getCable()==somBas.getCable() && somDroite.getCable()!=null){//angle droite-bas
					tmpSommet2=chercherSommet(graphe, tmpSommet.getX()+pas, tmpSommet.getY()+pas);
					tmpSommet2.setCable(tmpSommet.getCable());
					modifierAngle(tmpSommet.getCable(),tmpSommet.getX(),tmpSommet.getY(),tmpSommet.getX()+pas,tmpSommet.getY()+pas);
				}
				else{
					System.err.println("Problème lors du repoussement du cable");
				}*/
			} //Fin de déplacement du cable bloquant
			
			/*ICI ON DOIT, NON PAS SUPPRIMER, MAIS DEFINIR UNE PRIORITE AVEC UN NUMERO DE CABLE QUI PASSE PAR LE POINT*/
			cable.addAngle(tmpSommet); //On ajoute les segments (angles plats)
			tmpSommet.setCable(cable);
			boolean alter=false;
			if(tmpSommet.getPere()!=null && tmpSommet.getPere().getCable()!=null){ //On cherche un chemin autre de meme longueur
				for(Arc arc:tmpSommet.getArcs()){
					if(tmpSommet.getPere().getDistFromStart()>=arc.getSommet().getDistFromStart() && arc.getSommet()!=tmpSommet.getPere()){ //Si un autre chemin de meme longueur existe
						tmpSommet=arc.getSommet();
						alter=true;
						break;
					}
				}
			}
			if(!alter) tmpSommet=tmpSommet.getPere();
			//graphe.remove(supprSommet);
		} //Fin du tracé, arrivé au hub
		//cable.epurerAngles(); -> A faire séparement (etienne le fait pendant l'ecriture en json visiblement)
		return cable;
	}
	
	public void repousserCable(LinkedList<Sommet> graphe, int x, int y, int pas){
		Sommet somHaut=chercherSommet(graphe,x-pas, y);
		Sommet somBas=chercherSommet(graphe,x+pas, y);
		Sommet somGauche=chercherSommet(graphe,x, y-pas);
		Sommet somDroite=chercherSommet(graphe,x, y+pas);
		Sommet tmpSommet2=null;
		Sommet tmpSommet=chercherSommet(graphe, x, y);
		Cable cable=tmpSommet.getCable();
		if(somHaut!=null && somBas!=null && somHaut.getCable()==somBas.getCable() && somHaut.getCable()!=null){ //droite verticale
			tmpSommet2=chercherSommet(graphe, x-pas, y);
			if(tmpSommet2.getCable()==cable){ //Occupé à gauche
				tmpSommet2=chercherSommet(graphe, x+pas,y);
			}
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,tmpSommet2.getX(),tmpSommet2.getY());
			tmpSommet2=chercherSommet(graphe, tmpSommet2.getX(), y-pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			tmpSommet2=chercherSommet(graphe, tmpSommet2.getX(), y+pas+pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			bornerAngle(tmpSommet.getCable(),tmpSommet2.getX(),tmpSommet2.getY()-pas,tmpSommet2.getX(),tmpSommet2.getY(),tmpSommet2.getX(),tmpSommet2.getY()-pas-pas);
		}
		else if(somGauche!=null && somDroite!=null && somGauche.getCable()==somDroite.getCable() && somGauche.getCable()!=null){ //droite horizontale
			tmpSommet2=chercherSommet(graphe, x, y-pas);
			if(tmpSommet2.getCable()==cable){ //Occupé à gauche
				tmpSommet2=chercherSommet(graphe, x, y+pas);
			}
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,tmpSommet2.getX(),tmpSommet2.getY());
			tmpSommet2=chercherSommet(graphe, x-pas, tmpSommet2.getY());
			tmpSommet2.setCable(tmpSommet.getCable());
			tmpSommet2=chercherSommet(graphe, x+pas+pas, tmpSommet2.getY());
			tmpSommet2.setCable(tmpSommet.getCable());
			bornerAngle(tmpSommet.getCable(),tmpSommet2.getX()-pas,tmpSommet2.getY(),tmpSommet2.getX(),tmpSommet2.getY(),tmpSommet2.getX()-pas-pas,tmpSommet2.getY());
		}
		else if(somGauche!=null && somHaut!=null && somGauche.getCable()==somHaut.getCable() && somGauche.getCable()!=null){ //angle gauche-haut
			tmpSommet2=chercherSommet(graphe, x-pas, y-pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,x-pas,y-pas);
		}
		else if(somGauche!=null && somBas!=null && somGauche.getCable()==somBas.getCable() && somGauche.getCable()!=null){//angle gauche-bas
			tmpSommet2=chercherSommet(graphe, x-pas, y+pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,x-pas,y+pas);
		}
		else if(somHaut!=null && somDroite!=null && somDroite.getCable()==somHaut.getCable() && somDroite.getCable()!=null){//angle droite-haut
			tmpSommet2=chercherSommet(graphe, x+pas, y-pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,x+pas,y-pas);
		}
		else if(somDroite!=null && somBas!=null && somDroite.getCable()==somBas.getCable() && somDroite.getCable()!=null){//angle droite-bas
			tmpSommet2=chercherSommet(graphe, x+pas, y+pas);
			tmpSommet2.setCable(tmpSommet.getCable());
			modifierAngle(tmpSommet.getCable(),x,y,x+pas,y+pas);
		}
		else{
			System.err.println("Problème lors du repoussement du cable");
		}
	}
	
	/**
     * remplace un angle d'un cable par un autre angle
     *
     * @param c Le cable à modifier
     * @param x1,y1 Les coordonnées de l'angle à remplacer
     * @param x2,y2 Les coordonnées de l'angle à insérer
     */
    public void modifierAngle(Cable c, int x1, int y1, int x2, int y2){
            LinkedList<Coord> list= c.getAngles();
            for(Coord p:list){
                    if(p.getX()==x1 && p.getY()==y1){
                            p.setX(x2);
                            p.setY(y2);
                    }
            }
    }

    /**
     * Ajoute deux angles autour d'un premier angle.
     * Les angles seront placés si possible
     * de manière à conserver l'aspect orthogonal du cable.
     *
     * @param c Le cable à modifier
     * @param x1, y1 l'angle à borner
     * @param x2,y2 ; x3,y3 les deux angles à ajouter
     */
    public void bornerAngle(Cable c, int x1, int y1, int x2, int y2, int x3, int y3){
            LinkedList<Coord> list= c.getAngles();
            for(int i=1 ; i<list.size() ; i++){
                    if(list.get(i).getX()==x1 && list.get(i).getY() == y1){
                            if(list.get(i-1).getX() == x2 || list.get(i-1).getY() == y2){
                                    list.add(i+1,new Coord(x3,y3));
                                    list.add(i,new Coord(x2,y2));
                            }
                            else{
                                    list.add(i+1,new Coord(x2,y2));
                                    list.add(i,new Coord(x3,y3));
                            }
                            i++;
                    }
            }
    }
	
	public Sommet sortirObs(LinkedList<Sommet> graphe, Obstacle obs, Coord point){ //Version sortie la plus proche
		int dist=Integer.MAX_VALUE;
		int x=point.getX(); //Sécurité si x et y sont inchangés
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
		return sortie; //On retourne un sommet avec un arc vers l'extremite du cable de l'obstacle
	}
	
	public void relierObstacleGraphe(Map map, LinkedList<Sommet> graphe, Sommet point){ //Manque de précision
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
		graphe.add(point); //On rajoute le point sur le graphe
	}
	
	public void relierExtremiteGraphe(Map map, LinkedList<Sommet> graphe, Coord point){ //Fusion des deux précedentes méthodes
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
		if(plusProche.getX()==x || plusProche.getY()==y){ //Si l'extremite est alignée avec le graphe
			plusProche.addArc(new Arc(ext, distancePlusProche)); //Ajoute l'arc droit
			ext.addArc(new Arc(plusProche, distancePlusProche));
		}else{ //Sinon c'est une diagonale, et on décompose avec un point intermédiaire
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
	
	public void trierExtremitesStand(Map map1) {
		Coord norme = new Coord(map1.getExtremiteHub().getX(),map1.getExtremiteHub().getY()); //Un point pour calculer l'angle, 0 degré étant un point aligné a gauche horizontal
		LinkedList<Double> anglesStand = new LinkedList<Double>();
		LinkedList<Coord> listeExt = map1.getExtremitesStand();
		double angle=0.0;
		for(int i=0; i<listeExt.size(); i++){
			angle=-1.0*(-180+ Math.toDegrees(Math.atan2((double) 1.0*listeExt.get(i).getY()-norme.getY(), (double) 1.0*listeExt.get(i).getX()-norme.getX())));
			if(angle<0)
				angle+=360;
			anglesStand.add(angle);
		}
		Coord tmp = null;
		double tmpMin = Double.MAX_VALUE;
		int iMin = 0;
		for(int i=0; i<listeExt.size(); i++){ //Pour chaque extremite
			tmpMin = Double.MAX_VALUE;
			iMin=i;
			for(int j = i; j<listeExt.size(); j++){ //Recherche du min angle
				if(tmpMin>anglesStand.get(j)){
					tmpMin=anglesStand.get(j); //On récupere l'élément mini
					iMin=j;
				}
			}
			tmp=listeExt.get(i); //On echange le premier element avec le mini
			listeExt.set(i, listeExt.get(iMin));
			listeExt.set(iMin, tmp);
			anglesStand.set(iMin, anglesStand.get(i)); //On ne permute pas, juste ecrasement de valeur
		}
		map1.setExtremitesStand(listeExt);
	}


	public Sommet chercherSommet(LinkedList<Sommet> graphe, int x, int y) {
		for(Sommet som:graphe){
			if(som.getX()==x && som.getY()==y)
				return som;
		}
		return null;
	}
	
	/* A FAIRE
	 * 
	 * GERER LE CAS DES PETITS ALLERS RETOUR SUR UN AXE AU MOMENT DE REJOINDRE LE GRAPHE ( voir ext1 sur la visu)
	 * 
	 * Sprint 3 : faire un autre relierExtremiteGraphe mais en minimisant la distance du cable (partir en direction du hub directement plutot que de cherche le plus proche)
	 * */
	
}
