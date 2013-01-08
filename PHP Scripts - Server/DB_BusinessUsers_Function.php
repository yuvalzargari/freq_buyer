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
     * Get coupon and benefit by user email and business name
     */
    public function getcouponAndBenefitByUserEmailAndBusinessName($userEmail, $businessName) 
	{
		$result = mysql_query("SELECT bu.coupon, bu.Benefit
				 			   FROM business_users bu
				 			   INNER JOIN users u
  				 			   ON u.uid = bu.user_id AND u.email = '$userEmail'
				 			   INNER JOIN business b
				 			   ON b.id = bu.bussines_id 
					 		   WHERE b.`business name` = '$businessName'") or die(mysql_error());
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
    
    /**
     * Get consumer club list by owner name
     */
    public function getConsumerClubListByBusinessID($businessID)
    {							
    	
    	$result = mysql_query( "SELECT temp.email, temp.name
							    FROM (  SELECT u.email, u.name, b.id
    									FROM users u
								    	INNER JOIN business_users bu
								    	ON u.uid = bu.user_id
								    	INNER JOIN business b
								    	ON b.id = bu.bussines_id) as temp
							    WHERE temp.id =  '$businessID'");
		// check for result
		if (!$result) 
		{
			return false;
		}
		 
		if (mysql_num_rows($result) == 0) 
		{
			return false;
		}
		
		// Copy each row into data and return data
		
		$data = array();
		$i=0;
		while ($row = mysql_fetch_array($result))
		 {
			$data[$i]["email"] = $row[0];
			$data[$i]["name"] = $row[1];
			$i++;
		}
		return $data;
    }
    
    
 
}
 
?>