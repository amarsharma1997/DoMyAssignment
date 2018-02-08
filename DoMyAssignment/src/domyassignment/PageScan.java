package domyassignment;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PageScan extends javax.swing.JFrame {
    BufferedImage SelectedImage;
    int direction[][]={{-1,0},{1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    int Width=2825,Height=4200;
    //int Width=2408,Height=3580;
    boolean PixVis[][];
    int storeh[];
    Lines lns;
    BufferedImage temp;
    public PageScan(File SelectedFile) 
    {
        initComponents();
        setTitle("Page Scan");
        storeh=new int[Width];
        load(SelectedFile);
        PixVis=new boolean[Width][Height];
        lns=new Lines();
        filter();
        Scan();
        setVisible(true);
    }
    
    int mn,mx,reqmn,reqmx;
    
    void Init()
    {
        int i,j;
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                PixVis[i][j]=false;
            }
        }
    }
    
    boolean isValid(int x,int y)
    {
        if((x>=0)&&(x<Width)&&(y>=0)&&(y<Height))
        {
            return true;
        }
        return false;
    }
    
    void InitH()
    {
        int i;
        for(i=0;i<Width;i++)
        {
            storeh[i]=Height;
        }
    }
    
    int dir[][]={{1,1},{1,-1},{-1,1},{-1,-1}};
    
    void BFS(int x,int y)
    {
        int i,j,r,c,xx,yy;
        Queue<CoOrdinate> queue = new LinkedList<CoOrdinate>();
        queue.add(new CoOrdinate(x,y));
        CoOrdinate currentcrd;
        PixVis[x][y]=true;
        while(!queue.isEmpty())
        {
            currentcrd=queue.peek();
            queue.remove();
            x=currentcrd.x;
            y=currentcrd.y;
            mn=Math.min(mn,x);
            mx=Math.max(mx,x);
            storeh[x]=Math.min(storeh[x],y);
            for(i=0;i<=10;i++)
            {
                r=i;
                c=10-i;
                for(j=0;j<4;j++)
                {
                    xx=x+dir[j][0]*r;
                    yy=y+dir[j][1]*c;
                    if(isValid(xx,yy)&&(PixVis[xx][yy]==false)&&(isBlack(xx,yy)))
                    {
                        PixVis[xx][yy]=true;
                        queue.add(new CoOrdinate(xx,yy));
                    }
                }
            }
            for(i=0;i<4;i++)
            {
                xx=x+direction[i][0];
                yy=y+direction[i][1];
                if(isValid(xx,yy)&&(PixVis[xx][yy]==false)&&(isBlack(xx,yy)))
                {
                    PixVis[xx][yy]=true;
                    queue.add(new CoOrdinate(xx,yy));
                }
            }
        }
    }
    
    
    void ScanLines()
    {
        Init();
        int i,r,c,j,x,y,w=Width/2,h=0;
        int ii,jj,ttl=0;
        while(h<Height)
        {
            mx=w;
            mn=w;
            InitH();
            for(ii=w-10;ii<=w+10;ii++)
            {
                for(jj=h;jj<=h+10&&(jj<Height);jj++)
                {
                    if(PixVis[ii][jj]==true)
                    {
                        continue;
                    }
                    if((isValid(ii,jj))&&(PixVis[ii][jj]==false)&&(isBlack(ii,jj)))
                    {
                        BFS(ii,jj);
                    }
                }
            }
            int len=mx-mn;
            if((len>=reqmn)&&(len<=reqmx)) 
            {
                lns.newLine(mn, mx,storeh,Width);
                for(i=mn;i<=mx;i++)
                {
                    for(j=0;j<5;j++)
                    {
                        if(storeh[i]==Height)
                        {
                            storeh[i]=storeh[i-1];
                        }
                        SelectedImage.setRGB(i,storeh[i]+j,Color.red.getRGB());
                    }
                }
            }
            h+=10;
        }
    }
    
    
    void Scan()
    {
        int w=Width/2,h=Height/2,req=7*Width/10;
        while(true)
        {
            if(isBlack(w,h)&&(PixVis[w][h]==false))
            {
                mn=mx=w;
                FilterDFS(w,h);
                if(mx-mn>=req)
                {
                    req=mx-mn;
                    reqmn=req-25;
                    reqmx=req+25;
                    break;
                }
            }
            h++;
            if(h>=Height)
            {
                javax.swing.JOptionPane.showMessageDialog(this,"Select a Proper Page!!");
            }
        }
        ScanLines();
        Image img;
        File file;
        file=new File("F:\\Imgg0.jpeg");
        try
        {
            file.createNewFile();
            ImageIO.write(SelectedImage, "jpeg", file);
        }
        catch(Exception e)
        {
            
        }
        img=SelectedImage.getScaledInstance(700,700,Image.SCALE_DEFAULT);
        BufferedImage bi=new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(img,0,0,null);
        JLabel jl=new JLabel();
        jl.setSize(700,700);
        jl.setLocation(10,10);
        jl.setIcon(new ImageIcon(bi));
        add(jl);
    }
    
    void load(File SelectedFile)
    {
        Image img,timg;
        try
        {
            img=ImageIO.read(SelectedFile);
            img=img.getScaledInstance(Width,Height,Image.SCALE_DEFAULT);
            SelectedImage=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            SelectedImage.getGraphics().drawImage(img, 0, 0, null);
            temp=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            temp.getGraphics().drawImage(img,0,0,null);
        }
        catch(IOException e)
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Can Not Open Image.");
            dispose();
            return ;
        }
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
    
    ArrayList<CoOrdinate > current;
    void filter()
    {
        int i,j;
        boolean chng[][]=new boolean[Width][Height];
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                PixVis[i][j]=false;
                chng[i][j]=false;
            }
        }
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                if((PixVis[i][j]==false)&&(isBlack(i,j)))
                {
                    current=new ArrayList<CoOrdinate>();
                    int cnt=FilterDFS(i,j);
                    if(cnt<25)
                    {
                        CoOrdinate crd;
                        int ind;
                        for(ind=0;ind<cnt;ind++)
                        {
                            crd=current.get(ind);
                            chng[crd.x][crd.y]=true;
                        }
                    }
                }
            }
        }
        for(i=0;i<Width;i++)
        {
            for(j=0;j<Height;j++)
            {
                if(PixVis[i][j]==false)
                {
                    SelectedImage.setRGB(i,j,Color.white.getRGB());
                    temp.setRGB(i,j,Color.white.getRGB());
                }
                PixVis[i][j]=false;
                if(chng[i][j]==true)
                {
                    SelectedImage.setRGB(i,j,Color.white.getRGB());
                    temp.setRGB(i,j,Color.white.getRGB());
                }
            }
        }
    }
    
    int FilterDFS(int x,int y)
    {
        current.add(new CoOrdinate(x,y));
        mn=Math.min(x,mn);
        mx=Math.max(x,mx);
        PixVis[x][y]=true;
        int cnt=1,i,r,c;
        for(i=0;i<8;i++)
        {
            r=x+direction[i][0];
            c=y+direction[i][1];
            if((r>=0)&&(r<Width)&&(c>=0)&&(c<Height)&&(PixVis[r][c]==false)&&(isBlack(r,c)))
            {
                cnt+=FilterDFS(r,c);
            }
        }
        return cnt;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel1.setText("Style Name:-");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(429, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField1.getText().equals(""))
        {
            new JOptionPane().showMessageDialog(this,"Enter the Name First..");
            return ;
        }
        JFileChooser chooser=new JFileChooser();
        String Path=chooser.getCurrentDirectory().getAbsolutePath();
        Path=Path+"\\DoMyAssignment"+"\\Pages";
        String Name=jTextField1.getText();
        File file=new File(Path+"\\"+Name);
        if(file.exists())
        {
            jTextField1.setText("Already Taken..");
            return ;
        }
        storeName(Path,Name);
        file.mkdir();
        Path=Path+"\\"+Name;
        file=new File(Path+"\\"+Name);
        try
        {
            file.createNewFile();
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lns);
            oos.close();
            file=new File(Path+"\\"+"Image.jpeg");
            file.createNewFile();
            ImageIO.write(temp,"jpeg",file);
        }
        catch(Exception e)
        {
            
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    void storeName(String Path,String Name)
    {
        File file=new File(Path+"\\dmap");
        try
        {
            FileWriter fw=new FileWriter(file,true);
            fw.write(Name+"\n");
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
