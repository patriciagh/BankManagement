package model;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class SavingAccount extends Account 
{
	private final double dobanda = 2; // 2% pe luna
	// Daca control e 0 atunci inca nu s-a adaugat si nici scos .
	// O adaugare => control - 1 
	// O adaugare + retragere => control 2 - nu se poate retrage daca nu s-a adaugat pentru ca nu as avea ce sa retrag
	// Cand retrag dau si numarul de luni cat a stat acolo si inmultesc cu dobanda . 
    int durata = 6;    
	public SavingAccount(int id, double suma, Person p) 
	{
		super(id, suma,p);
	}		
	public void adaugare(double suma) 
	{
		//@pre : suma pe care o adaug sa fie >=1000
		assert suma >=1000 : "Suma introdusa este prea mica !";
		this.setSumaCont(this.getSumaCont()+suma);	
		setChanged();
		notifyObservers("S-au adaugat bani in contul "+this.getIdCont());
	}	
	
	public void dobanda()
	{
		double dobandaCalculata= (this.getSumaCont()*dobanda*durata)/100;// Cu cat creste suma dupa 6 luni	    
		System.out.println(" DOBANDA : "+dobandaCalculata );
		System.out.println(" SUMA CU DOBANDA : "+(this.getSumaCont()+dobandaCalculata));		
		this.setSumaCont(this.getSumaCont()+dobandaCalculata);
	}
	//@Override
	public void retragere(double s) 
	{
			// @pre : suma de extras sa fie >=1000 
			assert s >= 1000 : "Nu se pot retrage bani!";
		    // suma devine cea cu dobanda pe 6 luni
			assert this.getSumaCont()-s >=0 : "Stoc insuficient !";
			double sumaNoua=this.getSumaCont()-s;
			this.setSumaCont(sumaNoua); 
			System.out.println("Noua suma din cont dupa retragere : "+this.getSumaCont());
			System.out.println();
			//@post : sa nu ramana contul pe -
			assert this.getSumaCont() >=0 : "S-a extras prea mult ";// trebuie sa nu ajunga la 0
			setChanged();
			notifyObservers("S-au retras bani din contul "+this.getIdCont());
	}
	
}
