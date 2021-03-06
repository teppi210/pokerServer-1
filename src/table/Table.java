package table;

import game.PokerGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cards.Deck;
import player.Player;

public class Table {
	
	
	public ArrayList<Seat> seats;
	public HashSet<Player> players;
	public Seat dealerSeat;
	private Board board;
	public Deck deck;
	
	public Table(){
		initSeats(9);
		players = new HashSet<Player>();
		board = new Board();
		deck = new Deck();
	}
	
	public void pickDealer(){
		int start_index = (int)Math.floor((Math.random() * 10.0));
		if(start_index == seats.size()){
			start_index = 0;
		}
		for(int i = start_index; i < seats.size(); i++){
			if(seats.get(i).hasPlayer()){
				dealerSeat = seats.get(i);
				break;
			}
			
			if(i == seats.size()){
				i = -1;
			}
		}
	}
	
	private void initSeats(int seatCount){
		seats = new ArrayList<Seat>();
		for(int i = 0; i< seatCount; i++){
			Seat s = new Seat(i);
			//s.addPlayer(new Player());
			seats.add(s);
		}
	}
	
	public void addSeat(Seat seat, int positionIndex){
		System.out.println(seats);
		this.seats.add(positionIndex, seat);
	}
	
	public void addPlayerToTable(Player player, int seatNumber){
		players.add(player);
		seats.get(seatNumber).addPlayer(player);
		
		System.out.println("Added a new player!");
		System.out.println(this);
	}
	
	public void addPlayerToTable(Player player)
	{
		for(int i = 0; i < seats.size(); i++){
			if(!seats.get(i).hasPlayer()){
				addPlayerToTable(player, i);
				break;
			}
		}
	}
	public Seat getNextSeat(int currentPosition){
		boolean foundNextSeat = false;
		Seat nextSeat = null;
		int position = currentPosition + 1;
		
		
		while(foundNextSeat == false){
			if(position >= seats.size()){
				position = 0;
			}
			
			if(seats.get(position) != null){
				foundNextSeat = true;
				nextSeat = seats.get(position);
			}
			
			position ++;
		}
		
		return nextSeat;
		
	}
	
	
	
	public void startGame(){
		/*
		deck.createDeck();
		deck.shuffleDeck();
		*/
		
		System.out.println("The game has started!");
		System.out.println("The players are...");
		System.out.println(players);
		
		PokerGame p = new PokerGame(this);
	}
	
	
	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Table Info: \n");
		for (Seat seat : seats){
			s.append(seat);
			s.append("\n");
		}
		return s.toString();
	}

	public int getPlayerCount() {
		// TODO Auto-generated method stub
		return players.size();
	}

	public int getNextSeatNum() {
		// TODO Auto-generated method stub
		boolean lookingForSeat = true;
		int seatNum = 0;
		while(lookingForSeat){
			if(seats.get(seatNum).hasPlayer()){
				seatNum++;
			} else {
				lookingForSeat = false;
			}
		}
		
		return seatNum;
	}
}
