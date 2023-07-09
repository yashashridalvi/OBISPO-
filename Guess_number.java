//import java.text.Format;
import java.util.Random;
import java.util.Scanner;

public class Guess_number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int totalRounds = 2;
        int score = 0;

        System.out.println("\u001B[1m#####-----Welcome to the Number Guessing Game!-----#####" + "\u001B[0m ");
        System.out.println("I have chosen a number between " + minRange + " and " + maxRange + ".");
        System.out.println("\u001B[1m-----Try to guess the number!-----" + "\u001B[0m ");

        for (int round = 1; round <= totalRounds; round++) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guess = false;

            System.out.println("\nRound " + round);
            System.out.println("-----------");

            while (!guess) {
                System.out.print("Enter your guess: ");
                int guess_num = scanner.nextInt();
                attempts++;

                if (guess_num == randomNumber) {
                    System.out.println("\n \u001B[1mCongratulations! You guessed the number correctly.\n");
                    System.out.println("---------------------------");
                    System.out.println("| \u001B[1mNumber of attempts: " + attempts + "\u001B[0m |");
                    System.out.println("---------------------------");
                    score += attempts;
                    guess = true;
                } else if (guess_num < randomNumber) {
                    System.out.println("Your guess is lower than the generated number. Try again.");
                } else {
                    System.out.println("Your guess is higher than the generated number. Try again.");
                }
            }
        }

        System.out.println("\n ***** \u001B[1mYour Game Over  *****\n");
        System.out.println("---------------------------");
        System.out.printf("*#**#*\u001B[1mYour Total Score: " + score + "\u001B[0m *#**#*\n");
        System.out.println("---------------------------");
        // Close the scanner after usage
        scanner.close();
    }
}
