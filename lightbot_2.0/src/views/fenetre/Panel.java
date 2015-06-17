package views.fenetre;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;

import views.View;
import views.jsfml.VRectangle;

/**
 * Panel est un espace de dessin dans une fênetre. Il est uniquement repéré par
 * son origine (le coin haut gauche)
 *
 */
public class Panel extends View {

	/**
	 * Créé un panel dans une fenêtre donnée
	 *
	 * @param aWindow
	 *            RenderWindow dans lequel sera le Panel
	 * @param aZone
	 *            Rectangle dans la fenêtre définissant le Panel
	 */
	public Panel(FloatRect aZone) {
		setZone(aZone);
		initView();
	}

	@Override
	public void clearView() {
		super.clearView();
		initView();
	}

	@Override
	public void initView() {
		VRectangle wRect = new VRectangle(new FloatRect(0, 0, getWidth(), getHeight()));
		wRect.setFillColor(Color.TRANSPARENT);
		wRect.setOutlineColor(Color.WHITE);
		wRect.setOutlineThickness(3);
		addView(wRect);
	}

}
