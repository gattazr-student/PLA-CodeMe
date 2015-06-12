package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Cellule;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import views.View;
import views.bot.VBot;

public class VCarte extends View {

	private Carte pCarte;
	private List<Bot> pBots;
	private List<VBot> pVBots;

	public VCarte(Carte aCarte, List<Bot> aBots, FloatRect aZone) {
		super(aZone);
		this.pCarte = aCarte;
		this.pBots = aBots;
		initView(); // initialise la vue
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
		Vector2f wPositionFirst = new Vector2f(getWidth() / 4, 0);
		int wMaxX = this.pCarte.getMaxX();
		int wMaxY = this.pCarte.getMaxY();
		for (int wX = 0; wX < wMaxX; wX++) {
			for (int wY = 0; wY < wMaxY; wY++) {
				wPosition = new Position(wX, wY);
				wDepl = Vector2f.add(wPositionFirst, VCase.deplacementCase(wPosition));
				wVBot = null;
				for (int i = 0; i < this.pVBots.size(); i++) {
					Bot wParcours = this.pBots.get(i);
					if (wParcours.getPosition().equals(wPosition)) {
						wVBot = this.pVBots.get(i);
						break;
					}
				}
				/* TODO: remplacer par des statiques */
				Cellule wCellule = this.pCarte.getCellule(wPosition);
				VCellule wVCellule = new VCellule(wCellule, wVBot, new FloatRect(wDepl.x, wDepl.y,
						VCase.LARGEUR, VCase.HAUTEUR));
				addView(wVCellule);

			}
		}
	}
}
