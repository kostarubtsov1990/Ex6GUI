package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by kostarubtsov1990 on 17/01/18.
 */
public class WinnerStageController {

    @FXML
    Text text;
    @FXML
    Button button;
    @FXML
    protected void closeWindowHandler(){
        // get a handle to the stage
        Stage stage = (Stage) button.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void SetWinnerMessegeText(String winner) {
        text.setText(winner);
    }



}
