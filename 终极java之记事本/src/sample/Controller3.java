package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller3 {

    @FXML
    private ToggleGroup fangxiang;

    @FXML
    private Pane pane3;

    @FXML
    private ChoiceBox<String> daxiao;

    public void initialize()
    {
        daxiao.getItems().addAll("A4", "A5", "A6","B4");
        daxiao.setValue("A4");
    }


    @FXML
    void queding(ActionEvent event) {
        Stage s=(Stage) pane3.getScene().getWindow();
        s.close();
    }

    @FXML
    void quxiao(ActionEvent event) {
            Stage s=(Stage) pane3.getScene().getWindow();
            s.close();
    }

}
