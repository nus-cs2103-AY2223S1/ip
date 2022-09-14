# Duke project 

This is a greenfield Java project for CS2103. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `...plugin/src/main/java/Duke/DukeEntryPoint.java` file, right-click it, and choose `Run current file` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

![Screenshot_1](https://user-images.githubusercontent.com/53963433/190104338-df226794-bfbf-45d8-932f-16b151677463.png)

Try using the following commands:
* list (show all tasks)
* todo task description (add a todo to list)
* event task description /at yyyy-mm-dd hh:mm (add an event)
* deadline task description /at yyyy-mm-dd hh:mm (add a task)
* mark index (mark the task at that index)
* delete index (delete task at that index)
* find keyword (finds task containing that keyword)
* reminder (gives the latest reminder)

Sample sample results:
![Screenshot_2](https://user-images.githubusercontent.com/53963433/190105299-546af798-b096-4d54-86ba-b2f175c9db0d.png)

![Screenshot_1](https://user-images.githubusercontent.com/53963433/187814401-be9b142c-8053-4f86-8045-6e6dcfb06238.png)
