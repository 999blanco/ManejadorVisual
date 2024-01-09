package helpers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Reserva;

public class Dom {
	// Lectura
	
	// Iniciamos el DOM para la lectura
	public static Document initializeLectura(String nombre) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(nombre);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
	    return doc;
	}
	
	// Buscamos los dos de cada reserva, obtenemos el elemento con el metodo getElement y agregamos el objeto Reserva a la lista de reservas
	public static void root(Document doc) {
		NodeList nodeList = doc.getElementsByTagName("reserva");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Reserva.listaReservas.add(getElement(element));
			}
		}
	}
	
	// Obtenemos el elemento y devolvemos el objeto reserva con los datos del elemento obtenidos
	public static Reserva getElement(Element e) {
		String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
		String fechaEvento = e.getElementsByTagName("fechaEvento").item(0).getTextContent();
		String tipo = e.getElementsByTagName("tipo").item(0).getTextContent();
		String asistentes = e.getElementsByTagName("asistentes").item(0).getTextContent();
		String tipoCocina = e.getElementsByTagName("tipoCocina").item(0).getTextContent();
		String numeroJornadas = null;
		String habitaciones = null;
		String tipoMesa = null;
		String comensalesMesa = null;
		
		if (e.getElementsByTagName("numeroJornadas").getLength() > 0) {
			numeroJornadas = e.getElementsByTagName("numeroJornadas").item(0).getTextContent();
		}
		
		if (e.getElementsByTagName("habitaciones").getLength() > 0) {
			habitaciones = e.getElementsByTagName("habitaciones").item(0).getTextContent();
		}
		
		if (e.getElementsByTagName("tipoMesa").getLength() > 0) {
			tipoMesa = e.getElementsByTagName("tipoMesa").item(0).getTextContent();
		}
		
		if (e.getElementsByTagName("comensalesMesa").getLength() > 0) {
			comensalesMesa = e.getElementsByTagName("comensalesMesa").item(0).getTextContent();
		}
		
		Reserva reserva = new Reserva(nombre, fechaEvento, tipo, asistentes, tipoCocina, numeroJornadas, habitaciones, tipoMesa, comensalesMesa);
		return reserva;
	}	

	
	// Escritura
	
	// Iniciamos el DOM para la escritura
	public static Document initializeEscritura() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.newDocument();
	    return doc;
	}
	
	// Escribe el nodo raiz reservas y asigna todos los subnodos mediante el metodo rootNodos
	public static void rootRaiz(Document doc) throws TransformerException {
		Element eRaiz = doc.createElement("reservas");
		doc.appendChild(eRaiz);
		
		for (Reserva reserva : Reserva.listaReservas) {
			Element eReserva = rootNodos(reserva, doc);
			eRaiz.appendChild(eReserva);
		}
		
		transformer(doc);
	}
	
	// Crea el nodo y lo devuelve
	public static Element rootNodos(Reserva reserva, Document doc) throws TransformerException {
		Element eReserva = doc.createElement("reserva");
		
		Element eNombre = doc.createElement("nombre");
		eNombre.appendChild(doc.createTextNode(reserva.getNombre()));
		eReserva.appendChild(eNombre);
		
		Element eFechaEvento = doc.createElement("fechaEvento");
		eFechaEvento.appendChild(doc.createTextNode(reserva.getFechaEvento()));
		eReserva.appendChild(eFechaEvento);
		
		Element eTipo = doc.createElement("tipo");
		eTipo.appendChild(doc.createTextNode(reserva.getTipo()));
		eReserva.appendChild(eTipo);
		
		Element eAsistentes = doc.createElement("asistentes");
		eAsistentes.appendChild(doc.createTextNode(reserva.getAsistentes()));
		eReserva.appendChild(eAsistentes);
		
		Element eTipoCocina = doc.createElement("tipoCocina");
		eTipoCocina.appendChild(doc.createTextNode(reserva.getTipoCocina()));
		eReserva.appendChild(eTipoCocina);
		
		if (reserva.getNumeroJornadas() != null) {
			Element eNumeroJornadas = doc.createElement("numeroJornadas");
			eNumeroJornadas.appendChild(doc.createTextNode(reserva.getNumeroJornadas()));
			eReserva.appendChild(eNumeroJornadas);
		}
		
		if (reserva.getHabitaciones() != null) {
			Element eHabitaciones = doc.createElement("habitaciones");
			eHabitaciones.appendChild(doc.createTextNode(reserva.getHabitaciones()));
			eReserva.appendChild(eHabitaciones);
		}
		
		if (reserva.getTipoMesa() != null) {
			Element eTipoMesa = doc.createElement("tipoMesa");
			eTipoMesa.appendChild(doc.createTextNode(reserva.getTipoMesa()));
			eReserva.appendChild(eTipoMesa);
		}
		
		
		if (reserva.getComensalesMesa() != null) {
			Element eComensalesMesa = doc.createElement("comensalesMesa");
			eComensalesMesa.appendChild(doc.createTextNode(reserva.getComensalesMesa()));
			eReserva.appendChild(eComensalesMesa);
		}
		
		return eReserva;
	}
	
	public static void transformer(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
      	Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(new File("reservas-output.xml"));
	    transformer.transform(source, result);
	}
}
