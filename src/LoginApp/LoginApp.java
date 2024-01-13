package LoginApp;


import javafx.application.Application;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginApp extends Application {
    public void start(Stage stage)throws Exception{
        Parent root= (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));


        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setTitle("School Management System");
        stage.show();
    }
    public static void main (String[] args){
        launch(args);
    }
}