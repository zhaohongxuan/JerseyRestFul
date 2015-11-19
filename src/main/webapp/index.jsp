<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New User</title>
</head>
<body>
<form action="/jersey/api/users" method="POST">
    <label for="userId">userId</label>
    <input name="userId" id="userId" />
    <label for="userName">userName</label>
    <input name="userName" id="userName"/>
    <label for="userAge">userAge</label>
    <input name="userAge" id="userAge"/>
    <input type="submit" value="Add" />
</form>
</body>
</html>