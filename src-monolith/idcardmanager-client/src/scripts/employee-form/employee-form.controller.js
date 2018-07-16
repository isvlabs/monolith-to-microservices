'use strict';

angular.module('employeeForm')
    .controller('EmployeeFormController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;

        var employeeId = $stateParams.employeeId || 0;

        if (!employeeId) {
            self.employee = {};
        } else {
            $http.get("employees/" + employeeId).then(function (resp) {
                self.employee = resp.data;
            });
        }

        self.submitEmployeeForm = function () {
            var id = self.employeeId.id;

            if (id) {
                $http.put('employees/' + id, self.employee).then(function () {
                    $state.go('employeeDetails', {employeeId: employeeId});
                });
            } else {
                $http.post('employees', self.employee).then(function () {
                    $state.go('employees');
                });
            }
        };
    }]);
