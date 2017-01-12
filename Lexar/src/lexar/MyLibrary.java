package lexar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyLibrary {
	public static boolean IsSpliter(String ch) {
		String spliter = "	 +-*/=<>()[].,:;";
		return (spliter.indexOf(ch) > -1);
	}

	public static boolean CheckSpecial(String word, char ch) {
		String target = word + ch;
		ArrayList<String> opr = (ArrayList<String>) Arrays.asList("<>", "<=", ">=", ":=", "..");
		return opr.contains(target);
	}

	public static Pair3<String, String, Short> CheckToken(String str) {
		if (str.substring(0, 1).equals("\'"))
			return new Pair3<String, String, Short>(str, "SSTRING", (short) 45);

		for (Pair3<String, String, Short> token : tokens) {
			if (token.getL().equals(str))
				return token;
		}

	    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]*$");
	    java.util.regex.Matcher matcher = pattern.matcher(str.substring(0, 1));
		if (matcher.matches()){
			return new Pair3<String, String, Short>(str, "SCONSTANT", (short) 44);
		}

		return new Pair3<String, String, Short>(str, "SIDENTIFIER", (short) 43);
	}

	private static List<Pair3<String, String, Short>> tokens = Arrays.asList(
			new Pair3<String, String, Short>("and", "SAND", (short) 0),
			new Pair3<String, String, Short>("array", "SARRAY", (short) 1),
			new Pair3<String, String, Short>("begin", "SBEGIN", (short) 2),
			new Pair3<String, String, Short>("boolean", "SBOOLEAN", (short) 3),
			new Pair3<String, String, Short>("char", "SCHAR", (short) 4),
			new Pair3<String, String, Short>("div", "SDIVD", (short) 5),
			new Pair3<String, String, Short>("/", "SDIVD", (short) 5),
			new Pair3<String, String, Short>("do", "SDO", (short) 6),
			new Pair3<String, String, Short>("else", "SELSE", (short) 7),
			new Pair3<String, String, Short>("end", "SEND", (short) 8),
			new Pair3<String, String, Short>("false", "SFALSE", (short) 9),
			new Pair3<String, String, Short>("if", "SIF", (short) 10),
			new Pair3<String, String, Short>("integer", "SINTEGER", (short) 11),
			new Pair3<String, String, Short>("mod", "SMOD", (short) 12),
			new Pair3<String, String, Short>("not", "SNOT", (short) 13),
			new Pair3<String, String, Short>("of", "SOF", (short) 14),
			new Pair3<String, String, Short>("or", "SOR", (short) 15),
			new Pair3<String, String, Short>("procedure", "SPROCEDURE", (short) 16),
			new Pair3<String, String, Short>("program", "SPROGRAM", (short) 17),
			new Pair3<String, String, Short>("readln", "SREADLN", (short) 18),
			new Pair3<String, String, Short>("then", "STHEN", (short) 19),
			new Pair3<String, String, Short>("true", "STRUE", (short) 20),
			new Pair3<String, String, Short>("var", "SVAR", (short) 21),
			new Pair3<String, String, Short>("while", "SWHILE", (short) 22),
			new Pair3<String, String, Short>("writeln", "SWRITELN", (short) 23),
			new Pair3<String, String, Short>("=", "SEQUAL", (short) 24),
			new Pair3<String, String, Short>("<>", "SNOTEQUAL", (short) 25),
			new Pair3<String, String, Short>("<", "SLESS", (short) 26),
			new Pair3<String, String, Short>("<=", "SLESSEQUAL", (short) 27),
			new Pair3<String, String, Short>(">=", "SGREATEQUAL", (short) 28),
			new Pair3<String, String, Short>(">", "SGREAT", (short) 29),
			new Pair3<String, String, Short>("+", "SPLUS", (short) 30),
			new Pair3<String, String, Short>("-", "SMINUS", (short) 31),
			new Pair3<String, String, Short>("*", "SSTAR", (short) 32),
			new Pair3<String, String, Short>("(", "SLPAREN", (short) 33),
			new Pair3<String, String, Short>(")", "SRPAREN", (short) 34),
			new Pair3<String, String, Short>("[", "SLBRACKET", (short) 35),
			new Pair3<String, String, Short>("]", "SRBRACKET", (short) 36),
			new Pair3<String, String, Short>(";", "SSEMICOLON", (short) 37),
			new Pair3<String, String, Short>(":", "SCOLON", (short) 38),
			new Pair3<String, String, Short>("..", "SRANGE", (short) 39),
			new Pair3<String, String, Short>(":=", "SASSIGN", (short) 40),
			new Pair3<String, String, Short>(",", "SCOMMA", (short) 41),
			new Pair3<String, String, Short>(".", "SDOT", (short) 42));

}
