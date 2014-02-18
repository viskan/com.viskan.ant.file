package com.viskan.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.viskan.ant.VFileTD.*;

/**
 * Tests {@link VFileTask} 
 * 
 * @author stepan
 */
public class VFileTaskTest extends Assert
{
	private VFileTask fileTask;
	
	@Before
	public void setUp() throws Exception
	{
		fileTask = new VFileTask();
		fileTask.setProject(new Project());
	}
	
	@Test
	public void test_last_modified_file() throws Exception
	{
		final String property = "test";
		
		fileTask.setDestinationDir(RAMOS_KAPPAHL_PROD_PATH);
		fileTask.setWildcard(BAK_WILDCARD);
		fileTask.setProperty(property);
		
		fileTask.execute();
		
		String result = fileTask.getProject().getProperty(property);
		assertNotNull("The fetched latest file from kappahl path should not be null", result);
	}
	
	@Test(expected=BuildException.class)
	public void test_failed_build_if_modified_file_not_exist() throws Exception
	{
		final String wrongDestinationDir = "\\\\wrongpath\\test";

		fileTask.setDestinationDir(wrongDestinationDir);
		fileTask.setWildcard(BAK_WILDCARD);
		
		fileTask.execute();
	}
	
	@Test
	public void test_build_if_modified_file_not_exist_and_unset_failonerror() throws Exception
	{
		final String wrongDestinationDir = "\\\\wrongpath\\test";
		final String property = "test";
		
		fileTask.setDestinationDir(wrongDestinationDir);
		fileTask.setWildcard(BAK_WILDCARD);
		fileTask.setProperty(property);
		fileTask.setFailonerror(false);
		
		fileTask.execute();

		String result = fileTask.getProject().getProperty(property);
		assertNull("The fetched latest file should be null", result);
	}
}