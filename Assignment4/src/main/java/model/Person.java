package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class Person implements Serializable , Observer
{
	private static final long serialVersionUID = 8942736983789987232L;		
	private int id;
	private String nume ;
	private String prenume;
	private String cnp;
	public Person(int i , String n , String p, String c)
	{
		this.id=i;
		this.nume=n;
		this.prenume=p;
		this.cnp=c;
	}
	public Person() {	}
	public String getPrenume() 
	{
		return prenume;
	}
	public void setPrenume(String p) 
	{
		this.prenume = p;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int i) 
	{
		this.id = i;
	}
	public String getNume() 
	{
		return nume;
	}
	public void setNume(String n)
	{
		this.nume = n;
	}		
	public String getCnp() 
	{
		return cnp;
	}
	public void setCnp(String cnp) 
	{
		this.cnp = cnp;
	}	
	public String afisarePersoana()
	{
		return "Persoana "+this.id+"	 "+this.nume+" 	"+this.prenume+" 	"+this.cnp;
	}
	public void update(Observable cont, Object mesaj) 
	{
		System.out.println("Update : "+(String)mesaj);
	}
	@Override
	public int hashCode()
	{
		/*
		int nrPrim=13;
		if(cnp==null) return 0;
		else return nrPrim*cnp.hashCode();*/
		return this.getId();
	}
	@Override
	public boolean equals(Object object)
	{
		if(this == object) return true;
		else
		{
			if(object==null) return false;
			else
			{
				Person p= (Person)object;
				if((p.getId() == this.getId()) 
						&& (p.getNume() ==  this.getNume())
						&& (p.getPrenume() == this.getPrenume())
						&& (p.getCnp() == this.getCnp())) return true;
				else return false;
			}
		}
	}
	
}
	
