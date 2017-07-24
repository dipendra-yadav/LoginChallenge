app.controller('loginController', function($scope,RegistrationService, $location, $rootScope, $cookieStore){ 
    $scope.loginUser = function (){
				console.log("Inside the login controller -->login user");
				RegistrationService.loginUserData($scope.userlogin).then(function (response) {
				  console.log("loginUser success function**********");
                  console.log(response);
                  $scope.user= response.data;
                  $rootScope.currentUser=$scope.user
			      $cookieStore.put('currentUser',$rootScope.currentUser)
				  console.log("after cookieStore inside -->login user");
                  $location.path("/home")
                 
                 },
				 function(response) {
			        console.log("loginUser failure function**********");
					console.log("" + response.status)
					$scope.message = "Invalid Username or Password"
			        $location.path("/login")
		})
  
     }
	 
	 
	 
	 // logout button
	$scope.logout=function(){
		
		delete $rootScope.currentUser
		
		RegistrationService.logout()
		.then(function(response){
			
			console.log("**********response.status => " + response.status)
			$location.path('/login')
		},
		function(response){
			
			console.log("**********response.status => " + response.status)
		})
		
	}
	
     
});
