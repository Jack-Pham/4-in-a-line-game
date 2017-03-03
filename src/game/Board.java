/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Thong
 */
public class Board {
    
    private final int BSIZE = 8;
    private char[][] board = new char[BSIZE][BSIZE];
    private char[] a = {'A','B','C','D','E','F','G','H'};
    private boolean chanceToLose = false;
    private boolean chanceToWin = false;
    private Point[] move = new Point[2];
    private int score;
    
    public Board(char[][] state)
    {
        board = state;
    }
    
    public void placeAMove(Point p, int player)
    {
        if (player == 1)
            board[p.x][p.y] = 'O';
        else
            board[p.x][p.y] = 'X';
    }
    
    public void removeAMove(Point p)
    {
        board[p.x][p.y] = ' ';
    }
    
    public ArrayList<Point> generatePoints()
    {
        ArrayList<Point> successors = new ArrayList<Point>();
        if (checkChanceToWin1())
        {
            if (move[0].x != -1 && move[0].y != -1)
            {
                successors.add(move[0]);               
            }
            if (move[1].x != -1 && move[1].y != -1)
            {
                successors.add(move[1]);              
            }
        }
        else if(checkChanceToLose1())
        {
            if (move[0].x != -1 && move[0].y != -1)
            {
                successors.add(move[0]);               
            }
            if (move[1].x != -1 && move[1].y != -1)
            {
                successors.add(move[1]);              
            }
        }
        else if (checkChanceToWin2())
        {
            if (move[0].x != -1 && move[0].y != -1)
            {
                successors.add(move[0]);               
            }
            if (move[1].x != -1 && move[1].y != -1)
            {
                successors.add(move[1]);              
            }
        }
        else if (checkChanceToLose2())
        {
            if (move[0].x != -1 && move[0].y != -1)
            {
                successors.add(move[0]);               
            }
            if (move[1].x != -1 && move[1].y != -1)
            {
                successors.add(move[1]);              
            }
        }
        else
        {
            for (int i = 0; i < BSIZE; i++)
            {
                for (int j = 0; j < BSIZE; j++)
                {
                    if (board[i][j] == ' ')
                    {
                        successors.add(new Point(i,j));
                    }
                }
            }  
        }
        return successors;
    }
    
    public int evaluateFunction()
    {       
        int finalPoint = 0;        
        int pieceCount1 = 0;
        int pieceCount2 = 0;
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                int point = 0;
                if (board[i][j] == ' ')
                {
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount1 = 0;
                    
                    lastColumn++;
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 1)
                            point -= 10;
			if (pieceCount1 == 2)
                            point -= 50;                                                       
			if (pieceCount1 == 3)
                            point -= 500;
                        lastColumn++;
                    }
                    
