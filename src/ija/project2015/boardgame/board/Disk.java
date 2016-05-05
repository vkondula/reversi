package ija.project2015.boardgame.board;
/**
 * Class represents disk
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
public class Disk {
	protected boolean white;
	/**
	 * Creates new disk
	 * @param isWhite is color of created disk
	 */
	public Disk(boolean isWhite){
		this.white = isWhite;
	}
	/**
	 * Changes color of disk
	 */
	public void turn(){
		white = !white;
	}
	/**
	 * Provides color of disk
	 * @return color of disk
	 */
	public boolean isWhite(){
		return white;
	}
	/**
	 * Checks if two disks are equal
	 * @param obj to compare
	 */
	@Override
	public boolean equals(java.lang.Object obj){
		if (!(obj instanceof Disk))
			return false;
		else {
			Disk other = (Disk) obj;
			return white == other.isWhite();
		}
	}
	/**
	 * return code for color of stone
	 */
	@Override
	public int hashCode(){
		if (white)
			return 1;
		else
			return 0;
	}
	
}
