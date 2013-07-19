set MAVEN_OPTS="-javaagent:%~dp0spring-instrument-3.0.5.RELEASE.jar"
echo "MAVEN_OPTS:"  %MAVEN_OPTS%
call mvn clean assembly:assembly  
timeout 3
echo "cancello la cartella dist"

rem del /s /f /q dist
rem for /f %% in ('dir /ad /b dist') do rd /s /q dist

rmdir dist /s /q

mkdir dist\timer-manager

copy target\timer-manager-jar-with-dependencies.jar dist\timer-manager\timer-manager.jar
copy src\main\resources\timer-manager.properties dist\timer-manager
cd dist
timeout 2
echo "creo lo zip"
jar -cfM timer-manager.zip timer-manager\*
echo "ok"