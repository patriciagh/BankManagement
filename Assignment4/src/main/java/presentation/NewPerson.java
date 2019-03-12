package presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Bank;
import model.Person;
public class NewPerson extends JFrame{
	private JLabel idLabel=new JLabel("ID");
	private JLabel numeLabel=new JLabel("NUME");
	private JLabel prenumeLabel=new JLabel("PRENUME");
	private JLabel cnpLabel=new JLabel("CNP");
	
	private JTextField id=new JTextField();
	private JTextField nume=new JTextField();
	private JTextField prenume=new JTextField();
	private JTextField cnp=new JTextField();
	
	private JButton salvare=new JButton("Adaugare");
	
	private int idPersoana=0;
	private String numePersoana="";
	private String prenumePersoana="";
	private String cnpPersoana="";
	Bank banca = new Bank();
	public NewPerson() throws ClassNotFoundException, IOException
	{
		
		this.setLayout(null)	;	
		this.setSize(400,450);
		this.setTitle("Adaugare persoana ");
		this.setLocationRelativeTo(null);
		idLabel.setBounds(30, 0, 100, 30);
		id.setBounds(20, 30, 200, 30);
		numeLabel.setBounds(30, 60, 100, 30);
		nume.setBounds(20, 90, 200, 30);
		prenumeLabel.setBounds(30, 120, 100, 30);
		prenume.setBounds(20, 150, 200, 30);
		cnpLabel.setBounds(30,180,100,30);
		cnp.setBounds(20,210,200,30);
		salvare.setBounds(40, 260, 100, 30);
		
		salvare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(id.getText().equals("")==false && nume.getText().equals("")==false && prenume.getText().equals("")==false)
					{	
						
						try {
							banca.setMap(banca.citireBanca());
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						idPersoana=Integer.parseInt(id.getText());
						numePersoana=new String(nume.getText());
						prenumePersoana=new String(prenume.getText());
						cnpPersoana=new String(cnp.getText());
						Person persoanaNoua=new Person(idPersoana,numePersoana,prenumePersoana,cnpPersoana);
						banca.adaugarePersoana(persoanaNoua);
						banca.afisareBanca();
						try {
							banca.scriereBanca();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							Gui.actualizareTabelPersoane();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			}			
		});	
		this.add(cnp);
		this.add(cnpLabel);
		this.add(salvare);
		this.add(prenume);
		this.add(prenumeLabel);
		this.add(nume);
		this.add(numeLabel);
		this.add(idLabel);
		this.add(id);
		this.setVisible(true);
	}
	
}
