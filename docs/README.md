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
* [Mark Task as Done]
* [Unmark Task as Done]
* [List All Tasks]
* [Find Task]
* [Delete Task]

## Usage

### Add a ToDo Task - `todo`
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

### Add a Event Task - `event`
Add an Event task into the list.
<br>

Format: `event TASK_DESCRIPTION /by TASK_AT`
* `TASK_DESCRIPTION`, `/at` and `TASK_AT` must be present.
* `TASK_AT` follows the format `YYYY-MM-DD`

Example of usage: 
<br>
`event CS Workshop in LT /at 2022-12-12`

Expected outcome:
<br>

![image](https://user-images.githubusercontent.com/77394751/190421015-30f33487-afc9-44cd-985c-1569d9537a80.png)

### Add a Deadline Task - `deadline`
Add an Event task into the list.
<br>

Format: `event TASK_DESCRIPTION /by TASK_DEADLINE`
* `TASK_DESCRIPTION`, `/by` and `TASK_DEADLINE` must be present.
* `TASK_DEADLINE` follows the format `YYYY-MM-DD`

Example of usage: 
<br>
`deadline submit lab /by 2022-12-12`

Expected outcome:
<br>

![image](https://user-images.githubusercontent.com/77394751/190421418-6052a39c-1957-4ea8-aacc-6295a14edc4f.png)

### Mark Task as Done : `mark`
Mark task with specified index as done
<br>
Format: `done TASK_INDEX`
* `TASK_INDEX` must be present inside the task list.

Example of usage:
<br>
`mark 1`

Expected output:

![image](https://user-images.githubusercontent.com/77394751/190421956-16ce8947-4ec3-4662-b9e4-36a54b6f1a4a.png)




