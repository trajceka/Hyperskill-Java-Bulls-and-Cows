package bullscows;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new TextUserInterface(scanner);
        ui.start();
    }
}

interface UserInterface {
    
    void start();

}

class TextUserInterface implements UserInterface {

    private Scanner scanner;

    public TextUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public void start() {
        System.out.println("Please, enter the secret code's length:");
        String inputl = scanner.nextLine();
        if (inputl.matches("\\d+")) {
            System.out.println("Input the number of possible symbols in the code:");
            String inputn = scanner.nextLine();
            Game game;
            // System.out.println(game.check(input.toCharArray()));
            if (inputl.matches("\\d+") && inputn.matches("\\d+")) {
                int numberl = Integer.parseInt(inputl);
                int numbern = Integer.parseInt(inputn);
                if (numberl < numbern && numberl > 0 && numbern < 37) {
                    game = new Game(numberl, numbern);
                    System.out.println("The secret is prepared: " + pstar(numberl) + " (" + pnum(numbern) + ").");
                    System.out.println("Okay, let's start a game!");
                    int i = 1;
                    boolean ubool = true;
                    while (ubool) {
                        System.out.println("Turn " + i++ + ":");
                        ubool = game.check(scanner.nextLine().toCharArray());
                    }
                } else {
                    System.out.println("Error: can't generate a secret number with a length of " + numberl + " because there aren't enough unique digits.");
                }
            } else {
                System.out.println("Error: ln isn't a valid number.");
            }
        } else {
            System.out.println("Error: ln isn't a valid number.");
        }
    }

    private String pstar(int n) {
        String stars = "";
        for (short i = 0; i < n; i++) {
            stars += "*";
        }

        return stars;
    }

    private String pnum(int n) {
        String num = "";
        if (n < 11) {
            num = "0-" + (n - 1);
        } else if (n == 12) {
            num = "0-9, a";
        } else {
            num = "0-9, a - " + (char) ('a' + (n - 11));
        }

        return num;
    }


    // private void comment() {
        // System.out.println("Hello World!");
        // System.out.println("The secret code is prepared: ****.");
        // System.out.println("");
        // System.out.println("Turn 1. Answer:");
        // System.out.println("1234");
        // System.out.println("Grade: None.");
        // System.out.println("");
        // System.out.println("Turn 2. Answer:");
        // System.out.println("9876");
        // System.out.println("Grade: 4 bulls.");
        // System.out.println("Congrats! The secret code is 9876.");
        // System.out.println("");
    // }
}

class Game {
    char[] secret;

    public Game(int l, int n) {
        secret = this.generaten(l, n);
    }

    public Boolean check(char... guess) {
        boolean e = true;
        boolean[] isbull = new boolean[this.secret.length];
        short bull = 0;
        short cow = 0;
        for (short i = 0; i < isbull.length; i++) {
            if (guess[i] == this.secret[i]) {
                bull++;
                isbull[i] = true;
            }
        }
        for (short i = 0; i < isbull.length; i++) {
            for (short j = 0; j < isbull.length; j++) {
                if (!isbull[j] && i != j && guess[j] == secret[i]) {
                    cow++;
                }
            }
        }

        String bullsncows = "None.";
        if (bull == isbull.length) {
            bullsncows = "" + bull + " bulls.\nCongrats! You guessed the secret code.";
            e = false;

        } else if (bull > 0 && cow > 0) {
            bullsncows = bull + " bull(s) and " + cow + " cow(s).";
        } else if (bull > 0) {
            bullsncows = bull + " bull(s).";
        } else if (cow > 0) {
            bullsncows = cow + " cow(s).";
        }

        System.out.println("Grade: " + bullsncows);

        return e;
    }

    private char[] generaten(int l, int n) {
        ArrayList<Character> alchars = new ArrayList<>();
        alchars.add('0');
        alchars.add('1');
        alchars.add('2');
        alchars.add('3');
        alchars.add('4');
        alchars.add('5');
        alchars.add('6');
        alchars.add('7');
        alchars.add('8');
        alchars.add('9');
        for (char i = 0; i < 26; i++) {
            alchars.add((char) ('a' + i));
        }
        ArrayList<Character> characters = new ArrayList<>(alchars.subList(0, n));

        char[] achars = new char[l];

        // System.out.print("The random secret number is ");
        for (short i = 0; i < l; i++) {
            achars[i] = characters.remove((int)(Math.random() * characters.size()));
        }

        // System.out.print(".");

        return achars;
    }

}
