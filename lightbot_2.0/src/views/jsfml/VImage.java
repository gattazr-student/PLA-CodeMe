package views.jsfml;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Surcouche de la bibliothèque JSFML permettant la gestion d'images
 *
 */
public class VImage extends View {
	/** chemin vers le fichier contenant la texture du Bouton */
	private String pTextureFileName;
	/** Sprite du Bouton */
	private Sprite pSprite;

	/**
	 *
	 * @param aZone
	 *            Position et taille de la VImage
	 * @param aTextureFileName
	 *            chemin vers le fichier contenant la texture
	 */
	public VImage(FloatRect aZone, String aTextureFileName) {
		setZone(aZone);
		this.pTextureFileName = aTextureFileName;
		initView();
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		return false;
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
		try {
			wTexture.loadFromFile(Paths.get(this.pTextureFileName), new IntRect(0, 0, (int) getWidth(),
					(int) getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite wSprite = new Sprite();
		Vector2f wOrigin = getOrigin();
		wSprite.setTexture(wTexture);

		wSprite.setPosition(wOrigin);
		this.pSprite = wSprite;

	}

}
