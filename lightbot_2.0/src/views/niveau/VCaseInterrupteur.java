package views.niveau;

import java.io.IOException;
import java.nio.file.Paths;

import models.niveau.CaseInterrupteur;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class VCaseInterrupteur extends VCase {

	private CaseInterrupteur pCaseLampe;

	public VCaseInterrupteur(CaseInterrupteur aCaseInterrupteur, FloatRect aZone) {
		super(aZone);
		this.pCaseLampe = aCaseInterrupteur;
		this.pCaseLampe.addObserver(this);
		setHauteur(this.pCaseLampe.getHauteur());
		initView();
	}

	@Override
	public void initView() {
		Texture wTexture = new Texture();
		try {
			wTexture.loadFromFile(Paths.get("res/cases/case_ORANGE.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite wSprite = new Sprite();
		Vector2f wOrigin = getOrigin();
		wSprite.setTexture(wTexture);

		wSprite.setPosition(wOrigin);
		addSprite(wSprite);
		/* pour affichage de la hauteur */
		Vector2f wW = new Vector2f(0, -VCase.DEPL_HAUTEUR);
		for (int wI = 0; wI < this.pCaseLampe.getHauteur(); wI++) {
			wSprite = new Sprite();
			wOrigin = Vector2f.add(wOrigin, wW);
			wSprite.setPosition(wOrigin);
			wSprite.setTexture(wTexture);
			addSprite(wSprite);
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
	}
}
