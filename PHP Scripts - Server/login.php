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
    $db = new DB_Users_Functions();
 
    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 

    // Request type is check Login
    $email = $_POST['email'];
    $password = $_POST['password'];
 
    // check for user
    $user = $db->getUserByEmailAndPassword($email, $password);
    if ($user != false) 
	{
        // user found
        // echo json with success = 1
		$response["success"] = 1;
        $response["user"]["name"] = $user["name"];
        $response["user"]["email"] = $user["email"];
        $response["user"]["type"] = $user["type"];
        echo json_encode($response);
    } 
	else 
	{
		// user not found
        // echo json with error = 1
        $response["error"] = 1;
		$response["error_msg"] = "Incorrect email or password!";
		echo json_encode($response);
    }
} 
else 
{
    echo "Access Denied!";
}
?>