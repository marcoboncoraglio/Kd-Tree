package KdTree;

import Util.Util;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Marco
 */
public class Node {

    public Point datapoint;

    public static final double lowerBounduary = 0.5;
    public static final double upperBoundary = 1.5;
    private static final int bucketSize = 8;

    private double dataset[][];
    private List<Point> bucket;
    private Node left, right;
    private int keyIndex;
    private Pair<Integer, Double> median;

    static int nodeCount = 0;

    public Node(double[][] inputdata) {
        System.out.println(nodeCount++);
        dataset = inputdata;
        if (inputdata.length == bucketSize) {
            datapoint = new Point(dataset[0]);
            bucket = new ArrayList();
            for (int i = 1; i < dataset.length; i++) {
                bucket.add(new Point(dataset[i]));
            }
        }
        //get highest variance feature index
        double highestVariance = 0;
        for (int i = 0; i < dataset[0].length; i++) {
            double tmpVariance = Util.variance(Util.getColumn(dataset, i));
            if (tmpVariance > highestVariance) {
                highestVariance = tmpVariance;
                keyIndex = i;
            }
        }
        //calculate median
        System.out.println("Highest Variance index: " + keyIndex);
        median = Util.median(Util.getColumn(dataset, keyIndex));
        datapoint = new Point(inputdata[median.getKey()]);
        System.out.println("Median: " + median.getValue());
        //split list
        List<double[]> l = new ArrayList<>();
        List<double[]> r = new ArrayList<>();
        for (double[] dataset1 : dataset) {
            if (dataset1 == datapoint.getArray()) {
                continue;
            }
            if (dataset1[keyIndex] < median.getValue()) {
                l.add(dataset1);
            } else {
                r.add(dataset1);
            }
        }

        System.out.println("Left list size: " + l.size());
        System.out.println("Right list size: " + r.size());

        if (!l.isEmpty()) {
            double[][] matrix = new double[l.size()][];
            matrix = l.toArray(matrix);
            left = new Node(matrix);
        }

        if (!r.isEmpty()) {
            double[][] matrix = new double[r.size()][];
            matrix = r.toArray(matrix);
            right = new Node(matrix);
        }
    }
    
    private boolean ballWithinBounds(Point query, PrioQ queue, double lowerBound, double upperBound, int k) {
        for (int i = 0; i < k; ++i) {
            queue.queue.poll();
        }
        Pair<Double, Point> currentKNN = queue.queue.poll();
        double dist1 = Util.euclidianDistance(query, datapoint);
        double dist2 = Util.euclidianDistance(query, currentKNN.getValue());

        return dist1 < dist2;
    }
    
    private boolean boundsOverlapBall(Point query, PrioQ queue, double lowerBound, double upperBound, int k){
        return true;
    }
}
