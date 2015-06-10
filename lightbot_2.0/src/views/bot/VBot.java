package views.bot;

import java.io.IOException;
import java.nio.file.Paths;

import models.bot.Bot;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import views.niveau.VCase;

public class VBot implements Drawable {

	public static float DEPL_X = 19;
	public static float DEPL_Y = 73;

	private Bot pBot;
	private int pHauteur;

	public VBot(Bot aBot, int aHauteur) {
		this.pBot = aBot;
		this.pHauteur = aHauteur; // hauteur de la case dans lequel est le bot
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
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

		Vector2f wOrigin = aState.transform.transformPoint(new Vector2f(0, 0));
		Vector2f wDeplacement = VCase.deplacementCase(this.pBot.getPosition());
		Vector2f wPositionFinal = Vector2f.add(wOrigin, wDeplacement);

		/* pour affichage de la hauteur */
		Vector2f wW = new Vector2f(0, -VCase.DEPL_Z);
		for (int wI = 0; wI < this.pHauteur; wI++) {
			wPositionFinal = Vector2f.add(wPositionFinal, wW);
		}
		/* Déplace le bot pour le placer au milieu de la case */
		wW = new Vector2f(DEPL_X, -DEPL_Y);
		wPositionFinal = Vector2f.add(wPositionFinal, wW);

		wSprite.setPosition(wPositionFinal);
		aTarget.draw(wSprite);
	}
}
