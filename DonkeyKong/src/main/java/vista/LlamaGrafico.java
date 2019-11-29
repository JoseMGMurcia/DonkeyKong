package vista;

import javax.swing.JLabel;

import control.Control;

public class LlamaGrafico implements Runnable {
	JLabel llama;
	public LlamaGrafico() {
		llama = Control.getInstance().getVista().getLlama();
	}

	public void run() {
		int x;
		while (true) {
			x = (int) (Math.random() * 90) + 1;
			try {
				if (x < 35)
					llama.setIcon(new javax.swing.ImageIcon("./resources/images/Llamas1.png"));
				else if (x < 70)
					llama.setIcon(new javax.swing.ImageIcon("./resources/images/Llamas2.png"));
				else if (x < 80)
					llama.setIcon(new javax.swing.ImageIcon("./resources/images/Llamas3.png"));
				else if (x < 90)
					llama.setIcon(new javax.swing.ImageIcon("./resources/images/Llamas4.png"));
				Thread.sleep(90);
			} catch (Exception e) {	}
		}
	}
}
