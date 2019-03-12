package model;

import java.io.Serializable;
import java.util.Observable;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public abstract class Account extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idCont;
	private double sumaCont;
	private Person pers;
	public Account( int id , double suma , Person p)
	{
		this.idCont=id;
		this.sumaCont=suma;
		this.pers=p;
	}
	public double getSumaCont()
	{
		return this.sumaCont;
	}
	public void setSumaCont(double suma)
	{
		this.sumaCont=suma;		
	}
	public int getIdCont()
	{
		return this.idCont;
	}
	public Person getPersoana()
	{
		return this.pers;
	}
	public int getIdPersoana()
	{
		return this.pers.getId();
	}	
	public abstract void adaugare(double suma);// Adaugare bani in cont
	public abstract void retragere(double suma);// Retragere bani din cont
	public String afisareCont() 
	{
		return "Cont : "+this.idCont+" "+this.sumaCont+" "+this.getPersoana().getNume();
	}
}
