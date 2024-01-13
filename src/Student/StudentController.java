package Student;



import Admin.StudentDATA;
import DBUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController {
    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField Module;
    @FXML
    private TextField Departement;
    @FXML
    private TextField Filiere;
    @FXML
    private TableView<StudentDATA> studenttable;
    @FXML
    private TableColumn<StudentDATA, String> idcolumn;
    @FXML
    private TableColumn<StudentDATA, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentDATA, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentDATA, String> emailcolumn;
    @FXML
    private TableColumn<StudentDATA, String> dobcolumn;
    @FXML
    private TableColumn<StudentDATA, String> MODULEcolumn;
    @FXML
    private TableColumn<StudentDATA, String> FILIEREcolumn;
    @FXML
    private TableColumn<StudentDATA, String> DEPARTEMENTcolumn;
    private DBConnection dc;
    private ObservableList<StudentDATA> data;
    private String sql = "SELECT * FROM Student";
    public void initialize(URL url, ResourceBundle rb){
        this.dc = new DBConnection();

    }

    @FXML
    public void loadStudentData(ActionEvent tableViewSortEvent) {
        try{

            Connection conn = DBConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                this.data.add(new StudentDATA(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }


        }catch(SQLException e){
            System.err.println("Error"+e);
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("FirstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("LastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("DOB"));
        this.MODULEcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("DOB"));
        this.FILIEREcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("DOB"));
        this.DEPARTEMENTcolumn.setCellValueFactory(new PropertyValueFactory<StudentDATA, String>("DOB"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

    }
}
