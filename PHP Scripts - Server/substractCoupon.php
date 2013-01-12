<?php

/*
 *   get user after get user id
*   select line on user id and --1
*
*
*/
if (isset($_POST['tag']) && $_POST['tag'] != '')
{
	// get tag
	$tag = $_POST['tag'];

	// include db handler
	require_once '/home/a9208348/public_html/DB_BusinessUsers_Function.php';
	$db_BusinessUsers_Functions = new DB_BusinessUsers_Functions();

	// Get email and business name
	$email = $_POST['email'];
	$business_name = $_POST['business_name'];

	// response Array
	$response = array("tag" => $tag, "success" => 0, "error" => 0);

	$consumer = $db_BusinessUsers_Functions->substractCoupon($email, $business_name);
	if($consumer != false)
	{
		// consumer found and updated the coupon
		// echo json with success = 1
		$response["success"] = 1;
		$response[consumer]["coupon"] = $consumer["coupon"];
		$response[consumer]["Benefit"] = $consumer["Benefit"];
		echo json_encode($response);
	}
	else
	{
		// Error updating the coupon
		// echo json with error = 1
		$response["error"] = 1;
		$response["error_msg"] = "Error substracting coupon!";
		echo json_encode($response);
	}

}
else
{
	echo "Access Denied!";
}








?>