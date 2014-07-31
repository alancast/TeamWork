package dataStructures;

/**
 * Represents a Quiet Room
 * @author colallen
 *
 */
public class QuietRoom extends Workspace {

	public QuietRoom(boolean isOccupied, boolean hasTelepresence, 
			int roomNo, int floor, int seatCount) {
		super(isOccupied, hasTelepresence, roomNo, floor, seatCount);
	}
	
}
