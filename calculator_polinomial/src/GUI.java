package tema1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
class GUI extends JFrame{

	public GUI() {
		
		//definirea frame-ului mare care cuprinde toate componentele
		JFrame frame = new JFrame("Calculator polinoame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 225);
		
		
		JPanel introducerePolinoame = new JPanel(); //panelul in care vor fi incluse introducerea polinoamelor si optiunile de calcul pentru fiecare dintre acestea
		
		JPanel calculPolinom1 = new JPanel(); //panelul in care sunt incluse introducerea primului polinom si optiunile de calcul pentru acesta
		JPanel introducerePolinom1 = new JPanel(); //panelul in care este inclusa introducerea primului polinom
		JPanel optiuniCalculPolinom1 = new JPanel(); //panelul in care sunt incluse optiunile de calcul pentru primul polinom
		
		JPanel calculPolinom2 = new JPanel(); //panelul in care sunt incluse introducerea celui de-al doilea polinom polinom si optiunile de calcul pentru acesta
		JPanel introducerePolinom2 = new JPanel(); //panelul in care este inclusa introducerea celui de-al doilea polinom
		JPanel optiuniCalculPolinom2 = new JPanel(); //panelul in care sunt incluse optiunile de calcul pentru al doilea polinom
		
		JPanel optiuniCalcul = new JPanel(); //panelul in care sunt incluse optiunile de calcul intre cele doua polinoame
		
		JPanel afisorRezultat = new JPanel(); //panelul in care este afisat rezultatul actiunii cerute
		
		
		/*********************introducerePolinoame*********************/
		
		/*******calculPolinom1*******/
		
		/*introducerePolinom1*/
		
		JLabel polinom1 = new JLabel("Polinom1:"); 
		JLabel polinom10 = new JLabel("=0");
		JTextField inPolinom1 = new JTextField(20);
		JButton inPolinom1OK = new JButton("OK");
		
		introducerePolinom1.add(polinom1);
		introducerePolinom1.add(inPolinom1);
		introducerePolinom1.add(polinom10);
		introducerePolinom1.add(inPolinom1OK);
		
		introducerePolinom1.setLayout(new FlowLayout());
		
		/*optiuniCalculPolinom1*/
		
		JPanel valoareInX1 = new JPanel();
		JLabel x1 = new JLabel("x=");
		JTextField valoareX1 = new JTextField(5);
		JButton p1Button = new JButton("OK");
		
		valoareInX1.add(x1);
		valoareInX1.add(valoareX1);
		valoareInX1.add(p1Button);
		
		JButton derivarePolinom1 = new JButton("Derivare");
		JButton integrarePolinom1 = new JButton("Integrare");
		
		optiuniCalculPolinom1.add(valoareInX1);
		optiuniCalculPolinom1.add(derivarePolinom1);
		optiuniCalculPolinom1.add(integrarePolinom1);
		optiuniCalculPolinom1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
		
		
		calculPolinom1.add(introducerePolinom1);
		calculPolinom1.add(optiuniCalculPolinom1);
		calculPolinom1.setLayout(new BoxLayout(calculPolinom1, BoxLayout.Y_AXIS));
		calculPolinom1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		/*******calculPolinom2*******/
		
		/*introducerePolinom2*/
		
		JLabel polinom2 = new JLabel("Polinom2:"); 
		JLabel polinom20 = new JLabel("=0");
		JTextField inPolinom2 = new JTextField(20);
		JButton inPolinom2OK = new JButton("OK");
		
		introducerePolinom2.add(polinom2);
		introducerePolinom2.add(inPolinom2);
		introducerePolinom2.add(polinom20);
		introducerePolinom2.add(inPolinom2OK);
		
		introducerePolinom2.setLayout(new FlowLayout());
		
		/*optiuniCalculPolinom1*/
		
		JPanel valoareInX2 = new JPanel();
		JLabel x2 = new JLabel("x=");
		JTextField valoareX2 = new JTextField(5);
		JButton p2Button = new JButton("OK");
		
		valoareInX2.add(x2);
		valoareInX2.add(valoareX2);
		valoareInX2.add(p2Button);
		
		JButton derivarePolinom2 = new JButton("Derivare");
		JButton integrarePolinom2 = new JButton("Integrare");
		
		optiuniCalculPolinom2.add(valoareInX2);
		optiuniCalculPolinom2.add(derivarePolinom2);
		optiuniCalculPolinom2.add(integrarePolinom2);
		optiuniCalculPolinom2.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
			
		
		calculPolinom2.add(introducerePolinom2);
		calculPolinom2.add(optiuniCalculPolinom2);
		calculPolinom2.setLayout(new BoxLayout(calculPolinom2, BoxLayout.Y_AXIS));
		calculPolinom2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				
		
		introducerePolinoame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		introducerePolinoame.add(calculPolinom1);
		introducerePolinoame.add(calculPolinom2);
		
		
		/*********************optiuniCalcul*********************/
		
		JButton adunare = new JButton("+");
		JButton scadere = new JButton("-");
		JButton inmultire = new JButton("*");
		JButton raport = new JButton("/");
		
		
		optiuniCalcul.add(adunare);
		optiuniCalcul.add(scadere);
		optiuniCalcul.add(inmultire);
		optiuniCalcul.add(raport);
		
		optiuniCalcul.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		
		
		/*********************afisorRezultat*********************/
		
		JLabel result = new JLabel("Rezultat:");
		JTextField resultDisp = new JTextField(40);
		resultDisp.setEditable(false);
		resultDisp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		resultDisp.setBackground(Color.WHITE);
		
		afisorRezultat.add(result);
		afisorRezultat.add(resultDisp);
		afisorRezultat.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		
		
		/*********************Definire evenimente butoane*********************/
		
		derivarePolinom1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom1.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.derivare(p).toString());
			}
			
		});
		
		derivarePolinom2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom2.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.derivare(p).toString());
			}
			
		});

		integrarePolinom1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom1.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.integrare(p).toString());
			}
			
		});
		
		integrarePolinom2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom2.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.integrare(p).toString());
			}
			
		});
		
		adunare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom polinom1 = new Polinom(inPolinom1.getText());
				Polinom polinom2 = new Polinom(inPolinom2.getText());
				
				Operatii op = new Operatii();
				
				resultDisp.setText((op.adunare(polinom1, polinom2)).toString());
			}
			
		});
		
		scadere.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom polinom1 = new Polinom(inPolinom1.getText());
				Polinom polinom2 = new Polinom(inPolinom2.getText());
				
				Operatii op = new Operatii();
				
				resultDisp.setText((op.scadere(polinom1, polinom2)).toString());
			}
			
		});
		
		inmultire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom polinom1 = new Polinom(inPolinom1.getText());
				Polinom polinom2 = new Polinom(inPolinom2.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.inmultire(polinom1, polinom2).toString());
			}
			
		});
		
		raport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom polinom1 = new Polinom(inPolinom1.getText());
				Polinom polinom2 = new Polinom(inPolinom2.getText());
				Operatii op = new Operatii();
				
				resultDisp.setText(op.impartire(polinom1, polinom2));
			}
			
		});
		
		p1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom1.getText());
				Operatii op = new Operatii();
				DecimalFormat format = new DecimalFormat("0.#");
				
				resultDisp.setText("" + format.format(op.valoareInX(p, Float.parseFloat(valoareX1.getText()))));
			}
			
		});
		
		p2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom2.getText());
				Operatii op = new Operatii();
				DecimalFormat format = new DecimalFormat("0.#");
				
				resultDisp.setText("" + format.format(op.valoareInX(p, Float.parseFloat(valoareX2.getText()))));
			}
			
		});
		
		inPolinom2OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom2.getText());
				
				inPolinom2.setText(p.toString());
			}
		});
		
		inPolinom1OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom p = new Polinom(inPolinom1.getText());
				
				inPolinom1.setText(p.toString());
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(introducerePolinoame);
		panel.add(optiuniCalcul);
		panel.add(afisorRezultat);
		
		
		frame.setContentPane(panel);
		frame.setVisible(true); 
	}
}
