package dominio;

import control.Control;

public class Barril extends Elemento {
	private boolean aLaDerecha;
	private boolean cayendo;
	private Tablero tablero;
	private boolean running;

	public Barril(Tipo tipo, int coordenadaX, int coordenadaY, Tablero tablero) {
		super(tipo, coordenadaX, coordenadaY);
		cayendo = false;
		aLaDerecha = true;
		this.tablero = tablero;
	}

	@Override
	public void desplazamiento(int direccion) {
		switch (direccion) {
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

	synchronized public void run() {
		running = true;
		while (running) {
			this.tablero.moverBarril(this);
			try {
				Thread.sleep(Tablero.DURACION_CICLO * 2);
			} catch (InterruptedException e) {	}
			if (this.coordenadaY == 7 && this.coordenadaX == 46) {
				this.tablero.elementos.remove(this);
				running = false;
				Control.getInstance().playExplosion();
			}
			if (((Personaje) this.tablero.elementos.get(0)).isPausado()) {
				running = false;
				this.tablero.elementos.remove(this);
			}
		}
	}

	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}


	public boolean isCayendo() {
		return cayendo;
	}

	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}

	public boolean isaLaDerecha() {
		return aLaDerecha;
	}

	public void setaLaDerecha(boolean aLaDerecha) {
		this.aLaDerecha = aLaDerecha;
	}
}
