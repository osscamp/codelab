import java.util.Iterator;


public class Subset {
    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        assert(k >= 0);
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String[] input = StdIn.readLine().split("\\s+");
        for (String ip : input) {
            rq.enqueue(ip);            
        }
        Iterator<String> itr = rq.iterator();
        int ctr = 0;
        while (itr.hasNext() && ctr < k) {
            System.out.println(itr.next());
            ctr++;
        }
    }

}
