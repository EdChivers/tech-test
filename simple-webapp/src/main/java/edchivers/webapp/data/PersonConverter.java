package edchivers.webapp.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PersonConverter {

	private static final String FIRST_NAME_SUFFIX  = ".firstName";
	private static final String LAST_NAME_SUFFIX  = ".lastName";
	
	public static List<Person> convertPropertiesToPersonList(Properties prop)
	{
		List<Person> people = new ArrayList<Person>();
		
		//These next few lines ensure that the generated list is ordered by the
		//property key names. Probably a better way to do this.		
		
		Set<String> propertyNames = prop.stringPropertyNames();
		ArrayList<String> propertyList = new ArrayList<String>(propertyNames);
		Collections.sort(propertyList);
		
		for (String property : propertyList)
		{

			if (property.endsWith(FIRST_NAME_SUFFIX))
			{
				String firstNameProperty = property;
				
				String id = property.substring(0, property.lastIndexOf(FIRST_NAME_SUFFIX));
				String lastNameProperty = id + LAST_NAME_SUFFIX;
				
				if (prop.containsKey(lastNameProperty))
				{
					people.add(new Person(prop.getProperty(firstNameProperty), prop.getProperty(lastNameProperty)));
				}
			}
		}
		
		return people;
	}

	public static Properties convertPersonListToProperties(List<Person> personList)
	{
		Properties prop = new Properties();
		
		if (personList != null)
		{
			int index=0;
			
			for (Person p : personList)
			{
				if (isNotBlank(p.getFirstName()) && isNotBlank(p.getLastName()))
				{
				prop.setProperty(String.valueOf(index) + FIRST_NAME_SUFFIX, p.getFirstName());
				prop.setProperty(String.valueOf(index) + LAST_NAME_SUFFIX, p.getLastName());
				index++;
				}
			}
		}

		return prop;
	}
	
	private static boolean isNotBlank(String s)
	{
		return s != null && !s.trim().equals("");
	}
}
