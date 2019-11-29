package dominio;

public abstract class Elemento implements Runnable{

	public enum Tipo {ESCALERA, SUELO, PERSONAJE, BARRIL,TOP_ESCALERA, BAJO_ESCALERA;}
	Tipo tipo;
	int coordenadaX;
	int coordenadaY;

	public Elemento(Tipo tipo, int coordenadaX, int coordenadaY){
		this.tipo= tipo;
		this.coordenadaX=coordenadaX;
		this.coordenadaY=coordenadaY;
	}
	
	public int getCoordenadaX() {
		return coordenadaX;
	}

	public void desplazamiento(int direccion) {
	}

	public void setCoordenadaX(int coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public int getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(int coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

}
