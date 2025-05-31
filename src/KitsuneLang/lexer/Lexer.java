package KitsuneLang.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
		private String input;

		/**
		 * Constructor for the Lexer class.
		 *
		 * @param input The string input to be lexed.
		 */
		public Lexer(String input) {
				this.input = input;
		}

		/**
		 * Empty constructor for CLI usage.
		 */
		public Lexer() {}

		/**
		 * Sets the input for the lexer.
		 *
		 * @param input The string input to be lexed.
		 */
		public void setInput(String input) {
				this.input = input;
		}

		/**
		 * Runs the lexer on the provided input.
		 */
		public ArrayList<Token> run() {
				// Implement the lexing logic here
				return (ArrayList<Token>) List.of(new Token("test", "test", 0, 0));
		}
}
