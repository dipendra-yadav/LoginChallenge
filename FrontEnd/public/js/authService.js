app.factory('RegistrationService', function ($http, $q, $location) {
    var fac = {}
    fac.SaveFormData = function (register) {
        var defer = $q.defer();
        console.log(register);
                  
        $http({
            url: 'http://localhost:3011/AppRestServices/adduser',
            method: 'POST',
            data: register,
            headers: { 'content-type': 'application/json' }
        });
		
        return defer.promise;
		
    }
    fac.loginUserData = function (userlogin) {
        /*var defer = $q.defer();
        console.log(userlogin);
        $http({
            url: 'http://localhost:3011/AppRestServices/login',
            method: 'POST',
            data: userlogin,
            headers: { 'content-type': 'application/json' }
        })
		.then(function(response){
            $location.path('/home');
        });
		
        return defer.promise;
		
		*/
		
		return $http.post("http://localhost:3011/AppRestServices/"+ "login", userlogin)
		
		
    }
    fac.logout = function (userlogin) {
        /*var defer = $q.defer();
        $http({
            url: 'http://localhost:3011/AppRestServices/logout',
            method: 'PUT',
            data: userlogin,
            headers: { 'content-type': 'application/json' }
        });
        return defer.promise;
		*/
		return $http.put("http://localhost:3011/AppRestServices/logout")
    }
    return fac;
});
