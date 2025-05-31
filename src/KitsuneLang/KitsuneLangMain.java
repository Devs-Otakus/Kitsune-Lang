package KitsuneLang;

import java.util.Scanner;

import KitsuneLang.lexer.Lexer;

/**
 * KitsuneLangMain.java This is the main entry point for the KitsuneLang
 * programming language. It initializes the interpreter or compiler and starts
 * the execution.
 */
public class KitsuneLangMain {
    /**
     * The main method serves as the entry point for the KitsuneLang
     * application. It can be used to initialize the interpreter or compiler and
     * start the execution.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        String input;
        System.out.println("Welcome to KitsuneLang!");
        // Here you can add more functionality or call other methods/classes
        // to start the KitsuneLang interpreter or compiler.
        if (args.length == 0) {
            Lexer lexer = new Lexer();
            try (Scanner scanner = new Scanner(System.in)) {
                while(true){
                    System.out.print("> ");
                    try {
                        input = scanner.nextLine();
                    } catch (java.util.NoSuchElementException | IllegalStateException e) {
                        System.out.println("\nExiting KitsuneLang. Goodbye!");
                        break;
                    }
                    lexer.setInput(input);
                    lexer.run();
                }
            }
        }
    }
}