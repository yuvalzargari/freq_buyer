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
 
	// Request type is Register new user
    $name = $_POST['name'];
	$email = $_POST['email'];
	$password = $_POST['password'];
 
	// check if user is already exist
    if ($db->isUserExisted($email)) 
	{
		// user is already exist - error response
		$response["error"] = 2;
		$response["error_msg"] = "User already existed";
		echo json_encode($response);
    } 
	else 
	{
        // store user
		$user = $db->storeUser($name, $email, $password);
		if($user) 
		{
			// user stored successfully
			$response["success"] = 1;
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["type"] = $user["type"];
			echo json_encode($response);
        } 
		else 
		{
            // user failed to store
            $response["error"] = 1;
			$response["error_msg"] = "Error occured in Registartion";
			echo json_encode($response);
            
        }
    }
} 
else 
{
    echo "Access Denied!";
}
?>