package hr.unizg.fer.rassus.grupa5;

import javax.persistence.Id;


public class User {

	@Id

	private Long id;	
	private String username;
	private String firstName;	
	private String lastName;
	private Integer numOfDogs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getNumOfDogs() {
		return numOfDogs;
	}

	public void setNumOfDogs(Integer numOfDogs) {
		this.numOfDogs = numOfDogs;
	}
	
	
}
