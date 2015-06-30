'use strict';

/**
 * @ngdoc function
 * @name sevenWondersFrontApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sevenWondersFrontApp
 */
angular.module('sevenWondersFrontApp')
  .controller('MainCtrl', function ($scope, authenticationService) {

    $scope.logout = function() {
      authenticationService.logout();
    };

  });
