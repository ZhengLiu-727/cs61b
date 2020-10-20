package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Set;
import java.util.HashSet;

public class Percolation {

    private int N;
    private int nOpen = 0;
    private WeightedQuickUnionUF connectedGrids;
    private WeightedQuickUnionUF connectedGridsWithoutBot; // prevent backwash
    private boolean[][] openGridIn1D;
    private int topGrid = 0;
    private int botGrid;


    /**
     * create N-by-N grid, with all sites initially blocked
     *
     * @throws IllegalArgumentException when N <= 0
     */
    public Percolation(int N) throws IllegalArgumentException {
        if(N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater than 0 but given N = " + N + ".");
        }
        this.N = N;
        botGrid = N * N + 1;
        connectedGrids = new WeightedQuickUnionUF(N * N + 2);
        connectedGridsWithoutBot = new WeightedQuickUnionUF(N * N + 1);
        openGridIn1D = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                openGridIn1D[i][j] = false;
            }
        }
    }


    /**
     * helper function to return the 1D index of a grid
     */
    private int xyToIndexIn1D(int row, int col) {
        return N * row + col + 1;
    }

    private boolean isInvalidIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return false;
    }


    /**
     * Opens the site at (row, col) if it is not open already
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public void open(int row, int col) throws IndexOutOfBoundsException{
        if(isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + ".");
        }

        // Update the grid's condition if it's not already open
        if (openGridIn1D[row][col]) {
            return;
        }
        openGridIn1D[row][col] = true;
        nOpen += 1;


        int indexIn1D = xyToIndexIn1D(row, col);
        if (indexIn1D < N + 1) {    // connect the grid with topGrid if it's in the top layer
            connectedGrids.union(topGrid, indexIn1D);
            connectedGridsWithoutBot.union(topGrid, indexIn1D);
        }
        if (N * N - indexIn1D < N) { // connect the grid with botGrid if it's in the bottom layer
            connectedGrids.union(botGrid, indexIn1D);
        }

        // Update connection and full conditions both from top and bottom with neighbors around
        updateConnection(row, col, row - 1, col); // On the north
        updateConnection(row, col, row, col + 1); // On the east
        updateConnection(row, col, row + 1, col); // On the south
        updateConnection(row, col, row, col - 1); // On the west

    }

    private void updateConnection(int row, int col, int neighborRow, int neighborCol) {
        try {
            if (isOpen(neighborRow, neighborCol)) {
                int indexIn1D = xyToIndexIn1D(row, col);
                int neighborIndexIn1D = xyToIndexIn1D(neighborRow, neighborCol);
                connectedGrids.union(indexIn1D, neighborIndexIn1D);
                connectedGridsWithoutBot.union(indexIn1D, neighborIndexIn1D);
            }
        } catch (IndexOutOfBoundsException ex) {
            return;
        }
    }


    /**
     * check if the site (row, col) is open
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException{
        if(isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + ".");
        }
        return openGridIn1D[row][col];
    }


    /**
     * return if the grid is full
     *
     * @throws IndexOutOfBoundsException given arguments outside of constructed range
     */
    public boolean isFull(int row, int col) throws IndexOutOfBoundsException{
        if(isInvalidIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + ".");
        }
        int indexIn1D = xyToIndexIn1D(row, col);
        return connectedGridsWithoutBot.connected(topGrid, indexIn1D) && isOpen(row, col);
    }

    /**
     * return number of open sites
     */
    public int numberOfOpenSites() {
        return nOpen;
    }


    /**
     * return if the system percolate
     */
    public boolean percolates() {
        return connectedGrids.connected(topGrid, botGrid);
    }


    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation test = new Percolation(4);
        test.open(0, 2);
        test.open(1, 2);
        test.open(2, 2);
        test.open(3, 2);

        test.open(0, 0);
        test.open(3, 0);
        System.out.println(test.percolates());
        System.out.println(test.isFull(0, 0));
        System.out.println(test.isFull(3, 0));
    }


}
