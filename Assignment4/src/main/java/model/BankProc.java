package model;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public interface BankProc {
	/*
	 @pre  : Verfic daca persoana nu exista deja si daca este nula
	 @post  : Verific daca a crescut size-ul dupa adaugare */
	public void adaugarePersoana(Person p);
	/*
	 @pre  : Verfic daca persoana  exista 
	 @post  : Verific daca a scazut size-ul dupa stergere */
	public void stergerePersoana(Person p);
	
	/*
	 @pre  : Verfic daca contul nu exista deja 
	 		 Verific daca exista persoana respectiva
	 @post  : Verific daca a crescut size-ul dupa adaugare */
	public void adaugareCont(Person p ,Account c);
	
	/*
	 @pre  : Verfic daca contul exista 
	 		 Verific daca exista persoana respectiva
	 @post  : Verific daca a scazut size-ul dupa stergere */
	public void stergereCont(Person p, Account c);
	
	/*
	 * @pre : verific daca hashmapul nu e gol inainte*/
	public void afisareBanca();	
	
	/*
	 * @pre : verificare daca exista persoana cu acest id
	 */
	public Person cautarePersoana(int id);
	/*
	 * @pre : verificare daca exista cont cu acest id*/
	public Account cautareCont(int id);
	
}
