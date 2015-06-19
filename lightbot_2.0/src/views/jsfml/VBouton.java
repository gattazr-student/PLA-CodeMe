package views.jsfml;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Surcouche de la bibliothèque JSFML permettant la gestion de Boutons
 *
 */
public class VBouton extends View {
	/** chemin vers le fichier contenant la texture du Bouton */
	private String pTextureFileName;
	/** Sprite du Bouton */
	private Sprite pSprite;
	/** Nom du bouton */
	private String pName;

	/**
	 * @param aZone
	 *            emplacement et taille du bouton
	 * @param aName
	 *            nom du bouton
	 * @param aTextureFileName
	 *            chemin vers le fichier contenant la texture du bouton
	 */
	public VBouton(FloatRect aZone, String aName, String aTextureFileName) {
		setZone(aZone);
		this.pName = aName;
		this.pTextureFileName = aTextureFileName;
		initView();
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pSprite == null) {
			initView();
		}
		this.pSprite.draw(aTarget, aState);
	}

	/**
	 * Retourne le nom du bouton
	 *
	 * @return nom du bouton
	 */
	public String getName() {
		return this.pName;
	}

	@Override
	public void initView() {
		Texture wTexture = new Texture();
		try {
			wTexture.loadFromFile(Paths.get(this.pTextureFileName));
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
