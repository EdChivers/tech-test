package edchivers.webapp.validators;

import org.junit.Test;

import org.junit.Assert;

public class StringArrayDataValidatorTest {

	@Test
	public void testValidateArraysSameLength_ArraysDifferentLength_ReturnsFalse()
	{
		String[] array1 = new String[]{"one","two","three"};
		String[] array2 = new String[]{"one","two","three","four"};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateArraysSameLength(array1, array2));
	}
	
	@Test
	public void testValidateArraysSameLength_ArraysSameLength_ReturnsTrue()
	{
		String[] array1 = new String[]{"one","two","three"};
		String[] array2 = new String[]{"one","two","three"};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateArraysSameLength(array1, array2));
	}

	@Test
	public void testValidateArraysSameLength_ZeroLengthArrays_ReturnsTrue()
	{
		String[] array1 = new String[]{};
		String[] array2 = new String[]{};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateArraysSameLength(array1, array2));		
	}
	
	@Test
	public void testValidateNoEmptyElements_FirstArrayWithEmptyElement_ReturnsFalse()
	{
		String[] array1 = new String[]{"one","two",""};
		String[] array2 = new String[]{"one","two","three"};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateNoEmptyElements(array1, array2));
	}
	
	@Test
	public void testValidateNoEmptyElements_SecondArrayWithEmptyElement_ReturnsFalse()
	{
		String[] array1 = new String[]{"one","two","three"};
		String[] array2 = new String[]{"one","two",""};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateNoEmptyElements(array1, array2));
	}
	
	@Test
	public void testValidateNoEmptyElements_ArraysWithNoEmptyElements_ReturnsTrue()
	{
		String[] array1 = new String[]{"one","two","three"};
		String[] array2 = new String[]{"one","two","three"};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateNoEmptyElements(array1, array2));		
	}
	
	@Test
	public void testValidateNoEmptyElements_ZeroLengthArrays_ReturnsTrue()
	{
		String[] array1 = new String[]{};
		String[] array2 = new String[]{};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateNoEmptyElements(array1, array2));		
	}
	
	@Test
	public void testValidateNotNull_FirstArrayNull_ReturnsFalse()
	{
		String[] array1 = null;
		String[] array2 = new String[]{};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateNotNull(array1, array2));		
	}
	
	@Test
	public void testValidateNotNull_SecondArrayNull_ReturnsFalse()
	{
		String[] array1 = new String[]{};
		String[] array2 = null;
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateNotNull(array1, array2));		
	}
	
	@Test
	public void testValidateNotNull_BothArraysNull_ReturnsFalse()
	{
		String[] array1 = null;
		String[] array2 = null;
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertFalse("Unexpected validation result!", validator.validateNotNull(array1, array2));
	}
	
	@Test
	public void testValidateNotNull_ZeroLengthArrays_ReturnsTrue()
	{

		String[] array1 = new String[]{};
		String[] array2 = new String[]{};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateNotNull(array1, array2));
	}
	
	@Test
	public void testValidateNotNull_NoNullArrays_ReturnsTrue()
	{

		String[] array1 = new String[]{"one","two","three"};
		String[] array2 = new String[]{"one","two","three"};
		
		StringArrayDataValidator validator = new StringArrayDataValidator();
		
		Assert.assertTrue("Unexpected validation result!", validator.validateNotNull(array1, array2));
	}
}