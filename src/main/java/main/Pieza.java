package main;

import java.util.Iterator;
import java.util.LinkedList;

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

    public boolean mover(int x, int y) {
        if (nombre.equals("rey") && (x == 2 || x == 6) && (y == 0 || y == 7)) {
            return enroque(x, y);
        }

        if (puedeMoverse(x, y)) {
            LinkedList<Pieza> copiaPiezas = new LinkedList<>(piezas); // Crear una copia de la lista 
            for (Pieza pieza : copiaPiezas) { // Iterar sobre la copia de la lista
                                              // Si quitan esta copia se genera un error de excepción al capturar alguna pieza porfa no lo quiten
                if (pieza.posicionX == x && pieza.posicionY == y) {
                    pieza.comer();
                }
            }
            posicionX = x;
            posicionY = y;
            return true; 
        } else {
            return false;
        }
    }
    public boolean movimientoResuelveJaque(int x, int y) {
        int antiguaPosX = this.posicionX;
        int antiguaPosY = this.posicionY;
        this.posicionX = x;
        this.posicionY = y;
        boolean resuelveJaque = !estaEnJaque();
        this.posicionX = antiguaPosX;
        this.posicionY = antiguaPosY;
        return resuelveJaque;
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
        if (!reyEnJaqueDespuesMovimiento(x, y)) {
            return Math.abs(x - posicionX) <= 1 && Math.abs(y - posicionY) <= 1;
        } else {
            return false; // El rey no puede moverse a una posición donde esté en jaque
        }
    }

    public boolean reyEnJaqueDespuesMovimiento(int x, int y) {
        // Simular el movimiento
        int antiguaPosX = this.posicionX;
        int antiguaPosY = this.posicionY;
        this.posicionX = x;
        this.posicionY = y;

        // Verificar si el rey del jugador que mueve ya no está en jaque después del movimiento
        boolean resuelveJaque = !estaEnJaque();

        // Deshacer el movimiento
        this.posicionX = antiguaPosX;
        this.posicionY = antiguaPosY;

        return resuelveJaque;
    }


    private boolean movimientoPeon(int x, int y) {
    if (Math.abs(x - posicionX) == 1 && Math.abs(y - posicionY) == 1) {
        for (Pieza pieza : piezas) {
            if (pieza.posicionX == x && pieza.posicionY == y && pieza.esBlanca != this.esBlanca) {
                return true;
            }
        }
        return false;
    }
    if (esBlanca) {
        if (y == posicionY - 1 && x == posicionX) {
            return true;
        } else if (y == posicionY - 2 && x == posicionX && posicionY == 6) {
            return true;
        }
    } else {
        if (y == posicionY + 1 && x == posicionX) {
            return true;
        } else if (y == posicionY + 2 && x == posicionX && posicionY == 1) {
            return true;
        }
    }
    return false;
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
    public boolean estaEnJaque() {
        int reyX = -1, reyY = -1;
        for (Pieza pieza : piezas) {
            if (pieza.nombre.equals("rey") && pieza.esBlanca == this.esBlanca) {
                reyX = pieza.posicionX;
                reyY = pieza.posicionY;
                break;
            }
        }
        for (Pieza pieza : piezas) {
            if (pieza.esBlanca != this.esBlanca && pieza.puedeMoverse(reyX, reyY)) {
                if (hayPiezasBloqueando(pieza, reyX, reyY)) {
                    continue; 
                }
                return true; 
            }
        }

        return false; 
    }

    private boolean hayPiezasBloqueando(Pieza piezaAtacante, int reyX, int reyY) {
        if (piezaAtacante.nombre.equals("torre") || piezaAtacante.nombre.equals("alfil") || piezaAtacante.nombre.equals("reina")) {
            int difX = Integer.compare(reyX, piezaAtacante.posicionX);
            int difY = Integer.compare(reyY, piezaAtacante.posicionY);

            int x = piezaAtacante.posicionX + difX;
            int y = piezaAtacante.posicionY + difY;

            while (x != reyX || y != reyY) {
                for (Pieza pieza : piezas) {
                    if (pieza.posicionX == x && pieza.posicionY == y) {
                        return true; 
                    }
                }
                x += difX;
                y += difY;
            }
        }
        return false; // No hay piezas bloqueando la línea de ataque
    }
    public boolean estaEnJaqueMate() {
        // Verificar si el rey está en jaque
        if (!estaEnJaque()) {
            return false; // El rey no está en jaque mate
        }
        // Verificar si el rey puede moverse a una posición segura
        for (int i = posicionX - 1; i <= posicionX + 1; i++) {
            for (int j = posicionY - 1; j <= posicionY + 1; j++) {
                if (puedeMoverse(i, j)) {
                    // Si el rey puede moverse a una posición segura, no está en jaque mate
                    return false;
                }
            }
        }
        return true;
    }
    public boolean puedeMoverse(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        for (Pieza pieza : piezas) {
            if (pieza.posicionX == x && pieza.posicionY == y && pieza.esBlanca == this.esBlanca) {
                return false;
            }
        }
        return switch (nombre) {
            case "torre" -> mueveLineaRecta(x, y);
            case "caballo" -> mueveEnL(x, y);
            case "alfil" -> movimientoDiagonal(x, y);
            case "reina" -> mueveLineaRecta(x, y) || movimientoDiagonal(x, y);
            case "rey" -> movimientoRey(x, y) || enroqueCorto(x, y) || enroqueLargo(x, y);
            case "peon" -> movimientoPeon(x, y);
            default -> false;
        };
    }
    
    public boolean enroqueCorto(int x, int y) {
        if (!this.esBlanca && y == 7 && x == 6 && this.posicionX == 4 && this.posicionY == 7) {
            Pieza torre = obtenerTorre(7, 7);
            return torre != null && !hayPiezasEntre(this.posicionX, this.posicionY, torre.posicionX, torre.posicionY);
        } else if (this.esBlanca && y == 0 && x == 6 && this.posicionX == 4 && this.posicionY == 0) {
            Pieza torre = obtenerTorre(7, 0);
            return torre != null && !hayPiezasEntre(this.posicionX, this.posicionY, torre.posicionX, torre.posicionY);
        }
        return false;
    }
    
    public boolean enroqueLargo(int x, int y) {
        if (!this.esBlanca && y == 7 && x == 2 && this.posicionX == 4 && this.posicionY == 7) {
            Pieza torre = obtenerTorre(0, 7);
            return torre != null && !hayPiezasEntre(this.posicionX, this.posicionY, torre.posicionX, torre.posicionY);
        } else if (this.esBlanca && y == 0 && x == 2 && this.posicionX == 4 && this.posicionY == 0) {
            Pieza torre = obtenerTorre(0, 0);
            return torre != null && !hayPiezasEntre(this.posicionX, this.posicionY, torre.posicionX, torre.posicionY);
        }
        return false;
    }
    
    private Pieza obtenerTorre(int x, int y) {
        for (Pieza pieza : piezas) {
            if (pieza.nombre.equals("torre") && pieza.esBlanca == this.esBlanca && pieza.posicionX == x && pieza.posicionY == y) {
                return pieza;
            }
        }
        return null;
    }
    private boolean enroque(int x, int y) {
        if ((x == 2 || x == 6) && (y == 0 || y == 7)) {
            Pieza torre;
            if (x == 2) {
                torre = obtenerTorre(0, y);
            } else {
                torre = obtenerTorre(7, y);
            }
            if (torre != null && !hayPiezasEntre(posicionX, posicionY, torre.posicionX, torre.posicionY)) {
                torre.mover((x == 2) ? 3 : 5, y); 
                posicionX = x; 
                return true; 
            }
        }
        return false;
    }
    
    private boolean hayPiezasEntre(int x1, int y1, int x2, int y2) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        for (Pieza pieza : piezas) {
            if (pieza.posicionX > minX && pieza.posicionX < maxX && pieza.posicionY > minY && pieza.posicionY < maxY) {
                return true;
            }
        }
        return false;
    }

}