"use strict";angular.module("ownerForm").controller("OwnerFormController",["$http","$state","$stateParams",function(n,o,r){var e=this,t=r.ownerId||0;t?n.get("owners/"+t).then(function(n){e.owner=n.data}):e.owner={},e.submitOwnerForm=function(){var r=e.owner.id;r?n.put("owners/"+r,e.owner).then(function(){o.go("ownerDetails",{ownerId:t})}):n.post("owners",e.owner).then(function(){o.go("owners")})}}]);