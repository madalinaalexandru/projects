package tema1;

import java.text.*;
import java.util.ArrayList;
import java.util.Collections;

public class Polinom {

	private ArrayList<Monom> monomList;
	
	public Polinom(String p) {
		
		this.monomList = new ArrayList<Monom>();
		
		this.monomList = procesarePolinom(p);
	}
	
	public Polinom() {
		
		this.monomList = new ArrayList<Monom>();
	}

	public ArrayList<Monom> getMonomList() {
		
		return monomList;
	}
	
	public void adunarePuteriEgale() {
		Polinom p = new Polinom();
		int j = 0;
		
		if(this.getMonomList().size() != 1) {
		for(int i = 0; i < this.getMonomList().size(); i++) {
			if(i != this.getMonomList().size() - 1) {
			if(this.getMonomList().get(i).getPutere() == this.getMonomList().get(i+1).getPutere()) {
				
				Monom m = new Monom();
				m = this.getMonomList().get(i);
				j=i;
				i++;
				
				while(this.getMonomList().get(j).getPutere() == this.getMonomList().get(i).getPutere()) {
					m = m.adunareMonoame(this.getMonomList().get(i));
					i++;
					
					if(i==this.getMonomList().size())
						break;
				}
				i--;
				if(m.getCoeficient() != 0)
				p.getMonomList().add(m);
			}
			else {
				p.getMonomList().add(this.getMonomList().get(i));
			}
			}
			else {
				p.getMonomList().add(this.getMonomList().get(i));
			}
		}
		
		this.getMonomList().clear();
		
		for(Monom m:p.getMonomList()) {
			this.getMonomList().add(m);
		}
		}
	}
	
	public ArrayList<Monom> procesarePolinom(String p){
		
		//impartirea Stringului input pe monoame
		if(p == null)
			System.out.println("Nu s-a introdus niciun polinom");
		else {
			int i = 0;
			
			if(p.charAt(0) == 'x') {
				Monom m = new Monom();
				
				m.setCoeficient(1);
				
				if(p.length() > 1) {
					if(p.charAt(1) == '^') {
						String putere = new String();
						
						for(int k = 2; k < p.length(); k++)
							if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
								putere += p.charAt(k);
							}
							else
								break;
					
						m.setPutere(Integer.parseInt(putere));
						i += putere.length() + 2;
					}
					else {
						m.setPutere(1);
						i++;
					}
				}
				else {
					m.setPutere(1);
					i=p.length();
				}
				
				this.getMonomList().add(m);
			}
			
			if(p.charAt(0) <= '9' && p.charAt(0) >= '0') {
				Monom m = new Monom();
				
				if(p.length() >= 1) {
					String coeficient = new String();
					int index = 0;
				
					for(int k = 0; k < p.length(); k++) {
						if(p.charAt(k) <= '9' && p.charAt(k) >= '0' || p.charAt(k) == '.') {
							coeficient += p.charAt(k);
						}
						else {
							break;
							}
						
						index = k;
					}
					
					if(index == p.length() - 1) {
						m.setCoeficient(Float.parseFloat(coeficient));
						m.setPutere(0);
						i += p.length();
					}
					else {
						if(p.charAt(coeficient.length()) == 'x') {
							m.setCoeficient(Float.parseFloat(coeficient));
							i += coeficient.length() + 1;

							if(p.length() > coeficient.length() + 1) {
								if(p.charAt(i) == '^'){
									String putere = new String();
							
									for(int k = i+1; k < p.length(); k++)
										if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
											putere += p.charAt(k);
										}
										else
											break;
							
									m.setPutere(Integer.parseInt(putere));
									i += putere.length() + 1;
									}
								else {
									m.setPutere(1);
								}
								}
							else {
								m.setPutere(1);
							}
						}
					}
				}
				
				this.getMonomList().add(m);
			}
			
