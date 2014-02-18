com.viskan.ant.file
===================

Ant task to work with files

Background
==========

Ant does not work properly with unc path:
	<timestampselector property="latest.database.backup">
	<path>
		<fileset dir="\\192.168.10.33\SQLBackups\SQL2012\RamosKapTest26">
			<include name="*.*" />
		</fileset>
	</path>
	</timestampselector>

	<echo message="${latest.database.backup}" />

	The script has long timeout.

	So, was decided to create own ant task to fetch the latest file.

Usage
=====

From Ant:

	<vFile destinationDir="${my.path}" wildcard="Result*.xml" property="stable.result" failonerror="false" />

	The calls will look for a latest modified file in the "destinationDir" value with "wildcard" value and set the found file in "property" value.
	If no file find, the build will be failed. If failonerror=false, the build will continue and will return null in the "property" value.