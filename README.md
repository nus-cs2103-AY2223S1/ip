# Knight

A greenfield Java project. It's named after the Hollow Knight character of the same name.
Given below are instructions on how to use it.

Unfortunately, we can't use the Knight and Hornet sprites from Hollow Knight as
they are copyrighted - we used an image of a knight chess piece instead. :(

## Quickstart
1. Ensure you have Java 11 or above installed.
2. Download the latest version of Knight from [here](https://github.com/jontmy/ip/releases).
3. Copy the `.jar` file to the folder you want to use.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Enter commands in the command box and press Enter to execute them.

## Command Summary

| action                    | command                                    |
|---------------------------|--------------------------------------------|
| add a todo                | `todo <description>`                       |
| add a deadline            | `deadline <description> /by <date> <time>` |
| add an event              | `event <description> /at <date> <time>`    |
| list all tasks            | `list`                                     |
| find a task               | `find <search term>`                       |
| mark a task               | `mark <index>`                             |
| unmark a task             | `unmark <index>`                           |
| delete a task             | `delete <index>`                           |
| create a command shortcut | `alias add <alias> <command>`              |
| remove a command shortcut | `alias remove <alias> <command>`           |
| list all command shortcut | `alias list`                               |
| exit the program          | `bye`                                      |
