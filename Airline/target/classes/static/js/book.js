var app = angular.module("bookFlightApp",[]);

app.controller('bookFlightController', ['$scope','BookFlightService','$window', 
  function ($scope,BookFlightService,$window) {
	$scope.bookOnLoad = function() {
		$scope.username=$window.sessionStorage.getItem('username');
		$scope.isAuthenticated=$window.sessionStorage.getItem('isAuthenticated');
	};	
	$scope.flights = [];
	 $scope.IsVisible = false;
      $scope.searchFlights = function () {    	  
    	  BookFlightService.searchFlights($scope.searchcriteria.origin,$scope.searchcriteria.destination,$scope.searchcriteria.flightDate,$scope.searchcriteria.count)
    	  .then(function success(response) {
    		  if(response.data.length <=0){
    			  	$scope.IsVisible = false;
  					$scope.errorMessage='No Records Found!';
  					$scope.message='';
    		  }else{    			    
    	          	$scope.flights= response.data;
    				$scope.errorMessage='';
    				$scope.message='';
    				$scope.IsVisible = true;
    		  }
          	
          },
  	    function error(response) { 
          	$scope.errorMessage='Error while getting the flights. Please try again later!';
			$scope.message='';
          });
      };
}]);

app.service("BookFlightService", ['$http', function($http){
	this.searchFlights = function searchFlights(origin, destination, flightDate, count){
		return $http({
			method: 'POST', 
			url: 'searchFlights',
				data: {
					origin: origin,
					destination: destination,
					flightDate: flightDate,
					count: count
				}						
		});
	}
}]);