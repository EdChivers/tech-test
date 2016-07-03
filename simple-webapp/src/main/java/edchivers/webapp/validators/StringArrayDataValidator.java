package edchivers.webapp.validators;

public class StringArrayDataValidator {

	public boolean validate(String[] array1, String[] array2)
	{
		boolean response = true;
		response = response && validateNotNull(array1, array2);
		response = response && validateArraysSameLength(array1, array2);
		response = response && validateNoEmptyElements(array1, array2);
		
		return response;
	}

	/**
	 * 
	 * @param array1
	 * @param array2
	 * @return false if either array1 or array2 is null
	 */
	public boolean validateNotNull(String[] array1, String[] array2)
	{
		return (array1 != null && array2 != null);
	}
	
	/**
	 * 
	 * @param array1
	 * @param array2
	 * @return false if either array is null or the arrays are not the same length
	 */
	public boolean validateArraysSameLength(String[] array1, String[] array2)
	{
		return validateNotNull(array1, array2) && (array1.length == array2.length);
	}
	
	/**
	 * 
	 * @param array1
	 * @param array2
	 * @return false if either array is null, or any of the elements in array1 or array2 are null or whitespace
	 */
	public boolean validateNoEmptyElements(String[] array1, String[] array2)
	{
		return (validateNotNull(array1, array2) && noEmptyElements(array1) && noEmptyElements(array2));
	}
	
	/**
	 * 
	 * @param array
	 * @return false if any of the elements in the array are null or whitespace
	 */
	private boolean noEmptyElements(String[] array)
	{
		boolean result = true;
		
		for (int i=0; i < array.length && result; i++)
		{
			String s = array[i];
			result = result && (s != null) && (s.trim().length() > 0);
		}
		
		return result;
	}
}
