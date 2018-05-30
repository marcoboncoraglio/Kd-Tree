package KdTree;

import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author Marco
 */
public class PrioQ {

    PriorityQueue<Pair<Double, Point>> queue;
    int size;

    public PriorityQueue<Pair<Double, Point>> getQueue() {
        return queue;
    }

    public PrioQ(int size) {
        queue = new PriorityQueue<>(new TupleComparator());

        this.size = size;
    }

    private class TupleComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Pair<Double, Point> p1 = (Pair<Double, Point>) o1;
            Pair<Double, Point> p2 = (Pair<Double, Point>) o2;
            if(p1.getKey() < p2.getKey())
                return 1;
            else return -1;
        }

    }

}
