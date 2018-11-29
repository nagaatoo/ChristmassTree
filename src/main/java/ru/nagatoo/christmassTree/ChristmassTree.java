package ru.nagatoo.christmassTree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.*;

public class ChristmassTree {

    private boolean alwaysOnTop = false;

    private ImagePanel panel;
    private MetaDataClass metaDataClass = null;
    private JFrame frame = new JFrame();

    public ChristmassTree() {
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            metaDataClass = (MetaDataClass) oin.readObject();
            paintTree();
        } catch (Exception ioe) {
            metaDataClass = new MetaDataClass();
            makeNewTree();
        }
    }

    private void addListeners() {
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    if(event.getButton() == 3) {
                        saveLocation();
                        try {
                            FileOutputStream fos = new FileOutputStream("temp.out");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(metaDataClass);
                            oos.flush();
                            oos.close();
                        } catch (IOException ioe) {
                            JOptionPane.showMessageDialog(null, "Файл не найден", "Ошибка", JOptionPane
                                .ERROR_MESSAGE);
                        }
                        System.exit(0);
                    }
                    makeNewTree();
                }
                if (event.getButton() == 2) {
                    alwaysOnTop = !alwaysOnTop;
                    frame.setAlwaysOnTop(alwaysOnTop);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                metaDataClass.mouseX = e.getX();
                metaDataClass.mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - metaDataClass.mouseX;
                int newY = e.getYOnScreen() - metaDataClass.mouseY;
                frame.setLocation(newX, newY);
            }
        });
    }

    private void paintTree() {
        frame.dispose();
        frame = new JFrame();
        addListeners();
        String filePath = metaDataClass.file.toString();
        if (!new File(filePath).exists()) {
            makeNewTree();
            return;
        }
        panel = new ImagePanel(filePath);
        frame.getContentPane().add(panel);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));
        frame.pack();
        frame.setLocationRelativeTo(null);
        if(metaDataClass.firstRun) {
            metaDataClass.firstRun = false;
        } else {
            frame.setLocation(metaDataClass.windowX, metaDataClass.windowY);
        }
        frame.setVisible(true);
    }

    private void makeNewTree() {
        saveLocation();
        JFileChooser fileopen = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Только гифки, только хардкор", "gif", "gif");
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileopen.setCurrentDirectory(workingDirectory);
        fileopen.setFileFilter(filter);
        int ret = fileopen.showDialog(null, "Открыть файл");

        if (ret == JFileChooser.APPROVE_OPTION) {
            metaDataClass.file = fileopen.getSelectedFile();
            paintTree();
        }
    }

    private void saveLocation() {
        metaDataClass.windowX = frame.getX();
        metaDataClass.windowY = frame.getY();
    }

    public static void main(String [] p) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ChristmassTree();
        });
    }
}
