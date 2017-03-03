/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Thong
 */
public class adversarialSearch {
    
    private static Board currentState;
    private static char[] a = {'a','b','c','d','e','f','g','h'};
    private static int score;
    private static Point move;
    private static int myTime;
    private static long startTime, endTime;
    private static String a1,a2;
    private static int i = 1;
    private static Random random = new Random();
    
    public static void search(char[][] state, int player, int level, int time)
    {
        int row, column;
        int count = 0, count1 = 0;
        int m1 = 0;
        int m2 = 0;
        currentState = new Board(state);
        myTime = time;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < currentState.getState().length; i++)
        {
            for (int j = 0; j < currentState.getState().length; j++)
            {
                if (currentState.getState()[i][j] == ' ')
                    count++;
                if ((currentState.getState()[i][j] == 'O') || (currentState.getState()[i][j] == 'X'))
                {
                    count1++;
                    m1 = i;
                    m2 = j;
                }
            }
        }
        if (count == 64)
        {
            int number = 1 + random.nextInt(4);
            switch (number)
            {
                case 1:
                    row = 3;
                    column = 4;
                    break;
                case 2:
                    row = 4;
                    column = 4;
                    break;
                case 3:
                    row = 4;
                    column = 3;
                    break;
                case 4:
                    row = 3;
                    column = 3;
                    break;
                default:
                    row = 0;
                    column = 0;
                    break;
            }
                    
        }
        else if (count1 == 1)
        {
            if (m2+4 > 7)
            {
                row = m1;
                column = m2 - 1;
            }
            else
            {
                row = m1;
                column = m2 + 1;
            }
        }
        else
        {
            int[] result = pruning(level, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
            score = result[0];
            row = result[1];
            column = result[2];
        }
        move = new Point(row,column);
        currentState.placeAMove(move, player);
        currentState.printBoard();
        a1 = Character.toString(a[row]);
        a2 = Integer.toString(column + 1);
        System.out.println("The computer move is : " + a1 + a2);
    }
    
    public static int[] pruning(int depth, int player, int alpha, int beta)
    {
        int score;
        int bestRow = -1;
        int bestColumn = -1;
        
        ArrayList<Point> pointsGenerated = currentState.generatePoints();
        
        if (pointsGenerated.size() == 1)
        {
            score = currentState.evaluateFunction();
            return new int[] {score, pointsGenerated.get(0).x, pointsGenerated.get(0).y};
        }
        
        else if (pointsGenerated.isEmpty() || depth == 0 || (System.currentTimeMillis() - startTime >= myTime))
        {
            score = currentState.evaluateFunction();
            return new int[] {score, bestRow, bestColumn};
        }
        else
        {
            for (Point point : pointsGenerated)
            {
                if (player == 2)
                {
                    currentState.placeAMove(point, player);
                    score = pruning(depth - 1, 1, alpha, beta)[0];
                    if (score > alpha)
                    {
                        alpha = score;
                        bestRow = point.x;
                        bestColumn = point.y;                        
                    }
                }
                else
                {
                    currentState.placeAMove(point, player);
                    score = pruning(depth - 1, 2, alpha, beta)[0];
                    if (score < beta)
                    {
                        beta = score;
                        bestRow = point.x;
                        bestColumn = point.y;
                    }
                }
                currentState.removeAMove(point);
                if (alpha >= beta) break;
            }
            return new int[] {(player == 2) ? alpha : beta, bestRow, bestColumn};
        }
        
    }
    
}
