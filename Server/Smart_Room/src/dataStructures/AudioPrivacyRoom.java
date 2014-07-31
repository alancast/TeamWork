package dataStructures;

/**
 * Represents an Audio Privacy Room
 * @author colallen
 *
 */
public class AudioPrivacyRoom extends Workspace {
	
	public AudioPrivacyRoom(int roomNo, int floor) {
		super(false, false, roomNo, floor, 0);
	}
	
	public AudioPrivacyRoom(boolean isOccupied, boolean hasTelepresence, int roomNo, int floor, int seatCount) {
		super(isOccupied, hasTelepresence, roomNo, floor, seatCount);
	}
}
