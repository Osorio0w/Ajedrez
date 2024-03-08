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

public class Tablero extends JPanel
{
    public static void main(String[] args) throws IOException 
    {
        // Lista para almacenar las piezas del juego
        LinkedList<Pieza> ps=new LinkedList<>();
        
        // Cargar la imagen que contiene las piezas
        BufferedImage all=ImageIO.read(new File("src/main/java/imagenes/piezas.png"));
        
        // Arreglo para almacenar las imágenes de las piezas recortadas
        Image imgs[]=new Image[12];
        int ind=0;
       
        // Iterar a lo largo del eje y para recortar cada fila de piezas
        for(int x = 0; x < 720; x+=60)
        {
            for (int y = 0; y < 60; y += 60) 
            {
                imgs[ind] = all.getSubimage(x, y, 60, 60).getScaledInstance(128,128, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        
        // Crear las instancias de las piezas negras
        Pieza n_torre   = new Pieza(0, 0, false, "torre", ps);
        Pieza n_caballo = new Pieza(1, 0, false, "caballo", ps);
        Pieza n_alfil   = new Pieza(2, 0, false, "alfil", ps);
        Pieza n_reina   = new Pieza(3, 0, false, "reina", ps);
        Pieza n_rey     = new Pieza(4, 0, false, "rey", ps);
        Pieza n_alfil2  = new Pieza(5, 0, false, "alfil", ps);
        Pieza n_caballo2= new Pieza(6, 0, false, "caballo", ps);
        Pieza n_torre2  = new Pieza(7, 0, false, "torre", ps);
        Pieza n_peon1   = new Pieza(1, 1, false, "peon", ps);
        Pieza n_peon2   = new Pieza(2, 1, false, "peon", ps);
        Pieza n_peon3   = new Pieza(3, 1, false, "peon", ps);
        Pieza n_peon4   = new Pieza(4, 1, false, "peon", ps);
        Pieza n_peon5   = new Pieza(5, 1, false, "peon", ps);
        Pieza n_peon6   = new Pieza(6, 1, false, "peon", ps);
        Pieza n_peon7   = new Pieza(7, 1, false, "peon", ps);
        Pieza n_peon8   = new Pieza(0, 1, false, "peon", ps);
        
        // Crear las instancias de las piezas blancas
        Pieza b_torre   = new Pieza(0, 7, true, "torre", ps);
        Pieza b_caballo = new Pieza(1, 7, true, "caballo", ps);
        Pieza b_alfil   = new Pieza(2, 7, true, "alfil", ps);
        Pieza b_reina   = new Pieza(3, 7, true, "reina", ps);
        Pieza b_rey     = new Pieza(4, 7, true, "rey", ps);
        Pieza b_alfil2  = new Pieza(5, 7, true, "alfil", ps);
        Pieza b_caballo2= new Pieza(6, 7, true, "caballo", ps);
        Pieza b_torre2  = new Pieza(7, 7, true, "torre", ps);
        Pieza b_peon1   = new Pieza(1, 6, true, "peon", ps);
        Pieza b_peon2   = new Pieza(2, 6, true, "peon", ps);
        Pieza b_peon3   = new Pieza(3, 6, true, "peon", ps);
        Pieza b_peon4   = new Pieza(4, 6, true, "peon", ps);
        Pieza b_peon5   = new Pieza(5, 6, true, "peon", ps);
        Pieza b_peon6   = new Pieza(6, 6, true, "peon", ps);
        Pieza b_peon7   = new Pieza(7, 6, true, "peon", ps);
        Pieza b_peon8   = new Pieza(0, 6, true, "peon", ps); 
        
        // Crear la ventana del juego
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 1024, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        JPanel panel=new JPanel()
        {
            @Override
            public void paint(Graphics g) 
            {
            boolean white=true;
            // Dibujar el tablero
                for(int y = 0; y < 8; y ++)
                {
                    for(int x = 0 ; x < 8; x++)
                    {
                        if(white)
                        {
                            g.setColor(new Color(235,235, 208));
                        }else
                        {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*128, y*128, 128, 128);
                        white=!white;
                    }
                white=!white;
                }
                // Dibujar las piezas en el tablero
                for (Pieza p : ps) 
                {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("alfil")) 
                    {
                        ind = 0;
                    } else if (p.name.equalsIgnoreCase("rey")) 
                    {
                        ind = 1;
                    } else if (p.name.equalsIgnoreCase("caballo")) 
                    {
                        ind = 2;
                    } else if (p.name.equalsIgnoreCase("peon")) 
                    {
                        ind = 3;
                    } else if (p.name.equalsIgnoreCase("reina")) 
                    {
                        ind = 4;
                    } else if (p.name.equalsIgnoreCase("torre")) 
                    {
                        ind = 5;
                    }
                    if (!p.esBlanca) 
                    {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.xp * 128, p.yp * 128, this);
                }
                // Dibujar números de fila y letras de columna
                g.setColor(Color.BLACK);
                for (int i = 0; i < 8; i++) 
                {
                    // Dibujar números de fila
                    g.drawString(Integer.toString(8 - i), 5, i * (frame.getHeight() / 8) + (frame.getHeight() / 8) / 2);
                    // Dibujar letras de columna
                    g.drawString(Character.toString((char) ('A' + i)), i * (frame.getWidth() / 8) + (frame.getWidth() / 8) / 2, frame.getHeight() - 5);
                }
            }
        };
        
        panel.addMouseListener(new MouseAdapter() {
            public Pieza piezaSeleccionada = null;
            private boolean turnoBlancas = true;

            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX();
                int clickY = e.getY();

                int gridX = clickX / 128;
                int gridY = clickY / 128;

                // Iterar sobre todas las piezas para seleccionar la pieza clicada
                for (Pieza p : ps) {
                    // Verificar si la pieza está en la casilla clicada
                    if (p.xp == gridX && p.yp == gridY && p.esBlanca == turnoBlancas) {
                        piezaSeleccionada = p;
                        break;
                    }
                }

                // Verificar si ya hay una pieza seleccionada y si se hizo clic en una casilla vacía
                if (piezaSeleccionada != null && (piezaSeleccionada.xp != gridX || piezaSeleccionada.yp != gridY)) {
                    // Intentar mover la pieza seleccionada a la posición del segundo clic
                    piezaSeleccionada.move(gridX, gridY);
                    piezaSeleccionada = null; 
                    panel.repaint();

                    // Cambiar el turno al otro jugador
                    turnoBlancas = !turnoBlancas;
                }
            }
        });
        
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
