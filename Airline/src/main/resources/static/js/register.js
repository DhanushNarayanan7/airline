var app = angular.module("registerApp",[]);

app.controller('registerController', ['$scope','RegisterService', 
  function ($scope,RegisterService) {
      $scope.addUser = function () {
    	  RegisterService.addUser($scope.user.firstName, $scope.user.lastName, $scope.user.mobileNumber,
					$scope.user.gender,$scope.user.userName,$scope.user.password)
            .then(function success(response) {
            	$scope.message='Registration Successful! Click login ';
            	console.log(response.status);
            	console.log(response.data);
            	$scope.user = '';
				$scope.errorMessage='';
            },
    	    function error(response) {
            	$scope.errorMessage='Error occured during registration. Try again';
            	$scope.user = '';
				$scope.message='';
            });
      };
}]);

app.service("RegisterService", ['$http', function($http){
	this.addUser = function addUser(firstName, lastName, mobileNumber, gender, userName, password){
		return $http({
			method: 'POST', 
			url: 'register',
			data: {
				firstName: firstName,
				lastName: lastName,
				mobileNumber: mobileNumber,
				gender: gender,
				userName: userName,
				password: password
			}				
		});
	}
}]);