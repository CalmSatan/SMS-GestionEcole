package Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import DBUtil.DBConnection;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;


public class AdminController implements Initializable {

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
    private TextField IDEns;
    @FXML
    private TextField FirstNameEns;
    @FXML
    private TextField LastNameEns;
    @FXML
    private TextField emailEns;
    @FXML
    private  TextField DOBEn;

    @FXML
    private TableView<Enseignant> Enseignanttable;
    @FXML
    private TableColumn<Enseignant, String> idcolumn1;
    @FXML
    private TableColumn<Enseignant, String> firstnamecolumn1;
    @FXML
    private TableColumn<Enseignant, String> lastnamecolumn1;
    @FXML
    private TableColumn<Enseignant, String> emailcolumn1;
    @FXML
    private TableColumn<Enseignant, String> dobcolumn1;
    @FXML
    private TableColumn<Enseignant, String> gradecolumn1;
    @FXML
    private TextField filiereDepartment;
    @FXML
    private TextField filiereName;
    @FXML
    private TextField filiereManager;
    @FXML
    private TableView<Filiere> filiereTable;
    @FXML
    private TableColumn<Filiere, String> filiereNameColumn;
    @FXML
    private TableColumn<Filiere, String> filiereManagerColumn;
    @FXML
    private TableColumn<Filiere, String> filiereDepartmentColumn;
    @FXML
    private TextField moduleIntitule;
    @FXML
    private TextField moduleFiliere;
    @FXML
    private TextField moduleProfesseur;
    @FXML
    private TableView<Module> moduleTable;
    @FXML
    private TableColumn<Module, String> moduleIntituleColumn;
    @FXML
    private TableColumn<Module, String> moduleFiliereColumn;
    @FXML
    private TableColumn<Module, String> moduleProfesseurColumn;

    @FXML
    private TextField departementName;
    @FXML
    private TextField departementManager;
    @FXML
    private TableView<Departement> departementTable;
    @FXML
    private TableColumn<Departement, String> departementNameColumn;
    @FXML
    private TableColumn<Departement, String> departementManagerColumn;

    private DBConnection dc;
    private ObservableList<StudentDATA> data;
    private ObservableList<Enseignant> enseignantData;
    private ObservableList<Filiere> filiereData;
    private ObservableList<Module> moduleData;
    private ObservableList<Departement> departementData;


    private String sql = "SELECT * FROM Student";
    private String sql1 = "SELECT * FROM Enseignant";
    private String sql2 = "SELECT * FROM Module";
    private String sql3 = "SELECT * FROM Departement";
    private String sql4 = "SELECT * FROM Filiere";


