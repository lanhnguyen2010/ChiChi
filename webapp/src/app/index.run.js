(function() {
  'use strict';

  angular
    .module('angularFirstApp')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope) {
    $rootScope.userId='';

    $log.debug('runBlock end');
  }

})();
