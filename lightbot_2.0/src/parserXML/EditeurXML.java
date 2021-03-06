package parserXML;

import java.io.File;
import java.util.Scanner;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Classe permettant la création d'un fichier XML à partir d'une description faite au terminal
 *
 */
public class EditeurXML {

	/**
	 * Création du fichier XML nomFichier contenant la carte qui sera décrit
	 *
	 * @param nomfichier
	 *            nom du fichier de sortie contenant le XML
	 */
	public static void EditXML(String nomfichier) {
		/*
		 * Recuperation d'une instance de la classe "DocumentBuilderFactory"
		 */
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.newDocument();

			/*
			 * Cr�ation de l'Element racine
			 */
			final Element racine = document.createElement("level");
			document.appendChild(racine);

			Scanner sc = new Scanner(System.in);

			/*
			 * Cr�ation du nom du niveau
			 */

			System.out.println("Veuillez saisir le nom du niveau :");
			String str = sc.nextLine();
			final Element name = document.createElement("name");
			name.appendChild(document.createTextNode(str));
			racine.appendChild(name);

			/*
			 * List des actions
			 */
			final Element aActions = document.createElement("actions");

			System.out.println("Veuillez saisir le nombre d'actions que vous voulez saisir :");
			String nbActions = sc.nextLine();
			for (int i = 0; i < Integer.parseInt(nbActions); i++) {
				System.out.println("Veuillez saisir l'action " + (i + 1) + " : ");
				String action = sc.nextLine();
				final Element aAction = document.createElement("action");
				aAction.appendChild(document.createTextNode(action));
				aActions.appendChild(aAction);
			}

			racine.appendChild(aActions);

			/**
			 * List des Bots
			 */
			final Element aBots = document.createElement("bots");
			System.out.println("Veuillez saisir le nombre de Bot que vous voulez :");
			String nbBots = sc.nextLine();
			for (int i = 0; i < Integer.parseInt(nbBots); i++) {
				/**
				 * Bot et couleur
				 */
				System.out.println("Veuillez saisir le nom du bot  (minion1 ou minion2) " + (i + 1) + " : ");
				String couleur = sc.nextLine();
				final Element aBot = document.createElement("bot");
				aBot.setAttribute("name", couleur);

				/**
				 * Position init du Bot
				 */
				final Element aPosition = document.createElement("position");
				System.out.println("Veuillez saisir la position du bot  " + couleur + ", X = ");
				String aX = sc.nextLine();
				System.out.println("Veuillez saisir la position du bot  " + (i + 1) + ", Y = ");
				String aY = sc.nextLine();
				aPosition.setAttribute("x", aX);
				aPosition.setAttribute("y", aY);

				/**
				 * Orientation init du Bot
				 */
				final Element aOrientation = document.createElement("orientation");
				System.out.println("Veuillez saisir l'orientation du bot  " + couleur
						+ " (NORD SUD EST OUEST) ");
				String aOrient = sc.nextLine();
				aOrientation.setAttribute("init", aOrient);

				aBot.appendChild(aPosition);
				aBot.appendChild(aOrientation);
				aBots.appendChild(aBot);
			}

			racine.appendChild(aBots);

			/**
			 * Record
			 */
			final Element aRecord = document.createElement("record");
			System.out.println("Veuillez saisir le record en nombre de coups:");
			String coups = sc.nextLine();
			System.out.println("Veuillez saisir le record en nombre d'actions:");
			String actions = sc.nextLine();
			aRecord.setAttribute("coups", coups);
			aRecord.setAttribute("actions", actions);
			racine.appendChild(aRecord);

			/**
			 * La carte
			 */
			final Element aMap = document.createElement("map");

			/**
			 * Dimensions de la carte
			 */
			System.out.println("Veuillez saisir la dimention X =");
			String aDimX = sc.nextLine();
			System.out.println("Veuillez saisir la dimention Y =");
			String aDimY = sc.nextLine();
			final Element aDimensions = document.createElement("dimensions");
			aDimensions.setAttribute("x", aDimX);
			aDimensions.setAttribute("y", aDimY);
			aMap.appendChild(aDimensions);

			/**
			 * Nombres de cellules
			 */
			final Element aCellules = document.createElement("cellules");
			System.out.println("Veuillez saisir le nombre de cellules que vous voulez :");
			String nbCell = sc.nextLine();
			for (int i = 0; i < Integer.parseInt(nbCell); i++) {
				final Element aCellule = document.createElement("cellule");

				/**
				 * Une cellule
				 */
				final Element aCases = document.createElement("cases");
				System.out.println("_________________________________");
				System.out.println("          Cellule " + (i + 1));
				System.out.println("_________________________________");

				/**
				 * Position de la cellule
				 */
				final Element aPos = document.createElement("position");
				System.out.println("Veuillez saisir la position X = ");
				String aPX = sc.nextLine();
				System.out.println("Veuillez saisir la position Y = ");
				String aPY = sc.nextLine();
				aPos.setAttribute("x", aPX);
				aPos.setAttribute("y", aPY);
				aCases.appendChild(aPos);

				/**
				 * Nombre de cases dans la cellule
				 */
				System.out.println("Veuillez saisir le nombre de cases : ");
				String aNbCases = sc.nextLine();

				for (int j = 0; j < Integer.parseInt(aNbCases); j++) {

					/**
					 * Une Case
					 */
					final Element aCase = document.createElement("case");
					System.out.println("Veuillez saisir le type de la case : ");
					String aType = sc.nextLine();
					if (aType.equals("interrupteur")) {
						System.out.println("Veuillez saisir le nombre de case li� � l'interrupteur : ");
						String aNbPos = sc.nextLine();
						for (int p = 0; p < Integer.parseInt(aNbPos); p++) {
							final Element aPositionI = document.createElement("position");
							System.out.println("Veuillez saisir la position X = ");
							String aPosX = sc.nextLine();
							System.out.println("Veuillez saisir la position Y = ");
							String aPosY = sc.nextLine();
							aPositionI.setAttribute("x", aPosX);
							aPositionI.setAttribute("y", aPosY);
							aCase.appendChild(aPositionI);
						}
					}
					System.out.println("Veuillez saisir la hauteur de la case : ");
					String aHauteur = sc.nextLine();
					aCase.setAttribute("type", aType);
					aCase.setAttribute("h", aHauteur);
					aCases.appendChild(aCase);
				}
				aCellule.appendChild(aCases);
				aCellules.appendChild(aCellule);
			}

			aMap.appendChild(aCellules);
			racine.appendChild(aMap);

			/**
			 * Nombre de coups
			 */
			System.out.println("Veuillez saisir le nombre de procedure possible =");
			String aNb = sc.nextLine();
			final Element aCoups = document.createElement("coups");
			aCoups.setAttribute("nb", aNb);
			for (int i = 0; i < Integer.parseInt(aNb); i++) {
				switch (i) {
				case 0:
					System.out.println("Veuillez saisir le nombre d'action du main =");
					String aMain = sc.nextLine();
					aCoups.setAttribute("main", aMain);
					break;
				case 1:
					System.out.println("Veuillez saisir le nombre d'action de p1 =");
					String aP1 = sc.nextLine();
					aCoups.setAttribute("p1", aP1);
					break;
				case 2:
					System.out.println("Veuillez saisir le nombre d'action de p2 =");
					String aP2 = sc.nextLine();
					aCoups.setAttribute("p2", aP2);
					break;
				default:
				}
			}

			racine.appendChild(aCoups);

			sc.close();

			/*
			 * Affichage dans un fichier
			 */
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(nomfichier));
			// final StreamResult sortie = new StreamResult(System.out);

			// prologue
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

			// formatage
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			// sortie
			transformer.transform(source, sortie);

		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}