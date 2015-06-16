package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Avancer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

public class VAvancer extends VAction {

	private Avancer pAvancer;

	public VAvancer(Avancer aAvancer, FloatRect aZone) {
		super(aZone);
		this.pAvancer = aAvancer;
		this.pAvancer.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (this.pAvancer != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/avancer");

			switch (this.pAvancer.getCouleur()) {
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
