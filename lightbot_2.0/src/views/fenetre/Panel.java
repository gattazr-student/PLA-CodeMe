package views.fenetre;

import org.jsfml.graphics.FloatRect;

import views.View;

/**
 * Panel est un espace de dessin dans une fênetre. Il est repéré par un rectangle
 *
 */
public class Panel extends View {

	/**
	 * Créé un panel
	 *
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
	}

}
