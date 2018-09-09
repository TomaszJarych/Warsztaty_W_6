package pl.coderslab.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_table")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String userName;
	
	private String password;
	
	private Boolean enabled;
	
	@Column(unique=true)
	private String email;

}
