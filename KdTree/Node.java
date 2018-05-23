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

    public Node(double[][] inputdata) {
        dataset = inputdata;
        if (inputdata.length == 1) {
            datapoint = new Point(dataset[0]);
        }
        //get highest variance feature index
        double highestVariance = 0;
        int highestVarianceColumnnr = 0;
        for (int i = 0; i < dataset[0].length; i++) {
            double tmpVariance = Util.variance(Util.getColumn(dataset, i));
            if (tmpVariance > highestVariance) {
                highestVariance = tmpVariance;
                highestVarianceColumnnr = i;
            }
        }
        //calculate median
        double median = Util.median(Util.getColumn(dataset, highestVarianceColumnnr));
        
        //split list
        List<double[]> l = new ArrayList<>();
        List<double[]> r = new ArrayList<>();
        for (double[] dataset1 : dataset) {
            if (dataset1[highestVarianceColumnnr] < median) {
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