    public void initialize(URL url, ResourceBundle rb){
        this.dc = new DBConnection();

    }
    @FXML
    private Enseignant getEnseignantById(String id) throws SQLException {
        String sql = "SELECT * FROM Enseignant WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create a new Enseignant object from the query result
                    // This assumes that your Enseignant class has a constructor that takes all necessary parameters
                    return new Enseignant(rs.getString("ID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getString("DOB"));
                } else {
                    throw new SQLException("No Enseignant found with ID " + id);
                }
            }
        }

    }
    @FXML
    private Filiere getFiliereById(String id) throws SQLException {
        String sql4 = "SELECT * FROM Filiere WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql4)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String filiereId = rs.getString("ID");
                    String name = rs.getString("Name"); // Fetch the name from the database
                    String enseignantId = rs.getString("EnseignantID");
                    Enseignant enseignant = getEnseignantById(enseignantId); // Use the getEnseignantById method
                    return new Filiere(filiereId, name, enseignant); // Pass the name to the constructor
                } else {
                    throw new SQLException("No Filiere found with ID " + id);
                }
            }
        }
    }



    @FXML
    public void addStudent(javafx.event.ActionEvent actionEvent) {
        String sqlInsert = "INSERT INTO Student (id, firstname, lastname, email, dob) VALUES (?,?,?,?,?)";

        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());


            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void updateStudent(String id, String newFirstName, String newLastName, String newEmail, String newDob) {
        String sqlUpdate = "UPDATE Student SET firstname = ?, lastname = ?, email = ?, dob = ? WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setString(3, newEmail);
            stmt.setString(4, newDob);
            stmt.setString(5, id);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clearField(javafx.event.ActionEvent actionEvent) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);

    }

    @FXML
    public void loadStudentData(ActionEvent tableViewSortEvent) {

        String sql = "SELECT * FROM Student";
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

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

    }


    @FXML
    public void loadTeacherData(ActionEvent tableViewSortEvent) {
        String sql1 = "SELECT * FROM Enseignant";
        try {
            Connection conn = DBConnection.getConnection();
            this.enseignantData = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql1);
            while (rs.next()) {
                //
                this.enseignantData.add(new Enseignant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        //
        this.idcolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("ID"));
        this.firstnamecolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("FirstName"));
        this.lastnamecolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("LastName"));
        this.emailcolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("email"));
        this.dobcolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("DOB"));
        this.gradecolumn1.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("grade"));

        this.Enseignanttable.setItems(null);
        this.Enseignanttable.setItems(this.enseignantData);
    }

    @FXML
    public void addTeacher(ActionEvent actionEvent) {
        String sqlInsert = "INSERT INTO Enseignant(id, firstname, lastname, email, dob) VALUES (?,?,?,?,?)";

        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.IDEns.getText());
            stmt.setString(2, this.FirstNameEns.getText());
            stmt.setString(3, this.LastNameEns.getText());
            stmt.setString(4, this.emailEns.getText());
            stmt.setString(5, this.DOBEn.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateTeacher(String id, String newFirstName, String newLastName, String newEmail, String newDob) {
        String sqlUpdate = "UPDATE Enseignant SET firstname = ?, lastname = ?, email = ?, dob = ? WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setString(3, newEmail);
            stmt.setString(4, newDob);
            stmt.setString(5, id);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clearTeacherField(ActionEvent actionEvent) {
        this.IDEns.setText("");
        this.FirstNameEns.setText("");
        this.LastNameEns.setText("");
        this.emailEns.setText("");
        this.DOBEn.setText("");
    }
    @FXML
    private Filiere getFiliereByName(String name) {
        String sqlQuery = "SELECT * FROM Filiere WHERE name = ?";
        Filiere filiere = null;

        try {
            Connection conn = DBConnection.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                String managerId = rs.getString("manager_id");
                Enseignant manager = getEnseignantById(managerId);
                filiere = new Filiere(id, name, manager);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filiere;
    }
    @FXML
    public void addModuleToFiliere(String filiereName, Module module) {
        Filiere filiere = getFiliereByName(filiereName);
        filiere.addModule(module);
    }
    @FXML
    public void addFiliere(ActionEvent actionEvent) {
        String sqlInsert = "INSERT INTO Filiere(intitulé, responsable_id, département_id) VALUES (?,?,?)";

        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.filiereName.getText());
            stmt.setInt(2, Integer.parseInt(this.filiereManager.getText()));
            stmt.setInt(3, Integer.parseInt(this.filiereDepartment.getText()));

            stmt.execute();
            conn.close();

            // After creating the Filiere, add a Module to it

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateFiliere(String name, String newManager) {
        String sqlUpdate = "UPDATE Filiere SET manager = ? WHERE name = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, newManager);
            stmt.setString(2, name);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clearFiliereField(ActionEvent actionEvent) {
        this.filiereName.setText("");
        this.filiereManager.setText("");
    }

    @FXML
    public void loadFiliereData(ActionEvent tableViewSortEvent) {
        String sql4 = "SELECT * FROM Filiere";
        try {
            Connection conn = DBConnection.getConnection();
            this.filiereData = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql4);
            while (rs.next()) {
                String filiereid = rs.getString(1);
            String filiereName = rs.getString(2);
            String enseignantId = rs.getString(3);
            Enseignant enseignant = getEnseignantById(enseignantId); // Use the getEnseignantById method
            this.filiereData.add(new Filiere(filiereid, filiereName, enseignant));}
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        this.filiereNameColumn.setCellValueFactory(new PropertyValueFactory<Filiere, String>("name"));
        this.filiereManagerColumn.setCellValueFactory(new PropertyValueFactory<Filiere, String>("manager"));

        this.filiereTable.setItems(null);
        this.filiereTable.setItems(this.filiereData);
    }


    @FXML
    public void addModule(ActionEvent actionEvent) {
        String sqlInsert = "INSERT INTO Module(intitule, filiere, professeur) VALUES (?,?,?)";

        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.moduleIntitule.getText());
            stmt.setString(2, this.moduleFiliere.getText());
            stmt.setString(3, this.moduleProfesseur.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateModule(String intitule, String newFiliere, String newProfesseur) {
        String sqlUpdate = "UPDATE Module SET filiere = ?, professeur = ? WHERE intitule = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, newFiliere);
            stmt.setString(2, newProfesseur);
            stmt.setString(3, intitule);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clearModuleField(ActionEvent actionEvent) {
        this.moduleIntitule.setText("");
        this.moduleFiliere.setText("");
        this.moduleProfesseur.setText("");
    }
    @FXML
    public void loadModuleData(ActionEvent tableViewSortEvent) {
        String sql2 = "SELECT * FROM Module";
        try {
            Connection conn = DBConnection.getConnection();
            this.moduleData = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql2);
            while (rs.next()) {
                String moduleName = rs.getString(1);
                String filiereId = rs.getString(2);
                Filiere filiere = getFiliereById(filiereId); // Use the getFiliereById method
                String enseignantId = rs.getString(3);
                Enseignant enseignant = getEnseignantById(enseignantId); // Use the getEnseignantById method
                this.moduleData.add(new Module(moduleName, filiere, enseignant));}
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        this.moduleIntituleColumn.setCellValueFactory(new PropertyValueFactory<Module, String>("intitule"));
        this.moduleFiliereColumn.setCellValueFactory(new PropertyValueFactory<Module, String>("filiere"));
        this.moduleProfesseurColumn.setCellValueFactory(new PropertyValueFactory<Module, String>("professeur"));

        this.moduleTable.setItems(null);
        this.moduleTable.setItems(this.moduleData);
    }
    @FXML
    public void addDepartement(ActionEvent actionEvent) {
        String sqlInsert = "INSERT INTO Departement(name, manager) VALUES (?,?)";

        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.departementName.getText());
            stmt.setString(2, this.departementManager.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateDepartement(String name, String newManager) {
        String sqlUpdate = "UPDATE Departement SET manager = ? WHERE name = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, newManager);
            stmt.setString(2, name);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clearDepartementField(ActionEvent actionEvent) {
        this.departementName.setText("");
        this.departementManager.setText("");
    }
    @FXML
    public void loadDepartementData(ActionEvent tableViewSortEvent) {
        String sql = "SELECT * FROM Departement";
        try {
            Connection conn = DBConnection.getConnection();
            this.departementData = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String departementName = rs.getString(1);
                String IDEns = rs.getString(2);
                Enseignant enseignant = getEnseignantById(IDEns); // You need to implement this method
                this.departementData.add(new Departement(departementName, enseignant));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        this.departementNameColumn.setCellValueFactory(new PropertyValueFactory<Departement, String>("name"));
        this.departementManagerColumn.setCellValueFactory(new PropertyValueFactory<Departement, String>("manager"));

        this.departementTable.setItems(null);
        this.departementTable.setItems(this.departementData);
    }





}
