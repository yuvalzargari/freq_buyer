<?php

/*
*   get user after get user id
*   select line on user id and --1
*
*
*/
if (isset($_POST['tag']) && $_POST['tag'] != '') 
{
		
	// connect
	require_once '/home/a9208348/public_html/DB_Connect.php';
	$db = new DB_Connect();
	$con= $db->connect();		
	
	// get tag
	$tag = $_POST['tag'];
	$email = $_POST['email'];
	$business_name= $_POST['business_name'];


	$result = mysql_query("SELECT u.uid FROM users u WHERE u.email='$email'" , $con) or die(mysql_error());

	$bid = mysql_query("SELECT id FROM business WHERE `business name`='$business_name'" , $con) or die(mysql_error());
	
	$result = mysql_fetch_array($result);
	$bid = mysql_fetch_array($bid);

	$userId = $result["uid"];
	$businessid = $bid["id"];

	$result2 = mysql_query("UPDATE business_users SET coupon=coupon-1 WHERE user_id='$userId' AND bussines_id='$businessid'" , $con) or die(mysql_error());
	
	$returner = mysql_query("SELECT coupon FROM business_users WHERE user_id='$userId' AND bussines_id='$businessid'" , $con) or die(mysql_error());
	
	$returner = mysql_fetch_array($returner);
	echo json_encode($returner);

	$db->close();
}
else
{
	$respons = array("tag" => $tag, "success" => 0, "error" => 1);
	echo json_encode($response);
	$db->close();

}


?>