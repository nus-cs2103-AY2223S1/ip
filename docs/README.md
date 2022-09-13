# User Guide for Duke

Duke is a **Command Line Interface (CLI) based application** for managing your tasks. If you are familiar with CLI, 
you will find that this is faster than traditional GUI task managers.

[Quick start](##quick-start)

Features
- Viewing help: `help`
- Listing tasks: `list`
- Add todo task: `todo`
- Add deadline task: `deadline`
- Add event: `event`
- Mark task as done: `mark`
- Unmark done task: `unmark`
- Delete task: `delete`
- Find task by keyword: `find`
- Close the application: `bye`

Command summary

---

## Quick Start


1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/Bacon-Strips/ip/releases)
3. Copy the file to the folder you want to use as the __home folder__ for Duke.
4. Double-click the file to start the app. The GUI below should appear in a few seconds. 

5. Type the command in the text box and press Enter to execute it. e.g. typing `help` and pressing Enter will reveal the command guide. 
   
   Some example commands you can try:
   - `list`: Lists all your current tasks
   - 

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
