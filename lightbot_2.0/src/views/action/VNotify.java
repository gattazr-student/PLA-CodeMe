package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Notify;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

public class VNotify extends VAction {

	private Notify pNotify;

	public VNotify(Notify aNotify, FloatRect aZone) {
		super(aZone);
		this.pNotify = aNotify;
		this.pNotify.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (this.pNotify != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/notify");

			switch (this.pNotify.getCouleur()) {
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
