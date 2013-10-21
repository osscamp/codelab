import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
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

    // insert the item at the front
    public void addFirst(Item item) {
        if(item == null) {
            throw new NullPointerException();
        }
        Node<Item> node = new Node<Item>();
        node.item = item;
        Node<Item> temp = first;
        first = node;
        first.next = temp;
        if(temp != null) {
            temp.prev = first;
        } else {
            last = first;
        }
        N++;

    }

    // insert the item at the end
    public void addLast(Item item) {
        if(item == null) {
            throw new NullPointerException();
        }
        Node<Item> node = new Node<Item>();
        node.item = item;
        Node<Item> temp = last;
        last = node;
        if(temp != null) {
            temp.next = last;
        } else {
            first = last;
        }
        last.prev = temp;
        N++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if(N == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> temp = first;
        first = first.next;
        if(first != null) {
            first.prev = null;
        }
        N--;
        if(N <= 1) {
            last = first;
        }
        //first.prev = null;
        return temp.item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if(N == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> temp = last;
        last = last.prev;
        if(last != null) {
            last.next = null;
        }
        N--;
        if(N <= 1) {
            first = last;
        }
        //last.next = null;
        return temp.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if(current == null) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {         
                throw new UnsupportedOperationException("remove not implemented");
            }
        };
    }
    
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        //dq.addLast("s1");
        //dq.addLast("s2");
        //dq.addLast("s3");
        //dq.addLast(null);
        dq.addFirst("s3");
        /*dq.addFirst("s2");
        dq.addLast("s4");*/
        //dq.removeLast();
        System.out.println(dq.removeLast());
        //System.out.println(dq.removeFirst());
        //dq.addFirst("s2");
        //dq.addLast("s2");
        //dq.removeFirst();
       System.out.println("sz="+dq.size());
       System.out.println(dq.isEmpty());
       Iterator<String> itr = dq.iterator();
       while(itr.hasNext()) {
           System.out.println(itr.next());
       }
       //itr.next();
       /*Iterator<String> itr1 = dq.iterator();
       while(itr1.hasNext()) {
           System.out.println(itr1.next());
       }*/
    }

}
