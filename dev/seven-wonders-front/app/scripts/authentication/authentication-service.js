'use strict';
angular.module('sevenWondersFrontApp.auth', [])
  .factory('authenticationService', function($rootScope, $http, authService, Session) {

    function login(username) {
      $rootScope.authenticationError = false;
      var data = 'j_username=' + username;
      return $http.post('/app/login', data, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          ignoreAuthModule: true //deactivate the module http-auth-interceptor
        })
        .then(function() {
          authService.loginConfirmed(data);
        })
        .catch(function(error) {
          $rootScope.authenticationError = error.data.message;
        });
    }

    function valid() {
      $http.get('/api/rest/authenticate')
        .then(function() {
          return $http.get('/api/rest/userDetails');
        })
        .then(function(result) {
          Session.create(result.data);
          $rootScope.account = Session;
        })
        .catch(function(error) {
          Session.invalidate();
        });
    }

    return {
      login: login,
      valid: valid
    };
  });
