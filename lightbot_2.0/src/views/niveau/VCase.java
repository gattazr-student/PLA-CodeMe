package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;
import models.niveau.Case;
import models.niveau.CaseBasique;
import models.niveau.CaseInterrupteur;
import models.niveau.CaseLampe;
import models.niveau.CaseVide;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import views.View;

/**
 * Représentation graphique d'une Case
 *
 */
public abstract class VCase extends View implements Observer {

	/** Diagonale horizontale de la case */
	public static final float DIAG_HORIZONTALE = 70;
	/** Diagonale verticle de la case */
	public static final float DIAG_VERTICALE = 37;
	/** Déplacement horizontale */
	public static final float DEPL_HAUTEUR = 18;
	/** Hauteur de la case */
	public static final float HAUTEUR = 74;
	/** Largeur de la case */
	public static final float LARGEUR = 55;

	/**
	 * Calcule le vecteur de déplacement à appliquer pour placer une case à la position donnée en 2D
	 * isométrique
	 *
	 * @param aPosition
	 *            Position de la Case
	 * @return vecteur de déplacement à appliquer pour placer la case dans la VCarte
	 */
	public static Vector2f deplacementCase(Position aPosition) {
		/* Déplacement selon u */
		Transform wU = Transform.translate(new Transform(),
				new Vector2f(DIAG_HORIZONTALE / 2 * aPosition.getX(), ((int) (DIAG_VERTICALE / 2))
						* aPosition.getX()));
		/* Déplacement selon v */
		Transform wV = Transform.translate(new Transform(),
				new Vector2f(-DIAG_HORIZONTALE / 2 * aPosition.getY(), ((int) (DIAG_VERTICALE / 2))
						* aPosition.getY()));

		/* Déplacement combiné */
		Transform wFinalTransform = Transform.combine(wU, wV);
		Vector2f wDepl = new Vector2f(0, 0);
		wDepl = wFinalTransform.transformPoint(wDepl);

		return wDepl;
	}

	/**
	 * Retourne une case contenant une méthode draw à partir d'une case du Modèle
	 *
	 * @param aCase
	 *            Case à représenter
	 * @param aZone
	 *            Zone de la Case
	 * @return VCase créé
	 */
	public static VCase makeVCase(Case aCase, FloatRect aZone) {
		if (aCase instanceof CaseBasique) {
			return new VCaseBasique((CaseBasique) aCase, aZone);
		}
		if (aCase instanceof CaseLampe) {
			return new VCaseLampe((CaseLampe) aCase, aZone);
		}
		if (aCase instanceof CaseInterrupteur) {
			return new VCaseInterrupteur((CaseInterrupteur) aCase, aZone);
		}
		if (aCase instanceof CaseVide) {
			return new VCaseVide((CaseVide) aCase, aZone);
		}
		return null;
	}

	/** hauteur de la Csase */
	private int pHauteur;
	/** Case représenté */
	private Case pCase;

	/** Liste de case permettant la représentation de la VCase */
	private List<Sprite> pSprites;

	/**
	 *
	 * @param aCase
	 *            Case à représenter
	 * @param aZone
	 *            Position et taille de la VCase
	 */
	public VCase(Case aCase, FloatRect aZone) {
		this.pCase = aCase;
		this.pSprites = new LinkedList<Sprite>();
		setZone(aZone);
	}

	/**
	 * Ajoute un Sprite à la liste des sprites permettant la représentation d'une VCase
	 *
	 * @param aSprite
	 *            Sprite à rajouter
	 */
	public void addSprite(Sprite aSprite) {
		this.pSprites.add(aSprite);
	}

	@Override
	public boolean contains(Vector2f aPosition) {
		/* On a déja vérifié la hauteur et les zones mortes */
		return true;
	}

	@Override
	public void draw(RenderTarget aTarget, RenderStates aState) {
		if (this.pSprites == null) {
			initView();
		}
		/* Calcul de la position asbolue */
		Transform wTranslation = Transform.translate(new Transform(), getOrigin());
		new RenderStates(aState.blendMode, Transform.combine(wTranslation, aState.transform), aState.texture,
				aState.shader);
		for (Sprite wSprite : this.pSprites) {
			wSprite.draw(aTarget, aState);
		}
	}

	/**
	 * Retourne la Case associé à la VCase
	 *
	 * @return Case associé à la VCase
	 */
	public Case getCase() {
		return this.pCase;
	}

	/**
	 * Retourne la hauteur de la VCase
	 *
	 * @return hauteur de la VCase
	 */
	public int getHauteur() {
		return this.pHauteur;
	}

	/**
	 * Définit la hauteur de la VCase
	 *
	 * @param aHauteur
	 *            hauteur de la CVase
	 */
	public void setHauteur(int aHauteur) {
		this.pHauteur = aHauteur;
	}
}
