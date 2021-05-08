package sample;

import javafx.beans.property.SimpleStringProperty;

public class UserData {
    SimpleStringProperty ID;
    SimpleStringProperty Task;
    SimpleStringProperty Duration;
    SimpleStringProperty Date;
    SimpleStringProperty Category;
    SimpleStringProperty Status;

    UserData(String ID, String Task, String Duration, String Date, String Category, String Status) {
        this.ID = new SimpleStringProperty(ID);
        this.Task = new SimpleStringProperty(Task);
        this.Duration = new SimpleStringProperty(Duration);
        this.Date = new SimpleStringProperty(Date);
        this.Category = new SimpleStringProperty(Category);
        this.Status = new SimpleStringProperty(Status);
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String id_p) {
        ID.set(id_p);
    }

    public String getTask() {
        return Task.get();
    }

    public void setTask(String name) {
        Task.set(name);
    }

    public String getDuration() {
        return Duration.get();
    }

    public void setDuration(String surname) {
        Duration.set(surname);
    }

    public String getDate() {
        return Date.get();
    }

    public void setDate(String username) {
        Date.set(username);
    }

    public String getCategory() {
        return Category.get();
    }

    public void setCategory(String surname) {
        Category.set(surname);
    }

    public String getStatus() {
        return Status.get();
    }

    public void setStatus(String surname) {
        Status.set(surname);
    }
}