package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;


public class MainPage extends Application {
    MySQL data_base = new MySQL();

    @Override
    public void start(Stage stage) throws Exception {
        Connection con = null;
        ResultSet rset = null;
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        String user = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final Connection finalCon = con;
        String zapros = "SELECT * FROM javafx_proj.polzovateli WHERE ID=" + id_p;
        Label test = new Label("Welcome!");
        Statement statement = finalCon.createStatement();
        ResultSet resultSet = statement.executeQuery(zapros);
        while ((resultSet.next())) {
            test.setText("Hello " + resultSet.getString(4) + " " + resultSet.getString(5) + "!");
        }
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        root.getChildren().addAll(test);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 250, 200);
        stage.setScene(scene);
        stage.setTitle("ProfilePage");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public int id_p = 0;
    Stage stage = new Stage();

    public void sceneshow(int user_id) {
        try {
            id_p = user_id;
            start(stage);
            meth();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void meth() {
        Connection con = null;
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        ResultSet rset = null;
        Statement stmt = null;
        String user = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final Connection finalCon = con;
        Label numpages = new Label();
        numpages.setStyle("-fx-text-fill: red");

        Label task = new Label("\uD835\uDDE7\uD835\uDDEE\uD835\uDE00\uD835\uDDF8:");
        task.setStyle("-fx-text-fill: red");

        Label duration = new Label("\uD835\uDDD7\uD835\uDE02\uD835\uDDFF\uD835\uDDEE\uD835\uDE01\uD835\uDDF6\uD835\uDDFC\uD835\uDDFB:");
        duration.setStyle("-fx-text-fill: red");

        Label date = new Label("\uD835\uDDD7\uD835\uDDEE\uD835\uDE01\uD835\uDDF2:");
        date.setStyle("-fx-text-fill: red");

        Label category = new Label("\uD835\uDDD6\uD835\uDDEE\uD835\uDE01\uD835\uDDF2\uD835\uDDF4\uD835\uDDFC\uD835\uDDFF\uD835\uDE06:");
        category.setStyle("-fx-text-fill: red");

        Label l_search = new Label("\uD835\uDDE6\uD835\uDDF2\uD835\uDDEE\uD835\uDDFF\uD835\uDDF0\uD835\uDDF5:");
        l_search.setStyle("-fx-text-fill: red");

        Label filter = new Label("\uD835\uDDD9\uD835\uDDF6\uD835\uDDF9\uD835\uDE01\uD835\uDDF2\uD835\uDDFF\uD835\uDE00:");
        filter.setStyle("-fx-text-fill: red");

        Label page_num = new Label("\uD835\uDDE1\uD835\uDE02\uD835\uDDFA\uD835\uDDEF\uD835\uDDF2\uD835\uDDFF \uD835\uDDFC\uD835\uDDF3 \uD835\uDDFD\uD835\uDDEE\uD835\uDDF4\uD835\uDDF2\uD835\uDE00:");
        page_num.setStyle("-fx-text-fill: red");

        TextField t_task = new TextField();
        TextField t_duration = new TextField();
        TextField t_search = new TextField();

        Button plus_category = new Button("+");
        plus_category.setStyle("-fx-background-color: #00FF00");

        Button minus_category = new Button("-");
        minus_category.setStyle("-fx-background-color: #FF0000");

        Button create_task = new Button("Create");
        create_task.setStyle("-fx-text-fill: blue");

        Button delete = new Button("Delete task");
        delete.setStyle("-fx-text-fill: blue");

        Button statefilter = new Button("\uD835\uDDD9\uD835\uDDF6\uD835\uDDFB\uD835\uDDF6\uD835\uDE00\uD835\uDDF5\uD835\uDDF2\uD835\uDDF1");
        statefilter.setStyle("-fx-text-fill: black");

        String txtfornot = "Finished";
        String txtfornot1 = "Not finished";

        TableView<UserData> obslist = new TableView<>();
        TableColumn id_column = new TableColumn("ID");
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn task_column = new TableColumn("Task");
        task_column.setCellValueFactory(new PropertyValueFactory<>("Task"));

        TableColumn duration_column = new TableColumn("Duration");
        duration_column.setCellValueFactory(new PropertyValueFactory<>("Duration"));

        TableColumn date_column = new TableColumn("Date");
        date_column.setCellValueFactory(new PropertyValueFactory<>("Date"));

        TableColumn category_column = new TableColumn("Category");
        category_column.setCellValueFactory(new PropertyValueFactory<>("Category"));

        TableColumn status_column = new TableColumn("Status");
        status_column.setCellValueFactory(new PropertyValueFactory<>("Status"));

        obslist.setPrefWidth(800);
        System.out.println(t_search.getText());
        obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
        obslist.getColumns().addAll(id_column, task_column, duration_column, date_column, category_column, status_column);

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(datePicker.getValue());
            }
        });

