/**
 * Created by lanhnguyen on 04/08/2016.
 */
(function () {
  'use strict';

  angular
    .module('angularFirstApp')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($scope,$rootScope, $location,$timeout, $http, $window, LoginService, $cookies) {
    $timeout(function (){
      gapi.load('auth2', function() {
        gapi.auth2.init();
      });
    });

    $timeout(function () {
      $window.gapi.signin2.render('googleButtonPlaceholder', {
        'scope': 'https://www.googleapis.com/auth/plus.login',
        'width': 300,
        'height': 36,
        'longtitle': true,
        'theme': 'light',
        'onsuccess': $scope.federateWithGoogle
      });
    });
    $scope.federateWithGoogle = function(googleUser){
      var id_token = googleUser.getAuthResponse().id_token;
      LoginService.login(id_token).then(function success(data){
        $http.defaults.headers.common.Authorization = id_token;
        $cookies.put('tokenId', id_token);
        $location.path("/main");
      }, function error(data){


      });

    };

  }
})();
