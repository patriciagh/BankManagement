package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class NewAccount extends JFrame{
	private JLabel idPLabel=new JLabel("Id persoana");
	private JLabel idCLabel=new JLabel("Id cont");
	private JLabel label1=new JLabel();
	private JLabel label2=new JLabel();
	private JTextField idPersoana=new JTextField();
	private JTextField idCont=new JTextField();
	
	private JRadioButton spending = new JRadioButton("Spending");
	private JRadioButton saving = new JRadioButton("Saving");
	private ButtonGroup group = new ButtonGroup();
	private JButton salvare=new JButton("Adaugare");
	
	private int idPersInt=0;
	private int idContInt=0;
	Person persoana=new Person();
	Account cont;
	Bank banca = new Bank();
	
	public NewAccount()
	{
		try {
			banca.setMap(banca.citireBanca());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setLayout(null);	
		this.setSize(400,400);
		this.setTitle("Adaugare cont ");
		this.setLocationRelativeTo(null);
		idPLabel.setBounds(30, 0, 100, 30);
		idPersoana.setBounds(20, 30, 200, 30);
		
		idCLabel.setBounds(30, 60, 100, 30);
		idCont.setBounds(20, 90, 200, 30);
		
		label1.setBounds(20,210,200,30);
		label2.setBounds(20,260,200,30);
		
		spending.setBounds(20,120, 100,30);
		saving.setBounds(120,120, 100,30);
		salvare.setBounds(20, 170, 100, 30);
		
		group.add(spending);
		group.add(saving);
		
		salvare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	
				if(idPLabel.getText().equals("")==false && idCLabel.getText().equals("")==false)
				{
					idPersInt=Integer.parseInt(idPersoana.getText().trim());
					persoana=banca.cautarePersoana(idPersInt);
					label1.setText(persoana.afisarePersoana());
					int idVerificare = banca.verificareCont(Integer.parseInt(idCont.getText()));	
					if(getRadioSpending()==true)//	creareSpending();
					{
						creareSpending();
					}
					if(getRadioSaving()==true)	//	creareSaving();
					{
						creareSaving();
					}
					try {
						banca.scriereBanca();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					banca.afisareBanca();
					try {
						Gui.actualizareTabelConturi();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}			
		});	
		this.add(label2);
		this.add(saving);
		this.add(label1);
		this.add(spending);
		this.add(salvare);
		this.add(idPLabel);
		this.add(idCLabel);
		this.add(idPersoana);
		this.add(idCont);
		this.setVisible(true);
	}
	public boolean getRadioSpending()
	{			return spending.isSelected();	}
	public boolean getRadioSaving()
	{			return saving.isSelected();		}
	
	public void creareSpending()
	{
		cont = new SpendingAccount(Integer.parseInt(idCont.getText().trim()),0,persoana);
		banca.adaugareCont(persoana, cont);
		
	}
	public void creareSaving()
	{
		cont = new SavingAccount(Integer.parseInt(idCont.getText().trim()),0,persoana);
		banca.adaugareCont(persoana, cont);		
	}
	
}
