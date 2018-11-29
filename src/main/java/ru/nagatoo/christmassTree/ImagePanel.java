package ru.nagatoo.christmassTree;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    public ImagePanel(String patch) {
        this.setOpaque(false);
        makeTree(patch);
    }

    private void makeTree(String patch) {
        JLabel imageLabel = new JLabel();
        try {
            this.setLayout(new BorderLayout());
            this.setSize(new Dimension(400, 300));

            ImageIcon icon = null;
            try {
                icon = new ImageIcon(patch);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Елка (или иная клевая штука) не найдена", "Елка не "
                    + "найдена", JOptionPane.ERROR_MESSAGE);
            }

            imageLabel.setIcon(icon);
            this.revalidate();
            this.repaint();
            this.add(imageLabel, java.awt.BorderLayout.CENTER);
            this.setSize(new Dimension(icon.getIconWidth() + 50, icon.getIconHeight() + 50));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
