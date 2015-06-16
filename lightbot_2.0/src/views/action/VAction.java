package views.action;

import models.action.Action;
import models.action.Allumer;
import models.action.Attendre;
import models.action.Avancer;
import models.action.Divise;
import models.action.Route;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;

import views.View;

public abstract class VAction extends View implements Observer {

	public static final int HAUTEUR = 59;
	public static final int LARGEUR = 59;

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
		if (aAction instanceof Divise) {
			return new VDivise((Divise) aAction, aZone);
		}
		if (aAction instanceof Route) {
			return new VRoute((Route) aAction, aZone);
		}
		if (aAction instanceof Sauter) {
			return new VSauter((Sauter) aAction, aZone);
		}
		if (aAction instanceof TournerDroite) {
			return new VTournerDroite((TournerDroite) aAction, aZone);
		}
		if (aAction instanceof TournerGauche) {
			return new VTournerGauche((TournerGauche) aAction, aZone);
		}
		return null;
	}

	private Sprite pSprite;

	public VAction(FloatRect aZone) {
		setZone(aZone);
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

	public Sprite getSprite() {
		return this.pSprite;
	}

	@Override
	public void initView() {
		this.pSprite = new Sprite();
		setTexture();
	}

	public void setSprite(Sprite aSprite) {
		this.pSprite = aSprite;
	}

	public abstract void setTexture();

}
