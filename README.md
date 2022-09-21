# CatBot Project

What is CatBot?
- A command line chat bot.
- A tool to manage your task list.

Whats good about CatBot?
1. Text based!
2. Easy to learn!
2. Simple to set up! Just download from this [link](https://github.com/tyw2811/ip/releases/tag/A-Release).

Features of CatBot:
- [x] Storing tasks
- [x] Multiple types of tasks!
  - Simple tasks with no restrictions.
  - Tasks with a deadline.
  - Tasks that occur at a specified time.
  - Tasks that take a specific time to complete.
- [ ] Managing deadlines (coming soon)
- [ ] Reminders (coming soon)

Here is what CatBot looks like!
<img src="docs/Ui.png" width="370">

#

If you are a Java programmer, here is a snippet of the `main` method:
```java
public static void main(String[] args) {
    Application.launch(Main.class, args);
}
```
Note that this project is built with JavaFX and therefore this main method is found in the Launcher class.

Have fun with CatBot!! :smile_cat:

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/catbot/javafx/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the image below showing up:
   <img src="docs/start.png" width="370">
