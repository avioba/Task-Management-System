package manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Use this class to create entity manager .
 */
public class ManagerHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taskmanagementsystem");

	public static UserManager getUserManager() {
		return new UserManager(entityManagerFactory.createEntityManager());
	}
	
	public static TaskManager getTaskManager() {
		return new TaskManager(entityManagerFactory.createEntityManager());
	}

}
