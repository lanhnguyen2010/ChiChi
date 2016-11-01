/**
 * Created by lanhnguyen on 04/08/2016.
 */
(function() {
  'use strict';

  angular
    .module('angularFirstApp')
    .controller('ProfileController', ProfileController);

  /** @ngInject */
  function ProfileController($timeout, webDevTec, MainService) {
    var vm = this;

    vm.user = [];

    activate();

    function activate() {
      getWebDevTec();
    }

    function getWebDevTec() {
      console.log(MainService.getCurrentUser());
      vm.user = webDevTec.getUser();

    }
  }
})();
