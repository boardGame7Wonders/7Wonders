'use strict';

/**
 * @ngdoc function
 * @name sevenWondersFrontApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sevenWondersFrontApp
 */
angular.module('sevenWondersFrontApp')
  .controller('MainCtrl', function($scope, $http, $rootScope, authenticationService, Session) {

    $scope.logout = function() {
      authenticationService.logout();
    };

    $scope.kick = function(player) {
      $http.post('/api/rest/kickPlayer/' + player.login, {}, {
          ignoreAuthModule: true //deactivate the module http-auth-interceptor
        })
        .then(function(result) {
          Session.create(result.data);
          $rootScope.context = Session.context;
        }, function() {});
    };

  });
