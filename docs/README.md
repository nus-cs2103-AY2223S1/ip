# User Guide

Duke is a Command Line Application to help you manage your tasks.

![Ui](Ui.png)

## Start Guide

1. Ensure you have `Java 11` on your local machine (computer).
2. Create a new folder on your local machine.
3. Download the `jar` file [here](https://github.com/zicotjia/ip/releases/tag/v0.2).
4. Move the `jar` file to the newly created folder.
5. Execute `java -jar duke.jar` in a terminal in the folder. Alternatively, try to double-click on the `jar` file. The following output should be observed:

## Features 

* Add Task
  * Add ToDo
  * Add Event
  * Add Deadline
* Mark Task as Done
* Unmark Task as Done
* List All Tasks
* Find Task
* Delete Task

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

![image](https://user-images.githubusercontent.com/77394751/190427133-c8262117-e781-49d3-b399-94b940b36122.png)


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

![image](https://user-images.githubusercontent.com/77394751/190426879-7bf33da7-67a3-46c4-8e7f-1407441ba894.png)


### Mark Task as Done : `unmark`
Unmark task with specified index
<br>
Format: `done TASK_INDEX`
* `TASK_INDEX` must be present. 
* `TASK_INDEX` must be an index in the task list.

Example of usage:
<br>
`unmark 1`

Expected output:

![image](https://user-images.githubusercontent.com/77394751/190426760-8b215636-ce9f-4997-9950-edbf8f6188bd.png)


### List All Tasks : `list`
List all the tasks currently in the tasklist.
<br>
Format: `list`

Example of usage:
<br>
`list`

Expected output:

![image](https://user-images.githubusercontent.com/77394751/190426681-b1092baa-f4d1-4b6c-a9cd-2c4dd94df1d8.png)


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

![image](https://user-images.githubusercontent.com/77394751/190423507-a198ba49-3596-49a8-93bb-e54404f46401.png)

### Exitting the Program : `bye`
Exit Duke program. Task is only saved to local storage when this is called.
<br>
Format: `bye`

Example of usage:
<br>
`bye`


