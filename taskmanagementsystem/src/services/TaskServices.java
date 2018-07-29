package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Task;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding task manager function .
 */
@Path("/task")
public class TaskServices {
	
	@GET
	@Path("/get")
	public Task getTask(@QueryParam("id") int id) {
		return ManagerHelper.getTaskManager().get(id);
	}
	
	@GET
	@Path("/getTaskByName")
	public Task getTaskByName(@QueryParam("name") String name) {
		return (Task) ManagerHelper.getTaskManager().getTaskByName(name);
	}
	
	@GET
	@Path("/getTasksInProgress")
	public List<Task> getTasksInProgress(@QueryParam("user") int user) {
		return (List<Task>) ManagerHelper.getTaskManager().getTasksInProgress(user);
	}
	
	@GET
	@Path("/getTasksToDo")
	public List<Task> getTasksToDo(@QueryParam("user") int user) {
		return (List<Task>) ManagerHelper.getTaskManager().getTasksToDo(user);
	}
	
	@GET
	@Path("/getDoneTasks")
	public List<Task> getDoneTasks(@QueryParam("user") int user) {
		return (List<Task>) ManagerHelper.getTaskManager().getDoneTasks(user);
	}
	
	@GET
	@Path("/getTaskByUser")
	public List<Task> getTaskByUser(@QueryParam("user") int user) {
		System.out.println("Service ---- getTaskByUser, user = "+user);
		return (List<Task>) ManagerHelper.getTaskManager().getTaskByUser(user);
	}
	
	@GET
	@Path("/createTask")
	public Task createTask(@QueryParam("name") String name,@QueryParam("user") int user,@QueryParam("date") String date,@QueryParam("description") String description,@QueryParam("enddate") String enddate) {
		
		return (Task) ManagerHelper.getTaskManager().createTask(name, user, date, description, enddate);
	}
	
	@GET
	@Path("/updateTask")
	public Reply updateTask(@QueryParam("id") int id,@QueryParam("name") String name,@QueryParam("user") int user,@QueryParam("date") String date,@QueryParam("description") String description,@QueryParam("enddate") String enddate) {
		
		return ManagerHelper.getTaskManager().updateTask(id, name, user, date, description, enddate);
	}
	
	@GET
	@Path("/deleteTask")
	public Reply deleteProject(@QueryParam("id") int id) {
		return ManagerHelper.getTaskManager().deleteTask(id);
	}
}