			for(; i < p.length(); i++) {
				Monom m = new Monom();
				
				if(p.charAt(i) == '+') {
					i++;
					
					if(p.charAt(i) == 'x') {
						m.setCoeficient(1);
						
						if(p.length() > i+1) {
							if(p.charAt(i+1) == '^') {
								String putere = new String();
								
								for(int k = i+2; k < p.length(); k++)
									if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
										putere += p.charAt(k);
									}
									else
										break;
							
								m.setPutere(Integer.parseInt(putere));
								i += putere.length() + 1;
							}
							else {
								m.setPutere(1);
							}
						}
						else {
							m.setPutere(1);
						}
					}
					else {
					if(p.charAt(i) <= '9' && p.charAt(i) >= '0') {
						if(p.length() > 1) {
							String coeficient = new String();
						
							for(int k = i; k < p.length(); k++) {
								if(p.charAt(k) <= '9' && p.charAt(k) >= '0' || p.charAt(k) == '.') {
									coeficient += p.charAt(k);
								}
								else {
									break;
									}
							}
							i += coeficient.length();
							if(i == p.length()) {
								m.setCoeficient(Float.parseFloat(coeficient));
								m.setPutere(0);
								i += p.length();
							}
							else {
								if(p.charAt(i) == 'x') {
									m.setCoeficient(Float.parseFloat(coeficient));

									if(p.length() > i+1) {
										if(p.charAt(i+1) == '^'){
											String putere = new String();
									
											for(int k = i+2; k < p.length(); k++)
												if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
													putere += p.charAt(k);
												}
												else
													break;
									
											m.setPutere(Integer.parseInt(putere));
											i += putere.length() + 1;
											}
										else {
											m.setPutere(1);
										}
									}
									else
										m.setPutere(1);
								}
								else {
									m.setCoeficient(Float.parseFloat(coeficient));
									m.setPutere(0);
									i--;
								}
							}
						}
					}}
				}
				else {
					if(p.charAt(i) == '-') {
						i++;
						
						if(p.charAt(i) == 'x') {
							m.setCoeficient(-1);
							
							if(p.length() > i+1) {
								if(p.charAt(i+1) == '^') {
									String putere = new String();
									
									for(int k = i+2; k < p.length(); k++)
										if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
											putere += p.charAt(k);
										}
										else
											break;
								
									m.setPutere(Integer.parseInt(putere));
									i += putere.length() + 1;
								}
								else
									m.setPutere(1);
							}
							else {
								m.setPutere(1);
							}
						}
						else {
						if(p.charAt(i) <= '9' && p.charAt(i) >= '0') {
							String coeficient = new String();
							
							for(int k = i; k < p.length(); k++) {
								if(p.charAt(k) <= '9' && p.charAt(k) >= '0' || p.charAt(k) == '.') {
									coeficient += p.charAt(k);
								}
								else {
									break;
									}
							}
							
							i += coeficient.length();
							
							if(i == p.length()) {
								m.setCoeficient(Float.parseFloat(coeficient) * (-1));
								m.setPutere(0);
								i += p.length();
							}
							else {
								if(p.charAt(i) == 'x') {
									m.setCoeficient(Float.parseFloat(coeficient) * (-1));

									if(p.length() > i+1) {
										if(p.charAt(i+1) == '^'){
											String putere = new String();
										
											for(int k = i+2; k < p.length(); k++)
												if(p.charAt(k) <= '9' && p.charAt(k) >= '0') {
													putere += p.charAt(k);
												}
												else
													break;
										
											m.setPutere(Integer.parseInt(putere));
											i += putere.length() + 1;
											}
											
										else
											m.setPutere(1);
										}
									else {
										m.setPutere(1);
									}
									}
								else {
									m.setCoeficient(Float.parseFloat(coeficient) * (-1));
									m.setPutere(0);
									i--;
								}
								}
							}
						}
					}
				}
				if(m.getCoeficient() != 0)
					this.getMonomList().add(m);
			}
		}
		Collections.sort(this.getMonomList());
		this.adunarePuteriEgale();
		return this.getMonomList();
	}
	
	public String toString() {
		
		//functia care transforma un polinom in forma sa text
		String polinom = new String();
		int index = 0;
		DecimalFormat format = new DecimalFormat("0.###");
		
		for(Monom m : this.getMonomList()) {
			
			if(index == 0) {
				if(m.getCoeficient() == 0) {
					
					polinom += "0";
				}
				
				if(m.getCoeficient() == 1) {
					
					if(m.getPutere() == 0) {
						
						polinom += "1";
					}
				
					if(m.getPutere() == 1) {
						
						polinom += "x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += "x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() == -1) {
					
					if(m.getPutere() == 0) {
						
						polinom += "-1";
					}
				
					if(m.getPutere() == 1) {
						
						polinom += "-x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += "-x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() > 1 || (m.getCoeficient() > 0 && m.getCoeficient() < 1)) {
					
					if(m.getPutere() == 0) {
						
						polinom += format.format(m.getCoeficient());
					}
				
					if(m.getPutere() == 1) {
						
						polinom += format.format(m.getCoeficient()) + "x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += format.format(m.getCoeficient()) + "x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() < -1 || (m.getCoeficient() < 0 && m.getCoeficient() > -1)) {
					
					if(m.getPutere() == 0) {
						
						polinom += format.format(m.getCoeficient());
					}
				
					if(m.getPutere() == 1) {
						
						polinom += format.format(m.getCoeficient()) + "x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += format.format(m.getCoeficient()) + "x^" + m.getPutere();
					}
				}
				
				index++;
			}
			else {
				
				if(m.getCoeficient() == 1) {
					
					if(m.getPutere() == 0) {
						
						polinom += "+1";
					}
				
					if(m.getPutere() == 1) {
						
						polinom += "+x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += "+x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() == -1) {
					
					if(m.getPutere() == 0) {
						
						polinom += "-1";
					}
				
					if(m.getPutere() == 1) {
						
						polinom += "-x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += "-x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() > 1 || (m.getCoeficient() > 0 && m.getCoeficient() < 1)) {
					
					if(m.getPutere() == 0) {
						
						polinom += "+" + format.format(m.getCoeficient());
					}
				
					if(m.getPutere() == 1) {
						
						polinom += "+" + format.format(m.getCoeficient()) + "x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += "+" + format.format(m.getCoeficient()) + "x^" + m.getPutere();
					}
				}
			
				if(m.getCoeficient() < -1 || (m.getCoeficient() < 0 && m.getCoeficient() > -1)) {
					
					if(m.getPutere() == 0) {
						
						polinom += format.format(m.getCoeficient());
					}
				
					if(m.getPutere() == 1) {
						
						polinom += format.format(m.getCoeficient()) + "x";
					}
				
					if(m.getPutere() > 1) {
						
						polinom += format.format(m.getCoeficient()) + "x^" + m.getPutere();
					}
				}
			}
		}
		
		return polinom;
	}

}