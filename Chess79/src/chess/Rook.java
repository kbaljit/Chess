package chess;

public class Rook extends Piece{
	public Rook(String name, Block block){
		super(name, block);
	}
	
	public void move(Block moveTo){
		if(moveTo.isOccupied()){
			System.out.println("Invalid move: Block is occupied");
			return;
		}
		if(((getBlock().getRank() == moveTo.getRank()) && (getBlock().getFile() != moveTo.getFile())
				|| ((getBlock().getFile() == moveTo.getFile()) && (getBlock().getRank() != moveTo.getRank())))){
			setBlock(moveTo);
		}else{
			System.out.println("Invalid Move");
			//output to board 
		}
	}
}
