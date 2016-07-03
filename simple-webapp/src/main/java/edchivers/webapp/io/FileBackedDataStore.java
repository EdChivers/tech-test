package edchivers.webapp.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	private String getFileLocation()
	{
		return configuration.getProperty(DATA_FILE_KEY);
	}
	
	public List<Person> readData() throws IOException{
		
		Properties data = new Properties();
		
		FileInputStream fis = new FileInputStream(getFileLocation());
		
		data.load(fis);
		
		return PersonConverter.convertPropertiesToPersonList(data);
	}

	public List<Person> writeData(List<Person> data) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
