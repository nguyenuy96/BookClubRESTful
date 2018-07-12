package dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;
import model.UserDTO;

@Repository
public class UserDAOImp implements UserDAO {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public User save(User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void update(int id, User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User userUpdate = session.byId(User.class).load(id);
		userUpdate.setPassword(user.getPassword());
		session.getTransaction().commit();
	}

	@Override
	public User get(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		User expectedUser = session.get(User.class, id);
		return expectedUser;
	}

	@Override
	public List<UserDTO> list() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criQuery = criBuilder.createQuery(User.class);
		Root<User> root = criQuery.from(User.class);
		criQuery.select(root);
		Query<User> query = session.createQuery(criQuery);
		List<User> listUser = new ArrayList<User>();
		listUser = query.getResultList();
		List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
		ModelMapper modelMapper = new ModelMapper();
		for (User user : listUser) {
			UserDTO userDTO = new UserDTO();
			modelMapper.map(user, userDTO);
			listUserDTO.add(userDTO);
		}
		modelMapper.map(listUser, listUserDTO);
		return listUserDTO;
	}

	@Override
	public void delete(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User userDelete = session.byId(User.class).load(id);
		session.delete(userDelete);
		session.getTransaction().commit();
	}

	@Override
	public User getName(String username) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
		Query<User> query = session.createQuery(criteriaQuery);
		List<User> userFound = query.getResultList();
		User user = ((userFound == null) || (userFound.isEmpty())) ? null : userFound.get(0);
		return user;
	}

	@Override
	public void updateInf(int id, User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User updateInf = session.byId(User.class).load(id);
		if (user.getAddress() == null || user.getFullname() == null) {
			if (user.getAddress() == null) {
				updateInf.setFullname(user.getFullname());
			} else {
				updateInf.setAddress(user.getAddress());
			}
		} else {
			updateInf.setAddress(user.getAddress());
			updateInf.setFullname(user.getFullname());
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public UserDTO userResponse(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		User userEntity = session.get(User.class, id);
		UserDTO userDTO = new UserDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userEntity, userDTO);
		return userDTO;
	}

}
