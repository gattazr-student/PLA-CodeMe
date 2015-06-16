package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Sauter;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

public class VSauter extends VAction {

	private Sauter pSauter;

	public VSauter(Sauter aSauter, FloatRect aZone) {
		super(aZone);
		this.pSauter = aSauter;
		this.pSauter.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (this.pSauter != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/sauter");

			switch (this.pSauter.getCouleur()) {
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
		// TODO Auto-generated method stub

	}

}
