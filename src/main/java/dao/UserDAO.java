package dao;


import java.util.List;

import model.User;
import model.UserDTO;

public interface UserDAO {
	void update(int id, User user);
	User save(User user);
	User get(int id);
	List<UserDTO> list();
	void delete(int id);
	User getName(String username);
	void updateInf(int id, User user);
	UserDTO userResponse(int id);
}
