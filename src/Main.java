
/**
 * ===============================================================
 * What does the program do:
 * Must create a program that works as a sales register. The program
 * must be able to add articles, delete articles, view articles, sell
 * articles, print sales history, and sort the sales history.
 * ===============================================================
 * Pseudocode steps:
 * 1. Print the menu
 * 2. Read the input
 * 3. Use a switch-case to access the different functions
 * 4. Add every method the professor provided
 * 5. Prepare random generators for the articles
 * 6. Check that the methods described by professor work like he said
 * ===============================================================
 * Alessandro Suha
 * alesuh-1
 */

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    final int articleNumber = 1000; // or ID
    final int noOfArticles = 10; // or quantity

    Date[] salesDate = new Date[0];
    int[][] articles = new int[noOfArticles][articleNumber];
    int[][] sales = new int[noOfArticles][articleNumber];

    while (true) {
      int choice = menu();

      switch (choice) {
        case 1: // Enter articles.
          articles = insertArticle(articles);
          break;
        case 2: // Delete article.
          removeArticle(articles);
          break;
        case 3: // View articles.
          printArticles(articles);
          break;
        case 4: // Sell articles.
          System.out.println("\nWhich article do you want to sell? ");
          int articleToSell = input();

          System.out.println("\nHow many do you want to sell? ");
          int quantityToSell = input();

          sales = sellArticle(sales, salesDate, articles, articleToSell, quantityToSell);
          salesDate = sellArticleDate(articles, salesDate, articleToSell, quantityToSell, sales);
          break;
        case 5: // Print sales.
          printSales(sales, salesDate, articles);
          break;
        case 6: // Sort sale history.
          sortedTable(sales, salesDate, articles);
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
    System.out.println("5. Sell history");
    System.out.println("6. Sort sell history table");
    System.out.println("7. Exit");
    System.out.println("Your choice: ");

    int choice = input();

    return choice;
  }

  public static int input() {
    int input = 0;

    while (true) {
      // Check if input is a natural integer positive number.
      if (scanner.hasNextInt() && (input = scanner.nextInt()) > 0) {
        return input;
      } else {
        System.out.println("Invalid input, try again");
        scanner.next();
      }
    }
  }

  public static int[][] insertArticle(int[][] articles) {
    System.out.println("\nPlease enter the number of articles you would like to add: ");
    int noOfArticlesToAdd = input();

    int highestArticleNumber = 1000;

    // Find the highest article number in the array
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] > highestArticleNumber) {
        highestArticleNumber = articles[i][0];
      }
    }

    for (int i = 0; i < noOfArticlesToAdd; i++) {
      // Generate random values for quantity and price.
      int newQuantity = generateQuantity();
      int newPrice = generatePrice();

      // Add new values to the articles array.
      articles = checkFull(articles);
      for (int j = 0; j < articles.length; j++) {
        if (articles[j][0] == 0) {
          articles[j][0] = highestArticleNumber + i + 1; // New article number is the highest article number + i + 1
          articles[j][1] = newQuantity;
          articles[j][2] = newPrice;
          break;
        }
      }
    }

    return articles;
  }

  public static int[][] checkFull(int[][] articles) {
    // Check if the articles array is full. If it is, create a new array with double
    // the size.
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == 0) {
        break;
      }
      if (i == articles.length - 1) {
        int[][] newArticles = new int[articles.length * 2][articles[0].length];
        for (int j = 0; j < articles.length; j++) {
          for (int k = 0; k < articles[0].length; k++) {
            newArticles[j][k] = articles[j][k];
          }
        }
        articles = newArticles;
      }
    }
    return articles;
  }

  public static void removeArticle(int[][] articles) {
    System.out.println("\nWhich article do you want to remove? ");
    int articleToRemove = input();

    // Remove article from the matrix.
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

  public static Date[] sellArticleDate(int[][] articles, Date[] salesDate, int articleToSell, int quantityToSell,
      int[][] sales) {
    // Find the index of the article being sold in the articles array.
    int articleIndex = -1;
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == articleToSell) {
        articleIndex = i;
        break;
      }
    }

    if (articleIndex == -1) {
      // Article not found in the articles array.
      System.out.println("Article not found");
      return salesDate;
    }

    // Check if the salesDate array is large enough to hold the new sale.
    if (articleIndex >= salesDate.length) {
      // Increase the size of the salesDate array.
      Date[] newSalesDate = new Date[articleIndex + 1];
      System.arraycopy(salesDate, 0, newSalesDate, 0, salesDate.length);
      salesDate = newSalesDate;
    }

    // Update the salesDate array with the date of the sale.
    salesDate[articleIndex] = new Date();

    return salesDate;
  }

  public static int[][] sellArticle(int[][] sales, Date[] salesDate, int[][] articles, int articleToSell,
      int quantityToSell) {
    // Find the index of the article being sold in the articles array.
    int articleIndex = -1;
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == articleToSell) {
        articleIndex = i;
        break;
      }
    }

    if (articleIndex == -1) {
      // Article not found in the articles array.
      System.out.println("Article not found");
      return sales;
    }

    // Check if the sales array is large enough to hold the new sale.
    if (articleIndex >= sales.length) {
      // Increase the size of the sales array.
      int[][] newSales = new int[articleIndex + 1][2];
      System.arraycopy(sales, 0, newSales, 0, sales.length);
      sales = newSales;

      // Increase the size of the salesDate array.
      Date[] newSalesDate = new Date[articleIndex + 1];
      System.arraycopy(salesDate, 0, newSalesDate, 0, salesDate.length);
      salesDate = newSalesDate;
    }

    // Check if the requested quantity is greater than the current quantity.
    if (quantityToSell > articles[articleIndex][1]) {
      // Sell the remaining quantity.
      quantityToSell = articles[articleIndex][1];
    }

    // Update the sales array with the ID and quantity of the sold article.
    sales[articleIndex][0] = articleToSell;
    sales[articleIndex][1] += quantityToSell;

    // Decrement the quantity of the sold article in the articles array.
    articles[articleIndex][1] -= quantityToSell;

    // Check if the quantity of the sold article is 0 or negative.
    if (articles[articleIndex][1] <= 0) {
      // Remove the article from the articles array.
      int[][] newArticles = new int[articles.length - 1][2];
      System.arraycopy(articles, 0, newArticles, 0, articleIndex);
      System.arraycopy(articles, articleIndex + 1, newArticles, articleIndex, articles.length - articleIndex - 1);
      articles = newArticles;

      // Remove the date for the article from the salesDate array.
      Date[] newSalesDate = new Date[salesDate.length - 1];
      System.arraycopy(salesDate, 0, newSalesDate, 0, articleIndex);
      System.arraycopy(salesDate, articleIndex + 1, newSalesDate, articleIndex, salesDate.length - articleIndex - 1);
      salesDate = newSalesDate;
    }

    return sales;
  }

  public static void printSales(int[][] sales, Date[] salesDate, int[][] articles) {
    // Print sales if salesDate is not null.
    for (int i = 0; i < salesDate.length; i++) {
      if (salesDate[i] != null) {
        System.out.println("Article number: " + sales[i][0]);
        System.out.println("Quantity: " + sales[i][1]);
        System.out.println("Date: " + salesDate[i]);
        System.out.println();
      }
    }
  }

  public static void sortedTable(int[][] sales, Date[] salesDate, int[][] articles) {
    // Bubble sort articles matrix.
    // Sort by article number.
    // Then print sales with article number, quantity and date.
    int temp;
    for (int i = 0; i < articles.length; i++) {
      for (int j = 1; j < (articles.length - i); j++) {
        if (articles[j - 1][0] > articles[j][0]) {
          // Swap article number.
          temp = articles[j - 1][0];
          articles[j - 1][0] = articles[j][0];
          articles[j][0] = temp;

          // Swap quantity.
          temp = articles[j - 1][1];
          articles[j - 1][1] = articles[j][1];
          articles[j][1] = temp;

          // Swap price.
          temp = articles[j - 1][2];
          articles[j - 1][2] = articles[j][2];
          articles[j][2] = temp;
        }
      }
    }

    // Print sales if salesDate is not null.
    for (int i = 0; i < salesDate.length; i++) {
      if (salesDate[i] != null) {
        System.out.println("Article number: " + sales[i][0]);
        System.out.println("Quantity: " + sales[i][1]);
        System.out.println("Date: " + salesDate[i]);
        System.out.println();
      }
    }
  }

  public static int generateArticleNumber() {
    // Generate random article number starting at 1000 and increasing by 1.
    Random random = new Random();
    int newArticleNumber = random.nextInt(10) + 1000;
    return newArticleNumber;
  }

  public static int generateQuantity() {
    // Generate random quantity between 1 and 10.
    Random random = new Random();
    int newQuantity = random.nextInt(10) + 1;
    return newQuantity;
  }

  public static int generatePrice() {
    // Generate random price between 1 and 1000.
    Random random = new Random();
    int newPrice = random.nextInt(1000) + 1;
    return newPrice;
  }
}