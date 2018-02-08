package domyassignment;
import domyassignment.Line;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;


public class Lines implements Serializable
{
    private static final long serialVersionUID = 1111111111111111111L;
    public ArrayList<Line > arr;
    Lines()
    {
        arr = new ArrayList<Line>();
    }
    
    void newLine(int mn,int mx,int arr[],int size)
    {
        int i;
        Line lns=new Line(mn,mx);
        for(i=0;i<size;i++)
        {
            lns.a[i]=3580;
        }
        for(i=mn;i<=mx;i++)
        {
            if(arr[i]==3580)
            {
                lns.a[i]=lns.a[i-1];
            }
            else
            {
                lns.a[i]=arr[i];
            }
        }
        this.arr.add(lns);
    }  
}
