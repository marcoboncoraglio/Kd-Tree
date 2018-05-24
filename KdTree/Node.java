package KdTree;

import Util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class Node {

    double dataset[][];
    Point datapoint;
    Node left, right;
    int keyIndex;
    double median;

    public Node(double[][] inputdata) {
        dataset = inputdata;
        if (inputdata.length == 1) {
            datapoint = new Point(dataset[0]);
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
        median = Util.median(Util.getColumn(dataset, keyIndex));
        
        //split list
        List<double[]> l = new ArrayList<>();
        List<double[]> r = new ArrayList<>();
        for (double[] dataset1 : dataset) {
            if (dataset1[keyIndex] < median) {
                l.add(dataset1);
            } else {
                r.add(dataset1);
            }
        }
        
        this.datapoint = new Point(l.get(l.size()-1));
        l.remove(l.get(l.size()-1));
        left = new Node((double[][])l.toArray());
        right = new Node((double[][])r.toArray());
    }
}
