package views.niveau;

import models.basic.Position;
import models.niveau.Carte;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public class VCarte implements Drawable {

	private Carte pCarte;

	public VCarte(Carte aCarte) {
		this.pCarte = aCarte;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		Transform wNewTransform;
		RenderStates wNewState;
		Position wPosition;
		int wMaxX = this.pCarte.getMaxX();
		int wMaxY = this.pCarte.getMaxY();
		for (int wX = 0; wX < wMaxX; wX++) {
			for (int wY = 0; wY < wMaxY; wY++) {
				wPosition = new Position(wX, wY);
				VCase wCase = VCase.getVCase(this.pCarte.getCase(wPosition));
				if (wCase != null) {
					wNewTransform = Transform.translate(new Transform(), VCase.deplacementCase(wPosition));
					wNewState = new RenderStates(aState.blendMode, Transform.combine(aState.transform,
							wNewTransform), aState.texture, aState.shader);
					aState.transform.transformPoint(new Vector2f(0, 0));
					wCase.draw(aTarget, wNewState);
				}
			}
		}
	}
}
