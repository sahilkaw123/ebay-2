var ejs = require("ejs");
//var mysql = require('./mysqlpool');  // using pool
var mysql = require('./mysql'); // without using pool

// to validate  the login user signin function 

exports.checksignin = function(req,res){
	
	
	
	var username = req.param('username');
	var password = req.param('password');
	
	var json_responses;
	
	if((username!=='') && (password !=='')){
		var getUser = "select * from user where username='" + username +
						"'" + "or email='" + username +"' and password='" + password +"'";
		console.log("Query is :" +getUser);
	
	
	mysql.fetchData(function (err, results){
		if(err){
			throw err;
		}
		else{
			if(results.length > 0){
				console.log("Credentials accepted ");
				req.session.destroy();
				var username = results[0].USERNAME;
				
				var firstname1 = results[0].FIRSTNAME;
				var firstname = firstname1.toUpperCase(); 
				var lastname1 = results[0].LASTNAME;
				var lastname = lastname1.toUpperCase();
				req.session.username = username; // To assign the session to the user
				req.session.firstname = firstname;// To assign the session to the firstname
				req.session.lastname = lastname;// To assign the session to the firstname
				console.log("Session Initialized");
				console.log(results);
				console.log(req.session.username);
				console.log(req.session.firstname);
				json_responses = {"statusCode" : 200};
				res.send(json_responses);
			}
			else{
				console.log("Invalid Credentials");
				json_responses = {"statusCode" : 401};
				res.send(json_responses);
				
			}
		}	
		},getUser);
	}
	else{
		json_responses={"statusCode" : 401};
		res.send(json_responses);
	}
};//login function ends 

//rendering to signin page of ebay 

exports.signin = function(req,res){
	res.render('signin');
};




//HomePage redirection

//Redirects to the homepage
exports.loadSellPage = function(req,res)
{
	console.log(" I m here to Sell");
	
	//Checks before redirecting whether the session is valid
	console.log(req.session.username);
	if(req.session.username)
	{
		//Set these headers to notify the browser not to maintain any cache for the page being loaded
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("sell",{username:req.session.username,firstname:req.session.firstname,lastname:req.session.lastname,email:req.session.email});
		
	}
	else
	{
		res.redirect('/signin');
	}
};

//Logout the user - invalidate the session



//Signup

exports.signup = function(req,res) {

	ejs.renderFile('./views/signup.ejs', function(err, result) {
	   // render on success
	   if (!err) {
	            res.end(result);
	   }
	   // render or error
	   else {
	            res.end('An error occurred');
	            console.log(err);
	   }
   });
};

// sign up validation
exports.insertProduct = function(req,res)
{
	// check user already exists
	
	var S_FIRSTNAME = req.session.firstname;
	var S_LASTNAME  = req.session.lastname;
	var EMAIL = req.session.email;
	var S_USERNAME = req.session.username;
	var ITEM_NAME = req.param('ITEM_NAME');
	var ITEM_PRICE = req.param('ITEM_PRICE');
	var ITEM_QTY = req.param('ITEM_QTY');
	var ITEM_DESC = req.param('ITEM_DESC');
	var min_price = req.param('min_price');
	var category  = req.param('category');
	var BID   = req.param('BID');
	var Group_Name  = req.param('Group_Name');
	var COND   = req.param('COND');
	
	console.log(S_FIRSTNAME);
	console.log(S_LASTNAME);
	console.log(S_USERNAME);
	console.log(EMAIL);
	var json_responses;
	
	
	
	
	var addProd = "INSERT INTO product (ITEM_NAME, ITEM_DESC, ITEM_PRICE, ITEM_QTY, SELLER_FIRSTNAME, SELLER_LASTNAME, EMAIL, SELLER_USERNAME,category,Group_Name,BID,COND,min_price) VALUES  " +
    "('" + ITEM_NAME  + "','"+ ITEM_DESC +"','" + ITEM_PRICE + "','" + ITEM_QTY  + "','" + S_FIRSTNAME +"','" + S_LASTNAME  + "','"+ EMAIL +"','" + S_USERNAME  + "','"+ category +"','" +  Group_Name +"','" + BID +"','"+ COND +"','"  +ITEM_PRICE+"')";
	
	console.log("Query is:"+addProd);
	
	mysql.fetchData(function (err, results){
		if(err){
			throw err;
		}
		
			else 
			{					
				ejs.renderFile('./views/prodAdd.ejs',function(err, result) {
				 // render on success
				if (!err) {
					res.end(result);
				   }
				 	// render or error
				  else {
				     res.end('An error occurred');
				     console.log(err);
				   }
				});
			}  
		},addProd);	
	
	
};
