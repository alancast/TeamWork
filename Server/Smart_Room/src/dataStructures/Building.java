package dataStructures;

import java.util.ArrayList;

/**
 * Represents a single Cisco building.
 * @author colallen
 *
 */
public class Building {
	private String fullName, address, id;
	private ArrayList<Workspace> workspaces;
	
	public Building() {
		this("No Name Building", "1234 Street Rd., City, State, 98765", "ABC-1");
	}
	
	public Building(String fullName, String id) {
		this(fullName, "N/A", id);
	}
	
	
	public Building(String fullName, String address, String id) {
		this.fullName = fullName;
		this.address = address;
		this.id = id;
		this.workspaces = new ArrayList<Workspace>();
		this.populateDummyWorkspaces();
	}
	
	/**
	 * TEMPORARY: populates the building with workspaces with different data
	 */
	public void populateDummyWorkspaces() {
		for (int i = 1; i <= 10; i++) {
			boolean isOccupied = false, hasTP = false;
			//if (Math.random() > 0.75) isOccupied = true;
			if (Math.random() > 0.8) hasTP = true;
			
			if (Math.random() > 0.65) this.workspaces.add(new AudioPrivacyRoom(isOccupied, hasTP, i, 2, 4));
			else this.workspaces.add(new QuietRoom(isOccupied, hasTP, i, 2, 2));
		}
	}
	
	/**
	 * Retrieve the "full" name of a given building (e.g.: Building 12, McCarthy Ranch 3, etc)
	 * @return
	 */
	public String getName() { return this.fullName; }
	
	/**
	 * Retrieve the id of a given building (e.g.: SJC-1, RTP-5, etc)
	 * @return
	 */
	public String getID() { return this.id; }
	
	/**
	 * Retrieve the street address of a given building
	 * @return
	 */
	public String getAddress() { return this.address; }
	
	/**
	 * Retrieve the workspace with the given room number
	 * @param roomNumber
	 * @return
	 */
	public Workspace getWorkspace(int roomNumber) {
		for (Workspace ws : this.workspaces) {
			if (ws.getRoomNumber() == roomNumber) return ws;
		}
		if (roomNumber < 0) return null;
		// FOR NOW, if a workspace is not found with the given room number, create it
		Workspace newWorkspace = new AudioPrivacyRoom(false, false, roomNumber, 1, 4);
		this.workspaces.add(newWorkspace);
		return newWorkspace;
	}
	
	/**
	 * Retrieve all workspaces inside the given building
	 * @return
	 */
	public ArrayList<Workspace> getAllWorkspaces() { return this.workspaces; }
	
	/**
	 * Retrieve all AVAILABLE workspaces in the given building
	 * @return
	 */
	public ArrayList<Workspace> getAvailableWorkspaces() {
		ArrayList<Workspace> available = new ArrayList<Workspace>();
		for (Workspace ws : this.workspaces) {
			if (!ws.isOccupied) available.add(ws);
		}
		return available;
	}
	/**
	 * Retrieve all workspaces with TELEPRESENCE units in the given building
	 * @return
	 */
	public ArrayList<Workspace> getTelepresenceWorkspaces() {
		ArrayList<Workspace> tp = new ArrayList<Workspace>();
		for (Workspace ws : this.workspaces) {
			if (ws.hasTelepresence) tp.add(ws);
		}
		return tp;
	}
	/**
	 * Retrieve all workspaces on a given FLOOR in the given building
	 * @param floor The floor of interest
	 * @return
	 */
	public ArrayList<Workspace> getWorkspacesOnMyFloor(int floor) {
		ArrayList<Workspace> rooms = new ArrayList<Workspace>();
		for (Workspace ws : this.workspaces) {
			if (ws.getFloor() == floor) rooms.add(ws);
		}
		return rooms;
	}
	/**
	 * Retrieve all workspaces with a desired number of SEATS in the given building
	 * @param seatCount The desired number of seats in the workspace
	 * @return
	 */
	public ArrayList<Workspace> getWorkspacesWithSeatCount(int seatCount) {
		ArrayList<Workspace> rooms = new ArrayList<Workspace>();
		for (Workspace ws : this.workspaces) {
			if (ws.getSeatCount() == seatCount) rooms.add(ws);
		}
		return rooms;
	}
}
