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
		keywords.put("Int", TokenType.TYPE_INT);
		keywords.put("String", TokenType.TYPE_STRING);
		keywords.put("Bool", TokenType.TYPE_BOOL);
		keywords.put("Float", TokenType.TYPE_FLOAT);
		keywords.put("Void", TokenType.TYPE_VOID);
		keywords.put("struct", TokenType.STRUCT_DECLARATION);
		keywords.put("enum", TokenType.ENUM_DECLARATION);
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
		addToken(type, literal, text);
	}

	private void addToken(TokenType type, Object literal, String lexeme) {
		tokens.add(new Token(type, lexeme, literal, line));
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
		case '[':
			addToken(TokenType.LEFT_BRACKET);
			break;
		case ']':
			addToken(TokenType.RIGHT_BRACKET);
			break;
		case ',':
			addToken(TokenType.COMMA);
			break;
		case '.':
			addToken(TokenType.DOT);
			break;
		case '-':
			if (match('>')) {
				addToken(TokenType.ARROW);
			} else {
				addToken(TokenType.MINUS);
			}
			break;
		case '+':
			addToken(TokenType.PLUS);
			break;
		case '*':
			addToken(TokenType.MULTIPLY);
			break;
		case ';':
			addToken(TokenType.SEMICOLON);
			break;
		case ':':
			addToken(TokenType.COLON);
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
			break;
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
		StringBuilder value = new StringBuilder();

		while (peek() != '\'' && !isAtEnd()) {
			if (peek() == '\n') line++;

			if (peek() == '\\') {
				advance(); // consume '\'
				if (!isAtEnd()) {
					char escaped = advance();
					value.append(switch (escaped) {
						case 'n' -> '\n';
						case 't' -> '\t';
						case '\'' -> '\'';
						case '\\' -> '\\';
						default -> escaped;
					});
				}
			} else {
				value.append(advance());
			}
		}

		if (isAtEnd()) {
			KitsuneLangMain.error(line, "Unterminated string.");
			return;
		}

		advance(); // consume closing '

		addToken(TokenType.STRING, value.toString());
	}

	private void interpolation() {
		StringBuilder part = new StringBuilder();

		while (!isAtEnd() && peek() != '"') {
			if (peek() == '\\') {
				advance();
				if (!isAtEnd()) {
					char escaped = advance();
					part.append(switch (escaped) {
						case 'n' -> '\n';
						case 't' -> '\t';
						case '"' -> '"';
						case '\\' -> '\\';
						default -> escaped;
					});
				}
			} else if (peek() == '$' && peekNext() == '{') {
				if (part.length() > 0) {
					addToken(TokenType.STRING_PART, part.toString());
					part.setLength(0);
				}

				// Interpolation start
				advance(); // $
				advance(); // {
				start = current - 2;
				addToken(TokenType.INTERPOLATION_START);

				// Expression inside ${...}
				int exprStart = current;
				while (!isAtEnd() && peek() != '}') {
					if (peek() == '\n') line++;
					advance();
				}

				if (isAtEnd()) {
					KitsuneLangMain.error(line, "Unterminated interpolation.");
					return;
				}

				String expr = source.substring(exprStart, current).trim();
				advance(); // consume }

				addToken(TokenType.IDENTIFIER, expr, expr);
				start = current - 1;
				addToken(TokenType.INTERPOLATION_END);
				start = current;

				// Continue building the part AFTER the interpolation
			} else {
				if (peek() == '\n') line++;
				part.append(advance());
			}
		}

		if (part.length() > 0) {
			addToken(TokenType.STRING_PART, part.toString());
		}

		if (isAtEnd()) {
			KitsuneLangMain.error(line, "Unterminated string.");
			return;
		}

		advance(); // consume closing "
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

		addToken(TokenType.NUMBER_LITERAL,
				Double.parseDouble(source.substring(start, current)));
	}
}
