package Admin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class StudentDATA {
    private final StringProperty ID;
    private final StringProperty FirstName;
    private final StringProperty LastName;
    private final StringProperty email;
    private final StringProperty DOB;





    public StudentDATA(String id, String firstname, String lastname, String email, String dob){

        this.ID = new SimpleStringProperty(id);
        this.FirstName = new SimpleStringProperty(firstname);
        this.LastName = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);


    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public String getLastName() {
        return LastName.get();
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDOB() {
        return DOB.get();
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }




}