        ToggleGroup group = new ToggleGroup();
        RadioButton rd1 = new RadioButton("Finished");
        RadioButton rd2 = new RadioButton("Not finished");
        RadioButton rd3 = new RadioButton("All");
        rd1.setStyle("-fx-text-fill: blue");
        rd2.setStyle("-fx-text-fill: blue");
        rd3.setStyle("-fx-text-fill: blue");
        rd1.setToggleGroup(group);
        rd2.setToggleGroup(group);
        rd3.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                System.out.println("Pressed");
                if (rd3.isSelected()) {
                    System.out.println("All");
                } else if (rd1.isSelected()) {
                    System.out.println("Only finished");
                } else if (rd2.isSelected()) {
                    System.out.println("Only <<not finished>>");
                }
            }
        });

        rd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    obslist.setItems(data_base.statefilter_finished1(id_p));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        rd2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    obslist.setItems(data_base.statefilter_notfinished1(id_p));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        rd3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    obslist.setItems(data_base.statefilter_all(id_p));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        CheckBox allornot = new CheckBox("All");

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (allornot.isSelected()) {
                    obslist.getItems().clear();
                    data_base.deleteall();
                    numpages.setText(obslist.getItems().size() + "");
                } else {
                    String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
                    Connection con = null;
                    ResultSet rset = null;
                    Statement stmt = null;
                    String user = "root";
                    String password = "a3169464a";
                    try {
                        con = DriverManager.getConnection(url, user, password);
                        stmt = con.createStatement();
                        String sqlcommand = "DELETE FROM javafx_proj.tasks WHERE ID=?";
                        PreparedStatement preparedstatement_test = null;
                        String ididid = obslist.getSelectionModel().getSelectedItem().getID();
                        System.out.println("string: " + ididid);
                        int i = Integer.parseInt(ididid.trim());
                        System.out.println("int: " + i);
                        try {
                            preparedstatement_test = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                            preparedstatement_test.setString(1, obslist.getSelectionModel().getSelectedItem().getID());
                            preparedstatement_test.executeUpdate();
                            obslist.getItems().removeAll(obslist.getSelectionModel().getSelectedItem());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        System.out.println("Done");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    numpages.setText(obslist.getItems().size() + "");
                }
            }
        });


        System.out.println(id_p);
        ObservableList<String> cb = FXCollections.observableArrayList();
        ComboBox<String> myComboBox = new ComboBox<String>();
        data_base.selectmethodcat(myComboBox, id_p);
        t_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                try {
                    obslist.setItems(data_base.search_readybutton("SELECT * FROM tasks WHERE user_id=" + id_p + " and Task like '" + newValue + "%'"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        allornot.setSelected(false);
        allornot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(allornot.isSelected());
            }
        });
        plus_category.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TextInputDialog button_inpdial = new TextInputDialog();
                button_inpdial.setTitle("Add Category: ");
                button_inpdial.setHeaderText(null);
                button_inpdial.setContentText("Categories: ");
                Optional<String> finishedresultend = button_inpdial.showAndWait();
                myComboBox.getItems().add(finishedresultend.get());
                myComboBox.setItems(myComboBox.getItems());
                if (finishedresultend.isPresent()) {
                    System.out.println("Categories: " + finishedresultend.get());
                }
                try {
                    System.out.println("Connected-CREATE");
                    String sqlcommand = "INSERT INTO javafx_proj.categories (name,user_id) VALUES (?,?)";
                    PreparedStatement stmt = finalCon.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, finishedresultend.get());
                    stmt.setInt(2, id_p);
                    stmt.executeUpdate();
                    System.out.println("Done");
                    obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
                    numpages.setText(obslist.getItems().size() + "");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        statefilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(obslist.getSelectionModel().getSelectedItem().getStatus());
                if (obslist.getSelectionModel().getSelectedItem().getStatus().equals("Not finished")) {
                    statefilter.setText("Finished");
                    data_base.updatemethnotf(txtfornot, obslist.getSelectionModel().getSelectedItem().getID());
                    obslist.setItems(data_base.fill_table(id_p, t_search.getText()));

                } else {
                    statefilter.setText("Not finished");
                    data_base.updatemethnotf(txtfornot1, obslist.getSelectionModel().getSelectedItem().getID());
                    obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
                }

            }
        });

        minus_category.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TextInputDialog button_inpdial = new TextInputDialog();
                button_inpdial.setTitle("Remove Category: ");
                button_inpdial.setHeaderText(null);
                button_inpdial.setContentText("Categories: ");
                Optional<String> finishedresultend = button_inpdial.showAndWait();
                myComboBox.getItems().remove(finishedresultend.get());
                myComboBox.setItems(myComboBox.getItems());
                if (finishedresultend.isPresent()) {
                    System.out.println("Categories: " + finishedresultend.get());
                }
                String sqlcommand = "DELETE FROM javafx_proj.categories";
                PreparedStatement preparedstatement_test = null;
                try {
                    preparedstatement_test = finalCon.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                    preparedstatement_test.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
                numpages.setText(obslist.getItems().size() + "");
            }

        });

