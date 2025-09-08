// //convert XML to properties file

// String xmlFile = args[0];

// println("Opening " + xmlFile + " for processing...");

// def file = new File('apps.properties');
// def xml = new XmlSlurper().parse(xmlFile)

// xml.application.each { app->
//     println("found application: ${app}");
// 	file << "${app}=true\n";
// 	app.attributes().keySet().each{ key->
// 		def val = app.attributes().get(key);
// 		println("found attribute: ${key}=${val}");
// 		file << "${app}.${key}=${val}\n";
// 	}
// }

//import groovy.xml.XmlSlurper

def xmlFile = args[0] // Path to KeyCiteReleasesApps.xml
def outputFile = new File("apps.properties")
outputFile.withWriter { writer ->
    def root = new XmlSlurper().parse(new File(xmlFile))
    root.application.each { app ->
        def name = app.text()
        def version = app.@buildnum.text()
        writer.writeLine("${name}=true")
        writer.writeLine("${name}_version=${version}")
    }
}
println "apps.properties file generated."

