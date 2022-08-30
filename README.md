# Duke Project 🐧
> “Java is to JavaScript what car is to Carpet.” – Chris Heilmann

This is a project template for a greenfield Java project. It's named after the Java mascot [_Duke_](https://www.oracle.com/java/duke/). Given below are instructions on how to use it.

## Features
- Add new tasks, deadlines, and events
- Set dates for deadlines
- Save and load from files

## Building for Gradle
1. Ensure Gradle is properly installed and configured
2. Run `./gradlew shadowJar` to build the project as a **fat jar**
3. Alternatively, use the inbuilt Gradle console in IDEA to build using shadowJar
4. Run `java -jar build/libs/ip` to run the project. **Ensure that your working directory is correct**

## Running tests
### Running gradle tests
1. Run `./gradlew test` to run the tests
2. Alternatively, run using the inbuilt Gradle console in IDEA, under `verification > test`
### Running manual tests
1. Go to `text-ui-test`
```bash
cd <user directory>/ip/text-ui-test
```
2. Enter the expected output in EXPECTED.txt
3. Ensure that `runtest.sh` is executable
```bash
chmod 777 runtest.sh 
```
4. Run `./runtest.sh` to run the tests
```bash
./runtest.sh
```
5. If you do not have a POSIX shell, and are running in Windows, you can run the `runtest.bat` file instead.
```powershell
./runtest.bat
```

## Modifying the project
- The `psvm` for the project is located at `src/main/java/duke/Duke.java`
```java
public static void main(String[] args) {
     new Duke("./data/duke.txt").run();
}
```

## Todo
- [ ] JavaFX Wrapper


## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
