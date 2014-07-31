package dataStructures;

import java.util.ArrayList;

/**
 * Emulates a database that would store information about available and occupied workspaces
 * @author colallen
 *
 */
public class APRDB {
	private ArrayList<Building> buildings;
	
	// Constructor
	public APRDB() {
		this.buildings = new ArrayList<Building>();
		
		this.addBuilding(new Building("SJC Building 1", "SJC-1"));
		this.addBuilding(new Building("SJC Building 3", "SJC-3"));
		this.addBuilding(new Building("SJC Building 12", "SJC-12"));
		this.addBuilding(new Building("SJC McCarthy Ranch 3", "SJC-MR3"));
	}
	
	/**
	 * Determine if the database contains a building with the given ID
	 * @param buildingID The ID to check for
	 * @return
	 */
	public boolean contains(String buildingID) {
		for (Building building : this.buildings) {
			if (building.getID().equals(buildingID)) return true;
		}
		return false;
	}
	
	/**
	 * Retrieve the building with the given ID
	 * @param buildingID
	 * @return
	 */
	public Building getBuilding(String buildingID) {
		for (Building building : this.buildings) {
			if (building.getID().equals(buildingID)) return building;
		}
		return null;
	}
	
	/**
	 * Retrieve the availability of a particular room
	 * @param buildingID The building where the room is located
	 * @param roomNo The number of the room in question
	 * @return A boolean value representing the rooms occupied status: true for 'occupied', false for 'available'
	 */
	public boolean getOccupiedStatus(String buildingID, int roomNo) {
		if (!this.contains(buildingID)) return false;
		
		Building building = this.getBuilding(buildingID);
		Workspace workspace = building.getWorkspace(roomNo);
		return workspace.isOccupied;
	}
	
	/**
	 * Retrieve the occupied statuses for all rooms of a given building
	 * @param buildingID The ID of the building of interest
	 * @return An array of booleans representing the occupied statuses of all rooms in the building
	 */
	public ArrayList<Workspace> getWorkspaces(String buildingID) {
		if (!this.contains(buildingID)) return new ArrayList<Workspace>();
		return this.getBuilding(buildingID).getAllWorkspaces();
	}
	
	/**
	 * Set a particular room to 'occupied'
	 * @param buildingID The ID where the room is located
	 * @param roomNo The room number
	 */
	public void setOccupied(String buildingID, int roomNo) {
		if (!this.contains(buildingID)) return;
		Building building = this.getBuilding(buildingID);
		Workspace workspace = building.getWorkspace(roomNo);
		if (workspace != null) workspace.setOccupied();
	}
	
	/**
	 * Set a particular room to 'available'
	 * @param buildingID The ID of the building where the room is located
	 * @param roomNo The room number
	 */
	public void setAvailable(String buildingID, int roomNo) {
		if (!this.contains(buildingID)) return;
		Building building = this.getBuilding(buildingID);
		Workspace workspace = building.getWorkspace(roomNo);
		if (workspace != null) workspace.setAvailable();
	}
	
	/**
	 * Add a building, with a given number of rooms, to the database
	 * @param buildingID The ID to assign to the building
	 * @param aprCount The number of rooms in the building
	 */
	private void addBuilding(Building newBuilding) {
		this.buildings.add(newBuilding);
	}
}
