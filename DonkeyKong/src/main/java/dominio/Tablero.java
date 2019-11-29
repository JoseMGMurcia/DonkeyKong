package dominio;

import java.util.ArrayList;
import control.Control;
import dominio.Elemento.Tipo;
public class Tablero implements Runnable {

	// POSICIÓN INICIAL MARIO
	public static final int SALIDA_X = 46;
	public static final int SALIDA_Y = 2;

	public final static int TURNOS_ENTRE_BARRILES = 100;
	public static final int DURACION_CICLO = 70;
	private Tipo[][] coordenadas;
	private boolean jugando;
	ArrayList<Elemento> elementos;
	Control control;
	

	public enum Estado {
		DER, IZQ, SUBIR, BAJAR, CAER, SALTO, QUIETO;
	}

	public Tablero(Control control) {
		this.control = control;
		this.coordenadas = new Tipo[50][42];
		this.elementos = new ArrayList<Elemento>();
		this.elementos.add(new Personaje(Tipo.PERSONAJE, SALIDA_X, SALIDA_Y, this));
		// DEFINICIÓN TABLERO
		this.setEscalera(4, 12, 7); // ESCALERA 1
		this.setEscalera(4, 14, 7);
		this.setEscalera(4, 23, 7); // ESCALERA 2
		this.setEscalera(10, 16, 9); // ESCALERA 3
		this.coordenadas[14][16] = null;
		this.coordenadas[14][17] = null;
		this.setEscalera(11, 34, 6); // ESCALERA 4
		this.setEscalera(18, 6, 6); // ESCALERA 5
		this.setEscalera(18, 13, 7); // ESCALERA 6
		this.setEscalera(16, 31, 10); // ESCALERA 7
		this.coordenadas[21][31] = null;
		this.coordenadas[21][31] = null;
		this.coordenadas[22][32] = null;
		this.coordenadas[22][32] = null;
		this.setEscalera(24, 12, 9); // ESCALERA 8
		this.coordenadas[24][13] = Tipo.ESCALERA;
		this.coordenadas[28][12] = null;
		this.coordenadas[28][13] = null;
		this.coordenadas[29][12] = null;
		this.coordenadas[29][13] = null;
		this.setEscalera(25, 21, 8); // ESCALERA 9
		this.setEscalera(25, 34, 7); // ESCALERA 10
		this.setEscalera(33, 6, 6); // ESCALERA 11
		this.setEscalera(32, 18, 8); // ESCALERA 12
		this.setEscalera(40, 34, 6); // ESCALERA 13
		this.setEscalera(39, 15, 8); // ESCALERA 14
		this.coordenadas[42][15] = null;
		this.coordenadas[42][16] = null;
		this.coordenadas[43][15] = null;
		this.coordenadas[43][16] = null;
		// SUELO PISO 1
		for (int i = 0; i < 16; i++)
			this.coordenadas[11][i] = Tipo.SUELO;
		for (int i = 18; i < 30; i++)
			this.coordenadas[11][i] = Tipo.SUELO;
		for (int i = 30; i < 34; i++)
			this.coordenadas[12][i] = Tipo.SUELO;
		for (int i = 36; i < 39; i++)
			this.coordenadas[12][i] = Tipo.SUELO;
		// PISO 2
		for (int i = 3; i < 6; i++)
			this.coordenadas[19][i] = Tipo.SUELO;
		for (int i = 8; i < 13; i++)
			this.coordenadas[19][i] = Tipo.SUELO;
		for (int i = 15; i < 18; i++)
			this.coordenadas[19][i] = Tipo.SUELO;
		for (int i = 18; i < 30; i++)
			this.coordenadas[18][i] = Tipo.SUELO;
		this.coordenadas[17][30] = Tipo.SUELO;
		for (int i = 33; i < 42; i++)
			this.coordenadas[17][i] = Tipo.SUELO;
		// PISO 3
		for (int i = 0; i < 9; i++)
			this.coordenadas[24][i] = Tipo.SUELO;
		for (int i = 9; i < 12; i++)
			this.coordenadas[25][i] = Tipo.SUELO;
		for (int i = 14; i < 21; i++)
			this.coordenadas[25][i] = Tipo.SUELO;
		this.coordenadas[26][20] = Tipo.SUELO;
		for (int i = 23; i < 34; i++)
			this.coordenadas[26][i] = Tipo.SUELO;
		for (int i = 36; i < 39; i++)
			this.coordenadas[27][i] = Tipo.SUELO;
		// PISO 4
		for (int i = 3; i < 6; i++)
			this.coordenadas[34][i] = Tipo.SUELO;
		this.coordenadas[34][8] = Tipo.SUELO;
		for (int i = 9; i < 18; i++)
			this.coordenadas[33][i] = Tipo.SUELO;
		for (int i = 20; i < 24; i++)
			this.coordenadas[33][i] = Tipo.SUELO;
		for (int i = 24; i < 36; i++)
			this.coordenadas[32][i] = Tipo.SUELO;
		for (int i = 36; i < 42; i++)
			this.coordenadas[31][i] = Tipo.SUELO;
		// PISO 5
		for (int i = 0; i < 15; i++)
			this.coordenadas[39][i] = Tipo.SUELO;
		for (int i = 17; i < 30; i++)
			this.coordenadas[40][i] = Tipo.SUELO;
		for (int i = 30; i < 34; i++)
			this.coordenadas[41][i] = Tipo.SUELO;
		for (int i = 36; i < 39; i++)
			this.coordenadas[41][i] = Tipo.SUELO;
		// PISO 6
		for (int i = 0; i < 30; i++)
			this.coordenadas[47][i] = Tipo.SUELO;
		for (int i = 30; i < 42; i++)
			this.coordenadas[46][i] = Tipo.SUELO;
	}