//        Button search = new Button("Search");
//        search.setStyle("-fx-text-fill: blue");
//        search.setOnAction(new EventHandler<ActionEvent>() {
//
//                               @Override
//                               public void handle(ActionEvent event) {
//                                   obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
//                               }
//                           }
//        );

        create_task.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    System.out.println("Connected-CREATE");
                    String sqlcommand = "INSERT INTO javafx_proj.tasks (Task,Duration,Date,Category,Status,user_id) VALUES (?,?,?,?,?,?)";
                    PreparedStatement stmt = finalCon.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, t_task.getText());
                    stmt.setString(2, t_duration.getText());
                    stmt.setString(3, datePicker.getValue().toString());
                    stmt.setString(4, myComboBox.getValue());
                    stmt.setString(5, "Not finished");
                    stmt.setInt(6, id_p);
                    data_base.fill_table(id_p, t_search.getText());
                    stmt.executeUpdate();
                    System.out.println("Done");
                    obslist.setItems(data_base.fill_table(id_p, t_search.getText()));
                    obslist.setItems(data_base.fill_table(id_p, ""));
                    numpages.setText(obslist.getItems().size() + "");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #FFFFFF");
        gridPane.setMinSize(1000, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(7);
        gridPane.setHgap(7);
        gridPane.setAlignment(Pos.TOP_CENTER);

        gridPane.add(task, 0, 1);
        gridPane.add(t_task, 1, 1,4,1);

        gridPane.add(duration, 6, 1,3,1);
        gridPane.add(t_duration, 8, 1,2,1);

        gridPane.add(date, 10, 1);
        gridPane.add(datePicker, 11, 1);

        gridPane.add(category, 0, 2);
        gridPane.add(myComboBox, 1, 2,2,1);

        gridPane.add(minus_category, 3, 2,2,1);
        gridPane.add(plus_category, 5, 2);

        gridPane.add(l_search, 6, 2);
        gridPane.add(t_search, 7, 2,3,1);

        gridPane.add(filter, 0, 3);
        gridPane.add(rd1, 1, 3,2,1);
        gridPane.add(rd2, 4, 3,3,1);
        gridPane.add(rd3, 8, 3,2,1);
        gridPane.add(statefilter, 6, 4,3,1);

        gridPane.add(create_task, 0, 4);
        gridPane.add(allornot, 4, 4,2,1);
        gridPane.add(delete, 2, 4,2,1);

        gridPane.add(obslist, 0, 7,15,1);

        gridPane.add(page_num, 0, 8);
        gridPane.add(numpages, 1, 8);

        Scene myScene = new Scene(gridPane, 1000, 600);
        stage.setScene(myScene);
        stage.show();
    }
}

class MySQL {
    Connection con = null;
    String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
    ResultSet rset = null;
    Statement stmt = null;
    String user = "root";
    String password = "a3169464a";


    //    ObservableList<UserData> statefilter_all(int id_p) throws SQLException {
//        final ObservableList<UserData> data = FXCollections.observableArrayList();
//        Statement statement = con.createStatement();
//        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p;
//        ResultSet resultSet = statement.executeQuery(sqlcommand);
//        return data;
//    }
//
//    ObservableList<UserData> statefilter_finished(String finished, int id_p) throws SQLException {
//        final ObservableList<UserData> data = FXCollections.observableArrayList();
//        Statement statement = con.createStatement();
//        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p + " AND Status='" + finished + "'";
//        ResultSet resultSet = statement.executeQuery(sqlcommand);
//        return data;
//    }
//
//    ObservableList<UserData> statefilter_notfinished(String notfinished, int id_p) throws SQLException {
//        final ObservableList<UserData> data = FXCollections.observableArrayList();
//        Statement statement = con.createStatement();
//        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p + " AND Status='" + notfinished + "'";
//        ResultSet resultSet = statement.executeQuery(sqlcommand);
//        return data;
//    }


    ObservableList<UserData> search_readybutton(String sqlzapros_test) throws SQLException {
        final ObservableList<UserData> testing_button_data = FXCollections.observableArrayList();
        Statement statement = con.createStatement();
        String sqlzapros = sqlzapros_test;
        rset = statement.executeQuery(sqlzapros_test);
        while (rset.next()) {
            testing_button_data.add(new UserData(rset.getString("ID"), rset.getString("Task"), rset.getString("Duration"),
                    rset.getString("Date"), rset.getString("Category"), rset.getString("Status")));
        }
        return testing_button_data;
    }

