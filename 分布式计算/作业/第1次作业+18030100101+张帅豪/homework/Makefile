target: compile
	mvn exec:java -Dexec.mainClass="ServerPool"

client: target/classes/EchoClient.class
	java -cp target/classes EchoClient


compile:
	mvn clean
	mvn compile
