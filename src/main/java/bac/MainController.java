package bac;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    public Spinner<Integer> spinner1;
    public Spinner<Integer> spinner2;
    public Spinner<Integer> spinner3;
    public Spinner<Integer> spinner4;
    public TableView<Turn> history;
    public List<Integer> myNumber;

    public void doTurn() {
        var nr1 = spinner1.getValue();
        var nr2 = spinner2.getValue();
        var nr3 = spinner3.getValue();
        var nr4 = spinner4.getValue();
        var guess = "" + nr1 + nr2 + nr3 + nr4;

        var guessList = List.of(nr1, nr2, nr3, nr4);

        var bulls = 0;
        var cows = 0;
        for (int i = 0; i < 4; i++) {
            int myNum = myNumber.get(i);
            for (int j = 0; j < 4; j++) {
                int guessNum = guessList.get(j);
                if (myNum == guessNum) {
                   if (j == i) {
                       bulls++;
                   } else {
                       cows++;
                   }
                }
            }
        }

        var turn = new Turn();
        history.getItems().add(turn);
        turn.setGuess(guess);
        turn.setNr(history.getItems().size());
        turn.setBulls(bulls);
        turn.setCows(cows);

        if (bulls == 4) {
            var winnerInfo = new Alert(Alert.AlertType.INFORMATION, "You are the WINNER!");
            winnerInfo.setTitle("You are the Winner");
            winnerInfo.setHeaderText("Congratulation!");
            winnerInfo.showAndWait();
            resetGame();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetGame();
    }

    private void resetGame() {
        history.getItems().clear();
        var nums = new TreeSet<Integer>();
        var rand = new Random();
        while (nums.size() < 4) {
            var num = rand.nextInt(10);
            System.out.println(num);
            nums.add(num);
        }
        myNumber = new ArrayList<>(nums);
        System.out.println(myNumber);
    }
}
