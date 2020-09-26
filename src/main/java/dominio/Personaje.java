package dominio;

import control.Control;

public class Personaje extends Elemento {

	private int vida;
	private boolean vivo;
	private boolean pausado;
	private boolean saltando;
	private boolean cayendo;

	public Personaje(Tipo tipo, int coordenadaX, int coordenadaY, Tablero tablero) {
		super(tipo, coordenadaX, coordenadaY);
		this.vida = 3;
		this.vivo = true;
		this.pausado = false;
		this.saltando = false;
	}

	public boolean isPausado() {
		return pausado;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	@Override
	public void desplazamiento(int direccion) {
		switch (direccion) {
		case 8:
			this.coordenadaX--;
			break;
		case 4:
			this.coordenadaY--;
			break;
		case 6:
			this.coordenadaY++;
			break;
		case 2:
			this.coordenadaX++;
			break;
		}
	}

	public void perderVida(Tablero tablero) {
		((Personaje) tablero.elementos.get(0)).setPausado(true);
		Control.getInstance().mataAMario();
		((Personaje) tablero.elementos.get(0)).setVida(((Personaje) tablero.elementos.get(0)).getVida() - 1);
		Control.getInstance().getVista().perderVida();
	}

	public boolean vaACaer(Tablero tablero, Personaje mario) {
		if (tablero.getCoordenadas()[mario.getCoordenadaX() + 1][mario.getCoordenadaY()] == null
				&& tablero.getCoordenadas()[mario.getCoordenadaX() + 1][mario.getCoordenadaY() - 1] == null) {
			return true;
		}
		return false;
	}

	public boolean isCayendo() {
		return cayendo;
	}

	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void run() {

	}
}