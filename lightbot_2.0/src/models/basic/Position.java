package models.basic;

/**
 * Position d'un élément
 *
 */
public class Position {
	/** Position sur l'axe des abscisse */
	private int pX;
	/** Position sur l'axe des ordonnées */
	private int pY;

	/**
	 * Création d'un point à l'origine du repère (0,0)
	 */
	public Position() {
		this.pX = 0;
		this.pY = 0;
	}

	/**
	 * Création d'un point à une abscisse et une ordonnée donnée
	 *
	 * @param aX
	 *            valeur sur l'axe des abscisses
	 * @param aY
	 *            valeur sur l'axe des ordonnées
	 */
	public Position(int aX, int aY) {
		this.pX = aX;
		this.pY = aY;
	}

	/**
	 * Création d'une Position par copie
	 *
	 * @param aPosition
	 *            Position à copier
	 */
	public Position(Position aPosition) {
		this.pX = aPosition.getX();
		this.pY = aPosition.getY();
	}

	@Override
	public boolean equals(Object aPosition) {
		if (aPosition instanceof Position) {
			Position wPosition = (Position) aPosition;
			if ((wPosition.pX == this.pX) && (wPosition.pY == this.pY)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne la valeur sur l'axe des abscisses
	 *
	 * @return valeur sur l'axe des abscisses
	 */
	public int getX() {
		return this.pX;
	}

	/**
	 * Retourne la valeur sur l'axe des ordonnées
	 *
	 * @return valeur sur l'axe des ordonnées
	 */
	public int getY() {
		return this.pY;
	}

	@Override
	public int hashCode() {
		return new Integer(this.pY * 10000 + this.pX).hashCode();
	}

	/**
	 * Création d'un objet position après un déplacement selon une Orientation donnée
	 *
	 * @param aDirection
	 *            direction donnée
	 * @return nouvelle Position
	 */
	public Position move(Orientation aDirection) {
		Position wNew = new Position(this);
		if (aDirection == Orientation.NORD) {
			wNew.pY--;
		} else if (aDirection == Orientation.EST) {
			wNew.pX++;
		} else if (aDirection == Orientation.SUD) {
			wNew.pY++;
		} else {
			wNew.pX--;
		}
		return wNew;
	}
}
