'use strict';

/**
 * @ngdoc function
 * @name sevenWondersFrontApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sevenWondersFrontApp
 */
angular.module('sevenWondersFrontApp')
  .controller('MainCtrl', function ($scope, $http, $interval, authenticationService) {
    $scope.$watch('authenticated', function(newValue, oldValue) {
      if (oldValue !== newValue) {
        if (true === newValue) {
          $scope.refresh = $interval(function() {
            authenticationService.valid();
          }, 10000);
        } else if (false === newValue) {
          $interval.cancel($scope.refresh);
        }
      }
    });

    $scope.logout = function() {
      authenticationService.logout();
    };

    $scope.kick = function(player) {
      $http.post('/api/rest/kickPlayer/' + player.login);
    };

  });
