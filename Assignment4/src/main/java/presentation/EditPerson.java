package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

//GHITUN PATRICIA ROXANA - 30227
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Bank;
import model.Person;

public class EditPerson extends JFrame{
	private JLabel idCustomer=new JLabel("Introduceti id-ul persoanei : ");
	private JTextField id=new JTextField();	
	private JRadioButton nume = new JRadioButton("Nume");
	private JRadioButton prenume = new JRadioButton("Prenume");	
	private JLabel label=new JLabel("");
	private JLabel actualizat=new JLabel();
	private JButton salvare=new JButton("Salvare");
	private JButton cautare=new JButton("Cautare");
	private JTextField atribut=new JTextField();
	private ButtonGroup group = new ButtonGroup();
	private int idText=0;
	Bank banca = new Bank();
	Person persoana=new Person();
	public EditPerson() 
	{		
		this.setLayout(null);	
		this.setSize(450,450);
		this.setTitle("Editare persoana");
		this.setLocationRelativeTo(null);
		try {
			banca.setMap(banca.citireBanca());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 idCustomer.setBounds(20, 0, 250, 30);
		 id.setBounds(20, 30, 200, 30);//tf
		 label.setBounds(20, 100, 300, 30);
		 cautare.setBounds(20, 70, 100, 30);
		 nume.setBounds(20, 130, 100,30);
		 prenume.setBounds(20, 160, 100,30);
		 atribut.setBounds(20,230,200,30);//tf
		 salvare.setBounds(20, 280, 100,30);
		 actualizat.setBounds(20,310,300,30);
		 group.add(nume);
		 group.add(prenume);
		 cautare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					idText=Integer.parseInt(id.getText());
					persoana=banca.cautarePersoana(idText);						
					label.setText(persoana.afisarePersoana());
				}			
			});
		 salvare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(getRadioNume()==true)		actualizareNume();					
					if(getRadioPrenume()==true)		actualizarePrenume();					
				}			
			});
		 this.add(actualizat);
		 this.add(atribut);
		 this.add(salvare);
		 this.add(cautare);
		 this.add(label);
		 this.add(nume);
		 this.add(prenume);
		 this.add(id);
		 this.add(idCustomer);
		this.setVisible(true);
	}
	public boolean getRadioNume()
	{			return nume.isSelected();		}
	public boolean getRadioPrenume()
	{			return prenume.isSelected();		}
	

	public void actualizareNume()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			String numeNou=atribut.getText().trim();
			persoana.setNume(numeNou);
			try {
				banca.scriereBanca();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Gui.actualizareTabelPersoane();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			actualizat.setText(persoana.afisarePersoana()+" a fost actualizata.");
			System.out.println("A fost actualizat numele persoanei "+persoana.getId());
		}else actualizat.setText("Numele introdus este incorect");
	}
	
	public void actualizarePrenume()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			String prenumeNou=atribut.getText().trim();
			persoana.setPrenume(prenumeNou);
			try {
				banca.scriereBanca();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Gui.actualizareTabelPersoane();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			actualizat.setText(persoana.afisarePersoana()+" a fost actualizata.");
			System.out.println("A fost actualizat prenumele persoanei "+persoana.getId());
		}else actualizat.setText("Prenumele introdusa este incorecta");
	}
	
}
