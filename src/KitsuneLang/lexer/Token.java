package KitsuneLang.lexer;

public class Token {
	final TokenType type;
	final String lexeme;
	final Object object;
	final int line;

	Token(TokenType type, String lexeme, Object object, int line) {
		this.type = type;
		this.lexeme = lexeme;
		this.object = object;
		this.line = line;
	}

	public String toString() {
		return "Token{" + "type=" + type + ", lexeme='" + lexeme + '\''
				+ ", object=" + object + ", line=" + line + '}';
	}
}
