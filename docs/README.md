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

![image](https://user-images.githubusercontent.com/77394751/190422495-942ac3d9-8e1b-4ac3-abf0-01ef3dfaeabd.png)


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

![image](https://user-images.githubusercontent.com/77394751/190422564-49a79f42-f946-4e69-bcde-ac7f005a87cd.png)

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

![image](https://user-images.githubusercontent.com/77394751/190422703-3d756675-b47d-4702-ad0a-fe714998af23.png)

### Mark Task as Done : `mark`
Mark task with specified index as done
<br>
Format: `done TASK_INDEX`
* `TASK_INDEX` must be present. 
* `TASK_INDEX` must be present inside the task list.

Example of usage:
<br>
`mark 1`

Expected output:

![image](https://user-images.githubusercontent.com/77394751/190421956-16ce8947-4ec3-4662-b9e4-36a54b6f1a4a.png)

### Mark Task as Done : `mark`
Unmark task with specified index
<br>
Format: `done TASK_INDEX`
* `TASK_INDEX` must be present. 
* `TASK_INDEX` must be an index in the task list.

Example of usage:
<br>
`unmark 1`

Expected output:

![image](https://user-images.githubusercontent.com/77394751/190422936-dcc84565-90ca-41cb-90ad-fd10224335c8.png)

### List All Tasks : `list`
List all the tasks currently in the tasklist.
<br>
Format: `list`

Example of usage:
<br>
`list`

Expected output:
<br>

![image](https://user-images.githubusercontent.com/77394751/190423221-e39b0c4e-8543-4381-9d9a-a6174cfd0a39.png)

### Find a Task : `find`
Find all tasks in the list with the specified prefix.
<br>
Format: `find PREFIX`
* `PREFIX` must be present.
* `PREFIX` is case sensitive.

Example of usage:
<br>
`find C`

Expected output:
<br>

![image](https://user-images.githubusercontent.com/77394751/190424089-4c2df057-e3af-454f-b9ca-3b099cb8abca.png)

### Delete a Task : `delete`
Delete a task in the list.
<br>
Format: `delete TASK_INDEX`
* `TASK_INDEX` must be present. 
* `TASK_INDEX` must be an index in the task list.

Example of usage:
<br>
`delete 1`

Expected output:
<br>

![image](https://user-images.githubusercontent.com/77394751/190423507-a198ba49-3596-49a8-93bb-e54404f46401.png)

### Exitting the Program : `bye`
Exit Duke program. Task is only saved to local storage when this is called.
<br>
Format: `bye`

Example of usage:
<br>
`bye`


