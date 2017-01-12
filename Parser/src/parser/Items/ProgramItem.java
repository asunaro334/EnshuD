package parser.Items;

import java.util.ArrayList;

import parser.Exception.SyntaxException;
import parser.Types.Pair4;

public class ProgramItem {
	public ProgramItem(ArrayList<Pair4<String, String, Short, Short>> tokens) throws SyntaxException {
		int i;
		NameRowItem names;

		if(!"SPROGRAM".equals(tokens.get(0).getML()))
			throw new SyntaxException(tokens.get(0).getRR());

		if(!"SIDENTIFIER".equals(tokens.get(1).getML()))
			throw new SyntaxException(tokens.get(1).getRR());
		else if(!"SLPAREN".equals(tokens.get(1).getML()))
			throw new SyntaxException(tokens.get(2).getRR());

		for(i = 3; i < tokens.size(); i++){
			if(i == tokens.size() - 1){
				throw new SyntaxException(tokens.get(tokens.size() - 1).getRR());
			}
			if("SRPAREN".equals(tokens.get(i).getML())){
				names = new NameRowItem((ArrayList<Pair4<String, String, Short, Short>>)tokens.subList(3, i));
				i++;
				break;
			}
		}

		if(i == tokens.size() - 1)
			throw new SyntaxException(tokens.get(i).getRR());

		if(!"SSEMICOLON".equals(tokens.get(i).getML()))
			throw new SyntaxException(tokens.get(i).getRR());
		
		

		if(!"SDOT".equals(tokens.get(tokens.size() - 1).getML()))
			throw new SyntaxException(tokens.get(tokens.size() - 1).getRR());
	}
}
