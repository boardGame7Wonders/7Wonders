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
          authService.loginConfirmed();
        })
        .catch(function(error) {
          $rootScope.authenticationError = error.data.message;
        });
    }

    function valid() {
      $http.get('/api/rest/authenticate')
        .then(function() {
          return $http.get('/api/rest/playersDetails');
        })
        .then(function(result) {
          Session.create(result.data);
          $rootScope.context = Session.context;
          $rootScope.authenticated = true;
        })
        .catch(function(error) {
          Session.invalidate();
          $rootScope.context = null;
          $rootScope.authenticated = false;
        });
    }

    function logout() {
      $rootScope.authenticationError = false;

      $http.get('/app/logout').finally(function() {
        authService.loginCancelled();
      });
    }

    return {
      login: login,
      valid: valid,
      logout: logout
    };
  });
