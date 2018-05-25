package KdTree;

/**
 *
 * @author Marco
 */
public class Main {

    public static void main(String[] args) {
        double[][] dataset = Util.Util.readCSV("C:\\Users\\Marco\\Documents\\NetBeansProjects\\mavenproject1\\Kd-Tree\\src\\main\\java\\Dataset\\Madison.csv", ",");
        KdTree.Node root = new Node(dataset);
    }
}
