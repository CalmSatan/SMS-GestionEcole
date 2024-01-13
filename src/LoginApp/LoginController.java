package LoginApp;
import Admin.AdminController;
import Student.StudentController;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> ComboBox;
    @FXML
    private Button LoginButton;
    @FXML
    private Label loginstatus;


    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected to Database");
        } else {
            this.dbstatus.setText("Not Connected To Database");
        }
        this.ComboBox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void Login(ActionEvent event) {
        try {

            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option) this.ComboBox.getValue()).toString())) {
                Stage stage = (Stage) this.LoginButton.getScene().getWindow();
                stage.close();
                switch (((option) this.ComboBox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            } else {
                this.loginstatus.setText("Wrong Credials");
            }
        } catch (Exception localException) {

        }

    }

    public void studentLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Student/StudentFXML.fxml").openStream());


            StudentController studentsController = (StudentController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());


            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}

