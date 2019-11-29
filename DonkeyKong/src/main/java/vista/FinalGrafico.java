package vista;

public class FinalGrafico implements Runnable{
	private Vista vista;
	private boolean ganar;
	private boolean running;

	public FinalGrafico(Vista vista, boolean ganar) {
		this.vista = vista;
		this.ganar = ganar;
	}

	public void run() {
		try {Thread.sleep(3000);} catch (Exception e) {}
		vista.setFin(true);
		running = true;
		while (running) {
			vista.getFinal().setVisible(true);
			if (ganar) {
				try {
					if (vista.isPulsador()) {
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/VictoryAll.png"));
						Thread.sleep(500);
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/VictoryNew.png"));
						Thread.sleep(500);
					} else {
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/VictoryAll.png"));
						Thread.sleep(500);
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/VictoryExit.png"));
						Thread.sleep(500);
					}
				} catch (Exception e) {	}
			} else {
				try {
					if (vista.isPulsador()) {
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/GamerOverAll.png"));
						Thread.sleep(500);
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/GamerOverNew.png"));
						Thread.sleep(500);
					} else {
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/GamerOverAll.png"));
						Thread.sleep(500);
						vista.getFinal().setIcon(
								new javax.swing.ImageIcon("./resources/images/GamerOverExit.png"));
						Thread.sleep(500);
					}
				} catch (Exception e) {	}
			}
		}
		vista.getFinal().setVisible(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}