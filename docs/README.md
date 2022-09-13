# User Guide
Welcome to Duke, your personal task manager!

## Features 
1. Add tasks such as todos, events and deadlines to your list!
2. Search for any task in the list that you need.
3. Mark a task as done or unmark it if it is undone.
4. Delete any unwanted tasks!
5. Duplication detection where you will be warned if there is a duplicate task added to the list.

### `todo <description>`
Adds a todo task with the description as its content into your list.

Example of usage: 

`todo cs2103t assignment`

Expected outcome:

![todoExample](https://user-images.githubusercontent.com/93211040/189959478-a5d86a7a-cd5f-4d6a-900e-39d6da2a09d9.png)


### `event <description> /at <date>`
Adds an event task with the description and date as its content into your list.
Date is in yyyy-mm-dd format.

Example of usage: 

`event picnic /at 2022-08-12`

Expected outcome:

![eventExample](https://user-images.githubusercontent.com/93211040/189959657-51f47f5c-2633-487f-ab53-87bd6f2f52ef.png)


### `deadline <description> /by <date>`
Adds a deadline task with the description and date as its content into your list.
Date is in yyyy-mm-dd format.

Example of usage: 

`deadline hackathon /by 2022-09-25`

Expected outcome:

![deadlineExample](https://user-images.githubusercontent.com/93211040/189959714-4c6027ac-816a-4f16-aeb3-6028f81fa44f.png)


### `delete <index>`
Deletes the task with that index on your list.

Example of usage: 

`delete 2`

Expected outcome:

![deleteExample](https://user-images.githubusercontent.com/93211040/189959885-14cb2120-bad4-4fc9-ad65-9ca652b1b1d1.png)


### `list`
Lists all the tasks currently in your list.

Example of usage: 

`list`

Expected outcome:

![listExample](https://user-images.githubusercontent.com/93211040/189959939-ec47a563-5741-4791-ba93-c2a8b9704d17.png)


### `mark <index>`
Marks the task of that index in your list with a cross, indicating it is done.

Example of usage: 

`mark 2`

Expected outcome:

![markExample](https://user-images.githubusercontent.com/93211040/189960014-3588c5cd-1d7e-42d0-bc47-5051f049a74a.png)


### `unmark <index>`
Unmarks the task of that index in your list if it is marked, indicating it is done.

Example of usage: 

`unmark 2`

Expected outcome:

![unmarkExample](https://user-images.githubusercontent.com/93211040/189960052-502bb7b6-0a85-4019-a147-d3b756932352.png)


### `find <description>`
Finds the task in your list that matches the descriptiom.

Example of usage: 

`find breakfast`

Expected outcome:

![findExample](https://user-images.githubusercontent.com/93211040/189960107-91b669a6-f9df-4f31-8eaf-4752c19286e1.png)

