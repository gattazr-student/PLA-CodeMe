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

public class VBouton extends View {

	private String pTextureFileName;
	private Sprite pSprite;
	private String pName;

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
