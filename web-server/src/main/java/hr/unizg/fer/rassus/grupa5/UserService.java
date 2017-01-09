package hr.unizg.fer.rassus.grupa5;

import java.util.List;

public interface UserService {
	
	User findById(Long id);
	
	List<User> getAll();
	

}