	public synchronized Estado moverPersonaje(int num) {
		if (((Personaje) this.elementos.get(0)).isPausado()) {
			return Estado.QUIETO;
		}
		Personaje mario = (Personaje) this.elementos.get(0);
		int marioX = this.elementos.get(0).getCoordenadaX();
		int marioY = this.elementos.get(0).getCoordenadaY();
		mario.setCayendo(false);
		// Comprobacion de Caida
		if ((this.coordenadas[marioX + 1][marioY] == null || this.coordenadas[marioX + 1][marioY] == Tipo.TOP_ESCALERA
				)
				&& (this.coordenadas[marioX + 1][marioY - 1] == null
						|| this.coordenadas[marioX + 1][marioY - 1] == Tipo.TOP_ESCALERA
						)) {
			mario.desplazamiento(2);
			mario.setCayendo(true);
			return Estado.CAER;
		}
		switch (num) {
		case 8:
			if (this.coordenadas[marioX - 1][marioY] == Tipo.ESCALERA
					|| this.coordenadas[marioX - 1][marioY] == Tipo.BAJO_ESCALERA
					|| this.coordenadas[marioX - 1][marioY] == Tipo.TOP_ESCALERA) {
				mario.desplazamiento(num);
				return Estado.SUBIR;
			}
			break;

		case 6:
			if (mario.getCoordenadaY() + 1 < this.coordenadas[0].length) {
				if (this.coordenadas[marioX][marioY + 1] == Tipo.SUELO) {// Escalon de subida
					mario.desplazamiento(8);
				}else if  (this.coordenadas[marioX+1][marioY] == null || this.coordenadas[marioX+1][marioY] == Tipo.TOP_ESCALERA  || this.coordenadas[marioX+1][marioY] == Tipo.BAJO_ESCALERA ){
					if (this.coordenadas[marioX+1][marioY+1] == null || this.coordenadas[marioX+1][marioY+1] == Tipo.TOP_ESCALERA  || this.coordenadas[marioX+1][marioY+1] == Tipo.BAJO_ESCALERA ) {
						if (this.coordenadas[marioX+2][marioY] != null || this.coordenadas[marioX+2][marioY+1] != null) {
							mario.desplazamiento(2);
						}
					}
				}
				mario.desplazamiento(6);
			if (mario.vaACaer(this, mario)) {
				mario.setCayendo(true);	
			}
				return Estado.DER;
			}
			break;

		case 5:
			control.playSalto();
			mario.setSaltando(true);
			return Estado.SALTO;

		case 4:
			if (mario.getCoordenadaY() - 1 > 0) {
				if (marioX < 11 && marioY < 14) {
					return Estado.QUIETO;
				}
				if (this.coordenadas[marioX][marioY -2 ] == Tipo.SUELO) {// Escalon de subida
					mario.desplazamiento(8);
				}else if  (this.coordenadas[marioX+1][marioY-1] == null || this.coordenadas[marioX+1][marioY-1] == Tipo.TOP_ESCALERA  || this.coordenadas[marioX+1][marioY-1] == Tipo.BAJO_ESCALERA ){
					if (this.coordenadas[marioX+1][marioY-2] == null || this.coordenadas[marioX+1][marioY-2] == Tipo.TOP_ESCALERA  || this.coordenadas[marioX+1][marioY-2] == Tipo.BAJO_ESCALERA ) {
						if (this.coordenadas[marioX+2][marioY-1] != null || this.coordenadas[marioX+2][marioY-2] != null) {
							mario.desplazamiento(2);
						}
					}
				}
				mario.desplazamiento(4);
				if (mario.vaACaer(this, mario)) {
					mario.setCayendo(true);
				}
				return Estado.IZQ;
			}
			break;
		case 2:
			if (this.coordenadas[marioX + 1][marioY] == Tipo.ESCALERA
					|| this.coordenadas[marioX + 1][marioY] == Tipo.TOP_ESCALERA
					|| this.coordenadas[marioX + 1][marioY] == Tipo.BAJO_ESCALERA) {
				mario.desplazamiento(num);
				if (mario.vaACaer(this, mario)) {
					mario.setCayendo(true);
				}
				return Estado.BAJAR;
			}
			break;
		}
		if (mario.vaACaer(this, mario)) {
			mario.setCayendo(true);
		}
		return Estado.QUIETO;
	}

