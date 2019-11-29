package vista;

import control.Control;

public class MonoGrafico implements Runnable {

	private Control control;

	public MonoGrafico(Control control) {
		this.control = control;
	}

	public void run() {
		this.control.getVista().getMono()
				.setIcon(new javax.swing.ImageIcon("./resources/images/MonoIzq.png"));
		try {
			Thread.sleep(400);
		} catch (Exception e) {	}
		this.control.getVista().getMono()
				.setIcon(new javax.swing.ImageIcon("./resources/images/MonoDer.png"));
		try {
			Thread.sleep(800);
		} catch (Exception e) {	}
		this.control.getVista().getMono()
				.setIcon(new javax.swing.ImageIcon("./resources/images/Mono.png"));
		try {
			Thread.sleep(1000);
			this.control.getVista().getPrincesa().setIcon(new javax.swing.ImageIcon("./resources/images/prinHelp.png"));
			Thread.sleep(600);
			this.control.getVista().getPrincesa().setIcon(new javax.swing.ImageIcon("./resources/images/prin.png"));
		} catch (Exception e) {	}
	}
}
