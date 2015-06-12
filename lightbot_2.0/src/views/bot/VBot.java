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

	public static float DEPL_X = 19;
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

	@Override
	public void initView() {
		Texture wTexture = new Texture();
		Sprite wSprite = new Sprite();
		try {
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("res/lightbot/bot_");
			switch (this.pBot.getCouleur()) {
			case BLANC:
				wStringBuilder.append("BLANC_");
				break;
			case ROUGE:
				wStringBuilder.append("ROUGE_");
				break;
			case VERT:
				wStringBuilder.append("VERT_");
				break;
			}

			switch (this.pBot.getOrientation()) {
			case NORD:
				wStringBuilder.append("NORD");
				break;
			case SUD:
				wStringBuilder.append("SUD");
				break;
			case EST:
				wStringBuilder.append("EST");
				break;
			case OUEST:
				wStringBuilder.append("OUEST");
				break;
			}
			wStringBuilder.append(".png");
			wTexture.loadFromFile(Paths.get(wStringBuilder.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wSprite.setTexture(wTexture);
		this.pSprite = wSprite;
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub
		if (aString.equals("bot_couleur")) {
			clearView();
			initView();
		}
		if (aString.equals("bot_orientation")) {
			clearView();
			initView();
		}
	}
}
