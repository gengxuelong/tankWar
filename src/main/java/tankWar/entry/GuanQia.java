package tankWar.entry;


import tankWar.imageFactory.MyFactory;
import tankWar.page.*;
import utils.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author gengxuelong
 */
public class GuanQia extends JFrame{

    public  GuanQia(){
        /*
        frame
         */
        this.setSize(1200,660);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        /*
        contentPane
         */
        BackgroundPanel contentPane = new BackgroundPanel();
        this.setContentPane(contentPane);
        contentPane.setImage(MyFactory.beijing_guanQia);
        /*
        button
         */
        JButton button = new JButton("关卡1");
        contentPane.add(button);
        button.setBackground(Color.orange);
        button.setBounds(100,100,200,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame3();
            }
        });

        JButton button1 = new JButton("关卡4");
        contentPane.add(button1);
        button1.setBackground(Color.orange);
        button1.setBounds(100,350,200,100);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame1();
            }
        });

        JButton button2 = new JButton("关卡2");
        contentPane.add(button2);
        button2.setBackground(Color.orange);
        button2.setBounds(500,100,200,100);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame2();
            }
        });
        JButton button3 = new JButton("关卡5");
        contentPane.add(button3);
        button3.setBackground(Color.orange);
        button3.setBounds(500,350,200,100);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame4();
            }
        });
        JButton button4 = new JButton("关卡3");
        contentPane.add(button4);
        button4.setBackground(Color.orange);
        button4.setBounds(900,100,200,100);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GameFrame0();
            }
        });
      
    }
}
