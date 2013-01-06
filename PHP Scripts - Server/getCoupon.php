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
    require_once '/home/a9208348/public_html/DB_Users_Functions.php';
    $db_users = new DB_Users_Functions();
    require_once '/home/a9208348/public_html/DB_Business_Functions.php';
    $db_business = new DB_Business_Functions();
    require_once '/home/a9208348/public_html/DB_BusinessUsers_Functions.php';
    $db_business_users = new DB_BusinessUsers_Functions();
 
    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 

    // Request type is check Login
    $email = $_POST['email'];
    $business_name = $_POST['businessName'];
    
    $user = $db_users->getUserIDByEmail($email);
    $userID = $user["uid"];
    
    $business = $db_business->getBusinessIDByName($business_name);
    $businessID = $business["id"];
    
    $coupon = $db_business_users->getcouponByUserIDAndBusinessID($userID, $businessID);
 
    if ($coupon != false) 
	{
        // business user found
		$response["success"] = 1;
        $response["business_user"]["coupon"] = $coupon["coupon"];
        echo json_encode($response);
    } 
	else 
	{
		// business user not found
        // echo json with error = 1
        $response["error"] = 1;
		echo json_encode($response);
    }
} 
else 
{
    echo "Access Denied!";
}
?>