package vista;

import control.Control;

public class InicioGrafico implements Runnable {
	public InicioGrafico(){
	}
	public void run() {
		while (Control.getInstance().getVista().inicio) {
			try {
				Thread.sleep(500);
				Control.getInstance().getVista().getInicio()
						.setIcon(new javax.swing.ImageIcon("./resources/images/InicioNoAll.png"));
				Thread.sleep(500);
				Control.getInstance().getVista().getInicio()
						.setIcon(new javax.swing.ImageIcon("./resources/images/InicioAll.png"));
			} catch (Exception e) {	}
		}
		Control.getInstance().getVista().getInicio().setVisible(false);
	}
}
