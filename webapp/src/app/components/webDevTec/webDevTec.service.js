(function () {
  'use strict';

  angular
    .module('angularFirstApp')
    .service('webDevTec', webDevTec);

  /** @ngInject */
  function webDevTec() {
    var data = [
      {
        'title': 'Training AngularJS',
        'url': 'https://angularjs.org/',
        'description': 'HTML enhanced for web apps!',
        'logo': 'angular.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training BrowserSync',
        'url': 'http://browsersync.io/',
        'description': 'Time-saving synchronised browser testing.',
        'logo': 'browsersync.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training GulpJS',
        'url': 'http://gulpjs.com/',
        'description': 'The streaming build system.',
        'logo': 'gulp.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training Jasmine',
        'url': 'http://jasmine.github.io/',
        'description': 'Behavior-Driven JavaScript.',
        'logo': 'jasmine.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training Karma',
        'url': 'http://karma-runner.github.io/',
        'description': 'Spectacular Test Runner for JavaScript.',
        'logo': 'karma.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training Protractor',
        'url': 'https://github.com/angular/protractor',
        'description': 'End to end test framework for AngularJS applications built on top of WebDriverJS.',
        'logo': 'protractor.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training Angular Material Design',
        'url': 'https://material.angularjs.org/#/',
        'description': 'The Angular reference implementation of the Google\'s Material Design specification.',
        'logo': 'angular-material.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      },
      {
        'title': 'Training Sass (Node)',
        'url': 'https://github.com/sass/node-sass',
        'description': 'Node.js binding to libsass, the C version of the popular stylesheet preprocessor, Sass.',
        'logo': 'node-sass.png',
        'totalTime': '10 hours',
        'startDate': "12/11/2016",
        'deadLine': "10/12/2016"
      }
    ];

    var user = {
      'userName':'lanhnguyen',
      'firstName': 'Lanh',
      'lastName': "Nguyen",
      'pic': 'assets/images/yeoman.png'
    };

    this.getUser = getUser;
    function getUser() {
      return user;
    }

    this.getTec = getTec;

    function getTec() {
      return data;
    }
  }

})();
