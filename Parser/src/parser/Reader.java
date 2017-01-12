package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	public static String Read(String pass) {
		StringBuilder builder = new StringBuilder();

		try {
			File file = new File(pass);
			FileReader filereader = new FileReader(file);

			int ch;
			while ((ch = filereader.read()) != -1) {
				builder.append((char)ch);
			}

			filereader.close();
			String all = new String(builder);
			return all;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return "";
	}
}