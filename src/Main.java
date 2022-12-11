import java.util.Date;
import java.util.Scanner;

class Main {

  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int[][] articles = new int[10][2];
    int[] articleNumber = new int[1000];
    int[] noOfArticles = new int[1000];

    while (true) {
      int choice = menu();

      switch (choice) {
        case 1:
          int articleArray[][];

          System.out.println("DEBUG: Enter articles");
          articleArray = insertArticles(articles, articleNumber, noOfArticles);

          for (int i = 0; i < articleArray.length; i++) {
            System.out.println("DEBUG: " + articleArray[i][0]);
            System.out.println("DEBUG: " + articleArray[i][1]);
            System.out.println("DEBUG: " + articleArray[i][2]);
          }
          break;
        case 2:
          System.out.println("Delete article");
          break;
        case 3:
          System.out.println("View articles");
          break;
        case 4:
          System.out.println("Sales");
          break;
        case 5:
          System.out.println("Order history");
          break;
        case 6:
          System.out.println("Sort order history table");
          break;
        case 7:
          System.out.println("Exit");
          break;
        default:
          System.out.println("Invalid input, try again");
          break;
      }
    }
  }

  public static int menu() {
    System.out.println("1. Enter articles");
    System.out.println("2. Delete article");
    System.out.println("3. View articles");
    System.out.println("4. Sales");
    System.out.println("5. Order history");
    System.out.println("6. Sort order history table");
    System.out.println("7. Exit");
    System.out.println("Your choice: ");

    int choice = input();

    return choice;
  }

  public static int input() {
    int input = 0;

    while (true) {
      if (scanner.hasNextInt()) {

        // Use Math.abs() to avoid negative numbers
        input = Math.abs(scanner.nextInt());
        break;
      } else {

        System.out.println("Invalid input, try again");
        scanner.next();
      }
    }

    return input;
  }

  public static int[][] insertArticles(int[][] articles, int[] articleNumber, int[] noOfArticles) {

    int input;
    int currentSizeOfTable;

    currentSizeOfTable = articles.length;

    // Insert how many articles you want to add
    System.out.println("How many articles do you want to add?");
    input = input();

    if (input > currentSizeOfTable) {
      System.out.println("The table is full");

      // Increase the size of the table
      articles = new int[currentSizeOfTable + input][3];

      // Copy the old table to the new table
      for (int i = 0; i < currentSizeOfTable; i++) {
        articles[i][0] = articleNumber[i];
        articles[i][1] = noOfArticles[i];
      }
    }

    // Insert the new articles
    for (int i = currentSizeOfTable; i < articles.length; i++) {
      System.out.println("Enter article number");
      articles[i][0] = input();

      System.out.println("Enter quantity");
      articles[i][1] = input();

      System.out.println("Enter price");
      articles[i][2] = input();
    }

    return articles;
  }

  public static int[][] checkFull(int[][] articles, int noOfArticles) {
    int[][] newArticles = new int[10][3];

    if (noOfArticles == 10) {
      System.out.println("The table is full");
      return newArticles;
    } else {
      return articles;
    }
  }

  public static void removeArticle(int[][] articles) {
    int input;

    System.out.println("Enter item number");
    input = input();

    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == input) {
        articles[i][0] = 0;
        articles[i][1] = 0;
        articles[i][2] = 0;
      }
    }
  }

  public static void printArticles(int[][] articles) {
    for (int i = 0; i < articles.length; i++) {
      System.out.println("Item number: " + articles[i][0]);
      System.out.println("Quantity: " + articles[i][1]);
      System.out.println("Price: " + articles[i][2]);
    }
  }

  public static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
    int input;

    System.out.println("Enter item number");
    input = input();

    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == input) {
        sales[i][0] = articles[i][0];
        sales[i][1] = articles[i][1];
        sales[i][2] = articles[i][2];
        salesDate[i] = new Date();
      }
    }
  }

  public static void printSales(int[][] sales, Date[] salesDate) {
    for (int i = 0; i < sales.length; i++) {
      System.out.println("Item number: " + sales[i][0]);
      System.out.println("Quantity: " + sales[i][1]);
      System.out.println("Price: " + sales[i][2]);
      System.out.println("Date: " + salesDate[i]);
    }
  }

  public static void sortedTable(int[][] sales, Date[] salesDate) {
    int[][] sortedSales = new int[10][3];
    Date[] sortedSalesDate = new Date[10];

    for (int i = 0; i < sales.length; i++) {
      for (int j = 0; j < sales.length; j++) {
        if (sales[i][0] < sales[j][0]) {
          sortedSales[i][0] = sales[j][0];
          sortedSales[i][1] = sales[j][1];
          sortedSales[i][2] = sales[j][2];
          sortedSalesDate[i] = salesDate[j];
        }
      }
    }

    printSales(sortedSales, sortedSalesDate);
  }
}