package gui;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ConvertScreen {

    private static final int WINDOW_WIDTH ;
    private static final int WINDOW_HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width;
        WINDOW_HEIGHT = screenSize.height;
    }

    public ConvertScreen() {

        // Window
        JFrame convertFrame = new JFrame();
        convertFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        convertFrame.setTitle("CONVERT");
        convertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convertFrame.setResizable(true);
        convertFrame.setLocationRelativeTo(null);
        convertFrame.setLayout(null);
        convertFrame.getContentPane().setBackground(Color.WHITE);

        // Layout manager window
        convertFrame.setLayout(new BorderLayout());

        // Panels for the convertFrame
        TopPanel topPanel = new TopPanel(convertFrame);
        CentralPanel centralPanel = new CentralPanel();
        BottomPanel bottomPanel = new BottomPanel(centralPanel,convertFrame);

        // Adding the created panels to the window
        convertFrame.add(topPanel,BorderLayout.NORTH);
        convertFrame.add(centralPanel,BorderLayout.CENTER);
        convertFrame.add(bottomPanel,BorderLayout.SOUTH);

        convertFrame.setVisible(true);
    }
}

