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
	$name = $_POST['name'];
	$menu = $_POST['menu'];

	// include db handler
	require_once '/home/a9208348/public_html/DB_Business_Function.php';
	$db = new DB_Business_Functions();

	// response Array
	$response = array("tag" => $tag, "success" => 0, "error" => 0);


	// Request type is check Login

	// check for business
	$business = $db->updateMenu($name, $menu);
	if($business != false)
	{
		// business found
		// echo json with success = 1
		$response["success"] = 1;
		$response[business]["name"] = $business["name"];
		$response[business]["logo"] = $business["logo"];
		$response[business]["menu"] = $business["menu"];
		$response[business]["events"] = $business["events"];
		echo json_encode($response);
	}
	else
	{
		// business not found
		// echo json with error = 1
		$response["error"] = 1;
		$response["error_msg"] = "Error updating business";
		echo json_encode($response);
	}
}
else
{
	echo "Access Denied!";
}
?>