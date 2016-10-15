var ejs = require("ejs");
var mysql = require('./mysql');	

exports.openProfilePage = function(req,res)
{
	console.log(" I m here");
	
	//Checks before redirecting whether the session is valid
	console.log(req.session.username);
	if(req.session.username)
	{
		//Set these headers to notify the browser not to maintain any cache for the page being loaded
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("profile",{username:req.session.username,firstname:req.session.firstname,lastname:req.session.lastname});
		
	}

};

exports.person = function(req,res)
{
	console.log(" I m not in the here");
	
	//Checks before redirecting whether the session is valid
	console.log(req.session.username);
	if(req.session.username)
	{
		//Set these headers to notify the browser not to maintain any cache for the page being loaded
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("person",{username:req.session.username,firstname:req.session.firstname,lastname:req.session.lastname});
		
	}

};

exports.personDetail = function(req,res){
	var user = req.session.username;
	console.log("username is  "+ user);
	var fetProfile = "Select * from user where Username ='" + user +"';" ;
	var json_responses;
	mysql.fetchData(function(err,results){
		if(err){
			throw err;
		}
		else{
			var jsonString = JSON.stringify(results);
			var jsonParse = JSON.parse(jsonString);
			var newres = {"jsonParse": jsonParse,"rowcount":results.length};
			console.log("result" +results.length);
			console.log("result123" +jsonParse);
			console.log("DOB"+ results[0].BirthDay);
			console.log("DOB"+ results[0].PHONE);
			console.log("DOB"+ results[0].Joining);
			console.log("EMAIL"+ results[0].EMAIL);
			var email = results[0].EMAIL;
			req.session.email = email;
			console.log(email);
			console.log("I am the user");
			json_responses = {"statusCode" : 200};
			//res.send(json_responses);
			res.send(newres);
		}
	},fetProfile);
};


//
exports.updatePerson = function(req,res){
	var contact = req.param('contact');
	var birth = req.param('birth');
	var email = req.param('email');
	console.log("contact is  "+ contact);
	console.log("birth is  "+ birth);
	console.log("email" + email);
	var user = req.session.username;
	var fetProfile = "Update user set PHONE ='" + contact +"',"+"BirthDay = '" + birth +"'," +"EMAIL = '" + email +"'" +"where USERNAME ='" + user +  "';" ;
	console.log("query" + fetProfile);
	var json_responses;
	mysql.fetchData(function(err,results){
		if(err){
			throw err;
		}
		else{
			
			json_responses = {"statusCode" : 200};
			
			//res.send(json_responses);
			res.send(json_responses);
		}
	},fetProfile);
};

//
exports.profileDetail = function(req,res){
	var username = req.session.username;
	console.log("username is  "+ username);
	var buyResult = "Select * from orders where Buyer_Username ='" + username +"';" ;
	var sellResult = "Select * from orders where Seller_Username ='" + username +"';" ;
	console.log("Query is:" + buyResult);
	console.log("Query is:" + sellResult);
var json_responses;
	
	mysql.fetchData(function(err,results){
		if(err){
			throw err;
		}
		else{
				var jsonString = JSON.stringify(results);
				var jsonParse = JSON.parse(jsonString);
				//var newres = {"jsonParse": jsonParse,"rowcount":results.length};
				console.log("result" +results.length);
				console.log("result" +jsonParse);
				console.log("I am here444");
				
				mysql.fetchData(function(err1,results1){
					if(err1){
						throw err1;
					}
					else{
						var jsonString1 = JSON.stringify(results1);
						var jsonParse1 = JSON.parse(jsonString1);
						var newres = {"jsonParse": jsonParse,"rowcount":results.length,"jsonParse1":jsonParse1,"rowcount1":results1.length};
						console.log("result" +results1.length);
						console.log("result" +jsonParse1);
						console.log("I am here555");
						json_responses = {"statusCode" : 200};
						//res.send(json_responses);
						res.send(newres);
					}
				},sellResult);
				
			
			
		}
	},buyResult);
};

