import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class Main extends Application {
    
static String aid,name,email;
static String[] tips = new String[10];


    @Override
    public void start(Stage primaryStage) throws Exception{
        tips[0] = "Use insights to visualize sales and purchase data";
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Login");
    }
    
    
    public static void main(String[] args) {
        
        launch(args);
    }
}
