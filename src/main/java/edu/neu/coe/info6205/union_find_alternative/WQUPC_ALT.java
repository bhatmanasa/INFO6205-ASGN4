package edu.neu.coe.info6205.union_find_alternative;

import java.util.ArrayList;
import java.util.List;

//Created by Manasa Bhat 01-March-2021
public class WQUPC_ALT {
/**
 * Weighted Quick Union by Depth with intermediate Path Compression
 */

    private final int[] parent;   // parent[i] = parent of i
    private final int[] depth; //depth[i] = number of links on the path from its node to root.
    private final int[] height;
    private int count;  // number of components
    private boolean pathCompression;
    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WQUPC_ALT(int n,boolean pathCompression) {
        count = n;
        parent = new int[n];
        depth = new int[n];
        height = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
            depth[i] = 0;
            height[i] = 1;
        }
        this.pathCompression = pathCompression;
    }

    public WQUPC_ALT(int n) {
        count = n;
        parent = new int[n];
        depth = new int[n];
        height = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
            depth[i] = 0;
            height[i] = 1;
        }
        this.pathCompression = true;
    }
    public void show() {
        for (int i = 0; i < parent.length; i++) {
           System.out.printf("%d: %d, %d\n", i, parent[i], depth[i]);

        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
            int root = p;
            while(root != parent[root]){
                root = parent[root];
            }
            return root;
    }
    //Method to find the number of links on path from node to root.
    public void updateDepth(int d1) {
        int temp = d1;
            for (int n = 0; n < parent.length; n++) {
                if (parent[n] == temp) {
                    updateDepth(n);
                    depth[n]++;
                }
            }
    }
    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (connected(p,q)) {
            return;
        }
        // shorter depth linked to larger depth
        if (depth[p] <= depth[q]) {
            parent[rootP] = rootQ;
            height[q] += height[p];
                depth[p] += 1;
            updateDepth(p);//update Depth of all nodes below p having p as parent.
            if(parent[p]!=parent[parent[p]]) { //if depth is 0 then dont increase the depth of parent node
                depth[parent[p]]++;
            }
            if(pathCompression) {
                        doPathCompression(p);
            }
        } else {
            parent[rootQ] = rootP;
            height[p] += height[q];
                depth[q] += 1;
            updateDepth(q);//update Depth of all nodes below q having q as parent.
            if(parent[q]!=parent[parent[q]]) { //if depth is 0 then dont increase the depth of parent node
                depth[parent[q]]++;
            }
            if(pathCompression) {
                        doPathCompression(q);
            }
        }
        count--;
    }
//Implemented Path Compression to point every intermediate nodes to the root.
   private void doPathCompression(int i) {
        int root = i;
        int temp;
        while(root != parent[root]){
            temp=root;
            root = parent[root];
            parent[temp] = find(temp);
            depth[temp]--;
        }
    }
}
