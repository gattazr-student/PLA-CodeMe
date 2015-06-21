package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.cases.Case;
import models.niveau.Cellule;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import views.View;
import views.bot.VBot;
import views.cases.VCase;

/**
 * Représentation graphique d'une CaseVide
 *
 */
public class VCellule extends View implements Observer {

	/** Cellule à représenter */
	private Cellule pCellule;
	/** Liste de VCase représentant les Cases dans la Cellule */
	private List<VCase> pVCases;
	/** Indice désignant la Case courante dans la cellule */
	private int pCourant;
	/** Bot à représenter dans la cellule si nécessaire */
	private VBot pVBot;

	/**
	 * Création d'une VCellule avec un VBot en suplément optionnel
	 *
	 * @param aCellule
	 *            Cellule à représenter
	 * @param aVBot
	 *            Bot, optionnel, à rajouter pendant le dessin
	 * @param aZone
	 *            position et taille de la VCellule
	 */
	public VCellule(Cellule aCellule, VBot aVBot, FloatRect aZone) {
		super(aZone);
		this.pCellule = aCellule;
		this.pVBot = aVBot;
		this.pCellule.addObserver(this);
		initView(); // initialise la vue
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		/* Récupère la hauteur */
		Case wCase = this.pCellule.getCase();
		int wHauteur;
		if (wCase == null) {
			wHauteur = 0;
		} else {
			wHauteur = wCase.getHauteur();
		}
		Vector2f wOrigin = getOrigin();
		Vector2f wW = new Vector2f(0, -VCase.DEPL_HAUTEUR);
		for (int wI = 0; wI < wHauteur; wI++) {
			wOrigin = Vector2f.add(wOrigin, wW);
		}
		float wHauteurTotale = VCase.HAUTEUR;
		if (this.pVBot != null) {
			wHauteurTotale = wHauteurTotale + VBot.HAUTEUR;
			wOrigin = Vector2f.sub(wOrigin, new Vector2f(0, VBot.HAUTEUR));
		}
		if (new FloatRect(wOrigin.x, wOrigin.y, VCase.LARGEUR, wHauteurTotale).contains(aPosition)) {
			/* Pas besoin de vérifier les zones mortes */
			return true;
		}
		return false;
	}

	@Override
	public void initView() {
		List<Case> wCases = this.pCellule.getCases();
		FloatRect wZone = new FloatRect(0, 0, getWidth(), getHeight());
		this.pCourant = this.pCellule.getCourant();
		this.pVCases = new LinkedList<VCase>();
		for (Case wCase : wCases) {
			this.pVCases.add(VCase.makeVCase(wCase, wZone));
		}
		setBot(this.pVBot);
		updateCourant();
	}

	/**
	 * Définit le VBot à dessiner dans la cellule
	 *
	 * @param aVBot
	 *            VBot à dessiner dans la case si necessaire
	 */
	public void setBot(VBot aVBot) {
		if (aVBot != null) {
			this.pVBot = aVBot;
			Case wCase = this.pCellule.getCase();
			if (wCase != null) {
				this.pVBot.setZone(new FloatRect(VBot.DEPL_X, wCase.getHauteur() * -VCase.DEPL_HAUTEUR
						+ -VBot.DEPL_Y, this.pVBot.getWidth(), this.pVBot.getHeight()));
			}
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
		if (aString.equals("cellule_switch")) {
			clearView();
			initView();
		}
		if (aString.equals("cellule_reset")) {
			clearView();
			initView();
		}
	}

	/**
	 * Mise à jour de la cellule
	 */
	public void updateCourant() {
		clearView();
		if (this.pVCases.isEmpty() == false) {
			addView(this.pVCases.get(this.pCourant - 1));
		}
		if (this.pVBot != null) {
			addView(this.pVBot);
		}
	}
}
