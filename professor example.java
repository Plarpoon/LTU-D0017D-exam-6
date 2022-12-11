import java.util.*;

// What are arrays -> https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html
// Arrays class -> https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
// Bubble Sort -> https://en.wikipedia.org/wiki/Bubble_sort
// Arrays and ArrayList -> You cannot use that in this course.

class Main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Random rand = new Random(); // instance of random class used for random numbers
        int upperBound = 25;
        int someVariable = 10;
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++)
            numbers[i] = rand.nextInt(upperBound);

        System.out.println("Some Variable = " + someVariable);

        System.out.println("Numbers before sorting: ");
        // for(int i = 0; i < numbers.length; i++)
        // System.out.println("numbers[" + i + "] = " + numbers[i]);
        printArray(numbers);

        sort(numbers, someVariable);
        // Arrays.sort(numbers);

        // numbers = expandArray()

        System.out.println("Numbers after sorting: ");
        printArray(numbers);

        System.out.println("Some Variable = " + someVariable);

    }

    public static void printArray(int[] toPrint) {
        for (int i = 0; i < toPrint.length; i++)
            System.out.print(toPrint[i] + " ");
        System.out.println(" ");
    }

    public static void sort(int[] toBeSorted, int someValue) {
        int temp = 0;
        someValue = 20;
        for (int i = 0; i < toBeSorted.length; i++) {
            System.out.print("Pass " + i + ": ");
            for (int j = i + 1; j < toBeSorted.length; j++) {
                if (toBeSorted[i] > toBeSorted[j]) {
                    temp = toBeSorted[i];
                    toBeSorted[i] = toBeSorted[j];
                    toBeSorted[j] = temp;
                }
            }
            printArray(toBeSorted);
            System.out.println("Temp = " + temp);
            int find = Arrays.binarySearch(toBeSorted, 15);
            if (find < 0)
                System.out.println("Key not Found");
            else
                System.out.println("Key Found at  = " + find);
        }
    }

    // expandArray(numbers, numbers.length + 10)
    public static int[] expandArray(int[] num, int size) {
        int[] expandedArray = new int[size]; // Create a new array of required size

        // Copy contents from old array to new array
        // Tip: Alternatively, you can use System.arrayCopy()
        for (int i = 0; i < num.length; i++) {
            expandedArray[i] = num[i];
            // if (num[i] == 0)
            // mobe the values
        }

        // return new array
        return expandedArray;

    }

    // insertArticle(articles)
    // Check if articles array is full (check for number of zeros)
    // if not full, then insert directly at the first place where it is zero
    // if full, then create new array and return the new array and use that to add
    // new article
    // OR another way
    // Step 1: articles = checkFull()
    // Step 2: You can just go to first place where it is zero and add article
    // there,

    // checkFull

}