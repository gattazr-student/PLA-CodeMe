package views.niveau;

import models.basic.Position;
import models.niveau.Carte;
import models.niveau.Cellule;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import views.View;

public class VCarte extends View {

	private Carte pCarte;

	public VCarte(Carte aCarte, FloatRect aZone) {
		super(aZone);
		this.pCarte = aCarte;
		initView(); // initialise la vue
	}

	@Override
	public void initView() {
		Position wPosition;
		Vector2f wDepl;
		Vector2f wPositionFirst = new Vector2f(getWidth() / 4, 0);
		int wMaxX = this.pCarte.getMaxX();
		int wMaxY = this.pCarte.getMaxY();
		for (int wX = 0; wX < wMaxX; wX++) {
			for (int wY = 0; wY < wMaxY; wY++) {
				wPosition = new Position(wX, wY);
				wDepl = Vector2f.add(wPositionFirst, VCase.deplacementCase(wPosition));
				/* TODO: remplacer par des statiques */
				Cellule wCellule = this.pCarte.getCellule(wPosition);
				VCellule wVCellule = new VCellule(wCellule, new FloatRect(wDepl.x, wDepl.y, 78, 60));
				addView(wVCellule);
			}
		}
	}
}
