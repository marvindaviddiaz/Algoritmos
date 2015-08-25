package ueps;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class UEPSView extends JFrame implements ActionListener {

	UEPS ueps = new UEPS();

	private JFormattedTextField fecha, cantidad, precio, popcantidad;
	private JLabel labelFecha, labelCantidad, labelPrecio, popLabelCantidad;

	private JScrollPane scrollPane;
	private JTable table;
	private String[] columnNames = { "Fecha", "Cantidad", "Precio", "Total" };
	private JButton push, pop;

	public UEPSView() throws ParseException {
		super("UEPS - UMG");
		this.setLayout(null);
		super.setVisible(true);
		super.setSize(600, 500);

		labelFecha = new JLabel("Fecha:");
		labelCantidad = new JLabel("Cantidad:");
		labelPrecio = new JLabel("Precio:");
		labelFecha.setBounds(25, 20, 100, 25);
		labelCantidad.setBounds(25, 60, 100, 25);
		labelPrecio.setBounds(25, 100, 100, 25);
		labelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrecio.setHorizontalAlignment(SwingConstants.RIGHT);

		NumberFormat decimal_for = NumberFormat.getInstance();
		decimal_for.setMinimumFractionDigits(2);
		decimal_for.setMaximumFractionDigits(2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		fecha = new JFormattedTextField(sdf);
		fecha.setText(sdf.format(new Date()));
		cantidad = new JFormattedTextField(new Integer(0));
		precio = new JFormattedTextField(decimal_for);

		fecha.setBounds(150, 20, 100, 22);
		cantidad.setBounds(150, 60, 100, 22);
		precio.setBounds(150, 100, 100, 22);

		push = new JButton("Push");
		push.setBounds(175, 140, 75, 22);
		push.addActionListener(this);

		popLabelCantidad = new JLabel("Cantidad:");
		popLabelCantidad.setBounds(325, 100, 100, 25);
		popLabelCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		popcantidad = new JFormattedTextField(new Integer(0));
		popcantidad.setBounds(450, 100, 100, 22);

		pop = new JButton("Pop");
		pop.setBounds(475, 140, 75, 22);
		pop.addActionListener(this);

		this.add(labelFecha);
		this.add(labelCantidad);
		this.add(labelPrecio);
		this.add(fecha);
		this.add(cantidad);
		this.add(precio);
		this.add(push);

		this.add(popLabelCantidad);
		this.add(popcantidad);
		this.add(pop);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		fillList();
		this.add(scrollPane);

	}

	public void fillList() {
		Object[][] data = new Object[ueps.max][columnNames.length];
		for (int i = 0; i < ueps.top; i++) {
			data[i][0] = ueps.list()[i].fecha;
			data[i][1] = ueps.list()[i].cantidad;
			data[i][2] = ueps.list()[i].precio;
			data[i][3] = 0;
		}
		if (ueps.top == 0) {
			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
			data[0][3] = "";
		}
		table = new JTable(new AppTableModel(columnNames, data));
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		if (null == scrollPane) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 200, 500, 180);
		} else {
			remove(scrollPane);
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 200, 500, 180);
			this.add(scrollPane);
		}

		this.setVisible(false);
		this.setVisible(true);
		this.repaint();
	}

	public static void main(String[] args) {
		try {
			new UEPSView();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == push) {
			Producto r = new Producto();
			r.setFecha(new Date(fecha.getText()));
			r.setCantidad((Integer) cantidad.getValue());
			r.setPrecio(new Double(precio.getValue().toString()));
			ueps.push(r);
			fillList();
		} else if (e.getSource() == pop) {
			boolean flag = true;
			int cantidadParaPedir = (Integer) popcantidad.getValue();
			int stock = 0;
			String resultado = "";
			while (flag) {
				Resultado r = ueps.pop(cantidadParaPedir);
				cantidadParaPedir -= r.getCantidad();
				resultado += "\n" + r.getCantidad() + " de: Q. " + r.getPrecio();
				if (0 == cantidadParaPedir || r.getCantidad() == 0 ) {
					flag = false;
				}
			}
			JOptionPane.showMessageDialog(this, resultado);
			fillList();
		}
	}

}
