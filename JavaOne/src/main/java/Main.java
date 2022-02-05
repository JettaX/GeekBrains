import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            var frame = new View();
            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screeWidth = screenSize.width;
            int screeHeight = screenSize.height;
            int size = (screeHeight / 2) - (frame.getHeight() / 2);
            frame.setPreferredSize(new Dimension(size, size));
            frame.pack();
            frame.setLocation((screeWidth / 2) - (frame.getWidth() / 2), (screeHeight / 2) - (frame.getHeight() / 2));
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
