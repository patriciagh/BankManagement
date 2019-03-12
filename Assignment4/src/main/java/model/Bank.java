package model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class Bank implements BankProc , Serializable
{
	private static final long serialVersionUID = 1L;	
	private HashMap <Person, Set<Account>> map = new HashMap <Person, Set<Account>>();	
	private static final String fisierBanca="bank.ser";	
	
	public void setMap(HashMap<Person, Set<Account>> newMap) 
	{
		this.map = newMap;
	}
	public ArrayList<Person> getPersoane()
	{
		ArrayList<Person> persoane=new ArrayList<Person>();
		for(Entry<Person,Set<Account>> inregistrare : map.entrySet())
		{
			persoane.add(inregistrare.getKey());
		}
		return persoane;		
	}
	public void afisarePersoane(ArrayList<Person> persoane)
	{
		assert !size0() : "Hashmap-ul este gol";
		for(Person p : persoane)
		{
			System.out.println(p.getId()+"   "+p.getNume()+"   "+p.getPrenume()+"   "+p.getCnp());
		}
	}	
	public void afisareBanca()
	{
		assert !size0() : "Hashmap-ul este gol";
		
		System.out.println("Afisare Banca : ");
		for(Entry<Person,Set<Account>> inregistrare : map.entrySet())
		{
			System.out.println("Conturi ale : "+inregistrare.getKey().getId()+"  "+inregistrare.getKey().getNume()+" "+inregistrare.getKey().getPrenume()+ "  " + inregistrare.getKey().getCnp());
			
			for(Account cont : inregistrare.getValue())
			{
				System.out.println("      Id cont : "+cont.getIdCont()+"     Suma cont : "+cont.getSumaCont());
			}
			System.out.println();
		}
	}
	public int verificarePersoana(int idPersoana)
	{
		// Daca persoana nu exista deja in map , ok=1 , totul e ok
		// Daca o gasesc in map , atunci ok=0		
		int ok=1;
		for(Person persoana :  map.keySet())
		{
			if(persoana.getId()==idPersoana)
				ok=0;
		}
		return ok;	
	}	
	public void adaugarePersoana(Person p) 
	{		
		// preconditie 
		assert p!=null : "Persoana introdusa este nula !";
		assert !map.containsKey(p) : "Persoana respectiva a fost introdusa deja ! ";
		// verificare sa nu fie alta persoana cu acest ID		
		int ok=verificarePersoana(p.getId());		
		assert ok==1 : "Exista deja o persoana cu acest ID !";		
		int sizeVechi=map.keySet().size();
		
		// momentan conturile sunt goale		
		Set<Account> conturiPersoane= new HashSet<Account>();
		map.put(p,conturiPersoane);
		System.out.println("A fost adaugata persoana "+p.getId());
		int sizeNou=map.keySet().size();
		
		//postconditie
		assert sizeVechi + 1 == sizeNou : "Persoana nu a fost adaugata !";
	}	
	public Person cautarePersoana(int id)
	{
		Person p=new Person();
		int ok=verificarePersoana(id);
		assert ok==0 : "Nu exista o persoana cu acest ID !";
		for(Entry<Person,Set<Account>> inregistrare : map.entrySet())
		{
			if(inregistrare.getKey().getId()==id)
			{
				p=inregistrare.getKey();
			}
		}
		return p;
	}
	public Account cautareCont(int id)
	{
		Account c=null;
		int ok=verificareCont(id);
		assert ok==0 : "Nu exista cont cu acest ID !";
		for(Entry<Person,Set<Account>> e : map.entrySet())
		{
			for(Account cont : e.getValue())
			{
				if(cont.getIdCont()==id) c=cont;
			}
		}
		return c;
	}	
	public void adaugareCont(Person p , Account c) 
	{
		//pre
		assert map.containsKey(p) : "Persoana respectiva nu a fost introdusa  ! ";
		int ok2=verificareCont(c.getIdCont());		
		assert ok2==1 : "Exista deja un cont cu acest ID !";
		int sizeVechi=map.get(p).size();
		
		//adaugare cont
		map.get(p).add(c);		
		System.out.println("A fost adaugat contul "+c.getIdCont());
		c.addObserver(p);
		int sizeNou=map.get(p).size();
		
		//post
		assert sizeVechi + 1 == sizeNou : "Contul nu a fost adaugat !";			
	}
	
	//METODELE DE SERIALIZARE
	public void scriereBanca() throws IOException
	{		
		FileOutputStream fileOut=new FileOutputStream(fisierBanca);
		ObjectOutputStream out= new ObjectOutputStream(fileOut);
		out.writeObject(map);
		out.close();
		fileOut.close();
	}	
	public HashMap<Person,Set<Account>> citireBanca() throws IOException, ClassNotFoundException
	{
		
		 FileInputStream fileInput = new FileInputStream(fisierBanca);		
		 ObjectInputStream in = new ObjectInputStream(fileInput);
		 HashMap<Person, Set<Account>> hashMapCitit= (HashMap<Person, Set<Account>>) in.readObject();
		 in.close();
		 fileInput.close();
		 return hashMapCitit;		
	}	
	public ArrayList<Account> getConturi() 
	{
		
		ArrayList<Account> conturi=new ArrayList<Account>();
		for(Entry<Person,Set<Account>> e : map.entrySet())
		{
			for(Account ac : e.getValue())
			{
				conturi.add(ac);
			}
		}
		return conturi;
	}
	public void stergerePersoana(Person p) 
	{
		//@pre
		assert map.containsKey(p) : "Persoana nu exista in banca !";
		int sizeVechi=map.keySet().size();
		Person stearsa= this.cautarePersoana(p.getId());
		map.remove(stearsa);
		System.out.println(p.afisarePersoana()+"  a fost stearsa");
		//@post
		assert sizeVechi-1 == map.keySet().size() : "Persoana nu a fost stearsa";
	}	
	public int verificareCont(int idCont)
	{	int ok=1;
		for(Entry<Person,Set<Account>> e : map.entrySet())
		{
			for(Account cont : e.getValue())
			{
				if(cont.getIdCont()==idCont) ok=0;
			}
		}
		return ok;
	}	
	public void afisareConturi()
	{
		for(Entry<Person,Set<Account>> e : map.entrySet())
		{
			for(Account cont : e.getValue())
			{
				System.out.println(cont.afisareCont());
			}
		}
	}	
	public void stergereCont(Person p,  Account c) 
	{
		//pre
		assert map.containsKey(p) : "Persoana nu exista !";
		
		int sizeVechi=map.get(p).size();
		//Account sters= this.cautareCont(c.getIdCont());
		map.get(p).remove(c);
		System.out.println(c.afisareCont()+"  a fost sters");
		
		//post
		int sizeNou=map.get(p).size();		
		assert sizeVechi - 1 == sizeNou : "Contul nu a fost sters!";
	}	
	
	public boolean size0()
	{
		if(map.size()==0) return true;
		else return false;
	}
	
}
