/**
 * Created by lanhnguyen on 26/10/2016.
 */
'use strict';

angular.module('angularFirstApp')
  .service('ProfileService',['$http', '$cookies',  function($http, $cookies) {
    $http.defaults.headers.common.Authorization = $cookies.get('tokenId');

    this.getPicked = function(data){
       return $http.get('/api/job/getPickedBy?userId='+data);
    }

    this.getPosted = function(data){
      return $http.get('/api/job/getPostedBy?userId='+data);
    }
  }]);
