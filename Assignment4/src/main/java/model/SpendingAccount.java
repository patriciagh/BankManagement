package model;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class SpendingAccount extends Account{
	
	public SpendingAccount(int id, double suma, Person p) 
	{
		super(id, suma,p);		
	}	
	public void adaugare(double s) 
	{
		double sumaNoua=this.getSumaCont()+s;
		this.setSumaCont(sumaNoua);		
		setChanged();
		notifyObservers("S-au adaugat bani in contul "+this.getIdCont());
	}	
	public void retragere(double s) 
	{
		assert this.getSumaCont()-s >=0 : "Stoc insuficient !";
		double sumaNoua=this.getSumaCont()-s;
		this.setSumaCont(sumaNoua); 
		setChanged();
		notifyObservers("S-au retras bani din contul "+this.getIdCont());
	}	
}
