//loading the 'login' angularJS module

var login = angular.module('login', ['ui.router']);
login.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
		$locationProvider.html5Mode(true);
		$stateProvider.state('login', {	
			url : '/',
			views: {
	            'header': {
	                templateUrl : 'templates/header.html',
	            },
	            'content': {
	                templateUrl : 'templates/login.html',
	            },
			}
		})
		$urlRouterProvider.otherwise('/');
	});
//defining the login controller
login.controller('login', function($scope, $http,$state) {
	//Initializing the 'invalid_login' and 'unexpected_error' 
	//to be hidden in the UI by setting them true,
	//Note: They become visible when we set them to false
	
	$scope.invalid_login = true;
	$scope.validlogin = true;
	$scope.source = [];
	$scope.destination=[];
	$scope.submit = function() {
		$http({
			method : "POST",
			url : '/checklogin',
			data : {
				"username" : $scope.username,
				"password" : $scope.password
			}
		}).success(function(data) {
			//checking the response data for statusCode
			if (data.statusCode == 200) {
			
				$scope.source.push($scope.username);
				$scope.destination.push($scope.password);
				console.log($scope.source);
				console.log($scope.destination);
				$scope.validlogin = false;
				$scope.invalid_login = true;
				}
				//Making a get call to the '/redirectToHomepage' API
				//window.location.assign("/homepage"); 
		}).error(function(error) {
			$scope.validlogin = true;
			$scope.invalid_login = true;
		});
	};
})
