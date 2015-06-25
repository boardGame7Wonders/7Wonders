'use strict';
angular.module('sevenWondersFrontApp')
  .controller('LoginCtrl', function($rootScope, $scope, $http, $resource, Session, authService) {
    var accountService = $resource('/api/rest/userDetails', {}, {});

    $scope.login = function() {
      $rootScope.authenticationError = false;
      var data = 'j_username=' + $scope.username;
      return $http.post('/app/login', data, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          ignoreAuthModule: true //deactivate the module http-auth-interceptor
        })
        .then(function() {
          return accountService.get().$promise;
        })
        .then(function(data) {
          Session.create(data);
          $rootScope.account = Session;
          authService.loginConfirmed(data);
          return true;
        })
        .catch(function(error) {
          $rootScope.authenticationError = error;
          Session.invalidate();
          throw error;
        });
    };
  });
