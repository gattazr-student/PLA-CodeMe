package views.niveau;

import models.basic.Position;
import models.niveau.Case;
import models.niveau.CaseBasique;
import models.niveau.CaseLampe;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public abstract class VCase implements Drawable {

	public static float DEPL_X = 37;
	public static float DEPL_Y = 18;
	public static float DEPL_Z = 19;

	public static Vector2f deplacementCase(Position aPosition) {
		/* TODO: Pourquoi DEPL_X et DEPLX_Y div par 2 ? */
		/* Déplacment selon u */
		Transform wU = Transform.translate(new Transform(), new Vector2f(DEPL_X * aPosition.getX(), DEPL_Y
				* aPosition.getX()));
		/* Déplacment selon v */
		Transform wV = Transform.translate(new Transform(), new Vector2f(-DEPL_X * aPosition.getY(), DEPL_Y
				* aPosition.getY()));

		/* Déplacment combiné */
		Transform wFinalTransform = Transform.combine(wU, wV);
		Vector2f wDepl = new Vector2f(0, 0);
		wDepl = wFinalTransform.transformPoint(wDepl);

		return wDepl;
	}

	/**
	 * Retourne une case contenant une méthode draw à partir d'une case du Modèle
	 *
	 * @param aCase
	 *            Case
	 * @return VCase
	 */
	public static VCase getVCase(Case aCase) {
		if (aCase instanceof CaseBasique) {
			return new VCaseBasique((CaseBasique) aCase);
		}
		if (aCase instanceof CaseLampe) {
			return new VCaseLampe((CaseLampe) aCase);
		}
		return null;
	}
}
