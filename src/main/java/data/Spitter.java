package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spitter {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	public Spitter(String firstName, String lastName, String username, String password) {
		this(null, firstName, lastName, username, password);
	}

	public Spitter(Long id, String firstName, String lastName, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Spitter() {
		super();
		this.id = null;
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.password = null;
	}
	
	

}
