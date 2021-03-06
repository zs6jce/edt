To create an Eclipse product for the running platform:

1. Make sure your target platform has the appropriate Eclipse base and EDT build. For
   example, you could be using the Eclipse IDE for JEE Developers 3.7.1 build, with EDT 0.8.0
   installed into it. This is the basis for the plug-ins that get packaged.

2. Update the version of org.eclipse.epp.package.edt and org.eclipse.epp.package.edt.feature, replacing
   .qualifier with .v<timestamp> (e.g. 0.7.0.v201112071300). Use this same value for every platform being
   exported. There are four places to update: MANIFEST.MF, feature.xml, and epp.product (Overview tab's version
   and Dependencies tab feature version). Also update the version in about.properties and epp.product's "About Dialog"
   text on the Branding tab.

3. Update copyrights if necessary. Do a file search for the previous year to make sure places like the license text are updated too.

4. Right-click the epp.product file > Export > Plug-in Development > Eclipse Product.

5. Make sure 'Synchronize before exporting' and 'Generate metadata directory' are both checked,
   specify the destination Directory, then click Finish (if the destination directory exists from,
   a previous export, delete it before running another export).
   
   Note: delete any 'juno' update sites in your eclipse workspace before exporting, otherwise for
         some reason they get included. Verify the update sites in the exported package.

6. It will take a few minutes and the result will be an eclipse folder and a repository folder.

7. Zip up the eclipse folder, and then add the repository folder to the 'release' update site.
   Note: If exporting for multiple platforms, you'll need to merge the repository directories for
         each platform into a single repository. Use the 'merge.sh' script to do this (read it first!).


Notes:

No matter what, the eclipse.ini file will have "-showsplash org.eclipse.platform". This must be manually
changed to "-showsplash org.eclipse.epp.package.edt" for each exported platform.

Also you probably need to edit configuration/config.ini for the following two properties:
	eclipse.product=org.eclipse.epp.package.edt.product
	osgi.splashPath=platform\:/base/plugins/org.eclipse.epp.package.edt


The wizard has a bug (at least on some Linux machines) where a JRE is included regardless of your settings.
It should manually be removed before zipping up the eclipse folder. To remove the JRE from the eclipse folder
and drastically reduce file size:
	- delete the 'jre' folder
	- delete 'jre' contents from 'p2/org.eclipse.equinox.p2.core/cache/binary/epp.package.edt_root*'
	  (it's a jar without an extension)
	- update the file size in p2/org.eclipse.equinox.p2.core/cache/artifacts.xml for the modified file


Contents of this package are the JEE package plus EDT, minus:
      <import feature="org.eclipse.jsf.feature"/>
      <import feature="org.eclipse.jst.jsf.apache.trinidad.tagsupport.feature"/>
      <import feature="org.eclipse.jst.jsf.apache.trinidad.tagsupport.feature"/>
      <import feature="org.eclipse.mylyn.bugzilla_feature"/>
      <import feature="org.eclipse.mylyn.context_feature"/>
      <import feature="org.eclipse.mylyn.ide_feature"/>
      <import feature="org.eclipse.mylyn.java_feature"/>
      <import feature="org.eclipse.mylyn.wikitext_feature"/>
      <import feature="org.eclipse.mylyn_feature"/>
      <import feature="org.eclipse.rse"/>
      <import feature="org.eclipse.rse.useractions"/>
      <import feature="org.eclipse.tm.terminal"/>
      <import feature="org.eclipse.tm.terminal.ssh"/>
      <import feature="org.eclipse.tm.terminal.telnet"/>
      <import feature="org.eclipse.tm.terminal.view"/>
