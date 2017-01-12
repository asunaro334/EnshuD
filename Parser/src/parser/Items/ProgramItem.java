package parser.Items;

import java.util.ArrayList;

import parser.Exception.SyntaxException;
import parser.Types.Pair4;

public class ProgramItem {
	public ProgramItem(ArrayList<Pair4<String, String, Short, Short>> tokens) throws SyntaxException {
		int i = 0,j,nest=0;
		NameRowItem names;
		StatementItem state;
		BlockItem block;

		if(!"SDOT".equals(tokens.get(tokens.size() - 1).getML()))
			throw new SyntaxException(tokens.get(tokens.size() - 1).getRR());

		if(!"SPROGRAM".equals(tokens.get(0).getML()))
			throw new SyntaxException(tokens.get(0).getRR());

		if(!"SIDENTIFIER".equals(tokens.get(1).getML()))
			throw new SyntaxException(tokens.get(1).getRR());
		else if(!"SLPAREN".equals(tokens.get(2).getML()))
			throw new SyntaxException(tokens.get(2).getRR());

		for(i = 3; i < tokens.size(); i++){
			if("SRPAREN".equals(tokens.get(i).getML())){
				names = new NameRowItem((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(3, i));
				break;
			}
		}

		i++;

		if(!"SSEMICOLON".equals(tokens.get(i).getML()))
			throw new SyntaxException(tokens.get(i).getRR());

		j = tokens.size() - 2;

		for(j = tokens.size() - 2; j >= i; j--){
			if(j == i)
				throw new SyntaxException(tokens.get(j).getRR());
			if("SEND".equals(tokens.get(j).getML()))
				nest++;
			else if("SBEGIN".equals(tokens.get(j).getML()))
				nest--;

			if(nest == 0){
				state = new StatementItem((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(j, tokens.size() - 1));
				break;
			}
		}

		if(j > i + 1){
			block = new BlockItem((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(i + 1, j));
		}
	}
}
