package edchivers.webapp.data;

public class Person {

	String firstName = "";
	String lastName = "";
	
	public Person()
	{
		
	}
	
	public Person(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	@Override
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean match = false;
		
		if (o != null && o instanceof Person)
		{
			Person otherPerson = (Person) o;
			match = this.getFirstName().equals(otherPerson.getFirstName()) 
					&& this.getLastName().equals(otherPerson.getLastName()); 
		}
		
		return match;
	}
	
}
