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
    'pascalprecht.translate',
    'sevenWondersFrontApp.auth'
  ])
  .config(function($routeProvider, $translateProvider) {

    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        needAuthentication: true
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        isLoginPage: true
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
  .run(function($rootScope, $location, authenticationService, Session) {

    // Call when url changes
    $rootScope.$on('$routeChangeStart', function(event, next) {
      if (next.needAuthentication) {
        authenticationService.valid();
      } else if (next.isLoginPage) {
        if (Session.isConnected()) {
          $location.path('/').replace();
        }
      }
    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function() {
      $location.path('/login').replace();
    });

    // Call when sucessfully login
    $rootScope.$on('event:auth-loginConfirmed', function() {
      $location.path('/').replace();
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function() {
      $location.path('/login').replace();
    });

  });
