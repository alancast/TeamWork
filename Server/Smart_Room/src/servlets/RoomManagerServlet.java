package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataStructures.APRDB;
import dataStructures.Building;
import dataStructures.QuietRoom;
import dataStructures.Workspace;

/**
 * Servlet implementation class RoomManagerServlet
 */
@WebServlet("/RoomManagerServlet")
public class RoomManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static APRDB roomDB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomManagerServlet() {
        super();
        roomDB = new APRDB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buildingID = request.getParameter("buildingID");
		Building building = roomDB.getBuilding(buildingID);
		
		ArrayList<Workspace> rooms = building.getAllWorkspaces();
		
		String responseText =
				"<h1>" + building.getName() + "</h1>" +
				"<p><strong>ID: </strong>" + building.getID() + "</br>" +
				"<strong>Address: </strong>" + building.getAddress() + "</p>" +
				"<div class='report table table-responsive'>" +
				"<table class='table table-striped report-table'>" +
				"<thead>"
				+ "<tr>"
					+ "<th>Room Number</th>"
					+ "<th class='hidden-xs'>Floor</th>"
					+ "<th class='hidden-xs'>Approx. Location</th>"
					+ "<th class='hidden-xs'>Seat Count</th>"
					+ "<th class='hidden-xs'>Telepresence?</th>"
					+ "<th>Status</th>"
				+ "</tr>"
				+ "</thead>" +
				"<tbody>";
		for (Workspace ws : rooms) {
			String status = "Available", 
					labelMode = "label-success", 
					roomType = "",
					hasTelepresence = "";
			if (ws.isOccupied()) {
				status = "Occupied";
				labelMode = "label-danger";
			}
			if (ws.hasTelepresence()) {
				hasTelepresence = "<span class='glyphicon glyphicon-ok'></span>";
			}
			if (ws instanceof QuietRoom) roomType = "QR ";
			else roomType = "APR ";
			responseText += "<tr>"
					+ "<td>" + roomType + ws.getRoomNumber() + "</td>"
					+ "<td class='hidden-xs'>" + ws.getFloor() + "</td>"
					+ "<td class='hidden-xs'>Somewhere</td>"
					+ "<td class='hidden-xs'>" + ws.getSeatCount() + "</td>"
					+ "<td class='hidden-xs'>" + hasTelepresence + "</td>"
					+ "<td id='status'><span class='label " + labelMode + "'>" + status + "</span></td>"
					+ "</tr>";
		}
		
		responseText += "</tbody></table></div>";
		
		response.setContentType("text/html");
		response.getWriter().write(responseText);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buildingID = request.getParameter("buildingID");
		if (!roomDB.contains(buildingID)) return;
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		if (roomNo < 0) return; // Ignore invalid room numbers
		boolean status = Boolean.parseBoolean(request.getParameter("occupied"));
		
		System.out.println(" > HTTP POST: {buildingID: '" + buildingID + "', roomNo: '" + roomNo + "', occupied: '" + status + "' } ");
		
		if (status) roomDB.setOccupied(buildingID, roomNo);
		else roomDB.setAvailable(buildingID, roomNo);
	}
}
