package views.action;

import models.action.Action;
import models.action.Allumer;
import models.action.Attendre;
import models.action.Avancer;
import models.action.Break;
import models.action.Divise;
import models.action.Douche;
import models.action.Notify;
import models.action.Route;
import models.action.Sauter;
import models.action.TestAvancer;
import models.action.TestSauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.basic.Couleur;
import mvc.Observer;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Représentation graphique d'une Action
 *
 */
public abstract class VAction extends View implements Observer {
	/** Hauteur des sprites VAction */
	public static final int HAUTEUR = 55;
	/** Largeur des sprites VAction */
	public static final int LARGEUR = 55;

	/**
	 * Création d'une VAction représentant l'Action passé en paramètre
	 *
	 * @param aAction
	 *            Action à représenter
	 * @param aZone
	 *            Zone ou positionner la VAction
	 * @return VAction créée
	 */
	public static VAction makeVAction(Action aAction, FloatRect aZone) {
		if (aAction instanceof Allumer) {
			return new VAllumer((Allumer) aAction, aZone);
		}
		if (aAction instanceof Attendre) {
			return new VAttendre((Attendre) aAction, aZone);
		}
		if (aAction instanceof Avancer) {
			return new VAvancer((Avancer) aAction, aZone);
		}
		if (aAction instanceof Break) {
			return new VBreak((Break) aAction, aZone);
		}
		if (aAction instanceof Divise) {
			return new VDivise((Divise) aAction, aZone);
		}
		if (aAction instanceof Douche) {
			return new VDouche((Douche) aAction, aZone);
		}
		if (aAction instanceof Notify) {
			return new VNotify((Notify) aAction, aZone);
		}
		if (aAction instanceof Route) {
			return new VRoute((Route) aAction, aZone);
		}
		if (aAction instanceof Sauter) {
			return new VSauter((Sauter) aAction, aZone);
		}
		if (aAction instanceof TestAvancer) {
			return new VTestAvancer((TestAvancer) aAction, aZone);
		}
		if (aAction instanceof TestSauter) {
			return new VTestSauter((TestSauter) aAction, aZone);
		}
		if (aAction instanceof TournerDroite) {
			return new VTournerDroite((TournerDroite) aAction, aZone);
		}
		if (aAction instanceof TournerGauche) {
			return new VTournerGauche((TournerGauche) aAction, aZone);
		}
		return null;
	}

	/** Action représenté */
	private Action pAction;
	/** Sprite de la VAction */
	private Sprite pSprite;

	/**
	 * Création d'une VAction à partir d'une Action et d'une zone
	 *
	 * @param aAction
	 *            Action représenté
	 * @param aZone
	 *            position et taille de la VActions
	 */
	public VAction(Action aAction, FloatRect aZone) {
		setZone(aZone);
		this.pAction = aAction;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pSprite == null) {
			initView();
			this.pSprite = getSprite();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		Transform wNewTransform = Transform.combine(wTranslation, aState.transform);
		RenderStates wNewState = new RenderStates(aState.blendMode, wNewTransform, aState.texture,
				aState.shader);
		this.pSprite.draw(aTarget, wNewState);
		int wI = 0;
		for (String aName : getAction().getBotsCourant()) {
			CircleShape wCircle = new CircleShape(5);
			switch (aName) {
			case "minion1":
				wCircle.setFillColor(Color.YELLOW);
				break;
			case "minion2":
				wCircle.setFillColor(Color.MAGENTA);
				break;
			default:
				wCircle = null;
			}
			if (wCircle != null) {
				wCircle.setPosition(new Vector2f(5 + wI * 10, 5));
				wCircle.draw(aTarget, wNewState);
				wI++;
			}
		}
	}

	/**
	 * Retourne l'Action qui est représenté
	 *
	 * @return l'Action qui est représenté
	 */
	public Action getAction() {
		return this.pAction;
	}

	/**
	 * Retourne le Sprite utilisé pour l'affichage graphique
	 *
	 * @return le Sprite utilisé pour l'affichage graphique
	 */
	public Sprite getSprite() {
		return this.pSprite;
	}

	@Override
	public void initView() {
		this.pSprite = new Sprite();
		setTexture();
	}

	/**
	 * Définit la couleur de la VAction
	 *
	 * @param aCouleur
	 *            nouvelle Couleur de l'Action
	 */
	public void setCouleur(Couleur aCouleur) {
		this.pAction.setCouleur(aCouleur);
		setTexture();
	}

	/**
	 * Définit la Sprite à utiliser pour l'affichage
	 *
	 * @param aSprite
	 *            nouveau Sprite à utiliser
	 */
	public void setSprite(Sprite aSprite) {
		this.pSprite = aSprite;
	}

	/**
	 * Définition de la Structure à utiliser pour le Sprite
	 */
	public abstract void setTexture();

	@Override
	public void update(String aString, Object aObjet) {
	}

}
