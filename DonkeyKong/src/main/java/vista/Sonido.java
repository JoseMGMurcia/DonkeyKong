package vista;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class Sonido implements Runnable {

	private final int BUFFER_SIZE = 128000;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	public enum TipoSonido {
		BACKGROUND, COIN,  EXPLOSION, DEAD, GAMEOVER, INTRO, SALTO, WIN,
	}

	private String sonido;
	private boolean loop;

	public Sonido(TipoSonido sonido) {
		loop = false;

		switch (sonido) {
		case BACKGROUND:
			this.sonido = "./resources/sounds/bacmusic.wav";
			loop = true;
			break;
		case COIN:
			this.sonido = "./resources/sounds/coin.wav";
			break;
		case DEAD:
			this.sonido = "./resources/sounds/death.wav";
			break;
		case EXPLOSION:
			this.sonido = "./resources/sounds/explosion.wav";
			break;
		case GAMEOVER:
			this.sonido = "./resources/sounds/gameover.wav";
			break;
		case INTRO:
			this.sonido = "./resources/sounds/intro.wav";
			loop = true;
			break;
		case SALTO:
			this.sonido = "./resources/sounds/salto.wav";
			break;
		case WIN:
			this.sonido = "./resources/sounds/win.wav";
			break;
		}
	}

	public void run() {
		do {
			this.playSound(sonido);
		} while (loop);
	}

	public void playSound(String archivo) {
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(sonido));
			audioFormat = audioStream.getFormat();
			sourceLine = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, audioFormat));
			sourceLine.open(audioFormat);
			sourceLine.start();
			int nBytesRead = 0;
			byte[] abData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
				try {
					nBytesRead = audioStream.read(abData, 0, abData.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (nBytesRead >= 0) {
					@SuppressWarnings("unused")
					int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
				}
			}
			sourceLine.drain();
			sourceLine.close();

		} catch (Exception e) {
			System.out.println("Error de sonido: " + e.getMessage());
		}
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

}
