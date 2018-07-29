package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Task;
import entity.User;
import services.Reply;

/**
 * Use this class to make task query's for the database .
 */
public class TaskManager {
	
	private final EntityManager entityManager;

	public TaskManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Task task) {
		entityManager.getTransaction().begin();
		entityManager.merge(task);
		entityManager.getTransaction().commit();
	}

	public void create(Task task) {
		entityManager.getTransaction().begin();
		entityManager.persist(task);
		entityManager.getTransaction().commit();
	}

	public void delete(Task task) {
		entityManager.getTransaction().begin();
		entityManager.remove(task);
		entityManager.getTransaction().commit();
	}

	public Task get(Integer id) {
		return entityManager.find(Task.class, id);
	}
	
	public Task getTaskByName(String name){
		String sql = "SELECT * from task where name = '";
		return (Task) entityManager.createNativeQuery(sql + name + "'", Task.class).getSingleResult();
	}
	
	public List<Task> getTaskByUser(int user){
		System.out.println("Manager ---- getTaskByUser, user = "+user);
		String sql = "SELECT t.id, t.name, t.user, t.date, t.description, t.enddate, u.id, u.password, u.username from task t"
				+ " inner join user u on t.user = u.id"
				+ " where u.id = ";
		return entityManager.createNativeQuery(sql + user, Task.class).getResultList();
	}
	
	public List<Task> getTasksInProgress(int user) {
		String sql = "SELECT * from task t" 
				+ " inner join user u on u.id = t.user"
				+ " where u.id = ";
				String sql1 = " and now() between t.date and t.enddate";
		return (List) entityManager.createNativeQuery(sql + user + sql1, Task.class).getResultList();
	}
	
	public List<Task> getTasksToDo(int user) {
		String sql = "SELECT * from task t" 
				+ " inner join user u on u.id = t.user"
				+ " where u.id = ";
		String sql1 = " and t.date > now()";
		return (List) entityManager.createNativeQuery(sql + user + sql1, Task.class).getResultList();
	}
	
	public List<Task> getDoneTasks(int user) {
		String sql = "SELECT * from task t" 
				+ " inner join user u on u.id = t.user"
				+ " where u.id = ";
		String sql1 = " and t.enddate < now()";
		return (List) entityManager.createNativeQuery(sql + user + sql1, Task.class).getResultList();
	}
	
	public Task createTask(String name, int user, String date, String description, String enddate) {
		
		User u = ManagerHelper.getUserManager().get(user);
		
		Task task = new Task(name, u, date, description, enddate);
		
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(task);
			entityManager.getTransaction().commit();
		return task;
		
		}catch (Exception e) {
			
			return null;
		}
	}
	
	public Reply updateTask(int id, String name, int user, String date, String description, String enddate) {
		
		User u = ManagerHelper.getUserManager().get(user);
		
		Reply r = new Reply();
		Task task = new Task(id, name, u, date, description, enddate);
		try{
			entityManager.getTransaction().begin();
			entityManager.merge(task);
			entityManager.getTransaction().commit();
		return r;
		}catch (Exception e) {
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}	
	}
	
	public Reply deleteTask(int id){
		
		Task task = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(task);
			entityManager.getTransaction().commit();
		return new Reply() ;	
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

}
