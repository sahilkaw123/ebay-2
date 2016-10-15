var ejs = require("ejs");
var mysql = require('./mysql');
		

	
	exports.bid_job = {
		    
		    after: {                
		        seconds: 0,
		        minutes:0,
		        hours: 0,
		        days: 1
		    },
		    job: function (req,res) {	
	
		    	var  output = '';
		           var id = '';
		    	var bidEndTimeChk = "Select Item_Code from bidCheck where tme <= Current_Timestamp and Bid_Stat = 0;" ;
		    	//var maxPrice = "Select max(PRICE_BID) as M_Price from bid where ITEM_CODE ='" + id +"';" ;
		    	//var insMaxBid = "INSERT INTO bidOrder Select A.ITEM_CODE,  max(A.PRICE_BID) as M_Price, B.Seller_USERNAME, " +
		    	//		"A.Buyer_Username,A.ITEM_NAME from bid A , Product B  where  A.Buyer_Username != ' ' and  A.ITEM_CODE = B.ITEM_CODE  group by A.ITEM_CODE, A.ITEM_NAME, A.Buyer_Username,B.Seller_USERNAME " +
		    //			"having max(A.PRICE_BID) =(Select max(PRICE_BID) from bid where ITEM_CODE = '" + id +"'  );"
		    			
		    	mysql.fetchData(function(err,results){
		    		if(err){
		    			throw err;
		    		}
		    		else{
		    			if(results.length > 0){
		    				console.log("Bids Complete." + "\n");
		    				console.log(results);
		    				
		    				for (var i in results) {
		    		          var  output = results[i];
		    		           var id = output.Item_Code;
		    		            console.log(id);
		    		            
		    		            var insMaxBid = "INSERT INTO bidOrder Select A.ITEM_CODE,  max(A.PRICE_BID) as M_Price, B.Seller_USERNAME, A.Buyer_Username,A.ITEM_NAME, A.Qty from bid_dup A , Product B  where A.Buyer_Username IS NOT NULL and A.ITEM_CODE = B.ITEM_CODE  group by A.ITEM_CODE, A.ITEM_NAME, A.Buyer_Username,A.Qty,B.Seller_USERNAME having max(A.PRICE_BID) =(Select max(PRICE_BID) from bid where ITEM_CODE = '" + id +"')";
		    		            mysql.fetchData(function(err1,results1){
		    		            	if(err1){
		    			    			throw err;
		    			    		}
		    		            	else{
		    		            		for (var i in results) {
		    		            		 var  output = results[i];
		    		            		var id = output.Item_Code;
		    		            		console.log(id);
		    		            		var delBidEntry = "update bidCheck set Bid_Stat = 1 where ITEM_CODE = '" + id +"';";
		    		            		mysql.fetchData(function(err2,results2){
				    		            	if(err2){
				    			    			throw err;
				    			    		}
				    		            	else{
				    		            			for (var i in results) {
							    		            		 var  output = results[i];
							    		            		var id = output.Item_Code;
							    		            		console.log("ID is" + id);
						    		            		var updtMoney = "Select  DISTINCT(ITEM_CODE), Max_BIDP, SELLER_NAME, Buyer_Name, Qty from bidOrder where ITEM_code = '" + id +"';";
						    		            		console.log("I am done");
						    		            		mysql.fetchData(function(err4,results4){
								    		            	if(err4){
								    			    			throw err;
								    			    		}
								    		            	else{
								    		            		if(results.length > 0){
								    		            			for (var i in results) {
								    		        		            output = results[i];
								    		        		            amount = output.Max_BIDP;
								    		        		            S_name = output.SELLER_NAME;
								    		        		            B_name = output.Buyer_Name;
								    		        		            QTY = output.Qty;
								    		        		            var updatePrice = "update user set balance = balance +" + amount + " where USERNAME ='"+ S_name+"';"; 
								    		        		            mysql.fetchData(function(err4,results4){
												    		            	if(err4){
												    			    			throw err;
												    			    		}
												    		            	else{
												    		            		console.log("good Bye");
												    		            		}
								    		        		            },updatePrice);
								    		        		            
								    		            		}
								    		            			}
								    		            		}
						    		            		},updtMoney);
								    		            	}
						    		       
				    		            	
				    		            	}	
		    		            	},delBidEntry);
		    		            	}
		    		            	}
		    		            },insMaxBid);
		    				
		    		}
		    		}
		    		}
		    	},bidEndTimeChk);
		    }};
