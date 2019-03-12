package start;

import org.junit.Test;
import junit.framework.TestCase;
import model.*;
public class Teste extends TestCase {
	Bank banca = new Bank();
	Person persoana1 = new Person(1,"Cristea","Luminita","123");
	Person persoana2 = new Person(2,"Dascalu","Codrut","124");
	Person persoana3 = new Person(3,"Botolan","Alexandru","125");
	Person persoana4 = new Person(4,"Bruchental","Andreea","126");
	
	Account spending11=new SpendingAccount(1,200,persoana1);
	Account spending21=new SpendingAccount(2,300,persoana2);
	Account spending31=new SpendingAccount(3,400,persoana3);
	
	Account saving12=new SpendingAccount(4,1100,persoana1);
	Account saving41=new SpendingAccount(5,1500,persoana4);
	
	@Test
	public void testAdaugarePersoana()
	{
		banca.adaugarePersoana(persoana1);
		banca.adaugarePersoana(persoana2);
		banca.adaugarePersoana(persoana3);
		banca.adaugarePersoana(persoana4);
	}
	@Test
	public void testAfisare()
	{
		testAdaugarePersoana();
		banca.afisareBanca();
	}
	
	@Test
	public void testAdaugareCont()
	{
		testAdaugarePersoana();
		
		banca.adaugareCont(persoana1,spending11);
		banca.adaugareCont(persoana1, saving12);
		
		banca.adaugareCont(persoana2, spending21);
		banca.adaugareCont(persoana3, spending31);
		banca.adaugareCont(persoana4, saving41);
	}
	
	
	@Test
	public void testStergerePersoana()
	{
		testAdaugarePersoana();
		banca.stergerePersoana(persoana4);
	}
	
	
	@Test
	public void testStergereCont()
	{
		testAdaugareCont();
		banca.stergereCont(persoana1, spending11);
	}
	
	
	@Test
	public void testAdaugare()
	{
		testAdaugareCont();
		banca.cautareCont(1).adaugare(300);// adaugare in contul 1 - spending
		banca.cautareCont(4).adaugare(1200); // adaugare in cont 4 - saving
	}
	
	@Test
	public void testRetragere()
	{
		testAdaugareCont();
		banca.cautareCont(2).retragere(50);// retragere din contul 1 - spending
		banca.cautareCont(5).adaugare(1500);//retragere din contul 5 - saving
	}
	
	
}
