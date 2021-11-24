import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Jens Maas & Sophie Broersma
 *
 * This class handles the input of the buttons on the board
 */
// public class InputHandler implements EventHandler<ActionEvent> {

//    @Override
//    public void handle(ActionEvent e) {
//        BoardButton button = (BoardButton) e.getSource();
//
//        int parsedMove = TicTacToe.getInstance().makeMove(button.x,button.y);
//
//        CommandManager.getInstance().AddCommand(new MoveCommand(parsedMove,
//                GameManager.getInstance().getGame().getCurrentPlayer().userType,
//                GameManager.getInstance().getGame().getOtherPlayer().userType
//        ));
//
//    }
// }
