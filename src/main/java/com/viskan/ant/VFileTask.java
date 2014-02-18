package com.viskan.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Allows to work with files as ant task.
 * The current task allows to fetch the last modified file name in the predefined folder.
 * 
 * @author stepan
 */
public class VFileTask extends Task
{
	private String destinationDir;
	private String wildcard;
	private boolean failonerror;
	
	private String property;
	
	public VFileTask()
	{
		wildcard = "*.*";
		failonerror = true;
	}

	/**
	 * @param destinationDir to fetch files from
	 */
	public void setDestinationDir(String destinationDir)
	{
		this.destinationDir = destinationDir;
	}
	
	/**
	 * @param wildcard to filter files
	 */
	public void setWildcard(String wildcard)
	{
		this.wildcard = wildcard;
	}

	/**
	 * @param failonerror to throw exception if an error occurs
	 */
	public void setFailonerror(boolean failonerror)
	{
		this.failonerror = failonerror;
	}

	/**
	 * @param property to save lastModifiedFile value
	 */
	public void setProperty(String property)
	{
		this.property = property;
	}
	 
	@Override
	public void execute() throws BuildException
	{
		log("Looking for files in the folder: " + destinationDir + " with wildcard: " + wildcard);
		File lastModifiedFile = FileUtils.lastModifiedFile(destinationDir, wildcard, getProject());
		
		validateFetchedFile(lastModifiedFile);

		String fileName = getModifiedFileName(lastModifiedFile);
		
		log("Found the last modified file: " + fileName + " in the folder: " + destinationDir);
		getProject().setProperty(property, fileName);
	}

	private String getModifiedFileName(File lastModifiedFile)
	{
		String fileName = (lastModifiedFile == null) ? null : lastModifiedFile.getName();
		return fileName;
	}

	private void validateFetchedFile(File lastModifiedFile)
	{
		if (lastModifiedFile == null && failonerror)
		{
			throw new BuildException("Cannot find any file in the folder: " + destinationDir + " with wildcard: " + wildcard);
		}
	}
}