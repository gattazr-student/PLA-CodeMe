package views.niveau;

import java.io.IOException;
import java.nio.file.Paths;

import models.niveau.CaseBasique;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class VCaseBasique extends VCase {

	private CaseBasique pCaseBasique;

	public VCaseBasique(CaseBasique aCaseBasique) {
		this.pCaseBasique = aCaseBasique;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		Texture wTexture = new Texture();
		try {
			wTexture.loadFromFile(Paths.get("res/cases/CASE_BLANC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite wSprite = new Sprite();
		wSprite.setTexture(wTexture);

		Vector2f wOrigin = aState.transform.transformPoint(new Vector2f(0, 0));

		wSprite.setPosition(wOrigin);
		aTarget.draw(wSprite);
		/* pour affichage de la hauteur */
		Vector2f wW = new Vector2f(0, -VCase.DEPL_Z);
		for (int wI = 0; wI < this.pCaseBasique.getHauteur(); wI++) {
			wOrigin = Vector2f.add(wOrigin, wW);
			wSprite.setPosition(wOrigin);
			aTarget.draw(wSprite);
		}
	}

}
