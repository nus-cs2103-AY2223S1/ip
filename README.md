# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## THE ONLY DUKE YOU NEED

*Made with love :heart: for programmers by programmers.*

### Never miss another assignment again!

#### Features

- Execute commands by simply typing them like the command line
- Load in your tasks directly from your local file directory
- Perform basic operations on your tasks like add, delete and mark as completed
- Save your changes to your local file directory
- **And many more!**

#### How to use

1. Ensure that you have the correct version of Java installed on your machine
```zsh
$ java --version
```
> Supports only Java 11
2. Download `duke_v0.1.jar` file from [here](https://github.com/danielk0k/ip/releases/download/A-Jar/duke_v0.1.jar)
3. Double-click on the downloaded file and that's all!

#### More features to come

- [x] Search a task by typing `find taskKeyword`
- [ ] GUI interface using JavaFX

#### For the technical people

Run your own Duke program with the following main method in Java.
```java
public class Main {
    public static void main(String[] args) {
        Duke dk = new Duke("data/duke.txt");
        dk.run();
    }
}
```
