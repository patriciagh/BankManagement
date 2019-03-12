package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bank;
import model.Person;
import model.SpendingAccount;

public class Gui extends JFrame{
	private JLabel persoaneLabel=new JLabel("Persoane");
	private JLabel conturiLabel=new JLabel("Conturi");
	private JButton adaugarePersoana = new JButton("Adaugare");
	private JButton stergerePersoana = new JButton("Stergere");
	private JButton adaugareCont= new JButton("Adaugare");	
	private JButton stergereCont= new JButton("Stergere");
	private JButton editarePersoana= new JButton("Editare");	
	private JButton editareCont= new JButton("Editare");
	private JButton adaugareBani= new JButton("Adaugare bani");	
	private JButton retragereBani= new JButton("Retragere bani");
	private JScrollPane scrollPersoane;
	private static JTable tabelPersoane;
	private JScrollPane scrollConturi;
	private static JTable tabelConturi;
	private JLabel date=new JLabel("");
	static Bank banca = new Bank();
	
	public Gui() throws ClassNotFoundException, IOException
	{
		this.setTitle("Bank TP");
		this.setSize(800,700);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		date.setBounds(440,280,300,30);
		
		tabelPersoane=getTabelPersoane();		
	    scrollPersoane = new JScrollPane(tabelPersoane);
	    scrollPersoane.setBounds(10,10,300,300);
	    tabelConturi=getTabelConturi();
	    scrollConturi=new JScrollPane(tabelConturi);
	    scrollConturi.setBounds(10,320, 760,300);
	    
	    persoaneLabel.setBounds(370 , 10 , 100 , 30);
	    conturiLabel.setBounds(620 , 10 , 100 , 30);
	    
	    adaugarePersoana.setBounds(350 ,50,100,30);
	    stergerePersoana.setBounds(350 ,100,100,30);
	    editarePersoana.setBounds(350,150,100,30);
	    
	    adaugareCont.setBounds(600 ,50,100,30);
	    stergereCont.setBounds(600 ,100,100,30);
	    editareCont.setBounds(600,150,100, 30);
	    
	    adaugareBani.setBounds(460,200,150,30);
	    retragereBani.setBounds(460,250,150,30);
	    
	    adaugarePersoana.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	try {
				NewPerson adaugarePersoanaNoua = new NewPerson();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
					System.out.println("Adaugare persoana ");
			}			
		});
	    
	    adaugareCont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	NewAccount adaugareContNou = new NewAccount();
					System.out.println("Adaugare cont ");
			}			
		});
	    
	    stergerePersoana.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	DeletePerson stergere = new DeletePerson();
					System.out.println("Stergere persoana ");
			}			
		});
	    
	    stergereCont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	DeleteAccount stergere = new DeleteAccount();
					System.out.println("Stergere cont ");
			}			
		});
	  
	    editarePersoana.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	EditPerson editare = new EditPerson();
					System.out.println("Editare persoana ");
			}			
		});
	    
	    editareCont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	EditAccount editare = new EditAccount();
					System.out.println("Editare cont ");
			}			
		});
	    
	    adaugareBani.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	
				Deposit adaugare = new Deposit();
					System.out.println("Adaugare bani ");
			}			
		});
	   
	    retragereBani.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	Withdraw retragere = new Withdraw();
					System.out.println("Retragere bani ");
			}			
		});
	    
	    tabelConturi.addMouseListener(new MouseAdapter() 
	    {	       
	        public void mouseClicked(MouseEvent event) 
	        {
	        	int r = tabelConturi.rowAtPoint(event.getPoint());
	        	int c = tabelConturi.columnAtPoint(event.getPoint());
	        	
	        	if(c==0)
	        	{	        		
	        		int idCont=Integer.parseInt(tabelConturi.getModel().getValueAt(r,c).toString());
	        		date.setText("0 : "+banca.cautareCont(idCont).afisareCont());
	        	}
	        	if(c==1)
	        	{
	        		int idCont=Integer.parseInt(tabelConturi.getModel().getValueAt(r,c-1).toString());
	        		date.setText("1 : "+banca.cautareCont(idCont).afisareCont());
	        	}
	        	if(c==2)
	        	{
	        		int idPers=Integer.parseInt(tabelConturi.getModel().getValueAt(r,c).toString());
	        		date.setText("2 : "+banca.cautarePersoana(idPers).afisarePersoana());
	        	}
	        	if(c==3)
	        	{
	        		int idPers=Integer.parseInt(tabelConturi.getModel().getValueAt(r,c-1).toString());
	        		date.setText("3 : "+banca.cautarePersoana(idPers).afisarePersoana());
	        	}
	        }
	    });
	    this.add(date);
	    this.add(adaugarePersoana);
	    this.add(adaugareCont);
	    this.add(stergereCont);
	    this.add(stergerePersoana);
	    this.add(editareCont);
	    this.add(editarePersoana);
	    this.add(adaugareBani);
	    this.add(retragereBani);
	    this.add(persoaneLabel);
	    this.add(conturiLabel);
	    this.add(scrollPersoane);
	    this.add(scrollConturi);
		this.setVisible(true);
	}
	
	public  JTable getTabelPersoane() throws ClassNotFoundException, IOException
	{
		banca.setMap(banca.citireBanca());
		JTable tabel=new JTable();
		ArrayList<Person> persoane=banca.getPersoane();
		String[] coloane=getAtribute(new Person());			
		Object []rows;
		DefaultTableModel modelPersoane = new DefaultTableModel(coloane,0);
		for(Person p : persoane)
		{
			rows=new Object[]{
					p.getId(),
					p.getNume(),
					p.getPrenume(),
					p.getCnp()
					};
			modelPersoane.addRow(rows);
		}
		tabel.setModel(modelPersoane);	
		return tabel;
	}
	
	public JTable getTabelConturi() throws ClassNotFoundException, IOException
	{
		
		banca.setMap(banca.citireBanca());
		JTable tabel=new JTable();
		ArrayList<Account> conturi=banca.getConturi();
		String[] coloane= {"id","suma","idPersoana","numePersoana","Tip"};			
		Object []rows;
		DefaultTableModel modelConturi = new DefaultTableModel(coloane,0);
		String tip="";
		for(Account c : conturi)
		{
			if(c instanceof SpendingAccount) tip = "Spending";
			else tip = "Saving";
			rows=new Object[]{
					c.getIdCont(),
					c.getSumaCont(),
					c.getPersoana().getId(),
					c.getPersoana().getNume(),
					tip
					};
			modelConturi.addRow(rows);
		}
		tabel.setModel(modelConturi);	
		return tabel;
	}
	
	
	public static String[] getAtribute(Object obj)
	{
		String []col= {"id","nume","prenume","cnp"};
		return col;	
	}
	
	
	public static  void actualizareTabelPersoane() throws ClassNotFoundException, IOException
	{
		
		DefaultTableModel modelTabelPersoane = (DefaultTableModel) tabelPersoane.getModel();
		modelTabelPersoane.setRowCount(0);
		tabelPersoane.setModel(modelTabelPersoane);
		banca.setMap(banca.citireBanca());
		ArrayList<Person> persoane=banca.getPersoane();
		String[] coloane=getAtribute(new Person());			
		Object []rows;
		DefaultTableModel modelPersoane = new DefaultTableModel(coloane,0);
		for(Person p : persoane)
		{
			rows=new Object[]{
					p.getId(),
					p.getNume(),
					p.getPrenume(),
					p.getCnp()
					};
			modelPersoane.addRow(rows);
		}
		tabelPersoane.setModel(modelPersoane);
		System.out.println("Tabelul de persoane a fost actualizat !");
	}
	
	public static  void actualizareTabelConturi() throws ClassNotFoundException, IOException
	{		
		DefaultTableModel modelTabelConturi = (DefaultTableModel) tabelConturi.getModel();
		modelTabelConturi.setRowCount(0);
		tabelConturi.setModel(modelTabelConturi);
		banca.setMap(banca.citireBanca());
		ArrayList<Account> conturi=banca.getConturi();
		String[] coloane= {"id","suma","idPersoana","numePersoana","Tip"};			
		Object []rows;
		DefaultTableModel modelConturi = new DefaultTableModel(coloane,0);
		String tip="";
		for(Account c : conturi)
		{
			if(c instanceof SpendingAccount) tip = "Spending";
			else tip = "Saving";
			rows=new Object[]{
					c.getIdCont(),
					c.getSumaCont(),
					c.getPersoana().getId(),
					c.getPersoana().getNume(),
					tip
					};
			modelConturi.addRow(rows);
		}
		tabelConturi.setModel(modelConturi);	
		System.out.println("Tabelul de conturi a fost actualizat !");		
	}
}
