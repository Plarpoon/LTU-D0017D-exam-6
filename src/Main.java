import java.util.Date;
import java.util.Scanner;

class Main {

  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int articleNumber = 1000;
    int noOfArticles = 10;
    int price;
    int ID;
    int sum;

    Date[] salesDate;
    int[][] articles = new int[noOfArticles][articleNumber];
    int[][] sales;

    while (true) {
      int choice = menu();

      switch (choice) {
        case 1: // Enter articles.

          // Number of new articles to add
          int newItems;

          System.out.println("How many articles would you like to add?");
          newItems = input();

          // Check if there is enough space in the matrix
          if (articles.length < newItems) {
            // Create a new matrix with the new size
            int[][] NewArticles;

            // Create a new matrix with the new size
            NewArticles = new int[articles.length + newItems][articleNumber];

            // Copy the old matrix to the new one
            for (int i = 0; i < articles.length; i++) {
              for (int j = 0; j < articles[i].length; j++) {
                NewArticles[i][j] = articles[i][j];
              }
            }

            // Assign the new matrix to the old one
            articles = NewArticles;
          }

          // Enter the article number, quantity and price
          for (int i = articleNumber; i < articleNumber + newItems; i++) {
            System.out.println("Enter article ID number: ");
            articles[i][0] = input();

            System.out.println("Enter quantity: ");
            articles[i][1] = input();

            System.out.println("Enter price: ");
            while (true) {
              price = input();
              if (price < 1000) {
                System.out.println("Price must be at least 1000 SEK");
              } else {
                break;
              }
            }
            articles[i][2] = input();

            // Increment the article number
            articleNumber += newItems;

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
          System.exit(0);
          break;
        default:
          System.out.println("Invalid input, try again");
          break;
      }
    }
  }

  private static int menu() {
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

  private static int input() {
    int input = 0;

    while (true) {
      if (scanner.hasNextInt() & (input = scanner.nextInt()) > 0) {
        return input;
      } else {
        System.out.println("Invalid input, try again");
        scanner.next();
      }
    }
  }

  private static int[][] insertArticles(int[][] articles, int[] articleNumber, int[] noOfArticles) {

    return articles;
  }

  private static int[][] checkFull(int[][] articles, int noOfArticles) {

    return articles;
  }

  private static void removeArticle(int[][] articles) {
  }

  private static void printArticles(int[][] articles) {
  }

  private static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
  }

  private static void printSales(int[][] sales, Date[] salesDate) {
  }

  private static void sortedTable(int[][] sales, Date[] salesDate) {
  }
}