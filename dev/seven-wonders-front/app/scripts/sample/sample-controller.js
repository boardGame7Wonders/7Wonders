'use strict';

/**
 * @ngdoc function
 * @name sevenWondersFrontApp.controller:SampleCtrl
 * @description
 * # SampleCtrl
 * Controller of the sevenWondersFrontApp
 */
angular.module('sevenWondersFrontApp')
  .controller('SampleCtrl', function ($scope, $http) {

    $scope.errorMessage = null;

    $scope.getCardList = function() {
      $http.get('/api/rest/card/all')
        .success(function(data) {
          $scope.cardList = data;
        })
        .error(function(error, status) {
          if (404 === status) {
            $scope.errorMessage = 'Card list not found!';
          } else {
            $scope.errorMessage = 'Unable to get card list!';
          }
        });
    };

    $scope.getPlayer = function() {
      $http.get('/api/rest/player/p1')
        .success(function(data) {
          $scope.player = data;
        })
        .error(function(error, status) {
          if (404 === status) {
            $scope.errorMessage = 'Player not found!';
          } else {
            $scope.errorMessage = 'Unable to get player!';
          }
        });
    };

    $scope.playCard = function() {
      $http.post('/api/rest/player/p1/play', {
        id: 0,
        name: 'Lumber Yard',
        age: 1
      })
        .success(function(data) {
          $scope.player = data;
        })
        .error(function(error, status) {
          if (404 === status) {
            $scope.errorMessage = 'Player not found!';
          } else {
            $scope.errorMessage = 'Unable to get player!';
          }
        });
    };

  });
