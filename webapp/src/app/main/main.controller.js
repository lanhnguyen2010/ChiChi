(function () {
  'use strict';

  angular
    .module('angularFirstApp')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($scope, $rootScope, $timeout, webDevTec, $mdDialog, $mdMedia, MainService) {
    var vm = this;

    vm.awesomeThings = [];
    vm.classAnimation = '';
    vm.creationDate = 1470285994673;
    vm.showToastr = showToastr;
    vm.user = {};


    activate();

    function activate() {
      getUserProfile();
      getWebDevTec();
      $timeout(function () {
        vm.classAnimation = 'rubberBand';
      }, 4000);
    }

    function showToastr() {
      vm.classAnimation = '';
    }

    function getUserProfile() {
      MainService.getCurrentUser().then(function (data) {
        vm.user = data.data;
      }, function (error) {
        console.log(error);
      });

    }

    function getWebDevTec() {
      MainService.getAllJobs().then(function(data){
        console.log(data)
        vm.awesomeThings = data.data;

      });

    }

    $scope.pick = function(jobId){
      MainService.pickJob(jobId).then(function(data){
        console.log(data)
      })
    }

    $scope.showPost = function (ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
      $mdDialog.show({
          controller: DialogController,
          templateUrl: 'app/main/post.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true,
          fullscreen: useFullScreen
        })
        .then(function (answer) {
          $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
          $scope.status = 'You cancelled the dialog.';
        });
      $scope.$watch(function () {
        return $mdMedia('xs') || $mdMedia('sm');
      }, function (wantsFullScreen) {
        $scope.customFullscreen = (wantsFullScreen === true);
      });
    };
    function DialogController($scope, $mdDialog, $filter, MainService) {
      $scope.newJob = {};
      $scope.levels = ['SE', 'SSE', 'SA', 'QA', 'SQA'];
      $scope.myCroppedImage = '';
      $scope.myImage='';
      $scope.myCroppedImage = '';
      $scope.toggle = true;

      $scope.chooseFile = function(){
        document.getElementById('btnSelectFile').click();

      };

      $scope.hide = function () {
        $mdDialog.hide();
      };
      $scope.cancel = function () {
        $mdDialog.cancel();
      };

      $scope.createJob = function(){
        $scope.newJob.startTime = $scope.processDate($scope.startTime);
        $scope.newJob.deadLine = $scope.processDate($scope.deadLine);

        MainService.createJob($scope.newJob, $scope.picFile).then(function(data){
          getWebDevTec();
        }, function(error){
          window.alert(error)
        });
        $mdDialog.hide();

      };


      $scope.processDate = function (dt) {
        if (dt && dt != null) {
          return $filter('date')(dt, 'MM/dd/yyyy');
        }
      };



    }
  }

})();
