package geUtilities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public String WHISTLE = "whistle.wav";
	public String CHUG = "chug.wav";
	private final static Clip NO_CLIP = null;

	public Sound () {
	}

	public void playSoundClip (String aSoundFileName) {
		String tAudioFileName;

		tAudioFileName = "AudioClips" + File.separator + aSoundFileName;
		playSound (tAudioFileName);
	}

	public void playSound (String aSoundFileName) {
		Clip tClip;

		try {
			tClip = loadClip (aSoundFileName);
			setVolume (0.15f, tClip);
			if (tClip != NO_CLIP) {
				tClip.start ();
			}
		} catch (Exception ex) {
			ex.printStackTrace ();
		}
	}

	private Clip loadClip (String aSoundFileName) {
		File tAudioFile;
		AudioInputStream tAudioStream;
		Clip tClip = NO_CLIP;

		try {
			tAudioFile = new File (aSoundFileName);
			tAudioStream = AudioSystem.getAudioInputStream (tAudioFile);
			tClip = AudioSystem.getClip ();
			tClip.open (tAudioStream);
		} catch (UnsupportedAudioFileException eException) {
			System.err.println ("Unsupported Audio File Exception Thrown");
		} catch (IOException eException) {
			System.err.println ("IO Exception Thrown");
		} catch (LineUnavailableException eException) {
			System.err.println ("Line Unavailable Exception Thrown");
		}

		return tClip;
	}

	public float getVolume (Clip aClip) {
		FloatControl tGainControl;

		tGainControl = (FloatControl) aClip.getControl (FloatControl.Type.MASTER_GAIN);
		return (float) Math.pow (10f, tGainControl.getValue () / 20f);
	}

	public void setVolume (float volume, Clip aClip) {
		FloatControl tGainControl;

		if ((volume < 0f) || (volume > 1f))
			throw new IllegalArgumentException ("Volume not valid: " + volume);

		tGainControl = (FloatControl) aClip.getControl (FloatControl.Type.MASTER_GAIN);
		tGainControl.setValue (20f * (float) Math.log10 (volume));
	}
}
