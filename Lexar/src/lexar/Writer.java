package lexar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Writer {
	public static void Write(ArrayList<Pair4<String, String, Short, Short>> words,String pass) {

		try {
			File file = new File(pass);
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			PrintWriter pw = new PrintWriter(bw);

			for (Pair4<String, String, Short, Short> test  : words)
				pw.println(test.getLL() + " " + test.getML() + " " + test.getMR() + " " + test.getRR());

			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}