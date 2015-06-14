package controllers;

import models.action.Action;
import models.action.Route;
import models.basic.TypeRoute;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Niveau;

public class Ordonnanceur {
	private Niveau pNiveau;

	public Ordonnanceur(Niveau aNiveau) {
		this.pNiveau = aNiveau;

	}

	public Niveau getNiveau() {
		return this.pNiveau;
	}

	public void runP1(int i, TypeRoute atype) {
		Bot aBot = this.pNiveau.getBots().get(i);
		Carte aCarte = this.pNiveau.getCarte();
		Route routeP1 = this.pNiveau.getRoutes().get(0);
		int bposition = 0;
		/*
		 * while (routeP1.getAction().get(routeP1.getPosition()).getCouleur() != aBot.getCouleur() &&
		 * routeP1.getAction().get(routeP1.getPosition()).getCouleur() != Couleur.BLANC&&
		 * routeP1.getPosition() <= routeP1.getAction().size()) {
		 * bposition = routeP1.getPosition() + 1;
		 * routeP1.setPosition(bposition);
		 * }
		 */
		if (routeP1.getPosition() >= routeP1.getAction().size()) {
			bposition = this.pNiveau.getBots().get(i).getBot_Route().getPosition() + 1;
			this.pNiveau.getBots().get(i).getBot_Route().setPosition(bposition);
		} else {
			Action aAction = routeP1.getAction().get(routeP1.getPosition());
			if (aAction instanceof Route) {
				if (((Route) aAction).getType() == TypeRoute.P2) {

					this.pNiveau.getRoutes().get(1).setPosition(0);
					runP2(i, atype = TypeRoute.P1);
				} else {
					routeP1.setPosition(0);
					runP1(i, atype = TypeRoute.P1);
				}
			} else {// c'est le step

				if (aAction.valid(aBot, aCarte)) {
					aAction.apply(aBot, aCarte);
					bposition = this.pNiveau.getRoutes().get(0).getPosition() + 1;
				} else {
					bposition = this.pNiveau.getRoutes().get(0).getPosition() + 1;
				}
				if (bposition >= routeP1.getAction().size()) {
					if (atype == TypeRoute.P1) {
						this.pNiveau.getRoutes().get(0).setPosition(0);
					} else if (atype == TypeRoute.P2) {
						bposition = this.pNiveau.getRoutes().get(1).getPosition() + 1;
						this.pNiveau.getRoutes().get(1).setPosition(bposition);
					} else if (atype == TypeRoute.MAIN) {// type parent=main
						bposition = this.pNiveau.getBots().get(i).getBot_Route().getPosition() + 1;
						System.out.println(bposition);
						this.pNiveau.getBots().get(i).getBot_Route().setPosition(bposition);
					}
				} else {
					routeP1.setPosition(bposition);
				}

			}
		}
	}

	// bot 2

	public void runP2(int i, TypeRoute atype) {

		Bot aBot = this.pNiveau.getBots().get(i);
		Carte aCarte = this.pNiveau.getCarte();
		Route routeP2 = this.pNiveau.getRoutes().get(1);
		int bposition = 0;
		/*
		 * while (routeP2.getAction().get(routeP2.getPosition()).getCouleur() != aBot.getCouleur()
		 * && routeP2.getAction().get(routeP2.getPosition()).getCouleur() != Couleur.BLANC
		 * && routeP2.getPosition() <= routeP2.getAction().size()) {
		 * bposition = routeP2.getPosition() + 1;
		 * routeP2.setPosition(bposition);
		 * }
		 */
		if (routeP2.getPosition() >= routeP2.getAction().size()) {
			bposition = this.pNiveau.getBots().get(i).getBot_Route().getPosition() + 1;
			this.pNiveau.getBots().get(i).getBot_Route().setPosition(bposition);
		} else {

			Action aAction = routeP2.getAction().get(routeP2.getPosition());
			if (aAction instanceof Route) {

				if (((Route) aAction).getType() == TypeRoute.P1) {
					this.pNiveau.getRoutes().get(0).setPosition(0);
					runP1(i, atype = TypeRoute.P2);
				} else {
					routeP2.setPosition(0);
					runP2(i, atype = TypeRoute.P2);
				}
			} else {// c'est le step
				if (aAction.valid(aBot, aCarte)) {
					aAction.apply(aBot, aCarte);
					bposition = this.pNiveau.getRoutes().get(1).getPosition() + 1;
				} else {
					bposition = this.pNiveau.getRoutes().get(1).getPosition() + 1;
				}
				if (bposition >= routeP2.getAction().size()) {

					if (atype == TypeRoute.P1) {
						bposition = this.pNiveau.getRoutes().get(0).getPosition() + 1;
						this.pNiveau.getRoutes().get(0).setPosition(bposition);
					} else if (atype == TypeRoute.P2) {
						this.pNiveau.getRoutes().get(1).setPosition(0);
					} else {
						bposition = this.pNiveau.getBots().get(i).getBot_Route().getPosition() + 1;
						this.pNiveau.getBots().get(i).getBot_Route().setPosition(bposition);
					}
				} else {
					routeP2.setPosition(bposition);
				}
			}
		}
	}

