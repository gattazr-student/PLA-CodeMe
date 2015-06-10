package views.fenetre;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

/**
 * Panel est un espace de dessin dans une fênetre. Il est uniquement repéré par
 * son origine (le coin haut gauche)
 *
 */
public class Panel {

	private RenderWindow pWindow;
	private Vector2f pOrigin;
	private float pWidth;
	private float pHeight;

	/**
	 * Créé un panel dans une fenêtre donnée à l'origin donnée
	 *
	 * @param aWindow
	 *            RenderWindow dans lequel sera le panel
	 * @param aOrigin
	 *            origine du panel
	 */
	public Panel(RenderWindow aWindow, Vector2f aOrigin, float aWidth, float aHeight) {
		this.pWindow = aWindow;
		this.pOrigin = aOrigin;
		this.pWidth = aWidth;
		this.pHeight = aHeight;
	}

	/**
	 * Déssine un drawable dans le panel. La position du Drawable est relative
	 * dans le Panel
	 *
	 * @param aDrawable
	 *            Drawable à dessiner
	 * @param aState
	 *            TODO
	 * @param aState
	 *            Etat du drawable
	 */
	public void draw(Drawable aDrawable, RenderStates aState) {

		Transform wTranslation = Transform.translate(new Transform(), this.pOrigin);
		Transform wTransformation = Transform.combine(wTranslation, aState.transform);
		aDrawable.draw(this.pWindow, new RenderStates(wTransformation));
	}

	/**
	 * Retourne la heuteur du Panel
	 * 
	 * @return hauteur du Panel
	 */
	public float getHeight() {
		return this.pHeight;
	}

	/**
	 * Retourne les coordonnées du point origine du Panel
	 *
	 * @return coordonnées du point origine du Panel
	 */
	public Vector2f getOrigin() {
		return this.pOrigin;
	}

	/**
	 * Retourne la largeur du Panel
	 *
	 * @return largeur du Panel
	 */
	public float getWidth() {
		return this.pWidth;
	}
}
