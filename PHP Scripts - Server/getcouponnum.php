<?php 
	// Connects to your Database 
	require_once '/home/a9208348/public_html/config.php';      
	// connecting to mysql   
	$con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) or die(mysql_error());  
	// selecting database     
	mysql_select_db(DB_DATABASE);
	$result = mysql_query("SELECT * FROM business_users WHERE user_id = '4' AND bussines_id = '1'") ;
	// check for result     
	$no_of_rows = mysql_num_rows($result);     
	if ($no_of_rows > 0) 	
	{           
		$result = mysql_fetch_array($result);	
		echo json_encode($result);    
	}        
	else 	
	{         
		// business user not found        
		return false;       
	}
 ?> 