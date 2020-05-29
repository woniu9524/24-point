package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainController.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);//让原来的有标题的那一块透明
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);//让菜单栏和内容直接透明
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
