package domyassignment;

import java.io.Serializable;

public class Line implements Serializable
{
    public int mn,mx;
    public int a[];
    public Line(int mn,int mx)
    {
        this.mn=mn;
        this.mx=mx;
        a=new int[2825];
    }
}
