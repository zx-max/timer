mvn clean assembly:assembly  
sleep 3
rm ../dist -rf
mkdir ../dist
cp target/timer-manager.jar dist
cp src/main/resources/timer-manager.properties dist
