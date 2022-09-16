# Duke

A task management chatbot for learning Java software engineering.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, the CLI version of the bot should start.

## Acknowledgements

This project uses the following third-party resources and libraries:
* [JChronic](https://github.com/samtingleff/jchronic)
* Bot avatar: [Cat Avatar Generator](https://www.peppercarrot.com/extras/html/2016_cat-generator/) by David Revoy
* User avatar: [AP D. Rajapakse's profile picture](https://www.comp.nus.edu.sg/cs/people/damithch/)
