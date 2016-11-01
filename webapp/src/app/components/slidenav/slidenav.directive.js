/**
 * Created by lanhnguyen on 04/08/2016.
 */
(function() {
  'use strict';

  angular
    .module('angularFirstApp')
    .directive('acmeSlidenav', acmeSlidenav);

  /** @ngInject */
  function acmeSlidenav() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/slidenav/slidenav.html',
      scope: {
        creationDate: '='
      },
      controller: SlideNavController,
      controllerAs: 'vm',
      bindToController: true
    };

    return directive;

    /** @ngInject */
    function SlideNavController($scope, $mdSidenav) {
      var vm = this;

      // "vm.creationDate" is available by directive option "bindToController: true"
      vm.relativeDate = moment(vm.creationDate).fromNow();
    }
  }

})();
