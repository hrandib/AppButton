package com.appbutton;

import java.io.FileOutputStream;

import javax.swing.*;

public class App {
    private JButton mSendEventButton;
    private JPanel panelMain;
    private static FileOutputStream outPort;

    public App() {
        mSendEventButton.addActionListener(actionEvent -> {
//            JOptionPane.showMessageDialog(null, "The Message");
            tx();
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            outPort = new FileOutputStream("/home/dmytro/tty_master");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(2);
        }
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void tx()  {
        try {
            outPort.write(42);
            outPort.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
