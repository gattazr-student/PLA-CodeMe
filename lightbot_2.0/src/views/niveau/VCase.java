package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;
import models.niveau.Case;
import models.niveau.CaseBasique;
import models.niveau.CaseInterrupteur;
import models.niveau.CaseLampe;
import models.niveau.CaseVide;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

public abstract class VCase extends View implements Observer {

	public static final float DIAG_HORIZONTALE = 70;
	public static final float DIAG_VERTICALE = 37;
	public static final float DEPL_HAUTEUR = 18;
	public static final float HAUTEUR = 74;
	public static final float LARGEUR = 55;

	public static Vector2f deplacementCase(Position aPosition) {
		/* Déplacement selon u */
		Transform wU = Transform.translate(new Transform(),
				new Vector2f(DIAG_HORIZONTALE / 2 * aPosition.getX(), ((int) (DIAG_VERTICALE / 2))
						* aPosition.getX()));
		/* Déplacement selon v */
		Transform wV = Transform.translate(new Transform(),
				new Vector2f(-DIAG_HORIZONTALE / 2 * aPosition.getY(), ((int) (DIAG_VERTICALE / 2))
						* aPosition.getY()));

		/* Déplacement combiné */
		Transform wFinalTransform = Transform.combine(wU, wV);
		Vector2f wDepl = new Vector2f(0, 0);
		wDepl = wFinalTransform.transformPoint(wDepl);

		return wDepl;
	}

	/**
	 * Retourne une case contenant une méthode draw à partir d'une case du Modèle
	 *
	 * @param aCase
	 *            Case
	 * @return VCase
	 */
	public static VCase makeVCase(Case aCase, FloatRect aZone) {
		if (aCase instanceof CaseBasique) {
			return new VCaseBasique((CaseBasique) aCase, aZone);
		}
		if (aCase instanceof CaseLampe) {
			return new VCaseLampe((CaseLampe) aCase, aZone);
		}
		if (aCase instanceof CaseInterrupteur) {
			return new VCaseInterrupteur((CaseInterrupteur) aCase, aZone);
		}
		if (aCase instanceof CaseVide) {
			return new VCaseVide((CaseVide) aCase, aZone);
		}
		return null;
	}

	private int pHauteur;

	private List<Sprite> pSprites;

	public VCase(FloatRect aZone) {
		this.pSprites = new LinkedList<Sprite>();
		setZone(aZone);
	}

	public void addSprite(Sprite aSprite) {
		this.pSprites.add(aSprite);
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		/* On a déja vérifié la hauteur et les zones mortes */
		return true;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pSprites == null) {
			initView();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		new RenderStates(aState.blendMode, Transform.combine(wTranslation, aState.transform), aState.texture,
				aState.shader);
		for (Sprite wSprite : this.pSprites) {
			wSprite.draw(aTarget, aState);
		}
	}

	public int getHauteur() {
		return this.pHauteur;
	}

	public void setHauteur(int aHauteur) {
		this.pHauteur = aHauteur;
	}
}
