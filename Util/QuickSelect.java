package Util;

/**
 *
 * @author Marco
 */
public class QuickSelect {
    public static double quickSelect(double[] array, int pos, int left, int right) {
        double[] arr = array.clone();
        if (left == right && left == pos) {
            return arr[left];
        }
        int posRes = partition(arr, left, right, pos);
        if (posRes == pos) {
            return arr[posRes];
        } else if (posRes < pos) {
            return quickSelect(arr, pos, posRes + 1, right);
        } else {
            return quickSelect(arr, pos, left, posRes - 1);
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
