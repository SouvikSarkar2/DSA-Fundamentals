//SUDOKU SOLVER

public class Sudoku{
    public static void main(String args[]){
        int sudoku[][]={{0,0,8,0,0,0,0,0,0},
                        {4,9,0,1,5,7,0,0,2},
                        {0,0,3,0,0,4,1,9,0},
                        {1,8,5,0,6,0,0,2,0},
                        {0,0,0,0,2,0,0,6,0},
                        {9,6,0,4,0,5,3,0,0},
                        {0,3,0,0,7,2,0,0,4},
                        {0,4,9,0,3,0,0,5,7},
                        {8,2,7,0,0,9,0,1,3}};

        if(SudokuSolver(sudoku,0,0)){
            System.out.println("-------Solution exist-------");
            printSudoku(sudoku);
        }
        else{
            System.out.println("Solution doesnot exist");
        }                
    }
    public static boolean SudokuSolver(int sudoku[][],int row,int column){
        //base case
        if(row==9){
            return true;
        }
        //recursion case
        int newrow=row,newcolumn = column+1;
        if(column==8){
            newrow=row+1;
            newcolumn=0;
        }
        if(sudoku[row][column]!=0){
            return SudokuSolver(sudoku,newrow,newcolumn);
        }
        for(int i = 1 ; i<=9 ; i++){
            if(isSafe(sudoku,row,column,i)){
                sudoku[row][column]=i;
                if(SudokuSolver(sudoku,newrow,newcolumn)){
                    return true;
                }
                sudoku[row][column]=0;
            }
        }
        
        return false;
    }
    public static boolean isSafe(int sudoku[][],int row,int column,int i){
        //column check
        for(int j = 0 ; j<9 ; j++){
            if(sudoku[j][column]==i){
                return false;
            }
        }
        //row check
        for(int j = 0 ; j<9 ; j++){
            if(sudoku[row][j]==i){
                return false;
            }
        }
        //grid check
        int sr = (row/3)*3;
        int sc = (column/3)*3;
        for(int j = sr ; j<=sr+2 ; j++){
            for(int k = sc ; k<=sc+2 ; k++){
                if(sudoku[j][k]==i){
                    return false;
                }
            }
        }
        return true;
    }
    public static void printSudoku(int sudoku[][]){
        for(int i = 0 ; i<9 ; i++){
            for(int j = 0 ; j<9 ; j++){
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}



//NQUEEN



import java.util.*;
public class Nqueen{
    public static void main(String args[]){
        int n = 6;
        char board[][]=new char[n][n];
        for(int i = 0 ; i<board.length ; i++){
            for(int j = 0 ; j<board.length ; j++){
                board[i][j]='.';
            }
        }
        nQueen(board,0);
    }
    public static void nQueen(char board[][],int row){
        //base case
        if(row==board.length){
            printboard(board);
            return;
        }
        //column select
        for(int j = 0 ; j<board.length ; j++){
            if(isSafe(board,row,j)){
            board[row][j]='Q';
            nQueen(board,row+1);
            board[row][j]='.';   // backtraking step
          }  
        }

    }
    public static void printboard(char board[][]){
        System.out.println("-----------chess board-----------");
        for(int i = 0 ; i<board.length ; i++){
            for(int j = 0 ; j<board.length ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean isSafe(char board[][],int row,int column){
        //vertical up
        for(int i = row-1 ; i>=0 ; i--){
            if(board[i][column]=='Q'){
                return false;
            }
        }
        //left diagonal up
        for(int i=row-1 ,j=column-1 ; i>=0 && j>=0 ; i--,j--){
            if(board[i][j]=='Q'){
                return false;
            }
        }

        //right diagonal up
        for(int i = row-1 ,j=column+1 ; i>=0 && j<board.length ; i--,j++){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        return true;
    }
}
