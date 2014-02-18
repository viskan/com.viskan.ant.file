com.viskan.ant.file
===================

Ant task to work with files

Background
==========

Ant does not work properly with UNC path:

```xml
	<timestampselector property="latest.database.backup">
	<path>
		<fileset dir="\\192.168.10.33\SQLBackups\SQL2012\RamosKapTest26">
			<include name="*.*" />
		</fileset>
	</path>
	</timestampselector>

	<echo message="${latest.database.backup}" />
```

The script has long timeout.

The following ant call resolves UNC path and retuns the latest modified file in the "destinationDir" value.
```xml
	<vFile destinationDir="${my.path}" wildcard="Result*.xml" property="stable.result" />
```


Usage
=====

From Ant:
```xml
	<vFile destinationDir="${my.path}" wildcard="Result*.xml" property="stable.result" failonerror="false" />
```

The calls will look for a latest modified file in the "destinationDir" value with "wildcard" value and set the found file in "property" value.
If no file find, the build will be failed. If failonerror=false, the build will continue and will return null in the "property" value.