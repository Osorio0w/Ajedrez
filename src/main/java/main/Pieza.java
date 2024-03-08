package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pieza {
    int xp;
    int yp;
    boolean esBlanca;
    LinkedList<Pieza> ps;
    String name;

    public Pieza(int xp, int yp, boolean esBlanca, String n, LinkedList<Pieza> ps) {
        this.xp = xp;
        this.yp = yp;
        this.esBlanca = esBlanca;
        this.ps = ps;
        name = n;
        ps.add(this);
    }

    // Método para mover la pieza a una posición específica
    public void move(int xp, int yp) {
        // Validar el movimiento según el tipo de pieza
        switch (name.toLowerCase()) {
            case "torre" -> {
                if (xp == this.xp || yp == this.yp) {
                    movePiece(xp, yp);
                }
            }

            case "caballo" -> {
                if ((Math.abs(xp - this.xp) == 2 && Math.abs(yp - this.yp) == 1) ||
                        (Math.abs(xp - this.xp) == 1 && Math.abs(yp - this.yp) == 2)) {
                    movePiece(xp, yp);
                }
            }

            case "alfil" -> {
                if (Math.abs(xp - this.xp) == Math.abs(yp - this.yp)) {
                    movePiece(xp, yp);
                }
            }

            case "reina" -> {
                if (xp == this.xp || yp == this.yp || Math.abs(xp - this.xp) == Math.abs(yp - this.yp)) {
                    movePiece(xp, yp);
                }
            }

            case "rey" -> {
                if (Math.abs(xp - this.xp) <= 1 && Math.abs(yp - this.yp) <= 1) {
                    movePiece(xp, yp);
                }
            }

            case "peon" -> {
                if (this.esBlanca) {
                    // Si la pieza es blanca
                    if (yp == this.yp - 1 && xp == this.xp) {
                        // Avance de una casilla hacia adelante
                        movePiece(xp, yp);
                    } else if (yp == this.yp - 2 && xp == this.xp && this.yp == 6) {
                        // Avance de dos casillas hacia adelante desde la posición inicial
                        movePiece(xp, yp);
                    } else if (Math.abs(xp - this.xp) == 1 && yp == this.yp - 1) {
                        // Captura diagonal de una pieza contraria
                        movePiece(xp, yp);
                    }
                } else {
                    // Si la pieza es negra
                    if (yp == this.yp + 1 && xp == this.xp) {
                        // Avance de una casilla hacia adelante
                        movePiece(xp, yp);
                    } else if (yp == this.yp + 2 && xp == this.xp && this.yp == 1) {
                        // Avance de dos casillas hacia adelante desde la posición inicial
                        movePiece(xp, yp);
                    } else if (Math.abs(xp - this.xp) == 1 && yp == this.yp + 1) {
                        // Captura diagonal de una pieza contraria
                        movePiece(xp, yp);
                    }
                }
            }
        }
    }

    
    private void movePiece(int xp, int yp) {
        // Copiar la lista de piezas para evitar ConcurrentModificationException
        List<Pieza> copy = new LinkedList<>(ps);
        
        for (Pieza p : copy) {
            if (p.xp == xp && p.yp == yp) {
                p.kill();  // Si encuentra una pieza en la posición de destino, la elimina
            }
        }

        // Actualizar la posición x e y de la pieza
        this.xp = xp;
        this.yp = yp;
    }

    // Método para eliminar esta pieza de la lista de piezas
    public void kill() {
        Iterator<Pieza> iterator = ps.iterator();
        while (iterator.hasNext()) {
            Pieza p = iterator.next();
            if (p == this) {
                iterator.remove(); 
                break; 
            }
        }
    }
}
