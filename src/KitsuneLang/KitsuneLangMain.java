package KitsuneLang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import KitsuneLang.lexer.ScannerKitsuneLang;
import KitsuneLang.lexer.Token;

/**
 * KitsuneLangMain.java This is the main entry point for the KitsuneLang
 * programming language. It initializes the interpreter or compiler and starts
 * the execution.
 */
public class KitsuneLangMain {
    static boolean hadError = false;

    /**
     * This method is used to report errors encountered during the scanning or
     * parsing process.
     *
     * @param line    The line number where the error occurred.
     * @param where   A string indicating where the error occurred (optional).
     * @param message A descriptive message about the error.
     */
    private static void report(int line, String where, String message) {
        System.err
                .println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    /**
     * This method is used to report errors encountered during the scanning or
     * parsing process without specifying a location.
     *
     * @param line    The line number where the error occurred.
     * @param message A descriptive message about the error.
     */
    public static void error(int line, String message) {
        report(line, "", message);
    }

    /**
     * The main method serves as the entry point for the KitsuneLang
     * application. It can be used to initialize the interpreter or compiler and
     * start the execution.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError)
            System.exit(65);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {

        ScannerKitsuneLang scan = new ScannerKitsuneLang(source);
        List<Token> tokens = scan.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
