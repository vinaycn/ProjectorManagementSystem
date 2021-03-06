/**
 * 
 */


var app = angular.module('projectorManagementAdmin',[])

app.controller('adminController',['$scope','adminService',function($scope,adminService){
	
	
	$scope.teams =[];
	$scope.projectors =[];
	
	$scope.team = {
			name: ""
	};
	
	$scope.projector = {
			name: ""
	};
	
	$scope.load = function(){
		$scope.getTeams();
		$scope.getProjectors();
	};
	
	 $scope.isTNameDuplicate= false;
	 $scope.isPNameDuplicate = false;
	 
	$scope.addTeam = function(){
		var isTeamDuplicate = false;
		$scope.teams.forEach(function (element) {
	         if (JSON.stringify(element.name).toUpperCase() === JSON.stringify($scope.team.name).toUpperCase()) {
	        	 isTeamDuplicate=true;
	        	
	         }
	     });
		
		if(!isTeamDuplicate){
		adminService.addTeam($scope.team.name,function(data){
			$scope.teams = data;
			$scope.team.name="";
		});
		}else{
			alert("Team Name already Exists!");
		}
	};
	
	$scope.deleteTeam = function(id){
			
			adminService.deleteTeam(id,function(data){
				$scope.teams = data;
			});
		};
	
	
	$scope.addProjector = function(){
		var isDuplicate = false;
		$scope.projectors.forEach(function (element) {
	         if (JSON.stringify(element.name).toUpperCase() === JSON.stringify($scope.projector.name).toUpperCase()) {       
	        	 isDuplicate = true;
	         }
	     });
		
		if(!isDuplicate){
			adminService.addProjector($scope.projector.name,function(data){
				$scope.projectors = data;
				$scope.projector.name="";
			});
		}else{
			alert("Project Name already Exists!");
		}
		
	};
	
	$scope.deleteProjector = function(id){
		
		adminService.deleteProjector(id,function(data){
			$scope.projectors = data;
		});
	};
	
	$scope.getTeams = function(){
		adminService.getTeams(function(data){
			$scope.teams = data;
		});
	};
	
	$scope.getProjectors = function(){
		adminService.getProjectos(function(data){
			$scope.projectors = data;
		});	
	};
	
}]);


app.service('adminService',['$http',function($http){

	this.addTeam = function(name, callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		var data ='name='+name;
		$http({
			url : 'add-team',
			method : 'POST',
			data: data
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	this.deleteTeam = function(id, callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		var data ='id='+id;
		$http({
			url : 'delete-team',
			method : 'POST',
			data: data
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	this.addProjector = function(name, callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		var data ='name='+name;
		$http({
			url : 'add-projector',
			method : 'POST',
			data: data
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	this.deleteProjector = function(id, callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		var data ='id='+id;
		$http({
			url : 'delete-projector',
			method : 'POST',
			data: data
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	
	this.getTeams = function(callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		$http({
			url : 'get-teams',
			method : 'GET',
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	
	this.getProjectos = function(callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		$http({
			url : 'get-projectors',
			method : 'GET',
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
}]);

