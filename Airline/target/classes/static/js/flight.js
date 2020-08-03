var app = angular.module("searchFlightsApp",[]);

app.controller('searchFlightsController', ['$scope','SearchFlightsService','$window','$http',
  function ($scope,SearchFlightsService,$window,$http) {

	$scope.flightOnLoad = function() {
		$scope.username=$window.sessionStorage.getItem('username');
		$scope.isAuthenticated=$window.sessionStorage.getItem('isAuthenticated');
		$scope.isSearchEnabled= true;
	};	
	
	$scope.logout = function() {
		$window.sessionStorage.setItem('username','');
		$window.sessionStorage.setItem('isAuthenticated', false);
		 $window.location.href = '/';
	}
	
	$scope.flights = [];
	 $scope.IsVisible = false;
      $scope.searchFlights = function () {    	  
    	  SearchFlightsService.searchFlights($scope.searchcriteria.origin,$scope.searchcriteria.destination,$scope.searchcriteria.flightDate,$scope.searchcriteria.count)
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
      
      
  	
 	 $scope.isBookingEnabled=false;
      $scope.bookFlight= function(nameOfAirline,flightNumber,origin,destination,flightDate,flightTime,currency,fare) {
     	$scope.isBookingEnabled=true;
     	$scope.isSearchEnabled= false;
     	$scope.bookFlight.nameOfAirline= nameOfAirline;
     	$scope.bookFlight.flightNumber= flightNumber;
     	$scope.bookFlight.origin = origin;     	
     	$scope.bookFlight.destination = destination;
     	$scope.bookFlight.flightDate = flightDate;
     	$scope.bookFlight.flightTime = flightTime;
     	$scope.bookFlight.currency= currency;
    	$scope.bookFlight.fare= fare;     	
  	};
  	
  	$scope.bookFlightWithPassenger = function(bookFlight,passenger1, passenger2, passenger3, passenger4) {
  		 $scope.passengers= [];
  		 $scope.passengers.push({
	  				 firstName: passenger1.firstName,
	  				 lastName: passenger1.lastName,
	  				 gender: passenger1.gender,
	  				 mobileNumber: passenger1.mobileNumber
	  		 },
	  		{
					 firstName: passenger2.firstName,
					 lastName: passenger2.lastName,
					 gender: passenger2.gender,
					 mobileNumber: passenger2.mobileNumber
			 },
			 {
					 firstName: passenger3.firstName,
					 lastName: passenger3.lastName,
					 gender: passenger3.gender,
					 mobileNumber: passenger3.mobileNumber
			 },
			 {
					 firstName: passenger4.firstName,
					 lastName: passenger4.lastName,
					 gender: passenger4.gender,
					 mobileNumber: passenger4.mobileNumber
			 });
  		 bookFlight.passengers= $scope.passengers;
  		 console.log(bookFlight.passengers);
  		 console.log($scope.passengers);
  		 console.log(bookFlight);
  		 $scope.isBookingEnabled=false;
  		 $scope.isSearchEnabled= false;
  		 $scope.isBookingConfirmEnabled=true;
  		 $scope.bookingDetails='';
  		 
  		 $http({
			method: 'POST', 
			url: 'bookFlight',
				data: {
					flightNumber: bookFlight.flightNumber,
					origin: bookFlight.origin,
					destination: bookFlight.destination,
					fare: bookFlight.fare,
					flightDate: bookFlight.flightDate,
					flightTime: bookFlight.flightTime,
					passengers: bookFlight.passengers					
				}						
		}).then(function success(response) {
				console.log(response.data);
				$scope.bookingDetails=response.data;
		    },
		    function error(response) { 
		    	console.log(response.status);
		    });
  	};
      
}]);


app.service("SearchFlightsService", ['$http', function($http){
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

