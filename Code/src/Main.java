import java.io.File;
import java.io.IOException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


public class Main {
	
	static Document document;
	static Element racine;
	
	public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
		AfficheXml("Salon.xml");
	}
	
	//prend en paramètre le nom du fichier à afficher
	public static void AfficheXml(String nomFichier) throws ParserConfigurationException, SAXException, IOException{
		//Etape 1 : recuperation instance de la classe
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		
		final DocumentBuilder builder = factory.newDocumentBuilder();
		
		//etape 3 : Creation d'un document
		final Document document = builder.parse(new File(nomFichier));
		//etape 4 : Creation de l'element racine
		final Element racine = document.getDocumentElement();
		AnalyzeRacine((Node)racine);
		
	}

	//analyseur de la racine
	public static void AnalyzeRacine(Node no){
		//affichage du nom du noeud racine
		System.out.println(no.getNodeName());
		//affichage des racines si il y en a
		AnalyzerAttribute(no);
		System.out.println();
		Analyzer(no.getChildNodes());
	}
	
	//Analyseur d'une liste de noeud
	public static void Analyzer(NodeList noeud)
	{
		//recuperer le nombre de noeud se trouvant dans la liste
		int nombreDeNoeud = noeud.getLength();
		
		//parcours de la liste
		for(int i = 0; i < nombreDeNoeud; i++)
		{
			//vérification que l'item i est un élément
			if(noeud.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				//affectation de l'item i
				Node unNoeud = noeud.item(i);
				//Affichage du nom du noeud
				System.out.println(unNoeud.getNodeName());
				//appel de la méthode d'analyse de noeud
				AnalyzerAttribute(unNoeud);
				
				System.out.println();
				//rappel de la méthode pour la récurssivité
				Analyzer(unNoeud.getChildNodes());
			}
			System.out.println();
		}
	}
	
	//Anlyseur d'un noeud pour trouver les attributs
	
	//analyseur d'un noeud pour trouver des attributs
	public static void AnalyzerAttribute (Node node)
	{
		//recuper tous les attributs du noeud
		NamedNodeMap listAttribut = node.getAttributes();
		//stockage du nombre d'attribut
		int nombreDattribut = listAttribut.getLength();
		
		//parcours de la listAttribut
		for(int g = 0; g < nombreDattribut; g++)
		{
			//verification que l'item g est un attribut
			if(listAttribut.item(g).getNodeType() == Node.ATTRIBUTE_NODE)
			{
				//affectation à attribut d'un item g qui est casté avant l'affectation
				Attr attribut = (Attr) listAttribut.item(g);
				//affichage
				System.out.println(attribut.getName()+ " : "+attribut.getValue());
			}
		}
	}
}
	

