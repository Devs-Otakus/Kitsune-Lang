package KitsuneLang.lexer;

public class Token {
		private String type;
		private String value;
		private int line;
		private int column;

		/**
		 * Constructor for the Token class.
		 *
		 * @param type   The type of the token.
		 * @param value  The value of the token.
		 * @param line   The line number where the token was found.
		 * @param column The column number where the token was found.
		 */
		public Token(String type, String value, int line, int column) {
				this.type = type;
				this.value = value;
				this.line = line;
				this.column = column;
		}

		// Getters for the token properties
		public String getType() {
				return type;
		}

		public String getValue() {
				return value;
		}

		public int getLine() {
				return line;
		}

		public int getColumn() {
				return column;
		}
}
