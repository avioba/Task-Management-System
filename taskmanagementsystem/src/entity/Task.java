package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Use this class to create a task.
 */
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	private String date;
	private String description;
	private String enddate;
	
	public Task () {
		
	}
	
	public Task (String name, User user, String date, String description, String enddate) {
		this.name = name;
		this.user = user;
		this.date = date;
		this.description = description;
		this.enddate = enddate;
	}
	
	public Task (int id, String name, User user, String date, String description, String enddate) {	
		this.id = id;
		this.name = name;
		this.user = user;
		this.date = date;
		this.description = description;
		this.enddate = enddate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
}
