package domyassignment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Style implements Serializable
{
    private static final long serialVersionUID = 3687103236162027697L;
    CharacterProperty cp[];
    int total;
    Style(Map<Character,CharacterProperty > Mapping)
    {
        int i=0;
        total=Mapping.size();
        cp=new CharacterProperty[total];
        for(Map.Entry m:Mapping.entrySet())
        {
            cp[i++]=(CharacterProperty)m.getValue();
        }
    }
    
    public Map<Character,CharacterProperty > getMapping()
    {
        Map<Character,CharacterProperty > Mapping=new HashMap<Character,CharacterProperty>();
        int i=0;
        for(i=0;i<total;i++)
        {
            Mapping.put(cp[i].Char(),cp[i]);
        }
        return Mapping;
    }
}
