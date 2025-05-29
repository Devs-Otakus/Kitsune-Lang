package KitsuneLang.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KitsuneLang.KitsuneLangMain;

public class ScannerKitsuneLang {
	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	private int start = 0;
	private int current = 0;
	private int line = 1;

	private static final Map<String, TokenType> keywords;

	static {
		keywords = new HashMap<>();
		keywords.put("and", TokenType.AND);
		keywords.put("else", TokenType.ELSE_CONDITION);
		keywords.put("false", TokenType.FALSE);
		keywords.put("for", TokenType.FOR_LOOP);
		keywords.put("fun", TokenType.FUNCTION);
		keywords.put("if", TokenType.IF_CONDITION);
		keywords.put("nil", TokenType.NIL);
		keywords.put("or", TokenType.OR);
		keywords.put("print", TokenType.PRINT);
		keywords.put("return", TokenType.RETURN_STATEMENT);
		keywords.put("true", TokenType.TRUE);
		keywords.put("while", TokenType.WHILE_LOOP);
		keywords.put("do", TokenType.DO_BLOCK_START);
		keywords.put("end", TokenType.BLOCK_END);
		keywords.put("LET", TokenType.LET_IMMUTABLE);
		keywords.put("let", TokenType.LET_MUTABLE);
	}

	public ScannerKitsuneLang(String source) {
		this.source = source;
	}

	public List<Token> scanTokens() {
		while (!isAtEnd()) {
			start = current;
			scanToken();
		}
		tokens.add(new Token(TokenType.EOF, "", null, line));
		return tokens;
	}

	private boolean isAtEnd() {
		return current >= source.length();
	}

	private char peek() {
		if (isAtEnd())
			return '\0';
		return source.charAt(current);
	}

	private char advance() {
		return source.charAt(current++);
	}

	private void addToken(TokenType type) {
		addToken(type, null);
	}

	private void addToken(TokenType type, Object literal) {
		String text = source.substring(start, current);
		tokens.add(new Token(type, text, literal, line));
	}

	private boolean match(char expected) {
		if (isAtEnd())
			return false;
		if (source.charAt(current) != expected)
			return false;

		current++;
		return true;
	}

	private void scanToken() {
		char c = advance();
		switch (c) {
		// Sinais
		case '(':
			addToken(TokenType.LEFT_PAREN);
			break;
		case ')':
			addToken(TokenType.RIGHT_PAREN);
			break;
		case '{':
			addToken(TokenType.LEFT_BRACE);
			break;
		case '}':
			addToken(TokenType.RIGHT_BRACE);
			break;
		case ',':
			addToken(TokenType.COMMA);
			break;
		case '.':
			addToken(TokenType.DOT);
			break;
		case '-':
			addToken(TokenType.MINUS);
			break;
		case '+':
			addToken(TokenType.PLUS);
			break;
		case ';':
			addToken(TokenType.SEMICOLON);
			break;
		case '*':
			addToken(TokenType.STAR);
			break;
		// Operadores
		case '!':
			addToken(match('=') ? TokenType.NOT_EQUAL : TokenType.NOT);
			break;
		case '=':
			addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.ASSIGN);
			break;
		case '<':
			addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS_THAN);
			break;
		case '>':
			addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER_THAN);
			break;
		case '/':
			if (match('/')) {
				// Linha de comentário
				while (peek() != '\n' && !isAtEnd())
					advance();
			} else if (match('*')) {
				// Comentário de bloco
				while (!(peek() == '*' && match('/')) && !isAtEnd()) {
					if (advance() == '\n')
						line++;
				}
			} else {
				addToken(TokenType.DIVIDE);
			}
		case ' ':
		case '\r':
		case '\t':
			// Ignore whitespace.
			break;

		case '\n':
			line++;
			break;

		case '\'':
			string();
			break;
		case '"':
			interpolation();
			break;
		case 'a':

		default:
			if (isDigit(c)) {
				// Número
				number();

			} else if (isAlpha(c)) {
				identifier();
			} else {
				KitsuneLangMain.error(line, " Unexpected character: '" + c + "'");
			}
			break;
		}
	}

	private void string() {
		while (peek() != '\'' && !isAtEnd()) {
			if (peek() == '\n')
				line++;
			advance();
		}

		if (isAtEnd()) {
			KitsuneLangMain.error(line, "Unterminated string.");
			return;
		}

		// The closing ".
		advance();

		// Trim the surrounding quotes.
		String value = source.substring(start + 1, current - 1);
		addToken(TokenType.STRING, value);

	}

	private void interpolation() {
		while (peek() != '"' && !isAtEnd()) {
			if (peek() == '\n') {
				line++;
			} else if (peek() == '$' && peekNext() == '{') {
				// Handle interpolation
				advance(); // consume $
				advance(); // consume {
				addToken(TokenType.INTERPOLATION_START);
				while (peek() != '}' && !isAtEnd()) {
					advance();
				}
				if (peek() == '}') {
					advance(); // consume }
					addToken(TokenType.INTERPOLATION_END);
				}
			}
			advance();
		}

		if (isAtEnd()) {
			KitsuneLangMain.error(line, "Unterminated interpolation.");
			return;
		}

		// The closing '.
		advance();

		// Trim the surrounding quotes.
		String value = source.substring(start + 1, current - 1);
		addToken(TokenType.INTERPOLATION_START, value);
		addToken(TokenType.INTERPOLATION_END, value);
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private char peekNext() {
		if (current + 1 >= source.length())
			return '\0';
		return source.charAt(current + 1);
	}

	private boolean isAlpha(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
	}

	private boolean isAlphaNumeric(char c) {
		return isAlpha(c) || isDigit(c);
	}

	private void identifier() {
		while (isAlphaNumeric(peek()))
			advance();
		String text = source.substring(start, current);
		TokenType type = keywords.get(text);
		if (type == null)
			type = TokenType.IDENTIFIER;
		addToken(type);
	}

	private void number() {
		while (isDigit(peek()))
			advance();

		// Look for a fractional part.
		if (peek() == '.' && isDigit(peekNext())) {
			// Consume the "."
			advance();

			while (isDigit(peek()))
				advance();
		}

		addToken(TokenType.INTEGER_LITERAL,
				Double.parseDouble(source.substring(start, current)));
	}
}
