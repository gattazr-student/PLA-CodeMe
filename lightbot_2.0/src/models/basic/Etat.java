package models.basic;

/**
 *
 * Etat du Bot, s'il est ACTIF alors on peut effectuer des actions, mais s'il est PASSIF alors c'est qu'il
 * est en attente qu'on lui redonne la main par un Notify du second Bot
 *
 */
public enum Etat {
	ACTIF, PASSIF
}