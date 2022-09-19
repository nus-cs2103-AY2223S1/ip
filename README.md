# Anya Chatbot

This a chatbot that help you to manage your tasks. It came from a project template for a greenfield Java project. It's named after the the anime SpyXFamily's Anya. The **credits** for this project is in the [Credits.txt](https://github.com/xhphoong/ip/blob/master/Credits.txt). The **user guide** for this chatbot is [here](https://xhphoong.github.io/ip/). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, Gradle (if dont have, download and set it up), update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Duke.Launcher()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
 
 
 ![Screenshot (405)](https://user-images.githubusercontent.com/92239144/191008278-f7ca31ab-c6e8-4215-828c-2e855c8bdacd.png)
