package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Attendre;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

/**
 * Représentation graphique des Actions Attendre
 *
 */
public class VAttendre extends VAction {

	public VAttendre(Attendre aAttendre, FloatRect aZone) {
		super(aAttendre, aZone);
		aAttendre.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (getAction() != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/wait");

			switch (getAction().getCouleur()) {
			case BLANC:
				wStringBuilder.append(".png");
				break;
			case ROUGE:
				wStringBuilder.append("_ROUGE.png");
				break;
			case VERT:
				wStringBuilder.append("_VERT.png");
				break;
			}

			try {
				wTexture.loadFromFile(Paths.get(wStringBuilder.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			getSprite().setTexture(wTexture);
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
	}

}
