<html lang="en">
<head>
    <link rel='stylesheet' type='text/css' href='styles.css'/>
    <meta charset="UTF-8"/>
    <title>View Personal Information</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous"/>
</head>
<body>
    <header class="card">
        <h1> Here is your personal information </h1>
    </header>

    <div class="table-view">
        <table>
            <tr><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Email</th><th>Password</th></tr>
            <tr>
                <form method='post' action='/updateInfo.do'>
                    <td><input type='text' id='first_name' name='first_name' value='${emp.getFirstName()}'/></td>
                    <td><input type='text' id='last_name' name="last_name" value='${emp.getLastName()}'/></td>
                    <td><input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" id="phone_number" name="phone_number" value='${String.valueOf(emp.getPhoneNumber())}'/></td>
                    <td><input type='email' id='email' name='email' value='${emp.getEmail()}'/></td>
                    <td><input type='text' id='password' name='password' value='${emp.getPassword()}'/></td>
                    <button type="submit" class="btn btn-primary btn-lg"> Submit Changes</button>
                </form>
            </tr>
        </table>

    </div>
    <br/><br/>
    <a href='/employeeHome.html'><button class="btn btn-primary btn-lg" id="person-info-goBack">Go Back</button></a>
</body>
</html>



