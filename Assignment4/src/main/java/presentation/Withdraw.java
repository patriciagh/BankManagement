package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Account;
import model.Bank;
import model.SavingAccount;
import model.SpendingAccount;

public class Withdraw extends JFrame {
	private JLabel idLabel = new JLabel("Introduceti id-ul contului din care doriti sa extrageti : ");
	private JLabel sumaLabel = new JLabel("Introduceti suma : ");
	private JLabel label = new JLabel("");
	
	private JTextField idCont = new JTextField();
	private JTextField sumaCont = new JTextField();	
	
	private JButton verificare=new JButton("Verificare ID");
	private JButton retragere=new JButton("Retragere");
	
	Bank banca=new Bank();
	Account cont=null;
	double suma=0;
	int idContInt=0;
	public Withdraw()
	{
		this.setLayout(null);	
		this.setSize(500,350);
		this.setTitle("Retragere suma de bani");
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
		 retragere.setBounds(250,100,200,30);
		 
		 verificare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(idCont.getText()!=null)
					{
						idContInt=Integer.parseInt(idCont.getText().trim());					
						cont=banca.cautareCont(idContInt);
						if(cont instanceof SavingAccount) 
							afisareDobanda();						
						else label.setText(cont.afisareCont());
						
					}else label.setText("Id-ul este null");
				}			
			});
		 retragere.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					suma=Double.parseDouble(sumaCont.getText().trim());			
					
					if(cont instanceof SpendingAccount)
					{	
						retragereSpending();						
					}
					else 
					{
						retragereSaving();				
					}
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
		 this.add(retragere);
		 this.add(verificare);
		this.add(label);
		this.add(idCont);
		this.add(idLabel);
		this.add(sumaCont);
		this.add(sumaLabel);
		this.setVisible(true);
		
	}
	public void retragereSpending()
	{
		cont.retragere(suma);
		label.setText("A fost retrasa suma de "+ suma +" din contul de Spending");		
	}
	public void afisareDobanda()
	{
		SavingAccount contSaving = (SavingAccount) banca.cautareCont(cont.getIdCont());
		contSaving.dobanda();
		label.setText(contSaving.getSumaCont()+"");
	}
	public void retragereSaving()
	{				
		
		cont.retragere(suma);
		label.setText("A fost retrasa suma de "+ suma +" din contul de Saving");
		
	}
}
