package views.action;

import java.io.IOException;
import java.nio.file.Paths;

import models.action.Route;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;

public class VRoute extends VAction {

	private Route pRoute;

	public VRoute(Route aRoute, FloatRect aZone) {
		super(aZone);
		this.pRoute = aRoute;
		this.pRoute.addObserver(this);
		initView();
	}

	@Override
	public void setTexture() {
		if (this.pRoute != null && getSprite() != null) {
			Texture wTexture = new Texture();
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/action/route_");
			wStringBuilder.append(this.pRoute.getName());

			switch (this.pRoute.getCouleur()) {
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
