# CaCa

### _A personal assistant chatbot that helps users manage and track tasks._

<hr>

# CaCa Features

Functions with respective commands are listed below as Function (description): `command`. e.g...
- Greet user (triggered as soon as the chatbot is run)
- Exit program (end chatbot): `bye`
- Add tasks:
    - ToDos (tasks without any date/time): `todo taskDescription`
        - e.g.`todo borrow book`
    - Deadlines (tasks to be done before date/time): `deadline taskDescription /by dateTime`
        - e.g. `deadline return book /by Sunday`
    - Events (tasks that start and end at a specific time): `event taskDescription /at dateTime`
        - e.g. `event project meeting /at Mon 2-4pm`
- List task (displays a list of all tasks stored): `list`
- Mark task (marks task as done with a "X"): `mark taskIndex` 
    - e.g. `mark 2`
- Unmark task (marks task as not done and removes "X"): `unmark taskIndex`
    - e.g. `unmark 2`
- Delete task (deletes task from list): `delete taskIndex`
    - e.g. `delete 3`

<hr>

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
