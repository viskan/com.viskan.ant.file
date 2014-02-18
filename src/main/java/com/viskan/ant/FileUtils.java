package com.viskan.ant;

import java.io.File;
import java.io.FileFilter;

import org.apache.tools.ant.Project;

import static org.apache.commons.io.FilenameUtils.wildcardMatch;

/**
 * Provides functionality to work with files
 * 
 * @author stepan
 */
class FileUtils
{
	private FileUtils()
	{
	}

	/**
	 * {@link WildcardFileFilter} and {@link LastModifiedFileComparator} works very slow to filter files.
	 * Used own solution.
	 * 
	 * Returns the last modified file in the @param dir or null if any file was found.
	 *  
	 * @param dir is source folder
	 * @param wildcard is filter to list files
	 * @return the last modified file
	 */
	static File lastModifiedFile(String dir, final String wildcard, Project project)
	{
		File folder = new File(dir);
		
		if (folder.exists())
		{
			return fetchLastModifiedFileByWildcard(wildcard, folder, project);
		}

		return null;
	}

	private static File fetchLastModifiedFileByWildcard(final String wildcard, File folder, Project project)
	{
		project.log("Looking for files in the folder: " + folder.getName());
		
		File[] files = folder.listFiles(new FileFilter()
		{
			public boolean accept(File file)
			{
		        String name = file.getName();
		        return wildcardMatch(name, wildcard) && file.isFile(); 
			}
		});

		if (files != null)
		{
			return fetchLastModifiedFile(files, project);
		}
		
		project.log("No files found with wildcard: " + wildcard);
		return null;
	}

	private static File fetchLastModifiedFile(File[] files, Project project)
	{
		project.log("Found the last modified files: " + files);
		
		long lastModified = Long.MIN_VALUE;

		File choise = null;
		for (File file : files)
		{
			if (file.lastModified() > lastModified)
			{
				choise = file;
				lastModified = file.lastModified();
			}
		}
 		return choise;
	}
}