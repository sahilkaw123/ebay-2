
//Making connection pools of the mySql with a connection limit of 50
var ejs = require('ejs');
var mysql = require('mysql');


var  pool = mysql.createPool({
	connectionLimit : 50,
	host     : 'localhost',
	user     : 'root',
	password : 'test123',
	database : 'test',
	port     : 3306
	
});



var getData = function(callback, sqlQuery) { 
	
	
	pool.getConnection(function(err,connection){
		connection.query(sqlQuery, function(err,rows,fields){
			connection.release(); // to release the connection 
			if(err){
				console.log("ERROR : " + err.message);
				callback(err,rows);
			}
			else{ //return err or results
				console.log("Database Result :" + rows);
				callback(err,rows);
				
				
			}
		}); //Query ends
	}); // getConnection ends
};



exports.fetchData = getData;