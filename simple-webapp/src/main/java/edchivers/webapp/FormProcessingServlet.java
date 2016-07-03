package edchivers.webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edchivers.webapp.validators.StringArrayDataValidator;
import edchivers.webapp.data.Person;
import edchivers.webapp.io.DataStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class FormProcessingServlet extends HttpServlet {
	
	private StringArrayDataValidator dataValidator = new StringArrayDataValidator();
	
	private DataStore dataStore;
	
	public static final String FIRST_NAME_PARAM = "people[][firstname]";
	public static final String LAST_NAME_PARAM = "people[][surname]";
	public static final String DATA_STORE_INIT_PARAM = "dataStore";
	
	public void init(ServletConfig config) throws ServletException
	{
		try {

			String dataStoreType = config.getInitParameter(DATA_STORE_INIT_PARAM);
			Class<DataStore> storeClass = (Class<DataStore>) Class.forName(dataStoreType);
			this.dataStore = storeClass.newInstance();
			
		} catch (Exception e) {
			// TODO real logging implementation would be better
			e.printStackTrace();
			throw new ServletException("Unable to instantiate servlet - please check init parameters");
		}
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
 
		String[] firstNames = req.getParameterValues(FIRST_NAME_PARAM);
		String[] lastNames = req.getParameterValues(LAST_NAME_PARAM);
		
		List<Person> updatedData = writeFormData(firstNames, lastNames);
    			
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
 
    	// TODO this should read data
		throw new ServletException("not implemented yet, sorry!");
    			
    }

	
	private List<Person> writeFormData(String[] firstNames, String[] lastNames) throws IllegalArgumentException, IOException
	{		
		List<Person> people = new ArrayList<Person>();

		
		if (dataValidator.validate(firstNames, lastNames))
		{
			// assumption : arrays are the same length
			// this is reasonable as this is part of our validation criteria
			for (int i=0; i < firstNames.length; i ++)
			{
				Person p = new Person();
				p.setFirstName(firstNames[i]);
				p.setLastName(lastNames[i]);
				people.add(p);
			}
			
			// write to store
			List<Person> updatedStoreData = this.dataStore.writeData(people);
			
			if (updatedStoreData != null && updatedStoreData.size() > 0)
			{
				people.addAll(updatedStoreData);
			}
		}
		else
		{
			throw new IllegalArgumentException("Data not valid");
		}
		
		return people;
	}
}
