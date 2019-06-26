package com.four_twoProductions.decoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChooseFile extends JDialog {
    private JLabel myLabel;
    private JFileChooser fileChooser;
    private JButton open;
    private static JTextField textField;
    private JButton finish;
    private static boolean closeMe = false;
    public ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==open) {
                int x = fileChooser.showOpenDialog(null);
                if(x == JFileChooser.APPROVE_OPTION) textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            } else if (e.getSource()==finish) {
                closeMe = true;
                dispose();
            }
        }
    };
    public ChooseFile() {
        myLabel = new JLabel();
        fileChooser = new JFileChooser();
        open = new JButton();
        textField = new JTextField();
        finish = new JButton();

        myLabel.setText("Bitte wählen sie eine Spielfelddatei aus");
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);
        open.setText("Hier auswählen");
        finish.setText("Diese Datei");
        open.addActionListener(listener);
        fileChooser.setCurrentDirectory(new File("com.four_twoProductions.misc"));
        fileChooser.addActionListener(listener);
        finish.addActionListener(listener);



        setLayout(new GridLayout(2,2));
        setSize(500,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("");
        add(myLabel);
        add(open);
        add(textField);
        add(finish);
        setVisible(true);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }
    public static String filePath() throws InterruptedException {
        boolean close = false;
        while(!close) {
            try {
                ChooseFile amk = new ChooseFile();
                while(!closeMe){Thread.sleep(0,1);}
                closeMe = false;
                new Scanner(new File(textField.getText()));
                close = true;
            } catch (FileNotFoundException ex) {}

        }
        return textField.getText();
    }
}
