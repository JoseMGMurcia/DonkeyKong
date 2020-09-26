package vista;

import java.awt.Point;
import javax.swing.JLabel;
import control.Control;
import dominio.Tablero;
import dominio.Personaje;
import dominio.Tablero.Estado;

public class Vista extends javax.swing.JFrame {
	Tablero tablero;
	Point[][] tableroPixeles;
	boolean posicion = true;
	Estado estado;
	boolean pulsador = false;
	boolean inicio = true;

	public Vista(Tablero tablero) {
		initComponents();
		this.setSize(437, 530);
		this.tablero = tablero;
		tableroPixeles = new Point[50][42];
		rellenarTablero();
		estado = Estado.QUIETO;
		Corazon.setVisible(false);
		Final.setVisible(false);
		fin = false;
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		Mario = new javax.swing.JLabel();
		Mono = new javax.swing.JLabel();
		Princesa = new javax.swing.JLabel();
		Vidas = new javax.swing.JLabel();
		Corazon = new javax.swing.JLabel();
		Bidon = new javax.swing.JLabel();
		Fondo = new javax.swing.JLabel();
		Llama = new javax.swing.JLabel();
		Final = new javax.swing.JLabel();
		Inicio = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(500, 400));
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				formKeyPressed(evt);
			}
		});
		getContentPane().setLayout(null);

		Final.setIcon(new javax.swing.ImageIcon("./resources/images/GamerOverAll.png")); // NOI18N
		getContentPane().add(Final);
		Final.setBounds(60, 150, 300, 168);

		Inicio.setIcon(new javax.swing.ImageIcon("./resources/images/InicioAll.png")); // NOI18N
		getContentPane().add(Inicio);
		Inicio.setBounds(0, 0, 420, 491);

		Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioQuietoDer.png")); // NOI18N
		getContentPane().add(Mario);
		Mario.setBounds(0, 431, 30, 50);

		Bidon.setIcon(new javax.swing.ImageIcon("./resources/images/Bidon.png")); // NOI18N
		getContentPane().add(Bidon);
		Bidon.setBounds(27, 430, 50, 50);

		Llama.setIcon(new javax.swing.ImageIcon("./resources/images/Llamas1.png")); // NOI18N
		getContentPane().add(Llama);
		Llama.setBounds(29, 404, 38, 36);

		Princesa.setIcon(new javax.swing.ImageIcon("./resources/images/prin.png")); // NOI18N
		getContentPane().add(Princesa);
		Princesa.setBounds(165, -1, 92, 60);

		Mono.setIcon(new javax.swing.ImageIcon("./resources/images/Mono.png")); // NOI18N
		getContentPane().add(Mono);
		Mono.setBounds(35, 49, 85, 65);

		Vidas.setIcon(new javax.swing.ImageIcon("./resources/images/Vidas3.png")); // NOI18N
		getContentPane().add(Vidas);
		Vidas.setBounds(323, 0, 97, 50);

		Corazon.setIcon(new javax.swing.ImageIcon("./resources/images/CorazonRoto.png")); // NOI18N
		getContentPane().add(Corazon);
		Corazon.setBounds(195, 5, 36, 30);

		Fondo.setIcon(new javax.swing.ImageIcon("./resources/images/Fondo.jpg")); // NOI18N
		getContentPane().add(Fondo);
		Fondo.setBounds(0, 0, 420, 491);

		pack();
	}

	public javax.swing.JLabel getPrincesa() {
		return Princesa;
	}

	public void setPrincesa(javax.swing.JLabel princesa) {
		Princesa = princesa;
	}

	public javax.swing.JLabel getMono() {
		return Mono;
	}

	public void setMono(javax.swing.JLabel mono) {
		Mono = mono;
	}

	public synchronized void formKeyPressed(java.awt.event.KeyEvent e) {
		if (((Personaje) this.tablero.getElementos().get(0)).isCayendo()
				|| ((Personaje) this.tablero.getElementos().get(0)).isSaltando()) {
		} else {
			if (java.awt.event.KeyEvent.VK_W == e.getKeyCode()) {
				Control.getInstance().getPulsador().setTecla(8);
				pulsador = !pulsador;
			} else if (java.awt.event.KeyEvent.VK_S == e.getKeyCode()) {
				Control.getInstance().getPulsador().setTecla(2);
				pulsador = !pulsador;
			} else if (java.awt.event.KeyEvent.VK_A == e.getKeyCode()) {
				Control.getInstance().getPulsador().setTecla(4);
			} else if (java.awt.event.KeyEvent.VK_D == e.getKeyCode()) {
				Control.getInstance().getPulsador().setTecla(6);
			} else if (java.awt.event.KeyEvent.VK_SPACE == e.getKeyCode()) {
				Control.getInstance().getPulsador().setTecla(5);
			} else if (java.awt.event.KeyEvent.VK_ENTER == e.getKeyCode()) {
				if (fin && pulsador) {
					System.exit(0);
				} else if (fin && !pulsador) {
					Control.getInstance().nuevaPartida();
				} else if (inicio) {
					Control.getInstance().primeraPartida(tablero);
					inicio = false;
				}
			} else {
				Control.getInstance().getPulsador().setTecla(0);
			}
		}
	}

	public void vistaMuerto() {
		Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioMuerto.png"));
	}

	public void cambioDeImagen(Estado estado, JLabel label) {

		switch (estado) {
		case DER:
			Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioDer.png"));
			posicion = true;
			break;
		case IZQ:
			Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioIzq.png"));
			posicion = false;
			break;
		case BAJAR:
			Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioEscaleras.png"));
			break;
		case SUBIR:
			Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioEscaleras.png"));
			break;
		case CAER:
		case SALTO:

			if (label.equals(Mario)) {
				if (posicion)
					Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioSalCaiDer.png"));
				else
					Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioSalCaiIzq.png"));
			}
			break;
		case QUIETO:
			if (posicion)
				Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioQuietoDer.png"));
			else
				Mario.setIcon(new javax.swing.ImageIcon("./resources/images/MarioQuietoIzq.png"));
			break;
		default:
			break;
		}
	}

	public void caida(JLabel label) {
		cambioDeImagen(Estado.CAER, label);
		for (int i = 0; i < 8; i++) {
			label.setLocation(label.getLocation().x, label.getLocation().y + 1);
			this.repaint();
			try {
				Thread.sleep(18);
			} catch (Exception e) {
			}
		}

	}

	public void salto() {
		for (int i = 0; i < 35; i++) {
			Mario.setLocation(Mario.getLocation().x, Mario.getLocation().y - 1);
			this.repaint();
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
		for (int i = 0; i < 35; i++) {
			Mario.setLocation(Mario.getLocation().x, Mario.getLocation().y + 1);
			this.repaint();
			try {
				Thread.sleep(25);
			} catch (Exception e) {
			}
		}
	}

	public void perderVida() {

		switch (((Personaje) tablero.getElementos().get(0)).getVida()) {
		case 3:
			Vidas.setIcon(new javax.swing.ImageIcon("./resources/images/Vidas3.png"));
			this.repaint();
			break;
		case 2:
			Vidas.setIcon(new javax.swing.ImageIcon("./resources/images/Vidas2.png"));
			break;
		case 1:
			Vidas.setIcon(new javax.swing.ImageIcon("./resources/images/Vidas1.png"));
			break;
		case 0:
			Vidas.setIcon(new javax.swing.ImageIcon("./resources/images/Vidas0.png"));
			Control.getInstance().labelLose();
			break;
		default:
			break;
		}
	}

	public void cambioPixeles(Estado estado) {
		if (estado == Estado.CAER) {
		} else {
			try {
				Mario.setLocation(tableroPixeles[tablero.getElementos().get(0).getCoordenadaX()][tablero.getElementos()
						.get(0).getCoordenadaY()]);
			} catch (Exception e) {
			}
		}
	}

	private void rellenarTablero() {
		int pixel = 425;
		int cont = 0;
		// SUELO 1
		tableroPixeles[46][1] = new Point(-5, 428);
		for (int i = 2; i <= 20; i++) {
			tableroPixeles[46][i] = new Point((i * 10) - 8, 427);
		}
		for (int i = 21; i <= 29; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[46][i] = new Point((i * 10) - 11, pixel);
			cont++;
		}
		for (int i = 30; i <= 41; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[45][i] = new Point((i * 10) - 13, pixel);
			cont++;
		}

		// SUELO 2
		pixel = 367;
		for (int i = 40; i >= 30; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[40][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 30; i >= 15; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[39][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 15; i >= 2; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[38][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		tableroPixeles[38][1] = new Point((1 * 10) - 13, pixel);

		// SUELO 3
		pixel = 294;

		cont = 0;
		tableroPixeles[33][2] = new Point((2 * 10) - 15, pixel);
		for (int i = 3; i <= 8; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[33][i] = new Point((i * 10) - 13, pixel);
			cont++;

		}
		for (int i = 8; i <= 23; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[32][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 23; i <= 35; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[31][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 35; i <= 41; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[30][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}

		// SUELO 4
		pixel = 224;
		for (int i = 40; i >= 36; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[26][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 36; i >= 21; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[25][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 21; i >= 9; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[24][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		pixel -= 2;
		for (int i = 9; i >= 0; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[23][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}

		// SUELO 5
		pixel = 149;
		cont = 0;
		tableroPixeles[18][2] = new Point((2 * 10) - 15, pixel);
		for (int i = 3; i <= 17; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[18][i] = new Point((i * 10) - 13, pixel);
			cont++;
		}
		for (int i = 17; i <= 29; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[17][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		pixel -= 2;
		for (int i = 29; i <= 41; i++) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[16][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		pixel = 78;
		cont = 0;
		// SUELO 6
		for (int i = 40; i >= 30; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[11][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		for (int i = 30; i >= 27; i--) {
			if (cont > 2) {
				cont = 0;
				pixel -= 2;
			}
			tableroPixeles[10][i] = new Point((i * 10) - 10, pixel);
			cont++;
		}
		pixel -= 2;
		for (int i = 27; i >= 0; i--) {
			tableroPixeles[10][i] = new Point(i * 10, pixel);
		}
		pixel = 140;
		cont = 0;
		// ESCALERA 1
		tableroPixeles[45][15] = new Point(140, (45 * 10) - 30);
		tableroPixeles[45][16] = new Point(150, (45 * 10) - 30);
		tableroPixeles[44][15] = new Point(140, (44 * 10) - 30);
		tableroPixeles[44][16] = new Point(150, (44 * 10) - 30);

		tableroPixeles[40][15] = new Point(140, (40 * 10) - 30);
		tableroPixeles[40][16] = new Point(150, (40 * 10) - 30);

		tableroPixeles[45][14] = new Point(130, (45 * 10) - 30);
		tableroPixeles[45][17] = new Point(160, (45 * 10) - 30);
		tableroPixeles[44][14] = new Point(130, (44 * 10) - 30);
		tableroPixeles[44][17] = new Point(160, (44 * 10) - 30);

		tableroPixeles[40][14] = new Point(130, (40 * 10) - 30);
		tableroPixeles[40][17] = new Point(160, (40 * 10) - 30);

		// ESCALERA 2
		for (int i = 44; i >= 39; i--) {
			tableroPixeles[i][34] = new Point(330, (i * 10) - 35);
			tableroPixeles[i][35] = new Point(340, (i * 10) - 35);
			tableroPixeles[i][33] = new Point(320, (i * 10) - 35);
			tableroPixeles[i][36] = new Point(350, (i * 10) - 35);
		}

		// ESCALERA 3
		for (int i = 38; i >= 33; i--) {
			tableroPixeles[i][18] = new Point(170, (i * 10) - 35);
			tableroPixeles[i][19] = new Point(180, (i * 10) - 35);
			tableroPixeles[i][17] = new Point(160, (i * 10) - 35);
			tableroPixeles[i][20] = new Point(190, (i * 10) - 35);
		}

		// ESCALERA 4
		for (int i = 37; i >= 33; i--) {
			tableroPixeles[i][7] = new Point(60, (i * 10) - 36);
			tableroPixeles[i][6] = new Point(50, (i * 10) - 36);
			tableroPixeles[i][5] = new Point(40, (i * 10) - 36);
			tableroPixeles[i][8] = new Point(70, (i * 10) - 36);
		}

		// ESCALERA 5

		tableroPixeles[25][12] = new Point(110, (25 * 10) - 35);
		tableroPixeles[25][13] = new Point(120, (25 * 10) - 35);
		tableroPixeles[26][12] = new Point(110, (26 * 10) - 35);
		tableroPixeles[26][13] = new Point(120, (26 * 10) - 35);
		tableroPixeles[27][12] = new Point(110, (27 * 10) - 35);
		tableroPixeles[27][13] = new Point(120, (27 * 10) - 35);

		tableroPixeles[30][12] = new Point(110, (30 * 10) - 30);
		tableroPixeles[30][13] = new Point(120, (30 * 10) - 30);
		tableroPixeles[31][12] = new Point(110, (31 * 10) - 30);
		tableroPixeles[31][13] = new Point(120, (31 * 10) - 30);

		tableroPixeles[25][11] = new Point(100, (25 * 10) - 35);
		tableroPixeles[25][14] = new Point(130, (25 * 10) - 35);
		tableroPixeles[26][11] = new Point(100, (26 * 10) - 35);
		tableroPixeles[26][14] = new Point(130, (26 * 10) - 35);
		tableroPixeles[27][11] = new Point(100, (27 * 10) - 35);
		tableroPixeles[27][14] = new Point(130, (27 * 10) - 35);

		tableroPixeles[30][11] = new Point(100, (30 * 10) - 30);
		tableroPixeles[30][14] = new Point(130, (30 * 10) - 30);
		tableroPixeles[31][11] = new Point(100, (31 * 10) - 30);
		tableroPixeles[31][14] = new Point(130, (31 * 10) - 30);

		// ESCALERA 6
		for (int i = 31; i >= 26; i--) {
			tableroPixeles[i][21] = new Point(200, (i * 10) - 36);
			tableroPixeles[i][22] = new Point(210, (i * 10) - 36);
			tableroPixeles[i][20] = new Point(190, (i * 10) - 36);
			tableroPixeles[i][23] = new Point(220, (i * 10) - 36);
		}

		// ESCALERA 7
		for (int i = 29; i >= 26; i--) {
			tableroPixeles[i][34] = new Point(330, (i * 10) - 36);
			tableroPixeles[i][35] = new Point(340, (i * 10) - 36);
			tableroPixeles[i][33] = new Point(320, (i * 10) - 36);
			tableroPixeles[i][36] = new Point(350, (i * 10) - 36);
		}

		// ESCALERA 8
		for (int i = 19; i >= 17; i--) {
			tableroPixeles[i][31] = new Point(300, (i * 10) - 36);
			tableroPixeles[i][32] = new Point(310, (i * 10) - 36);
			tableroPixeles[i][30] = new Point(290, (i * 10) - 36);
			tableroPixeles[i][33] = new Point(320, (i * 10) - 36);
		}

		tableroPixeles[24][31] = new Point(300, (24 * 10) - 30);
		tableroPixeles[24][32] = new Point(310, (24 * 10) - 30);
		tableroPixeles[24][30] = new Point(290, (24 * 10) - 30);
		tableroPixeles[24][33] = new Point(320, (24 * 10) - 30);

		tableroPixeles[23][31] = new Point(300, (23 * 10) - 30);
		tableroPixeles[23][32] = new Point(310, (23 * 10) - 30);
		tableroPixeles[23][30] = new Point(290, (23 * 10) - 30);
		tableroPixeles[23][33] = new Point(320, (23 * 10) - 30);

		// ESCALERA 9
		for (int i = 23; i >= 19; i--) {
			tableroPixeles[i][13] = new Point(120, (i * 10) - 32);
			tableroPixeles[i][14] = new Point(130, (i * 10) - 32);
			tableroPixeles[i][12] = new Point(110, (i * 10) - 32);
			tableroPixeles[i][15] = new Point(140, (i * 10) - 32);
		}

		// ESCALERA 10
		for (int i = 23; i >= 19; i--) {
			tableroPixeles[i][6] = new Point(50, (i * 10) - 32);
			tableroPixeles[i][7] = new Point(60, (i * 10) - 32);
			tableroPixeles[i][5] = new Point(40, (i * 10) - 32);
			tableroPixeles[i][8] = new Point(70, (i * 10) - 32);
		}

		// ESCALERA 11
		for (int i = 13; i >= 11; i--) {
			tableroPixeles[i][16] = new Point(160, (i * 10) - 32);
			tableroPixeles[i][17] = new Point(170, (i * 10) - 32);
			tableroPixeles[i][15] = new Point(150, (i * 10) - 32);
			tableroPixeles[i][18] = new Point(180, (i * 10) - 32);
		}
		for (int i = 17; i >= 15; i--) {
			tableroPixeles[i][16] = new Point(150, (i * 10) - 32);
			tableroPixeles[i][17] = new Point(160, (i * 10) - 32);
			tableroPixeles[i][15] = new Point(140, (i * 10) - 32);
			tableroPixeles[i][18] = new Point(170, (i * 10) - 32);
		}

		// ESCALERA 12
		for (int i = 15; i >= 12; i--) {
			tableroPixeles[i][34] = new Point(330, (i * 10) - 32);
			tableroPixeles[i][35] = new Point(340, (i * 10) - 32);
			tableroPixeles[i][33] = new Point(320, (i * 10) - 32);
			tableroPixeles[i][36] = new Point(350, (i * 10) - 32);
		}

		// ESCALERA 13
		for (int i = 9; i >= 4; i--) {
			tableroPixeles[i][24] = new Point(240, (i * 10) - 30);
			tableroPixeles[i][23] = new Point(230, (i * 10) - 30);
			tableroPixeles[i][22] = new Point(220, (i * 10) - 30);
			tableroPixeles[i][25] = new Point(250, (i * 10) - 30);
		}

		// ESCALERA 14
		for (int i = 9; i >= 4; i--) {
			tableroPixeles[i][12] = new Point(120, (i * 10) - 32);
			tableroPixeles[i][13] = new Point(130, (i * 10) - 32);
			tableroPixeles[i][14] = new Point(140, (i * 10) - 32);
			tableroPixeles[i][15] = new Point(150, (i * 10) - 32);
			tableroPixeles[i][11] = new Point(110, (i * 10) - 32);
			tableroPixeles[i][16] = new Point(160, (i * 10) - 32);
		}

	}

	private javax.swing.JLabel Fondo;
	private javax.swing.JLabel Mario;
	private javax.swing.JLabel Princesa;
	private javax.swing.JLabel Mono;
	private javax.swing.JLabel Vidas;
	private javax.swing.JLabel Corazon;
	private javax.swing.JLabel Bidon;
	private javax.swing.JLabel Llama;
	private javax.swing.JLabel Final;
	private javax.swing.JLabel Inicio;
	private boolean fin = false;

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public javax.swing.JLabel getFondo() {
		return Fondo;
	}

	public void setFondo(javax.swing.JLabel fondo) {
		Fondo = fondo;
	}

	public javax.swing.JLabel getMario() {
		return Mario;
	}

	public void setMario(javax.swing.JLabel mario) {
		Mario = mario;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public javax.swing.JLabel getVidas() {
		return Vidas;
	}

	public void setVidas(javax.swing.JLabel vidas) {
		Vidas = vidas;
	}

	public javax.swing.JLabel getCorazon() {
		return Corazon;
	}

	public void setCorazon(javax.swing.JLabel corazon) {
		Corazon = corazon;
	}

	public javax.swing.JLabel getBidon() {
		return Bidon;
	}

	public void setBidon(javax.swing.JLabel bidon) {
		Bidon = bidon;
	}

	public javax.swing.JLabel getLlama() {
		return Llama;
	}

	public void setLlama(javax.swing.JLabel llama) {
		Llama = llama;
	}

	public javax.swing.JLabel getFinal() {
		return Final;
	}

	public void setFinal(javax.swing.JLabel final1) {
		Final = final1;
	}

	public boolean isPulsador() {
		return pulsador;
	}

	public void setPulsador(boolean pulsador) {
		this.pulsador = pulsador;
	}

	public javax.swing.JLabel getInicio() {
		return Inicio;
	}

	public void setInicio(javax.swing.JLabel inicio) {
		Inicio = inicio;
	}

}
