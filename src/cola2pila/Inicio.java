package cola2pila;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import numeracionmaya.Pantalla;

public class Inicio extends JFrame implements ActionListener {

	Pila one = new Pila();
	Pila two = new Pila();
	JButton push, pop;
	JLabel label;
	String c = "";

	public static void main(String[] args) {
		new Pantalla();
	}

	public Inicio() {
		super("COLAS-PILAS  UMG");
		this.setLayout(null);
		super.setVisible(true);
		super.setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		push = new JButton("Push");
		push.setBounds(50, 50, 75, 22);
		push.addActionListener(this);

		pop = new JButton("Pop");
		pop.setBounds(50, 80, 75, 22);
		pop.addActionListener(this);

		label = new JLabel();
		label.setBounds(50, 120, 500, 22);

		this.add(pop);
		this.add(push);
		this.add(label);

	}

	public void mostrarLista() {
		String a = "";
		for (int i = 0; i < one.getTop(); i++) {
			a += one.listar()[i] + " ";
		}
		this.label.setText(a);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Se presiona el boton PUSH
		if (e.getSource() == this.push) {
			c = JOptionPane.showInputDialog("Ingresar Caracter: ");
			if (c != null) {
				one.push(c.charAt(0));
				mostrarLista();
			}
		}
		
		//Se presiona el boton POP
		if (e.getSource() == this.pop) {
			while (one.getTop() > 0) {
				two.push(one.pop());
			}
			char r = two.pop();
			while (two.getTop() > 0) {
				one.push(two.pop());
			}
			JOptionPane.showMessageDialog(this, r);
			mostrarLista();
		}
	}
}
