package Util;

import KdTree.Point;
import KdTree.PrioQ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

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
        double[] column = new double[array.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    public static Pair<Integer, Double> median(double[] arr) {
        return QuickSelect.quickSelectPair(arr, arr.length / 2, 0, arr.length - 1);
    }

    public static double highest(double[] arr) {
        return QuickSelect.quickSelect(arr, arr.length - 1, 0, arr.length - 1);
    }

    public static double lowest(double[] arr) {
        return QuickSelect.quickSelect(arr, 0, 0, arr.length - 1);
    }

    public static double[][] readCSV(String path, String separator) {
        List<double[]> dataset = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = null;

            while ((line = br.readLine()) != null) {
                dataset.add(Arrays.asList(line.split(separator)).stream().mapToDouble(Double::parseDouble).toArray());
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        double[][] matrix = new double[dataset.size()][];
        matrix = dataset.toArray(matrix);
        return matrix;
    }

    private boolean boundsOverlapBall(Point query, PrioQ queue, int k) {

        return true;
    }
}
