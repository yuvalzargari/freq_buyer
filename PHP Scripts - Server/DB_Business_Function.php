<?php

class DB_Business_Functions
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

	public function storeBusiness($name, $menu, $events)
	{
		$result = mysql_query("INSERT INTO business(business name, menu, events) VALUES('$name', '$menu', '$events')");
		// check for successful store
		if($result)
		{
			// get business details
			$id = mysql_insert_id(); // last inserted id
			$result = mysql_query("SELECT * FROM business WHERE uid = $id");
			// return business details
			return mysql_fetch_array($result);
		}
		else
		{
			return false;
		}
	}
	
	public function getBusinessIDByName($name)
	{
		$result = mysql_query("SELECT id FROM business WHERE name = '$name'");
		// check for result
		$no_of_rows = mysql_num_rows($result);
		if ($no_of_rows > 0)
		{
			$result = mysql_fetch_array($result);
			return $result;
		}
		else
		{
			// Business not found
			return false;
		}
	}

	public function getBusinessByName($name)
	{
		$result = mysql_query("SELECT * FROM business WHERE name = '$name'");
		// check for result
		$no_of_rows = mysql_num_rows($result);
		if ($no_of_rows > 0)
		{
			$result = mysql_fetch_array($result);
			return $result;
		}
		else
		{
			// Business not found
			return false;
		}
	}
	
	public function getBusinessByOwnerEmail($ownerEmail)
	{
		$result = mysql_query("SELECT b.id, b.`business name` , b.logo, b.menu, b.events
							   FROM business b
							   INNER JOIN owners o ON b.id = o.bid
							   INNER JOIN users u ON u.uid = o.uid
							   WHERE u.email =  '$ownerEmail'");
		// check for result
		$no_of_rows = mysql_num_rows($result);
		if ($no_of_rows > 0)
		{
			$result = mysql_fetch_array($result);
			return $result;
		}
		else
		{
			// Business not found
			return false;
		}
	}

	public function getAllBusiness()
	{
		$result = mysql_query("SELECT * FROM business") or die(mysql_error());
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
			$data[$i]["name"] = $row[1];
			$data[$i]["logo"] = $row[2];
			$data[$i]["menu"] = $row[3];
			$data[$i]["events"] = $row[4];
			$i++;
		}
		return $data;
	}

	public function isBusinessExist($name)
	{
		$result = mysql_query("SELECT * FROM business WHERE name = '$name'");
		// check for result
		$no_of_rows = mysql_num_rows($result);
		if ($no_of_rows > 0)
		{
			return true;
		}
		else
		{
			// Business not found
			return false;
		}
	}

}

?>