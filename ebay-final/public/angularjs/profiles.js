var app = angular.module("profile", []);

app.controller('profController',function ($scope, $http){ 
	
	//$scope.Now = function(ITEMCODE){
	//	$scope.value = ITEMCODE;
	//	console.log($scope.value);
	window.onload = function(){
		
		$http({
			method :"GET",
			url : "/profileDetail",
			data : {
				
			}
		}).success(function(response){
			$scope.newResult1 = response.jsonParse;
			$scope.newResult2 = response.jsonParse1;
			$scope.ROW_COUNT1= response.rowcount;
			$scope.ROW_COUNT2= response.rowcount1;
			//$scope.BID_PRICE = response.jsonMaxP[0].M_Price;
			console.log($scope.ROW_COUNT1);
			console.log($scope.ROW_COUNT2);
			//console.log($scope.BID_PRICE);
		});
	
}; 

//$scope.placeBid = function(ITEMCODE, BidP){
$scope.profile = function(username){
	$scope.value = username;
	console.log($scope.value);
		
		$http({
			method :"POST",
			url : "/fetchProfile",
			data : {
				username : $scope.value
				
			}
		}).success(function(response){
			 window.location.assign('/person');
			
			
		});
	
}; 

//change cart to directbuy page
$scope.buyNow = function(ITEMCODE, Qty){
	$scope.value = ITEMCODE;
	console.log($scope.value);
		
		$http({
			method :"POST",
			url : "/buyProducts",
			data : {
				item_id : $scope.value
			}
		}).success(function(data){
			if (data.statusCode == 200) {
                window.location.assign('/cart');
			}
			}).error(function (err) {
				
			});
	}
})


