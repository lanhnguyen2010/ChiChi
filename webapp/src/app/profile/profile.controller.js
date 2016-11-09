/**
 * Created by lanhnguyen on 04/08/2016.
 */
(function() {
  'use strict';

  angular
    .module('angularFirstApp')
    .controller('ProfileController', ProfileController);

  /** @ngInject */
  function ProfileController($timeout,  MainService, ProfileService) {
    var vm = this;

    vm.user={};
    vm.picked=[];
    vm.posted=[];

    activate();

    function activate() {
      MainService.getCurrentUser().then(function(data){
        vm.user = data.data;
        console.log(vm.user);
        ProfileService.getPicked(vm.user.id).then(function(data){
          vm.picked = data.data;
          console.log(vm.picked);
        }, function(error){
          console.log(error);
        });
        ProfileService.getPosted(vm.user.id).then(function(data){
          vm.posted = data.data;
          console.log(vm.posted);
        }, function(error){
          console.log(error);
        });
      }, function(error){

      });


    }


  }
})();
