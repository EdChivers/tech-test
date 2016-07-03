
package edchivers.webapp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import org.junit.Assert;

public class PersonConverterTest {

	@Test
	public void testConvertPropertiesToPersonList_SampleInput_ListIsCorrectLength()
	{
		Properties p = getSampleInput();
		
		int expectedLength = p.size() / 2;
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedLength, people.size());		
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusUnrelatedData_ListIsCorrectLength()
	{
		Properties p = getSampleInput();
		
		int expectedLength = p.size() / 2;
		
		p.setProperty("unrelated.field", "unrelated.value");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedLength, people.size());		
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusInvalidData_ListIsCorrectLength()
	{
		Properties p = getSampleInput();
		
		int expectedLength = p.size() / 2;
		
		//note these are not a matching pair so should be ignored
		p.setProperty("12345.firstname", "firstname");
		p.setProperty("54321.lastname", "lastname");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedLength, people.size());		
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInput_ListContainsExpectedData()
	{
		Properties p = getSampleInput();
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getExpectedPersonList()));
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusUnrelatedData_ListContainsExpectedData()
	{
		Properties p = getSampleInput();
		
		p.setProperty("unrelated.field", "unrelated.value");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getExpectedPersonList()));
	}

	
	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusInvalidData_ListContainsExpectedData()
	{
		Properties p = getSampleInput();
		
		//note these are not a matching pair so should be ignored
		p.setProperty("12345.firstname", "firstname");
		p.setProperty("54321.lastname", "lastname");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getExpectedPersonList()));		
	}
	
	private List<Person> getExpectedPersonList()
	{
		List<Person> people = new ArrayList<Person>();
		
		people.add(new Person("Fred", "Jones"));
		people.add(new Person("Bob", "Smith"));
		people.add(new Person("Claire","Wright"));
		people.add(new Person("Sarah", "Thompson"));
		
		return people;
	}
	
	private Properties getSampleInput()
	{
		Properties p = new Properties();
		
		p.setProperty("1.firstName", "Fred");
		p.setProperty("1.lastName", "Jones");

		p.setProperty("2.firstName", "Bob");
		p.setProperty("2.lastName", "Smith");
		
		p.setProperty("3.firstName", "Claire");
		p.setProperty("3.lastName", "Wright");
		
		p.setProperty("4.firstName", "Sarah");
		p.setProperty("4.lastName", "Thompson");
		
		return p;
	}
}
