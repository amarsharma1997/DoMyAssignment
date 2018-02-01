package domyassignment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class WriteClass 
{
    StyleModel myModel;
    Color color;
    int Width=2825,Height=4200;
    int curpage=-1;
    int curx,cury,linespace,charactergap,space,MXH,MNH,MXW,MNW;
    BufferedImage bi[],cur;
    WriteClass(String style,Color clr)
    {
        curx=Width+1;
        cury=Height+1;
        linespace=150;
        charactergap=40;
        space=100;
        MXH=Height-100;
        MNH=500;
        MXW=Width-100;
        MNW=200;
        myModel=new StyleModel(style);
        this.color=clr;
    }
    public BufferedImage writeText(String s)
    {
        int i,len=s.length();
        for(i=0;i<len;i++)
        {
            changeLine();
            changePage();
            if(s.charAt(i)==' ')
            {
                curx+=space;
            }
            else if(s.charAt(i)=='\n')
            {
                curx=MXW+1;
            }
            else
            {
                CharacterProperty cp= myModel.getStyle(s.charAt(i));
                ArrayList<CoOrdinate > arr;
                try
                {
                    arr =cp.getPixels();
                }
                catch(NullPointerException e)
                {
                    continue;
                }
                int ind,curmx=curx;
                CoOrdinate c;
                for(ind=0;ind<arr.size();ind++)
                {   
                    c=arr.get(ind);
                    curmx=Math.max(curmx,curx+c.x);
                }
                if(curmx>MXW)
                {
                    changeLine();
                    changePage();
                }
                for(ind=0;ind<arr.size();ind++)
                {   
                    c=arr.get(ind);
                    cur.setRGB(curx+c.x,cury+c.y,color.getRGB());
                    curmx=Math.max(curmx,curx+c.x);
                }
                curx=curmx;
            }
        }
        try
        {
            File file=new File("F:\\img"+curpage+".jpeg");
            file.createNewFile();
            ImageIO.write(cur, "jpeg", file);
        }
        catch(IOException e)
        {
        }
        return cur;
    }
    
    void changeLine()
    {
        if(curx>MXW)
        {
            curx=MNW;
            cury=cury+linespace;
        }
    }
    
    void changePage() 
    {
        if(cury>MXH)
        {
            if(curpage>=0)
            {
                //bi[curpage]=cur;
                try
                {
                    File file=new File("F:\\img"+curpage+".jpeg");
                    file.createNewFile();
                    ImageIO.write(cur, "jpeg", file);
                }
                catch(IOException e)
                {
                    System.out.println(e);
                }
            }
            curpage++;
            cur=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            putWhite();
            curx=MNW;
            cury=MNH;
        }
    }
    void putWhite()
    {
        int i,j;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                cur.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
    }
}
