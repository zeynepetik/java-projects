collect:
	find src -name "*.java" > sources.txt
build:
	mkdir -p build
	javac -Xlint:unchecked -d . src/**/*.java Test/SpellCheckerTest.java
run:
	java -cp . Test/SpellCheckerTest.java $(ARGS)
clean:
	rm -rf build sources.txt