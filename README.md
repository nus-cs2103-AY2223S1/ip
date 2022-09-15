# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Option 1: Setting up in Intellij

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
   Hello! I'm duke.Duke
   What can I do for you?
   ```
   
## Option 2: Using JAR file 

1. Copy `ip.jar` file into an empty folder 
2. Open a command window in that folder 
3. Run the command `java -jar ip.jar` (i.e., run the command in the same folder as the jar file).
4. If setup is correct, you should see below as the output: 
   ```
   Hello! I'm duke.Duke
   What can I do for you?
   ```