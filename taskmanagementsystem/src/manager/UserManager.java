package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.User;

/**
 * Use this class to make user query's for the database .
 */
public class UserManager {
	
	private final EntityManager entityManager;
	
	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}
	
	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public User create(User user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	public User get(int id) {

		return entityManager.find(User.class, id);
	}
	
	public User getUser(String username, String password) {
		String sql = "select * from user where username = '";
		String sql1 = " and password = '";
		return (User) entityManager.createNativeQuery(sql + username + "'" + sql1 + password + "'", User.class).getSingleResult();
	}
	
	public User newUser(String username, String password) {
		try {
			User user = new User(username,password);
			create(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


}
