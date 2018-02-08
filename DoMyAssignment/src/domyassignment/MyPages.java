package domyassignment;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class MyPages extends javax.swing.JFrame {
    ArrayList<String > Arrs;
    int current,cur;
    String InitPath;
    ArrayList<ImageIcon > ArrImage;
    
    
    public MyPages() 
    {
        initComponents();
        setTitle("MyPages");
        load();
        this.current=0;
        cur=0;
        jButton1.setEnabled(false);
        getContentPane().setBackground(new Color(100,50,50));
        if(Arrs.size()==1)
        {
            jButton2.setEnabled(false);
        }
        loadCurrent();
    }
    
    
    
    void load()
    {
        String s;
        s=new JFileChooser().getCurrentDirectory().getAbsolutePath();
        InitPath=s+"\\DoMyAssignment\\Pages";
        s=s+"\\DoMyAssignment\\Pages\\dmap";
        File file = new File(s);
        Arrs=new ArrayList<String >();
        ArrImage=new ArrayList<ImageIcon >();
        try
        {
            String inp;
            Scanner sc=new Scanner(file);
            while(sc.hasNext())
            {
                inp=sc.next();
                Arrs.add(inp);
                ArrImage.add(new ImageIcon(loadImage(inp)));
            }
            sc.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    String getSelected()
    {
        return Arrs.get(current);
    }
    
    BufferedImage loadImage(String s)
    {
        Image img;
        BufferedImage SelectedImage;
        File SelectedFile=new File(InitPath+"\\"+s+"\\Image.jpeg");
        int Width=700,Height=700;
        try
        {
            img=ImageIO.read(SelectedFile);
            img=img.getScaledInstance(Width,Height,Image.SCALE_DEFAULT);
            SelectedImage=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
            SelectedImage.getGraphics().drawImage(img, 0, 0, null);
            return SelectedImage;
        }
        catch(IOException e)
        {
            System.out.println(e);
            javax.swing.JOptionPane.showMessageDialog(this,"Can Not Open Image.");
        }
        return null;
    }
    
    void loadCurrent()
    {
        if(Arrs.size()==1)
        {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
        else if(cur==0)
        {
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
        }
        else if(cur==Arrs.size()-1)
        {
            jButton2.setEnabled(false);
            jButton1.setEnabled(true);
        }
        else
        {
            jButton2.setEnabled(true);
            jButton1.setEnabled(true);
        }
        jLabel1.setSize(700,700);
        jLabel1.setIcon(ArrImage.get(cur));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel1.setText("No Available Pages");
        jLabel1.setMaximumSize(new java.awt.Dimension(700, 700));
        jLabel1.setMinimumSize(new java.awt.Dimension(700, 700));
        jLabel1.setPreferredSize(new java.awt.Dimension(700, 700));

        jButton1.setText("Prev");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Select");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)))
                .addContainerGap(303, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(cur+1<Arrs.size())
        {
            cur++;
            loadCurrent();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(cur>0)
        {
            cur--;
            loadCurrent();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        current=cur;
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
