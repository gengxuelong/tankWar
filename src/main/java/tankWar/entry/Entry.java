package tankWar.entry;

import tankWar.imageFactory.MyFactory;
import utils.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author gengxuelong
 */
public class Entry extends JFrame {
    public  Entry(){
        /*
        frame
         */
        this.setSize(MyFactory.size.x, MyFactory.size.y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        /*
        contentPane
         */
        BackgroundPanel contentPane = new BackgroundPanel();
        this.setContentPane(contentPane);
        contentPane.setImage(MyFactory.beijing_entry);
        /*
        button
         */
        JButton button = new JButton("开始游戏");
        contentPane.add(button);
        button.setBackground(Color.gray);
        button.setBounds(900,300,280,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuanQia();
            }
        });

        JButton button1 = new JButton("退出");
        contentPane.add(button1);
        button1.setBackground(Color.gray);
        button1.setBounds(900,450,280,100);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        new Entry();
    }

}
