package Util;

import KdTree.Point;

/**
 *
 * @author Marco
 */
public class Util {

    public static double euclidianDistance(Point x, Point y) {
        double distance = 0;
        for (int i = 0; i < x.features.length; i++) {
            distance += Math.abs(x.features[i] - y.features[i]);
        }
        return Math.sqrt(distance);
    }

    public static double mean(double[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }

    public static double variance(double[] arr) {
        double mean = mean(arr);
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Math.pow(arr[i] - mean, 2);
        }
        return sum / (arr.length - 1);
    }

    public static double[] getColumn(double[][] array, int index) {
        double[] column = new double[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    public static double median(double[] arr) {
        return QuickSelect.quickSelect(arr, arr.length / 2, 0, arr.length - 1);
    }
}
