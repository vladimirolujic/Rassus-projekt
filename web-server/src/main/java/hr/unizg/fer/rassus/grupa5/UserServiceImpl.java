package hr.unizg.fer.rassus.grupa5;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl implements UserService{
	
	protected Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	private String usersServiceUrl;

	public UserServiceImpl(String usersServiceUrl) {
		this.usersServiceUrl = usersServiceUrl.startsWith("http") ? usersServiceUrl
				: "http://" + usersServiceUrl;
	}

	@Override
	public User findById(Long id) {
		logger.info("findById() invoked: for " + id);
		return restTemplate.getForObject(usersServiceUrl + "/users/{id}",
				User.class, id);
	}

	@Override
	public List<User> getAll() {
		logger.info("getAll() invoked");
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl + "/users/",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });
		List<User> users = usersResponse.getBody();;
		return users;
	}

}
