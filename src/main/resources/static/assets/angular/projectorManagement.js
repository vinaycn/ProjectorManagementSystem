/**
 * 
 */

/**
 * 
 */


var app = angular.module('projectorManagement',[])
          .controller('projectorManagementController',function($scope,pmService){
	
  
	$scope.teams =[];
	$scope.teamReservatios=[];
	
	$scope.rates
	
	
	
	$scope.load = function(){
		$scope.getTeams();
		$scope.getProjectorReservations("All");
	};
	
	$scope.reserveProjectorId;
	
	
	$scope.getTeams = function(){
		pmService.getTeams(function(data){
			$scope.teams = data;
			
		});
	};
	
	$scope.getProjectorReservations =function(){
	
		pmService.getReservedProjectors(function(data){
			$scope.teamReservatios = data;
		});
	}
	
	$scope.deleteProjectorRequest = function(id){
		alert(id);
		pmService.deleteProjectorRequest(id,function(data){
			$scope.teamReservatios = data;
		});
	}
	
	
});


app.service('pmService',function($http){
	
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
	
	
	this.getReservedProjectors = function(callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		
		$http({
			url : 'getReserved-projectors',
			method : 'GET',
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};
	
	this.deleteProjectorRequest = function(id,callBack) {
		$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		var data = 'id='+id;
		$http({
			url : 'delete-projectorRequest',
			method : 'POST',
			data: data
		}).then(function(response) {
			console.log(response.data);
			callBack(response.data);
		}, function(failure) {
			alert(failure);
		});
	};

});




