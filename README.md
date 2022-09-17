# dukepro project template

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
3. After that, locate the `src/main/java/dukepro.java` file, right-click it, and choose `Run dukepro.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Commands and Usage

Key in the following commands and press enter to execute

1. 'Bye' or 'bye': shuts down the program
2. 'list' or 'List': displays all tasks in the tasklist
3. 'delete' or 'Delete' followed by the number to be deleted: deletes a specified task from the list
4. 'todo' <NAME>: creates a new Todo task
5. 'deadline' <NAME> '/by' <DD-MM-YYYY>: creates a new Deadline
6. 'event' <NAME> '/at' <NAME OF LOCATION>: creates a new Event
7. 'mark' <TASK ID>: marks a task as "done"
8. 'unmark' <TASK ID>: marks a task as "not done"
9. 'showExpense': displays all expenses in a list
10. 'delExpense' <TASK NUMBER>: deletes an expense
11. 'totalSpent': shows the total amount spent of all time
12. 'spentOn' + <DD-MM-YYYY>: shows the amount of money spent on this day

Try it out yourself!
