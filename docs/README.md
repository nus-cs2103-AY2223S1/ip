# User Guide

Duke is a Command Line Application to help you manage your tasks.

## Start Guide

1. Ensure you have `Java 11` on your local machine (computer).
2. Create a new folder on your local machine.
3. Download the `jar` file [here](https://github.com/zicotjia/ip/releases/tag/A-Release).
4. Move the `jar` file to the newly created folder.
5. Execute `java -jar ip.jar` in a terminal in the folder. Alternatively, try to double-click on the `jar` file. The following output should be observed:

## Features 

* [Add Task]
  * [Add ToDo]
  * [Add Event]
  * [Add Deadline]
* [Mark/unmark Task as Done]
* [List All Tasks]
* [Find Task]
* [Delete Task]

## Usage

### `Add a ToDo Task` - todo
Add a ToDo task into the list.
<br>

Format: `todo TASK_DESCRIPTION`
* `TASK_DESCRIPTION` must be present.

Example of usage: 
<br>
`todo Hello World`

Expected outcome:
<br>

![image](https://user-images.githubusercontent.com/77394751/190419190-82958682-ca76-43ab-9d23-7465063be044.png)

### `Add a Event Task` - event
Add an Event task into the list.
<br>

Format: `event TASK_DESCRIPTION /by TASK_AT`
* `TASK_DESCRIPTION`, `/by` and `TASK_AT` must be present.
* `TASK_AT` follows the format `YYYY-MM-DD`

Example of usage: 
<br>
`event CS Workshop in LT /at 2022-12-12`

Expected outcome:
<br>

![image](https://user-images.githubusercontent.com/77394751/190421015-30f33487-afc9-44cd-985c-1569d9537a80.png)


