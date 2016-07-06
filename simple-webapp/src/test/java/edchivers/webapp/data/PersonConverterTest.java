
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
		Properties p = getSampleProperties();
		
		int expectedLength = p.size() / 2;
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedLength, people.size());		
	}

	@Test
	public void testConvertPropertiesToPersonList_PersonWithOnlyFirstName_PersonIsPresentInList()
	{
		String firstName = "Bob";
		String lastName = "";
		Properties p = new Properties();
		p.setProperty("0.firstName", firstName);
		
		List<Person> expectedPeople = new ArrayList<Person>();
		expectedPeople.add(new Person(firstName, lastName));
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedPeople, people);		
	}

	@Test
	public void testConvertPropertiesToPersonList_PersonWithOnlyLastName_PersonIsPresentInList()
	{
		String firstName = "";
		String lastName = "Smith";
		Properties p = new Properties();
		p.setProperty("0.lastName", lastName);
		
		List<Person> expectedPeople = new ArrayList<Person>();
		expectedPeople.add(new Person(firstName, lastName));
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedPeople, people);		
	}
	
	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusUnrelatedData_ListIsCorrectLength()
	{
		Properties p = getSampleProperties();
		
		int expectedLength = p.size() / 2;
		
		p.setProperty("unrelated.field", "unrelated.value");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertEquals("List was not the expected length!", expectedLength, people.size());		
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusInvalidData_ListIsCorrectLength()
	{
		Properties p = getSampleProperties();
		
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
		Properties p = getSampleProperties();
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getSamplePersonList()));
	}

	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusUnrelatedData_ListContainsExpectedData()
	{
		Properties p = getSampleProperties();
		
		p.setProperty("unrelated.field", "unrelated.value");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getSamplePersonList()));
	}

	
	@Test
	public void testConvertPropertiesToPersonList_SampleInputPlusInvalidData_ListContainsExpectedData()
	{
		Properties p = getSampleProperties();
		
		//note these are not a matching pair so should be ignored
		p.setProperty("12345.firstname", "firstname");
		p.setProperty("54321.lastname", "lastname");
		
		List<Person> people = PersonConverter.convertPropertiesToPersonList(p);
		
		Assert.assertTrue("List contained unexpected data!", people.containsAll(getSamplePersonList()));		
	}

	@Test
	public void testConvertPersonListToProperties_SampleInput_PropertiesIsCorrectSize()
	{
		List<Person> people = getSamplePersonList();
		
		int expectedSize = people.size() * 2;
		
		Properties props = PersonConverter.convertPersonListToProperties(people);
		
		Assert.assertEquals("Properties was not the expected size!", expectedSize, props.size());		
	}

	@Test
	public void testConvertPersonListToProperties_SampleInput_PropertiesContainsExpectedData()
	{
		List<Person> people = getSamplePersonList();
		
		Properties props = PersonConverter.convertPersonListToProperties(people);
		
		Assert.assertEquals("Properties did not contain the expected data!", getSampleProperties(), props);		
	}

	@Test
	public void testConvertPersonListToProperties_BlankPersonData_BlankPersonsNotPresentInProperties()
	{
		List<Person> people = getSamplePersonList();
		people.add(new Person("",""));
		
		int expectedSize = getSamplePersonList().size() * 2;
		
		Properties props = PersonConverter.convertPersonListToProperties(people);
		
		Assert.assertEquals("Conversion did not ignore people with blank first and last names!", expectedSize, props.size());		
	}

	@Test
	public void testConvertPersonListToProperties_BlankLastName_FirstNameInProperties()
	{
		List<Person> people = new ArrayList<Person>();
		people.add(new Person("Tom",""));
		
		//should just contain the first name
		int expectedSize = 1;
		
		Properties props = PersonConverter.convertPersonListToProperties(people);
		
		Assert.assertEquals("Unexpected number of properties!", expectedSize, props.size());		
	}

	@Test
	public void testConvertPersonListToProperties_BlankFirstName_LastNameInProperties()
	{
		List<Person> people = new ArrayList<Person>();
		people.add(new Person("","Smith"));
		
		// should just be the last name
		int expectedSize = 1;
		
		Properties props = PersonConverter.convertPersonListToProperties(people);
		
		Assert.assertEquals("Conversion did not ignore people with blank first and last names!", expectedSize, props.size());		
	}

	
	private List<Person> getSamplePersonList()
	{
		List<Person> people = new ArrayList<Person>();
		
		people.add(new Person("Fred", "Jones"));
		people.add(new Person("Bob", "Smith"));
		people.add(new Person("Claire","Wright"));
		people.add(new Person("Sarah", "Thompson"));
		
		return people;
	}
	
	private Properties getSampleProperties()
	{
		Properties p = new Properties();
		
		p.setProperty("0.firstName", "Fred");
		p.setProperty("0.lastName", "Jones");

		p.setProperty("1.firstName", "Bob");
		p.setProperty("1.lastName", "Smith");
		
		p.setProperty("2.firstName", "Claire");
		p.setProperty("2.lastName", "Wright");
		
		p.setProperty("3.firstName", "Sarah");
		p.setProperty("3.lastName", "Thompson");
		
		return p;
	}
}
