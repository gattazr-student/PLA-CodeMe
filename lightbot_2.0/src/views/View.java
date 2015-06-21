package views;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

/**
 * View. Objet graphique de base. Cet Objet peut être dessiné et/ou être un conteneur pour d'autre objets View
 *
 */
public abstract class View implements Drawable {

	/** List des éléments dessinés dans cet élément view */
	private List<View> pViews;
	/**
	 * Zone utilisé par la view. Décrit la position dans la vue container supérieur et la taille de la View
	 * courante
	 */
	private FloatRect pZone;

	/**
	 * Création d'un objet view vide placé en 0,0 et de taille 0,0.
	 */
	public View() {
		this.pViews = new LinkedList<View>();
		this.pZone = new FloatRect(0, 0, 0, 0);
	}

	/**
	 * Création d'un objet View à la position et la taille donnée
	 *
	 * @param aRect
	 *            position et taille de la View
	 */
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
	 * Vide la vue
	 */
	public void clearView() {
		this.pViews.clear();
	}

	/**
	 * Retourne true si la position passé en paramètre est contenue dans la vue. False sinon
	 *
	 * @param aPosition
	 *            position à tester
	 * @return true si la position passé en paramètre est contenue dans la vue. False sinon
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
	 * Retourne l'objet View qui a été la cible du click
	 *
	 * @param aPosition
	 *            position du click dans la View
	 * @return View qui a été clické
	 */
	public View isClickedOn(Vector2f aPosition) {
		ListIterator<View> wIterator = this.pViews.listIterator(this.pViews.size());
		View wView;
		while (wIterator.hasPrevious()) {
			wView = wIterator.previous();
			if (wView.contains(aPosition)) {
				Vector2f wNewPos = Vector2f.sub(aPosition, wView.getOrigin());
				return wView.isClickedOn(wNewPos);
			}
		}
		return this;
	}

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
