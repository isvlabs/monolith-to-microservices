'use strict';

angular.module('employeeList', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('employees', {
                parent: 'app',
                url: '/list',
                template: '<employee-list></employee-list>'
            })
    }]);

