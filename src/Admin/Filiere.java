package Admin;
import Admin.Departement;
import Admin.Enseignant;
import Admin.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Filiere {
    private final StringProperty id;
    private final StringProperty name; // Add a name field
    private Enseignant manager;
    private List<Module> modules;



    // other fields, getters and setters

    public Filiere(String id,String name, Enseignant manager) {
        if (manager == null) {
            throw new IllegalArgumentException("A Filiere must have a manager");
        }
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.manager = manager;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Enseignant getManager() {
        return manager;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setManager(Enseignant manager) {
        if (manager == null) {
            throw new IllegalArgumentException("A Filiere must have a manager");
        }
        this.manager = manager;
    }
    public void addModule(Module module) {
        if (this.modules.size() >= 12) {
            throw new IllegalArgumentException("A Filiere can have a maximum of 12 modules");
        }
        this.modules.add(module);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }



}
