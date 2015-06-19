package views.jsfml;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Surcouche à la bibliothèque JSFML permettant la gestion des rectangles
 *
 */
public class VRectangle extends View {

	/** Rectangle JSFML */
	RectangleShape pRectangle;

	/**
	 *
	 * @param aZone
	 *            Position et taille du rectangle
	 */
	public VRectangle(FloatRect aZone) {
		setZone(aZone);
		initView();
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		return false;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		/* Dessine un rectangle à la position */
		if (this.pRectangle == null) {
			initView();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		RenderStates wNewState = new RenderStates(aState.blendMode, Transform.combine(wTranslation,
				aState.transform), aState.texture, aState.shader);
		this.pRectangle.draw(aTarget, wNewState);
	}

	@Override
	public void initView() {
		this.pRectangle = new RectangleShape();
		this.pRectangle.setPosition(0, 0);
		this.pRectangle.setSize(new Vector2f(getWidth(), getHeight()));
	}

	/**
	 * Définit la couleur interne du rectangle
	 *
	 * @param aColor
	 *            nouvelle Couleur interne du Rectangle
	 */
	public void setFillColor(Color aColor) {
		if (this.pRectangle == null) {
			initView();
		}
		this.pRectangle.setFillColor(aColor);
	}

	/**
	 * Définit la couleur du trait extérieur du rectangle
	 *
	 * @param aColor
	 *            nouvelle couleur
	 */
	public void setOutlineColor(Color aColor) {
		if (this.pRectangle == null) {
			initView();
		}
		this.pRectangle.setOutlineColor(aColor);
	}

	/**
	 * Définit l'épaisseur du trait extérieur du rectangle
	 *
	 * @param aThickness
	 *            nouvelle épaisseur
	 */
	public void setOutlineThickness(float aThickness) {
		if (this.pRectangle == null) {
			initView();
		}
		this.pRectangle.setOutlineThickness(aThickness);
		;
	}
}
