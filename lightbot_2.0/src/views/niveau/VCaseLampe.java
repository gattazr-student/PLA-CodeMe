package views.niveau;

import java.io.IOException;
import java.nio.file.Paths;

import models.niveau.CaseLampe;
import models.niveau.CaseLampe.ETAT_LAMPE;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class VCaseLampe extends VCase {

	private CaseLampe pCaseLampe;

	public VCaseLampe(CaseLampe aCaseLampe, FloatRect aZone) {
		super(aZone);
		this.pCaseLampe = aCaseLampe;
		this.pCaseLampe.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		Texture wTexture = new Texture();
		try {
			if (this.pCaseLampe.getEtat() == ETAT_LAMPE.ALLUMEE) {
				wTexture.loadFromFile(Paths.get("res/cases/case_JAUNE.png"));
			} else {
				wTexture.loadFromFile(Paths.get("res/cases/case_BLEU.png"));
			}

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
		/* TODO: Remplacer uniquement le dernier sprite plutot que de tout recréer */
		if (aString.equals("caseLampe_etat")) {
			clearView();
			initView();
		}
	}
}
