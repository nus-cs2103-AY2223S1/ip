# Hi! I'm Floren, your dearest assistant😉!

_Congratulations!_ You have stumbled upon __Floren__, the best personal assistant who will help you manage your daily tasks, events and deadlines! Specifically, you can:
- Add your tasks
- Categorize your tasks into three types: deadlines, events, todos
- List all of your tasks
- Find your tasks with a keyword
- Mark your tasks as done, or unmark them if it was a mistake
- Delete your tasks in the list
- Save your tasks in a file, so you can load them back up
- And what more, Floren will tell you if there is task duplication! If you are a paranoid, this is the right product for you🤩!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/duke/Launcher.java` file, right-click it, and choose
   `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If
   the setup is correct, you should see something like the below as the output: <br><br>
   <img src="docs/Ui.png" height="500px">
