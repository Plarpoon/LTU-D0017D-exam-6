import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class Main {

  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int articleNumber = 1000; // or ID
    int noOfArticles = 10; // or quantity

    Date[] salesDate = new Date[articleNumber];
    int[][] articles = new int[noOfArticles][articleNumber]; // using variables for array size
    int[][] sales = new int[noOfArticles][articleNumber];

    while (true) {
      int choice = menu();

      switch (choice) {
        case 1: // Enter articles.
          insertArticle(articles, articleNumber, noOfArticles);
          break;
        case 2: // Delete article.
          removeArticle(articles);
          break;
        case 3: // View articles.
          printArticles(articles);
          break;
        case 4: // Sell articles.
          sellArticle(sales, salesDate, articles);
          break;
        case 5: // Print sales.
          printSales(sales, salesDate);
          break;
        case 6: // Sort sale history.
          sortedTable(sales, salesDate);
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

  private static int[][] insertArticle(int[][] articles, int articleNumber, int noOfArticles) {
    articles = checkFull(articles, noOfArticles);
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == 0) {
        ++articleNumber;
        articles[i][0] = articleNumber;
        articles[i][1] = quantity();
        articles[i][2] = price();
        break;
      }
    }
    return articles;
  }

  private static int[][] checkFull(int[][] articles, int noOfArticles) {
    System.out.println("Please enter the number of articles you would like to add: ");
    int noOfArticlesToAdd = input();

    if (noOfArticlesToAdd > noOfArticles) {
      // Expand array.
      int[][] newArticles = new int[noOfArticles + noOfArticlesToAdd][3];

      // Copy old array to new array.
      for (int i = 0; i < articles.length; i++) {
        newArticles[i][0] = articles[i][0];
        newArticles[i][1] = articles[i][1];
        newArticles[i][2] = articles[i][2];
      }
    }
    return articles;
  }

  private static void removeArticle(int[][] articles) {
    System.out.println("Which article do you want to remove? ");
    int articleToRemove = input();

    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == articleToRemove) {
        articles[i][0] = 0;
        articles[i][1] = 0;
        articles[i][2] = 0;
        break;
      }
    }
  }

  private static void printArticles(int[][] articles) {
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] != 0) {
        System.out.println("Article number: " + articles[i][0]);
        System.out.println("Quantity: " + articles[i][1]);
        System.out.println("Price: " + articles[i][2]);
      }
    }
  }

  private static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
    System.out.println("Which article do you want to sell? ");
    int articleToSell = input();

    System.out.println("How many do you want to sell? ");
    int quantityToSell = input();

    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == articleToSell) {
        if (articles[i][1] >= quantityToSell) {
          articles[i][1] -= quantityToSell;
          sales[i][0] = articleToSell;
          sales[i][1] += quantityToSell;
          salesDate[i] = new Date();
          break;
        } else {
          System.out.println("Not enough articles in stock");
          break;
        }
      }
    }
  }

  private static void printSales(int[][] sales, Date[] salesDate) {
    for (int i = 0; i < sales.length; i++) {
      if (sales[i][0] != 0) {
        System.out.println("Article number: " + sales[i][0]);
        System.out.println("Quantity: " + sales[i][1]);
        System.out.println("Date: " + salesDate[i]);
      }
    }
  }

  private static void sortedTable(int[][] sales, Date[] salesDate) {
    // Sort sales by article number.
    for (int i = 0; i < sales.length; i++) {
      for (int j = 0; j < sales.length; j++) {
        if (sales[i][0] < sales[j][0]) {
          int temp = sales[i][0];
          sales[i][0] = sales[j][0];
          sales[j][0] = temp;
        }
      }
    }
  }

  private static int randomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  public static int quantity() {
    int nrOfPieces = randomNumber(1, 10);
    return nrOfPieces;
  }

  public static int price() {
    int price = randomNumber(100, 1000);
    return price;
  }
}