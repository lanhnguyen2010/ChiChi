(function() {
  'use strict';

  angular
    .module('angularFirstApp')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/main', {
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .when('/', {
        templateUrl: 'app/welcome/welcome.html',
        controller: 'WelcomeController',
        controllerAs: 'welcome'

      })
      .when('/login', {
        templateUrl: 'app/login/login.html',
        controller: 'LoginController',
        controllerAs: 'login'
      })
      .when('/profile', {
        templateUrl: 'app/profile/profile.html',
        controller: 'ProfileController',
        controllerAs: 'profile'
      })
      .otherwise({
        redirectTo: '/'
      });
  }

})();
