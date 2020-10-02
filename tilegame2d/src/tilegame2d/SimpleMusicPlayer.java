/*
Copyright (c) 2020 Ganesha Ajjampura

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package tilegame2d;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Simplifies the process of playing music files.
 * 
 * @author Ganesha Ajjampura
 * @version 0.5.0
 *
 */
public class SimpleMusicPlayer {

	private AudioInputStream rawAudio;
	private Clip clip;

	/**
	 * Opens and plays supported music file types. <b> Note: the music file must be
	 * located in a source folder. </b>
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

	public boolean stopMusic() throws IOException {
		if (clip != null && clip.isOpen()) {
			rawAudio.close();
			clip.close();
			return true;
		}
		return false;
	}
}
