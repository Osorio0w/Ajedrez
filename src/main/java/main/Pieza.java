package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pieza 
{
    int xp;
    int yp;
    boolean esBlanca;
    LinkedList<Pieza> ps;
    String name;
    public Pieza(int xp, int yp, boolean esBlanca,String n, LinkedList<Pieza> ps) 
    {
        this.xp = xp;
        this.yp = yp;
        this.esBlanca = esBlanca;
        this.ps=ps;
        name=n;
        ps.add(this);
    }
    
    // Método para mover la pieza a una posición específica
    public void move(int xp, int yp) 
    {
        // Copiar la lista de piezas para un error de excepcion
        List<Pieza> copy = new LinkedList<>(ps);

        // Buscar entre todas las piezas en la copia
        for (Pieza p : copy) 
        {
            if (p.xp == xp && p.yp == yp) 
            {
                p.kill();  // Si encuentra una pieza en la posición de destino, la elimina
            }
        }

        this.xp = xp; // Actualizar la posición x de la pieza
        this.yp = yp; // Actualizar la posición y de la pieza
    }

    
    
    public void kill() {
    Iterator<Pieza> iterator = ps.iterator();
    while (iterator.hasNext()) {
        Pieza p = iterator.next();
        if (p == this) {
            iterator.remove(); // Elimina esta pieza de la lista de piezas
            break; // Termina la iteración una vez que se ha eliminado la pieza
        }
    }
}

}
