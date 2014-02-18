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
	<!-- Copy-paste com.viskan.ant.file-1.0-SNAPSHOT.jar into /lib folder -->
    <path id="classpath">
        <fileset dir="lib" includes="**/*.jar"/>
    </path>
	<taskdef name="vFile" classname="com.viskan.ant.VFileTask" classpathref="classpath"/>

	<target name="test vFile">
		<vFile destinationDir="${my.path}" wildcard="Result*.xml" property="my.result" failonerror="false" />
		<echo message="Fetched the latest modified file: ${my.result}" />
	</target>

```

The calls will look for a latest modified file in the "destinationDir" value with "wildcard" value and set the found file in "property" value.
If no file find, the build will be failed. If failonerror=false, the build will continue and will return null in the "property" value.