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
 
    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 

    // Get email and business name
    $email = $_POST['email'];
    $business_name = $_POST['businessName'];
    
    $coupon = $db_BusinessUsers_Functions->getcouponAndBenefitByUserEmailAndBusinessName($email, $business_name);
 
    if ($coupon != false) 
	{
        // business user found
		$response["success"] = 1;
        $response["business_user"]["coupon"] = $coupon["coupon"];
        $response["business_user"]["Benefit"] = $coupon["Benefit"];
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