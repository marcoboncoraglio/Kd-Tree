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

    double dataset[][];
    Point datapoint;
    Node left, right;
    int keyIndex;
    Pair<Integer, Double> median;

    static int nodeCount = 0;

    public Node(double[][] inputdata) {
        System.out.println(nodeCount++);
        dataset = inputdata;
        if (inputdata.length == 1) {
            datapoint = new Point(dataset[0]);
            System.out.println("End");
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
        System.out.println(datapoint);

        //split list
        List<double[]> l = new ArrayList<>();
        List<double[]> r = new ArrayList<>();
        for (double[] dataset1 : dataset) {
            if(dataset1 == datapoint.getArray())
                continue;
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
}
