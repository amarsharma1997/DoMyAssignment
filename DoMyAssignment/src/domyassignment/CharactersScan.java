package domyassignment;
//Character input class.
//Try to use A4 size paper.Do use >8MP camera for the picture.
//Get the full size picture of the page in which characters are written.
//A-Z ,a-z, 0-9, special characters:-! @ # $ % ^ & * ( ) _ + { } [ ] | \ : ; " ' ~ , . / < > ?  

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

class TipsDialog extends JDialog implements ActionListener
{
    //Class Variables.....
    File SelectedFile;
    boolean SelectionApproval;
    JFrame Parent;
    
    
    TipsDialog(JFrame parentFrame)
    {
        //Dialog Box Property Initialization.....
        super(parentFrame,true);
        setTitle("DoMyAssignment(Select File)");
        setLayout(null);
        getContentPane().setBackground(new Color(100,50,50));
        setSize(760,460);
        setLocation(100,100);
        
        
        //Class Variables.....
        SelectionApproval=false;
        Parent=parentFrame;    

        
        //Label Declaration.....
        JLabel Note,Sequence1,Sequence2,Tip1,Tip2,Tip3,Tip4,Tip5,Label;
        Note=new JLabel("Read these points carefully to get proper results.");
        Sequence1=new JLabel("1) Use A4 paper(Without Line) and black pen and camera >8MP.");
        Sequence2=new JLabel("2) Write this sequence a-z   A-Z   0-9   !   @   #   $   %   ^   &   *   (   )   _   +   {   }   [   ]   |   \\   :   ;   '   ~   ,   .   /   <   >   ?");
        Tip1=new JLabel("3) You can change lines but not the sequence.Please Maintain a distance between two consecutive character rows.");
        Tip2=new JLabel("4) There should no extra dots or anything this may cause errors in character recognition.");
        Tip3=new JLabel("5) Get the picture carefully the picture must contain only the paper area.");
        Tip4=new JLabel("6) Capture the whole paper it doesn't matter there is unused area and Background should be white only.");
        Note.setForeground(Color.yellow);
        Sequence1.setForeground(Color.green);
        Sequence2.setForeground(Color.green);
        Tip1.setForeground(Color.green);
        Tip2.setForeground(Color.green);
        Tip3.setForeground(Color.green);
        Tip4.setForeground(Color.green);
        
        //Button For Selecting Files.....
        JButton button=new JButton("Select File.");
        button.addActionListener(this);
        
        
        //Size of Components.....
        Note.setSize(800,50);
        Sequence1.setSize(800,30);
        Sequence2.setSize(800,30);
        Tip1.setSize(800,30);
        Tip2.setSize(800,30);
        Tip3.setSize(800,30);
        Tip4.setSize(800,30);
        button.setSize(100,40);
        
        
        //Locating Components.....
        Note.setLocation(220,50);
        Sequence1.setLocation(50,110);
        Sequence2.setLocation(50,140);
        Tip1.setLocation(50,170);
        Tip2.setLocation(50,200);
        Tip3.setLocation(50,230);
        Tip4.setLocation(50,260);
        button.setLocation(320,360);
        
        
        //Adding All The Components To The TipsDialog
        add(Note);
        add(Sequence1);
        add(Sequence2);
        add(Tip1);
        add(Tip2);
        add(Tip3);
        add(Tip4);
        add(button);
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        JFileChooser Chooser=new JFileChooser();
        Chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        
        if(Chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            SelectionApproval=true;
            SelectedFile=Chooser.getSelectedFile();
        }
        dispose();
    }
}



public class CharactersScan extends javax.swing.JFrame 
{
    //Class Variables.....
    Map<Character ,CharacterProperty > Mapping;
    BufferedImage SelectedImage,temp;
    int Width,Height;
    int Maxx,Maxy,Charx,Chary,Charmny;
    int Curx,Cury,Offx,Offy,curchar=0,length;
    ArrayList<Integer > Colomn[];
    ArrayList<CoOrdinate > Current;
    boolean PixVis[][];
    int Direction[][]={{1,0},{0,1},{-1,0},{0,-1}};
    boolean flg=false;
    String Pattern;
    JLabel jl;
    
    public CharactersScan() 
    {
        //Frame Property Initialization.....
        setSize(1366,768);
        setState(JFrame.NORMAL);
        setLayout(null);
        setTitle("DoMyAssignment(Scan Characters)");
        initComponents();
        getContentPane().setBackground(new Color(100,50,50));
        jLabel2.setForeground(Color.green);
        setVisible(false);
        
        
        
        //HashMap Initialization
        Mapping=new HashMap<Character ,CharacterProperty >();
        
        Width=2480;
        Height=3508;
        PixVis=new boolean[Width+7][Height+7];
        
        
        int i;
        Colomn=new ArrayList[Width+7];
        for(i=0;i<=Width;i++)
        {
            Colomn[i]=new ArrayList<Integer >();
        }
        
        //Tips and Recognition.....
        generateTips();
    }
    
    
    
