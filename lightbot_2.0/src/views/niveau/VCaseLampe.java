package views.niveau;

import java.io.IOException;
import java.nio.file.Paths;

import models.niveau.CaseLampe;
import models.niveau.CaseLampe.ETAT_LAMPE;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class VCaseLampe extends VCase {

	private CaseLampe pCaseLampe;

	public VCaseLampe(CaseLampe aCaseLampe) {
		this.pCaseLampe = aCaseLampe;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		Texture wTexture = new Texture();
		Sprite wSprite = new Sprite();
		try {
			if (this.pCaseLampe.getEtat() == ETAT_LAMPE.ALLUMEE) {
				wTexture.loadFromFile(Paths.get("res/cases/case_JAUNE.png"));
			} else {
				wTexture.loadFromFile(Paths.get("res/cases/case_BLEU.png"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		wSprite.setTexture(wTexture);

		Vector2f wOrigin = aState.transform.transformPoint(new Vector2f(0, 0));

		wSprite.setPosition(wOrigin);
		aTarget.draw(wSprite);
		/* pour affichage de la hauteur */
		Vector2f wW = new Vector2f(0, -VCase.DEPL_Z);
		for (int wI = 0; wI < this.pCaseLampe.getHauteur(); wI++) {
			wOrigin = Vector2f.add(wOrigin, wW);
			wSprite.setPosition(wOrigin);
			aTarget.draw(wSprite);
		}
	}

}
