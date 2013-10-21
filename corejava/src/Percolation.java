/**
 * 
 * @author susmit ushukla
 * @login shukla.susmit@gmail.com
 * @Date 10/06/2013 Implements monte carlo simulation of Percolation problem
 * 
 */
public class Percolation {

    /*
     * N x N array of Sites
     */
    private boolean[][] grid;

    /*
     * Weighted quick union data structure
     */
    private WeightedQuickUnionUF quickUnion;

    /*
     * lattice dimension
     */
    private int N;
    
    private boolean isVirtualBottomOpen = false;

    /**
     * Initialize the array of Sites and add 2 virtual sites on top and bottom
     * 
     * @param N
     */
    public Percolation(int N) {
        this.N = N;
        grid = new boolean[N][N];
        quickUnion = new WeightedQuickUnionUF(N * N + 2);
        // 2 virtual sites on top and bottom
        for (int ctr = 0; ctr < N; ctr++) {
            quickUnion.union(ctr, N * N);
            quickUnion.union((N * N - N) + ctr, N * N + 1);
        }
    }

    /**
     * Open a site at i,j index. Indices start at 1,1
     * 
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        validate(i, j);
        int zi = i - 1;
        int zj = j - 1;
        grid[zi][zj] = true;
        int up = Math.max(0, zi - 1);
        int right = Math.min(N - 1, zj + 1);
        int down = Math.min(N - 1, zi + 1);
        int left = Math.max(0, zj - 1);
        if (up != zi && grid[up][zj]) {
            quickUnion.union(N * up + zj, N * zi + zj);
        }
        if (right != zj && grid[zi][right]) {
            quickUnion.union(N * zi + right, N * zi + zj);
        }
        if (down != zi && grid[down][zj]) {
            quickUnion.union(N * down + zj, N * zi + zj);
        }
        if (left != zj && grid[zi][left]) {
            quickUnion.union(N * zi + left, N * zi + zj);
        }
        for(int aa=0; aa < N; aa++) {
            if(N > 1) {
                if(!grid[N-1][aa]) {
                    isVirtualBottomOpen = false;
                    break;
                } else {
                    isVirtualBottomOpen = true;
                }
            }
        }
    }

    /**
     * determine if a site at index i,j is open
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        validate(i,j);
        return grid[i - 1][j - 1];
    }

    /**
     * determine if a site at index i,j is open. i.e.connected to top
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
        validate(i,j);
        int ti = i - 1;
        int tj = j - 1;
        if(!percolates()) {
            return grid[ti][tj]
                    && quickUnion.connected((N * ti) + tj, N * N);
        } else {
            //if(quickUnion.connected((N * ti) + tj, N * N + 1)) {
                return grid[ti][tj]
                                && quickUnion.connected((N * ti) + tj, N * N) && isVirtualBottomOpen;            
            //}
        }
    }

    /**
     * return true if system percolates
     * 
     * @return
     */
    public boolean percolates() {
        if (N == 1) {
            return grid[0][0];
        }
        return quickUnion.connected(N * N + 1, N * N);
    }
    
    private boolean validate(int i, int j) throws IndexOutOfBoundsException{
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        /*for (int i = 1; i < 5; i++) {
            int randomI = StdRandom.uniform(1, 4);
            int randomJ = StdRandom.uniform(1, 4);
            percolation.open(randomI, randomJ);
        }*/
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,3);
        //percolation.open(3,1);
        percolation.open(2,1);
        percolation.open(1,1);
        //percolation.open(3,2);
       System.out.println(percolation);
        System.out.println(percolation.percolates());
        System.out.println(percolation.isFull(2, 1));
    }

}
