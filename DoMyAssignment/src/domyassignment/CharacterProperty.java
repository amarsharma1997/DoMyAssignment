package domyassignment;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CharacterProperty implements Serializable
{
    //Class Variables.....
    char c;
    int set;
    ArrayList<CoOrdinate > Pixels;
    
    
   
    CharacterProperty(char c)
    {
        this.c=c;
        if((c=='i')||(c=='j')||(c==';')||(c==':')||(c=='!')||(c=='?'))
        {
            set=2;
        }
        else if(c=='%')
        {
            set=3;
        }
        else if((c=='g')||(c=='p')||(c=='q')||(c=='y'))
        {
            set=4;
        }
        else
        {
            set=1;
        }
        Pixels = new ArrayList<CoOrdinate>();
    }
    
    public char Char()
    {
        return c;
    }
    
    //Adds Pixels Set For The Character..... 
    public void addPixel(CoOrdinate crd)
    {
        Pixels.add(crd);
    }
    
    public ArrayList<CoOrdinate> getPixels()
    {
        return Pixels;
    }
}
