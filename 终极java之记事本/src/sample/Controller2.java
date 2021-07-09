package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class Controller2 {

    Font font;
    @FXML
    private Pane pane2;

    @FXML
    private TextField ziti_tf;

    @FXML
    private TextField daxiao_tf;

    @FXML
    private TextField tixing_tf;

    @FXML
    private ListView<String> ziti_lv;

    @FXML
    private ListView<String> daxiao_lv;

    @FXML
    private ListView<String> zixing_lv;

    @FXML
     TextArea font_tf;

    //设置字体字形大小，javafx中文字体似乎没有斜体，但有加粗
    public void initialize()
    {

        ObservableList<String> strList = FXCollections.observableArrayList("仿宋","黑体","新宋体","楷体","微软雅黑体");
        //ListView<String> listView = new ListView<>(strList);
        ziti_lv.setItems(strList);
        ObservableList<String> strList2 = FXCollections.observableArrayList("常规","粗体","倾斜","粗体倾斜");
        zixing_lv.setItems(strList2);
        ObservableList<String> strList3 = FXCollections.observableArrayList("一号","二号","三号","四号","五号","六号");
        daxiao_lv.setItems(strList3);
        ziti_lv.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                   if(newValue.equals("仿宋"))
                   {
                       ziti_tf.setText("仿宋");
                        font= Font.font("FangSong",20);
                        font_tf.setFont(font);
                   }
                   else if(newValue.equals("黑体"))
                   {
                       ziti_tf.setText("黑体");
                        font=Font.font("SimHei",20);
                       font_tf.setFont(font);
                   }
                   else if(newValue.equals("新宋体"))
                   {
                       ziti_tf.setText("新宋体");
                       font=Font.font("NSimSun",20);
                       font_tf.setFont(font);
                   }
                   else if(newValue.equals("楷体"))
                   {
                       ziti_tf.setText("楷体");
                       font=Font.font("KaiTi",20);
                       font_tf.setFont(font);
                   }
                   else if(newValue.equals("微软雅黑体"))
                   {
                       ziti_tf.setText("微软雅黑体");
                       font=Font.font("Microsoft YaHei",20);
                       font_tf.setFont(font);
                   }
                });
        zixing_lv.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    if(newValue.equals("常规"))
                    {
                            tixing_tf.setText("常规");
                            font_tf.setFont(font);
                    }
                    else if(newValue.equals("粗体"))
                    {
                        tixing_tf.setText("粗体");
                        font=Font.font(font.getName(),FontWeight.BOLD,20);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("倾斜"))
                    {
                        tixing_tf.setText("倾斜");
                        font=Font.font(font.getName(), FontWeight.NORMAL,FontPosture.ITALIC,20);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("粗体倾斜"))
                    {
                        tixing_tf.setText("粗体倾斜");
                        font=Font.font(font.getName(), FontWeight.BOLD ,FontPosture.ITALIC,20);
                        font_tf.setFont(font);
                    }
                });
        daxiao_lv.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    if(newValue.equals("一号"))
                    {
                        daxiao_tf.setText("一号");
                        font=Font.font(26);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("二号"))
                    {
                        daxiao_tf.setText("二号");
                        font=Font.font(22);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("三号"))
                    {
                        daxiao_tf.setText("三号");
                        font=Font.font(16);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("四号"))
                    {
                        daxiao_tf.setText("四号");
                        font=Font.font(14);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("五号"))
                    {
                        daxiao_tf.setText("五号");
                        font=Font.font(10.5);
                        font_tf.setFont(font);
                    }
                    else if(newValue.equals("六号"))
                    {
                        daxiao_tf.setText("六号");
                        font=Font.font(7.5);
                        font_tf.setFont(font);
                    }
                });

    }
    @FXML
    void queding_bt(ActionEvent event) throws Exception{

        Controller controller = (Controller) Main.controllers.get(Controller.class.getSimpleName());
        controller.textarea.setFont(font);
        Stage s=(Stage)pane2.getScene().getWindow();
        s.close();

    }

    @FXML
    void quxiao_bt(ActionEvent event) {
        font=Font.getDefault();
        Stage s=(Stage)pane2.getScene().getWindow();
        s.close();
    }

    Font getFont()
    {
        return font;
    }
}
