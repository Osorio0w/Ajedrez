package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tablero extends JPanel {
    private static Pieza piezaSeleccionada = null; 
    private static boolean turnoBlancas = true; 
    private LinkedList<Pieza> piezas = new LinkedList<>();
    
    public Tablero() 
    {
        piezas = new LinkedList<>();
    }
    public static void main(String[] args) throws IOException {
        Tablero tablero = new Tablero(); 
        LinkedList<Pieza> piezas = tablero.piezas;
        
        BufferedImage imagenPiezas = ImageIO.read(new File("src/main/java/imagenes/piezas.png"));
        Image[] imagenes = new Image[12];
        int ind = 0;

        for(int x = 0; x < 720; x+=60)
        {
            for (int y = 0; y < 60; y += 60) 
            {
                imagenes[ind] = imagenPiezas.getSubimage(x, y, 60, 60).getScaledInstance(128,128, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        
        // Crear las instancias de las piezas negras
        Pieza n_torre   = new Pieza(0, 0, false, "torre", piezas);
        Pieza n_caballo = new Pieza(1, 0, false, "caballo", piezas);
        Pieza n_alfil   = new Pieza(2, 0, false, "alfil", piezas);
        Pieza n_reina   = new Pieza(3, 0, false, "reina", piezas);
        Pieza n_rey     = new Pieza(4, 0, false, "rey", piezas);
        Pieza n_alfil2  = new Pieza(5, 0, false, "alfil", piezas);
        Pieza n_caballo2= new Pieza(6, 0, false, "caballo", piezas);
        Pieza n_torre2  = new Pieza(7, 0, false, "torre", piezas);
        Pieza n_peon1   = new Pieza(1, 1, false, "peon", piezas);
        Pieza n_peon2   = new Pieza(2, 1, false, "peon", piezas);
        Pieza n_peon3   = new Pieza(3, 1, false, "peon", piezas);
        Pieza n_peon4   = new Pieza(4, 1, false, "peon", piezas);
        Pieza n_peon5   = new Pieza(5, 1, false, "peon", piezas);
        Pieza n_peon6   = new Pieza(6, 1, false, "peon", piezas);
        Pieza n_peon7   = new Pieza(7, 1, false, "peon", piezas);
        Pieza n_peon8   = new Pieza(0, 1, false, "peon", piezas);
        
        // Crear las instancias de las piezas blancas
        Pieza b_torre   = new Pieza(0, 7, true, "torre", piezas);
        Pieza b_caballo = new Pieza(1, 7, true, "caballo", piezas);
        Pieza b_alfil   = new Pieza(2, 7, true, "alfil", piezas);
        Pieza b_reina   = new Pieza(3, 7, true, "reina", piezas);
        Pieza b_rey     = new Pieza(4, 7, true, "rey", piezas);
        Pieza b_alfil2  = new Pieza(5, 7, true, "alfil", piezas);
        Pieza b_caballo2= new Pieza(6, 7, true, "caballo", piezas);
        Pieza b_torre2  = new Pieza(7, 7, true, "torre", piezas);
        Pieza b_peon1   = new Pieza(1, 6, true, "peon", piezas);
        Pieza b_peon2   = new Pieza(2, 6, true, "peon", piezas);
        Pieza b_peon3   = new Pieza(3, 6, true, "peon", piezas);
        Pieza b_peon4   = new Pieza(4, 6, true, "peon", piezas);
        Pieza b_peon5   = new Pieza(5, 6, true, "peon", piezas);
        Pieza b_peon6   = new Pieza(6, 6, true, "peon", piezas);
        Pieza b_peon7   = new Pieza(7, 6, true, "peon", piezas);
        Pieza b_peon8   = new Pieza(0, 6, true, "peon", piezas); 


        JFrame ventana = new JFrame();
        ventana.setBounds(10, 10, 1024, 1024);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setUndecorated(true);
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean blanco = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (blanco) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x * 128, y * 128, 128, 128);
                        blanco = !blanco;
                    }
                    blanco = !blanco;
                }
                for (Pieza p : piezas) 
                {
                    int ind = 0;
                    if (p.nombre.equals("alfil")) 
                    {
                        ind = 0;
                    } else if (p.nombre.equals("rey")) 
                    {
                        ind = 1;
                    } else if (p.nombre.equals("caballo")) 
                    {
                        ind = 2;
                    } else if (p.nombre.equals("peon")) 
                    {
                        ind = 3;
                    } else if (p.nombre.equals("reina")) 
                    {
                        ind = 4;
                    } else if (p.nombre.equals("torre")) 
                    {
                        ind = 5;
                    }
                    if (!p.esBlanca) 
                    {
                        ind += 6;
                    }
                    g.drawImage(imagenes[ind], p.posicionX * 128, p.posicionY * 128, this);
                }
                g.setColor(Color.BLACK);
                for (int i = 0; i < 8; i++) {
                    g.drawString(Integer.toString(8 - i), 5, i * (ventana.getHeight() / 8) + (ventana.getHeight() / 8) / 2);
                    g.drawString(Character.toString((char) ('A' + i)), i * (ventana.getWidth() / 8) + (ventana.getWidth() / 8) / 2, ventana.getHeight() - 5);
                }
            }
        };

    panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        Tablero tablero = new Tablero();

        int cuadriculaX = clickX / 128;
        int cuadriculaY = clickY / 128;

        // Si no hay una pieza seleccionada, intentamos seleccionar una
        if (piezaSeleccionada == null) {
            for (Pieza p : piezas) {
                if (p.posicionX == cuadriculaX && p.posicionY == cuadriculaY && p.esBlanca == turnoBlancas) {
                    piezaSeleccionada = p;
                    break;
                }
            }
        } else {
            if (!(piezaSeleccionada.posicionX == cuadriculaX && piezaSeleccionada.posicionY == cuadriculaY)) {
                boolean movimientoExitoso = piezaSeleccionada.mover(cuadriculaX, cuadriculaY);
                
                if (!movimientoExitoso && piezaSeleccionada.nombre.equals("rey")) { 
                    boolean enroqueCorto = piezaSeleccionada.enroqueCorto(cuadriculaX, cuadriculaY);
                    boolean enroqueLargo = piezaSeleccionada.enroqueLargo(cuadriculaX, cuadriculaY);
                    if (enroqueCorto || enroqueLargo) {
                        int torreX = enroqueCorto ? 5 : 3;
                        int torreY = piezaSeleccionada.esBlanca ? 7 : 0;
                        Pieza torre = tablero.obtenerTorre(torreX, torreY);
                        if (torre != null) {
                            torre.mover(enroqueCorto ? 6 : 2, torreY);
                        }
                        piezaSeleccionada.mover(enroqueCorto ? 6 : 2, cuadriculaY);
                        panel.repaint();
                        turnoBlancas = !turnoBlancas;
                        piezaSeleccionada = null;
                        return;
                    }
                }

                if (movimientoExitoso) {
                    panel.repaint();
                    if(turnoBlancas)
                    {
                        turnoBlancas  = false;
                    } else {
                        turnoBlancas = true;
                    }
                }
            }
            piezaSeleccionada = null;
        }
    }
});



        ventana.add(panel);
        ventana.setVisible(true);
    }
    
    public Pieza obtenerPieza(int x, int y) {
        for (Pieza pieza : piezas) {
            if (pieza.posicionX == x && pieza.posicionY == y) {
                return pieza;
            }
        }
        return null;
    }

    public Pieza obtenerTorre(int x, int y) {
        for (Pieza pieza : piezas) {
            if (pieza.nombre.equals("torre") && pieza.posicionX == x && pieza.posicionY == y) {
                return pieza;
            }
        }
        return null;
    }
}
