package hr.unizg.fer.rassus.grupa5;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WebUsersServiceImpl implements WebUserService{
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String usersServiceUrl;
	
	public WebUsersServiceImpl(String usersServiceUrl) {
		this.usersServiceUrl = usersServiceUrl.startsWith("http") ? usersServiceUrl : "http://" + usersServiceUrl;
	}
	
	public List<User> findById(Long userId) {
		User[] users = null;
		users = restTemplate.getForObject(usersServiceUrl + "/{userId}", User[].class, userId);
		if (users == null || users.length == 0)
			return null;
		else
			return Arrays.asList(users);
	}
	
	public List<User> getAll() {
//		logger.info("getAll() invoked");
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl + "/users/",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });
		List<User> users = usersResponse.getBody();;
		return users;
	}

}
