package mco364;

import java.util.concurrent.ScheduledThreadPoolExecutor;

class MyThread implements Runnable {

    private int row;
    private boolean[][] nextGeneration;
    private GameOfLife game;

    MyThread(int row, boolean[][] nextGeneration, GameOfLife game) {
        this.row = row;
        this.nextGeneration = nextGeneration;
        this.game = game;
    }

    @Override
    public void run() {
        //System.out.println(Thread.currentThread().getName()+ " has begun.");
        checkRow();
        //System.out.println(Thread.currentThread().getName() + "has ended.");
    }

    private void checkRow() {
        for (int i = 1; i < game.boardSize - 1; i++) {
            nextGeneration[row][i] = game.isAliveNextGeneration(row, i);
        }
    }
}

public class GameOfLife {

    final int boardSize = 27;
    boolean[][] board1 = new boolean[boardSize][boardSize];
    boolean[][] board2 = new boolean[boardSize][boardSize];
    boolean[][] thisGeneration;
    boolean[][] nextGeneration;
    boolean isThisGeneration = false;

    GameOfLife(int pattern) {
        setupBoard(pattern);
    }

    public int neighborCount(int row, int col) {
        int counter = 0;
        if (thisGeneration[row - 1][col - 1]) {
            counter++;
        }
        if (thisGeneration[row - 1][col]) {
            counter++;
        }
        if (thisGeneration[row - 1][col + 1]) {
            counter++;
        }
        if (thisGeneration[row][col - 1]) {
            counter++;
        }
        if (thisGeneration[row][col + 1]) {
            counter++;
        }
        if (thisGeneration[row + 1][col - 1]) {
            counter++;
        }
        if (thisGeneration[row + 1][col]) {
            counter++;
        }
        if (thisGeneration[row + 1][col + 1]) {
            counter++;
        }
        return counter;
    }

    public boolean isAliveNextGeneration(int row, int col) {
        thisGeneration = isThisGeneration ? board1 : board2;
        int count = neighborCount(row, col);
        if (thisGeneration[row][col]) {
            return (count == 2 || count == 3);
        } else {
            return count == 3;
        }
    }

    public void nextGen() {
        isThisGeneration = !isThisGeneration;
        nextGeneration = isThisGeneration ? board2 : board1;
        ScheduledThreadPoolExecutor pool;
        pool = new ScheduledThreadPoolExecutor(10);

        for (int i = 1; i < 27; i++) {
            pool.execute(new Thread(new MyThread(i, nextGeneration, this)));
        }

    }

    private void setupBoard(int pattern) {
        //Blinker
        if (pattern == 1) {
            board1[12][11] = true;
            board1[12][12] = true;
            board1[12][13] = true;
        }
        
        //Toad
        if (pattern == 2) {
            board1[12][12] = true;
            board1[12][13] = true;
            board1[12][14] = true;
            board1[13][11] = true;
            board1[13][12] = true;
            board1[13][13] = true;
        }
        
        //Beacon
        if (pattern == 3) {
            board1[10][10] = true;
            board1[10][11] = true;
            board1[11][10] = true;
            board1[11][11] = true;
            board1[12][12] = true;
            board1[12][13] = true;
            board1[13][12] = true;
            board1[13][13] = true;
        }
        
        //Pulsar
        if (pattern == 4) {
            //Top Left
            board1[7][9] = true;
            board1[7][10] = true;
            board1[7][11] = true;
            board1[9][7] = true;
            board1[10][7] = true;
            board1[11][7] = true;
            board1[12][9] = true;
            board1[12][10] = true;
            board1[12][11] = true;
            board1[9][12] = true;
            board1[10][12] = true;
            board1[11][12] = true;
            
            //Bottom Left
            board1[14][9] = true;
            board1[14][10] = true;
            board1[14][11] = true;
            board1[15][7] = true;
            board1[16][7] = true;
            board1[17][7] = true;
            board1[19][9] = true;
            board1[19][10] = true;
            board1[19][11] = true;
            board1[15][12] = true;
            board1[16][12] = true;
            board1[17][12] = true;
            
            //Top Right
            board1[7][15] = true;
            board1[7][16] = true;
            board1[7][17] = true;
            board1[9][14] = true;
            board1[10][14] = true;
            board1[11][14] = true;
            board1[12][15] = true;
            board1[12][16] = true;
            board1[12][17] = true;
            board1[9][19] = true;
            board1[10][19] = true;
            board1[11][19] = true;
            
            //Bottom Right
            board1[14][15] = true;
            board1[14][16] = true;
            board1[14][17] = true;
            board1[15][14] = true;
            board1[16][14] = true;
            board1[17][14] = true;
            board1[19][15] = true;
            board1[19][16] = true;
            board1[19][17] = true;
            board1[15][19] = true;
            board1[16][19] = true;
            board1[17][19] = true;
            
            
        }
        //Pentadechalthon
        if (pattern == 5) {
            board1[11][10] = true;
            board1[11][15] = true;
            board1[12][8] = true;
            board1[12][9] = true;
            board1[12][11] = true;
            board1[12][12] = true;
            board1[12][13] = true;
            board1[12][14] = true;
            board1[12][16] = true;
            board1[12][17] = true;
            board1[13][10] = true;
            board1[13][15] = true;
        }
        
    }
    
    public void printBoard(){
        for (int i = 1; i < boardSize -1; i++) {
            for (int j = 1; j < boardSize -1; j++) {
                System.out.print("|" + (thisGeneration[i][j] ? "\u2588" : " "));
                System.out.print(j == 25 ? "|\n" : "");
            }
        }
    }

}
