package edchivers.webapp.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PersonConverter {

	private static final String FIRST_NAME_SUFFIX  = ".firstName";
	private static final String LAST_NAME_SUFFIX  = ".lastName";
	
	public static List<Person> convertPropertiesToPersonList(Properties prop)
	{
		List<Person> people = new ArrayList<Person>();
		
		Iterator<String> propertyIterator = prop.stringPropertyNames().iterator();
		
		while (propertyIterator.hasNext())
		{
			String property = propertyIterator.next();
			
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

	
}
