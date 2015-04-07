package Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amd on 3/15/15.
 */
public class Board {
    public static final int SIZE=8;
    Player players[][];

    public Board() {
        players = new Player[9][9];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int i = 1 ; i <= 8 ; i+=7) {
            String color=null;
            if(i == 1)
                color="w";
            else
                color="b";
            players[i][1] = new Rook(color, new Pos(i, 1));
            players[i][2] = new Knight(color, new Pos(i, 2));
            players[i][3] = new Bishop(color, new Pos(i, 3));
            players[i][4] = new Queen(color, new Pos(i, 4));
            players[i][5] = new King(color, new Pos(i, 5));
            players[i][8] = new Rook(color, new Pos(i, 8));
            players[i][7] = new Knight(color, new Pos(i, 7));
            players[i][6] = new Bishop(color, new Pos(i, 6));
        }

        for(int i = 2 ; i <=7 ; i+=5){
            String color=null;
            if(i==2)
                color="w";
            else
                color="b";
            for(int j = 1 ; j<=8 ; j++){
                players[i][j] = new Pawn(color, new Pos(i, j));
            }
        }
    }

    public void showBoard(){
        System.out.print("\n");
        boolean flip=false;
        for(int i = SIZE ; i >= 1; i-- ){
            for(int j = 1 ; j <= SIZE; j++){
                String val="##";
                flip=!flip;
                if(flip)
                    val="  ";
                if(players[i][j]!=null)
                    val = players[i][j].getValue();
                System.out.print(val+" ");
            }
            System.out.print("\n");
            flip=!flip;
        }
        System.out.print("\n");

    }

    public boolean playMove(String color, Pos start, Pos end ){
        Player fromPlayer = players[start.getX()][start.getY()];
    //    Player toPlayer = players[end.getX()][end.getY()];
        if(fromPlayer==null)
            return false;
        if(!fromPlayer.getColor().equals(color))
            return false;
        if(!fromPlayer.isValidMove(end, players))
            return false;
        else {
            //play move
            Player toPlayer = players[end.getX()][end.getY()];
            if (toPlayer!=null){
                toPlayer.kill();
                players[end.getX()][end.getY()] = fromPlayer;
                fromPlayer.setPos(end);
                players[start.getX()][start.getY()]=null;
            }
            else{
                players[end.getX()][end.getY()] = fromPlayer;
                fromPlayer.setPos(end);
                players[start.getX()][start.getY()]=null;
            }
            String c = color;
            if (c.equals("w"))
                c = "b";
            else c = "w";
            if(isCheck(c))
                System.out.println("Check!\n");
            if(isCheckMate(c)) {
                System.out.println("Check Mate" );
                if(color.equals("w"))
                    System.out.println("White Wins");
                else
                    System.out.println("Black Wins");
                System.exit(1);
            }
            return true;
        }
    }

    public boolean isCheck(String color){
        Player oppKing = null;
        for(int i = 1 ; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++){
                if(players[i][j]!=null && players[i][j].getColor().equals(color) && players[i][j].getValue().equals(color+"K"))
                    oppKing = players[i][j];
            }
        }
        for(int i = 1 ; i <=SIZE ; i++){
            for(int j=1; j<=SIZE; j++){
                if(players[i][j]!=null && !players[i][j].getColor().equals(color)){
                    if (players[i][j].isValidMove(oppKing.getPos(), players))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(String color){
        Player oppKing = null;
        for(int i = 1 ; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++){
                if(players[i][j]!=null && players[i][j].getColor().equals(color) && players[i][j].getValue().equals(color+"K"))
                    oppKing = players[i][j];
            }
        }
        return oppKing == null;
    }
}
