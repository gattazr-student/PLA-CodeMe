package views.menu;

import java.util.LinkedList;
import java.util.List;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

import views.fenetre.Fenetre;
import views.fenetre.Panel;
import views.jsfml.VBouton;
import controllers.engine.Engine;

/**
 * Vue Selection du niveau
 *
 */
public class SelectLevel extends Fenetre {

	/** Panel entête */
	private Panel pPanelEntete;
	/** Panel Monde1 */
	private Panel pPanelWorld1;
	/** Panel Monde2 */
	private Panel pPanelWorld2;
	/** Panel Monde3 */
	private Panel pPanelWorld3;

	/** Booleén retour à true lorsque l'on clicke sur le bouton Return */
	private boolean pReturn;
	/** Bouton return utilisé pour sortir de la vue */
	private VBouton pBoutonReturn;
	/** Bouton dans le panel Monde1 */
	private List<VBouton> pBoutonWorld1;
	/** Bouton dans le panel Monde2 */
	private List<VBouton> pBoutonWorld2;
	/** Bouton dans le panel Monde3 */
	private List<VBouton> pBoutonWorld3;

	/**
	 * Constructeur permettant d'initialiser tous les panels
	 *
	 * @param aWindow
	 *            : une fenêtre
	 */
	public SelectLevel(RenderWindow aWindow) {
		super(aWindow);
		/* Création des panels */
		float wXSep, wYSep;
		Vector2i wSize = aWindow.getSize();

		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = (float) (wSize.x * 0.33); // Calcul 33,3% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.15); // Calcul 15% de la hauteur de la fenêtre

		/* Ajoute les 4 panels dans la liste des éléments de la vue */

		this.pPanelEntete = new Panel(new FloatRect(0, 0, wSize.x, wYSep));
		this.pPanelWorld1 = new Panel(new FloatRect(0, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelWorld2 = new Panel(new FloatRect(wXSep, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelWorld3 = new Panel(new FloatRect(2 * wXSep, wYSep, wXSep, wSize.y - wYSep));

		addView(this.pPanelEntete);
		addView(this.pPanelWorld1);
		addView(this.pPanelWorld2);
		addView(this.pPanelWorld3);

		this.pReturn = false;
		this.pBoutonWorld1 = new LinkedList<VBouton>();
		this.pBoutonWorld2 = new LinkedList<VBouton>();
		this.pBoutonWorld3 = new LinkedList<VBouton>();
		initView();
	}

	/**
	 * Fonction de creation d'un bouton de niveau
	 *
	 * @param aPanel
	 *            : un panel
	 * @param aNbLevel
	 *            : nombre de niveau
	 * @param aNomBouton
	 *            : nom du bouton
	 * @param aNomSprite
	 *            : chemin de l'image associé
	 * @return : un objet de type VBouton
	 */
	public VBouton createButtonLevel(Panel aPanel, int aNbLevel, String aNomBouton, String aNomSprite) {

		VBouton wButton = new VBouton(LevelIconePosition(aPanel, aNbLevel), aNomBouton, aNomSprite);
		aPanel.addView(wButton);

		return wButton;
	}

	/**
	 * Fonction de realisation du panel d'entete, mise en place du bouton back et image d'entete
	 *
	 * @param aNomBoutonEntete
	 *            : nom du bouton d'entete
	 * @param aNomSpriteEntete
	 *            : chemin de l'image associée au bouton d'entête
	 * @param aNomBoutonBack
	 *            : nom du bouton de retour
	 * @param aNomSpriteBack
	 *            ; chemin de l'image associée au bouton de reotur
	 */
	public void initEntete(String aNomBoutonEntete, String aNomSpriteEntete, String aNomSpriteBack) {

		float wWidthEntete = 629;
		float wHeightEntete = 150;
		float wWidthBack = 129;
		float wHeightBack = 127;

		// Definition des boutons du panel
		VBouton wButton_Entete = new VBouton(new FloatRect(
				(this.pPanelEntete.getWidth() / 2 - wWidthEntete / 2),
				(this.pPanelEntete.getHeight() - wHeightEntete / 2) / 2, wWidthEntete, wHeightEntete),
				aNomBoutonEntete, aNomSpriteEntete);
		this.pPanelEntete.addView(wButton_Entete);

		VBouton wButton_Back = new VBouton(new FloatRect(0, 0, wWidthBack, wHeightBack), "return",
				aNomSpriteBack);
		this.pBoutonReturn = wButton_Back;
		this.pPanelEntete.addView(wButton_Back);

	}

	/**
	 * Fonction permettant de générer tout les objets présents dans la fenêtre
	 */
	@Override
	public void initView() {
		initEntete("Select", "res/menu/selectionNiveau.gif", "res/menu/Retour.gif");
		initWorld(this.pPanelWorld1, 6);
		initWorld(this.pPanelWorld2, 4);
		initWorld(this.pPanelWorld3, 6);

	}

	/**
	 * Fonction de création des objets présent dans un panel de selection du niveau. Elle crée l'image
	 * thématique ainsi que les icones de selection du monde
	 *
	 * @param aPanel
	 *            : un panel
	 * @param aNbLevel
	 *            : nombre de niveau disponible dans ce monde
	 */
	public void initWorld(Panel aPanel, int aNbLevel) {
		float wXSep = aPanel.getWidth();
		float wYSep = aPanel.getHeight();
		float wPied = (float) 0.05 * wXSep;
		float wXIcone = 246;
		float wYIcone = 207;
		float wLevelNumDim = 100;
		float wMargeIcone = (float) 0.5 * (wXSep - wXIcone); // marge relative a la premiere image du panel
		float wSpaceRemaining = wYSep - wPied - 3 * wLevelNumDim - 2 * (float) 0.01 * wXSep - wYIcone;
		float wEntete = (float) 0.55 * wSpaceRemaining; // image (wMargeIcone,wEntete)
		List<VBouton> wBoutonList;
		StringBuilder wFilePath = new StringBuilder();
		wFilePath.append("res/xml/");

		if (aPanel == this.pPanelWorld1) {
			// Creation icone du theme instruction conditionnelle
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_if_n.png");
			wBoutonList = this.pBoutonWorld1;
			wFilePath.append("Conditions/Level");
			aPanel.addView(wButton_Icone);

		} else if (aPanel == this.pPanelWorld2) {
			// Creation icone du theme modification de la carte
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_modif_n.png");
			wFilePath.append("ModifEnvironnement/Level");
			wBoutonList = this.pBoutonWorld2;
			aPanel.addView(wButton_Icone);

		} else {
			// Creation icone du theme duo
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_duo_n.png");
			wFilePath.append("MultiBots/Level");
			wBoutonList = this.pBoutonWorld3;
			aPanel.addView(wButton_Icone);
		}

		// Création des boutons selection
		for (int i = 1; i <= aNbLevel; i++) {
			VBouton wVBouton = new VBouton(LevelIconePosition(aPanel, i), wFilePath.toString().concat(
					Integer.toString(i).concat(".xml")), "res/menu/sprite_".concat(Integer.toString(i))
					.concat("_n.png"));
			wBoutonList.add(wVBouton);
			aPanel.addView(wVBouton);
		}

	}

	/**
	 * Fonction de calcul du rectangle associé à l'indice aNbLevel passé en pamaètre
	 *
	 * @param aPanel
	 *            : un panel
	 * @param aNbLevel
	 *            : nombre de niveau disponible dans ce monde
	 * @return le rectangle flottant associé à l'image du terrain
	 */
	public FloatRect LevelIconePosition(Panel aPanel, int aNbLevel) {
		float wXSep = aPanel.getWidth();
		float wYSep = aPanel.getHeight();
		float wPied = (float) 0.08 * wYSep;
		float wLevelNumDim = 100;
		float wMarge = (float) 0.5 * (wXSep - 2 * wLevelNumDim); // wMarge entre les
		// icones
		float wMargeH = (float) 0.005 * wYSep;

		FloatRect wFloatRect;
		switch (aNbLevel) {
		case 1:
			wFloatRect = new FloatRect(wXSep - 2 * wLevelNumDim - wMarge, wYSep - 3 * wLevelNumDim - 2
					* wMargeH - wPied, wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		case 2:
			wFloatRect = new FloatRect(wXSep - wLevelNumDim - wMarge, wYSep - 3 * wLevelNumDim - 2 * wMargeH
					- wPied, wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		case 3:
			wFloatRect = new FloatRect(wXSep - 2 * wLevelNumDim - wMarge, wYSep - 2 * wLevelNumDim - wMargeH
					- wPied, wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		case 4:
			wFloatRect = new FloatRect(wXSep - wLevelNumDim - wMarge, wYSep - 2 * wLevelNumDim - wMargeH
					- wPied, wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		case 5:
			wFloatRect = new FloatRect(wXSep - 2 * wLevelNumDim - wMarge, wYSep - wLevelNumDim - wPied,
					wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		case 6:
			wFloatRect = new FloatRect(wXSep - wLevelNumDim - wMarge, wYSep - wLevelNumDim - wPied,
					wLevelNumDim, wLevelNumDim);
			return wFloatRect;
		default:

		}
		return null;
	}

	/**
	 * Fonction permettant de redessiner la fenêtre
	 *
	 * @param aWindow
	 *            : une fenetre
	 */
	public void redraw() {
		RenderWindow aWindow = getWindow();
		aWindow.clear();
		draw(aWindow, new RenderStates(new Transform()));
		aWindow.display();
	}

	/**
	 * Fonction permettant d'effectuer la selection du niveau
	 *
	 * @param aWindow
	 */
	public void runSelect() {
		RenderWindow aWindow = getWindow();
		this.pPanelWorld1.getHeight();
		redraw();
		// Limite le framerate
		aWindow.setFramerateLimit(30);
		while (aWindow.isOpen() && !this.pReturn) {

			// Gère les events
			for (Event wEvent : aWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					aWindow.close();
				}
				if (wEvent.type == Event.Type.RESIZED) {
					redraw();
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					if (wMouseEvent.button == Button.LEFT) {
						Vector2f pos = new Vector2f(wMouseEvent.position);

						// Définition zone de clic pour bouton back
						// pour delimiter la zone
						if (this.pPanelEntete.contains(pos)) {
							pos = Vector2f.sub(pos, this.pPanelEntete.getOrigin());
							if (this.pBoutonReturn.contains(pos)) {
								this.pReturn = true;
							}

						} else if (this.pPanelWorld1.contains(pos)) {
							// On clique dans le panel 1, on cherche quel level est selectionné
							pos = Vector2f.sub(pos, this.pPanelWorld1.getOrigin());
							for (VBouton wBouton : this.pBoutonWorld1) {
								if (wBouton.contains(pos)) {
									new Engine(getWindow()).startLevel(wBouton.getName());
								}
							}
							redraw();
						} else if (this.pPanelWorld2.contains(pos)) {
							// On clique dans le panel 2, on cherche quel level est selectionné
							pos = Vector2f.sub(pos, this.pPanelWorld2.getOrigin());
							for (VBouton wBouton : this.pBoutonWorld2) {
								if (wBouton.contains(pos)) {
									new Engine(getWindow()).startLevel(wBouton.getName());
								}
							}
							redraw();
						} else if (this.pPanelWorld3.contains(pos)) {
							// On clique dans le panel 3, on cherche quel level est selectionné
							pos = Vector2f.sub(pos, this.pPanelWorld3.getOrigin());
							for (VBouton wBouton : this.pBoutonWorld3) {
								if (wBouton.contains(pos)) {
									new Engine(getWindow()).startLevel(wBouton.getName());
								}
							}
							redraw();
						}
					}
				}
			}
		}
	}

}
