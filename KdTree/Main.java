package KdTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Marco
 */
public class Main {

    private static int[] n = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
    private static int[] k = {5, 10, 15, 20, 25, 30, 35};
    private static int maxValue = 10;

    static double[][] createRandomDataset(int n, int k) {
        Random r = new Random();
        double[][] dataset = new double[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dataset[i][j] = r.nextDouble() * maxValue;
                //System.out.println(dataset[i][j]);
            }
        }

        return dataset;
    }

    public static void main(String[] args) {
        //double[][] dataset = Util.Util.readCSV("C:\\Users\\Marco\\Documents\\NetBeansProjects\\mavenproject1\\Kd-Tree\\src\\main\\java\\Dataset\\Madison.csv", ",");
        try {
            FileWriter writer = new FileWriter("output.csv");

            for (int i = 0; i < n.length; i++) {
                System.out.println(i);
                double[][] dataset = createRandomDataset(n[i], k[0]);
                long startTime = System.currentTimeMillis();
                KdTree.Node root = new Node(dataset);
                long endTime = System.currentTimeMillis();
                writer.append(Integer.toString(n[i]));
                writer.append(',');
                writer.append(Long.toString(endTime - startTime));
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
