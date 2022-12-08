[![Pages](https://github.com/AY2223S1-CS2103T-T15-4/tp/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/ErvinK123/ip/actions/workflows/pages/pages-build-deployment)

# PukeBot

PukeBot is a chatbot that helps you keep track of your tasks. This is a greenfield java project done in the span of 6 weeks by me as part of CS2103T module in NUS.

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
