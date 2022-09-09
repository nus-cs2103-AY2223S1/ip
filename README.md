# Our new and improved Duke 

Duke simulates a task manager where you can add 3 types of events, individually known as: Todo / Event / Deadline. These all come with a description of the task. They are stored in a list. You can mark/unmark/delete/view a list and more! 

## What do I need to use the project?

Prerequisites: JDK 11

## How do I set up this project? 

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

## Using our commands 
Enter the corresponding input as required! 

| Command Functionality | Syntax/Format Examples | Description |
| --- | --- | --- | 
| Add todo |todo <description>  | E.g: todo clean dishes
Adds a todo event to the list |

