package control;

import dominio.Barril;
import dominio.Personaje;
import dominio.Tablero;
import vista.BarrilGrafico;
import vista.CorazonGrafico;
import vista.FinalGrafico;
import vista.InicioGrafico;
import vista.LlamaGrafico;
import vista.MonoGrafico;
import vista.Sonido;
import vista.Vista;
import vista.Sonido.TipoSonido;

public class Control {
	private Pulsador pulsador;
	private static Control INSTANCE = null;
	private CorazonGrafico corazon;
	private Sonido sonido;
	private Vista vista;
	private Sonido intro;
	private FinalGrafico fin;

	private Control() {
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Control();
		}
	}

	public static Control getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}

	public void start() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Tablero tablero = new Tablero(getInstance());
				vista = new Vista(tablero);
				vista.setVisible(true);
				new Thread(new InicioGrafico()).start();
				intro = new Sonido(TipoSonido.INTRO);
				new Thread(intro).start();
			}
		});
	}

	public void nuevaPartida() {
		try {
			vista.setFin(false);
			corazon.setRunning(false);
			fin.setRunning(false);
		} catch (Exception e) {	}
		Tablero tablero = new Tablero(Control.getInstance());
		this.vista.setTablero(tablero);
		this.pulsador.setTablero(tablero);
		new Thread(new Sonido(TipoSonido.COIN)).start();
		vista.perderVida();
		new Thread(tablero).start();
	}

	public void primeraPartida(Tablero tablero) {
		intro.setLoop(false);
		new Thread(new Sonido(TipoSonido.COIN)).start();
		new Thread(tablero).start();
		new Thread(new LlamaGrafico()).start();
		pulsador = new Pulsador(tablero);
		new Thread(pulsador).start();
	}

	public void labelWin() {
		corazon = new CorazonGrafico(vista, true);
		fin = new FinalGrafico(vista, true);
		new Thread(corazon).start();
		new Thread(fin).start();
	}
	
	public void labelLose() {
		corazon = new CorazonGrafico(vista, false);
		fin = new FinalGrafico(vista, false);
		new Thread(corazon).start();
		new Thread(fin).start();
	}
	

	public void lanzaBarril(Barril barril) {
		new Thread(new BarrilGrafico(barril, getInstance())).start();
	}

	public void movimientoMono() {
		new Thread(new MonoGrafico(getInstance())).start();
	}

	public void mataAMario() {
		vista.vistaMuerto();
		vista.perderVida();
	}

	public Pulsador getPulsador() {
		return pulsador;
	}

	public void setPulsador(Pulsador pulsador) {
		this.pulsador = pulsador;
	}

	public void saltoMario() {
		vista.salto();
		((Personaje) vista.getTablero().getElementos().get(0)).setSaltando(false);
	}

	public void caida() {
		vista.caida(vista.getMario());
	}

	public void playSalto() {
		new Thread(new Sonido(TipoSonido.SALTO)).start();
	}

	public void playBackground() {
		sonido = new Sonido(TipoSonido.BACKGROUND);
		new Thread(sonido).start();
	}

	public void playDeath() {
		new Thread(new Sonido(TipoSonido.DEAD)).start();
	}

	public void playExplosion() {
		new Thread(new Sonido(TipoSonido.EXPLOSION)).start();
	}

	public void playGameover() {
		new Thread(new Sonido(TipoSonido.GAMEOVER)).start();
	}

	public void playWin() {
		new Thread(new Sonido(TipoSonido.WIN)).start();
	}

	public void stopBackground() {
		sonido.setLoop(false);
	}

	public Sonido getSonido() {
		return sonido;
	}

	public void setSonido(Sonido sonido) {
		this.sonido = sonido;
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}
}
