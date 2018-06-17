'use strict';

var app = angular.module('app', []);
 
app.controller('robotController', function($scope, $http) {
	
	var urlBase = '';
	$scope.listRobots = [];
	
	$scope.selectedRobot;
	// for update message
	$scope.error = false;
	$scope.errorMessage;
	$scope.reportMessage;
	
	
	$scope.initializeRobot = function () {
		return $http.get(urlBase + '/robot/place/' + $scope.x_position + '/' + $scope.y_position + '/' + $scope.facing )
		.then(function successCallback(response) {
			if (response.data.error) {
				return null;
		     } else {
	           if (response.data !== null && response.data !== undefined) {
	        	   $scope.selectedRobot = response.data;
	        	   $scope.listRobots.push(response.data);
	        	   $scope.error = false;
	        	   console.log('initializeRobot:' + $scope.selectedRobot.idRobot);
	        	   $scope.drawRobot($scope.selectedRobot);
	           } else {
	               return null;
	           }
		     }
	    }, function errorCallback(response) {
	    	$scope.error = true;
	    	$scope.errorMessage = response.data.message;
	    });
	};
	$scope.moveRobot = function (robot) {
		$scope.removedRobot = robot; 
		return $http.get(urlBase + '/robot/move/' + robot.idRobot)
		.then(function successCallback(response) {
			if (response.data.error) {
				return null;
		     } else {
	           if (response.data !== null && response.data !== undefined) {
	        	   $scope.selectedRobot = response.data;
	  
	        	   for (var i in $scope.listRobots) {
        		     if ($scope.listRobots[i].idRobot == $scope.selectedRobot.idRobot) {
        		    	 $scope.removeRobot($scope.listRobots[i]);
        		    	 
        		    	 $scope.listRobots[i].x = $scope.selectedRobot.x;
        		    	 $scope.listRobots[i].y = $scope.selectedRobot.y;
        		    	 break; //Stop this loop, we found it!
        		     }
	        	   }
	        	   $scope.error = false;
	        	   console.log('initializeRobot:' +$scope.selectedRobot.idRobot);
	        	   
	        	   $scope.drawRobot($scope.selectedRobot);
	           } else {
	               return null;
	           }
		     }
	    }, function errorCallback(response) {
	    	$scope.error = true;
	    	$scope.errorMessage = response.data.message;
	    });
	};
	$scope.rotateRobot = function (robot, left_right) {
		return $http.get(urlBase + '/robot/rotate/' + robot.idRobot + '/' + left_right)
		.then(function successCallback(response) {
			if (response.data.error) {
				return null;
		     } else {
	           if (response.data !== null && response.data !== undefined) {
	        	   $scope.selectedRobot = response.data;
	  
	        	   for (var i in $scope.listRobots) {
        		     if ($scope.listRobots[i].idRobot == $scope.selectedRobot.idRobot) {
        		    	 $scope.listRobots[i].facing = $scope.selectedRobot.facing;

        		    	 break;
        		     }
	        	   }
	        	   $scope.error = false;
	           } else {
	               return null;
	           }
		     }
	    }, function errorCallback(response) {
	    	$scope.error = true;
	    	$scope.errorMessage = response.data.message;
	    });
	};
	$scope.reportRobot = function (robot) {
		return $http.get(urlBase + '/robot/report/' + robot.idRobot)
		.then(function successCallback(response) {
			if (response.data.error) {
				return null;
		     } else {
	           if (response.data !== null && response.data !== undefined) {
	        	   $scope.reportMessage = response.data.message;
	        	   $scope.error = false;
	           } else {
	               return null;
	           }
		     }
	    }, function errorCallback(response) {
	    	$scope.error = true;
	    	$scope.errorMessage = response.data;
	    });
	};
	
	// canvas drawing
	$scope.removeRobot = function (robot) {
		var canvas = document.getElementById("robotTabletopCanvas");
		var ctx = canvas.getContext("2d");
		ctx.clearRect(robot.x*50, 250 - robot.y*50, 50, 50);
	};
	$scope.drawRobot = function (robot) {
		var canvas = document.getElementById("robotTabletopCanvas");
		var ctx = canvas.getContext("2d");
		ctx.fillStyle = "#FF0000";
		ctx.fillRect(robot.x*50, 250 - robot.y*50, 50, 50);
	};
	
});