    void generateTips()
    {
        TipsDialog Tip=new TipsDialog(this);
        if(Tip.SelectionApproval==false)
        {
            dispose();
        }
        else 
        {
            recognizeCharacters(Tip.SelectedFile);
            setVisible(true);
        }
    }
    
    
        
    //Recognizing The Characters From The Submitted Image.....
    void recognizeCharacters(File SelectedFile)
    {
        Pattern="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+{}[]|\\:;'~,./<>?";
        length=Pattern.length();
        int i,j;
        Image img;
        try
        {
            img=ImageIO.read(SelectedFile);
            img=img.getScaledInstance(Width,Height,Image.SCALE_DEFAULT);
            SelectedImage=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            SelectedImage.getGraphics().drawImage(img, 0, 0, null);
        }
        catch(IOException e)
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Can Not Open Image.");
            dispose();
            return ;
        }
        Filter();
        
        
        //Removing useless dots by making them white.....
        Color black,white;
        black=new Color(0,0,0);
        white=new Color(255,255,255);
        
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                if(PixVis[i][j]==true)
                {
                    SelectedImage.setRGB(i, j,black.getRGB());
                }
                else
                {
                    SelectedImage.setRGB(i, j,white.getRGB());
                }
            }
        }
        
        Maxy=0;
        ChangeLine();
        //Make All Pixels Unvisited.....
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                PixVis[i][j]=false;
            }
        }
        
        
        //Recognize Characters One By One.....
        for(i=0;i<length;i++)
        {
            runAlgorithm(Pattern.charAt(i));
        }
        img=SelectedImage.getScaledInstance(700,700,Image.SCALE_DEFAULT);
        temp=new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);
        temp.getGraphics().drawImage(img, 0, 0, null);
        //flg=true;
        jl=new JLabel();
        jl.setSize(700,700);
        jl.setLocation(10,10);
        jl.setIcon(new ImageIcon(temp));
        add(jl);
    }
    int cur=0;
    
    void runAlgorithm(char c)
    {
        CharacterProperty Pxls=new CharacterProperty(c);
        int run;
        if(Pxls.set==1)
        {
            //1 DFS Offset With Max Height.....
            run=1;
        }
        else if(Pxls.set==2)
        {
            //2 DFS Offset With Max Height.....
            run=2;
        }
        else if(Pxls.set==3)
        {
            //3 DFS Offset With Max Height.....
            run=3;
        }
        else
        {
            //1 DFS Offset With Middle Height.....
            run=1;
        }
        Charx=Width;
        Chary=0;
        Current=new ArrayList<CoOrdinate>();
        Charmny=Height;
        while(run>0)
        {
            while((Curx<=Maxx)&&(run>0))
            {
                if((PixVis[Curx][Cury]==false)&&(isBlack(Curx,Cury)))
                {
                    run--;
                    DepthFirstSearch(Curx,Cury);
                    if(Current.size()<50)
                    {
                        run++;
                    }
                }
                Cury++;
                if(Cury==Maxy)
                {
                    Cury=Offy;
                    Curx++;
                }   
            }
            if(Curx>Maxx)            
            {
                ChangeLine();
            }
        }
        
        int i,Total=Current.size();
        CoOrdinate crd;
        if(Pxls.set==4)
        {
            Chary=(Chary+Charmny)/2-5;
        }
        if(this.cur==0)
        {
            for(i=0;i<Total;i++)
            {
                crd=Current.get(i);
                crd=new CoOrdinate(crd.x-Charx,crd.y-Chary);
                Pxls.addPixel(crd);
                SelectedImage.setRGB(crd.x+Charx, crd.y+Chary,Color.red.getRGB());
            }
            cur=1;
        }
        else
        {
            for(i=0;i<Total;i++)
            {
                crd=Current.get(i);
                crd=new CoOrdinate(crd.x-Charx,crd.y-Chary);
                Pxls.addPixel(crd);
                SelectedImage.setRGB(crd.x+Charx, crd.y+Chary,Color.blue.getRGB());
            }
            this.cur=0;
        }
        Mapping.put(c, Pxls);
    }
    
    
    boolean ChangeLine()
    {
        Cury=Offy=pass(Maxy,0);
        if(Cury>=Height)
        {
            return false;
        }
        Maxy=pass(Offy,1);
        
        Curx=Offx=Ends(1);
        Maxx=Ends(-1);
        int i,j;
        return true;
    }
    
    
    boolean isBlack(int x,int y)
    {
        try
        {
            Color clr;
            int Blackd,Whited,Red,Blue,Green;
            clr=new Color(SelectedImage.getRGB(x,y));
            Red=clr.getRed();
            Blue=clr.getBlue();
            Green=clr.getGreen();
            Whited=(255-Red)*(255-Red)+(255-Green)*(255-Green)+(255-Blue)*(255-Blue);
            Blackd=Red*Red+Green*Green+Blue*Blue;
            if(Blackd<Whited)
            {
                return true;
            }
        }
        catch(Error e)
        {
            return false;
        }
        return false;
    }
    
    



    //To Find All The Connected Points.....
    void DepthFirstSearch(int X,int Y)
    {
        PixVis[X][Y]=true;
        Current.add(new CoOrdinate(X,Y));
        Charx=Math.min(Charx,X);
        Chary=Math.max(Chary,Y);
        Charmny=Math.min(Charmny,Y);
        int i,x,y;
        for(i=0;i<4;i++)
        {
            x=X+Direction[i][0];
            y=Y+Direction[i][1];
            if((x>=0)&&(x<Width)&&(y>=0)&&(y<Height)&&(PixVis[x][y]==false)&&(isBlack(x,y)))
            {
                DepthFirstSearch(x,y);
            }
        }
    }
    
    
    
    

    //Returns the Height With first Black Point.....
    int pass(int x,int type)
    {
        //Leave all the useless lines.....
        if(x>=Height)
        {
            return Height;
        }
        int i=0;
        while((i<Width)&&(isBlack(i,x)))
        {
            i++;
        }
        while((i<Width)&&(!isBlack(i,x)))
        {
            i++;
        }
        while((i<Width)&&(isBlack(i,x)))
        {
            i++;
        }
        if(type==0)
        {
            if(i==Width)
            {
                return pass(x+1,type);
            }
            else
            {
                return x;
            }
        }
        else
        {
            if(i==Width)
            {
                return x;
            }
            else
            {
                return pass(x+1,type);
            }
        }
    };
    
    
    int Ends(int dx)
    {
        int i,j;
        if(dx==1)
        {
            int mx=0;
            for(i=Cury;i<Maxy;i++)
            {
                j=0;
                while((j<Width)&&(isBlack(j,i)))
                {
                    j++;
                }
                mx=Math.max(mx,j);
            }
            return mx;
        }
        else
        {
            int mn=Width-1;
            for(i=Cury;i<Maxy;i++)
            {
                j=Width-1;
                while((j>=0)&&(isBlack(j,i)))
                {
                    j--;
                }
                mn=Math.min(mn,j);
            }
            return mn;
        }
    }


    void Filter()
    {
        int i,j;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                PixVis[i][j]=false;
            }
        }
        int cnt,k;
        CoOrdinate crd;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                if(isBlack(i,j)&&(PixVis[i][j]==false))
                {
                    Current=new ArrayList<CoOrdinate>();
                    FilterDFS(i,j);
                    cnt=Current.size();
                    if(cnt<=50)
                    {
                        for(k=0;k<cnt;k++)
                        {
                            crd=Current.get(k);
                            PixVis[crd.x][crd.y]=false;
                        }
                    }
                }
            }
        }
    }
    
    void FilterDFS(int X,int Y)
    {
        int i,x,y;
        PixVis[X][Y]=true;
        Current.add(new CoOrdinate(X,Y));
        for(i=0;i<4;i++)
        {
            x=X+Direction[i][0];
            y=Y+Direction[i][1];
            if((x>=0)&&(x<Width)&&(y>=0)&&(y<Height)&&isBlack(x,y)&&(PixVis[x][y]==false))
            {
                FilterDFS(x,y);
            }
        }
    }
    
    void storeNewStyleName(String Path,String NewStyle)
    {
        try
        {
            FileWriter fw=new FileWriter(Path+"\\dmas",true);
            fw.write(NewStyle+"\n");
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            //FileNotFoundException from fos
            //Ioexception from oos
        }
    }
    
    
    void storeMappings(File file)
    {
        try
        {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(new Style(Mapping));
            oos.close();
        }
        catch(IOException  e)
        {
            System.out.println(e);
        }
    }
    
    boolean StoreStyle(String StyleName)
    {
        JFileChooser jc=new JFileChooser();
        String cPath=jc.getCurrentDirectory().getAbsolutePath()+"\\DoMyAssignment";
        String Path=cPath+"\\Styles\\"+StyleName;
        File file=new File(Path);
        if(file.exists())
        {
            jTextField1.setText("Already Taken");
            return false;
        }
        file.mkdir();
        Path=Path+"\\"+StyleName;
        file=new File(Path);
        try
        {
            file.createNewFile();
        }
        catch(IOException e)
        {}
        storeNewStyleName(cPath,StyleName);
        storeMappings(file);
        return true;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jButton1.setText("Perfect!!!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Style Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(StoreStyle(jTextField1.getText()))
       {
           dispose();
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
