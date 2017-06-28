<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="projectorManagement">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/4.17.37/build/css/bootstrap-datetimepicker.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>
<script src="assets/angular/projectorManagement.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<title>Projector Request Page</title>
</head>
<body  id="pmController" ng-controller="projectorManagementController" ng-init="load()">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>You Can Book Your Projector Here!</h1>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				
				<form method="post" id="projectRequestForm">
					<div class="form-group">
						<label for="datetimepicker6">Start Time:</label>
						<div class='input-group date' id="datetimepicker6">
							<input type='text' class="form-control" id="from" required /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="datetimepicker7">End Time:</label>
						<div class='input-group date' id='datetimepicker7'>
							<input type='text'  class="form-control" id="to" required> <span
								class="input-group-addon" > <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="team">Select your Team :</label> <select id="team"
							class="form-control" ng-model="reserveProjectorId">
							<option ng-repeat="team in teams" value="{{team.teamId}}"
								selected="selected">{{team.name}}</option>
						</select>
					</div>
					<div id="success"></div>
					
					<div class="form-group">
						<div class="col-xs-5 col-xs-offset-3">
							<button type="submit" class="btn btn-default">Book Projector</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
		<br /> <br /> <br />
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
				</div>
				<table class="table table-hover">
					<caption>Teams List</caption>
					<thead>
						<tr>
							<th>#</th>
							<th>Team Name</th>
							<th>Projector Name</th>
							<th>Start Time</th>
							<th>End Time</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="reservation in teamReservatios">
							<td>{{$index + 1}}</td>
							<td>{{reservation.team.name}}</td>
							<td>{{reservation.projector.name}}</td>
							<td>{{reservation.startTime | date: 'MM-dd-yyyy HH:mm a'}}</td>
							<td>{{reservation.endTime | date: 'MM-dd-yyyy HH:mm a'}}</td>
							<td><p data-placement="top" data-toggle="tooltip"
									title="Delete">
									<button class="btn btn-danger btn-xs" data-title="Delete"
										data-toggle="modal" ng-click="deleteProjectorRequest(reservation.reservationId)" data-target="#delete">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</p></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker6').datetimepicker({
				minDate : new Date(),
				format: 'MM/DD/YYYY HH:mm'
				
			});
			$('#datetimepicker7').datetimepicker({
				useCurrent : false,
				format: 'MM/DD/YYYY HH:mm'
			//Important! See issue #1075
			});
			$("#datetimepicker6").on("dp.change", function(e) {
				$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
			});
			$("#datetimepicker7").on("dp.change", function(e) {
				$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
			});
		});
	</script>
	<script src="assets/js/jqBootstrapValidation.js"></script>
	<script src="assets/js/reserveProjector.js"></script>
</body>
</html>