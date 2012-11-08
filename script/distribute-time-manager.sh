export MAVEN_OPTS="-Xmx2024m -Xms1024m -javaagent:C:\Users\MAX\.m2\repository\org\springframework\org.springframework.instrument\3.0.5.RELEASE\org.springframework.instrument-3.0.5.RELEASE.jar"
echo "MAVEN_OPTS:"  $MAVEN_OPTS
mvn clean assembly:assembly  
sleep 3
rm ../dist -rf
mkdir ../dist
cp target/timer-manager-jar-with-dependencies.jar dist/timer-manager.jar
cp src/main/resources/timer-manager.properties dist
