package tema1;

public class Monom implements Comparable <Monom>{

	private int putere;
	private float coeficient;
	
	public Monom(int putere, float coeficient) {
		
		this.putere = putere;
		this.coeficient = coeficient;
	}
	
	public Monom() {
		
		this.putere = 0;
		this.coeficient = 0;
	}
	
	public float getCoeficient() {
		
		return coeficient;
	}
	
	public void setCoeficient(float coeficient) {
		
		this.coeficient = coeficient;
	}
	
	public int getPutere() {
		
		return putere;
	}
	
	public void setPutere(int d) {
		
		this.putere = d;
	}

	public Monom adunareMonoame(Monom m) {
		
		Monom result = new Monom();
			
		result.setPutere(this.getPutere());
		result.setCoeficient(this.getCoeficient() + m.getCoeficient());
		
		return result;
	}
	
	public Monom derivareMonom() {
		
		Monom result = new Monom();
		
		result.setCoeficient(this.getCoeficient() * this.getPutere());
		result.setPutere(this.putere - 1);
		
		return result;
	}
	
	public Monom integrareMonom() {
		
		Monom result = new Monom();
		
		result.setCoeficient(this.getCoeficient() / (this.getPutere()+1));
		result.setPutere(this.putere + 1);
		
		return result;
	}
	
	public Monom inmultireMonoame(Monom m) {
		
		Monom result = new Monom();
		
		result.setPutere(this.getPutere() + m.getPutere());
		result.setCoeficient(this.getCoeficient() * m.getCoeficient());
		
		return result;
	}
	
	public Monom impartireMonoame(Monom m) {
		
		Monom result = new Monom();
		
		result.setPutere(this.getPutere() - m.getPutere());
		result.setCoeficient(this.getCoeficient() / m.getCoeficient());
		
		return result;
	}
	
	@Override
	public int compareTo(Monom o) {
		if(this.getPutere() > o.getPutere())
			return -1;
		else
			if(this.getPutere() == o.getPutere()) {
				if(this.getCoeficient() > o.getCoeficient())
					return -1;
				else
					if(this.getCoeficient() < o.getCoeficient())
						return 1;
					else
						if(this.getCoeficient() == o.getCoeficient())
							return 0;
					}
			else
				if(this.getPutere() < o.getPutere())
					return 1;
		return -1;
	}

}
