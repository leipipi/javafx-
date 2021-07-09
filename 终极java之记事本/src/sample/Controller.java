package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import java.io.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.net.URL;
import javafx.beans.value.*;

public class Controller {


//    public VBox root;

    String textString ; // 获取记事本文本域的字符串
    String tfString;//替换功能使用的字符串
    private boolean save=false;//确定是否保存
    private String path=null;//文件路径
    int start_index=0;
    String search_string="";//用于记录当前查找的字符串
    double default_fontsize;//默认字体大小
    int reverse_find=1;//翻转查找标记
    String replace_string="";//替换字符串存放
    FileChooser fc;


    @FXML
    private VBox vbox;

    @FXML
    private MenuItem cut;

    @FXML
    private MenuItem switchto;

    @FXML
    private MenuItem savefile;

    @FXML
    private MenuItem replace;

    @FXML
    private MenuItem about;

    @FXML
    private MenuItem zoomdown;

    @FXML
    TextArea textarea;

    @FXML
    private MenuItem paste;

    @FXML
    private MenuItem pagesetup;

    @FXML
    private MenuItem delete;

    @FXML
    private Label showzoom;

    @FXML
    private MenuItem zoomup;

    @FXML
    private MenuItem defaultzoom;

    @FXML
    private MenuItem search;

    @FXML
    private CheckMenuItem word_warp;

    @FXML
    private Menu file;

    @FXML
    private MenuBar menubar1;

    @FXML
    private MenuItem copy;

    @FXML
    private MenuItem selectall;

    @FXML
    private MenuItem newfile;

    @FXML
    private MenuItem repeal;

    @FXML
    private Menu edit;

    @FXML
    private Menu format;

    @FXML
    private MenuItem searchnext;

    @FXML
    private Menu zoom;

    @FXML
    private Menu look;

    @FXML
    private MenuItem typeface;

    @FXML
    private MenuItem exit;

    @FXML
    private Menu help;

    @FXML
    private Label ranks;

    @FXML
    private MenuItem openfile;

    @FXML
    private MenuItem date_and_time;

    @FXML
    private CheckMenuItem statusbar;

    @FXML
    private MenuItem saveasfile;

    @FXML
    HBox StatusHbox;

