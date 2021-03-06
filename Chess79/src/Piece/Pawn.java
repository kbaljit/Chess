package Piece;

import chess.Block;
import chess.Player;


/**
 * @author Baljit Kaur
 * @author Milan Patel
 */
public class Pawn extends Piece{
	/**
	 * Initializes a Pawn object.
	 * @param name Name used to identify a Pawn.
	 * @param block The block where the Pawn is initially placed.
	 * @param color Color of the piece.
	 */
	public Pawn(String name, Block block, String color, Player player){
		super(name, block, color, player);
	}
	
	/**
	 * Determines if a move to a new block is valid.
	 * The Pawn is moved if the move is valid, else an error is returned.
	 * @param moveTo The block a Pawn will be moved to if the move is valid.
	 */
	public boolean move(Block moveTo, boolean check, String move){
		
		//Translate File and Rank to array indices
		int srcFile  = this.getBlock().getFile();
		int srcRank  = chess.Chess.Rmap.get(this.getBlock().getRank()+"");
		int destFile = moveTo.getFile();
		int destRank = chess.Chess.Rmap.get(moveTo.getRank()+"");
		
		int prevSrcRank=chess.Chess.Rmap.get(chess.Chess.prevMove.charAt(1)+"");
		int prevDestFile=chess.Chess.Fmap.get(chess.Chess.prevMove.charAt(3)+"");
		int prevDestRank=chess.Chess.Rmap.get(chess.Chess.prevMove.charAt(4)+"");
		
		if(check == false){
			System.out.println();
		}
		
		//Check White Pawn Legality
		if(this.getName().charAt(0) == 'w'){
			if(moveTo.isOccupied()){
			   if((destRank == srcRank - 1) && (Math.abs(destFile-srcFile)==1)){
					if(moveTo.getPiece().getColor().equals(chess.Chess.board[srcRank][srcFile].getPiece().getColor())==false){
						//A check to see if this move puts the opponent's King in check
						if(check == true){
							return true;
						}
						//Call deletePiece to indicate that target piece has been captured
						chess.Chess.board[destRank][destFile].getPiece().deletePiece(chess.Chess.board[destRank][destFile].getPiece().getNumber(), chess.Chess.board[destRank][destFile].getPiece());
						
						chess.Chess.board[destRank][destFile].setPiece(getBlock().getPiece());
						chess.Chess.board[destRank][destFile].setDisplay("wp ");
						
						this.getBlock().setOccupied(false);
						this.getBlock().setPiece(null);
						
						if(this.getBlock().isShaded()){
							this.getBlock().setDisplay("## ");
						}
						else{
							this.getBlock().setDisplay("   ");
						}
						this.setBlock(moveTo);
						if(destRank==0){
							promotion(destRank, destFile, move);
						}
					
						chess.Chess.printBoard();
						System.out.println();
						
					
						return true;
						
						
					}
				   else{
					   if(check == true){
						   return false;
					   }
						System.out.println("Invalid move, try again");
						return false;
					}
				}
				
			  else if(destRank == srcRank - 1)
						/*|| ((moveTo.getRank() == getBlock().getRank() + 2) &&
								(moveTo.getRank() - 1  )))*/{
				  if(check == true){
					   return false;
				   }
				System.out.println("Invalid move, try again");
				return false;
			  }
			 
			  else if(srcRank==6 && destRank==srcRank-2){
				  if(check == true){
					   return false;
				   }
				  System.out.println("Invalid move, try again");
				  return false;
			  }
			 
			  else{
				  if(check == true){
					   return false;
				   }
				  System.out.println("Invalid move, try again");
				  return false;
			  }
			  
	}
		
		//Destination is not occupied	
		else{
			//Pawn moves 1 forward
			if(destRank == srcRank -1 && destFile==srcFile){
				//A check to see if this move puts the opponent's King in check
				if(check == true){
					return true;
				}
				chess.Chess.board[destRank][destFile].setPiece(this);
				chess.Chess.board[destRank][destFile].setOccupied(true);
				chess.Chess.board[destRank][destFile].setDisplay("wp ");
				
				
				this.getBlock().setOccupied(false);
				this.getBlock().setPiece(null);
				
				if(this.getBlock().isShaded()){
					this.getBlock().setDisplay("## ");
				}
				else{
					this.getBlock().setDisplay("   ");
				}
				this.setBlock(moveTo);
				if(destRank==0){
					promotion(destRank, destFile, move);
				}
				
				chess.Chess.printBoard();
				System.out.println();
				return true;
			}
			
			//Pawn moves two forward on first move
			else if(srcRank==6 && destRank == srcRank-2){
				//A check to see if this move puts the opponent's King in check
				if(check == true){
					return true;
				}
				chess.Chess.board[destRank][destFile].setPiece(this);
				chess.Chess.board[destRank][destFile].setOccupied(true);
				chess.Chess.board[destRank][destFile].setDisplay("wp ");
				
				this.getBlock().setOccupied(false);
				this.getBlock().setPiece(null);
				
				if(this.getBlock().isShaded()){
					this.getBlock().setDisplay("## ");
				}
				else{
					this.getBlock().setDisplay("   ");
				}
				this.setBlock(moveTo);
				if(destRank==0){
					promotion(destRank, destFile, move);
				}
				chess.Chess.printBoard();
				System.out.println();
				return true;
			}
			//Enpassant Capture
			else  if((destRank == srcRank - 1) && (Math.abs(destFile-srcFile)==1) && srcRank==3){
					
				if((chess.Chess.board[srcRank][destFile].getPiece().equals(chess.Chess.board[prevDestRank][prevDestFile].getPiece())) &&
					(Math.abs(prevDestRank-prevSrcRank)==2) && (prevSrcRank==1)){
					//A check to see if this move puts the opponent's King in check
					if(check == true){
						return true;
					}
					//Call deletePiece to indicate that target piece has been captured
					chess.Chess.board[srcRank][destFile].getPiece().deletePiece(chess.Chess.board[srcRank][destFile].getPiece().getNumber(), chess.Chess.board[srcRank][destFile].getPiece());
					
					chess.Chess.board[destRank][destFile].setPiece(getBlock().getPiece());
					chess.Chess.board[destRank][destFile].setDisplay("wp ");
					chess.Chess.board[destRank][destFile].setOccupied(true);

					
					this.getBlock().setOccupied(false);
					this.getBlock().setPiece(null);
					
					if(this.getBlock().isShaded()){
						this.getBlock().setDisplay("## ");
					}
					else{
						this.getBlock().setDisplay("   ");
					}
					
					chess.Chess.board[srcRank][destFile].setPiece(null);
					chess.Chess.board[srcRank][destFile].setOccupied(false);

					if(chess.Chess.board[srcRank][destFile].isShaded()){
						chess.Chess.board[srcRank][destFile].setDisplay("## ");
					}
					else{
						chess.Chess.board[srcRank][destFile].setDisplay("   ");
					}
					
					this.setBlock(moveTo);
					chess.Chess.printBoard();
					System.out.println();
				
					return true;
					
				}
				else{
					System.out.println("Invalid Move, try again");
					return false;
				}
				
			}
			
			//Illegal Move
			else{
				if(check == true){
					   return false;
				}
				System.out.println("Invalid Move, try again");
				return false;
			}
			
		}
			
			
	}
		
		//Black Pawn Move
		else if(this.getName().charAt(0) == 'b'){
			if(moveTo.isOccupied()){
				   if((destRank == srcRank + 1) && (Math.abs(destFile-srcFile)==1)){
						if(moveTo.getPiece().getColor().equals(chess.Chess.board[srcRank][srcFile].getPiece().getColor())==false){
							//A check to see if this move puts the opponent's King in check
							if(check == true){
								return true;
							}
							
							//capture opponent's piece
							//send message to remove
							
							//Call deletePiece to indicate that target piece has been captured
							chess.Chess.board[destRank][destFile].getPiece().deletePiece(chess.Chess.board[destRank][destFile].getPiece().getNumber(), chess.Chess.board[destRank][destFile].getPiece());
							
							chess.Chess.board[destRank][destFile].setPiece(this);
							chess.Chess.board[destRank][destFile].setDisplay("bp ");
							
							this.getBlock().setOccupied(false);
							this.getBlock().setPiece(null);
							
							if(this.getBlock().isShaded()){
								this.getBlock().setDisplay("## ");
							}
							else{
								this.getBlock().setDisplay("   ");
							}
							this.setBlock(moveTo);
							if(destRank==7){
								promotion(destRank, destFile, move);
							}
							chess.Chess.printBoard();
							System.out.println();
							return true;
							
							
						}else{
							if(check == true){
								   return false;
							}
							System.out.println("Invalid move, try again");
							return false;
						}
					}
					
				  else if(destRank == srcRank + 1)
							/*|| ((moveTo.getRank() == getBlock().getRank() + 2) &&
									(moveTo.getRank() - 1  )))*/{
					
					  if(check == true){
						   return false;
					   }
					System.out.println("Invalid move, try again");
					return false;
				  }
				 
				  else if(srcRank==1 && destRank==srcRank+2){
					  if(check == true){
						   return false;
					   }
					  System.out.println("Invalid move, try again");
					  return false;
				  }
				 
				  else{
					  if(check == true){
						   return false;
					   }
					  System.out.println("Invalid move, try again");
					  return false;
				  }
				  
		}
			
			//Destination is not occupied	
			else{
				//Pawn moves forward 1 tile
				if(destRank == srcRank +1 && destFile==srcFile){
					//A check to see if this move puts the opponent's King in check
					if(check == true){
						return true;
					}
					chess.Chess.board[destRank][destFile].setPiece(this);
					chess.Chess.board[destRank][destFile].setOccupied(true);
					chess.Chess.board[destRank][destFile].setDisplay("bp ");
					
					this.getBlock().setOccupied(false);
					this.getBlock().setPiece(null);
					
					if(this.getBlock().isShaded()){
						this.getBlock().setDisplay("## ");
					}
					else{
						this.getBlock().setDisplay("   ");
					}
					this.setBlock(moveTo);
					if(destRank==7){
						promotion(destRank, destFile,  move);
					}
					chess.Chess.printBoard();
					System.out.println();
					return true;
				}
				//Pawn moves forward 2 tiles on first move
				else if(srcRank==1 && destRank == srcRank+2){
					//A check to see if this move puts the opponent's King in check
					if(check == true){
						return true;
					}
					
					chess.Chess.board[destRank][destFile].setPiece(this);
					chess.Chess.board[destRank][destFile].setOccupied(true);
					chess.Chess.board[destRank][destFile].setDisplay("bp ");
					
					this.getBlock().setOccupied(false);
					this.getBlock().setPiece(null);
					
					if(this.getBlock().isShaded()){
						this.getBlock().setDisplay("## ");
					}
					else{
						this.getBlock().setDisplay("   ");
					}
					this.setBlock(moveTo);
					if(destRank==7){
						promotion(destRank, destFile, move);
					}
					chess.Chess.printBoard();
					System.out.println();
					return true;
				}
				
				//Enpassant capture 
				else if((destRank == srcRank + 1) && (Math.abs(destFile-srcFile)==1) && srcRank==4){
					if((chess.Chess.board[srcRank][destFile].getPiece().equals(chess.Chess.board[prevDestRank][prevDestFile].getPiece())) &&
							(Math.abs(prevDestRank-prevSrcRank)==2) && (prevSrcRank==6)){
						//A check to see if this move puts the opponent's King in check
						if(check == true){
							return true;
						}
						
						//Call deletePiece to indicate that target piece has been captured
						chess.Chess.board[srcRank][destFile].getPiece().deletePiece(chess.Chess.board[srcRank][destFile].getPiece().getNumber(), chess.Chess.board[srcRank][destFile].getPiece());
						
						chess.Chess.board[destRank][destFile].setPiece(getBlock().getPiece());
						chess.Chess.board[destRank][destFile].setDisplay("bp ");
						
						this.getBlock().setOccupied(false);
						this.getBlock().setPiece(null);
						
						if(this.getBlock().isShaded()){
							this.getBlock().setDisplay("## ");
						}
						else{
							this.getBlock().setDisplay("   ");
						}
						
						chess.Chess.board[srcRank][destFile].setPiece(null);
						chess.Chess.board[srcRank][destFile].setOccupied(false);

						if(chess.Chess.board[srcRank][destFile].isShaded()){
							chess.Chess.board[srcRank][destFile].setDisplay("## ");
						}
						else{
							chess.Chess.board[srcRank][destFile].setDisplay("   ");
						}
						
						this.setBlock(moveTo);
						
						chess.Chess.printBoard();
						System.out.println();
					
						return true;
					}
					
					else{
						if(check == true){
							   return false;
						}
						System.out.println("Invalid Move, try again");
						return false;
					}
					
				}
				
				//Invalid move
				else{
					if(check == true){
						   return false;
					}
					System.out.println("Invalid Move, try again");
					return false;
				}
				
			}
		}
		return false;
	}
	
