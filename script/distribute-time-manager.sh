mvn clean assembly:assembly  
sleep 3
rm ../dist -rf
mkdir ../dist
cp target/timer-manager-jar-with-dependencies.jar dist/timer-manager.jar
cp src/main/resources/timer-manager.properties dist
