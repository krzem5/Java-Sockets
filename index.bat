echo off
echo NUL>_.class&&del /s /f /q *.class
cls
javac com/krzem/sockets/Main.java
javac com/krzem/sockets/Client.java
start /max cmd /c "java com/krzem/sockets/Client 192.168.178.56:8080"
java com/krzem/sockets/Main 8080
start /min cmd /c "echo NUL>_.class&&del /s /f /q *.class"

