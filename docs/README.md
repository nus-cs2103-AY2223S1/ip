# User Guide
Welcome to Duke! Duke is your personal assistant chatbot that helps you efficiently manage your tasks!
Let's get started!

## Features 
Duke can help you to:
1. Add tasks to your list
- To Do Tasks
- Deadline Tasks
- Event Tasks
2. Update the completion status of these tasks
2. View all your tasks along with their type and completion status in a list format
4. Sort your list of tasks to view all Deadlines in chronological order
5. Filter your list according to a particular keyword
6. Delete a particular task

## Usage

### Add tasks to your list 

All tasks will be marked as uncompleted upon creation. 

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

### Update completion status
Syntax: `mark [num]` to mark as completed.

Syntax: `unmark [num]` to mark as uncompleted.

`num` represents the index of the task in the list.

Example of usage:

`mark 2` `unmark 1`

### View list
Syntax: `view`

### Sort list
Syntax: `sort`

### Filter list 
Syntax: `find [keyword]`

You can only filter by a `keyword` in the main task description (not after /by or /at).

Example of usage: 

`find book`

### Delete task
Syntax: `delete [num]`

`num` represents the index of the task in the list. 

Example usage: 

`delete 3` 
