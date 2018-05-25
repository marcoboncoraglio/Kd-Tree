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

    public Point(double[] arr) {
        features = arr;
    }

    @Override
    public String toString() {
        String out = new String();
        for (int i = 0; i < features.length; i++) {
            out += features[i];
            out += " ";
        }
        out += "\n";
        return out;
    }
    
    public double[] getArray(){
        return features;
    }
}
