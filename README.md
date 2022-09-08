# duke.Duke project template

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
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
   _____________   _       _______________
   |___   __||  | | |     | |__  __| ____|
       | |  | __ ||  \   /  |  | | | |___
    _  | | | |__| |   \_/   |  | | |  ___|
   | |_| | |  __  | |\   /| |__| |_| |___
   |_____| |_|  |_|_| \_/ |_|______|_____|
   ```
## Commands

`list`

### Operations

1. `mass` {operation} {ALL/numbers}
2. `delete` {number}
3. `mark` {number}
4. `unmark` {number}
5. `find` {keyword}

### Creating tasks

1. `todo` {description}
2. `event` {description} `/at` {dd/MM/YYYY,HHmm}
3. `deadline` {description} `/by` {dd/MM/YYYY,HHmm}
4. `fixed` {description} `/takes` {duration in hours}
