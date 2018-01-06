import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel  {
    // TODO Проверку на gif +
    // запуск по умолчанию
    // замена деревьев на лету +



    public ImagePanel(String patch) {

        this.setOpaque(false);
        makeTree(patch);
    }

    private void makeTree(String patch) {
        JLabel imageLabel = new JLabel();
        try {
            this.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));

            ImageIcon ii = null;
            try {
                  ii  = new ImageIcon(patch);
                } catch (NullPointerException e) {
                }

                imageLabel.setIcon(ii);
            this.revalidate();
            this.repaint();
                this.add(imageLabel, java.awt.BorderLayout.CENTER);
                this.setSize(new Dimension(ii.getIconWidth() + 50, ii.getIconHeight() + 50));


            } catch(Exception exception){
                exception.printStackTrace();
            }
        }

}