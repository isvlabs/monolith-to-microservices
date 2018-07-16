'use strict';

angular.module('employeeList')
    .controller('EmployeeListController', ['$http', function ($http) {
        var self = this;

        $http.get('employees/list').then(function (resp) {
            self.employees = resp.data;
        });
    }]);
