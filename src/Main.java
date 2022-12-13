import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    final int articleNumber = 1000; // or ID
    final int noOfArticles = 10; // or quantity

    Date[] salesDate = new Date[0];
    int[][] articles = new int[noOfArticles][articleNumber]; // using variables for array size
    int[][] sales = new int[0][0];

    while (true) {
      int choice = menu();

      switch (choice) {
        case 1: // Enter articles.
          articles = insertArticle(articles, articleNumber, noOfArticles);
          break;
        case 2: // Delete article.
          removeArticle(articles);
          break;
        case 3: // View articles.
          printArticles(articles);
          break;
        case 4: // Sell articles.
          sales = sellArticle(sales, salesDate, articles);
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

  public static int menu() {
    System.out.println("1. Enter articles");
    System.out.println("2. Delete article");
    System.out.println("3. View articles");
    System.out.println("4. Sell articles");
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
      if (scanner.hasNextInt() & (input = scanner.nextInt()) > 0) {
        return input;
      } else {
        System.out.println("Invalid input, try again");
        scanner.next();
      }
    }
  }

  public static int[][] insertArticle(int[][] articles, int articleNumber, int noOfArticles) {
    System.out.println("\nPlease enter the number of articles you would like to add: ");
    int noOfArticlesToAdd = input();

    for (int j = 0; j < noOfArticlesToAdd; j++) {
      articles = checkFull(articles, noOfArticles, noOfArticlesToAdd);
      for (int i = 0; i < articles.length; i++) {
        if (articles[i][0] == 0) {
          ++articleNumber;
          articles[i][0] = articleNumber;
          articles[i][1] = quantity();
          articles[i][2] = price();
          break;
        }
      }
    }
    return articles;
  }

  public static int[][] checkFull(int[][] articles, int noOfArticles, int noOfArticlesToAdd) {
    // Check how many empty or deleted articles there are in the matrix.
    int count = 0;
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == 0) {
        count++; // Amount of deleted or empty articles in the already existing matrix.
      }
    }

    if (count < noOfArticlesToAdd) {

      // Create new matrix with more space.
      int[][] newArticles = new int[noOfArticlesToAdd][3];

      // Copy old matrix to new matrix.
      for (int i = 0; i < articles.length; i++) {
        newArticles[i][0] = articles[i][0];
        newArticles[i][1] = articles[i][1];
        newArticles[i][2] = articles[i][2];
      }

      return newArticles;
    }

    return articles;
  }

  public static void removeArticle(int[][] articles) {
    System.out.println("\nWhich article do you want to remove? ");
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

  public static void printArticles(int[][] articles) {
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] != 0) {
        System.out.println("Article number: " + articles[i][0]);
        System.out.println("Quantity: " + articles[i][1]);
        System.out.println("Price: " + articles[i][2]);
        System.out.println();
      }
    }
  }

  public static int[][] sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
    System.out.println("\nWhich article do you want to sell? ");
    int articleToSell = input();

    System.out.println("\nHow many do you want to sell? ");
    int quantityToSell = input();

    // Create new sales matrix with same size as articles.
    int[][] newSales = new int[articles.length][2];

    // Copy old sales matrix to new sales matrix.
    for (int i = 0; i < sales.length; i++) {
      newSales[i][0] = sales[i][0]; // Copy article number.
      newSales[i][1] = sales[i][1]; // Copy quantity.
    }

    // Create new salesDate array with same size as articles.
    Date[] newSalesDate = new Date[articles.length];

    // Copy old salesDate array to new salesDate array.
    for (int i = 0; i < salesDate.length; i++) {
      newSalesDate[i] = salesDate[i];
    }

    // Check if article exists in articles.
    // Check if quantity is available.
    // If quantity is available, subtract quantity from articles.
    // Add article and quantity to sales.
    // Add date to salesDate.
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == articleToSell) {
        if (articles[i][1] > quantityToSell) {
          articles[i][1] -= quantityToSell;
          newSales[i][0] = articleToSell;
          newSales[i][1] += quantityToSell;
          newSalesDate[i] = new Date();
          break;
        } else if (articles[i][1] == quantityToSell) {
          // Remove article from articles.
          articles[i][0] = 0;
          articles[i][1] = 0;
          articles[i][2] = 0;
        } else {
          System.out.println("Not enough articles in stock\n");
          break;
        }
      }
    }
    return sales;
  }

  public static void printSales(int[][] sales, Date[] salesDate) {
    for (int i = 0; i < sales.length; i++) {
      if (sales[i][0] != 0) {
        System.out.println("Article number: " + sales[i][0]);
        System.out.println("Quantity: " + sales[i][1]);
        System.out.println("Date: " + salesDate[i]);
        System.out.println();
      }
    }
  }

  public static void sortedTable(int[][] sales, Date[] salesDate) {
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

  public static int randomNumber(int min, int max) {
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