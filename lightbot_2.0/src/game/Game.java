package game;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import views.fenetre.Fenetre;
import views.menu.Home;

/**
 * Point d'entrée du programme
 *
 */
public class Game {

	/**
	 * Point d'entré du programme
	 *
	 * @param aArgs
	 *            Paramètres
	 */
	public static void main(String[] aArgs) {
		SoundBuffer wSoundBuffer = new SoundBuffer();
		try {
			wSoundBuffer.loadFromFile(Paths.get("res/audio/BGM/morceau.ogg"));
		} catch (IOException e) {
			System.err.println("Le fichier son est introuvable");
		}
		Sound wSound = new Sound();
		wSound.setBuffer(wSoundBuffer);
		wSound.setLoop(true);
		wSound.play();
		Home wViewHome = new Home(Fenetre.FENETRE);
		wViewHome.runHome();
	}

}