	void moverBarril(Barril barril) {// Comprobacion de Caida
		if ((this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()] == null
				|| this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()] == Tipo.TOP_ESCALERA 
				|| this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()] == Tipo.BAJO_ESCALERA)
				&& (this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY() - 1] == null
						|| this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()
								- 1] == Tipo.TOP_ESCALERA
								|| this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()
								                 								- 1] == Tipo.BAJO_ESCALERA)) {
			barril.desplazamiento(2);
			if (this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY()] != null // Comprovación de escalón
					&& this.coordenadas[barril.getCoordenadaX() + 1][barril.getCoordenadaY() - 1] != null
					&& !barril.isCayendo()) {
				if (barril.isaLaDerecha()) {
					barril.desplazamiento(6);
				} else {
					barril.desplazamiento(4);
				}
			} else {// No es un escalón
				barril.setCayendo(true);
			}
		} else {
			if (barril.isCayendo()) {
				barril.setCayendo(false);
				barril.setaLaDerecha(!barril.isaLaDerecha());
			}
			if (barril.isaLaDerecha()) {
				barril.desplazamiento(6);
			} else {
				barril.desplazamiento(4);
			}
		}
	}

	public synchronized boolean colision() {
		if (this.elementos.size() < 2 || ((Personaje) this.elementos.get(0)).isSaltando()) {
			return false;
		} else {
			try {
				for (int i = 1; i < this.elementos.size(); i++) { // Recorre todos los bariles
					for (int mx = -2; mx < 1; mx++) {
						for (int my = -1; my < 1; my++) { // recorre todas las posiciones de mario
							for (int by = -1; by < 1; by++) {
								for (int bx = -1; bx < 1; bx++) { // recorre todas las posiciones del barril
									if (this.elementos.get(i).coordenadaX + bx == this.elementos.get(0).coordenadaX + mx
											&& this.elementos.get(i).coordenadaY
													+ by == this.elementos.get(0).coordenadaY + my) {
										return true;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Fallo en colisión" + e.getMessage());
			}
		}
		return false;
	}

	public void softReset() {
		Personaje mario = (Personaje) this.elementos.get(0);
		mario.setPausado(true);
		control.playDeath();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mario.setCoordenadaX(SALIDA_X);
		mario.setCoordenadaY(SALIDA_Y);
		mario.setPausado(false);
		Control.getInstance().getPulsador().setTecla(0);
	}

	public void gameOver() {
		Personaje mario = (Personaje) this.elementos.get(0);
		mario.setPausado(true);
		control.stopBackground();
		control.playGameover();
		jugando = false;
	}

	public void win() {
		Personaje mario = (Personaje) this.elementos.get(0);
		mario.setPausado(true);
		jugando = false;
		Control.getInstance().labelWin();
		control.stopBackground();
		control.playWin();
	}

	public void run() {
		Tablero tablero = this;
		int turnos = Tablero.TURNOS_ENTRE_BARRILES - 15;
		jugando = true;
		control.playBackground();
		while (jugando) {
			Personaje mario = (Personaje) this.elementos.get(0);
			if (mario.isCayendo()) {
				Control.getInstance().caida();
				this.moverPersonaje(2);
			} else if (mario.isSaltando()) {
				Control.getInstance().saltoMario();
				this.moverPersonaje(0);
			}
			int x = (int) (Math.random() * 10) + 1;
			if (turnos > Tablero.TURNOS_ENTRE_BARRILES && x > 9) { // Lanzamiento nuevos barriles
				Control.getInstance().movimientoMono();
				try {
					Thread.sleep(700);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Barril barril = new Barril(Tipo.BARRIL, 10, 11, tablero);
				this.elementos.add(barril);
				new Thread(barril).start();
				Control.getInstance().lanzaBarril(barril);
				turnos = 0;
			} else {
				turnos++;
			}
			try {
				Thread.sleep(Tablero.DURACION_CICLO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (this.colision()) {
				mario.perderVida(this);
				if (mario.getVida() > 0) {
					tablero.softReset();
				} else {
					tablero.gameOver();
				}
			} else if (this.elementos.get(0).getCoordenadaX() < 5) {
				tablero.win();
			}
		}
	}

	private void setEscalera(int coorXArribaIzquierda, int coorYAarribaIzquierda, int altura) {
		for (int x = 0; x < altura; x++) {
			if (x == 0) {
				this.coordenadas[coorXArribaIzquierda][coorYAarribaIzquierda] = Tipo.TOP_ESCALERA;
				this.coordenadas[coorXArribaIzquierda][coorYAarribaIzquierda + 1] = Tipo.TOP_ESCALERA;
			} else if (x < altura-1) {
				this.coordenadas[coorXArribaIzquierda + x][coorYAarribaIzquierda] = Tipo.ESCALERA;
				this.coordenadas[coorXArribaIzquierda + x][coorYAarribaIzquierda + 1] = Tipo.ESCALERA;
			} else {
				this.coordenadas[coorXArribaIzquierda + x][coorYAarribaIzquierda] = Tipo.BAJO_ESCALERA;
				this.coordenadas[coorXArribaIzquierda + x][coorYAarribaIzquierda + 1] = Tipo.BAJO_ESCALERA;
			}
		}
	}

	public Tipo[][] getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Tipo[][] coordenadas) {
		this.coordenadas = coordenadas;
	}
						
	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Elemento> elementos) {
		this.elementos = elementos;
	}
}