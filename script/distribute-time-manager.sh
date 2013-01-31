export MAVEN_OPTS="-javaagent:C:\max\maven_repo\org\springframework\org.springframework.instrument\3.0.5.RELEASE\org.springframework.instrument-3.0.5.RELEASE.jar"
echo "MAVEN_OPTS:"  $MAVEN_OPTS
mvn clean assembly:assembly  
sleep 3
rm dist -rf
mkdir -p dist/timer-manager
cp target/timer-manager-jar-with-dependencies.jar dist/timer-manager/timer-manager.jar
cp src/main/resources/timer-manager.properties dist/timer-manager
cd dist
sleep 2
zip timer-manager timer-manager/*
#
#
# other way
# zip -r dist/timer-manager.zip dist/*