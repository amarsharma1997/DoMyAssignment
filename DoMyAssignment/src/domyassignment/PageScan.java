package domyassignment;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PageScan extends javax.swing.JFrame {
    BufferedImage SelectedImage;
    int direction[][]={{-1,0},{1,0},{0,1},{0,-1}};
    int Width=2408,Height=3580;
    boolean PixVis[][];
    public PageScan(File SelectedFile) 
    {
        initComponents();
        load(SelectedFile);
        setVisible(true);
        PixVis=new boolean[Width][Height];
        filter();
        Scan();
    }
    
    void Scan()
    {
        
    }
    
    
    void load(File SelectedFile)
    {
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
                    if(cnt<50)
                    {
                        CoOrdinate crd;
                        int ind;
                        for(ind=0;ind<current.size();i++)
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
                PixVis[i][j]=false;
                if(chng[i][j]==true)
                {
                    SelectedImage.setRGB(i,j,Color.white.getRGB());
                }
            }
        }
    }
    
    int FilterDFS(int x,int y)
    {
        current.add(new CoOrdinate(x,y));
        PixVis[x][y]=true;
        int cnt=1,i,r,c;
        for(i=0;i<4;i++)
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

        jLabel1.setText("Style Name:-");

        jButton1.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
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
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
