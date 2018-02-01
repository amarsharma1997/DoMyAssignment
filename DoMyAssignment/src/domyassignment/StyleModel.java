package domyassignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;

public class StyleModel 
{
    String Name;
    Map<Character,CharacterProperty > Mapping;
    StyleModel(String s)
    {
        Name=s;
        Mapping=new HashMap<Character,CharacterProperty >();
        LoadModel(s);
    }
    void LoadModel(String s)
    {
        String Path=new JFileChooser().getCurrentDirectory().getAbsolutePath()+"\\DoMyAssignment\\Styles\\"+s+"\\"+s;
        Style style;
        try
        {
            ObjectInputStream oos=new ObjectInputStream(new FileInputStream(Path));
            style=(Style ) oos.readObject();
            oos.close();
            Mapping=style.getMapping();
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    CharacterProperty getStyle(char c)
    {
        return Mapping.get(c);
    }
}
