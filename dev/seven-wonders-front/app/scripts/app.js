'use strict';

/**
 * @ngdoc overview
 * @name sevenWondersFrontApp
 * @description
 * # sevenWondersFrontApp
 *
 * Main module of the application.
 */
angular
  .module('sevenWondersFrontApp', [
    'ngRoute',
    'ngCookies',
    'ngResource',
    'http-auth-interceptor',
    'pascalprecht.translate'
  ])
  .config(function($routeProvider, $translateProvider) {

    function isAuthenticated($http) {
      return $http.get('/api/rest/authenticate');
    }

    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        resolve: {
          isAuthenticated: isAuthenticated
        }
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

    $translateProvider.useStaticFilesLoader({
      prefix: 'locales/local-',
      suffix: '.json'
    });
    $translateProvider.preferredLanguage('en');
    $translateProvider.useCookieStorage();
  })
  .run(function($rootScope, $location) {

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function() {
      $location.path('/login').replace();
    });

    // Call when sucessfully login
    $rootScope.$on('event:auth-loginConfirmed', function() {
      $location.path('/').replace();
    });

  });
