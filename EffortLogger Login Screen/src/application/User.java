package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private final SimpleIntegerProperty userId;
	private SimpleStringProperty projectName;
	private SimpleStringProperty keyword;
	
	public User(int userId, String projectName, String keyword) {
		this.userId = new SimpleIntegerProperty(userId);
		this.projectName = new SimpleStringProperty(projectName);
		this.keyword = new SimpleStringProperty(keyword);
	}
	public int getUserId() {
		return userId.get();
	}
	
	public void setUserId(int userId) {
		this.userId.set(userId);
	}
	
	public IntegerProperty userIdProperty() {
		return userId;
	}
	
	public String getProjectName() {
		return projectName.get();
	}
	
	public void setProjectName(String projectName) {
		this.projectName.set(projectName);
	}
	
	public StringProperty projectNameProperty() {
		return projectName;
	}
	
	public String getKeyword() {
		return keyword.get();
	}
	
	public void setKeyword(String keyword) {
		this.keyword.set(keyword);
	}
	
	public StringProperty keywordProperty() {
		return keyword;
	}
}
