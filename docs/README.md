# User Guide

This is the user guide for CWQ, a task management software.

## <font color='#bc6c25'>Features</font>

### <font color='#dda15e'>Manage Tasks </font>

In CWQ, we support three types of tasks:

- **ToDo**: a task to do in the future
- **Event**: an event that task place on a certain time
- **Deadline**: a task that dues on a certain time

To manage all your tasks, you can:

- **Add** a task to your task list.
- **Mark / Unmark** a task to indicate completeness.
- **Delete** a task from your task list.
- **Edit** the content or time of your task (**to be finished**)

### <font color='#dda15e'>Visualize Tasks</font>

In CWQ, you can visualize your task list with all the information you need, including task content, task completeness and task date (if applicable). 

### <font color='#dda15e'>Find Relevant Task</font>

In CWQ, we support you to find tasks that you want. Simply type in some keywords, then you can get all relevant tasks.

### <font color='#dda15e'>Get Upcoming Tasks in Order</font>

In CWQ, you can sort all your events and deadlines by time. This can help you know what your priority for each task should be.

### <font color='#dda15e'>Remind Deadlines</font>

In CWQ, we can remind you of all your deadlines. 

## <font color='#bc6c25'>Usage</font>

### `add /p TASK_TYPE /p CONTENT /p [TASK_TIME]` <font color='#dda15e'>- Add a task </font>

This command allows you to add a task, including ToDo, Event, Deadline, to your task list.

#### Notes for the command

- `TASK_TYPE` should be one of `ToDo`, `Event`, `Deadline`.
- `CENTENT` **should not** be empty. 
- `TASK_TIME` is an optional parameter. If adding a `ToDo`, time field is not required. Otherwise (`Event` and `Deadline`), you are expected to indicate the time of your task. `TASK_TIME` should follow the format of `yyyy-mm-dd HH:MM`

#### Examples of usage

- `add /p ToDo /p CS2103T Tutorial`

  expected outcome:

  <img src="README.assets/Screenshot 2022-09-15 at 1.40.44 PM-3229846.png" alt="Screenshot 2022-09-15 at 1.40.44 PM" style="zoom:50%;" />

- `add /p Event /p CS2103T Lecture /p 2022-09-16 16:00 `

  expected outcome:

<img src="README.assets/Screenshot 2022-09-15 at 2.58.05 PM.png" alt="Screenshot 2022-09-15 at 2.58.05 PM" style="zoom: 50%;" />

- `add /p Deadline /p CS2103T Final Release /p 2022-09-16 18:00`

  expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 4.19.46 PM.png" alt="Screenshot 2022-09-15 at 4.19.46 PM" style="zoom:50%;" />



### `list` <font color='#dda15e'>- Visualise task list</font>

This command allows you to visualise your task list

#### Examples of usage

`list`

expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 4.24.23 PM.png" alt="Screenshot 2022-09-15 at 4.24.23 PM" style="zoom:50%;" />



### `delete /p TASK_INDEX` <font color='#dda15e'>- Delete a task</font>

This command allows you to delete a specific task indicated by the `TASK_INDEX`

#### Notes for the command:

- Before you `delete` a task, you are recommended to use `list` command first to make sure what the corresponding `TASK_INDEX` should be. If an invalid `TASK_INDEX` is passed, then the command won't be executed.

#### Examples of usage:

- `delete /p 6`

​		expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 5.30.21 PM.png" alt="Screenshot 2022-09-15 at 5.30.21 PM" style="zoom:50%;" />

### `mark /p TASK_INDEX` <font color='#dda15e'>- Mark a task to be done</font>

This command allows you to mark a task at index `TASK_INDEX` to be done.

#### Notes for the command:

- Before you `mark` a task, you are recommended to use `list` command first to make sure what the corresponding `TASK_INDEX` should be. If an invalid `TASK_INDEX` is passed, then the command won't be executed.

#### Examples of usage

- `mark /p 5`

  expected outcome:<img src="README.assets/Screenshot 2022-09-15 at 5.34.41 PM.png" alt="Screenshot 2022-09-15 at 5.34.41 PM" style="zoom:50%;" />

### `unmark /p TASK_INDEX` <font color='#dda15e'>- Unmark a task to be undone</font>

This command is the inverse of `mark`. It allows you to set the status of a task to be undone.

#### Notes for the command:

- Before you `unmark` a task, you are recommended to use `list` command first to make sure what the corresponding `TASK_INDEX` should be. If an invalid `TASK_INDEX` is passed, then the command won't be executed.

#### Examples of usage

- `unmark /p 5 `

  expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 5.50.18 PM.png" alt="Screenshot 2022-09-15 at 5.50.18 PM" style="zoom:50%;" />

### `find /p KEYWORDS` <font color='#dda15e'>- Find task containing keywords</font>

This command allows you to find tasks that contain your keywords in the content.

#### Notes for the command:

- `KEYWORDS` should be **at least one word** and **at most three words**.

#### Examples of usage

- `find /p CS2103T`

  expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 5.57.35 PM.png" alt="Screenshot 2022-09-15 at 5.57.35 PM" style="zoom:50%;" />



### `sort` <font color='#dda15e'>- Sort you events and deadlines by time</font>

This command allows you to sort all your events and deadlines by time.

#### Examples of usage:

- `sort`

  expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 6.04.57 PM.png" alt="Screenshot 2022-09-15 at 6.04.57 PM" style="zoom:50%;" />

### `reminder` <font color='#dda15e'>- List all the upcoming deadlines</font>

This command help to remind you of all your upcoming deadlines.

#### Examples of usage

- `reminder`

  expected outcome: <img src="README.assets/Screenshot 2022-09-15 at 6.06.53 PM.png" alt="Screenshot 2022-09-15 at 6.06.53 PM" style="zoom:50%;" />

### `exit` <font color='#dda15e'>- Exit the App</font>

This command allows you to exit our app.