	public void setNiveau(Niveau aNiveau) {
		this.pNiveau = aNiveau;
	}

	public void step() {

		Bot aBot = new Bot();
		aBot = this.pNiveau.getBots().get(0);// Bot 1
		Bot bBot = new Bot();// bot 2
		bBot = this.pNiveau.getBots().get(1);
		Carte aCarte = this.pNiveau.getCarte();
		int bposition = 0;
		int aBotsize = aBot.getBot_Route().getAction().size();// taille of bot1
		int bBotsize = bBot.getBot_Route().getAction().size();// taille of bot2
		Route aBotRoute = aBot.getBot_Route();// mainroute of bot1
		Route bBotRoute = bBot.getBot_Route();// mainroute of bot2
		if (aBotRoute.getPosition() < aBotsize) {// exectue le action dans mainroute of bot1
			/*
			 * while (aBotRoute.getAction().get(aBotRoute.getPosition()).getCouleur() != aBot.getCouleur()
			 * && aBotRoute.getAction().get(aBotRoute.getPosition()).getCouleur() != Couleur.BLANC&&
			 * aBotRoute.getPosition() < aBotsize) {
			 * 
			 * bposition = aBotRoute.getPosition() + 1;
			 * aBotRoute.setPosition(bposition);
			 * }
			 */// test coleur
			if (aBotRoute.getAction().get(aBotRoute.getPosition()) instanceof Route) {
				Route aAction = (Route) aBotRoute.getAction().get(aBotRoute.getPosition());// action est
																							// un route
				if (aAction.getAction().size() == 1 & aAction.getAction().get(0) instanceof Route
						&& ((Route) aAction.getAction().get(0)).getAction().get(0) instanceof Route) {
					bposition = aBotRoute.getPosition() + 1;
					aBotRoute.setPosition(bposition);
				}
				if (aAction.getType() == TypeRoute.P1) {
					runP1(0, TypeRoute.MAIN);// &&&&&&&&
				} else if (aAction.getType() == TypeRoute.P2) {
					runP2(0, TypeRoute.MAIN);
				}

			} else {// je pense c'est le step();
				if (aBotRoute.getAction().get(aBotRoute.getPosition()).valid(aBot, aCarte)) {
					aBotRoute.getAction().get(aBotRoute.getPosition()).apply(aBot, aCarte);
					bposition = aBotRoute.getPosition() + 1;
					aBotRoute.setPosition(bposition);
				} else {
					bposition = aBotRoute.getPosition() + 1;
					aBotRoute.setPosition(bposition);
				}
			}
		}// bot 1

		if (bBotRoute.getPosition() < bBotsize) {
			/*
			 * while (bBotRoute.getAction().get(bBotRoute.getPosition()).getCouleur() != bBot.getCouleur()
			 * && bBotRoute.getAction().get(bBotRoute.getPosition()).getCouleur() != Couleur.BLANC
			 * && bBotRoute.getPosition() < bBotsize) {
			 * 
			 * bposition = bBotRoute.getPosition() + 1;
			 * bBotRoute.setPosition(bposition);
			 * }
			 */
			if (bBotRoute.getAction().get(bBotRoute.getPosition()) instanceof Route) {
				Route aAction = (Route) bBotRoute.getAction().get(bBotRoute.getPosition());
				if (aAction.getAction().size() == 1 & aAction.getAction().get(0) instanceof Route
						&& ((Route) aAction.getAction().get(0)).getAction().get(0) instanceof Route) {
					bposition = bBotRoute.getPosition() + 1;
					bBotRoute.setPosition(bposition);
				}
				if (aAction.getType() == TypeRoute.P1) {
					runP1(1, TypeRoute.MAIN);
				} else if (aAction.getType() == TypeRoute.P2) {
					runP2(1, TypeRoute.MAIN);
				}
			}

			else {// c'est le step

				if (bBotRoute.getAction().get(bBotRoute.getPosition()).valid(bBot, aCarte)) {

					bBotRoute.getAction().get(bBotRoute.getPosition()).apply(bBot, aCarte);

					bposition = bBotRoute.getPosition() + 1;

					bBotRoute.setPosition(bposition);

				} else {
					bposition = bBotRoute.getPosition() + 1;

					bBotRoute.setPosition(bposition);
				}
			}
		}
	}
}
