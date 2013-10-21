import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int N;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
    }

    // construct an empty deque
    public RandomizedQueue() {
        first = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // insert the item at front
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> node = new Node<Item>();
        node.item = item;
        Node<Item> temp = first;
        first = node;
        first.next = temp;
        N++;

    }

    // delete and return a random item
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int randomI = StdRandom.uniform(0, N);
        N--;
        Node<Item> ctr = first;
        if (N == 0 || (randomI == 0)) {
            Node<Item> temp = first;
            first = first.next;
            return temp.item;
        }
        // if(randomI == 0) {
        // Node<Item> temp = first;
        // first = first.next;
        // return temp.item;
        // }
        for (int i = 0; i < randomI - 1; i++) {
            ctr = ctr.next;
        }
        Node<Item> temp = ctr.next;
        ctr.next = ctr.next.next;
        return temp.item;
    }

    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int randomI = StdRandom.uniform(N);
        Node<Item> ctr = first;
        for (int i = 0; i < randomI; i++) {
            ctr = ctr.next;
        }
        return ctr.item;
    }

    // return an iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private boolean[] visited = new boolean[N];

            @Override
            public boolean hasNext() {
                if (N == 0) {
                    return false;
                }
                int total = 0;
                for (int ctr = 0; ctr < N; ctr++) {
                    if (visited[ctr]) {
                        total++;
                    }
                }
                if (total > N - 1) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Item next() {
                if (N == 0) {
                    throw new NoSuchElementException();
                }
                int total = 0;
                for (int ctr = 0; ctr < N; ctr++) {
                    if (visited[ctr]) {
                        total++;
                    }
                }
                if (total > N - 1) {
                    throw new NoSuchElementException();
                }
                int randomI = StdRandom.uniform(N);
                while (visited[randomI]) {
                    randomI = StdRandom.uniform(N);
                }
                visited[randomI] = true;
                Node<Item> ctr = first;
                for (int i = 0; i < randomI; i++) {
                    ctr = ctr.next;
                }
                return ctr.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "remove not implemented");
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("i1");
        rq.enqueue("r2");
        rq.dequeue();
//        rq.enqueue("r3");
//        rq.enqueue("r8");
//        rq.enqueue("r4");
//        rq.enqueue("r9");
        rq.dequeue();
        //rq.enqueue("r11");
        rq.enqueue("r11");
        System.out.println("empty " + rq.isEmpty());
        System.out.println("sz "+rq.size());
        Iterator<String> itr = rq.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("dequeued " + rq.dequeue());
        rq.enqueue("r12");
        System.out.println("sample " + rq.sample());

//         itr.next();
        Iterator<String> itr1 = rq.iterator();
        while (itr1.hasNext()) {
            System.out.println(itr1.next());
        }
    }

}
