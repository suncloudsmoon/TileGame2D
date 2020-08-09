package tilegame2d;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Contains a method that simplifies the process of playing music files.
 * 
 * @author Ganesha Ajjampura
 *
 */
public class SimpleMusicPlayer {

	private AudioInputStream rawAudio;
	private Clip clip;

	/**
	 * Opens and plays supported music file types. Note: the music file must be
	 * located in a source folder.
	 * 
	 * @param fileName A String value that specifies the directory path of the music
	 *                 file.
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws IOException
	 */
	public void playMusic(String fileName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		if (clip != null && clip.isOpen()) {
			clip.close();
		}
		rawAudio = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(fileName));
		clip = AudioSystem.getClip();

		clip.open(rawAudio);
		clip.start();
	}
}
