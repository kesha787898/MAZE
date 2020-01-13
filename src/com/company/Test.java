package com.company;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class Test {
    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon();
        panel.add(label);
        JTextField text=new JTextField(10);


        JButton buttontest = new JButton("test");
        buttontest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process p=Runtime.getRuntime().exec("python C:\\Users\\alex\\IdeaProjects\\Generate_Maze\\script.py");
                    while(p.isAlive()){}
                    System.out.println(p.exitValue());



                    TimeUnit.SECONDS.sleep(0);
                    label.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\alex\\IdeaProjects\\Generate_Maze\\ans.jpg"))));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "warnning", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

panel.add(buttontest);
        JButton button = new JButton("generate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


try {
                Graph G=Graph.Empty(Integer.parseInt(text.getText()),false);
                G.alg();
                BufferedImage i =G.firstdraw(false,false);
                label.setIcon(new ImageIcon(i));
                File outputfile = new File("image.bmp");
                try {
                    ImageIO.write(i, "bmp", outputfile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "warnning", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

            }
        }});
        panel.add(button);
        panel.add(text);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(icon.getIconWidth()+100, icon.getIconWidth()+100));
        frame.setSize(1600,1000);
        frame.setVisible(true);
    }
    public static void draw() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}

