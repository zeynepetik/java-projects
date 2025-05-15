collect:
	find src -name "*.java" > sources.txt
build:
	mkdir -p build
	javac -d build @sources.txt
run:
	java -cp build src/Main/Main.java $(ARGS)
javadoc:
	javadoc -d doc @sources.txt
clean:
	rm -rf build sources.txt doc