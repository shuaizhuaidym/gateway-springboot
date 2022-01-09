package springbootvue.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

	int id;
	private String firstName;
	private String lastName;
	private String occupation;

	private int age;
	private LocalDate registered;
	
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	public User() {
	}

	public User(String firstName, int age, LocalDate registered) {
		super();
		this.firstName = firstName;
		this.age = age;
		this.registered = registered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("User{").append("id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", occupation=").append(occupation).append("}");

		return builder.toString();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getRegistered() {
		return registered;
	}

	public void setRegistered(LocalDate registered) {
		this.registered = registered;
	}
}