package application;

import java.util.HashMap;
import java.util.Map;

import application.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



@SuppressWarnings("unused")
public class Main extends Application {
	public void start(Stage primaryStage) {
		try {
			HashMap<String, String> users = new HashMap<String, String>();
			login login = new login(users);
			primaryStage.setTitle("Effort Logger Login");
			GridPane root = new GridPane();
			root.setAlignment(Pos.CENTER);
			root.setHgap(10);
			root.setVgap(10);
			root.setPadding(new Insets(25,25,25,25));
			Scene scene1 = new Scene(root,300,250);
			primaryStage.setScene(scene1);
			
			Scene scene2 = new Scene(new Group());
	 
	        TableView table = new TableView<User>();
	        final ObservableList<User> data =
	    	        FXCollections.observableArrayList(
	    	            new User(1001, "Project1", "keyword1"),
	    	            new User(1002, "Project1", "keyword1"),
	    	            new User(1003, "Project1", "keyword1"),
	    	            new User(1001, "Project2", "keyword2"),
	    	            new User(1002, "Project2", "keyword2"),
	    	            new User(1002, "Project3", "keyword2")
	    	        );
	        final TextField search = new TextField();
	        Button btn = new Button("Search");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	public void handle(ActionEvent e) {
	        		ObservableList<User> searched = FXCollections.observableArrayList();
	        		if(search.getText().trim().isEmpty()) {
	        			
	        		}
	        		else {
		        		for(User user : data) {
		        			if(user.getKeyword().equals(search.getText().trim())) {
		        				searched.add(user);
		        			}
		        		}
		        		table.setItems(searched);
	        		}
	        	}
	        });
	 
	        table.setEditable(true);
	 
	        TableColumn userIdCol = new TableColumn("User ID");
	        userIdCol.setMinWidth(100);
	        userIdCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
	        
	        TableColumn projectNameCol = new TableColumn("Project Name");
	        projectNameCol.setMinWidth(200);
	        projectNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("projectName"));
	        
	        TableColumn keywordCol = new TableColumn("Keyword");
	        keywordCol.setMinWidth(100);
	        keywordCol.setCellValueFactory(new PropertyValueFactory<User, String>("keyword"));
	        
	        table.setItems(data);
	        table.getColumns().addAll(userIdCol, projectNameCol, keywordCol);
	        
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(search, btn, table);
	 
	        ((Group) scene2.getRoot()).getChildren().addAll(vbox);
			
			Text title = new Text("Welcome");
			root.getChildren().add(title);
			
			Label user = new Label("User Name:");
			root.add(user, 0, 1);
			
			TextField userField = new TextField();
			root.add(userField, 1, 1);
			
			Label password = new Label("Password:");
			root.add(password, 0, 2);
			
			TextField passwordField = new PasswordField();
			root.add(passwordField, 1, 2);
			
			Button loginBtn = new Button("Log in");
			loginBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(login.checkLogin(userField.getText().trim(), passwordField.getText().trim()) == 0) { //this is if login or password field is completely empty
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Please enter your username and password.");
						fail.showAndWait();
					}
					else if(login.checkLogin(userField.getText().trim(), passwordField.getText().trim()) == 2) { //if too many attempts are made, login will be locked
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Too many attempts. Please reset.");
						fail.showAndWait();
					}
					else if(login.checkLogin(userField.getText().trim(), passwordField.getText().trim()) == 1) { //this is if the user password combination is invalid
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Login information not found.");
						fail.showAndWait();
					}
					else if(login.checkLogin(userField.getText().trim(), passwordField.getText().trim()) == 3) { //this is successful
						Alert success = new Alert(AlertType.INFORMATION);
						success.setContentText("Success!");
						success.showAndWait();
						primaryStage.setScene(scene2);
					}
					else { //if unforeseen error shows up
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Invalid input!");
						fail.showAndWait();
					}
				}
			});
			root.add(loginBtn, 1, 3);
			Button registerBtn = new Button("Register");
			registerBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(login.checkLogin(userField.getText().trim(), passwordField.getText().trim()) == 0) { //this is if login or password field is completely empty
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Please enter in a valid username and password.");
						fail.showAndWait();
					}
					else if(login.userExists(userField.getText().trim())) {
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Username already exists.");
						fail.showAndWait();
					}
					else {
						login.addLogin(userField.getText().trim(), passwordField.getText().trim());
						Alert fail = new Alert(AlertType.INFORMATION);
						fail.setContentText("Success!");
						fail.showAndWait();
					}
					
				}
			});
			root.add(registerBtn, 1, 4);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
