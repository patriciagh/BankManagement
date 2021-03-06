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
import model.Person;
public class DeleteAccount extends JFrame {
	
	private JLabel idLabel=new JLabel("ID : ");	
	private JLabel label=new JLabel("");
	private JTextField id=new JTextField();	
	private JButton stergere=new JButton("Stergere");
	Bank banca = new Bank();
	Account cont;
	int idCont;
	public DeleteAccount() 
	{		
		
		try {
			banca.setMap(banca.citireBanca());
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		this.setLayout(null);	
		this.setSize(400,250);
		this.setTitle("Stergere cont ");
		this.setLocationRelativeTo(null);
		idLabel.setBounds(20, 10, 250, 30);
		id.setBounds(60,10,150,30);
		label.setBounds(20,90,200,30);
		stergere.setBounds(20,60,200,30);
		stergere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				banca.afisareBanca();
				if(id.getText()!=null)
				{
					idCont=Integer.parseInt(id.getText().trim());
					cont=banca.cautareCont(idCont);
					banca.stergereCont(cont.getPersoana(),cont);
					label.setText(cont.afisareCont());
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
				}else label.setText("Id-ul introdus este nul");
				
			}			
		});
		this.add(stergere);
		this.add(id);
		this.add(idLabel);
		this.add(label);
		this.setVisible(true);

	}
	
	}
