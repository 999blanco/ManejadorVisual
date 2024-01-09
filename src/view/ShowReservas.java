package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import helpers.Dom;
import model.Reserva;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ShowReservas {

	private JFrame frmReservasSalonHabana;
	private JTable datosReservas;
	private JTextField inputRutaFichero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowReservas window = new ShowReservas();
					window.frmReservasSalonHabana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowReservas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservasSalonHabana = new JFrame();
		frmReservasSalonHabana.setResizable(false);
		frmReservasSalonHabana.getContentPane().setBackground(new Color(90, 60, 20));
		frmReservasSalonHabana.setTitle("RESERVAS SALON HABANA");
		frmReservasSalonHabana.setBounds(100, 100, 952, 580);
		frmReservasSalonHabana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservasSalonHabana.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 211, 881, 303);
		frmReservasSalonHabana.getContentPane().add(scrollPane);
		
		datosReservas = new JTable();
		scrollPane.setViewportView(datosReservas);
		datosReservas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Nombre completo", "Telefono", "Fecha", "Tipo Reserva", "Num Personas", "Tipo Cocina", "Tipo Mesa", "Num Comensales", "Habitaciones?", "Num Jornadas"
			}
		));
		datosReservas.getColumnModel().getColumn(0).setPreferredWidth(125);
		datosReservas.getColumnModel().getColumn(0).setMinWidth(100);
		datosReservas.getColumnModel().getColumn(1).setPreferredWidth(80);
		datosReservas.getColumnModel().getColumn(2).setPreferredWidth(80);
		datosReservas.getColumnModel().getColumn(5).setPreferredWidth(78);
		datosReservas.getColumnModel().getColumn(7).setPreferredWidth(84);
		datosReservas.getColumnModel().getColumn(8).setPreferredWidth(82);
		
		JLabel titulo = new JLabel("RESERVAS SALON HABANA");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		titulo.setBounds(29, 160, 368, 31);
		frmReservasSalonHabana.getContentPane().add(titulo);
		
		JPanel importarPanel = new JPanel();
		importarPanel.setForeground(new Color(50, 31, 3));
		importarPanel.setBackground(new Color(50, 31, 3));
		importarPanel.setBounds(29, 26, 881, 117);
		frmReservasSalonHabana.getContentPane().add(importarPanel);
		importarPanel.setLayout(null);
		
		JLabel lblImportarRegistrosDesde = new JLabel("Importar registros desde un fichero");
		lblImportarRegistrosDesde.setForeground(Color.WHITE);
		lblImportarRegistrosDesde.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImportarRegistrosDesde.setBounds(20, 11, 368, 31);
		importarPanel.add(lblImportarRegistrosDesde);
		
		inputRutaFichero = new JTextField();
		inputRutaFichero.setBounds(21, 72, 505, 20);
		importarPanel.add(inputRutaFichero);
		inputRutaFichero.setColumns(10);
		
		JLabel labelRutaFichero = new JLabel("Ruta del fichero");
		labelRutaFichero.setForeground(Color.WHITE);
		labelRutaFichero.setBounds(20, 47, 155, 14);
		importarPanel.add(labelRutaFichero);
		
		JButton btnSeleccionarFichero = new JButton("Seleccionar fichero");
		btnSeleccionarFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int i = fileChooser.showOpenDialog(btnSeleccionarFichero);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					inputRutaFichero.setText(file.getPath());
				}
			}
		});
		btnSeleccionarFichero.setBounds(536, 71, 148, 23);
		importarPanel.add(btnSeleccionarFichero);
		
		JButton btnObtenerReservas = new JButton("Obtener reservas");
		btnObtenerReservas.setBounds(694, 71, 164, 23);
		importarPanel.add(btnObtenerReservas);
		
		btnObtenerReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Document doc;
				try {
					doc = Dom.initializeLectura(inputRutaFichero.getText());
					Dom.root(doc);
					DefaultTableModel model = (DefaultTableModel) datosReservas.getModel();
					model.setRowCount(0);
					for (Reserva reserva : Reserva.listaReservas) {
						model.addRow(new Object[]{reserva.getNombre(), null, reserva.getFechaEvento(), reserva.getTipo(), reserva.getAsistentes(),
				                reserva.getTipoCocina(), reserva.getTipoMesa(), reserva.getComensalesMesa(), reserva.getHabitaciones(), reserva.getNumeroJornadas()});
					}
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
