package a1;

public class Paleta implements Runnable {
	private String Nom;
	private int maons;

	Paleta(String nom, int maons) {
		Nom = nom;
		maons = maons;
	}

	public void run() {
		posaMaons(maons);
	}

	public void posaMaons(int maons) {
		//Temps que triga a col·locar els maons entre 1 i 4 segons per cada maó
		System.out.println(Nom + " posant maons...");
		try {
			Thread.sleep((long) ((Math.random()*300)+100)*maons );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Nom + " ha acabat.");
		
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}
	
	

}
