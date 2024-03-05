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
    
    public void move(int xp,int yp)
    {
        ps.stream().filter((p) -> (p.xp==xp&&p.yp==yp)).forEachOrdered((p) -> 
        {
            p.kill();
        });
        this.xp=xp;
        this.yp=yp;
    }
    public void kill()
    {
        ps.remove(this);
    }
}
