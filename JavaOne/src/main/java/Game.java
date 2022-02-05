
public class Game {
    private final String HUMAN_SIGHT = "X";
    private final String AI_SIGHT = "Y";
    private final String EMPTY_SIGHT = " ";
    private int FIELD_SIZE = 3;
    private int NUMBER_OF_CHIPS = 3;

    private int counterStrokes = 0;
    private String[][] fieldPop = renderField(FIELD_SIZE);
    private View view;

    public Game(View view) {
        this.view = view;
    }

    public void setFIELD_SIZE(int FIELD_SIZE) {
        this.FIELD_SIZE = FIELD_SIZE;
        fieldPop = renderField(FIELD_SIZE);
    }

    public void setNUMBER_OF_CHIPS(int NUMBER_OF_CHIPS) {
        this.NUMBER_OF_CHIPS = NUMBER_OF_CHIPS;
    }

    public void startGame(int posX, int posY) {
        if (!isCellValid(fieldPop, posX, posY)) {
            view.generateField(fieldPop, true);
            return;
        }

        if(counterStrokes == FIELD_SIZE * FIELD_SIZE - 1) {
            view.gameOver("DRAW");
            return;
        }

        humanMove(fieldPop, HUMAN_SIGHT, posX, posY);
        if (isWin(fieldPop, HUMAN_SIGHT)) {
            view.gameOver("YOU IS WIN");
            return;
        }

        aIMove(fieldPop, AI_SIGHT);
        if (isWin(fieldPop, AI_SIGHT)) {
            view.gameOver("AI IS WIN");
            return;
        }

        view.generateField(fieldPop, true);

    }

    public String[][] renderField(int sizeField) {
        return fillField(new String[sizeField][sizeField]);
    }

    public String[][] fillField(String[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[j][i] = EMPTY_SIGHT;
            }
        }
        return field;
    }

    public void humanMove(String[][] field, String sign, int posX, int posY) {
        counterStrokes++;
        reRenderField(field, sign, posX, posY);
    }

    public void aIMove(String[][] field, String sign) {
        boolean lock = false;
        int x;
        int y;
        do {
            if (!lock) {
                int[] aiWin = checkAIMove(field, AI_SIGHT);
                if (aiWin.length != 0) {
                    x = aiWin[0];
                    y = aiWin[1];
                    break;
                }
                int[] humanBlock = checkAIMove(field, HUMAN_SIGHT);
                if (humanBlock.length != 0) {
                    x = humanBlock[0];
                    y = humanBlock[1];
                    break;
                }
                lock = true;
            }
            x = (int) (Math.random() * FIELD_SIZE);
            y = (int) (Math.random() * FIELD_SIZE);
        } while (!isCellValid(field, x, y));

        counterStrokes++;
        reRenderField(field, sign, x, y);
    }

    public int[] checkAIMove(String[][] field, String sign) {
        String[][] expField = cloneArray(field);

        for (int i = 0; i < expField.length; i++) {
            for (int j = 0; j < expField.length; j++) {
                if (isCellValid(expField, i, j)) {
                    reRenderField(expField, sign, i, j);
                    if (isWin(expField, sign)) {
                        return new int[]{i, j};
                    }
                    expField = cloneArray(field);
                }
            }
        }
        return new int[]{};
    }

    public void reRenderField(String[][] field, String sign, int x, int y) {
        field[x][y] = sign;
    }

    public boolean isCellValid(String[][] field, int x, int y) {
        if (x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE) {
            return false;
        }
        return field[x][y].equals(EMPTY_SIGHT);
    }

    public String[][] cloneArray(String[][] src) {
        int length = src.length;
        String[][] target = new String[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public boolean isWin(String[][] field, String sign) {
        int winDL = 0;
        int winDR = 0;
        int save = 0;

        // Logic for vertical and horizontal
        for (int vertical = 0; vertical < FIELD_SIZE; vertical++) {
            int winH = 0;
            int winV = 0;

            for (int horizontal = 0; horizontal < FIELD_SIZE; horizontal++) {

                if (field[vertical][horizontal].equals(sign)) {
                    winH++;
                } else {
                    winH = 0;
                }
                if (winH == NUMBER_OF_CHIPS) {
                    return true;
                }

                if (field[horizontal][vertical].equals(sign)) {
                    winV++;
                } else {
                    winV = 0;
                }
                if (winV == NUMBER_OF_CHIPS) {
                    return true;
                }
            }
        }

        // Logic for Diagonally from left to right
        for (int vertical = 0; vertical < FIELD_SIZE; vertical++) {
            for (int horizontal = save; horizontal < FIELD_SIZE; horizontal++) {

                if (field[vertical][horizontal].equals(sign)) {
                    winDL++;
                    if (winDL == NUMBER_OF_CHIPS) {
                        return true;
                    }
                    save = horizontal + 1;
                    break;
                }
                if (winDL > 0) {
                    save -= winDL - 1;
                    vertical -= winDL + 1;
                    winDL = 0;
                    break;
                }
                if (horizontal == FIELD_SIZE - 1) {
                    save = 0;
                }
            }
        }

        save = 0;

        // Logic for Diagonally from right to left
        for (int vertical = 0; vertical < FIELD_SIZE; vertical++) {
            for (int horizontal = save; horizontal < FIELD_SIZE; horizontal++) {

                if (vertical >= 0 && horizontal >= 0 && field[vertical][horizontal].equals(sign)) {
                    winDR++;
                    if (winDR == NUMBER_OF_CHIPS) {
                        return true;
                    }
                    if (horizontal - 1 >= 0) {
                        save = horizontal - 1;
                        break;
                    } else {
                        winDR--;
                    }
                }
                if (winDR > 0) {
                    save += winDR + 1;
                    vertical -= winDR + 1;
                    winDR = 0;
                    break;
                }
                if (horizontal == FIELD_SIZE - 1) {
                    save = 0;
                }
            }
        }

        return false;
    }

}
