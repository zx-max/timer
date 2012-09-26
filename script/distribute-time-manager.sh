mvn clean assembly:assembly  
rm dist -rf
mkdir dist
cp target/timer-manager-jar-with-dependencies.jar dist/timer-manager.jar
cp src/main/resources/timer-manager.properties dist
zip -r dist/timer-manager.zip dist/*
