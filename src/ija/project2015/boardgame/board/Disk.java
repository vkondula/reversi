package ija.project2015.boardgame.board;
/**
 * TODO
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
public class Disk {
	protected boolean white;
	public Disk(boolean isWhite){
		this.white = isWhite;
	}
	public void turn(){
		white = !white;
	}
	public boolean isWhite(){
		return white;
	}
	@Override
	public boolean equals(java.lang.Object obj){
		if (!(obj instanceof Disk))
			return false;
		else {
			Disk other = (Disk) obj;
			return white == other.isWhite();
		}
	}
	@Override
	public int hashCode(){
		if (white)
			return 1;
		else
			return 0;
	}
	
}
