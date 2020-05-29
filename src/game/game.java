package game;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class game {
    Scene scene;
    Button one;Button two;Button three;Button four;
    ChoiceBox<String> choose;Button start;Button exit;
    Button jia;Button jian;Button chen;Button chu;
    public int level;int[][] datas;int[] using_data;Label game_over_lable;

    public game(){}

    public void  initialize(Button one, Button two, Button three,
                            Button four, Label game_over_lable, Button start,
                            Button exit, Button jia, Button jian, Button chen, Button chu,ChoiceBox<String> choose){
        this.one=one;this.two=two;this.three=three;this.four=four;this.start=start;this.game_over_lable=game_over_lable;
        this.exit=exit;this.jia=jia;this.jian=jian;this.chen=chen;this.chu=chu;this.choose=choose;
        //颜色
        one.setStyle("-fx-background-color: #7CCD7C");
        two.setStyle("-fx-background-color: #7CCD7C");
        three.setStyle("-fx-background-color: #7CCD7C");
        four.setStyle("-fx-background-color: #7CCD7C");

        //获取数据 //结果筛选1~5，6~12，13~24
        calculate cl=new calculate();
        this.datas=cl.calculate_do();
        getData();
        //按钮数据清空
        one.setText("点");
        two.setText("击");
        three.setText("开");
        four.setText("始");
        start_click();
        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        four.setVisible(true);
        //标签清空
        game_over_lable.setText("");
        //四个数字按钮
        fourNumbersDo();
    }

    //四个数字按钮事件+四个符号按钮事件
    public void fourNumbersDo(){
        int[] flag={0,0};//未被点击0，点击一次1，点击符号按钮2，点击俩次0;第二个参数是哪一个按钮 1 2 3 4
        final int[] count = {0};//记录游戏是否结束
        final int[] sign = {-1};//记录符号
        //四个数字按钮
        Button[] btns={one,two,three,four};
        final int[] sum = {0};
       for(int i=0;i<4;i++){
           int finalI = i;
           btns[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   if(flag[0]==0){
                       try{
                           Integer.valueOf(btns[finalI].getText());
                           flag[1]= finalI;//an niu 0 1 2 3
                           flag[0]=1;//有一个被点击
                           btns[finalI].setStyle("-fx-background-color: #7CFC00");

                       }catch (Exception e){
                           System.out.println("还没开始");
                       }

                   }else if(flag[0]==2){
                       sum[0]=using_data[flag[1]]+using_data[finalI];
                       sum[0]=calculate_do(using_data[flag[1]],using_data[finalI],sign[0]);
                       if(sum[0]==-99){//非整除
                           gameOver(sum[0]);
                           count[0]=0;
                       }
                       using_data[finalI]=sum[0];
                       btns[finalI].setText(String.valueOf(sum[0]));
                       btns[flag[1]].setVisible(false);
                       flag[1]=finalI;
                       flag[0]=0;
                       count[0]++;
                       if(count[0]==3){
                           gameOver(sum[0]);
                           count[0]=0;
                       }
                   }

               }
           });
       }
       //四个符号按钮
        Button[] signs={jia,jian,chen,chu};
       for(int i=0;i<4;i++){
           int finalI = i;
           signs[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   if(flag[0]==1){//只有在第一次点击后生效
                       sign[0] = finalI;
                       flag[0]=2;
                   }
               }
           });
       }

    }
    //+-*/运算
    public int calculate_do(int a,int b,int sign){
        if(sign==0){
            return a+b;
        }
        if(sign==1){
            return a-b;
        }
        if(sign==2){
            return a*b;
        }
        if(sign==3){
            if(a%b!=0){
                return -99;
            }
        }
        return a/b;
    }
    public void gameOver(int num){
        if(num==-99){
            game_over_lable.setText("只接受整除");
            game_over_lable.setTextFill(Color.RED);
        }else if(num==24){
            game_over_lable.setText("结果正确");
            game_over_lable.setTextFill(Color.GREEN);
        }else {
            game_over_lable.setText("结果错误");
            game_over_lable.setTextFill(Color.RED);
        }
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        start.setText("继续");

    }
    //按钮触碰变小手事件,要把window传进来
    public void toBeHead(AnchorPane window){
        Button[] btns={one,two,three,four,start,exit,jia,jian,chen,chu};
        for(Button btn:btns){
            //变手
            btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    scene=get_Scene(window);
                    scene.setCursor(Cursor.HAND);
                }
            });
            //变鼠标
            btn.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    scene=get_Scene(window);
                    scene.setCursor(Cursor.DEFAULT);
                }
            });
        }

    }
    //获取scene
    private Scene get_Scene(AnchorPane window){
        if(scene==null){
            scene=(Scene)window.getScene();
        }
        return scene;
    }
    //开始按钮被点击 [开始 重来]
    public void start_click(){
        final int[] flag = {0};//开始[0] 重来[1] 继续[2]
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(flag[0] ==0){
                    using_data=getData();
                    one.setText(String.valueOf(using_data[0]));
                    two.setText(String.valueOf(using_data[1]));
                    three.setText(String.valueOf(using_data[2]));
                    four.setText(String.valueOf(using_data[3]));
                    start.setText("重来");
                    flag[0] =1;//变成从新开始
                }else if(flag[0]==1){
                    initialize(one,two,three,four,game_over_lable,start,exit,jia,jian,chen,chu,choose);
                    start.setText("开始");
                    flag[0]=0;
                }
            }
        });
    }
    //获取数据
    public int[] getData(){
        int[] result=new int[4];
        if(level==0){
            while (true){
              int r=((int)(Math.random()*1000))%716;//随机数
                int[] dt=datas[r];
                if(dt[4]>=13){
                    for(int i=0;i<4;i++)
                        result[i]=dt[i];
                    break;
                }
            }
        }else if(level==1){
            while (true){
                int r=((int)(Math.random()*1000))%716;//随机数
                int[] dt=datas[r];
                if(dt[4]>=6&&dt[4]<=12){
                    for(int i=0;i<4;i++)
                        result[i]=dt[i];
                    break;
                }
            }
        }else if(level==2){
            while (true){
                int r=((int)(Math.random()*1000))%716;//随机数
                int[] dt=datas[r];
                if(dt[4]>=1&dt[4]<=5){
                    for(int i=0;i<4;i++)
                        result[i]=dt[i];
                    break;
                }
            }
        }
        return result;
    }
    //level获取


}

