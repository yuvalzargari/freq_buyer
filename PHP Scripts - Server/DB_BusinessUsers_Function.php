<?php
 
class DB_BusinessUsers_Functions
{
 
    private $db;
 
    //put your code here
    // constructor
    function __construct() 
	{
        require_once '/home/a9208348/public_html/DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 
    // destructor
    function __destruct() 
	{
		$this->db->close();
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storeConsumerClub($uid, $bid, $coupon) 
	{
		$type = "client";
        $result = mysql_query("INSERT INTO business_users(user_id, bussines_id, coupon) VALUES('$uid', '$bid', '$coupon')");
        // check for successful store
        if ($result) 
		{
            // get consumer details
            $id = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT coupon FROM business_users WHERE id = $id");
            // return user details
            return mysql_fetch_array($result);
        } 
		else 
		{
            return false;
        }
    }
 
    /**
     * Get user by email and password
     */
    public function getcouponByUserIDAndBusinessID($userID, $businessID) 
	{
        $result = mysql_query("SELECT * FROM business_users WHERE user_id = '$userID' AND bussines_id = '$businessID'") or die(mysql_error());
        // check for result
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) 
		{
            $result = mysql_fetch_array($result);
			return $result;
        } 
        else 
		{
            // business user not found
            return false;
        }
    }
 
}
 
?>