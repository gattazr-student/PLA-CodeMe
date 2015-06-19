package views.jsfml;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Surcouche à la bibliothèque JSFML permettant la gestion de textes
 *
 */
public class VTexte extends View {

	private String pMessageAfficher;
	private Text pTexte;
	Font wFont = new Font();
	private int pTailleTexte;

	public VTexte(FloatRect aZone, String aMessageAfficher, int aTailleTexte) {
		setZone(aZone);
		this.pMessageAfficher = aMessageAfficher;
		this.pTailleTexte = aTailleTexte;
		initView();
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pTexte == null) {
			initView();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		RenderStates wNewState = new RenderStates(aState.blendMode, Transform.combine(wTranslation,
				aState.transform), aState.texture, aState.shader);
		this.pTexte.draw(aTarget, wNewState);
	}

	public FloatRect getLocalBounds() {
		return this.pTexte.getLocalBounds();
	}

	@Override
	public void initView() {
		try {
			this.wFont.loadFromFile(Paths.get("res/font/BlackKids.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Text wTexte = new Text(this.pMessageAfficher, this.wFont, this.pTailleTexte);
		wTexte.setColor(Color.BLUE);

		Vector2f wOrigin = getOrigin();
		wTexte.setPosition(wOrigin);
		this.pTexte = wTexte;
	}

	public void setColor(Color aColor) {
		this.pTexte.setColor(aColor);
	}
}
