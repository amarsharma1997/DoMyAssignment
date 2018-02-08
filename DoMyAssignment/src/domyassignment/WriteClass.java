package domyassignment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class WriteClass 
{
    StyleModel myModel;
    Color color;
    int Width=2825,Height=4200;
    //int Width=2408,Height=3580;
    int curpage=-1,curline;
    int curx,cury,linespace,charactergap,space,MXH,MNH,MXW,MNW;
    BufferedImage bi[],cur;
    int totallines;
    Lines lns;
    BufferedImage PageStyle;
    String Storepath;
    
    
    WriteClass(String style,Color clr,String Path,String Storepath,String PageName)
    {
        this.Storepath=Storepath;
        loadPageStyle(Path+"\\Pages\\"+PageName,PageName);
        curx=Width+1;
        cury=Height+1;
        curline=totallines;
        linespace=150;
        myModel=new StyleModel(style);
        this.color=clr;
    }
    
    void loadAttributes()
    {
        charactergap=40;
        space=60;
        totallines=lns.arr.size();
    }
    
    void loadPageStyle(String Path,String PageName)
    {
        File file=new File(Path+"\\Image.jpeg");
        try
        {
            Image img=ImageIO.read(file);
            PageStyle=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            PageStyle.getGraphics().drawImage(img, 0, 0, null);
            file=new File(Path+"\\"+PageName);
            ObjectInputStream oos=new ObjectInputStream(new FileInputStream(Path+"\\"+PageName));
            lns=(Lines ) oos.readObject();
            oos.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        loadAttributes();
    }
    
    public BufferedImage writeText(String s)
    {
        int i,len=s.length();
        for(i=0;i<len;i++)
        {
            changePage();
            changeLine();
            cury=lns.arr.get(curline).a[curx];
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
        //            System.out.println((curx+c.x)+" "+(cury+c.y));
                    cur.setRGB(curx+c.x,cury+c.y,color.getRGB());
                    curmx=Math.max(curmx,curx+c.x);
                }
                curx=curmx;
            }
        }
        try
        {
            File file=new File(Storepath+"\\Image"+curpage+".jpeg");
            file.createNewFile();
            ImageIO.write(cur, "jpeg", file);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return cur;
    }
    
    void changeLine()
    {
        if(curx>MXW)
        {
            curline++;
            if(curline<totallines)
            {
                curx=lns.arr.get(curline).mn;
                MXW=lns.arr.get(curline).mx;
            }
            else
            {
                changePage();
            }
        }
    }
    
    void changePage() 
    {
        if(curline>=totallines)
        {
            if(curpage>=0)
            {
                try
                {
                    File file=new File(this.Storepath+"\\Image"+curpage+".jpeg");
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
            makePage();
            curline=0;
            curx=lns.arr.get(curline).mn;
            MXW=lns.arr.get(curline).mx;
        }
    }
    void makePage()
    {
        int i,j;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                cur.setRGB(i, j, PageStyle.getRGB(i,j));
            }
        }
    }
}
