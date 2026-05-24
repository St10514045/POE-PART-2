package tumeloquickchat;

import java.util.Scanner;

public class TumeloQuickChat {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Part1 auth = new Part1();
        Part2 message = new Part2();

        boolean appRunning = true;
        boolean sessionActive = false;

        System.out.println("Welcome to QuickChat.");
        System.out.println("---------------------------");
        System.out.println("Are you a new user? (YES/NO)");
        String entry = input.nextLine().trim().toUpperCase();

        while (!entry.equals("YES") && !entry.equals("NO")) {
            System.out.println("Invalid input. Please type YES or NO.");
            entry = input.nextLine().trim().toUpperCase();
        }

        if (entry.equals("YES")) {
            System.out.println("Let's get you set up!");

            System.out.print("Enter your first name: ");
            auth.setGivenName(input.nextLine().trim());

            System.out.print("Enter your last name: ");
            auth.setFamilyName(input.nextLine().trim());

            System.out.print("Choose a username (must contain _ and be 5 characters or less): ");
            String newId = input.nextLine().trim();
            while (!auth.validateUserId(newId)) {
                System.out.println("Username must contain an underscore and be no more than 5 characters.");
                System.out.print("Choose a username: ");
                newId = input.nextLine().trim();
            }

            System.out.print("Choose a password: ");
            String newSecret = input.nextLine().trim();
            while (!auth.checkPasswordStrength(newSecret)) {
                System.out.println("Password must be at least 10 characters and include uppercase, lowercase, a digit, and a special character.");
                System.out.print("Choose a password: ");
                newSecret = input.nextLine().trim();
            }

            System.out.println(auth.createAccount(newId, newSecret));

            boolean loggedIn = false;
            System.out.println("Now log in to access QuickChat.");
            while (!loggedIn) {
                System.out.print("Username: ");
                String loginId = input.nextLine().trim();
                System.out.print("Password: ");
                String loginSecret = input.nextLine().trim();

                if (auth.verifyCredentials(loginId, loginSecret)) {
                    System.out.println(auth.getLoginMessage(loginId, loginSecret));
                    sessionActive = true;
                    loggedIn = true;
                } else {
                    System.out.println("Login failed! Please check your username and password and try again.");
                }
            }

        } else {
            System.out.println("Good to have you back!");
            boolean loggedInExisting = false;
            while (!loggedInExisting) {
                System.out.print("Username: ");
                String existingId = input.nextLine().trim();
                System.out.print("Password: ");
                String existingSecret = input.nextLine().trim();

                if (auth.verifyCredentials(existingId, existingSecret)) {
                    System.out.println(auth.getLoginMessage(existingId, existingSecret));
                    sessionActive = true;
                    loggedInExisting = true;
                } else {
                    System.out.println("Login failed! Please check your username and password and try again.");
                }
            }
        }

        boolean running = sessionActive;
        int numericMenu;

        while (running) {
            System.out.println("Choose one option from the following numeric menu: ");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Quit");
           
            numericMenu = input.nextInt();
            input.nextLine();

            switch (numericMenu) {
                case 1:
                    System.out.println(message.processMessages());
                    break;

                case 2:
                    System.out.println(message.fetchSentMessages());
                    break;

                case 3:
                    System.out.println("Quitting QuickChat.");
                    running = false;
                    appRunning = false;
                    break;

                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }

        input.close();
    }
}