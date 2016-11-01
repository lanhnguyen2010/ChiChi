/**
 * Created by lanhnguyen on 26/10/2016.
 */
'use strict';

angular.module('angularFirstApp')
  .service('MainService',['$http', '$cookies', 'Upload',  function($http, $cookies, Upload) {
    $http.defaults.headers.common.Authorization = $cookies.get('tokenId');

    this.getUser = function (data) {
      return $http.get('/api/user/getUser', {
        params: {
          'userId' : data
        }
      });
    };
    this.getCurrentUser = function(){
      return $http.get('/api/user/getCurrentUser');
    };

    this.pickJob = function(data){
      return $http.get('/api/job/pick', {
        params: {
          'jobId' : data
        }
      });
    }

    this.createJob = function(newJob, logo){
      return Upload.upload({
        url: 'api/job/create',
        fields:{'title':  newJob.title,
                'totalHours': newJob.totalHours,
                'description': newJob.description,
                'startTime': newJob.startTime,
                'deadLine': newJob.deadLine,
                'requiredLevel': newJob.requiredLevel},
        file: logo
      });

    };

    this.getAllJobs = function(){
      return $http.get("api/job/getAll");
    };
    this.testAPI = function (data) {
      return $http.get('/api/test',{
        params: {'id_token': data}
      });
    };


  }]);
