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
	// get tag
	$tag = $_POST['tag'];

	// include db handler
	require_once '/home/a9208348/public_html/DB_BusinessUsers_Function.php';
	$db_BusinessUsers_Functions = new DB_BusinessUsers_Functions();
	
	require_once '/home/a9208348/public_html/DB_Business_Function.php';
	$db_Business_Functions = new DB_Business_Functions();

	// Get email and business name
	$email = $_POST['email'];

	// response Array
	$response = array("tag" => $tag, "success" => 0, "error" => 0);
	
	$businessID = $db_Business_Functions->getBusinessByOwnerEmail($email);
	$businessID = $businessID["id"];


	// check for business
	$business = $db_BusinessUsers_Functions->getConsumerClubListByBusinessID($businessID);
	if($business != false)
	{
		// business found
		// echo json with success = 1
		$response["success"] = 1;
		$size = count($business);
		$i=0;
		while($i < $size)
		{
			$response[user][$i]["name"] = $business[$i]["name"];
			$response[user][$i]["email"] = $business[$i]["email"];
			$i++;
		}
		echo json_encode($response);
	}
	else
	{
		// business not found
		// echo json with error = 1
		$response["error"] = 1;
		$response["error_msg"] = "No business found!";
		echo json_encode($response);
	}
}
else
{
	echo "Access Denied!";
}
?>