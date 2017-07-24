 app.controller('registerController', function($scope, RegistrationService,$location){
  $scope.SaveData = function () {
             $scope.submitted = true;
             $scope.message = '';
                 $scope.submitText = 'Please Wait...';
                 RegistrationService.SaveFormData($scope.register)
				 .then(function (response)
				 {
                     alert(response);
                     if (response == 'Success') {
                         //have to clear form here
                         
                         ClearForm();
						 $location.path('/login');
                     }
                     $scope.submitText = "Save";
					 
                 });


                var regExpress = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
                 function passwordvcheck(passwordCheck){
                   var passwordCheck = $scope.register.password;
                   console.log(passwordCheck);
                   if(regExpress.test(passwordCheck)){
                     $scope.passwordvalidatemsg = "Password is strong"
                     return (passwordCheck);
                   }else{
                     $scope.passwordinvalidatemsg = "Password must contain Uppercase, Number and Special character"
                   }
                 }
                 passwordvcheck();

     }
});
