package parser;

import java.util.ArrayList;

import parser.Types.Pair4;

public class Parser {
	public static void main(String[] args) {
		String all = Reader.Read("C:\\testdata\\tmp.ts ");

		ArrayList<Pair4<String, String, Short, Short>> tokens = new ArrayList<Pair4<String, String, Short, Short>>();

		tokens = Decode(all);


	}

	static private ArrayList<Pair4<String, String, Short, Short>> Decode(String all){
		ArrayList<Pair4<String, String, Short, Short>> tokens = new ArrayList<Pair4<String, String, Short, Short>>();

		int mode = 0;
		String name = "",tokenName = "", id = "", rowNum = "";

		for (char ch : all.toCharArray()){
			if(ch == ' ')
				mode++;
			else if(ch == '\n'){
				Pair4<String, String, Short, Short> token = new Pair4<String, String, Short, Short>(name,tokenName,Short.parseShort(id),Short.parseShort(rowNum));
				tokens.add(token);
				name = "";
				tokenName = "";
				id = "";
				rowNum = "";
			}
			else {
				switch (mode){
				case 0:
					name = name + ch;
				case 1:
					tokenName = tokenName + ch;
				case 2:
					id = id + ch;
				case 3:
					rowNum = rowNum + ch;
				}
			}
		}

		return tokens;
	}

	static private int SyntaxCheck(ArrayList<Pair4<String, String, Short, Short>> tokens){
		int status = -1;
		int last = tokens.size() - 1;
		int i;

		if(!"SPROGRAM".equals(tokens.get(0).getML()))
			return tokens.get(0).getRR();

		if(!"SIDENTIFIER".equals(tokens.get(0).getML()))
			return tokens.get(0).getRR();
		else if(!"SLPAREN".equals(tokens.get(1).getML()))
			return tokens.get(1).getRR();

		for(i = 2; i < tokens.size(); i++){
			if(i == last){
				return tokens.get(last).getRR();
			}
			if("SRPAREN".equals(tokens.get(i).getML())){
				if(i != 3)
					status = NamesCheck((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(3, i));
				if(status > -1)
					return status;
				i++;
				break;
			}
		}

		if(i == last || i + 1 == last || i + 2 == last)
			return tokens.get(tokens.size()).getRR();

		if(!"SSEMICOLON".equals(tokens.get(i).getML()))
			return tokens.get(i).getRR();
		i++;

		status = BlockAndConbinedCheck((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(i, tokens.size()));
		if(status > -1)
			return status;

		if(!"SDOT".equals(tokens.get(tokens.size() - 1).getML()))
			return tokens.get(tokens.size() - 1).getRR();

		return -1;
	}

	static private int NamesCheck(ArrayList<Pair4<String, String, Short, Short>> tokens){
		if(tokens.size()%2 == 0)
			return tokens.get(tokens.size() - 1).getRR();

		for(int i = 0;i < tokens.size(); i++){
			if(i%2==0){
				if(!"SIDENTIFIER".equals(tokens.get(1).getML()))
					return tokens.get(1).getRR();
			}
			else if(i%2==1){
				if(!"SCOMMA".equals(tokens.get(1).getML()))
					return tokens.get(1).getRR();
			}
		}
		return -1;
	}

	static private int BlockAndConbinedCheck(ArrayList<Pair4<String, String, Short, Short>> tokens){
		int status;
		int i,j;
		boolean var = false,procedure = false;

		if("SVAR".equals(tokens.get(0).getML()))
			var = true;
		if("SPROCEDURE".equals(tokens.get(0).getML()))
			procedure = true;

		for(i = 1; i < tokens.size(); i++){
			if(var){
				j = i;

				while(!"SCOLON".equals(tokens.get(i).getML())){
					i++;

					if(i == tokens.size())
						return tokens.get(tokens.size() -1).getRR();
				}

				if(j == i)
					return tokens.get(i).getRR();

				status = NamesCheck((ArrayList<Pair4<String, String, Short, Short>>) tokens.subList(j, i));

				if(status > -1)
					return status;

				i++;
				if(i == tokens.size())
					return tokens.get(tokens.size() -1).getRR();

				if("array".equals(tokens.get(i).getLL())){
					i++;
					if(i == tokens.size() || i + 1 == tokens.size() || i + 2 == tokens.size() || i + 3 == tokens.size() || i + 4 == tokens.size() || i + 5 == tokens.size() || i + 6 == tokens.size())
						return tokens.get(tokens.size() -1).getRR();
					if(!"[".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(!isNumMatch(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(!"..".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(!isNumMatch(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(!"]".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(!"of".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
				}

				if(!"integer".equals(tokens.get(i).getLL()) || !"char".equals(tokens.get(i).getLL()) || !"boolean".equals(tokens.get(i).getLL()))
					return tokens.get(i).getRR();

				i++;
				if(i == tokens.size())
					return tokens.get(tokens.size() -1).getRR();

				if(!";".equals(tokens.get(i).getLL()))
					return tokens.get(i).getRR();

				if("SPROCEDURE".equals(tokens.get(i + 1).getML())){
					i++;
					var = false;
					procedure = true;
				}
				else if("SBEGIN".equals(tokens.get(i + 1).getML())){
					var = false;
					procedure = false;
				}
			}
			else if(procedure){
				if(!"SIDENTIFIER".equals(tokens.get(i).getML()))
					return tokens.get(i).getRR();
				i++;
				if(i == tokens.size() || i + 1 == tokens.size())
					return tokens.get(tokens.size() -1).getRR();
				if(!"(".equals(tokens.get(i).getLL()))
					return tokens.get(i).getRR();

				i++;

				while(!")".equals(tokens.get(i).getLL())){
					j = i;

					while(!":".equals(tokens.get(i).getLL())){
						i++;

						if(i == tokens.size())
							return tokens.get(tokens.size() -1).getRR();
					}
					if(j!=i){
						status = NamesCheck((ArrayList<Pair4<String, String, Short, Short>>) tokens.subList(j, i));

						if(status > -1)
							return status;
					}

					i++;
					if(i == tokens.size() || i + 1 == tokens.size())
						return tokens.get(tokens.size() -1).getRR();
					if(!"integer".equals(tokens.get(i).getLL()) || !"char".equals(tokens.get(i).getLL()) || !"boolean".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
					i++;
					if(";".equals(tokens.get(i).getLL()))
						i++;
					else if(!")".equals(tokens.get(i).getLL()))
						return tokens.get(i).getRR();
				}

				i++;
				if(i == tokens.size() || i + 1 == tokens.size() || i + 2 == tokens.size())
					return tokens.get(tokens.size() -1).getRR();
				if(!";".equals(tokens.get(i).getLL()))
					return tokens.get(i).getRR();

				if("SVAR".equals(tokens.get(i + 1).getML())){
					i++;
					var = false;
					procedure = true;
				}
				else if("SBEGIN".equals(tokens.get(i + 1).getML())){
					var = false;
					procedure = false;
				}
			}
		}

		return -1;
	}

	static boolean isNumMatch(String number) {
	    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]*$");
	    java.util.regex.Matcher matcher = pattern.matcher(number);
	    return matcher.matches();
	}
}