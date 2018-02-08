package domyassignment;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
//Entry Class

public class DoMyAssignment {
    public static void main(String[] args) 
    {
        isItFirstTime();
        new Start();
    }    
    public static void isItFirstTime()
    {
        String Path =new JFileChooser().getCurrentDirectory().getAbsolutePath()+"\\DoMyAssignment";
        File file=new File(Path);
        if(file.exists())
        {
            return ;
        }
        //if DoMyAssignment Does not Exist then create.....
        file.mkdir();
        try
        {
            new File(Path+"\\dmas").createNewFile();
            new File(Path+"\\Styles").mkdir();
            new File(Path+"\\Pages").mkdir();
            new File(Path+"\\Outputs").mkdir();
            new File(Path+"\\Pages\\dmap").createNewFile();
            new File(Path+"\\Pages\\BlankPage").mkdir();
            new File(Path+"\\Pages\\BlankPage\\BlankPage").createNewFile();
        }
        catch(java.io.IOException e)
        {}
        createBlankPage(Path+"\\Pages");
    }
    public static void createBlankPage(String s)
    {
        int Width=2825,Height=4200;
        int linespace=120;
        int MXH=Height-100;
        int MNH=500;
        int MXW=Width-100;
        int MNW=200;
        int a[]=new int[2825],i=MNH,j;
        Lines lns=new Lines();
        while(i<=MXH)
        {
            for(j=MNW;j<=MXW;j++)
            {
                a[j]=i;
            }
            lns.newLine(MNW,MXW,a,Width);
            i+=linespace;
        }
        BufferedImage img=createImage();
        store(img,lns,s);
    }
    
    public static void store(BufferedImage temp,Lines lns,String Path)
    {
        //storeName(Path,Name);
        File file=new File(Path+"\\BlankPage\\BlankPage");
        try
        {
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lns);
            oos.close();
            file=new File(Path+"\\BlankPage\\"+"Image.jpeg");
            file.createNewFile();
            ImageIO.write(temp,"jpeg",file);
            file=new File(Path+"\\dmap");
            FileWriter fw=new FileWriter(file,true);
            fw.write("BlankPage"+"\n");
            fw.close();
        
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static BufferedImage createImage()
    {
        BufferedImage img=new BufferedImage(2825,4200,BufferedImage.TYPE_INT_RGB);
        int i,j,Width=2825,Height=4200;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                img.setRGB(i,j,Color.white.getRGB());
            }
        }
        return img;
    }
}
