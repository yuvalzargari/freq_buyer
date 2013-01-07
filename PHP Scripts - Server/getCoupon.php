<?php
/**
 * File to handle all API requests
 * Accepts GET and POST
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 
  /**
 * check for POST request
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') 
{
	require_once '/home/a9208348/public_html/DB_Connect.php';
        // connecting to database
        $db = new DB_Connect();
        $db->connect();

    	$email = $_POST['email'];
   	$business_name = $_POST['businessName'];	    
        
    	$result = mysql_query(
				"SELECT bu.coupon, bu.Benefit
				 FROM business_users bu
				 INNER JOIN users u
  				 ON u.uid = bu.user_id AND u.email = '$email'
				 INNER JOIN business b
				 ON b.id = bu.bussines_id 
				 WHERE b.`business name` = '$business_name'"
			) ;

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
} 
else 
{
    echo "Access Denied!";
}
?>