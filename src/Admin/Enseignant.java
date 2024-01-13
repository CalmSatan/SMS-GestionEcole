package Admin;

import DBUtil.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.net.URL;
import java.util.ResourceBundle;


public class Enseignant {
    private final StringProperty IDEns;
    private final StringProperty FirstNameEns;
    private final StringProperty LastNameEns;
    private final StringProperty emailEns;
    private final StringProperty DOBEn;

    public Enseignant(String id, String firstname, String lastname, String email, String dob ){

        this.IDEns = new SimpleStringProperty(id);
        this.FirstNameEns = new SimpleStringProperty(firstname);
        this.LastNameEns = new SimpleStringProperty(lastname);
        this.emailEns = new SimpleStringProperty(email);
        this.DOBEn = new SimpleStringProperty(dob);


    }



    public String getID() {
        return IDEns.get();
    }

    public StringProperty IDProperty() {
        return IDEns;
    }

    public void setID(String ID) {
        this.IDEns.set(ID);
    }

    public String getFirstName() {
        return FirstNameEns.get();
    }

    public StringProperty firstNameProperty() {
        return FirstNameEns;
    }

    public void setFirstName(String firstName) {
        this.FirstNameEns.set(firstName);
    }

    public String getLastName() {
        return LastNameEns.get();
    }

    public StringProperty lastNameProperty() {
        return LastNameEns;
    }

    public void setLastName(String lastName) {
        this.LastNameEns.set(lastName);
    }

    public String getEmail() {
        return emailEns.get();
    }

    public StringProperty emailProperty() {
        return emailEns;
    }

    public void setEmail(String email) {
        this.emailEns.set(email);
    }

    public String getDOBEn() {
        return DOBEn.get();
    }

    public StringProperty DOBEnProperty() {
        return DOBEn;
    }

    public void setDOBEn(String DOBEn) {
        this.DOBEn.set(DOBEn);
    }
}
