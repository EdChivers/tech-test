package edchivers.webapp.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edchivers.webapp.data.Person;
import edchivers.webapp.data.PersonConverter;

public class FileBackedDataStore implements DataStore {

	private static final String CONFIG_FILE = "/datastoreconfig.properties";
	private static final String DATA_FILE_KEY = "dataFile";
	
	private Properties configuration = new Properties();
	
	public FileBackedDataStore()
	{
		init();
	}
	
	/**
	 * Initialises the configuration for this data store.
	 */
	private void init()
	{
		InputStream is = this.getClass().getResourceAsStream(CONFIG_FILE);
		
		if (is != null)
		{
			try
			{
				this.configuration.load(is);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		else
		{
			throw new RuntimeException("Cannot find configuration file at " + CONFIG_FILE);
		}	
	}
	
	/**
	 * 
	 * @return the configured path to the data file.
	 */
	private String getFileLocation()
	{
		return configuration.getProperty(DATA_FILE_KEY);
	}
	
	/**
	 * Attempts to read a list of people from a data file - if no file is found this will return an empty list
	 * @return a list of <code>Person</code> objects, which may be empty if no data can be found
	 */
	public synchronized List<Person> readData() throws IOException{
		
		FileInputStream fis = null;
		List<Person> people = new ArrayList<Person>();
		
		try
		{
			fis = new FileInputStream(getFileLocation());
			
			Properties data = new Properties();
			data.load(fis);
			
			people.addAll(PersonConverter.convertPropertiesToPersonList(data));		
		}
		catch (Exception e)
		{
			// log exception, do not pass it on
			e.printStackTrace();
		}
		finally
		{
			if (fis != null)
			{
				fis.close();
			}
		}
		
		return people;
	}

	/**
	 * Writes data to the file - if the write fails this returns the current state of the file.
	 */
	public List<Person> writeData(List<Person> data) throws IOException {
		
		List<Person> updatedStoreData = data;
		
		if (writeToFile(data))
		{
			// update failed, return values from store
			updatedStoreData = readData();
		}
		
		return updatedStoreData;
	}
	
	private synchronized boolean writeToFile(List<Person> data) throws IOException
	{
		FileOutputStream fos = null;
		boolean updateSuccess = false; 
		
		try
		{
			//delete existing file before write
			File f = new File(getFileLocation());
			f.delete();
			
			fos = new FileOutputStream(getFileLocation());

			Properties prop = PersonConverter.convertPersonListToProperties(data);
			
			prop.store(fos, null);
			updateSuccess = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fos != null)
			{
				fos.close();
			}
		}
		
		return updateSuccess;
	}
	
}
