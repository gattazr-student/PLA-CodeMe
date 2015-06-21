package views.menu;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

import views.View;
import views.fenetre.Fenetre;
import views.fenetre.Panel;
import views.jsfml.VBouton;
import controllers.engine.Engine;

public class SelectLevel extends View {

	public static void main(String[] aArgs) {
		RenderWindow aWindow = new RenderWindow();
		aWindow = Fenetre.FENETRE;
		SelectLevel wViewSelect = new SelectLevel(Fenetre.FENETRE);
		wViewSelect.runSelect(aWindow);
		System.out.println("Goodbye World!");

	}

	private Panel pPanelEntete;
	private Panel pPanelWorld1;
	private Panel pPanelWorld2;
	private Panel pPanelWorld3;

	/**
	 * Constructeur permettant d'initialiser tous les panels
	 *
	 * @param aWindow
	 *            : une fenêtre
	 */
	public SelectLevel(RenderWindow aWindow) {

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
	public void initEntete(String aNomBoutonEntete, String aNomSpriteEntete, String aNomBoutonBack,
			String aNomSpriteBack) {

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

		VBouton wButton_Back = new VBouton(new FloatRect(0, 0, wWidthBack, wHeightBack), aNomBoutonBack,
				aNomSpriteBack);
		this.pPanelEntete.addView(wButton_Back);

	}

	/**
	 * Fonction permettant de générer tout les objets présents dans la fenêtre
	 */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initEntete("Select", "res/menu/selectionNiveau.gif", "Back", "res/menu/Retour.gif");
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

		VBouton wTab[] = new VBouton[aNbLevel];

		if (aPanel == this.pPanelWorld1) {
			// Creation icone du theme instruction conditionnelle
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_if_n.png");
			aPanel.addView(wButton_Icone);

		} else if (aPanel == this.pPanelWorld2) {
			// Creation icone du theme modification de la carte
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_modif_n.png");
			aPanel.addView(wButton_Icone);

		} else {
			// Creation icone du theme duo
			VBouton wButton_Icone = new VBouton(new FloatRect(wMargeIcone, wEntete, wXIcone, wYIcone),
					"IconeItem", "res/menu/sprite_duo_n.png");
			aPanel.addView(wButton_Icone);
		}

		// Création des boutons selection
		for (int i = 1; i <= aNbLevel; i++) {
			wTab[i - 1] = new VBouton(LevelIconePosition(aPanel, i), "Level".concat(Integer.toString(i)),
					"res/menu/sprite_".concat(Integer.toString(i)).concat("_n.png"));
			aPanel.addView(wTab[i - 1]);
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
	public void redraw(RenderWindow aWindow) {
		// TODO Auto-generated method stub
		aWindow.clear();
		draw(aWindow, new RenderStates(new Transform()));
		aWindow.display();
	}

	/**
	 * Fonction permettant d'effectuer la selection du niveau
	 *
	 * @param aWindow
	 */
	public void runSelect(RenderWindow aWindow) {
		this.pPanelWorld1.getHeight();
		redraw(aWindow);
		float wWidthBack = 129;
		float wHeightBack = 127;

		// Limite le framerate
		aWindow.setFramerateLimit(30);
		while (aWindow.isOpen()) {

			// Gère les events
			for (Event wEvent : aWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					aWindow.close();
				}
				if (wEvent.type == Event.Type.RESIZED) {
					redraw(aWindow);
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					if (wMouseEvent.button == Button.LEFT) {
						Vector2f pos = new Vector2f(wMouseEvent.position);

						// Définition zone de clic pour bouton back
						// pour delimiter la zone
						float wXCentre = this.pPanelEntete.isClickedOn(pos).getOrigin().x + (wWidthBack / 2);
						float wYCentre = this.pPanelEntete.isClickedOn(pos).getOrigin().x + (wHeightBack / 2);
						System.out.println(wXCentre + " " + wYCentre + " " + pos);
						if (this.pPanelEntete.contains(pos)
								&& (Math.sqrt((pos.x - wXCentre) * (pos.x - wXCentre) + (wYCentre - pos.y)) < wWidthBack / 2)) {
							pos = Vector2f.sub(pos, this.pPanelEntete.getOrigin());

							// TODO : ouvrir la fenetre de menu principal
							System.out.println("boutonback cliqué");

							// On clique dans le panel 1, on cherche quel level est selectionné
						} else if (this.pPanelWorld1.contains(pos)) {
							pos = Vector2f.sub(pos, this.pPanelWorld1.getOrigin());

							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld1, 1).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 1, "Level1", "res/menu/sprite_1_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 2).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 2, "Level2", "res/menu/sprite_2_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 3).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 3, "Level3", "res/menu/sprite_3_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 4).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 4, "Level4", "res/menu/sprite_4_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 5 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 5).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 5, "Level5", "res/menu/sprite_5_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 6 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 6).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 6, "Level6", "res/menu/sprite_6_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}

						} else if (this.pPanelWorld2.contains(pos)) {

							pos = Vector2f.sub(pos, this.pPanelWorld2.getOrigin());
							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld2, 1).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld2, 1, "Level1", "res/menu/sprite_1_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 2).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld2, 2, "Level2", "res/menu/sprite_2_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 3).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld2, 3, "Level3", "res/menu/sprite_3_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 4).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld2, 4, "Level4", "res/menu/sprite_4_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}

						} else {
							pos = Vector2f.sub(pos, this.pPanelWorld3.getOrigin());
							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld3, 1).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 1, "Level1", "res/menu/sprite_1_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 2).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 2, "Level2", "res/menu/sprite_2_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 3).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 3, "Level3", "res/menu/sprite_3_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 4).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 4, "Level4", "res/menu/sprite_4_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 5 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 5).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 5, "Level5", "res/menu/sprite_5_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}
							// Si on clique sur le niveau 1, on charge l'image 6 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 6).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld3, 6, "Level6", "res/menu/sprite_6_c.png");
								redraw(aWindow);
								runSelect(aWindow);
							}

						}
					}
				}

				if (wEvent.type == Event.Type.MOUSE_BUTTON_RELEASED) {

					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();

					if (wMouseEvent.button == Button.LEFT) {
						Vector2f pos = new Vector2f(wMouseEvent.position);

						if (this.pPanelWorld1.contains(pos)) {
							pos = Vector2f.sub(pos, this.pPanelWorld1.getOrigin());
							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld1, 1).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 1, "Level1", "res/menu/sprite_1_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level1.xml");

							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 2).contains(pos)) {
								createButtonLevel(this.pPanelWorld1, 2, "Level2", "res/menu/sprite_2_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level2.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 3).contains(pos.x, pos.y)) {
								createButtonLevel(this.pPanelWorld1, 3, "Level3", "res/menu/sprite_3_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level3.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 4).contains(pos)) {
								createButtonLevel(this.pPanelWorld1, 4, "Level4", "res/menu/sprite_4_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level4.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 5 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 5).contains(pos)) {
								createButtonLevel(this.pPanelWorld1, 5, "Level5", "res/menu/sprite_5_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level5.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 6 cliquée
							else if (LevelIconePosition(this.pPanelWorld1, 6).contains(pos)) {
								createButtonLevel(this.pPanelWorld1, 6, "Level6", "res/menu/sprite_6_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/Conditions/level6.xml");
							}

						} else if (this.pPanelWorld2.contains(pos)) {
							pos = Vector2f.sub(pos, this.pPanelWorld2.getOrigin());
							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld2, 1).contains(pos)) {
								createButtonLevel(this.pPanelWorld2, 1, "Level1", "res/menu/sprite_1_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/ModifEnvironnement/Level1.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 2).contains(pos)) {
								createButtonLevel(this.pPanelWorld2, 2, "Level2", "res/menu/sprite_2_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/ModifEnvironnement/Level2.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 3).contains(pos)) {
								createButtonLevel(this.pPanelWorld2, 3, "Level3", "res/menu/sprite_3_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/ModifEnvironnement/Level3.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld2, 4).contains(pos)) {
								createButtonLevel(this.pPanelWorld2, 4, "Level4", "res/menu/sprite_4_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/ModifEnvironnement/Level4.xml");
							}

						} else {
							pos = Vector2f.sub(pos, this.pPanelWorld3.getOrigin());
							// Si on clique sur le niveau 1, on charge l'image 1 cliquée
							if (LevelIconePosition(this.pPanelWorld3, 1).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 1, "Level1", "res/menu/sprite_1_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level1.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 2 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 2).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 2, "Level2", "res/menu/sprite_2_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level2.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 3 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 3).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 3, "Level3", "res/menu/sprite_3_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level3.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 4 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 4).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 4, "Level4", "res/menu/sprite_4_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level4.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 5 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 5).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 5, "Level5", "res/menu/sprite_5_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level5.xml");
							}
							// Si on clique sur le niveau 1, on charge l'image 6 cliquée
							else if (LevelIconePosition(this.pPanelWorld3, 6).contains(pos)) {
								createButtonLevel(this.pPanelWorld3, 6, "Level6", "res/menu/sprite_6_n.png");
								redraw(aWindow);

								// lancement du terrain selectionné
								Engine wEngine = new Engine();
								wEngine.startLevel("res/xml/MultiBots/Level6.xml");

							}
						}
					}

				}

			}
		}
	}

}
