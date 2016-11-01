'use strict';

angular.module('angularFirstApp')
  .service('LoginService', function($http) {

    this.login = function (data) {
      return $http.get('/api/authenticate', {
        params: {idToken :data }
      });
    };
    this.testAPI = function (data) {
      return $http.get('/api/test',{
        params: {'id_token': data}
      });
    };


  });
