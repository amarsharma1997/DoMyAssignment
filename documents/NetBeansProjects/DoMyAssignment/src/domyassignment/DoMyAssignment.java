package domyassignment;
import javax.swing.JFileChooser;
import java.io.File;
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
        }
        catch(java.io.IOException e)
        {}
    }
}
