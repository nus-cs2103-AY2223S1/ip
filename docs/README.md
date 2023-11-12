# Wanya Bot User Guide
Wanya Bot is a chat bot that helps you keep track of tasks easily and efficiently. 😄

## Features 

Notes about the usage format
- Words within <> are parameters to be inputted by the users.



Wanya Bot is able to store 4 types of tasks. 

1. ToDo: tasks without any date/time attached to it e.g., visit new theme park
2. Deadline: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2022 5pm
3. Event: tasks that will be happening on the specfic date/time e.g., team project meeting on 2/10/2022 2pm
4. Period: tasks that start at a specific date/time and ends at a specific date/time e.g., cs2103 lecture from 16/9/2022 4pm to 16/9/2022 6pm

### 1. Add a task

Adds a task, any of the 4 types mentioned above, to store in Wanya Bot. 

The task added will be keep tracked for completeness. 
For deadline, event and period, Wanya Bot will keep track of additional date time information specific to the type of tasks.

#### Usage
`todo <description>` - Adds a ToDo task with the description. 

`deadline <description> /by <date/time>` - Adds a Deadline task with the description and due date of date/time.

`event <description> /at <date/time>` - Adds a Event task with the description and event date of date/time.

`period <description> /from <date/time> to <date/time>` - Adds a Period task with the description and starting date/time and ending date/time.


##### <date/time> format
The date/time format accepted will be of type
`"YYYY-MM-DD HH:mm" or "YYYY-MM-DD"` 

where YYYY is the year, MM is the month in number, DD is the day, HH is the hour and mm is the minute. **Time is optional but a date must be provided!**


Example of usage: `todo 100 push ups` 

Expected outcome: Adds a todo task with description 100 push ups

Example of usage: `deadline earn a stella star /by 2022-10-23`

Expected outcome: Adds a deadline task with description earn a stella star by Oct 23 2022 12:00 am

Example of usage: `event sports day /at 2022-09-30 13:00` 

Expected outcome: Adds a event task with description sports day at Sep 30 2022 01:00 pm

Example of usage: `period recess week /from 2022-09-17 to 2022-09-25 23:59`

Expected outcome: Adds a period task with description recess week from Sep 17 2022 12:00am to Sep 25 2022 11:59 pm

![image](https://user-images.githubusercontent.com/78785369/190052466-69c8746a-3887-4994-9d5f-61e931df3686.png)


### 2. List all tasks

Display all the tasks currently in task list and their completedness. 
The first [ ] represents the task type, and the second [ ] represents the completedness. If the task is completed, it will be displayed as [X], otherwise, it will be [ ].


#### Usage
`list`

![image](https://user-images.githubusercontent.com/78785369/190053677-69d52b21-62d0-4ffd-a5cb-d8cac23459ba.png)

### 3. Mark task

Marks the task as completed and display that task.

#### Usage
`mark <index>` - Marks the task at index as completed. 

Example of usage: `mark 4` - Marks the task at index 4 as completed.
![image](https://user-images.githubusercontent.com/78785369/190054158-75716176-12ad-4ca1-ad7f-ff01ef2f5dd8.png)


### 4. Unmark task

Marks the task as uncompleted and display that task.

#### Usage
`unmark <index>` - Unmarks the task at index as uncompleted.

Example of usage: `unmark 4` - Marks the task at index 4 as uncompleted.
![image](https://user-images.githubusercontent.com/78785369/190054483-62dbaee2-18f4-4c7b-b3d5-d09d7d3cec0e.png)

### 5. Delete task

Deletes the tasks from task list.

#### Usage
`delete <index>` - Deletes the task at index from task list.

Example of usage: `delete 1` - Delete the task at index 1.

![image](https://user-images.githubusercontent.com/78785369/190054904-82216cb2-29a3-4bb3-afda-798d59dff8c1.png)

### 6. Find task

Finds the task by searching for a keyword in the task description.

#### Usage
`find <keyword>` - Finds the tasks that contains the keyword in the task description.

Example of usage: `find 100` - Finds all tasks that contains the keyword '100'.
![image](https://user-images.githubusercontent.com/78785369/190055418-1ac86534-78a2-462b-93e2-11f7b4f8ff3f.png)

### 7. Exit

Exit from Wanya bot.

#### Usage
`bye` - Exits the program.

Expected outcome: Wanya bot will close.
