# Mia - adapted from Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_.

## Setting up

Prerequisites: Java 11. If you use a version manager, look at the version specified in `.tool-versions`. Example below is using [asdf](https://asdf-vm.com/):

```bash
$ asdf local java zulu-javafx-11.58.23
```

1. Set up git hooks:

   ```bash
   $ make hooks
   ```

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)

1. Open the project into Intellij as follows:

   1. Click `Open`.

   1. Select the project directory, and click `OK`.

   1. If there are any further prompts, accept the defaults.

1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).

   In the same dialog, set the **Project language level** field to the `SDK default` option.

Image credit: Bot/user avatars are from https://doodleipsum.com/
