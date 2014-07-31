package dataStructures;

/**
 * Represents a Cisco "workspace" (e.g.: Audio Privacy Room (APR), Quiet Room (QR), etc)
 * @author colallen
 *
 */
public class Workspace {
	protected boolean isOccupied, hasTelepresence;
	protected int roomNo, floor, seatCount;
	
	protected Workspace(boolean isOccupied, boolean hasTelepresence,
			int roomNo, int floor, int seatCount) {
		this.isOccupied = isOccupied;
		this.hasTelepresence = hasTelepresence;
		this.roomNo = roomNo;
		this.floor = floor;
		this.seatCount = seatCount;
	}
	
	/**
	 * Determine if the given workspace is occupied
	 * @return
	 */
	public boolean isOccupied() { return this.isOccupied; }
	
	/**
	 * Set the workspace to an 'occupied' state
	 */
	public void setOccupied() { this.isOccupied = true; }
	
	/**
	 * Set the workspace to an 'available' state
	 */
	public void setAvailable() { this.isOccupied = false; }
	
	/**
	 * Determine if the given workspace has a telepresence unit
	 * @return
	 */
	public boolean hasTelepresence() { return this.hasTelepresence; }
	
	/**
	 * Retrieve the room number of the given workspace
	 * @return
	 */
	public int getRoomNumber() { return this.roomNo; }
	
	/**
	 * Retrieve the floor the given workspace is on, within its building
	 * @return
	 */
	public int getFloor() { return this.floor; }
	
	/**
	 * Retrieve the seat count of the given workspace
	 * @return
	 */
	public int getSeatCount() { return this.seatCount; }
}
