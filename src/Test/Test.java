package Test;

import Player.Board;
import Player.Pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by amd on 3/15/15.
 */
public class Test {
    public static void main(String args[]) throws IOException {
//        TestPawn testPawn = new TestPawn();
//        testPawn.testPawn();
        String filename = "moves.txt";
        Scanner s = new Scanner(new File(filename));
        Board b = new Board();
        boolean drawable = false;
        boolean flip = false;
        String color="w";

        while (s.hasNext())
        {
           b.showBoard();
            String str = s.nextLine();

            if (str.contains("draw?"))
                drawable = true;
            if(str.equals("draw") && drawable) {
                System.out.println("Draw");
                System.exit(1);
                drawable = false;
            }
            if (str.equals("resign")){
                if (color.equals("w"))
                    System.out.println("White Resign\nBlack Wins");
                else
                    System.out.println("Black Resign\nWhite Wins");
                System.exit(1);
            }

            Pos pos1 = getPos(str.split(" ")[0].trim());
            Pos pos2 = getPos(str.split(" ")[1].trim());

            if(flip)
                color="b";
            else
                color="w";
            flip = !flip;

            if(!b.playMove(color, pos1, pos2)){
                System.out.println("Invalid Move");
            }

        }

    }

    static public Pos getPos(String str){
        int x = (int)str.charAt(0)-96;
        int y = Integer.parseInt(str.charAt(1)+"");

        if((x<1)||(x>8)||(y<1)||(y>8))
        {
            System.out.println("Error in input");
            System.exit(1);
        }
        else {
            return new Pos(y,x);
        }
        return null;
    }
}
