package hand;

import java.util.ArrayList;

import cards.Card;

public class HighCard extends Hand{
	
	Card card;
	
	public HighCard(Card card){
		this.card = card;
		setHighCard(card);
	}
	
	@Override
	public ArrayList<Card>getCards(){
		return new ArrayList<Card>(){{
				add(card);
			}};
	}

}
