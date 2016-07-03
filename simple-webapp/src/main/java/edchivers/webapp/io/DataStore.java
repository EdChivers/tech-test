package edchivers.webapp.io;

import java.io.IOException;
import java.util.List;

import edchivers.webapp.data.Person;

public interface DataStore {

	/**
	 * 
	 * @return the contents of the data store
	 */
	public List<Person> readData() throws IOException;
	
	/**
	 * 
	 * @param data a List of <code>Person</code> data items
	 * @return the updated contents of the data store
	 */
	public List<Person> writeData(List<Person> data) throws IOException;
	
}
