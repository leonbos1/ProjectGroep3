import javafx.scene.control.Button;

public class BoardButton extends Button{

    public int x;
    public int y;

    public BoardButton(String s, int x, int y) {
        super(s);
        this.x = x;
        this.y = y;
    }

}