	/**
	 * Promotes Pawn that reaches other end of board after a move.
	 * @param destRank Rank of the destination block.
	 * @param destFile File of the destination block.
	 * @param move The name of the piece the user wants to promote a Pawn to.
	 */
	public void promotion(int destRank, int destFile, String move){
		Player player=this.getPlayer();
		String promote=" ";
		if(move.length()==7)
			promote=move.substring(6);
		
		if(promote.equals("R")){
			int num=chess.Chess.board[destRank][destFile].getPiece().getNumber();
			if(num==1){
				Rook Rook1;
				if(player.getColor().equals("White"))
					Rook1=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook1=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn1().setBlock(null);
				player.setPawn1(null);
				player.RPromote1=Rook1;
				player.RPromote1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook1);
				Rook1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote1.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==2){
				Rook Rook2;
				if(player.getColor().equals("White"))
					Rook2=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook2=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn2().setBlock(null);
				player.setPawn2(null);
				player.RPromote2=Rook2;
				player.RPromote2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook2);
				Rook2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote2.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==3){
				Rook Rook3;
				if(player.getColor().equals("White"))
					Rook3=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook3=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn3().setBlock(null);
				player.setPawn3(null);
				player.RPromote3=Rook3;
				player.RPromote3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook3);
				Rook3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote3.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==4){
				Rook Rook4;
				if(player.getColor().equals("White"))
					Rook4=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook4=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn4().setBlock(null);
				player.setPawn4(null);
				player.RPromote4=Rook4;
				player.RPromote4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook4);
				Rook4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote4.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==5){
				Rook Rook5;
				if(player.getColor().equals("White"))
					Rook5=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook5=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn5().setBlock(null);
				player.setPawn5(null);
				player.RPromote5=Rook5;
				player.RPromote5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook5);
				Rook5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote5.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==6){
				Rook Rook6;
				if(player.getColor().equals("White"))
					Rook6=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook6=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn6().setBlock(null);
				player.setPawn6(null);
				player.RPromote6=Rook6;
				player.RPromote6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook6);
				Rook6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote6.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==7){
				Rook Rook7;
				if(player.getColor().equals("White"))
					Rook7=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook7=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn7().setBlock(null);
				player.setPawn7(null);
				player.RPromote7=Rook7;
				player.RPromote7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook7);
				Rook7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote7.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else{
				Rook Rook8;
				if(player.getColor().equals("White"))
					Rook8=new Rook("wR ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Rook8=new Rook("bR ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn8().setBlock(null);
				player.setPawn8(null);
				player.RPromote8=Rook8;
				player.RPromote8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Rook8);
				Rook8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.RPromote8.setBlock(chess.Chess.board[destRank][destFile]);
			}
			
		}
		else if(promote.equals("B")){
			int num=chess.Chess.board[destRank][destFile].getPiece().getNumber();
			if(num==1){
				Bishop Bishop1;
				if(player.getColor().equals("White"))
					Bishop1=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop1=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn1().setBlock(null);
				player.setPawn1(null);
				player.BPromote1=Bishop1;
				player.BPromote1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop1);
				Bishop1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote1.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==2){
				Bishop Bishop2;
				if(player.getColor().equals("White"))
					Bishop2=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop2=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn2().setBlock(null);
				player.setPawn2(null);
				player.BPromote2=Bishop2;
				player.BPromote2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop2);
				Bishop2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote2.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==3){
				Bishop Bishop3;
				if(player.getColor().equals("White"))
					Bishop3=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop3=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn3().setBlock(null);
				player.setPawn3(null);
				player.BPromote3=Bishop3;
				player.BPromote3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop3);
				Bishop3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote3.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==4){
				Bishop Bishop4;
				if(player.getColor().equals("White"))
					Bishop4=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop4=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn4().setBlock(null);
				player.setPawn4(null);
				player.BPromote4=Bishop4;
				player.BPromote4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop4);
				Bishop4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote4.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==5){
				Bishop Bishop5;
				if(player.getColor().equals("White"))
					Bishop5=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop5=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn5().setBlock(null);
				player.setPawn5(null);
				player.BPromote5=Bishop5;
				player.BPromote5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop5);
				Bishop5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote5.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==6){
				Bishop Bishop6;
				if(player.getColor().equals("White"))
					Bishop6=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop6=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn6().setBlock(null);
				player.setPawn6(null);
				player.BPromote6=Bishop6;
				player.BPromote6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop6);
				Bishop6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote6.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==7){
				Bishop Bishop7;
				if(player.getColor().equals("White"))
					Bishop7=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop7=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn7().setBlock(null);
				player.setPawn7(null);
				player.BPromote7=Bishop7;
				player.BPromote7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop7);
				Bishop7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote7.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else{
				Bishop Bishop8;
				if(player.getColor().equals("White"))
					Bishop8=new Bishop("wB ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Bishop8=new Bishop("bB ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn8().setBlock(null);
				player.setPawn8(null);
				player.BPromote8=Bishop8;
				player.BPromote8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Bishop8);
				Bishop8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.BPromote8.setBlock(chess.Chess.board[destRank][destFile]);
			}
			
		}
		else if(promote.equals("N")){
			int num=chess.Chess.board[destRank][destFile].getPiece().getNumber();
			if(num==1){
				Knight Knight1;
				if(player.getColor().equals("White"))
					Knight1=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight1=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn1().setBlock(null);
				player.setPawn1(null);
				player.KPromote1=Knight1;
				player.KPromote1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight1);
				Knight1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote1.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==2){
				Knight Knight2;
				if(player.getColor().equals("White"))
					Knight2=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight2=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn2().setBlock(null);
				player.setPawn2(null);
				player.KPromote2=Knight2;
				player.KPromote2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight2);
				Knight2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote2.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==3){
				Knight Knight3;
				if(player.getColor().equals("White"))
					Knight3=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight3=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn3().setBlock(null);
				player.setPawn3(null);
				player.KPromote3=Knight3;
				player.KPromote3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight3);
				Knight3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote3.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==4){
				Knight Knight4;
				if(player.getColor().equals("White"))
					Knight4=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight4=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn4().setBlock(null);
				player.setPawn4(null);
				player.KPromote4=Knight4;
				player.KPromote4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight4);
				Knight4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote4.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==5){
				Knight Knight5;
				if(player.getColor().equals("White"))
					Knight5=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight5=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn5().setBlock(null);
				player.setPawn5(null);
				player.KPromote5=Knight5;
				player.KPromote5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight5);
				Knight5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote5.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==6){
				Knight Knight6;
				if(player.getColor().equals("White"))
					Knight6=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight6=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn6().setBlock(null);
				player.setPawn6(null);
				player.KPromote6=Knight6;
				player.KPromote6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight6);
				Knight6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote6.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==7){
				Knight Knight7;
				if(player.getColor().equals("White"))
					Knight7=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight7=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn7().setBlock(null);
				player.setPawn7(null);
				player.KPromote7=Knight7;
				player.KPromote7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight7);
				Knight7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote7.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else{
				Knight Knight8;
				if(player.getColor().equals("White"))
					Knight8=new Knight("wN ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Knight8=new Knight("bN ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn8().setBlock(null);
				player.setPawn8(null);
				player.KPromote8=Knight8;
				player.KPromote8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Knight8);
				Knight8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.KPromote8.setBlock(chess.Chess.board[destRank][destFile]);
			}
			
		}
		else{
			int num=chess.Chess.board[destRank][destFile].getPiece().getNumber();
			if(num==1){
				Queen Queen1;
				if(player.getColor().equals("White"))
					Queen1=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen1=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn1().setBlock(null);
				player.setPawn1(null);
				player.QPromote1=Queen1;
				player.QPromote1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen1);
				Queen1.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote1.setBlock(chess.Chess.board[destRank][destFile]);
				
			}
			else if(num==2){
				Queen Queen2;
				if(player.getColor().equals("White"))
					Queen2=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen2=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn2().setBlock(null);
				player.setPawn2(null);
				player.QPromote2=Queen2;
				player.QPromote2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen2);
				Queen2.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote2.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==3){
				Queen Queen3;
				if(player.getColor().equals("White"))
					Queen3=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen3=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn3().setBlock(null);
				player.setPawn3(null);
				player.QPromote3=Queen3;
				player.QPromote3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen3);
				Queen3.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote3.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==4){
				Queen Queen4;
				if(player.getColor().equals("White"))
					Queen4=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen4=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn4().setBlock(null);
				player.setPawn4(null);
				player.QPromote4=Queen4;
				player.QPromote4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen4);
				Queen4.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote4.setBlock(chess.Chess.board[destRank][destFile]);
				
			}
			else if(num==5){
				Queen Queen5;
				if(player.getColor().equals("White"))
					Queen5=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen5=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn5().setBlock(null);
				player.setPawn5(null);
				player.QPromote5=Queen5;
				player.QPromote5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen5);
				Queen5.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote5.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==6){
				Queen Queen6;
				if(player.getColor().equals("White"))
					Queen6=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen6=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn6().setBlock(null);
				player.setPawn6(null);
				player.QPromote6=Queen6;
				player.QPromote6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen6);
				Queen6.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote6.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else if(num==7){
				Queen Queen7;
				if(player.getColor().equals("White"))
					Queen7=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen7=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn7().setBlock(null);
				player.setPawn7(null);
				player.QPromote7=Queen7;
				player.QPromote7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen7);
				Queen7.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote7.setBlock(chess.Chess.board[destRank][destFile]);
			}
			else{
				Queen Queen8;
				if(player.getColor().equals("White"))
					Queen8=new Queen("wQ ", chess.Chess.board[destRank][destFile], "White", player);
				else
					Queen8=new Queen("bQ ", chess.Chess.board[destRank][destFile], "Black", player);
				player.getPawn8().setBlock(null);
				player.setPawn8(null);
				player.QPromote8=Queen8;
				player.QPromote8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setPiece(Queen8);
				Queen8.setBlock(chess.Chess.board[destRank][destFile]);
				chess.Chess.board[destRank][destFile].setDisplay(chess.Chess.board[destRank][destFile].getPiece().getName());
				chess.Chess.board[destRank][destFile].setOccupied(true);
				player.QPromote8.setBlock(chess.Chess.board[destRank][destFile]);
			}
			
		}
		
	}
}
		
