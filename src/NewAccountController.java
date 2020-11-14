/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class NewAccountController implements Initializable {

    @FXML
    private TextField aidInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField companyInput;
    @FXML
    private ChoiceBox<String> roleInput;
    @FXML
    private PasswordField passwordInput;
    private ObservableList<String> roleList = FXCollections.observableArrayList("admin","sales","purchase","analyst");
    @FXML
    private Label nameError;
    @FXML
    private Label emailError;
    @FXML
    private Label passwordError;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleInput.setItems(roleList);
        
        try {
            aidInput.setText(newID());
            companyInput.setText(AccountData.company);
        } catch (SQLException ex) {
            Logger.getLogger(NewAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roleInput.getSelectionModel().select(0);
        emailError.setVisible(false);
        nameError.setVisible(false);
        passwordError.setVisible(false);
    }    
    public static String newID() throws SQLException{
        String opNum = null;
        String id = "";
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT aid from accounts";
        ResultSet rs    = stmt.executeQuery(sql);
        while(rs.next()){
             id = String.valueOf(rs.getString("aid"));  
        }
        char letter = id.charAt(0);
        int number=0;
        for(int i=1;i<=2;i++){
            String character = String.valueOf(id.charAt(i));
            number = number*10+Integer.parseInt(character);
        }
        if(number<999){
            number++;
            opNum = String.valueOf(number);
            if(number<10)
                opNum = "00"+opNum;
            else if(number<100)
                opNum = "0"+opNum;
        }
        else 
            opNum = "000";
        if(opNum.equals("000")){
            letter = (char) (letter +1);
        }
        id = String.valueOf(letter)+opNum;
        return id;
    }

    @FXML
    private void createButton(MouseEvent event) throws SQLException, IOException {
        String id = aidInput.getText();
        String name = nameInput.getText();
        String email = emailInput.getText();
        String company = companyInput.getText();
        String role = roleInput.getValue();
        String password = passwordInput.getText();
        if(!(name.equals("")))
        {
            nameError.setVisible(false);
            if(LoginController.isValid(email))
            {
                emailError.setVisible(false);
                if(ChangePasswordController.isValid(password))
                {
                    passwordError.setVisible(false);
                    Connection conn = MySQLJDBCUtil.getConnection();
                    Statement stmt  = conn.createStatement();
                    String sql = "INSERT into accounts "+"VALUES(?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql,
                              Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, id);
                    pstmt.setString(2, name);
                    pstmt.setString(3, email);
                    pstmt.setString(4, password);
                    pstmt.setString(5, role);
                    pstmt.setString(6, company);
                    int rows = pstmt.executeUpdate();
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "New Account Created Successfully";
                    root = FXMLLoader.load(getClass().getResource("SuccessMessageDisplay.fxml"));
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);
                    window.show();
                }
                else
                {
                    passwordError.setVisible(true);
                }
            }
            else
            {
                emailError.setVisible(true);
            }
        }
        else
        {
            nameError.setVisible(true);
    }
    }

    @FXML
    private void exitButton(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Account.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
