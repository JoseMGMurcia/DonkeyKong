package vista;

import javax.swing.JLabel;

import control.Control;
import dominio.Barril;
import dominio.Tablero;

public class BarrilGrafico implements Runnable {

	private Barril barril;
	private JLabel label;
	private Control control;
	private String[] imagenes = { "./resources/images/Barril1.png", "./resources/images/Barril2.png",
			"./resources/images/Barril3.png", "./resources/images/Barril4.png" };

	public BarrilGrafico(Barril barril, Control control) {
		this.barril = barril;
		this.control = control;
	}

	synchronized public void run() {
		label = new JLabel();
		label.setIcon(new javax.swing.ImageIcon("./resources/images/Barril.png"));
		this.control.getVista().getContentPane().add(label);
		this.control.getVista().getContentPane().setComponentZOrder(label, 1);
		label.setBounds(118, 92, 28, 22);
		int cont = 0;
		while (this.barril.isRunning()) {
			try {
				if (this.barril.isCayendo()) {
					label.setIcon(new javax.swing.ImageIcon("./resources/images/BarrilCaida.png"));
					this.control.getVista().caida(label);
				} else {
					label.setIcon(new javax.swing.ImageIcon(imagenes[(int) cont / 4]));
					if (cont == 15 && barril.isaLaDerecha()) {
						cont = 0;
					} else if (cont == 0 && !barril.isaLaDerecha()) {
						cont = 15;
					}
					this.label.setLocation(
							this.control.getVista().tableroPixeles[this.barril.getCoordenadaX()][this.barril
									.getCoordenadaY()].x,
							this.control.getVista().tableroPixeles[this.barril.getCoordenadaX()][this.barril
									.getCoordenadaY()].y + 24);
					Thread.sleep(Tablero.DURACION_CICLO);
					if (barril.isaLaDerecha()) {
						cont++;
					} else {
						cont--;
					}
				}
			} catch (Exception e) {
			}
		}
		this.label.setVisible(false);
		try {

			label.setIcon(new javax.swing.ImageIcon("./resources/images/Explosion1.png"));
			this.label.setLocation(
					this.control.getVista().tableroPixeles[this.barril.getCoordenadaX()][this.barril
							.getCoordenadaY()].x,
					this.control.getVista().tableroPixeles[this.barril.getCoordenadaX()][this.barril.getCoordenadaY()].y
							+ 20);
			this.label.setVisible(true);
			Thread.sleep(100);
			label.setIcon(new javax.swing.ImageIcon("./resources/images/Explosion2.png"));
			Thread.sleep(100);
			label.setIcon(new javax.swing.ImageIcon("./resources/images/Explosion3.png"));
			Thread.sleep(100);
			label.setIcon(new javax.swing.ImageIcon("./resources/images/Explosion4.png"));
			Thread.sleep(100);

			this.control.getVista().getPrincesa().setIcon(new javax.swing.ImageIcon("./resources/images/prinHelp.png"));
			Thread.sleep(400);
			this.control.getVista().getPrincesa().setIcon(new javax.swing.ImageIcon("./resources/images/prin.png"));

		} catch (Exception e) {
		}
		this.control.getVista().getContentPane().remove(label);
		this.control.getVista().repaint();
	}

}
