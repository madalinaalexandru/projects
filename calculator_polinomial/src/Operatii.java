package tema1;

import java.util.ArrayList;

public class Operatii {
	
	public Operatii() {
	
	}

	public Polinom adunare(Polinom p1, Polinom p2) {
		
		Polinom rez = new Polinom();
		
		int indexP1 = 0; //indexul cu care parcurgem primul polinom
		int indexP2 = 0; //indexul cu care parcurgem al doilea polinom
		
		//verificam ca nu am ajuns la sfarsitul niciunuia dintre polinoame
		while(indexP1 != p1.getMonomList().size() && indexP2 != p2.getMonomList().size()) {
			
			//monom in care va fi memorat, pe rand, rezultatul adunarii a doua monoame
			Monom mResult = new Monom(); 
			
			//testam daca puterile monoamelor curente sunt egale; in cazul in care sunt, sunt adunati coeficientii
			if(p1.getMonomList().get(indexP1).getPutere() == p2.getMonomList().get(indexP2).getPutere()) {
				mResult = p1.getMonomList().get(indexP1).adunareMonoame(p2.getMonomList().get(indexP2)); //se aduna monoamele curente
				
				//in cazul in care rezultatul nu este 0, monomul este adaugat la polinomul rezultat
				if(mResult.getCoeficient() != 0)
					rez.getMonomList().add(mResult);
				
				indexP1++;
				indexP2++;
			}
			else //in cazul in care puterile monoamelor curente nu sunt egale, este adaugat la rezultat monomul cu puterea cea mai mare
				if(p1.getMonomList().get(indexP1).getPutere() > p2.getMonomList().get(indexP2).getPutere()) {
					mResult.setCoeficient(p1.getMonomList().get(indexP1).getCoeficient());
					mResult.setPutere(p1.getMonomList().get(indexP1).getPutere());
					
					rez.getMonomList().add(mResult);
					indexP1++;
				}
				else
					if(p1.getMonomList().get(indexP1).getPutere() < p2.getMonomList().get(indexP2).getPutere()) {
						
						mResult.setCoeficient(p2.getMonomList().get(indexP2).getCoeficient());
						mResult.setPutere(p2.getMonomList().get(indexP2).getPutere());
						
						rez.getMonomList().add(mResult);
						indexP2++;
					}
		}
		
		//verificam daca exista un polinom din care nu au fost procesate toate monoamele si i le adaugam la rezultat in cazul in care nu, in ordinea descrescatoare a puterilor
		if(indexP1 != p1.getMonomList().size()) {
			for(; indexP1 < p1.getMonomList().size(); indexP1++) {
				Monom mResult = new Monom();
				
				mResult.setCoeficient(p1.getMonomList().get(indexP1).getCoeficient());
				mResult.setPutere(p1.getMonomList().get(indexP1).getPutere());
				
				rez.getMonomList().add(mResult);			
			}
		}
		
		if(indexP2 != p2.getMonomList().size()) {
			for(; indexP2 < p2.getMonomList().size(); indexP2++) {
				Monom mResult = new Monom();
				
				mResult.setCoeficient(p2.getMonomList().get(indexP2).getCoeficient());
				mResult.setPutere(p2.getMonomList().get(indexP2).getPutere());
				
				rez.getMonomList().add(mResult);			
			}
		}
		
		//in cazul in care rezultatul nu contine niciun monom, rezultatul va primi valoarea 0
		if(rez.getMonomList().isEmpty())
			rez.getMonomList().add(new Monom());
		
		return rez;
	}
	
	public Polinom scadere(Polinom p1, Polinom p2) {

		Polinom rez = new Polinom();
		Polinom p2Copy = new Polinom(p2.toString());
		
		for(Monom m : p2Copy.getMonomList()) {
			m.setCoeficient(m.getCoeficient() * (-1));
		}
		
		Operatii op = new Operatii();
		
		rez = op.adunare(p1, p2Copy);
		
		return rez;
	}
	
