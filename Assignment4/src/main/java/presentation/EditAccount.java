package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

//GHITUN PATRICIA ROXANA - 30227
import javax.swing.*;
import model.*;

public class EditAccount extends JFrame{
	// EDITARE SUMA CONT
	private JLabel idCont=new JLabel("Introduceti id-ul contului : ");
	private JLabel sumaLabel=new JLabel("Introduceti suma noua : ");
	
	private JTextField id=new JTextField();
	private JTextField suma=new JTextField();
	private JLabel label=new JLabel("");
	private JLabel actualizat=new JLabel();
	private JButton salvare=new JButton("Salvare");
	private JButton cautare=new JButton("Cautare");
	Bank banca = new Bank();
	Account cont=null;
	int idContInt=0;
	double sumaNoua=0;
	public EditAccount() 
	{		
		this.setLayout(null);	
		this.setSize(450,350);
		this.setTitle("Editare cont ");
		this.setLocationRelativeTo(null);
		try {
			banca.setMap(banca.citireBanca());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 idCont.setBounds(20, 0, 250, 30);
		 id.setBounds(20, 30, 200, 30);//tf
		 
		 suma.setBounds(20,100 , 200,30);
		 sumaLabel.setBounds(20, 70, 250, 30);
		
		 label.setBounds(20, 130, 300, 30);
		 cautare.setBounds(20, 170, 100, 30);		
		 salvare.setBounds(20, 210, 100,30);
		 actualizat.setBounds(20,225,300,30);
		 cautare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(id.getText()!=null)
					{
						idContInt=Integer.parseInt(id.getText().trim());					
						cont=banca.cautareCont(idContInt);
						label.setText(cont.afisareCont());
					}else label.setText("Id-ul este null");
				}			
			});
		 
		 salvare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					sumaNoua=Double.parseDouble(suma.getText().trim());
					if(cont instanceof SpendingAccount)
						cont.setSumaCont(sumaNoua);
					else label.setText("Nu se poate edita suma din SavingAccount!");
					try {
						banca.scriereBanca();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Gui.actualizareTabelConturi();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}			
			}); 
		 this.add(sumaLabel);
		 this.add(actualizat);
		 this.add(salvare);
		 this.add(cautare);
		 this.add(label);
		 this.add(id);
		 this.add(idCont);
		 this.add(suma);
		this.setVisible(true);
	}
	
}
