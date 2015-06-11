package views;

import java.util.LinkedList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public abstract class View implements Drawable {

	/**
	 * List des éléments dessinés dans cet élément view
	 */
	private List<View> pViews;

	/**
	 * Zone utilisé par la view dans une fenetre JSFML
	 */
	private FloatRect pZone;

	public View() {
		this.pViews = new LinkedList<View>();
		this.pZone = new FloatRect(0, 0, 0, 0);
	}

	public View(FloatRect aRect) {
		this.pViews = new LinkedList<View>();
		this.pZone = aRect;
	}

	/**
	 * Ajoute un élément dans la liste des View contenues dans la View. L'élément est ajouté en fin de liste
	 *
	 * @param aView
	 *            View à rajouter
	 */
	public void addView(View aView) {
		this.pViews.add(aView);
	}

	/**
	 * Retourne vrai si la position passé en paramètre est contenue dans la vue
	 *
	 * @param aPosition
	 * @return
	 */
	public boolean contains(Vector2f aPosition) {
		return this.pZone.contains(aPosition);
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		RenderStates wNewState = new RenderStates(aState.blendMode, Transform.combine(wTranslation,
				aState.transform), aState.texture, aState.shader);
		for (View wView : this.pViews) {
			wView.draw(aTarget, wNewState);
		}
	}

	/**
	 * Retourne la hauteur de la View
	 *
	 * @return hauteur de la View
	 */
	public float getHeight() {
		return this.pZone.height;
	}

	/**
	 * Retourne les coordonnées du point origine de la View
	 *
	 * @return coordonnées du point origine de la View
	 */
	public Vector2f getOrigin() {
		return new Vector2f(this.pZone.left, this.pZone.top);
	}

	/**
	 * Retourne la largeur de la View
	 *
	 * @return largeur de la View
	 */
	public float getWidth() {
		return this.pZone.width;
	}

	/**
	 * Initialise la View
	 */
	public abstract void initView();

	/**
	 * Définit la zone de la vue
	 *
	 * @param aZone
	 *            nouvelle zone de la vue à définir
	 */
	public void setZone(FloatRect aZone) {
		this.pZone = aZone;
	}
}
