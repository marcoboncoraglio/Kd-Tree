package KdTree;

/**
 *
 * @author Marco
 */
public class Point {
    public double[] features;

    public Point(int dimensionality) {
        features = new double[dimensionality];
    }
    
    public Point(double[] arr){
        features = arr;
    }
}
