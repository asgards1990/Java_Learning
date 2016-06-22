import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CreateXMLFileCourse {
    public static void main(final String[] args) {
        /*
	 * Etape 1 : r�cup�ration d'une instance de la classe "DocumentBuilderFactory"
	 */
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
	try {
	    /*
	     * Etape 2 : cr�ation d'un parseur
	     */
	    final DocumentBuilder builder = factory.newDocumentBuilder();
	    		
	    /*
	     * Etape 3 : cr�ation d'un Document
	     */
	    final Document document= builder.newDocument();
					
	    /*
	     * Etape 4 : cr�ation de l'Element racine
	     */
	    final Element racine = document.createElement("repertoire");
	    document.appendChild(racine);			
			
	    /*
	     * Etape 5 : cr�ation d'une personne
	     */
	    final Comment commentaire = document.createComment("John DOE");
	    racine.appendChild(commentaire);
			
	    final Element personne = document.createElement("personne");
	    personne.setAttribute("sexe", "masculin");
	    racine.appendChild(personne);
			
	    /*
	     * Etape 6 : cr�ation du nom et du pr�nom
	     */
	    final Element nom = document.createElement("nom");
	    nom.appendChild(document.createTextNode("DOE"));
			
	    final Element prenom = document.createElement("prenom");
	    prenom.appendChild(document.createTextNode("John"));
			
	    personne.appendChild(nom);
	    personne.appendChild(prenom);			
							
	    /*
	     * Etape 7 : r�cup�ration des num�ros de t�l�phone
	     */
	    final Element telephones = document.createElement("telephones");
	    
	    final Element fixe = document.createElement("telephone");
	    fixe.appendChild(document.createTextNode("01 02 03 04 05"));
	    fixe.setAttribute("type", "fixe");
			
	    final Element portable = document.createElement("telephone");
	    portable.appendChild(document.createTextNode("06 07 08 09 10"));
	    portable.setAttribute("type", "portable");
			
	    telephones.appendChild(fixe);
	    telephones.appendChild(portable);
	    personne.appendChild(telephones);
			
	    /*
	     * Etape 8 : affichage
	     */
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    
	    //If one wants to create a new file
//	    final StreamResult sortie = new StreamResult(new File("file.xml"));
	    final StreamResult result = new StreamResult(System.out);
			
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
//	    transformer.transform(source, sortie);
	    transformer.transform(source, result);
	}
	catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerException e) {
	    e.printStackTrace();
	}			
    }
}
