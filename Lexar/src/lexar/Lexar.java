package lexar;

import java.util.ArrayList;

public class Lexar {
	public static void main(String[] args) {
		String all = Reader.Read("C:\\testdata\\normal_01.pas");

		ArrayList<Pair<String, Short>> words = Disassembler.Disassemble(all);
		ArrayList<Pair4<String, String, Short, Short>> checkedwords = new ArrayList<Pair4<String, String, Short, Short>>();

		for (Pair<String, Short> word : words) {
			Pair3<String, String, Short> token = MyLibrary.CheckToken(word.getL());
			checkedwords.add(new Pair4<String, String, Short, Short>(word.getL(),token.getM(),token.getR(),word.getR()));
		}

		Writer.Write(checkedwords,"C:\\testdata\\tmp.ts");

		for (Pair4<String, String, Short, Short> test : checkedwords)
			System.out.println(test.getLL() + " " + test.getML() + " " + test.getMR() + " " + test.getRR());
	}
}