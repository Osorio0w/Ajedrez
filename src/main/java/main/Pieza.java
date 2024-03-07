package main;

import java.util.LinkedList;

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
    public void move(int xp,int yp)
    {
        // Busca entre todas las piezas
        ps.stream().filter((p) -> (p.xp==xp&&p.yp==yp)).forEachOrdered((p) -> 
        {
            p.kill();  // Si encuentra una pieza en la posición de destino, la elimina
        });
        this.xp = xp; // Actualiza la posición x de la pieza
        this.yp = yp; // Actualiza la posición y de la pieza
    }
    public void kill()
    {
        ps.remove(this); // Elimina esta pieza de la lista de piezas
    }
}
