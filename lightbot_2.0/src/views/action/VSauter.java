package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Sauter;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

public class VSauter extends VAction {

	public VSauter(Sauter aSauter, FloatRect aZone) {
		super(aSauter, aZone);
		aSauter.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (getAction() != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/sauter");

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
