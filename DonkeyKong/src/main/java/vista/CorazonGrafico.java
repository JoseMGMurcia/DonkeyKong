package vista;

public class CorazonGrafico implements Runnable {

	private Vista vista;
	private boolean ganar;
	private boolean running;

	public CorazonGrafico(Vista vista, boolean ganar) {
		this.vista = vista;
		this.ganar = ganar;
	}

	public void run() {
		running = true;
		while (running) {

			vista.getCorazon().setIcon(new javax.swing.ImageIcon("./resources/images/Corazon.png"));
			vista.getCorazon().setVisible(true);
			if (ganar) {
				try {
					Thread.sleep(800);
					vista.getCorazon()
							.setIcon(new javax.swing.ImageIcon("./resources/images/CorazonPeque.png"));
					Thread.sleep(500);
				} catch (Exception e) {}
			} else {
				try {
					Thread.sleep(500);
					vista.getCorazon()
							.setIcon(new javax.swing.ImageIcon("./resources/images/CorazonRoto.png"));
					Thread.sleep(800);
				} catch (Exception e) {}
			}
		}
		vista.getCorazon().setVisible(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
