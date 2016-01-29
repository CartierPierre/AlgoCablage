
public class Arc {
	private Sommet sommet;
	private int longueur;
	
	public Arc(Sommet sommet, int longueur) {
		this.sommet = sommet;
		this.longueur = longueur;
	}

	public Sommet getSommet() {
		return sommet;
	}

	public void setSommet(Sommet sommet) {
		this.sommet = sommet;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	
}
