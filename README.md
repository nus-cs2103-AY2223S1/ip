# Jude the chatbot

This is a task tracker chatbot which came from a project template for a greenfield Java project. 
It's named after the Beatles' hit song *Hey Jude*. Given below are instructions on how to set up
the project. For information on how to use Jude the chatbot, go [here](https://cheeheng.github.io/ip/).

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. Install Gradle plugin in Intellij (if you have not done so yet).
1. Add a new Gradle configuration by clicking on `Edit configurations` then clicking on the `+` button in the pop-up window and set the command to `run`.
1. After that, select the Gradle configuration you have just created, then click `Run` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see a graphical user interface pop up.

## Credits
- src/main/resources/images/User.png taken from https://www.iconfinder.com/search?q=person&price=free 
(original author Bombasticon Studio) under Free for commercial use licence.

- src/main/resources/images/ManSmilingBehindWall.png taken from
https://www.pexels.com/photo/man-smiling-behind-wall-220453/ licenced under CC0. Original author Pixabay.