    public void initialize()//初始化记事本
    {
        Main.controllers.put(this.getClass().getSimpleName(), this);
        textarea.setStyle(" -fx-background-insets: 0");

        default_fontsize=textarea.getFont().getSize();
        search.setDisable(true);
        searchnext.setDisable(true);
        switchto.setDisable(true);
        copy.setDisable(true);
        cut.setDisable(true);
        repeal.setDisable(true);
        delete.setDisable(true);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (((CheckMenuItem)e.getSource()).isSelected())
                {
                    textarea.wrapTextProperty().setValue(true);
                }
                else
                {
                    textarea.wrapTextProperty().setValue(false);
                }
            }
        };
        this.word_warp.setOnAction(event);
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {//状态栏是否可视事件
            public void handle(ActionEvent e2)
            {
                if (((CheckMenuItem)e2.getSource()).isSelected())
                {
                    StatusHbox.setVisible(true);
                }
                else
                {
                    StatusHbox.setVisible(false);
                }
            }
        };
        this.statusbar.setOnAction(event2);
        textarea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals(""))
                {
                    search.setDisable(true);
                    searchnext.setDisable(true);
                    switchto.setDisable(true);
                    copy.setDisable(true);
                    cut.setDisable(true);
                    repeal.setDisable(true);
                    delete.setDisable(true);
                }
                else if(oldValue.equals("")&&!newValue.equals(""))
                {
                    search.setDisable(false);
                    searchnext.setDisable(false);
                    switchto.setDisable(false);
                    copy.setDisable(false);
                    cut.setDisable(false);
                    repeal.setDisable(false);
                    delete.setDisable(false);
                }
            }
        });



    }
    @FXML
    void get_the_rank(MouseEvent event) {//获取行列函数
        int lie=textarea.getCaretPosition();
        int hang=textarea.getText(0,lie).split("\n").length;
        ranks.setText("第"+hang+"行,第"+lie+"列");
    }


    @FXML
    void New_File(ActionEvent event) {
        Stage stage=(Stage) vbox.getScene().getWindow();
        if (save) {  //如果内容已经保存了直接新建一个文件夹
            textarea.setText("");
            // 更改窗口标题
            stage.setTitle("新文件.txt");
            // 将开打的文件的路径设置为null
            path = null;
        } else if (!save && !textarea.getText().isEmpty()) { //文本域内有内容且文本没有保存时跳出提示
            Stage newStage = new Stage();
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(5,10,5,20));
            vBox.setSpacing(5);
            Label label1 = new Label("你还没有保存当前内容");
            Label label2 = new Label("按“确定”则新建一个文本，");
            Label label3= new Label("按“取消”则退出该窗口。");
            vBox.getChildren().addAll(label1,label2,label3);
            Button ok = new Button("确定");
            Button cancel = new Button("取消");
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20,10,5,80));
            hBox.setSpacing(30);
            hBox.getChildren().addAll(ok,cancel);
            BorderPane rootNode = new BorderPane();
            rootNode.setTop(vBox);
            rootNode.setCenter(hBox);

            Scene scene = new Scene(rootNode,300,150);
            newStage.setTitle("提示");
            newStage.setScene(scene);
            newStage.show();

            ok.setOnAction(event1->{
                // 清除文本框的内容
                textarea.setText("");
                // 更改窗口标题

                stage.setTitle("新文件.txt");
                // 将开打的文件的路径设置为null
                path = null;
                newStage.close();
            });

            cancel.setOnAction(event2->{
                newStage.close();
            });

        }
    }

    @FXML
    void Open_File(ActionEvent event) {
        if(save||textarea.getText().isEmpty()) {
            fc = new FileChooser(); // fileChooser打开一个目录
            fc.setTitle("打开"); // 设置文件选择框的标题
            // 过滤文件
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
            File file = fc.showOpenDialog(vbox.getScene().getWindow()); // 打开文件
            if (file != null && file.exists()) { // 文件存在
                try {
                    // 读取数据 放进多行文本框
                    //FileInputStream in =;// 用File对象创建文件输入流对象
                    BufferedReader BR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
                    String all = null;
                    String str = null;
                    while ((str = BR.readLine()) != null) {
                        all = all + str;
                    }
                    textarea.setText(all);
                    //in.close();
                    // 将文件路径 保留到成员变量path中
                    path = file.getPath();
                    // 更改窗口标题
                    int lastIndex = path.lastIndexOf("\\"); // 查找字符串最后一次出现的位置
                    String title = path.substring(lastIndex + 1); // 从下标lastIndex开始到结束产生一个新的字符串
                    //vbox.getScene().getWindow().s(title + "-我的记事本"); // 利用新的字符串重新设置窗口的标题
                    Stage stage = (Stage) vbox.getScene().getWindow();
                    stage.setTitle(title);
                } catch (Exception e) {
                }
            }
        }
            else if (!save && !textarea.getText().isEmpty()) { //文本域内有内容且文本没有保存时跳出提示
                Stage newStage = new Stage();
                VBox vBox = new VBox();
                vBox.setPadding(new Insets(5,10,5,20));
                vBox.setSpacing(5);
                Label label1 = new Label("你还没有保存当前内容");
                Label label2 = new Label("按“确定”则打开新文本，");
                Label label3= new Label("按“取消”则退出该窗口。");
                vBox.getChildren().addAll(label1,label2,label3);
                Button ok = new Button("确定");
                Button cancel = new Button("取消");
                HBox hBox = new HBox();
                hBox.setPadding(new Insets(20,10,5,80));
                hBox.setSpacing(30);
                hBox.getChildren().addAll(ok,cancel);
                BorderPane rootNode = new BorderPane();
                rootNode.setTop(vBox);
                rootNode.setCenter(hBox);

                Scene scene = new Scene(rootNode,300,150);
                newStage.setTitle("提示");
                newStage.setScene(scene);
                newStage.show();

                ok.setOnAction(event1->{
                    // 清除文本框的内容
                    fc = new FileChooser(); // fileChooser打开一个目录
                    fc.setTitle("打开"); // 设置文件选择框的标题
                    // 过滤文件
                    fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
                    File file2 = fc.showOpenDialog(vbox.getScene().getWindow()); // 打开文件
                    if (file2 != null && file2.exists()) { // 文件存在
                        try {
                            // 读取数据 放进多行文本框
                            BufferedReader BR=new BufferedReader(new InputStreamReader( new FileInputStream(file2), "gbk"));
                            // 将内容设置到多行文本框
                            String all=null;
                            String str = null;
                            while((str = BR.readLine()) != null)
                            {
                                all=all+str;
                            }
                            textarea.setText(all);
                            //in.close();
                            // 将文件路径 保留到成员变量path中
                            path = file2.getPath();
                            // 更改窗口标题
                            int lastIndex = path.lastIndexOf("\\"); // 查找字符串最后一次出现的位置
                            String title = path.substring(lastIndex + 1); // 从下标lastIndex开始到结束产生一个新的字符串
                            //vbox.getScene().getWindow().s(title + "-我的记事本"); // 利用新的字符串重新设置窗口的标题
                            Stage stage=(Stage) vbox.getScene().getWindow();
                            stage.setTitle(title);
                        } catch (Exception e) {
                        }
                }



            });
            cancel.setOnAction(event2->{
                newStage.close();
            });
        }

    }

    @FXML
    void Save_FIle(ActionEvent event) {
        // 需要判断是新建之后的保存 还是打开之后的保存
        // 新建之后的保存
        if (path == null) {
            FileChooser fc = new FileChooser();
            fc.setTitle("保存");
            // 获取被用户选择的文件
            File file = new File(String.valueOf(fc.showSaveDialog(vbox.getScene().getWindow()))+".txt");

            // 如果用户选了 而且文件是存在的
            if (file != null && !file.exists()) {
                // 将多行文本框中的内容写到file指向的文件中去
                try {
                    // 创建输出流
                    FileOutputStream out = new FileOutputStream(file);
                    out.write(textarea.getText().getBytes());
                    out.flush();
                    out.close();
                    save = true; // 已经保存了
                    path= String.valueOf(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {// 打开之后的保存
            try {
                // 创建输出流
                FileOutputStream out = new FileOutputStream(path);
                out.write(textarea.getText().getBytes());
                out.flush();
                out.close();
                save = true;// 已经保存了
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Save_As_File(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("另存为");
        File file = new File(String.valueOf(fc.showSaveDialog(vbox.getScene().getWindow()))+".txt");

        if (file != null) {
            try {
                FileOutputStream out = new FileOutputStream(file);
                String str = textarea.getText();
                byte[] bs = str.getBytes();// 字符串转换成字节数组
                out.write(bs);
                out.flush();
                out.close();
                save = true;
                path=String.valueOf(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void Page_Set(ActionEvent event) throws Exception{
        Stage stage =new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample3.fxml"));
        Pane rootLayout = (Pane) loader.load();
        Scene scene = new Scene(rootLayout);
        stage.setTitle("页面设置");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Exit(ActionEvent event) {
        Stage stage=(Stage) vbox.getScene().getWindow();
        if (save||textarea.getText().isEmpty()) {
            stage.close();
        } else if (!save && !textarea.getText().isEmpty()) {
            Stage newStage = new Stage();
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(5,10,5,20));
            vBox.setSpacing(5);
            Label label1 = new Label("你还没有保存当前内容");
            vBox.getChildren().addAll(label1);
            Button ok = new Button("直接退出");
            Button cancel = new Button("取消");
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20,10,5,80));
            hBox.setSpacing(30);
            hBox.getChildren().addAll(ok,cancel);
            BorderPane rootNode = new BorderPane();
            rootNode.setTop(vBox);
            rootNode.setCenter(hBox);

            Scene scene = new Scene(rootNode,300,150);
            newStage.setTitle("提示");
            newStage.setScene(scene);
            newStage.show();

            ok.setOnAction(event1->{
                // 清除文本框的内容
                newStage.close();
               stage.close();
            });

            cancel.setOnAction(event2->{
                newStage.close();
            });
        }

    }

    @FXML
    void Repeal(ActionEvent event) {
        textarea.undo();
    }

    @FXML
    void Cut(ActionEvent event) {
        textarea.cut();
    }

    @FXML
    void Copy(ActionEvent event) {
        textarea.copy();
    }
    @FXML
    void huifu(ActionEvent event) {
        textarea.redo();
    }

    @FXML
    void Paste(ActionEvent event) {
        textarea.paste();
    }

    @FXML
    void Delete(ActionEvent event) {
        textarea.deleteNextChar();
    }

    @FXML
    void Search(ActionEvent event) {
        textString="";
        ArrayList<Integer> have_find=new ArrayList<>();

        HBox h1 = new HBox();
        h1.setPadding(new Insets(20, 5, 20, 5));
        h1.setSpacing(5);
        Label lable1 = new Label("查找内容(N):");
        TextField tf1 = new TextField();
        h1.getChildren().addAll(lable1, tf1);

        VBox v1 = new VBox();
        v1.setPadding(new Insets(20, 5, 20, 10));
        Button btn1 = new Button("查找下一个(F)");
        v1.getChildren().add(btn1);
        CheckBox cb=new CheckBox();
        cb.setPadding(new Insets(20, 5, 20, 15));
        cb.setText("区分大小写");
        CheckBox cb2=new CheckBox();
        cb2.setPadding(new Insets(20, 5, 20, 25));
        cb2.setText("循环");
        CheckBox cb3=new CheckBox();
        cb3.setPadding(new Insets(20, 5, 20, 30));
        cb3.setText("反向查找");

        HBox findRootNode = new HBox();
        findRootNode.getChildren().addAll(h1, v1,cb,cb2,cb3);

        Stage findStage = new Stage();
        Scene scene1 = new Scene(findRootNode, 650, 90);
        findStage.setTitle("查找");
        findStage.setScene(scene1);
        findStage.setResizable(false); // 固定窗口大小
        findStage.show();
        textString=textarea.getText().toLowerCase();
        // 获取要查找的字符串
        cb.setOnAction((actionEvent -> {
            if(!cb.isSelected())
            {
                textString=textString.toLowerCase();
                search_string=search_string.toLowerCase();
            }
            else
            {
                textString=textarea.getText();
                search_string = tf1.getText();
            }
        }));
        btn1.setOnAction((ActionEvent e) -> {
            if(!search_string.equals(tf1.getText().toLowerCase()))
            {
                search_string = tf1.getText().toLowerCase();
                start_index=0;
            }
            if (!tf1.getText().isEmpty()) {
                if (textString.contains(search_string)) {
                    // 查找方法
                    if (start_index == -1|| start_index>=textString.length()) {// not found
                        if(!cb2.isSelected())
                        {
                            Alert alert1 = new Alert(AlertType.WARNING);
                            alert1.titleProperty().set("提示");
                            alert1.headerTextProperty().set("已经找不到相关内容了。");
                            alert1.show();
                        }
                        else
                        {
                            start_index=0;
                        }
                    }
                    //根据标记确定是正向查找还是反向查找，然后进行相应查询
                    //将查询到的结果存入列表，以便“查找下一个”按钮可以正常
                    start_index = textString.indexOf(tf1.getText(),start_index);
                    if(!have_find.contains(start_index))
                       have_find.add(start_index);
                    if (start_index >= 0 && start_index < textarea.getText().length()&&!cb3.isSelected()) {
                        reverse_find=1;
                        textarea.selectRange(start_index, start_index+tf1.getText().length());
                        start_index += tf1.getText().length();
                    }
                    else if(start_index >= 0 && start_index < textarea.getText().length()&&cb3.isSelected())
                    {
                        start_index=have_find.get(have_find.size()-reverse_find-2);
                        System.out.println(start_index);
                        reverse_find=reverse_find+1;
                        textarea.selectRange(start_index, start_index+tf1.getText().length());
                        start_index += tf1.getText().length();
                    }
                }
                if (!textString.contains(search_string)) {
                    Alert alert1 = new Alert(AlertType.WARNING);
                    alert1.titleProperty().set("提示");
                    alert1.headerTextProperty().set("找不到相关内容了。");
                    alert1.show();
                }
            } else if (tf1.getText().isEmpty()) {
                Alert alert1 = new Alert(AlertType.WARNING);
                alert1.titleProperty().set("错误");
                alert1.headerTextProperty().set("输入内容不能为空");
                alert1.show();
            }
        });
    }

    @FXML
    void Search_Next(ActionEvent event) {
        if (!search_string.equals("")) {
            if (textString.contains(search_string)) {
                // 查找方法
                if (start_index == -1) {// not found
                    Alert alert1 = new Alert(AlertType.WARNING);
                    alert1.titleProperty().set("提示");
                    alert1.headerTextProperty().set("已经找不到相关内容了。");
                    alert1.show();
                }
                start_index = textString.indexOf(search_string,start_index);
                if (start_index >= 0 && start_index < textarea.getText().length()) {
                    textarea.selectRange(start_index, start_index+search_string.length());
                    start_index += search_string.length();
                }
            }
            if (!textString.contains(search_string)) {
                Alert alert1 = new Alert(AlertType.WARNING);
                alert1.titleProperty().set("提示");
                alert1.headerTextProperty().set("找不到相关内容了。");
                alert1.show();
            }
        } else {
            Alert alert1 = new Alert(AlertType.WARNING);
            alert1.titleProperty().set("错误");
            alert1.headerTextProperty().set("输入内容不能为空");
            alert1.show();
        }


    }

    @FXML
    void Replace(ActionEvent event) {
        textString="";
        replace_string="";
        tfString="";
        HBox h1 = new HBox();
        h1.setPadding(new Insets(20, 5, 10, 8));
        h1.setSpacing(5);
        Label label1 = new Label("查找下一个(F)");
        TextField tf1 = new TextField();
        h1.getChildren().addAll(label1, tf1);

        HBox h2 = new HBox();
        h2.setPadding(new Insets(5, 5, 20, 8));
        h2.setSpacing(5);
        Label label2 = new Label("替换内容(N):");
        TextField tf2 = new TextField();
        h2.getChildren().addAll(label2, tf2);

        CheckBox cb=new CheckBox("区分大小写");
        cb.setPadding(new Insets(5, 5, 30, 8));

        VBox v1 = new VBox();
        v1.getChildren().addAll(h1, h2, cb);

        VBox v2 = new VBox();
        v2.setPadding(new Insets(21, 5, 20, 10));
        v2.setSpacing(13);
        Button btn1 = new Button("查找下一个");
        Button btn2 = new Button("替换为");
        Button btn3=new Button("全部替换");
        Button btn4=new Button("取消");
        v2.getChildren().addAll(btn1, btn2,btn3,btn4);

        HBox replaceRootNode = new HBox();
        replaceRootNode.getChildren().addAll(v1, v2);

        Stage replaceStage = new Stage();
        Scene scene = new Scene(replaceRootNode, 380, 160);
        replaceStage.setTitle("替换");
        replaceStage.setScene(scene);
        replaceStage.setResizable(false); // 固定窗口大小
        replaceStage.show();


        cb.setOnAction((actionEvent -> {
            if(!cb.isSelected())
            {
                start_index=0;
                textString=textarea.getText();
                tfString=tf1.getText();
                replace_string = tf1.getText();
                textString=textString.toLowerCase();
                tfString=tfString.toLowerCase();
                replace_string.toLowerCase();
            }
            else
            {
                start_index=0;
                textString=textarea.getText();
                tfString=tf1.getText();
                replace_string = tf1.getText();
            }
        }));
        cb.setSelected(true);
        btn1.setOnAction((ActionEvent e) -> {
            if(cb.isSelected())
            {
                textString=textarea.getText();
                tfString = tf1.getText(); // 获取查找内容的字符串
            }
            else
            {
                textString=textarea.getText().toLowerCase();
                tfString = tf1.getText().toLowerCase(); // 获取查找内容的字符串
            }
            if(!replace_string.equals(tfString))
            {
                replace_string=tfString;
                start_index=0;
            }
            if (!tf1.getText().isEmpty()) {
                if (textString.contains(tfString)) {
                    if (start_index == -1) {// not found
                        Alert alert1 = new Alert(AlertType.WARNING);
                        alert1.titleProperty().set("提示");
                        alert1.headerTextProperty().set("已经找不到相关内容了！！！");
                        alert1.show();
                    }
                    start_index = textString.indexOf(tf1.getText(),start_index);
                    if (start_index >= 0 && start_index < textarea.getText().length()) {
                        textarea.selectRange(start_index, start_index+tf1.getText().length());
                        start_index += tf1.getText().length();
                    }
                    btn2.setOnAction((ActionEvent e2) -> {
                        if(tf2.getText().isEmpty()) {  //替换内容为空时
                            Alert alert1 = new Alert(AlertType.WARNING);
                            alert1.titleProperty().set("出错了");
                            alert1.headerTextProperty().set("替换内容为空");
                            alert1.show();
                        }else {     //替换内容不为空则替换
                            textarea.replaceSelection(tf2.getText());
                        }

                    });
                }
                if (!textString.contains(tfString)) {
                    Alert alert1 = new Alert(AlertType.WARNING);
                    alert1.titleProperty().set("提示");
                    alert1.headerTextProperty().set("找不到相关内容了！！！");
                    alert1.show();
                }

            } else if (tf1.getText().isEmpty()) {
                Alert alert1 = new Alert(AlertType.WARNING);
                alert1.titleProperty().set("出错了");
                alert1.headerTextProperty().set("输入内容为空");
                alert1.show();
            }
        });
        btn3.setOnAction((ActionEvent e) -> {
            start_index=0;
            if(cb.isSelected())
            {
                textString=textarea.getText();
                tfString = tf1.getText(); // 获取查找内容的字符串
            }
            else
            {
                textString=textarea.getText().toLowerCase();
                tfString = tf1.getText().toLowerCase(); // 获取查找内容的字符串
            }
            if(!replace_string.equals(tfString))
            {
                replace_string=tfString;
                start_index=0;
            }
            if (!tf1.getText().isEmpty()) {
                if (textString.contains(tfString)) {
                    if (start_index == -1) {// not found
                        Alert alert1 = new Alert(AlertType.WARNING);
                        alert1.titleProperty().set("提示");
                        alert1.headerTextProperty().set("已经找不到相关内容了！！！");
                        alert1.show();
                    }
                    textString=textString.replace(tfString,tf2.getText());
                    textarea.setText(textString);
                }
            }
            else if (tf1.getText().isEmpty()) {
                Alert alert1 = new Alert(AlertType.WARNING);
                alert1.titleProperty().set("出错了");
                alert1.headerTextProperty().set("输入内容为空");
                alert1.show();
            }
        });
        btn4.setOnAction((ActionEvent e) -> {
            start_index=0;
            replaceStage.close();
        });

    }

    @FXML
    void Switch_To(ActionEvent event) {
        HBox findRootNode = new HBox();
        findRootNode.setPadding(new Insets(20, 5, 10, 8));
        Label l=new Label();
        l.setText("行数：");
        TextField tf=new TextField();
        Button bt=new Button();
        bt.setText("确定");
        findRootNode.setSpacing(10);
        findRootNode.getChildren().addAll(l,tf,bt);

        Stage findStage = new Stage();
        Scene scene1 = new Scene(findRootNode, 300, 60);
        findStage.setTitle("转到指定行");
        findStage.setScene(scene1);
        findStage.setResizable(false); // 固定窗口大小
        findStage.show();
        bt.setOnAction((ActionEvent e) -> {
            int hang=Integer.parseInt(tf.getText());
            String[] alltext=textarea.getText().split("\n");
            int caret=0;
            if(hang!=1)
            {
                for(int i=0;i<hang-1;i++)
                {
                    caret=caret+alltext[i].length();
                }
            }
            textarea.positionCaret(caret+hang-1);
            findStage.close();
        });

    }

    @FXML
    void Select_All(ActionEvent event) {
        textarea.selectAll();
    }

    @FXML
    void Date_And_Time(ActionEvent event) {
        Date now_date=new Date();
        textarea.appendText(String.valueOf(now_date));
    }

    @FXML
    void Word_Warp(ActionEvent event) {//已经实现

   }

    //用到sample2界面，加载进来
    @FXML
    void Type_Face(ActionEvent event) throws Exception{
       Stage stage =new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample2.fxml"));
        Pane rootLayout = (Pane) loader.load();

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Zoom_Up(ActionEvent event) {
        Font font=textarea.getFont();
        font=Font.font(font.getFamily(),font.getSize()+10);
        textarea.setFont(font);
    }

    @FXML
    void Zoom_Down(ActionEvent event) {
        Font font=textarea.getFont();
        font=Font.font(font.getFamily(),font.getSize()-10);
        textarea.setFont(font);

    }

    @FXML
    void Default_Zoom(ActionEvent event) {
        Font font=textarea.getFont();
        font=Font.font(font.getFamily(),default_fontsize);
        textarea.setFont(font);
    }

    @FXML
    void Status_Bar(ActionEvent event) {//已经实现

    }

    @FXML
    void About(ActionEvent event) {
        Alert information = new Alert(Alert.AlertType.INFORMATION,"leipipi学习javafx制作");
        information.setTitle("关于");
        information.setHeaderText("关于此记事本");
        information.show();
    }

}
