package domyassignment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Assignment extends javax.swing.JFrame 
{
    WriteClass wobj;
    String Cpath;
    ButtonGroup group;
    Color clr;
    MyPages Mypage;
    String InitPath;
    public Assignment() 
    {
        setState(JFrame.NORMAL);
        setTitle("Convert Here");
        initComponents();
        Mypage=new MyPages();
        jLabel1.setForeground(Color.green);
        jLabel3.setForeground(Color.green);
        getContentPane().setBackground(new Color(100,50,50));
        jRadioButton1.setBackground(new Color(100,50,50));
        jRadioButton2.setBackground(new Color(100,50,50));
        jRadioButton3.setBackground(new Color(100,50,50));
        jRadioButton4.setBackground(new Color(100,50,50));
        setVisible(true);
        group=new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton3);
        group.add(jRadioButton4);
        jRadioButton1.setSelected(true);
        clr=new Color(0,15,85);
        LoadAllStyles();
    }
    
    void LoadAllStyles()
    {
        DefaultListModel<String > model=new DefaultListModel();
        String name,Path;
        Cpath=new javax.swing.JFileChooser().getCurrentDirectory().getAbsolutePath();
        Path =Cpath+"\\DoMyAssignment\\dmas";
        InitPath=Cpath+"\\DoMyAssignment";
        try
        {
            Scanner sc=new Scanner(new File(Path));
            while(sc.hasNext())
            {
                name=sc.next();
                model.addElement(name);
            }
            sc.close();
            jList1.setModel(model);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void addPanels(BufferedImage bi[])
    {
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DoMyAssignment");
        setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel1.setText("Pick Style:-");

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Page Style");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Write");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton3.setText("Select Document File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton1.setForeground(new java.awt.Color(0, 255, 51));
        jRadioButton1.setText("Blue");

        jRadioButton2.setForeground(new java.awt.Color(0, 255, 51));
        jRadioButton2.setText("Black");

        jRadioButton3.setForeground(new java.awt.Color(0, 255, 51));
        jRadioButton3.setText("Red");

        jRadioButton4.setForeground(new java.awt.Color(0, 255, 51));
        jRadioButton4.setText("Green");

        jLabel2.setForeground(new java.awt.Color(0, 255, 51));
        jLabel2.setText("Select Color:-");

        jLabel3.setText("Folder Name For Images:-");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(513, 513, 513)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(135, 135, 135)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRadioButton2)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton4)
                                        .addComponent(jRadioButton3))
                                    .addGap(32, 32, 32))
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addGap(0, 82, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Mypage.cur=Mypage.current;
        Mypage.loadCurrent();
        Mypage.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    void checkColor()
    {
        if(jRadioButton1.isSelected())
        {
            clr=new Color(0,15,85);
        }
        else if(jRadioButton2.isSelected())
        {
            clr=new Color(0,0,0);
        }
        else if(jRadioButton3.isSelected())
        {
            clr=new Color(215,38,38);
        }
        else
        {
            clr=new Color(133,187,101);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jList1.getSelectedIndex()==-1)
        {
            new javax.swing.JOptionPane().showMessageDialog(this,"Select the style First.");
            return ;
        }
        if(jTextField1.getText().equals(""))
        {
            new javax.swing.JOptionPane().showMessageDialog(this,"Enter the Folder Name first.");
            return ;
        }
        File file=new File(InitPath+"\\Outputs\\"+jTextField1.getText());
        if(file.exists())
        {
            jTextField1.setText("Already Taken");
            return ;
        }
        file.mkdir();
        checkColor();
        String style = jList1.getSelectedValue();
        //addPanels();
        wobj=new WriteClass(style,clr,InitPath,InitPath+"\\Outputs\\"+jTextField1.getText(),Mypage.getSelected());
        BufferedImage bi=wobj.writeText(jTextArea1.getText());
        new javax.swing.JOptionPane().showMessageDialog(this,"Your result is stored at Location :- "+InitPath+"\\Outputs\\"+jTextField1.getText());
        /*Image img=bi.getScaledInstance(470,700,Image.SCALE_DEFAULT);
        BufferedImage temp=new BufferedImage(470,700,BufferedImage.TYPE_INT_RGB);
        temp.getGraphics().drawImage(img, 0, 0, null);
        JLabel jl=new JLabel();
        jl.setSize(470,700);
        jl.setLocation(10,10);
        jl.setIcon(new ImageIcon(temp));
        add(jl);
        revalidate();*/
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser=new JFileChooser();
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file=chooser.getSelectedFile();
                Scanner sc=new Scanner(file);
                sc.useDelimiter("\\Z");
                String s=sc.next();
                sc.close();
                jTextArea1.setText(s);
            }
            catch(FileNotFoundException e)
            {
                
            }
        }
        return ;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
