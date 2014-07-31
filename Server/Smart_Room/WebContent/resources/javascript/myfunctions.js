$(document).ready(function() {
	
	/**
	 * Reloads the current report when the user clicks the refresh button
	 */
	$(document).on('click', '.refresh-report', function() {
		loadRoomStatuses($(this).attr('target-report'));
	});
	
	/**
	 * Reloads the current report with a different sorting method
	 */
	$(document).on('click', '.report-header', function() {
		window.alert("RE-SORT: " + $(this).attr('column'));
	});
	
	/**
	 * Empties the report view and loads the overview
	 */
	$('#overview').click(function() {
		//$('#report-area').empty().html("<h1 class='page-header'>Welcome to Smart Room!</h1><p>Workspace info will appear here...</p>");
		$('#report-area').empty().load('resources/shared/overview.jsp');
	});
	
	/**
	 * Loads the appropriate report into the viewing area when the user makes a
	 * selection from
	 */
	$('.report-gen').click(function() {
		// alert($(this).attr('building'));
		loadRoomStatuses($(this).attr('buildingID'));
		// Toggle the sidebar upon making a selection
		$('button[data-toggle="offcanvas"]').click();
	});
	
	/**
	 * Test HTTP POST requests to the RoomManagerServlet
	 */
	$('#testPost').click(function() {
		$.ajax({
			type: 'POST',
			url: 'RoomManagerServlet',
			data: {'buildingID' : 'SJC-MR3', 'roomNo' : '3', 'occupied' : 'true'},
			success: function() { alert("Success!"); },
			error: function() { alert("Error!"); }
		});
		$.ajax({
			type: 'POST',
			url: 'RoomManagerServlet',
			data: {'buildingID' : 'SJC-1', 'roomNo' : '0', 'occupied' : 'true'},
			success: function() { alert("Success!"); },
			error: function() { alert("Error!"); }
		});
		$.ajax({
			type: 'POST',
			url: 'RoomManagerServlet',
			data: {'buildingID' : 'SJC-12', 'roomNo' : '9', 'occupied' : 'true'},
			success: function() { alert("Success!"); },
			error: function() { alert("Error!"); }
		});
	});
	
});

/**
 * Loads the report with the given Report ID into the viewing window
 * @param reportID
 */
function loadRoomStatuses(buildingID) {
	$('#report-area').empty().html("<div class='loader' align='center'><img src='resources/images/loader.gif' /></div>");
	$.ajax({
		type: 'GET',
		url: 'RoomManagerServlet',
		data: {'buildingID' : buildingID},
		success: function(responseText) { $('#report-area').empty().html(responseText); },
		error: function(responseText) { $('#report-area').empty().html("<h2>No information available!!</h2>"); }
	});
}

