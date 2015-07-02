'use strict';

angular.module('sevenWondersFrontApp.auth')
  .factory('authService', ['$rootScope', function($rootScope) {
    return {
      /**
       * Call this function to indicate that authentication was successfull.
       * @param data an optional argument to pass on to $broadcast
       */
      loginConfirmed: function(data) {
        $rootScope.$broadcast('event:auth-loginConfirmed', data);
      },

      /**
       * Call this function to indicate that authentication should not proceed.
       * All deferred requests will be abandoned.
       * @param data an optional argument to pass on to $broadcast.
       */
      loginCancelled: function(data) {
        $rootScope.$broadcast('event:auth-loginCancelled', data);
      }
    };
  }])
  /**
   * $http interceptor.
   * On 401 response (without 'ignoreAuthModule' option) stores the request
   * and broadcasts 'event:auth-loginRequired'.
   * On 403 response (without 'ignoreAuthModule' option) discards the request
   * and broadcasts 'event:auth-forbidden'.
   */
  .config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push(['$rootScope', '$q', function($rootScope, $q) {
      return {
        responseError: function(rejection) {
          if (!rejection.config.ignoreAuthModule) {
            switch (rejection.status) {
              case 401:
                var deferred = $q.defer();
                $rootScope.$broadcast('event:auth-loginRequired', rejection);
                return deferred.promise;
              case 403:
                $rootScope.$broadcast('event:auth-forbidden', rejection);
                break;
            }
          }
          // otherwise, default behaviour
          return $q.reject(rejection);
        }
      };
    }]);
  }]);
