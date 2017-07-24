app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');

}]);
app.config(['$routeProvider',function($routeProvider){
  $routeProvider.when('/home',{
    templateUrl:'public/views/home.html',
    controller:'homeController'
  }).when('/register',{
    templateUrl:'public/views/register.html',
    controller:'registerController'
  }).when('/login',{
  
    templateUrl:'public/views/login.html',
    controller:'loginController'
	
  }).otherwise({ redirectTo: '/login' });
}]);