                    if (j - 1 >= 0)
                        lastColumn = j - 1;
                    while ((lastColumn >= 0) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 1)
                            point -= 10;
			if (pieceCount1 == 2)
                            point -= 500;                           
			if (pieceCount1 == 3)
                            point -= 500;                           
                        lastColumn--;
                    }
                    
                    pieceCount1 = 0;
                    lastColumn = j;
                    lastRow++;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 1)
                            point -= 10;
			if (pieceCount1 == 2)
                            point -= 50;                          
			if (pieceCount1 == 3)
                            point -= 500;                            
                        lastRow++;
                    }
                    
                    if (i - 1 >= 0)
                        lastRow = i - 1;
                    while ((lastRow >= 0) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 1)
                            point -= 10;
			if (pieceCount1 == 2)
                            point -= 50;                           
			if (pieceCount1 == 3)
                            point -= 500;                           
                        lastRow--;
                    }
            
                    lastRow = i;
                    lastColumn = j;
                    pieceCount2 = 0;
                    
                    lastColumn++;
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount2++;
                        if (pieceCount2 == 1)
                            point += 10;
			if (pieceCount2 == 2)
                            point += 50;
			if (pieceCount2 == 3)
                            point += 500;
                        lastColumn++;
                    }
                    
                    if (j - 1 >= 0)
                        lastColumn = j - 1;
                    while ((lastColumn >= 0) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount2++;
                        if (pieceCount2 == 1)
                            point += 10;
			if (pieceCount2 == 2)
                            point += 50;
			if (pieceCount2 == 3)
                            point += 500;
                        lastColumn--;
                    }
                    
                    pieceCount2 = 0;
                    lastColumn = j;
                    lastRow++;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount2++;
                        if (pieceCount2 == 1)
                            point += 10;
			if (pieceCount2 == 2)
                            point += 50;
			if (pieceCount2 == 3)
                            point += 500;
                        lastRow++;
                    }
                    
                    if (i - 1 >= 0)
                        lastRow = i - 1;
                    while ((lastRow >= 0) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount2++;
                        if (pieceCount2 == 1)
                            point += 10;
			if (pieceCount2 == 2)
                            point += 50;
			if (pieceCount2 == 3)
                            point += 500;
                        lastRow--;
                    }
                }
                else if (board[i][j] == 'O')
                {
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount1 = 0;
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 4)
                            point -= 5000;
                        lastColumn++;
                    }
                    pieceCount1 = 0;
                    lastColumn = j;
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 4)
                            point -= 5000;
                        lastRow++;
                    }                                      
                }
                else if (board[i][j] == 'X')
                {
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount1 = 0;
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 4)
                            point += 5000;
                        lastColumn++;
                    }
                    pieceCount1 = 0;
                    lastColumn = j;
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount1++;
                        if (pieceCount1 == 4)
                            point += 5000;
                        lastRow++;
                    }                                    
                }
                finalPoint += point;
            }
        }       
        return finalPoint;
    }
    
    public boolean checkChanceToLose1()
    {
        int pieceCount;
        move[0] = new Point(-1,-1);
        move[1] = new Point(-1,-1);
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {               
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount = 0;

                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount++;			
			if (pieceCount == 3)
                        {
                            if ((lastColumn + 1 < BSIZE ) && (board[lastRow][lastColumn + 1] == ' '))
                            {
                                chanceToLose = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;                            
                            }
                            else if ((lastColumn - 3 >= 0) && (board[lastRow][lastColumn - 3] == ' '))
                            {
                                chanceToLose = true;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 3;
                            }
                        }                        
                        lastColumn++;
                    }                   
                    
                    pieceCount = 0;
                    lastColumn = j;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount++;
			if (pieceCount == 3)
                        {
                            if ((lastRow + 1 < BSIZE ) && (board[lastRow + 1][lastColumn] == ' '))
                            {                                
                                chanceToLose = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                            }
                            else if ((lastRow - 3 >= 0) && (board[lastRow - 3][lastColumn] == ' '))
                            {
                                chanceToLose = true;
                                move[1].x = lastRow - 3;
                                move[1].y = lastColumn;
                            }                          
                        }                  
                        lastRow++;
                    }               
            }
        }
        return chanceToLose;
    }
    
    public boolean checkChanceToLose2()
    {
        int pieceCount;
        move[0] = new Point(-1,-1);
        move[1] = new Point(-1,-1);
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {               
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount = 0;
                    
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount++;
			if (pieceCount == 2)
                        {                            
                            if (((lastColumn + 1 < BSIZE ) && (board[lastRow][lastColumn + 1] == ' '))
                                    && ((lastColumn - 2 >= 0) && (board[lastRow][lastColumn - 2] == ' ')))
                            {
                                chanceToLose = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 2;
                            }
                        }			  
                        lastColumn++;
                    }
                    
                    pieceCount = 0;
                    lastColumn = j;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                    {
                        pieceCount++;
			if (pieceCount == 2)
                        {                            
                            if (((lastRow + 1 < BSIZE ) && (board[lastRow + 1][lastColumn] == ' '))
                                    && ((lastRow - 2 >= 0) && board[lastRow - 2][lastColumn] == ' '))
                            {
                                chanceToLose = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                                move[1].x = lastRow - 2;
                                move[1].y = lastColumn;
                            }
                        }			
                        lastRow++;
                    }                   
            }
        }
        return chanceToLose;
    }
    
    public boolean checkChanceToWin1()
    {
        int pieceCount;
        move[0] = new Point(-1,-1);
        move[1] = new Point(-1,-1);
        
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount = 0;
                    
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount++;			
			if (pieceCount == 3)
                        {
                            if ((lastColumn + 1 < BSIZE ) && (board[lastRow][lastColumn + 1] == ' '))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;                            
                            }
                            else if ((lastColumn - 3 >= 0) && (board[lastRow][lastColumn - 3] == ' '))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 3;
                            }
                        }
                        if (pieceCount == 2)
                        {
                            if ((lastColumn + 1 < BSIZE) && (lastColumn + 2 < BSIZE) && 
                                    (board[lastRow][lastColumn + 1] == ' ') && (board[lastRow][lastColumn + 2] == 'X'))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;
                            }
                            else if ((lastColumn - 2 >= 0) && (lastColumn - 3 >= 0) && 
                                    (board[lastRow][lastColumn - 2] == ' ') && (board[lastRow][lastColumn - 3] == 'X'))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 2;
                            }
                        }
                        lastColumn++;
                    }
                    
                    pieceCount = 0;
                    lastColumn = j;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount++;			
			if (pieceCount == 3)
                        {
                            if ((lastRow + 1 < BSIZE ) && (board[lastRow + 1][lastColumn] == ' '))
                            {                                
                                chanceToWin = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                            }
                            else if ((lastRow - 3 >= 0) && (board[lastRow - 3][lastColumn] == ' '))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow - 3;
                                move[1].y = lastColumn;
                            }                          
                        }
                        if (pieceCount == 2)
                        {
                            if ((lastRow + 1 < BSIZE) && (lastRow + 2 < BSIZE) && 
                                    (board[lastRow + 1][lastColumn] == ' ') && (board[lastRow + 2][lastColumn] == 'X'))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                            }
                            else if ((lastRow - 2 >= 0) && (lastRow - 3 >= 0) && 
                                    (board[lastRow - 2][lastColumn] == ' ') && (board[lastRow - 3][lastColumn] == 'X'))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow - 2;
                                move[1].y = lastColumn;
                            }
                        }
                        lastRow++;
                    }               
            }
        }
        return chanceToWin;       
    }
    
    public boolean checkChanceToWin2()
    {
        int pieceCount;
        move[0] = new Point(-1,-1);
        move[1] = new Point(-1,-1);
        
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                    int lastRow = i;
                    int lastColumn = j;
                    pieceCount = 0;
                    
                    while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount++;
			if (pieceCount == 2)
                        {                            
                            if (((lastColumn + 1 < BSIZE ) && (board[lastRow][lastColumn + 1] == ' '))
                                    && ((lastColumn - 2 >= 0) && (board[lastRow][lastColumn - 2] == ' ')))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 2;
                            }
                            else if ((lastColumn + 1 < BSIZE ) && (board[lastRow][lastColumn + 1] == ' '))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow;
                                move[0].y = lastColumn + 1;
                            }
                            else if ((lastColumn - 2 >= 0) && (board[lastRow][lastColumn - 2] == ' '))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow;
                                move[1].y = lastColumn - 2;
                            }                                
                        }			
                        lastColumn++;
                    }
                
                    pieceCount = 0;
                    lastColumn = j;
                    
                    while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                    {
                        pieceCount++;
			if (pieceCount == 2)
                        {                            
                            if (((lastRow + 1 < BSIZE ) && (board[lastRow + 1][lastColumn] == ' '))
                                    && ((lastRow - 2 >= 0) && board[lastRow - 2][lastColumn] == ' '))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                                move[1].x = lastRow - 2;
                                move[1].y = lastColumn;
                            }
                            else if ((lastRow + 1 < BSIZE ) && (board[lastRow + 1][lastColumn] == ' '))
                            {
                                chanceToWin = true;
                                move[0].x = lastRow + 1;
                                move[0].y = lastColumn;
                            }
                            else if ((lastRow - 2 >= 0) && (board[lastRow - 2][lastColumn] == ' '))
                            {
                                chanceToWin = true;
                                move[1].x = lastRow - 2;
                                move[1].y = lastColumn;
                            }                     
                        }			
                        lastRow++;
                    }
            }
        }
        return chanceToWin;
    }
    
    public boolean checkXWin()
    {
        int pieceCount;
        
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                int lastRow = i;
                int lastColumn = j;
                pieceCount = 0;
                
                while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                {
                    pieceCount++;			
                    if (pieceCount == 4)
                    {
                        return true;
                    }
                    lastColumn++;
                }
                
                pieceCount = 0;
                lastColumn = j;
                
                while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'X'))
                {
                    pieceCount++;			
                    if (pieceCount == 4)
                    {
                        return true;
                    }               
                    lastRow++;
                }
            }
        }
        return false;
    }
    
    public boolean checkOWin()
    {
        int pieceCount;
        
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                int lastRow = i;
                int lastColumn = j;
                pieceCount = 0;
                
                while ((lastColumn < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                {
                    pieceCount++;			
                    if (pieceCount == 4)
                    {
                        return true;
                    }
                    lastColumn++;
                }
                
                pieceCount = 0;
                lastColumn = j;
                
                while ((lastRow < BSIZE) && (board[lastRow][lastColumn] == 'O'))
                {
                    pieceCount++;			
                    if (pieceCount == 4)
                    {
                        return true;
                    }               
                    lastRow++;
                }
            }
        }
        return false;
    }
    
    public boolean checkDraw()
    {
        int count = 0;
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j < BSIZE; j++)
            {
                if (board[i][j] == ' ')
                    count++;
            }
        }
        return count == 0;
    }
    
    public boolean checkValidMove(Point p)
    {
        return board[p.x][p.y] == ' ';
    }
    
    public void printBoard()
    {
        System.out.println();
        for (int i = 0; i <= BSIZE; i++)
        {
            if (i == 0)
                System.out.print("   ");
            else
                System.out.print(i + "   ");
        }
        System.out.println();
        for (int i = 0; i < BSIZE; i++)
        {
            for (int j = 0; j <= BSIZE; j++)
            {
                if (j == 0)
                    System.out.print(a[i] + " ");
                else
                {
                    if ((board[i][j-1] == 'X') || (board[i][j-1] == 'O'))
                        System.out.printf(" %s |",board[i][j-1]);
                    else
                        System.out.printf(" %s |",' ');
                }
            }
            System.out.println();
            System.out.println("  --------------------------------");
        }
    }
    
    public char[][] getState()
    {
        return board;   
    }
    
    public int getScore()
    {
        return score = evaluateFunction();
    }
    
}
