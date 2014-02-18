package com.viskan.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.junit.Assert;
import org.junit.Test;

import static com.viskan.ant.FileUtils.lastModifiedFile;
import static com.viskan.ant.VFileTD.*;

/**
 * Tests {@link FileUtils} 
 * 
 * @author stepan
 */
public class FileUtilsTest extends Assert
{
	
	/**
	 * Fetches the latest modified file in viskan file system.
	 * The tests can be failed if we do not use viskan environment.
	 */
	@Test
	public void test_last_modified_file() throws Exception
	{
		Project project = new Project();
		
		File uncfile = lastModifiedFile(RAMOS_HAMBRI_SE_PROD_PATH, BAK_WILDCARD, project);
		assertNotNull(uncfile);

		File uncfileProdAll = lastModifiedFile(RAMOS_KAPPAHL_PROD_PATH, ALL_WILDCARD, project);
		assertNotNull(uncfileProdAll);

		File uncfileProdBak = lastModifiedFile(RAMOS_KAPPAHL_PROD_PATH, BAK_WILDCARD, project);
		assertNotNull(uncfileProdBak);
		
		File file = lastModifiedFile(LOCAL_PATH, ALL_WILDCARD, project);
		assertNotNull(file);
	}
}