package Player;
import java.lang.Math.*;
/**
 * Created by amd on 3/15/15.
 */
public class Pawn implements Player {
    private String color;
    private Pos pos;
    private boolean dead;
    public static final String value = "P";

    public Pawn(String color, Pos pos){
        this.color=color;
        this.pos=pos;
        this.dead=false;
    }
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getXPos() {
        return pos.getX();
    }

    @Override
    public int getYPos() {
        return pos.getY();
    }

    @Override
    public Pos getPos() {
        return pos;
    }

    @Override
    public void setPos(Pos toPos) {
        this.pos = toPos;
    }

    @Override
    public boolean isValidMove(Pos toPos, Player[][] players) {
        int fromX = this.getXPos();
        int fromY = this.getYPos();
        int toX = toPos.getX();
        int toY = toPos.getY();

        if( !isPathClear(toPos, players) )
            return false;

        if((fromY == toY) && (players[toX][toY] == null)){
            // first step as 2
            if((fromX==2 || fromX==7)&& Math.abs(fromX-toX)==2)
                return true;
            //
            else return Math.abs(fromX - toX) == 1;
        }
        else if ((Math.abs(fromX - toX)==1)&&(Math.abs(fromY - toY)==1)&&(players[toX][toY]!=null))
            return true;
        else
            return false;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void move(Pos start, Pos end) {

    }

    @Override
    public String getValue() {
        return color+value;
    }

    @Override
    public void kill() {
        this.dead=true;
    }

    @Override
    public Player getPlayer(Pos pos) {
        return null;
    }

    public boolean isPathClear(Pos toPos, Player [][]players){
        int fromX = this.getXPos();
        int fromY = this.getYPos();
        int toX = toPos.getX();
        int toY = toPos.getY();

        if( Math.abs(fromX-toX)==Math.abs(fromY-toY)){
            //  e
            // /
            //s
            if((fromX < toX) && (fromY < toY)){
                for(int i = fromX+1, j = fromY+1 ; i < toX && j < toY ; i++,j++){
                    if(players[i][j]!=null)
                        return false;
                }
                return true;
            }
            //e
            // \
            //  s
            else if((fromX < toX) && (fromY > toY)){
                for(int i = fromX+1, j = fromY-1 ; i < toX && j>toY ; i++,j-- ){
                    if(players[i][j]!=null)
                        return false;
                }
                return true;
            }
            //s
            // \
            //  e
            else if ((fromX>toX) && (fromY<toY)){
                for(int i = fromX-1, j = fromY+1 ; i<toX && j>toY ; i--,j++){
                    if (players[i][j]!=null)
                        return false;
                }
                return true;
            }
            //  s
            // /
            //e
            else if((fromX>toX) && (fromY>toY)){
                for(int i = fromX-1, j=fromY-1 ; i>toX && j>toY ; i--,j--){
                    if(players[i][j]!=null)
                        return false;
                }
                return true;
            }
            System.out.println("Is Path Clear Failed");
            return true;
        }
        int i;
        if(Math.abs(fromX-toX)==0){
            i = fromX;
            for(int j = Math.min(fromY,toY)+1 ; j<Math.max(fromY,toY) ; j++){
                if(players[i][j]!=null)
                    return false;
            }
            return true;
        }
        if(Math.abs(fromY-toY)==0){
            i = fromY;
            for(int j = Math.min(fromX, toX)+1 ; j < Math.max(fromX, toX) ; j++){
                if(players[i][j]!=null)
                    return false;
            }
            return true;
        }
        return true;
    }

}
