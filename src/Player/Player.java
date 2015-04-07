package Player;


/**
 * Created by amd on 3/15/15.
 */
public interface Player {

    public String getColor();

    public int getXPos();

    public int getYPos();

    public Pos getPos();

    public void setPos(Pos toPos);

    public boolean isValidMove(Pos toPos , Player[][]players);

    public boolean isDead();

    public Player getPlayer(Pos pos);

    public void move(Pos start, Pos end);

    public String getValue();

    public void kill();
}
