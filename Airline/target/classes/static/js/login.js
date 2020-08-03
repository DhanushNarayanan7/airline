var app = angular.module("loginApp",[]);

app.controller('loginController', ['$scope','LoginService','$window',
  function ($scope,LoginService, $window) {
      $scope.login = function () {
    	  LoginService.login($scope.user.username, $scope.user.password)
            .then(function success(response) {            	
            	console.log('login success');            	
            	console.log(response.status);
            	 $window.sessionStorage.setItem('username',$scope.user.username);
            	 $window.sessionStorage.setItem('isAuthenticated',true);
            	 $window.location.href = '/';
            },
    	    function error(response) {
            	$scope.errorMessage='Error while login';
            	$window.sessionStorage.setItem('isAuthenticated',false);
            	console.log(response.status);
            });
      };
}]);

app.service("LoginService", ['$http', function($http){
	this.login = function login(username, password){
		var data = $.param({
			username: username,
			password: password
        });
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
        }
		return  $http.post('login', data, config);
	}
}]);