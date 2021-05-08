package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Запуск приложения JavaFX");
        launch(args);
    }

    public TextField t_username = new TextField();
    public PasswordField t_parol = new PasswordField();
    public TextField t_name = new TextField();
    public TextField t_surname = new TextField();

    public void init() {//initialize
        System.out.println("В теле метода init()");
    }

    MySQLFX database = new MySQLFX();
    int update_num = 0;

    public void start(Stage myStage) {
        Stage stage = new Stage();
        System.out.println("В теле метода start()");
        myStage.setTitle("MainExam");
        database.connection();

        Label l_password = new Label("\uD835\uDDE3\uD835\uDDEE\uD835\uDE00\uD835\uDE00\uD835\uDE04\uD835\uDDFC\uD835\uDDFF\uD835\uDDF1 : ");
        Label l_username = new Label("\uD835\uDDE8\uD835\uDE00\uD835\uDDF2\uD835\uDDFF\uD835\uDDFB\uD835\uDDEE\uD835\uDDFA\uD835\uDDF2 : ");
        Label l_name = new Label("\uD835\uDDE1\uD835\uDDEE\uD835\uDDFA\uD835\uDDF2\n");
        Label l_surname = new Label("\uD835\uDDE6\uD835\uDE02\uD835\uDDFF\uD835\uDDFB\uD835\uDDEE\uD835\uDDFA\uD835\uDDF2");

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #D3D3D3");
        gridPane.setMinSize(900, 900);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);

        gridPane.add(l_username, 0, 1);
        gridPane.add(t_username, 1, 1);

        gridPane.add(l_password, 0, 2);
        gridPane.add(t_parol, 1, 2);

        gridPane.add(l_name, 0, 3);
        gridPane.add(t_name, 1, 3);
        gridPane.add(l_surname, 0, 4);
        gridPane.add(t_surname, 1, 4);

        Button log_in = new Button("Login");
        log_in.setStyle("-fx-background-color: #FFFF00");
        log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sqlcommand = "SELECT * FROM javafx_proj.polzovateli WHERE Username='" + t_username.getText() + "' and Parol='" + t_parol.getText() + "'";
                database.connection();
                database.knopka_log(sqlcommand);
                myStage.hide();
                database.closeconnection();
            }
        });

        Button create = new Button("Create");
        create.setStyle("-fx-background-color: #ADFF2F");
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.connection();
                database.insertandselectmethod(t_username.getText(), t_parol.getText(), t_name.getText(), t_surname.getText());
            }
        });

        Button delete = new Button("Delete");
        delete.setStyle("-fx-background-color: #FF0000");
        String qw = t_username.getText();
        System.out.println(qw);
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.connection();
                database.testerid(t_username.getText(), t_parol.getText());
                System.out.println(qw);
            }

        });

        gridPane.add(delete, 2, 4);
        gridPane.add(create, 2, 3);
        gridPane.add(log_in, 2, 1);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        Scene myScene = new Scene(gridPane, 400, 200);
        myStage.setScene(myScene);
        myStage.show();
    }


    public void stop() {
        database.closeconnection();
        System.out.println("В теле метода stop()");
    }

}

class MySQLFX {
    Connection con = null;

    public void testerid(String t_username, String t_parol) {
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        ResultSet rset = null;
        Statement stmt = null;
        Connection con = null;
        String user = "root";
        String user_txt = t_username;
        String pwd_txt = t_parol;
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected-INSERT");
            stmt = con.createStatement();
            String sqlselect = "SELECT * FROM javafx_proj.polzovateli WHERE Username='" + user_txt + "' and Parol='" + pwd_txt + "'";
            rset = stmt.executeQuery(sqlselect);
            String i = "";
            while (rset.next()) {
                i = rset.getString(1);
            }
            String sqlcommand = "DELETE FROM javafx_proj.polzovateli WHERE ID=?";
            PreparedStatement preparedstatement_test = null;
            try {
                preparedstatement_test = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                preparedstatement_test.setString(1, i);
                preparedstatement_test.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            while (rset.next()) {
                System.out.println(rset.getString(1));
            }

//            PreparedStatement stmt = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
////            stmt.setString(1, id);
//            int rowsReturned = statm.executeUpdate();//1
//            System.out.println(rowsReturned);
            System.out.println("Done");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertandselectmethod(String t_username, String t_password, String t_name, String t_surname) {
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        ResultSet rset;
        Connection con = null;
        String user = "root";
        String password = "a3169464a";
        String user_txt = t_username;
        String pwd_txt = t_password;
        String name_txt = t_name;
        String sname_txt = t_surname;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected-CREATE");
            String sqlcommand = "INSERT INTO javafx_proj.polzovateli (Username,Parol,Name,Surname) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user_txt);
            statement.setString(2, pwd_txt);
            statement.setString(3, name_txt);
            statement.setString(4, sname_txt);
            statement.executeUpdate();
//            int rowsReturned = statement.executeUpdate();//1
//            if (rowsReturned == 1) {
//                int userid = 0;
//                rset = statement.getGeneratedKeys();
//                if (rset.next()) {
//                    userid = rset.getInt(1);
//                }
//            }
            System.out.println("Done");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void connection() {
        String url = "jdbc:mysql://localhost:3306/javafx_proj?serverTimezone=UTC";
        String username = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void knopka_log(String sqlcommand) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(sqlcommand);
            boolean if_user = false;
            int user_id = 0;
            while (rset.next()) {
                if_user = true;
                user_id = rset.getInt(1);
            }
            if (if_user) {
                MainPage testlogin = new MainPage();
                testlogin.sceneshow(user_id);
                System.out.println("Logged in");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void closeconnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



