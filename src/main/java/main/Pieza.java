package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pieza {
    int posicionX;
    int posicionY;
    boolean esBlanca;
    LinkedList<Pieza> piezas;
    String nombre;

    public Pieza(int posicionX, int posicionY, boolean esBlanca, String n, LinkedList<Pieza> piezas) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.esBlanca = esBlanca;
        this.piezas = piezas;
        nombre = n;
        piezas.add(this);
    }

    // Método para mover la pieza a una posición específica
    public void move(int x, int y) {
        switch (nombre) {
            case "torre":
                if (mueveLineaRecta(x, y)) {
                    moverPieza(x, y);
                }
                break;

            case "caballo":
                if (mueveEnL(x, y)) {
                    moverPieza(x, y);
                }
                break;

            case "alfil":
                if (movimientoDiagonal(x, y)) {
                    moverPieza(x, y);
                }
                break;

            case "reina":
                if (mueveLineaRecta(x, y) || movimientoDiagonal(x, y)) {
                    moverPieza(x, y);
                }
                break;

            case "rey":
                if (movimientoRey(x, y)) {
                    moverPieza(x, y);
                }
                break;

            case "peon":
                if (movimientoPeon(x, y)) {
                    moverPieza(x, y);
                }
                break;
        }
    }

    private boolean mueveLineaRecta(int x, int y) {
        return x == posicionX || y == posicionY;
    }

    private boolean mueveEnL(int x, int y) {
        return (Math.abs(x - posicionX) == 2 && Math.abs(y - posicionY) == 1) ||
               (Math.abs(x - posicionX) == 1 && Math.abs(y - posicionY) == 2);
    }

    private boolean movimientoDiagonal(int x, int y) {
        return Math.abs(x - posicionX) == Math.abs(y - posicionY);
    }

    private boolean movimientoRey(int x, int y) {
        return Math.abs(x - posicionX) <= 1 && Math.abs(y - posicionY) <= 1;
    }

    private boolean movimientoPeon(int x, int y) {
        if (esBlanca) {
            if (y == posicionY - 1 && x == posicionX) {
                return true;
            } else if (y == posicionY - 2 && x == posicionX && posicionY == 6) {
                return true;
            } else return Math.abs(x - posicionX) == 1 && y == posicionY - 1;
        } else {
            if (y == posicionY + 1 && x == posicionX) {
                return true;
            } else if (y == posicionY + 2 && x == posicionX && posicionY == 1) {
                return true;
            } else return Math.abs(x - posicionX) == 1 && y == posicionY + 1;
        }
    }

    private void moverPieza(int x, int y) {
        for (Pieza pieza : piezas) {
            if (pieza.posicionX == x && pieza.posicionY == y) {
                pieza.comer();
            }
        }
        posicionX = x;
        posicionY = y;
    }

    public void comer() {
        Iterator<Pieza> iterator = piezas.iterator();
        while (iterator.hasNext()) {
            Pieza pieza = iterator.next();
            if (pieza == this) {
                iterator.remove();
                break;
            }
        }
    }
}