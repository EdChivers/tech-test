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
		
		//this allows us to keep a list of all IDs processed so far
		List<String> processedIds = new ArrayList<String>();
		
		for (String property : propertyList)
		{
			if (property.endsWith(FIRST_NAME_SUFFIX))
			{	
				String id = property.substring(0, property.lastIndexOf(FIRST_NAME_SUFFIX));
				
				if (!processedIds.contains(id))
				{
					String firstName = prop.getProperty(property);
					// person may not have a last name, in this case pad to empty String
					String lastName = padNullData(prop.getProperty(id + LAST_NAME_SUFFIX));
					
					people.add(new Person(firstName, lastName));
					processedIds.add(id);
				}
			}
			else if (property.endsWith(LAST_NAME_SUFFIX))
			{	
				String id = property.substring(0, property.lastIndexOf(LAST_NAME_SUFFIX));
				
				if (!processedIds.contains(id))
				{
					// person may not have a first name, in this case pad to empty String
					String firstName = padNullData(prop.getProperty(id + FIRST_NAME_SUFFIX));
					String lastName = prop.getProperty(property);
					
					people.add(new Person(firstName, lastName));
					processedIds.add(id);
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
				boolean personAdded = false;
				
				if (isNotBlank(p.getFirstName()))
				{
					prop.setProperty(String.valueOf(index) + FIRST_NAME_SUFFIX, p.getFirstName());
					personAdded=true;
				}
				if (isNotBlank(p.getLastName()))
				{
					prop.setProperty(String.valueOf(index) + LAST_NAME_SUFFIX, p.getLastName());
					personAdded = true;
				}

				//only update the index if we added data to the properties instance
				if (personAdded)
				{
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
	
	private static String padNullData(String s)
	{
		String output = s;
		//pads null data to an empty String
		if (s == null)
		{
			output = "";
		}
		
		return output;
	}
}
