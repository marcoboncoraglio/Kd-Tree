package KdTree;

/**
 *
 * @author Marco
 */
public class Main {

    public static void main(String[] args) {
        double[][] dataset = Util.Util.readCSV("C:\\Users\\Marco\\Desktop\\Madison.csv", ",");
        KdTree.Node root = new Node(dataset);
    }
}
