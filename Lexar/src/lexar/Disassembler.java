package lexar;

import java.util.ArrayList;

public class Disassembler {
	public static ArrayList<Pair<String, Short>> Disassemble(String all) {
		Boolean comment = false;
		ArrayList<Pair<String, Short>> words = new ArrayList<Pair<String, Short>>();
		String word = "";
		Short ln = 0;
		boolean spliter = false;
		boolean str = false;

		for (char ch : all.toCharArray()) {
			if (comment == true) {
				if (ch == '}') {
					comment = false;
				}
			} else if (ch == '{' && comment == false) {
				comment = true;
			} else if (comment == false) {
				if (str == true) {
					word = word + ch;
					if (ch == '\'')
						str = false;
				} else if (ch == ' ' || ch == '	' || ch == '\n') {
					if (word != "") {
						words.add(new Pair<String, Short>(word,ln));
						word = "";
						spliter = false;
					}
				} else if (MyLibrary.IsSpliter("" + ch)) {
					if (word + ch == "<>" || word + ch == "<=" || word + ch == ">=" || word + ch == ":=") {
						word = word + ch;
						words.add(new Pair<String, Short>(word,ln));
						word = "";
						spliter = false;
					} else {
						if (word != "")
							words.add(new Pair<String, Short>(word,ln));
						word = "" + ch;
						spliter = true;
					}
				} else {
					if (spliter) {
						words.add(new Pair<String, Short>(word,ln));
						word = "";
						spliter = false;
					}
					word = word + (char) ch;
					if (ch == '\'')
						str = true;
				}
			}
			if (ch == '\n')
				ln++;
		}
		return words;
	}
}
