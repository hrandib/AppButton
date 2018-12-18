package com.appbutton;

import java.io.FileOutputStream;
import java.io.File;

import javax.swing.*;

public class App {
    private JButton mSendEventButton;
    private JPanel panelMain;
    private static FileOutputStream outPort;

    public App() {
        mSendEventButton.addActionListener(actionEvent -> tx());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if(args.length == 0) {
                final String message = "The first argument should be the path to a serial port";
                JOptionPane.showMessageDialog(null, message);
                System.out.println(message);
                System.exit(3);
            }
            File file = new File(args[0]);
            if(!file.exists()) {
                final String message = "The port is not exist";
                JOptionPane.showMessageDialog(null, message);
                System.out.println(message);
                System.exit(4);
            }
            outPort = new FileOutputStream(file);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(2);
        }
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    outPort.close();
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                System.exit(0);
            }
        });
        frame.setResizable(false);
        frame.setSize(100, 100);
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
