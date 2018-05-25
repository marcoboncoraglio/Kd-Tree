package Util;

import javafx.util.Pair;

/**
 *
 * @author Marco
 */
public class QuickSelect {
    public static Pair<Integer,Double> quickSelect(double[] array, int pos, int left, int right) {
        if (left == right && left == pos) {
            return new Pair<>(left,array[left]);
        }
        int posRes = partition(array, left, right, pos);
        if (posRes == pos) {
            return new Pair<>(posRes,array[posRes]);
        } else if (posRes < pos) {
            return quickSelect(array, pos, posRes + 1, right);
        } else {
            return quickSelect(array, pos, left, posRes - 1);
        }
    }

    private static int partition(double[] arr, int left, int right, int pos) {
        double pivot = arr[left];
        int position = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] <= pivot) {
                position++;
                swap(arr, i, position);
            }
        }
        swap(arr, left, position);
        return position;
    }

    private static void swap(double[] arr, int first, int second) {
        double temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
