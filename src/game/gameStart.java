package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author Thong
 */
public class gameStart {
    
    private static char[][] state = new char[8][8];
    private static char[] a = {'a','b','c','d','e','f','g','h'};
    private static String move;
    private static char a1,a2;
    private static int x;
    private static Point p;
    private static Board board;
    private static int currentPlayer;
    private static Scanner input = new Scanner(System.in);
    private static String choice;
    private static int time;

    public static void main(String[] args) 
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
                state[i][j] = ' ';
        }
        System.out.println("Welcome to the game contest!");
        board = new Board(state);
        board.printBoard();       
        
        System.out.print("\nWould you like to play first (y/n) : ");
        choice = input.nextLine();
        if (choice.equals("y"))
            currentPlayer = 1;
        else
            currentPlayer = 2;
        
        System.out.print("\nHow long will the computer be allowed to place a move (in seconds) ? ");
        time = input.nextInt()*1000;
        
        while ((!board.checkOWin()) && (!board.checkXWin()) && (!board.checkDraw()))
        {
            if (currentPlayer == 1)
            {
                p = playByHuman();
                while (!board.checkValidMove(p))
                {
                    System.out.println("\nThis move has been played.\n");
                    p = playByHuman();
                }
                board.placeAMove(p, currentPlayer);
                board.printBoard();
                //System.out.println(board.getScore());
                if ((board.checkOWin()) || (board.checkXWin()) || (board.checkDraw()))
                {
                    break;
                }
                currentPlayer = 2;
            }
            else
            {
                playByMachine(10, time);
                currentPlayer = 1;
            }
        }
        
        if (board.checkOWin())
        {
            System.out.println("\nHuman Wins!!!\n");
        }
        else if (board.checkXWin())
        {
            System.out.println("\nComputer Wins!!!\n");           
        }
        else
        {
            System.out.println("\nIt's a draw!!!\n");
        }
    }
    
    public static Point playByHuman()
    {
        Point p = new Point(0,0);
        boolean validMove = false;
        input = new Scanner(System.in);
        System.out.print("\nChoose your next move : ");
        move = input.nextLine();
        a1 = move.charAt(0);
        a2 = move.charAt(1);
        x = Character.getNumericValue(a2);
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == a1)
            {
                validMove = true;
                break;
            }
        }
        while ((move.length() != 2) || (x < 1) || (x > 8) || (!validMove))
        {
            System.out.print("\nInvalid move. Choose your next move again : ");
            move = input.nextLine();
            a1 = move.charAt(0);
            a2 = move.charAt(1);
            x = Character.getNumericValue(a2);
            for (int i = 0; i < a.length; i++)
            {
                if (a[i] == a1)
                {
                    validMove = true;
                    break;
                }
            }
        }
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == a1)
            {
                p.x = i;
                p.y = x - 1;
            }
        }
        return p;
    }
    
    public static void playByMachine(int level, int time)
    {
        adversarialSearch.search(board.getState(), currentPlayer, level, time);
    }
    
}
