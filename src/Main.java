
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
     int[][] articles = new int[noOfArticles][articleNumber]; // using variables for array size
     int[][] sales = new int[noOfArticles][articleNumber];
 
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
       // Check if input is an integer.
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
 
     // Add articles to the matrix.
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
 
     // Return old matrix if there is enough space.
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
 
   private static Date[] sellArticleDate(int[][] articles, Date[] salesDate, int articleToSell, int quantityToSell,
       int[][] sales) {
     // Create new salesDate array with same size as sales.
     Date[] newSalesDate = new Date[sales.length];
 
     // Copy old salesDate array to new salesDate array.
     for (int i = 0; i < salesDate.length; i++) {
       newSalesDate[i] = salesDate[i];
     }
 
     // Update salesDate array.
     for (int i = 0; i < articles.length; i++) {
       if (articles[i][0] == articleToSell) {
         if (articles[i][1] >= quantityToSell) {
           newSalesDate[i] = new Date(System.currentTimeMillis());
         }
       }
     }
 
     return newSalesDate;
   }
 
   public static int[][] sellArticle(int[][] sales, Date[] salesDate, int[][] articles, int articleToSell,
       int quantityToSell) {
 
     // Create new sales matrix with same size as articles.
     int[][] newSales = new int[articles.length][2];
 
     // Copy old sales matrix to new sales matrix.
     for (int i = 0; i < articles.length; i++) {
       newSales[i][0] = sales[i][0]; // Copy article number.
       newSales[i][1] = sales[i][1]; // Copy quantity.
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
     return newSales;
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
 
   // Generate random number.
   public static int randomNumber(int min, int max) {
     Random random = new Random();
     return random.nextInt(max - min) + min;
   }
 
   // Generate random article number.
   public static int quantity() {
     int nrOfPieces = randomNumber(1, 10);
     return nrOfPieces;
   }
 
   // Generate random price.
   public static int price() {
     int price = randomNumber(100, 1000);
     return price;
   }
 }