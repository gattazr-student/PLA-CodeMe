package views.niveau;

import java.io.IOException;
import java.nio.file.Paths;

import models.niveau.CaseBasique;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class VCaseBasique extends VCase {

	private CaseBasique pCaseBasique;

	public VCaseBasique(CaseBasique aCaseBasique, FloatRect aZone) {
		super(aZone);
		this.pCaseBasique = aCaseBasique;
		initView();
	}

	@Override
	public void initView() {
		/* TODO: REMOVE DEBUG MESSAGE */
		Texture wTexture = new Texture();
		try {
			wTexture.loadFromFile(Paths.get("res/cases/CASE_BLANC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite wSprite = new Sprite();
		Vector2f wOrigin = getOrigin();
		wSprite.setTexture(wTexture);

		wSprite.setPosition(wOrigin);
		addSprite(wSprite);
		/* pour affichage de la hauteur */
		Vector2f wW = new Vector2f(0, -VCase.HAUTEUR);
		for (int wI = 0; wI < this.pCaseBasique.getHauteur(); wI++) {
			wSprite = new Sprite();
			wOrigin = Vector2f.add(wOrigin, wW);
			wSprite.setPosition(wOrigin);
			wSprite.setTexture(wTexture);
			addSprite(wSprite);
		}
	}
}
