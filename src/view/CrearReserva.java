package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import helpers.Dom;
import model.Reserva;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;

public class CrearReserva {

	private JFrame frmReservasSalonHabana;
	private JTextField inputNombre;
	private JTextField inputTelefono;
	private JTextField inputFecha;
	private JTextField inputNumPersonas;
	private JTextField numJornadasInput;
	private JTextField inputNumComensales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearReserva window = new CrearReserva();
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
	public CrearReserva() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservasSalonHabana = new JFrame();
		frmReservasSalonHabana.getContentPane().setBackground(new Color(90, 62, 20));
		frmReservasSalonHabana.setTitle("RESERVAR SALON HABANA");
		frmReservasSalonHabana.setBounds(100, 100, 480, 619);
		frmReservasSalonHabana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(90, 62, 20));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		frmReservasSalonHabana.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(7, 1, 0, 0));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelTitulo);
		panelTitulo.setLayout(new GridLayout(1, 1, 0, 0));

		JLabel titulo = new JLabel("RESERVAR SALON HABANA");
		titulo.setBackground(new Color(50, 31, 3));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.WHITE);
		titulo.setOpaque(true);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelTitulo.add(titulo);

		JPanel panelSubtitulo1 = new JPanel();
		panelSubtitulo1.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelSubtitulo1);
		panelSubtitulo1.setLayout(new GridLayout(1, 1, 0, 0));

		JLabel subtitulo1 = new JLabel("Datos Personales");
		subtitulo1.setForeground(Color.WHITE);
		subtitulo1.setFont(new Font("Tahoma", Font.BOLD, 12));
		subtitulo1.setHorizontalAlignment(SwingConstants.LEFT);
		panelSubtitulo1.add(subtitulo1);

		JPanel panelInputs1 = new JPanel();
		panelInputs1.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelInputs1);
		panelInputs1.setLayout(new GridLayout(2, 2, 20, 0));

		JLabel labelNombre = new JLabel("Nombre completo");
		labelNombre.setForeground(Color.WHITE);
		panelInputs1.add(labelNombre);

		JLabel labelTelefono = new JLabel("Teléfono");
		labelTelefono.setForeground(Color.WHITE);
		panelInputs1.add(labelTelefono);

		inputNombre = new JTextField();
		panelInputs1.add(inputNombre);
		inputNombre.setColumns(10);

		inputTelefono = new JTextField();
		panelInputs1.add(inputTelefono);
		inputTelefono.setColumns(10);

		JPanel panelSubtitulo2 = new JPanel();
		panelSubtitulo2.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelSubtitulo2);
		panelSubtitulo2.setLayout(new GridLayout(1, 1, 0, 0));

		JLabel lblDatosReservas = new JLabel("Datos Reservas");
		lblDatosReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosReservas.setForeground(Color.WHITE);
		lblDatosReservas.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSubtitulo2.add(lblDatosReservas);

		JPanel panelInputs2 = new JPanel();
		panelInputs2.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelInputs2);
		panelInputs2.setLayout(new GridLayout(2, 2, 20, 0));

		JLabel labelFecha = new JLabel("Fecha");
		labelFecha.setForeground(Color.WHITE);
		panelInputs2.add(labelFecha);

		JLabel labelTipoReserva = new JLabel("Tipo Reserva");
		labelTipoReserva.setForeground(Color.WHITE);
		panelInputs2.add(labelTipoReserva);

		inputFecha = new JTextField();
		inputFecha.setColumns(10);
		panelInputs2.add(inputFecha);

		JComboBox comboTipoReserva = new JComboBox();
		comboTipoReserva.setModel(
				new DefaultComboBoxModel(new String[] { "Selecciona un tipo:", "Jornada", "Banquete", "Congreso" }));
		panelInputs2.add(comboTipoReserva);

		JPanel panelInputs3 = new JPanel();
		panelInputs3.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelInputs3);
		panelInputs3.setLayout(new GridLayout(2, 2, 20, 0));

		JLabel labelNumPersonas = new JLabel("Numero Personas");
		labelNumPersonas.setForeground(Color.WHITE);
		labelNumPersonas.setBackground(new Color(90, 62, 20));
		panelInputs3.add(labelNumPersonas);

		JLabel labelTipoCocina = new JLabel("Tipo Cocina");
		labelTipoCocina.setForeground(Color.WHITE);
		panelInputs3.add(labelTipoCocina);

		inputNumPersonas = new JTextField();
		inputNumPersonas.setColumns(10);
		panelInputs3.add(inputNumPersonas);

		JComboBox comboTipoCocina = new JComboBox();
		comboTipoCocina.setModel(new DefaultComboBoxModel(
				new String[] { "Selecciona un tipo:", "Buffet", "Carta", "Pedir cita con chef", "No precisa" }));
		panelInputs3.add(comboTipoCocina);

		JPanel panelInputs4 = new JPanel();
		panelInputs4.setBackground(new Color(90, 62, 20));
		panelInputs4.setLayout(new GridLayout(2, 2, 20, 0));

		JLabel labelHabitaciones = new JLabel("¿Requerirán Habitaciones?");
		labelHabitaciones.setForeground(Color.WHITE);
		labelHabitaciones.setBackground(new Color(90, 62, 20));
		panelInputs4.add(labelHabitaciones);

		JLabel labelNumJornadas = new JLabel("Numero Jornadas");
		labelNumJornadas.setForeground(Color.WHITE);
		panelInputs4.add(labelNumJornadas);

		JComboBox comboHabitaciones = new JComboBox();
		comboHabitaciones.setModel(new DefaultComboBoxModel(
				new String[] { "Selecciona una respuesta:", "Si, requerimos habitaciones", "No, no es necesario" }));
		panelInputs4.add(comboHabitaciones);

		numJornadasInput = new JTextField();
		numJornadasInput.setColumns(10);
		panelInputs4.add(numJornadasInput);

		JPanel panelInputs5 = new JPanel();
		panelInputs5.setBackground(new Color(90, 62, 20));
		panelInputs5.setLayout(new GridLayout(2, 2, 20, 0));

		JLabel labelTipoMesa = new JLabel("Tipo Mesa");
		labelTipoMesa.setForeground(Color.WHITE);
		labelTipoMesa.setBackground(new Color(90, 62, 20));
		panelInputs5.add(labelTipoMesa);

		JLabel labelNumComensales = new JLabel("Numero Comensales");
		labelNumComensales.setForeground(Color.WHITE);
		panelInputs5.add(labelNumComensales);

		JComboBox comboTipoMesa = new JComboBox();
		comboTipoMesa
				.setModel(new DefaultComboBoxModel(new String[] { "Selecciona un tipo:", "Rectangular", "Redonda" }));
		panelInputs5.add(comboTipoMesa);

		inputNumComensales = new JTextField();
		inputNumComensales.setColumns(10);
		panelInputs5.add(inputNumComensales);

		JPanel panelBoton = new JPanel();
		panelBoton.setBorder(new EmptyBorder(25, 0, 0, 0));
		panelBoton.setBackground(new Color(90, 62, 20));
		mainPanel.add(panelBoton);
		panelBoton.setLayout(new GridLayout(1, 1, 0, 0));

		JButton btnCrearReserva = new JButton("CREAR RESERVA");
		btnCrearReserva.setForeground(Color.WHITE);
		btnCrearReserva.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearReserva.setBackground(new Color(50, 31, 3));
		panelBoton.add(btnCrearReserva);

		comboTipoReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) comboTipoReserva.getSelectedItem();

				if (seleccion.equals("Congreso")) {
					mainPanel.remove(panelInputs5);
					mainPanel.remove(panelBoton);
					mainPanel.add(panelInputs4);
					mainPanel.add(panelBoton);
					mainPanel.add(panelBoton);
					mainPanel.setLayout(new GridLayout(8, 1, 0, 0));
				} else if (seleccion.equals("Banquete")) {
					mainPanel.remove(panelInputs4);
					mainPanel.remove(panelBoton);
					mainPanel.add(panelInputs5);
					mainPanel.add(panelBoton);
					mainPanel.add(panelBoton);
					mainPanel.setLayout(new GridLayout(8, 1, 0, 0));
				} else {
					mainPanel.remove(panelInputs4);
					mainPanel.remove(panelInputs5);
					mainPanel.setLayout(new GridLayout(7, 1, 0, 0));
				}
			}
		});

		btnCrearReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = inputNombre.getText();
				String fechaEvento = inputFecha.getText();
				String tipo = (String) comboTipoReserva.getSelectedItem();
				String asistentes = inputNumPersonas.getText();
				String tipoCocina = (String) comboTipoCocina.getSelectedItem();
				String numeroJornadas = numJornadasInput.getText();
				String habitaciones = (String) comboHabitaciones.getSelectedItem();
				String tipoMesa = (String) comboTipoMesa.getSelectedItem();
				String comensalesMesa = inputNumComensales.getText();
				
				if (tipo.equals("Congreso")) {
					tipoMesa = null;
					comensalesMesa = null;
				} else if (tipo.equals("Banquete")) {
					habitaciones = null;
					numeroJornadas = null;
				} else {
					tipoMesa = null;
					comensalesMesa = null;
					habitaciones = null;
					numeroJornadas = null;
				}
				
				Reserva.listaReservas.add(new Reserva(nombre, fechaEvento, tipo, asistentes, tipoCocina, numeroJornadas,
						habitaciones, tipoMesa, comensalesMesa));
				
				try {
					Document doc = Dom.initializeEscritura();
					Dom.rootRaiz(doc);
					System.out.println("¡Reserva creada! El fichero reservas-output.xml se ha creado satisfactoriamente.");
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

				inputNombre.setText("");
				inputTelefono.setText("");
				inputFecha.setText("");
				comboTipoReserva.setSelectedIndex(0);
				inputNumPersonas.setText("");
				comboTipoCocina.setSelectedIndex(0);
				numJornadasInput.setText("");
				comboHabitaciones.setSelectedIndex(0);
				comboTipoMesa.setSelectedIndex(0);
				inputNumComensales.setText("");
			}
		});
	}
}