    ObservableList<UserData> statefilter_all(int id_p) throws SQLException {
        final ObservableList<UserData> datatest_filter = FXCollections.observableArrayList();
        Statement statement = con.createStatement();
        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p;
        rset = statement.executeQuery(sqlcommand);
        while (rset.next()) {
            datatest_filter.add(new UserData(rset.getString("ID"), rset.getString("Task"), rset.getString("Duration"),
                    rset.getString("Date"), rset.getString("Category"), rset.getString("Status")));
        }
        return datatest_filter;
    }

    ObservableList<UserData> statefilter_finished1(int id_p) throws SQLException {
        final ObservableList<UserData> datatest_filter = FXCollections.observableArrayList();
        Statement statement = con.createStatement();
        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p + " and status='Finished'";
        rset = statement.executeQuery(sqlcommand);
        while (rset.next()) {
            datatest_filter.add(new UserData(rset.getString("ID"), rset.getString("Task"), rset.getString("Duration"),
                    rset.getString("Date"), rset.getString("Category"), rset.getString("Status")));
        }
        return datatest_filter;
    }

    ObservableList<UserData> statefilter_notfinished1(int id_p) throws SQLException {
        final ObservableList<UserData> datatest_filter = FXCollections.observableArrayList();
        Statement statement = con.createStatement();
        String sqlcommand = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p + " and Status='Not finished'";
        rset = statement.executeQuery(sqlcommand);
        while (rset.next()) {
            datatest_filter.add(new UserData(rset.getString("ID"), rset.getString("Task"), rset.getString("Duration"),
                    rset.getString("Date"), rset.getString("Category"), rset.getString("Status")));
        }
        return datatest_filter;
    }


    void selectmethodcat(ComboBox<String> myComboBox, int id_p) {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected-INSERT");
            stmt = con.createStatement();
            String sqlselect = "SELECT name FROM javafx_proj.categories WHERE user_id=" + id_p;

            rset = stmt.executeQuery(sqlselect);
            String i = "";
            while (rset.next()) {
                i = rset.getString(1);
                myComboBox.getItems().add(i);
            }
            System.out.println("Done");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void updatemethodfin(String txtfornot1, String id) {
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        ResultSet rset;
        Connection con = null;
        String user = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            String sqlcommand = "UPDATE javafx_proj.tasks SET Status=? where ID=?";
            PreparedStatement stmt = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, txtfornot1);
            stmt.setString(2, id);
            int rowsReturned = stmt.executeUpdate();//1
            System.out.println(String.format(("Rows affected %d"), rowsReturned));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void updatemethnotf(String txtfornot, String id) {
        String url = "jdbc:mysql://127.0.0.1:3306/javafx_proj?serverTimezone=UTC";
        ResultSet rset;
        Connection con = null;
        String user = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            String sqlcommand = "UPDATE javafx_proj.tasks SET Status=? where ID=?";
            PreparedStatement stmt = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, txtfornot);
            stmt.setString(2, id);
            int rowsReturned = stmt.executeUpdate();//1
            System.out.println(String.format(("Rows affected %d"), rowsReturned));
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

    void deleteid(int id) {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected-INSERT");
            stmt = con.createStatement();
            String sqlcommand = "DELETE FROM javafx_proj.tasks WHERE ID= '" + id + "'";
            PreparedStatement preparedstatement_test = null;
            try {
                preparedstatement_test = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                preparedstatement_test.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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

    void deleteall() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected-INSERT");
            stmt = con.createStatement();
            String sqlcommand = "DELETE FROM javafx_proj.tasks";
            PreparedStatement preparedstatement_test = null;
            try {
                preparedstatement_test = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                preparedstatement_test.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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

    ObservableList<UserData> fill_table(int id_p, String word) {
        String url = "jdbc:mysql://localhost:3306/javafx_proj?serverTimezone=UTC";
        String username = "root";
        String password = "a3169464a";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        final ObservableList<UserData> data = FXCollections.observableArrayList();
        try {
            Statement stmt = con.createStatement();
            String zapros = "SELECT * FROM javafx_proj.tasks WHERE user_id=" + id_p + " AND Task like '" + word + "%'";
            ResultSet rset = stmt.executeQuery(zapros);
            while (rset.next()) {
                data.add(new UserData(
                        rset.getString(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4),
                        rset.getString(5),
                        rset.getString(6)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

    void deletemethod(int id_p) {
        String sqlcommand = "DELETE FROM javafx_proj.tasks WHERE user_id=?";

        PreparedStatement preparedstatement_test = null;

        try {
            preparedstatement_test = con.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
            preparedstatement_test.setString(1, String.valueOf(id_p));
            preparedstatement_test.executeUpdate();
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
