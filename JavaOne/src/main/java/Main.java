import java.util.Scanner;

public class Main {
    static final int MAX_SIZE_FIELD = 5;
    static final int MIN_SIZE_FIELD = 3;
    static final int MAX_NUMBER_CHIPS = 5;
    static final int MIN_NUMBER_CHIPS = 3;
    static final String SIGN_X = "X";
    static final String SIGN_Y = "Y";
    static int FIELD_SIZE;
    static int NUMBER_OF_CHIPS;
    static int counterStrokes = 0;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        startSettings(scanner);
        String[][] field = renderField(FIELD_SIZE);
        printField(field);

        while (true) {
            humanMove(field, scanner, SIGN_X);
            if (isWin(field, SIGN_X)) {
                System.out.println("Human is win");
                startGame();
            }
            if (counterStrokes == FIELD_SIZE * 3) {
                System.out.println("DRAW");
                startGame();
            }
            aIMove(field, SIGN_Y);
            if (isWin(field, SIGN_Y)) {
                System.out.println("AI is win");
                startGame();
            }
        }
    }

    public static void startSettings(Scanner scanner) {
        do {
            System.out.println("Enter field size from 3 to 5");
            FIELD_SIZE = Integer.parseInt(String.valueOf(scanner.next()));
        } while (!(FIELD_SIZE <= 5 && FIELD_SIZE >= 3));

        do {
            System.out.println("Enter number of chips from 3 to 5");
            NUMBER_OF_CHIPS = Integer.parseInt(String.valueOf(scanner.next()));
        } while (!(NUMBER_OF_CHIPS <= FIELD_SIZE && NUMBER_OF_CHIPS <= MAX_NUMBER_CHIPS && NUMBER_OF_CHIPS >= MIN_NUMBER_CHIPS));
    }

    public static String[][] renderField(int sizeField) {
        if (sizeField > MAX_SIZE_FIELD) {
            sizeField = MAX_SIZE_FIELD;
        } else if (sizeField < MIN_SIZE_FIELD) {
            sizeField = MIN_SIZE_FIELD;
        }
        return fillField(new String[sizeField][sizeField]);
    }

    public static String[][] fillField(String[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[j][i] = "0";
            }
        }
        return field;
    }

    public static void humanMove(String[][] field, Scanner scanner, String sign) {
        String next;
        int[] movements;

        do {
            next = scanner.nextLine();
            movements = parser(next);
        } while (!isCellValid(field, movements[0], movements[1]));

        counterStrokes++;
        reRenderField(field, sign, movements[0], movements[1]);
        printField(field);
    }

    public static void aIMove(String[][] field, String sign) {
        boolean lock = false;
        int x;
        int y;
        do {
            if (!lock) {
                int[] aiWin = checkAIMove(field, SIGN_Y);
                if (aiWin.length != 0) {
                    x = aiWin[0];
                    y = aiWin[1];
                    break;
                }
                int[] humanBlock = checkAIMove(field, SIGN_X);
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
        printField(field);
    }

    public static int[] checkAIMove(String[][] field, String sign) {
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

    public static void reRenderField(String[][] field, String sign, int x, int y) {
        field[x][y] = sign;
    }

    public static boolean isCellValid(String[][] field, int x, int y) {
        if (x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE) {
            return false;
        }
        return field[x][y].equals("0");
    }

    public static String[][] cloneArray(String[][] src) {
        int length = src.length;
        String[][] target = new String[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static int[] parser(String string) {
        int oneValue;
        int twoValue;
        try {
            string = string.replaceAll("\\s+", "");
            oneValue = Integer.parseInt(String.valueOf(string.charAt(0))) - 1;
            twoValue = Integer.parseInt(String.valueOf(string.charAt(1))) - 1;
        } catch (Exception e) {
            return new int[]{MAX_SIZE_FIELD, MAX_SIZE_FIELD};
        }
        return new int[]{oneValue, twoValue};
    }

    public static boolean isWin(String[][] field, String sign) {
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

    public static void printField(String[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (i == 0) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    System.out.print("    " + (j + 1));
                }
                System.out.println();
                System.out.println();
            }
            for (int j = 0; j < field[0].length; j++) {
                System.out.print((j == 0 ? (i + 1) : "") + "   " + field[i][j] + " ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
