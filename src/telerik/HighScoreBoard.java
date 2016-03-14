package telerik;
/*
 * Class that can add player to the chart 
 */
import java.util.LinkedList;


public class HighScoreBoard {
	LinkedList list;
	public final int boardSize = 5;
	public HighScoreBoard(){
		list = new LinkedList();
	}
	
	public boolean addPlayerToChart(Player player){
		if(list.size()==0){
			list.addFirst(player);
			return true;
		}	
		
		if((list.size()>0)&&(list.size()<boardSize)){	
			addPlayer(player);
		}
		if((list.size()==boardSize)) {
			addPlayer(player);
			return true;
		}
		return false;
	}
	void printBoard(LinkedList list){
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" ");
			System.out.print("Escaped in "+p.movesCount+" moves");
			System.out.println();
		}
	}
	void addPlayer(Player player){
		Player pl = (Player) list.get(list.size()-1);
		if((player.movesCount<pl.movesCount)){
			list.remove(list.size() - 1);
			int index = 0;
			while (index < list.size()) {
				pl = (Player) list.get(index);
				if (player.movesCount <= pl.movesCount) {
					list.add(index, player);
					
				}
				index++;
			}
		}
	}
}

