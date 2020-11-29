<html>
    <head>
        <title>DeinServer.net - Redeem</title>
        <link rel="stylesheet" href="style.css">
    </head>

    <body>
        <center>
        <div id="block">
            <div id="title">
                <h1>DeinServer.net - Beta</h1>
            </div>

            <form action="index.php" method='post'>

            <div id="form">
                <input type="text" name="username" id="username" name="username" placeholder="Minecraft Benutzername">
                <input type="text" name="code" id="code" name="code" placeholder="Code">
                <br>
                <button type="submit" name="redeem">Einlösen</button>
            </div>

            </form>


            <?php
            function username_to_profile($username) {
                if (is_valid_username($username)) {
                    $json = file_get_contents('https://api.mojang.com/users/profiles/minecraft/' . $username);
                    if (!empty($json)) {
                        $data = json_decode($json, true);
                        if (is_array($data) and !empty($data)) {
                            return $data;
                        }
                    }
                }
                return false;
            }

            function username_to_uuid($username) {
                $profile = username_to_profile($username);
                if (is_array($profile) and isset($profile['id'])) {
                    return $profile['id'];
                }
                return false;
            }

            function is_valid_username($string) {
                return is_string($string) and strlen($string) >= 2 and strlen($string) <= 16 and ctype_alnum(str_replace('_', '', $string));
            }




            if(isset($_POST['redeem'])) {
                $username = $_POST['username'];
                $code = $_POST['code'];

                require('mysql.php');

                function UserIsExists() {

                    $username = $_POST['username'];

                    $uuid = username_to_uuid($username);

                    require('mysql.php');
               $stmt = $db->prepare("SELECT * FROM beta WHERE uuid = ?");
               $stmt->bind_param('s', $uuid);
               $stmt->execute();
               $stmt->store_result();
               if ($stmt->num_rows == 1) 
                 return 1;
               else 
                 return 0;
               $stmt->close();
}



                function CodeIsExist($name) {

                    $username = $_POST['username'];
                    $code = $_POST['code'];

                    require('mysql.php');
               $stmt = $db->prepare("SELECT * FROM betacodes WHERE codes = ?");
               $stmt->bind_param('s', $code);
               $stmt->execute();
               $stmt->store_result();
               if ($stmt->num_rows == 1) 
                 return 1;
               else 
                 return 0;
               $stmt->close();
}



                        if(CodeIsExist($code)) {

                            $stmt1 = $db->prepare("SELECT * FROM betacodes WHERE codes = ?");
                            $stmt1->bind_param('s', $code);
                            $stmt1->execute();
                            $result1 = $stmt1->get_result();
                            while ($row1 = $result1->fetch_assoc()){
                                $used = $row1['used'];
                          }

                          if($used == 0) {
                            $uuid = username_to_uuid($username);

                                $sql = "INSERT INTO beta(uuid, whitelisted) VALUES ('$uuid', '1')";
                
                                if ($db->query($sql) === TRUE) {
                                    echo "Erfolgreich! Du kannst dich jetzt Einloggen!";
                                  } else {
                                    echo "Der Code existiert leider nicht!";
                                  }
    
                                  $sql1 = "UPDATE betacodes SET used='1' WHERE codes='$code'";
    
                                  if ($db->query($sql1) === TRUE) {
                                    echo "";
                                  } else {
                                    echo "Fehler!";
                                  }
                    
                        } else {
                            echo "Der Code wurde bereits genutzt!";
                        }  
                          } else {
                            echo "Der Code existiert leider nicht!";  
                          }
                        }

                        
            ?>

        </div>
    </center>
    </body>
</html>