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
	
	$tag = $_POST['tag'];
    	$email = $_POST['email'];
	$business_name = $_POST['businessName'];
	$set_Coupon = $_POST['setCoupon'];
	$benefit_name = $_POST['benefit'];
        
    	$result = mysql_query(
				"  
				SELECT t.uid, t.id, t.coupon, t.Benefit
				FROM
				(
					 SELECT bu.coupon, bu.Benefit, b.`business name`, u.email, u.uid, b.id
					 FROM users u, business b, business_users bu
					 WHERE u.uid=bu.user_id
					 AND b.id=bu.bussines_id
				) AS t
				WHERE t.`business name`='$business_name' AND t.email='$email' 
				"				
			) ;
	$response=mysql_fetch_array($result ,MYSQL_NUM);       
	$bu_uid = $response[0];
	$bu_bid = $response[1];
	$result2 = mysql_query(
				"
				UPDATE business_users
				SET coupon='$set_Coupon', Benefit='$benefit_name'
				WHERE '$bu_uid'=user_id AND '$bu_bid'=bussines_id
				"
			);  
	$result3 = mysql_query(
				"
				SELECT coupon,Benefit
				FROM business_users
				WHERE '$bu_uid'=user_id AND '$bu_bid'=bussines_id	
				"
				);
	$result3=mysql_fetch_array($result3);       
	echo json_encode($result3);

} 
else 
{
    echo "Access Denied!";
}
?>