package views.bot;

import java.io.IOException;
import java.nio.file.Paths;

import models.bot.Bot;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.Transform;

import views.View;

public class VBot extends View implements Observer {

	public static float DEPL_X = 10;
	public static float DEPL_Y = 73;
	public static float HAUTEUR = 102;
	public static float LARGEUR = 47;

	private Bot pBot;
	private Sprite pSprite;

	public VBot(Bot aBot, FloatRect aZone) {
		super(aZone);
		this.pBot = aBot;
		this.pBot.addObserver(this);
		initView();
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pSprite == null) {
			initView();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		RenderStates wNewState = new RenderStates(aState.blendMode, Transform.combine(wTranslation,
				aState.transform), aState.texture, aState.shader);
		this.pSprite.draw(aTarget, wNewState);
	}

	public Bot getBot() {
		return this.pBot;
	}

	@Override
	public void initView() {
		Sprite wSprite = new Sprite();
		this.pSprite = wSprite;
		resetTexture();
	}

	public void resetTexture() {
		if (this.pSprite != null) {
			Texture wTexture = new Texture();

			try {
				StringBuilder wStringBuilder = new StringBuilder();
				wStringBuilder.append("res/lightbot/");
				wStringBuilder.append(this.pBot.getName());
				switch (this.pBot.getCouleur()) {
				case BLANC:
					wStringBuilder.append("_BLANC");
					break;
				case ROUGE:
					wStringBuilder.append("_ROUGE");
					break;
				case VERT:
					wStringBuilder.append("_VERT");
					break;
				}

				switch (this.pBot.getOrientation()) {
				case NORD:
					wStringBuilder.append("_NORD");
					break;
				case SUD:
					wStringBuilder.append("_SUD");
					break;
				case EST:
					wStringBuilder.append("_EST");
					break;
				case OUEST:
					wStringBuilder.append("_OUEST");
					break;
				}
				wStringBuilder.append(".png");
				wTexture.loadFromFile(Paths.get(wStringBuilder.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.pSprite.setTexture(wTexture);
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
		if (aString.equals("bot_couleur")) {
			resetTexture();
		}
		if (aString.equals("bot_orientation")) {
			resetTexture();
		}
		if (aString.equals("bot_reset")) {
			resetTexture();
		}
	}
}
