'use strict';
angular.module('sevenWondersFrontApp.auth')
  .factory('Session', function() {

    this.create = function(data) {
      if (null === data || angular.isUndefined(data) || !angular.isObject(data)) {
        throw new Error('Can\'t create session with null or not object');
      }

      this.user = data;
    };

    this.invalidate = function() {
      this.user = null;
    };

    this.isConnected = function() {
      return !!this.user;
    };

    return this;
  });
