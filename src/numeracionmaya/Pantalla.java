package numeracionmaya;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pantalla extends JFrame implements ActionListener, KeyListener {

	PilaMaya one = new PilaMaya();
	PilaMaya two = new PilaMaya();
	JButton push, botonResultado;
	JTextArea label, resultado;
	JTextField campoTexto;
	String c = "";

	public static void main(String[] args) {
		new Pantalla();
	}

	public Pantalla() {
		super("Numeracion Maya  UMG");
		this.setLayout(null);
		super.setVisible(true);
		super.setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		push = new JButton("Push");
		push.setBounds(50, 50, 75, 22);
		push.addActionListener(this);

		campoTexto = new JTextField("");
		campoTexto.setBounds(150, 50, 75, 22);
		campoTexto.addActionListener(this);
		campoTexto.addKeyListener(this);

		botonResultado = new JButton("Resultado");
		botonResultado.setBounds(50, 80, 100, 22);
		botonResultado.addActionListener(this);

		label = new JTextArea();
		label.setBounds(50, 120, 100, 300);
		label.disable();
		label.setFont(new Font("Arial", Font.BOLD, 30));

		resultado = new JTextArea();
		resultado.setBounds(180, 120, 400, 300);
		resultado.disable();
		resultado.setFont(new Font("Arial", Font.BOLD, 30));

		this.add(push);
		this.add(botonResultado);
		this.add(label);
		this.add(resultado);
		this.add(campoTexto);
	}

	public void mostrarLista() {
		String a = "";
		for (int i = 0; i < one.getTop(); i++) {
			a += one.listar()[i] + "\n";
		}
		this.label.setText(a);
		this.campoTexto.setText("");
	}

	public void resultado() {
		String a = "";

		while (one.getTop() > 0) {
			two.push(one.pop());
		}
		
		int cont = 0;
		while (two.getTop() > 0) {
			String c = two.pop();
			one.push(c);
			a += one.toDecimal(cont++,c) + "\n";
		}
		this.resultado.setText(a);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.push) {
			if (campoTexto.getText() != null) {
				one.push(campoTexto.getText().trim());
				mostrarLista();
			}
		} else if (e.getSource() == botonResultado) {
			resultado();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (PilaMaya.LINEA != e.getKeyChar() && PilaMaya.PUNTO != e.getKeyChar()) {
			char c = ' ';
			e.setKeyChar(c);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.campoTexto.setText(campoTexto.getText().trim());
	}
}