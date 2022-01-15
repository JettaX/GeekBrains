import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Exercise 1");
        System.out.println(Arrays.toString(invertValues(new int[]{1, 0, 1, 1, 1, 0, 0, 0, 1})));
        System.out.println("________________________");

        System.out.println("Exercise 2");
        System.out.println(Arrays.toString(fillArray(new int[100])));
        System.out.println("________________________");

        System.out.println("Exercise 3");
        System.out.println(Arrays.toString(multiplyValueLessThanSix(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1})));
        System.out.println("________________________");

        System.out.println("Exercise 4");
        printDoubleArrays(fillingArrayDiagonally(new int[5][5]));
        System.out.println("________________________");

        System.out.println("Exercise 5");
        System.out.println(Arrays.toString(createArrayAndFill(10, 66)));
        System.out.println("________________________");

        System.out.println("Exercise 6*");
        int[] array = new int[]{1, 2, 6, 2, 10, 20, 500, 50, 37, 59, 92, 567, 24, -1, 50, 971, -24};
        System.out.println("Max value is " + findMaxValue(array));
        System.out.println("Min value is " + findMinValue(array));
        System.out.println("________________________");

        System.out.println("Exercise 7**");
        System.out.println(checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        System.out.println(checkBalance(new int[]{1, 1, 1, 2, 1}));
        System.out.println(checkBalance(new int[]{-2, -1}));
        System.out.println(checkBalance(new int[]{-2, -2}));
        System.out.println("________________________");

        System.out.println("Exercise 8***");
        System.out.println(Arrays.toString(arrayShiftByN(new int[]{1, 2, 3}, 1)));
        System.out.println(Arrays.toString(arrayShiftByN(new int[]{3, 5, 6, 1}, -2)));
        System.out.println("________________________");
    }

    private static int[] invertValues(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 1) {
                values[i] = 0;
            } else {
                values[i] = 1;
            }
        }
        return values;
    }

    private static int[] fillArray(int[] values) {
        for (int i = 1; i <= values.length; i++) {
            values[i - 1] = i;
        }
        return values;
    }

    private static int[] multiplyValueLessThanSix(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] < 6) {
                values[i] *= 2;
            }
        }
        return values;
    }

    private static int[][] fillingArrayDiagonally(int[][] values) {
        for (int vertical = 0; vertical < values.length; vertical++) {

            for (int horizontal = 0; horizontal < values.length; horizontal++) {
                if (vertical == horizontal) {
                    values[vertical][horizontal] = 1;
                    values[vertical][values.length - horizontal - 1] = 1;
                }

            }

        }
        return values;
    }

    private static int[] createArrayAndFill(int length, int initialValue) {
        int[] array = new int[length];
        Arrays.fill(array, initialValue);
        return array;
    }

    private static int findMinValue(int[] array) {
        int minValue = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    private static int findMaxValue(int[] array) {
        int maxValue = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    private static boolean checkBalance(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0 && array[i] == sumValuesInArray(array, i + 1, array.length)) {
                return true;
            } else if (i == array.length - 1 && array[i] == sumValuesInArray(array, 0, i)) {
                return true;
            } else if (sumValuesInArray(array, 0, i + 1) == sumValuesInArray(array, i + 1, array.length)) {
                return true;
            }
        }
        return false;
    }

    private static int sumValuesInArray(int[] array, int from, int to) {
        int sum = 0;
        for (int i = from; i < to; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static int[] arrayShiftByN(int[] array, int n) {
        for (int j = 0; j < (n < 0 ? (-n - n + n) : n); j++) {
            int shift = 0;
            if (n > 0) {
                for (int k = 0; k < array.length; k++) {
                    if (k == array.length - 1) {
                        array[0] = shift;
                    } else if (k == 0) {
                        shift = array[k + 1];
                        array[k + 1] = array[k];
                    } else {
                        int x = array[k + 1];
                        array[k + 1] = shift;
                        shift = x;
                    }
                }
            } else {
                for (int k = array.length - 1; k >= 0; k--) {
                    if (k == 0) {
                        array[array.length - 1] = shift;
                    } else if (k == array.length - 1) {
                        int x = array[k - 1];
                        array[k - 1] = array[k];
                        shift = x;
                    } else {
                        int x = array[k - 1];
                        array[k - 1] = shift;
                        shift = x;
                    }
                }
            }
        }
        return array;
    }

    public static void printDoubleArrays(int[][] mass) {
        for (int[] ints : mass) {
            for (int j = 0; j < mass[0].length; j++) {
                System.out.print(" " + ints[j] + " ");
            }
            System.out.println();
        }
    }

}
