'use strict';
angular.module('sevenWondersFrontApp')
  .controller('LoginCtrl', function($scope, authenticationService) {

    $scope.username = '';

    $scope.login = function() {
      authenticationService.login($scope.username);
    };

  });
