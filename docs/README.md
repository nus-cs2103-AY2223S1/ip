# User Guide
Welcome to Duke! Duke is your personal assistant chatbot that helps you efficiently manage your tasks!
Let's get started!

## Features 
Duke can help you to:
1. Add tasks to your list
- To Do Tasks (just basic tasks)
- Deadline Tasks (tasks with deadlines)
- Event Tasks (events occurring at a certain time)
2. Update the completion status of these tasks
2. View all your tasks along with their type and completion status in a list format
4. Sort your list of tasks to view all Deadline Tasks in chronological order
5. Filter your list according to a particular keyword
6. Delete a particular task

## Usage

### Add Tasks

All tasks will be marked as uncompleted upon creation.

All tasks are automatically saved upon creation.

#### To Do Task

Syntax: `todo [description]`

Example of usage: 

`todo read book`

#### Deadline Task

Syntax: `deadline [description] /by [date]`

`date` has to be in the format YYYY-MM-DD.

Example of usage: 

`deadline return book /by 2022-04-28`

#### Event Task

Syntax: `event [description] /at [date/time]`

Example of usage: 

`event book session /at Monday 3pm`

### Update Task Completion Status

Syntax: `mark [num]` to mark as completed.

Syntax: `unmark [num]` to mark as uncompleted.

`num` represents the index of the task in the list.

Example of usage:

`mark 2` `unmark 1`

### View List
Syntax: `view`

### Sort List

Syntax: `sort`

Sorting the list does not change the original list; it simply displays it in a different format for you. 

Hence, all subsequent operations should still be done in reference to the original list. 

Deadline Tasks are prioritised over all other tasks without deadlines and displayed in chronological order. 

All other tasks' relative positions will stay the same. 

### Filter List 
Syntax: `find [keyword]`

You can only filter by a `keyword` in the main task description (not after /by or /at).

Example of usage: 

`find book`

### Delete Task
Syntax: `delete [num]`

`num` represents the index of the task in the list. 

Example usage: 

`delete 3` 

That's all the features for now! Hope you have fun using Duke!
