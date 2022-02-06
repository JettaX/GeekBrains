import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
    private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    private Font fontForLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    private Font fontFieldButton = new Font(Font.SANS_SERIF, Font.BOLD, 70);

    private Color backgroundColor = new Color(0, 64, 104);
    private Color buttonColor = new Color(189, 35, 28);
    private Color comboBoxColor = new Color(189, 35, 28);
    private Color fontColor = Color.WHITE;
    private Color fontColorFieldButtno = new Color(0, 64, 104);

    private int FIELD_SIZE = 3;
    private int NUMBER_OF_CHIPS = 3;

    private JPanel panelStart;
    private JPanel panelForComboBox;
    private JPanel panelForGameField;
    private Game game;

    public View() {
        initView();
    }

    private void initView() {
        game = new Game(this);

        UIManager.put("Button.font", font);

        var layout = new GridLayout(5, 1, 50, 20);
        panelForComboBox = new JPanel(layout);
        panelForComboBox.setBackground(backgroundColor);

        var labelField = new JLabel("Select the size of the field");
        labelField.setForeground(fontColor);
        labelField.setFont(fontForLabel);
        var comboField = new JComboBox<Integer>();
        comboField.setFont(fontForLabel);
        comboField.setBackground(comboBoxColor);

        comboField.addItem(3);
        comboField.addItem(4);
        comboField.addItem(5);
        comboField.addActionListener(e -> {
            game.setFIELD_SIZE(comboField.getItemAt(comboField.getSelectedIndex()));
            FIELD_SIZE = comboField.getItemAt(comboField.getSelectedIndex());
        });

        var labelChips = new JLabel("Choose the number of chips");
        labelChips.setForeground(fontColor);
        labelChips.setFont(fontForLabel);
        var comboChips = new JComboBox<Integer>();
        comboChips.setFont(fontForLabel);
        comboChips.setBackground(comboBoxColor);

        comboChips.addItem(3);
        comboChips.addItem(4);
        comboChips.addItem(5);
        comboChips.addActionListener(e -> {
            NUMBER_OF_CHIPS = comboChips.getItemAt(comboChips.getSelectedIndex());
        });

        panelStart = new JPanel(new GridLayout(2, 1));
        var newGameButton = new JButton("New Game");
        newGameButton.setBackground(buttonColor);
        newGameButton.addActionListener(e -> {
            if (NUMBER_OF_CHIPS > FIELD_SIZE) {
                NUMBER_OF_CHIPS = FIELD_SIZE;
            }
            game.setNUMBER_OF_CHIPS(NUMBER_OF_CHIPS);
            panelStart.setVisible(false);
            generateField(new String[FIELD_SIZE][FIELD_SIZE], false);
        });

        panelForComboBox.add(labelField);
        panelForComboBox.add(comboField);
        panelForComboBox.add(labelChips);
        panelForComboBox.add(comboChips);
        panelStart.add(panelForComboBox);
        panelStart.add(newGameButton);
        add(panelStart);
    }

    public void generateField(String[][] field, boolean isRemove) {
        if (isRemove) {
            remove(panelForGameField);
        }

        panelForGameField = new JPanel(new GridLayout(FIELD_SIZE, FIELD_SIZE, 20, 20));
        panelForGameField.setBackground(backgroundColor);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                var jButton = new JButton(field[i][j]);
                jButton.addActionListener(new FieldButtonsListener(i, j, game));
                jButton.setBackground(buttonColor);
                jButton.setPreferredSize(new Dimension(100, 100));
                jButton.setFont(fontFieldButton);
                jButton.setForeground(fontColorFieldButtno);
                panelForGameField.add(jButton);
            }
        }
        add(panelForGameField);
        pack();
    }

    public void gameOver(String winner) {
        remove(panelForGameField);

        var panelLabel = new JPanel(new GridLayout(2, 1));
        panelLabel.setBackground(backgroundColor);

        var label = new JLabel(winner, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 100));
        label.setFont(font);
        label.setForeground(fontColor);

        var newGameButton = new JButton("New Game");
        newGameButton.setBackground(buttonColor);
        newGameButton.addActionListener(e -> {
            remove(panelLabel);
            remove(panelForComboBox);
            remove(panelForGameField);
            remove(panelStart);
            FIELD_SIZE = 3;
            NUMBER_OF_CHIPS = 3;
            initView();
            pack();
        });

        panelLabel.add(label);
        panelLabel.add(newGameButton);
        add(panelLabel);
        pack();
    }
}