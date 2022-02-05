import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldButtonsListener implements ActionListener {
    private int posX;
    private int posY;
    private Game game;

    public FieldButtonsListener(int posX, int posY, Game game) {
        this.posX = posX;
        this.posY = posY;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.startGame(posX, posY);
    }

}