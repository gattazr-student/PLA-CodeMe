package views.action;

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
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;

import views.View;

public abstract class VAction extends View implements Observer {

	public static final int HAUTEUR = 55;
	public static final int LARGEUR = 55;

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

	private Action pAction;
	private Sprite pSprite;

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
		RenderStates wNewState = new RenderStates(aState.blendMode, Transform.combine(wTranslation,
				aState.transform), aState.texture, aState.shader);
		this.pSprite.draw(aTarget, wNewState);
	}

	public Action getAction() {
		return this.pAction;
	}

	public Sprite getSprite() {
		return this.pSprite;
	}

	@Override
	public void initView() {
		this.pSprite = new Sprite();
		setTexture();
	}

	public void setCouleur(Couleur aCouleur) {
		this.pAction.setCouleur(aCouleur);
		setTexture();
	}

	public void setSprite(Sprite aSprite) {
		this.pSprite = aSprite;
	}

	public abstract void setTexture();

}
