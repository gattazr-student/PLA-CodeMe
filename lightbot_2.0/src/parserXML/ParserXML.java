package parserXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import models.action.Action;
import models.action.Allumer;
import models.action.Attendre;
import models.action.Avancer;
import models.action.Break;
import models.action.Divise;
import models.action.Notify;
import models.action.Route;
import models.action.Sauter;
import models.action.TestAvancer;
import models.action.TestSauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.basic.Couleur;
import models.basic.Etat;
import models.basic.Orientation;
import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;
import models.niveau.CaseBasique;
import models.niveau.CaseInterrupteur;
import models.niveau.CaseLampe;
import models.niveau.CaseVide;
import models.niveau.Niveau;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserXML {

	public static Niveau creatLevelXML(String fichierXML) {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Declaration du niveau
		Niveau wLevel = new Niveau();

		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(fichierXML));

			// Recuperation de l'Element racine
			final Element racine = document.getDocumentElement();

			// Noeud 1 : Nom du niveau
			final Element levelName = (Element) racine.getElementsByTagName("name").item(0);

			// Initialise le nom du niveau
			wLevel.setNom(levelName.getTextContent());

			// Noeud 2 : List des Actions
			final Element actions = (Element) racine.getElementsByTagName("actions").item(0);
			final NodeList actionslist = actions.getElementsByTagName("action");
			final int nbActions = actionslist.getLength();

			Element action;
			Action aAction;
			for (int j = 0; j < nbActions; j++) {
				aAction = null;
				action = (Element) actionslist.item(j);
				switch (action.getTextContent()) {
				case "allumer":
					aAction = new Allumer();
					break;
				case "wait":
					aAction = new Attendre();
					break;
				case "avancer":
					aAction = new Avancer();
					break;
				case "break":
					aAction = new Break();
					break;
				case "divise":
					aAction = new Divise();
					break;
				case "notify":
					aAction = new Notify();
					break;
				case "sauter":
					aAction = new Sauter();
					break;
				case "testAvancer":
					aAction = new TestAvancer();
					break;
				case "testSauter":
					aAction = new TestSauter();
					break;
				case "droite":
					aAction = new TournerDroite();
					break;
				case "gauche":
					aAction = new TournerGauche();
					break;
				default:
					break;
				}
				if (aAction != null) {
					wLevel.addAction(aAction);
				}
			}

			// Noeud 3 : list de bots
			final Element bots = (Element) racine.getElementsByTagName("bots").item(0);
			final NodeList botslist = bots.getElementsByTagName("bot");
			final int nbBots = botslist.getLength();

			for (int i = 0; i < nbBots; i++) {

				if (botslist.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element bot = (Element) botslist.item(i);

					final String aName = bot.getAttribute("couleur");

					// position
					final Element position = (Element) bot.getElementsByTagName("position").item(0);

					final Position aPosBot = new Position(Integer.parseInt(position.getAttribute("x")),
							Integer.parseInt(position.getAttribute("y")));

					// Orientation
					final Element orient = (Element) bot.getElementsByTagName("orientation").item(0);

					if ((orient.getAttribute("init")).equals("EST")) {
						final Orientation aOrientBot = Orientation.EST;
						final Bot aBot = new Bot(aPosBot, aOrientBot, Couleur.BLANC, Etat.ACTIF);
						aBot.setName(aName);
						wLevel.addBot(aBot);
					}
					if ((orient.getAttribute("init")).equals("SUD")) {
						final Orientation aOrientBot = Orientation.SUD;
						final Bot aBot = new Bot(aPosBot, aOrientBot, Couleur.BLANC, Etat.ACTIF);
						aBot.setName(aName);
						wLevel.addBot(aBot);
					}
					if ((orient.getAttribute("init")).equals("OUEST")) {
						final Orientation aOrientBot = Orientation.OUEST;
						final Bot aBot = new Bot(aPosBot, aOrientBot, Couleur.BLANC, Etat.ACTIF);
						aBot.setName(aName);
						wLevel.addBot(aBot);
					}
					if ((orient.getAttribute("init")).equals("NORD")) {
						final Orientation aOrientBot = Orientation.NORD;
						final Bot aBot = new Bot(aPosBot, aOrientBot, Couleur.BLANC, Etat.ACTIF);
						aBot.setName(aName);
						wLevel.addBot(aBot);
					}

				}

			}

			/*
			 * Noeud 4 : Record
			 */
			final Element record = (Element) racine.getElementsByTagName("record").item(0);
			final int aRecord = Integer.parseInt(record.getAttribute("num"));
			wLevel.setRecord(aRecord);

			/*
			 * Noeud 5 : La map
			 */
			final Element map = (Element) racine.getElementsByTagName("map").item(0);

			// Dimentions
			final Element dimension = (Element) map.getElementsByTagName("dimensions").item(0);

			final Carte aCarte = new Carte(Integer.parseInt(dimension.getAttribute("x")),
					Integer.parseInt(dimension.getAttribute("y")));

			// Cellules
			final Element cellules = (Element) map.getElementsByTagName("cellules").item(0);
			final NodeList cellulelist = cellules.getElementsByTagName("cellule");
			final int nbCellule = cellulelist.getLength();

			for (int x = 0; x < nbCellule; x++) {

				final Element cellule = (Element) cellulelist.item(x);

				// Position
				final Element laPosition = (Element) cellule.getElementsByTagName("position").item(0);

				final Position aPosCase = new Position(Integer.parseInt(laPosition.getAttribute("x")),
						Integer.parseInt(laPosition.getAttribute("y")));

				// Cases
				final Element cases = (Element) cellule.getElementsByTagName("cases").item(0);
				final NodeList caseslist = cases.getElementsByTagName("case");
				final int nbCases = caseslist.getLength();

				Case aCase;
				for (int k = 0; k < nbCases; k++) {
					final Element uneCase = (Element) caseslist.item(k);
					aCase = null;
					switch (uneCase.getAttribute("type")) {
					case "basique":
						aCase = new CaseBasique(aPosCase, Integer.parseInt(uneCase.getAttribute("h")));
						break;
					case "lampe":
						aCase = new CaseLampe(aPosCase, Integer.parseInt(uneCase.getAttribute("h")));
						break;
					case "interrupteur":
						CaseInterrupteur wCase = new CaseInterrupteur(aPosCase, Integer.parseInt(uneCase
								.getAttribute("h")));

						/* TODO: Récupérer les positions ici */
						// eg : wCase.addPosition(new Position(11, 3));
						final NodeList positionlist = cases.getElementsByTagName("position");
						final int nbPos = positionlist.getLength();
						for (int j = 0; j < nbPos; j++) {
							wCase.addPosition(new Position(Integer.parseInt(((Element) positionlist.item(j))
									.getAttribute("x")), Integer.parseInt(((Element) positionlist.item(j))
											.getAttribute("y"))));
						}
						aCase = wCase;
						break;
					case "vide":
						aCase = new CaseVide(aPosCase);
						break;
					default:
						break;
					}
					aCarte.addCase(aCase);
				}

			}

			// Nombre de coups possibles
			final Element coups = (Element) racine.getElementsByTagName("coups").item(0);
			final int aNbRoute = Integer.parseInt(coups.getAttribute("nb"));
			for (int i = 0; i < aNbRoute; i++) {
				switch (i) {
				case 0:
					Route wMain;
					int wLongeur = Integer.parseInt(coups.getAttribute("main"));
					List<Bot> aBotList = wLevel.getBots();
					for (int j = 0; j < aBotList.size(); j++) {
						wMain = new Route(wLongeur, new ArrayList<Action>(), "main");
						(aBotList.get(j)).setRouteMain(wMain);
					}

					break;
				case 1:
					final Route aP1 = new Route(Integer.parseInt(coups.getAttribute("p1")),
							new ArrayList<Action>(), "p1");
					wLevel.addRoute(aP1);
					break;
				case 2:
					final Route aP2 = new Route(Integer.parseInt(coups.getAttribute("p2")),
							new ArrayList<Action>(), "p2");
					wLevel.addRoute(aP2);
					break;
				default:
				}
			}

			wLevel.setCarte(aCarte);

			return wLevel;

		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}