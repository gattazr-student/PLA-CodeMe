package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Cellule;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import views.View;
import views.bot.VBot;

/**
 * Représentation graphique d'une Carte
 *
 */
public class VCarte extends View implements Observer {

	/** Carte */
	private Carte pCarte;
	/** Liste des Bots représenté dans la carte */
	private List<Bot> pBots;
	/** Liste des VBots utilisé dans la carte */
	private List<VBot> pVBots;

	/**
	 *
	 * @param aCarte
	 *            Carte à représenter
	 * @param aBots
	 *            Liste des Bots à représenter
	 * @param aZone
	 *            Position et taille de la VCarte
	 */
	public VCarte(Carte aCarte, List<Bot> aBots, FloatRect aZone) {
		super(aZone);
		this.pCarte = aCarte;
		this.pBots = aBots;
		this.pCarte.addObserver(this);
		for (Bot wBot : this.pBots) {
			wBot.addObserver(this);
		}
		initView(); // initialise la vue
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		/* Retourne vrai si le click est sur la carte ou les 100 pixels au dessus */
		Vector2f wOrigin = getOrigin();
		FloatRect wFloatRect = new FloatRect(wOrigin.x, wOrigin.y - 100, getWidth(), getHeight() + 100);
		return wFloatRect.contains(aPosition);
	}

	@Override
	public void initView() {
		this.pVBots = new LinkedList<VBot>();
		for (Bot wBot : this.pBots) {
			this.pVBots.add(new VBot(wBot, new FloatRect(0, 0, VBot.LARGEUR, VBot.HAUTEUR)));
		}
		VBot wVBot;
		Position wPosition;
		Vector2f wDepl;
		float wFreeX = getWidth()
				- ((this.pCarte.getMaxY() + this.pCarte.getMaxX()) * VCase.DIAG_HORIZONTALE / 2)
				- VCase.DIAG_HORIZONTALE;
		float wFirstX = wFreeX / 2 + this.pCarte.getMaxY() * VCase.DIAG_HORIZONTALE / 2;

		float wFreeY = getHeight()
				- ((this.pCarte.getMaxY() + this.pCarte.getMaxX()) * VCase.DIAG_VERTICALE / 2);
		float wFirstY = wFreeY / 2;
		Vector2f wPositionFirst = new Vector2f(wFirstX, wFirstY);
		int wMaxX = this.pCarte.getMaxX();
		int wMaxY = this.pCarte.getMaxY();
		for (int wX = 0; wX < wMaxX; wX++) {
			for (int wY = 0; wY < wMaxY; wY++) {
				wPosition = new Position(wX, wY);
				wDepl = Vector2f.add(wPositionFirst, VCase.deplacementCase(wPosition));
				wVBot = null;
				for (int wI = 0; wI < this.pVBots.size(); wI++) {
					Bot wParcours = this.pBots.get(wI);
					if (wParcours.getPosition().equals(wPosition)) {
						wVBot = this.pVBots.get(wI);
						break;
					}
				}
				Cellule wCellule = this.pCarte.getCellule(wPosition);
				VCellule wVCellule = new VCellule(wCellule, wVBot, new FloatRect(wDepl.x, wDepl.y,
						VCase.LARGEUR, VCase.HAUTEUR));
				addView(wVCellule);

			}
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
		if (aString.equals("carte_switch")) {
			clearView();
			initView();
		}
		if (aString.equals("bot_position")) {
			clearView();
			initView();
		}
		if (aString.equals("carte_reset")) {
			clearView();
			initView();
		}
		if (aString.equals("bot_reset")) {
			clearView();
			initView();
		}
	}
}
