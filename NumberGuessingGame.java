import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        boolean playAgain = true;

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("I have a number between 1 and 100, can you guess it?");

        while (playAgain) {
            int numberToGuess = (int)(Math.random() * 100) + 1;
            int attemptsLeft = 7; // Limited attempts
            boolean guessedCorrect = false;

            while (attemptsLeft > 0) {
                System.out.println("\nAttempts left: " + attemptsLeft);
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                if (guess < numberToGuess) {
                    System.out.println("Too low! 🔻");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! 🔺");
                } else {
                    System.out.println("🎉 Congratulations! You guessed it right!");
                    score += 10;
                    guessedCorrect = true;
                    break;
                }

                attemptsLeft--;
            }

            if (!guessedCorrect) {
                System.out.println("😢 Out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            sc.nextLine(); // Clear buffer
            String choice = sc.nextLine();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
                System.out.println("\nThanks for playing! Your final score: " + score);
            }
        }

        sc.close();
    }
    }
