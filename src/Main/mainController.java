package Main;
import game.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import calculator.*;
import javax.sound.midi.Soundbank;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/*
* 控制界面
* */
public class mainController  implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView game;

    @FXML
    private Button jia;

    @FXML
    private TextField calculate_target;

    @FXML
    private Button body_to_calculate;

    @FXML
    private ChoiceBox<String> choose;

    @FXML
    private AnchorPane body;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button calculate_btn;

    @FXML
    private AnchorPane head;

    @FXML
    private Label calculator_title1;

    @FXML
    private Button four;

    @FXML
    private Button start_game;

    @FXML
    private ImageView close;

    @FXML
    private Button jian;

    @FXML
    private Button chen;

    @FXML
    private ImageView calculator;

    @FXML
    private TextArea calculate_textarea;

    @FXML
    private AnchorPane calculator_body;

    @FXML
    private Button one;

    @FXML
    private Button calculate_btn1;

    @FXML
    private AnchorPane game_body;

    @FXML
    private Button exit_game;

    @FXML
    private Button clear_btn;

    @FXML
    private ImageView home;

    @FXML
    private Button chu;

    @FXML
    private Button body_to_game;

    @FXML
    private TextField calculate_input;

    @FXML
    private AnchorPane window;

    @FXML
    private Label calculator_title;

    @FXML
    private Label game_over_lable;


    @Override
    //初始化程序
    public void initialize(URL url, ResourceBundle resourceBundle) {
        body.setVisible(true);
        game_body.setVisible(false);
        calculator_body.setVisible(false);
        moveWindow();
        icoTouch(home,calculator,close,game);
        icoClick();
        home_btn_do();
        gameDo();
    }
    //获取stage
    private Stage getStage() {//获取stage
        if (stage == null) {
            stage = (Stage) window.getScene().getWindow();//通过id来找到stage
        }
        return stage;
    }
    //获取scene
    private Scene get_Scene(){
        if(scene==null){
            scene=(Scene)window.getScene();
        }
        return scene;
    }
    //移动窗口
    private void moveWindow(){
        final double[] eventX = {0};// 鼠标点击位置
        final double[] eventY = {0};
        final double[] stageX = new double[1];//窗体位置
        final double[] stageY = new double[1];
        head.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 stage=getStage();
                 stageX[0] =stage.getX();//获取窗口坐标x
                 stageY[0] =stage.getY();//获取窗口坐标Y
                 eventX[0] =event.getScreenX();//获取鼠标点击的坐标x
                 eventY[0] =event.getScreenY();//获取鼠标点击的坐标y
            }
        });
        head.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage=getStage();
                double x1=event.getScreenX();//鼠标松开位置x
                double y1=event.getScreenY();//鼠标松开位置y
                //窗体现在坐标=鼠标松开坐标+窗体原来坐标-鼠标点击坐标
                stage.setX(x1+ stageX[0] -eventX[0]);
                stage.setY(y1+ stageY[0] -eventY[0]);
            }
        });
}
    //批量处理菜单栏imageview接触事件
    private void icoTouch(ImageView... image) {
        for(ImageView im:image){
            toBeTouch(im);
            leaveTouch(im);
        }
    }
    //监听菜单栏的imageview，触碰变成手
    private void toBeTouch(ImageView image){
        image.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.HAND);
            }
        });
    }
    //离开变成鼠标
    private void leaveTouch(ImageView image){
        image.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }
    //icons点击事件
    private void icoClick(){
            //close的鼠标点击事件
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage=getStage();
                    stage.close();

                }
            });
            //game点击事件
            game.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    game_body.setVisible(true);
                    calculator_body.setVisible(false);
                    body.setVisible(false);

                }
            });
            //calculate点击时间
            calculator.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    game_body.setVisible(false);
                    calculator_body.setVisible(true);
                    body.setVisible(false);
                }
            });
            //home点击事件
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                game_body.setVisible(false);
                calculator_body.setVisible(false);
                body.setVisible(true);
            }
        });

    }
    //计算器事件
    @FXML//计算所有结果
    void calculate_click(ActionEvent event) {
        calculate_textarea.clear();
        //获取目标值
        int target;
        int[] nums;
        if(calculate_target.getText()==null||calculate_target.getText().isEmpty()){
            target=24;
        }else {
            target=Integer.valueOf(calculate_target.getText());
        }

        System.out.println(target);
        //得到输入数字
        if(calculate_input.getText()==null||calculate_input.getText().isEmpty()){
                calculate_textarea.setText("Please Enter values");
        }else {
            String string=calculate_input.getText();
            String[] t=string.split(" ");
            nums=new int[t.length];
            for(int i=0;i<t.length;i++){
                nums[i]=Integer.valueOf(t[i]);
            }
            //获取结果
            commonSolve cs=new commonSolve();
            HashMap<int[],int[]> dic=cs.commonSolve(nums,target,1);
            calculator cl=new calculator();
            String[] out=cl.print(dic,target);
            //排序显示
            if(out.length==0){
                calculate_textarea.appendText("No result");
            }else {
                Arrays.sort(out,new sortResults());
                calculate_textarea.setWrapText(true);
                calculate_textarea.setScrollTop(Double.MAX_VALUE);

                for (int i=0;i<out.length;i++){
                    calculate_textarea.appendText(out[i]+"\n");
                }
            }

        }
    }
    @FXML//计算一个结果
    void calculate_click1(ActionEvent event){
        calculate_textarea.clear();
//获取目标值
        int target;
        int[] nums;
        if(calculate_target.getText()==null||calculate_target.getText().isEmpty()){
            target=24;
        }else {
            target=Integer.valueOf(calculate_target.getText());
        }

        System.out.println(target);
        //得到输入数字
        if(calculate_input.getText()==null||calculate_input.getText().isEmpty()){
            calculate_textarea.setText("Please Enter values");
        }else {
            String string=calculate_input.getText();
            String[] t=string.split(" ");
            nums=new int[t.length];
            for(int i=0;i<t.length;i++){
                nums[i]=Integer.valueOf(t[i]);
            }
            //获取结果
            commonSolve cs=new commonSolve();
            HashMap<int[],int[]> dic=cs.commonSolve(nums,target,0);
            calculator cl=new calculator();
            String[] out=cl.print(dic,target);
            //排序显示
            if(out.length==0){
                calculate_textarea.appendText("No result");
            }else {
                Arrays.sort(out,new sortResults());
                calculate_textarea.setWrapText(true);
                calculate_textarea.setScrollTop(Double.MAX_VALUE);

                for (int i=0;i<out.length;i++){
                    calculate_textarea.appendText(out[i]+"\n");
                }
            }

        }
    }
    @FXML//清空内容
    void calculate_clear(ActionEvent event) {
        calculate_textarea.clear();
        calculate_input.clear();
        calculate_target.clear();
    }
    //主菜单事件
    public  void home_btn_do(){
        //
        body_to_game.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.HAND);
            }
        });
        body_to_game.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                game_body.setVisible(true);
                calculator_body.setVisible(false);
                body.setVisible(false);
            }
        });
        body_to_game.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        body_to_calculate.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.HAND);
            }
        });
        body_to_calculate.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene=get_Scene();
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        body_to_calculate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                game_body.setVisible(false);
                calculator_body.setVisible(true);
                body.setVisible(false);
            }
        });
    }
    //游戏窗口事件
    public void gameDo(){
    game gm=new game();
    final int[] level = {0};//简单[0] 中等[1] 困难[2]
        //这个choice box写在game包里会报错不知道为什么
        choose.getItems().addAll("简单模式","中等模式","困难模式");
        choose.getSelectionModel().selectFirst();//默认选中第一个选项
        choose.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                level[0] =newValue.intValue();
                gm.level=level[0];
                System.out.println(level[0]);
            }
        });
       //游戏窗口退出按钮
        exit_game.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                game_body.setVisible(false);
                calculator_body.setVisible(false);
                body.setVisible(true);
            }
        });

    gm.initialize(one,two,three,four,game_over_lable,start_game,exit_game,jia,jian,chen,chu,choose);
    gm.toBeHead(window);

    }




}
