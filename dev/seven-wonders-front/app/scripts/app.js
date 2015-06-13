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
    'ngRoute'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
