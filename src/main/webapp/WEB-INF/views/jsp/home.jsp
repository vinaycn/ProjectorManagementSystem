<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="projectorManagementAdmin">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>
<script src="assets/angular/admin.js"></script>
<title>Projector Management System</title>
</head>
<body ng-controller="adminController" ng-init="load()">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Welcome to Projector Management System</h1>
				<span class="pull-right"> <a href="projector-reserve"
					class="btn btn-default">Request Projectors</a>
				</span>
			</div>
		</div>
	</div>

	<br />
	<br />
	<br />

	<div class="container">
		<div class="row">
			<div class="col-md-6">

				<form class="form-inline" name="teamForm"
					ng-submit="teamForm.$valid&&addTeam()">
					<p style="color: Red" ng-show="teamForm.team.$error.required">
						Team Name is Required!</p>
					<p style="color: Red"
						ng-show="teamForm.team.$dirty&&teamForm.team.$error.pattern">
						Please Enter an Valid Name!</p>
					<div class="form-group">
						<label for="exampleInputName2">Name of the Team</label> <input
							type="text" class="form-control" name="team" ng-model="team.name"
							ng-change="checkTeamName()" id="exampleInputName2"
							placeholder="Enter Team Name.." required="required"
							ng-pattern="/^[a-z0-9]+$/i">
					</div>

					<button type="submit" ng-disabled="isTNameDuplicate" class="btn btn-default">Add Team</button>

				</form>
				<table class="table table-hover">
					<caption>Teams List</caption>
					<thead>
						<tr>
							<th>#</th>
							<th>Team Name</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="team in teams">
							<td>{{$index + 1}}</td>
							<td>{{team.name}}</td>
							<td><p data-placement="top" data-toggle="tooltip"
									title="Delete">
									<button class="btn btn-danger btn-xs" data-title="Delete"
										data-toggle="modal" ng-click="deleteTeam(team.teamId)"
										data-target="#delete">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</p></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="col-md-6">
				<form class="form-inline" name="projectorForm"
					ng-submit="projectorForm.$valid&&addProjector()">
					<p style="color: Red"
						ng-show="projectorForm.projector.$error.required">Projector
						Name is Required!</p>
					<p style="color: Red"
						ng-show="projectorForm.projector.$dirty&&projectorForm.projector.$error.pattern">
						Please Enter an Valid Name!</p>
					<div class="form-group">
						<label for="exampleInputName2">Name of the Projector</label> <input
							type="text" name="projector" class="form-control"
							ng-model="projector.name" ng-change="projectorNameValidation()"
							ng-pattern="/^[a-z0-9]+$/i" id="exampleInputName2"
							required="required" placeholder="Enter Projector Name..">

					</div>
					<button type="submit" class="btn btn-default">Add
						Projector</button>


				</form>
				<table class="table table-hover">
					<caption>Projector List</caption>
					<thead>
						<tr>
							<th>#</th>
							<th>Projector Name</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="projector in projectors">
							<td>{{$index + 1}}</td>
							<td>{{projector.name}}</td>
							<td><p data-placement="top" data-toggle="tooltip"
									title="Delete">
									<button class="btn btn-danger btn-xs" data-title="Delete"
										data-toggle="modal" ng-click="deleteProjector(projector.id)"
										data-target="#delete">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</p></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>

</body>
</html>