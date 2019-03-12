package presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.*;
public class Deposit extends JFrame {
	private JLabel idLabel = new JLabel("Introduceti id-ul contului in care doriti sa depuneti suma: ");
	private JLabel sumaLabel = new JLabel("Introduceti suma : ");
	private JLabel label = new JLabel("");
	
	private JTextField idCont = new JTextField();
	private JTextField sumaCont = new JTextField();	
	
	private JButton verificare=new JButton("Verificare ID");
	private JButton adaugare=new JButton("Adaugare");
	
	Bank banca=new Bank();
	Account cont=null;
	double suma=0;
	int idContInt=0;
	public Deposit()
	{
		this.setLayout(null);	
		this.setSize(500,350);
		this.setTitle("Adaugare suma de bani");
		this.setLocationRelativeTo(null);
		try {
			banca.setMap(banca.citireBanca());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		idLabel.setBounds(20, 0, 350, 30);
		idCont.setBounds(20, 30, 200, 30);//tf
		 
		 sumaCont.setBounds(20,100 , 200,30);
		 sumaLabel.setBounds(20, 70, 250, 30);
		 
		 verificare.setBounds(250,30,200,30);
		 label.setBounds(20, 140, 350, 30);
		 adaugare.setBounds(250,100,200,30);
		 
		 verificare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(idCont.getText()!=null)
					{
						idContInt=Integer.parseInt(idCont.getText().trim());					
						cont=banca.cautareCont(idContInt);
						label.setText(cont.afisareCont());
					}else label.setText("Id-ul este null");
				}			
			});
		 adaugare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					suma=Double.parseDouble(sumaCont.getText().trim());
					label.setText(suma+"");
					
					
					if(cont instanceof SpendingAccount)
						adaugareSpending();
					else adaugareSaving();
					
					
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
		 this.add(adaugare);
		 this.add(verificare);
		this.add(label);
		this.add(idCont);
		this.add(idLabel);
		this.add(sumaCont);
		this.add(sumaLabel);
		this.setVisible(true);
		
	}
	public void adaugareSpending()
	{
		cont.adaugare(suma);
		label.setText("A fost adaugata suma"+ suma +" in contul de Spending");
		
	}
	public void adaugareSaving()
	{
		cont.adaugare(suma);
		label.setText("A fost adaugata suma"+ suma +" in contul de Saving");
		
	}
}
