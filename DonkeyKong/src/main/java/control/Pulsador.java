package control;

import dominio.Personaje;
import dominio.Tablero;
import dominio.Tablero.Estado;

public class Pulsador implements Runnable {

	Tablero tablero;
	private int tecla;
	public boolean modificado;

	public Pulsador(Tablero tablero) {
		this.tablero = tablero;
	}

	synchronized public void run() {
		Estado estado = Estado.QUIETO;
		while (true) {
			
			try {
				if (modificado && 	!((Personaje)(tablero.getElementos().get(0))).isPausado()  ) {
					Thread.sleep(50);
					estado =  tablero.moverPersonaje(tecla);
					Control.getInstance().getVista().cambioDeImagen(estado, Control.getInstance().getVista().getMario());
					Control.getInstance().getVista().cambioPixeles(estado);
					modificado = false;
				}else{
				Thread.sleep(50);
				}
			} catch (Exception e) {
			}
	
		}
	}
	
	
	

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public int getTecla() {
		return tecla;
	}

	public void setTecla(int tecla) {
		this.modificado = true;
		this.tecla = tecla;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

}
