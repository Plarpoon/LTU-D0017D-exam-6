# Assignment 6 - Cash register

## Task

You must create a program that works as a simple cash register system. In the program you should be able to add / remove articles, sell articles and present sales history.

Via a text-based menu, the user can choose what he / she wants to do, the menu should have the following options:

```generic
1. Enter articles
2. Delete article
3. View articles
4. Sales
5. Order history
6. Sort order history table
7. Exit
Your choice:
```

## Datastructure / Database

You need to store data in several data structures / databases. You need an array to store items (integer) [item number, quantity, price], an array to store sales (integer) [item number, number, sum] and an array (Date) that stores time / date for each sale. Dates must be on the same index as the corresponding sales.

The initial size should hold 10 items. Before adding new articles, the program should ask the user how many articles to add and test if the data structure holds the desired number of articles, otherwise the data structure should be expanded to accommodate the new articles. Article number should start at 1000 and increase by 1 for each new article. The number of sales can be fixed (at least 1000).

Also, remember to check that the entered values ​​are valid. All input from the user must be done by the intended method.

You may be able to find alternative solutions, but the task is formulated so that it tests certain important steps and therefore it should be solved in the manner specified below. You will practice using arrays/matrices and sending/receiving variables to/from methods.

Begin the task by constructing a flow chart and program logic on paper. Then describe your structure in text form (pseudocode) in the comment header of the program and in the method comments so that an outsider can understand what the program does and how your logic/algorithm works.

It is not allowed to use e.g. ArrayList or similar classes, nor can global variables (variables created outside the methods) be used in the program except for the Scanner object to be created in the class body (see Main.java)

### The signatures of the methods must be

```java
public static int menu()
public static int input()
public static int[][] insertArticles (int[][]articles, int articleNumber, int noOfArticles)
public static int[][] checkFull(int[][]articles, int noOfArticles)
public static void removeArticle (int[][]articles)
public static void printArticles (int[][]articles)
public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles)
public static void printSales(int[][]sales, Date[] salesDate)
public static void sortedTable(int[][]sales,  Date[] salesDate)
```

### Tasks of the methods

```java
menu()
```

- Presents the menu, loads, and returns the user's selection.

```java
input()
```

- All input from the user must be done by this method. The method waits for input and returns the integer entered by the user. The method must also handle entries that are not an integer and as long as the user enters something other than a valid integer, he will have a new chance to enter an integer.

```java
insertArticle(int[][] articles, int articleNumber, int noOfArticles)
```

- Adds the specified number of items in the matrix after first checking that the number of vacancies is sufficient. Each item is added to the first vacant position. Article number is based on input parameters (last used article number) and increases by one for each new article. Number of units of resp. article is randomly selected (1-10 pcs) and the article price (SEK 100-1000 / pc). The method returns either the new matrix or the original one.

```java
checkFull (int[][] articles, int noOfArticles)
```

- Checks if the matrix holds the specified number of new items. If necessary, a new matrix is ​​created that also holds the new articles and existing articles are copied over. The method returns either the new matrix or the original one.

```java
removeArticle (int[][] articles)
```

- Asks the user for the item number to be removed. All fields for this article are set to 0.

```java
printArticles (int[][] articles)
```

- Prints article number, number, and price for all articles in articles that have an article number. The printout must be sorted into ascending article numbers.

```java
sellArticle(int[][] sales, Date[] salesDate, int[][] articles)
```

- Asks for article number and number of items to be sold. If there are enough goods in stock, the number of goods is reduced and a sale is registered in the intended matrix (article number, number, sum). The date and time of the transaction are saved in the dateTime array at the corresponding position.

```java
public static void printSales (int[][] sales, Date[] salesDate)
```

- Prints all sales transactions with date, item number, number and amount.

```java
public static void sortedTable(int[][]sales, Date[] salesDate)
```

- sort the selling table by article number, in ascending order. The program shall print the sorted table, that keeps the correct information about the time and price of the sold articles.

You may create additional methods if justified (eg functionality that reappears in several places in the program)

## Error checks

All inputs in the program should be integers. However, entering anything other than integers should not crash the program, incorrect entry should result in the user having to enter a new selection.

You should not be able to add, delete or sell 0 items. Likewise, the price should not be able to be SEK 0.

The number of an individual item should never be less than 0, the program should not allow residual listing.

## Tips

When you submit an array as an input parameter to a method, it is a reference to the memory location that is copied over to the method. All changes we make to this copy (eg add new values) are "visible" even for the original reference in the main method as long as we do not create a new memory space (change reference to memory space). If we need to create a new memory space, the new reference needs to be returned to main and replace the previous reference.

To copy values ​​between two arrays, you can create a loop and copy one value at a time or use Java's built-in function:

```java
System.arraycopy()
```
