package Player;

import Main.Main;

/**
 * Created by amd on 3/15/15.
 */
public class King implements Player {
    private String color;
    private Pos pos;
    private boolean dead;
    public static final String value = "K";


    public King(String color, Pos pos){
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

        return (Math.abs(fromX - toX) == 1 )||( Math.abs(fromY - toY) == 1);
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



}
