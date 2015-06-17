package views.menu;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;

import views.View;
import views.fenetre.Panel;
import views.jsfml.VBouton;

public class SelectLevel extends View {

	private RenderWindow pWindow;
	private Panel pPanelEntete;
	private Panel pPanelWorld1;
	private Panel pPanelWorld2;
	private Panel pPanelWorld3;

	public SelectLevel(RenderWindow aWindow) {
		this.pWindow = aWindow;

		/* Création des panels */
		float wXSep, wYSep;
		Vector2i wSize = this.pWindow.getSize();

		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = wSize.x * (1 / 3); // Calcul 33,3% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.15); // Calcul 15% de la hauteur de la fenêtre

		/* Ajoute les 4 panels dans la liste des éléments de la vue */

		this.pPanelEntete = new Panel(new FloatRect(0, 0, wSize.x, wYSep));
		this.pPanelWorld1 = new Panel(new FloatRect(0, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelWorld2 = new Panel(new FloatRect(wXSep, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelWorld2 = new Panel(new FloatRect(2 * wXSep, wYSep, wXSep, wSize.y - wYSep));

		addView(this.pPanelEntete);
		addView(this.pPanelWorld1);
		addView(this.pPanelWorld2);
		addView(this.pPanelWorld3);

		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	public void initEntete(String aNomBoutonEntete, String aNomSpriteEntete, String aNomBoutonBack, String aNomSpriteBack) {

		float wWidthEntete = 479;
		float wHeightEntete = 167;

		float wWidthBack = ;
		float wHeightBack = ;
		VBouton wButton_Entete = new VBouton(new FloatRect((this.pPanelEntete.getWidth() / 2 - wWidthEntete / 2),
				(this.pPanelEntete.getHeight() / 2 - wHeightEntete / 2), wWidthEntete, wHeightEntete), aNomBoutonEntete, aNomSpriteEntete);
		this.pPanelEntete.addView(wButton_Entete);

		VBouton wButton_Back = new VBouton(new FloatRect((this.pPanelEntete.getWidth() / 2 - wWidthBack / 2),
				(this.pPanelEntete.getHeight() / 2 - wHeightBack / 2), wWidthBack, wHeightBack), aNomBoutonBack, aNomSpriteBack);
		this.pPanelEntete.addView(wButton_Entete);


	}
}
