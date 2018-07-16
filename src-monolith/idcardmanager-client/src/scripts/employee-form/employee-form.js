'use strict';

angular.module('employeeForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('employeeNew', {
                parent: 'app',
                url: '/employee/new',
                template: '<employee-form></employee-form>'
            })
    }]);
