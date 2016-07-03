package edchivers.webapp.data;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

	@Test
	public void testEquals_SameData_ReturnsTrue()
	{
		Person personA = new Person("first", "last");
		Person personB = new Person("first", "last");
		
		Assert.assertTrue(personA.equals(personB));
	}
	
	@Test
	public void testEquals_DifferentData_ReturnsFalse()
	{
		Person personA = new Person("first", "last");
		Person personB = new Person("last", "first");
		
		Assert.assertFalse(personA.equals(personB));		
	}
	
	@Test
	public void testEquals_NullComparisonObject_ReturnsFalse()
	{
		Person personA = new Person("first", "last");
		
		Assert.assertFalse(personA.equals(null));		
	}

	@Test
	public void testEquals_DifferentType_ReturnsFalse()
	{
		Person personA = new Person("first", "last");
		String s = "";
		
		Assert.assertFalse(personA.equals(s));
	}
}