	public Polinom inmultire(Polinom p1,Polinom p2) {
		
		ArrayList<Polinom> listaPolinoame = new ArrayList<Polinom>(); //lista de polinoame rezultate dupa inmultirea cu monoamele celui de-al doilea 
		
		//parcurgerea monoamelor primului polinom
		for(Monom m : p1.getMonomList()) {
			 
			Polinom result = new Polinom(); //polinomul rezultat dupa inmultirea monomului curent din primul polinom cu fiecare dintre monoamele celui de-al doilea
			
			//parcurgerea monoamelor celui de-al doilea polinom
			for(Monom n : p2.getMonomList()) {
				
				//inmultirea monoamelor curente
				result.getMonomList().add(m.inmultireMonoame(n)); 
			}
			
			listaPolinoame.add(result); //se adauga polinomul rezultat in lista de polinoame
		}
		
		Polinom rez = new Polinom(); //polinomul rezultat in care vom aduna rezultatele inmultirilor
		Operatii op = new Operatii();
		
		for(Polinom polinom : listaPolinoame) {
			
			rez = op.adunare(rez, polinom);
		}
		
		return rez;
	}
	
	public String impartire(Polinom p1, Polinom p2) {

		String rez = new String(); //Stringul care va contine rezultatul impartirii
		
		Polinom cat = new Polinom(); //polinomul care va contine catul impartirii
		Polinom rest = new Polinom(); //polinomul care va contine restul impartirii
		
		rest = p1; //mai intai, restul ia valoarea deimpartitului
		
		//in cazul in care impartitorul are un grad mai mare decat deimpartitul, catul va fi 0, iar restul isi va pastra valoarea
		if(rest.getMonomList().get(0).getPutere() < p2.getMonomList().get(0).getPutere()) {
			
			rez += "cat = 0" + ", rest = " + rest.toString();
			return rez;
		}
		else {
			
			//impatirea se va face cat timp restul are un grad >= cu gradul impartitorului
			while(rest.getMonomList().get(0).getPutere() >= p2.getMonomList().get(0).getPutere()) {
				
				Monom monomC = new Monom(); //monomul in care se retine monomul = cu raportul dintre monomul cu puterea cea mai mare putere din polinomul rest si monomul cu cea mai mare putere din polinomul impartitor 
				Polinom polinomC = new Polinom(); //polinomul ce va contine monomul cu care trebuie inmultit impartitorul pentru a fi scazut din rest
			
				//se face raportul dintre monoame			
				monomC = rest.getMonomList().get(0).impartireMonoame(p2.getMonomList().get(0));
				
				//monomul rezultat se adauga la cat si in polinomul cat curent
				cat.getMonomList().add(monomC); 
				polinomC.getMonomList().add(monomC);
			
				Operatii opScadere = new Operatii();
				Operatii opInmultire = new Operatii();
				rest = opScadere.scadere(rest, opInmultire.inmultire(p2, polinomC)); //restul curent se calcularea prin scaderea polinomului rezultat din scaderea polinomului cat curent inmultita cu impartitorul din restul curent
			
				//cazul in care restul este 0
				if(rest.getMonomList().isEmpty()) {
					
					rest.getMonomList().add(new Monom(0,0));
				}
			}
		
			rez += "cat = " + cat.toString() + ", rest = " + rest.toString();
		}
		
		return rez;
	}
	
	public Polinom derivare(Polinom p) {
		
		Polinom rez = new Polinom(); //polinomul in care va fi retinut rezultatul derivarii
		
		//parcurgem lista de monoame a poliomului
		for(Monom m : p.getMonomList()) {
			
			//in cazul in care puterea este mai mare decat 0, putem deriva monomul curent
			if(m.getPutere() > 0) {
				
				rez.getMonomList().add(m.derivareMonom());//adugam monomul derivat in polinomul rezultat
			}
		}
		return rez;
	}
	
	public Polinom integrare(Polinom p) {
		
		Polinom rez = new Polinom(); //polinomul in care este retinut rezultatul integrarii
		
		for(Monom m : p.getMonomList()) {
			
			 //integrarea monomului curent si adaugarea lui in lista de monoame a rezultatului	
			rez.getMonomList().add(m.integrareMonom());
		}
		
		return rez;
	}
	
	public float valoareInX(Polinom p, float x) {
		
		float rez = 0; //rezultatul este initial 0
		
		//parcurgem lista de monoame a polinomului
		for(Monom m : p.getMonomList()) {
			
			rez += m.getCoeficient() * Math.pow(x, m.getPutere()); //pentru fiecare monom calculam valoarea in valoarea ceruta si o adaugam la rezultat
		}
		
		return rez;
	}
}
