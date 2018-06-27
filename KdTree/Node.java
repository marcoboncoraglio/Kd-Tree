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
    private static final int bucketSize = 8;

    private double dataset[][];
    private List<Point> bucket;
    private Node left, right;
    private int keyIndex;
    private Pair<Integer, Double> median;

    static int nodeCount = 0;

    public Node(double[][] inputdata) {
        //System.out.println(nodeCount++);
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

        double[] column = Util.getColumn(dataset, keyIndex);
        median = Util.median(column);
        datapoint = new Point(inputdata[median.getKey()]);
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
    
    private void nearestNeighborSearch(Point query, PrioQ q, double[] lowBound, double[] highBound, int k){
        if(left == null && right == null){
            for(Point p: bucket){
                double distance = Util.euclidianDistance(p, query);
                if(q.queue.offer(new Pair<>(distance, p))){
                    if(ballWithinBounds(query, q.queue.poll().getKey(), lowBound, highBound))
                        return;
                }
            }
        }
        if(query.features[keyIndex] <= median.getValue()){
            
        }
    }

    private boolean ballWithinBounds(Point query, double kClosestDistance, double[] lowBound, double[] highBound) {
        for (int i = 0; i < query.features.length; i++) {
            if (Math.abs(query.features[i] - lowBound[i]) <= kClosestDistance) {
                return false;
            }
            if (Math.abs(query.features[i] - highBound[i]) <= kClosestDistance) {
                return false;
            }
        }
        return true;
    }

    private boolean boundsOverlapBall(Point query, double kClosestDistance, double[] lowBound, double[] highBound) {
        double sum = 0;
        for (int i = 0; i < query.features.length; i++) {
            if (query.features[i] < lowBound[i]) {
                sum += Math.abs(query.features[i] - lowBound[i]);
                if (sum > kClosestDistance) {
                    return true;
                }
            } else if (query.features[i] > highBound[i]) {
                sum += Math.abs(query.features[i] - highBound[i]);
                if (sum > kClosestDistance) {
                    return true;
                }
            }
        }
        return false;
    }
}
