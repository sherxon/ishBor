<%--
  Created by IntelliJ IDEA.
  User: sherxon
  Date: 2/22/16
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button value="like" onclick="ok.performClick(this.value)">Like</button>

<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <%--<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/assets/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
    <div>
        <span id="companyName"></span>
    </div>
    <div>
        <span id="postion"></span>
    </div>

    <div>
        <span id="salary"></span>
    </div>

    <div>
        <span id="created"></span>
    </div>
    <div>
        <span id="descc"></span>
    </div>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="/assets/js/jquery.js"></script>
<script type="text/javascript" src="/assets/js/materialize.min.js"></script>
</body>
</html>

<script type="text/javascript">
    var vacancy=JSON.parse('${item}');
    $(document).ready(function () {
        $('#companyName').html(vacancy.company_name);
        $('#position').html(vacancy.vac_position);
        $('#salary').html(vacancy.st_price);
        $('#created').html(vacancy.st_date);
        $('#descc').html(vacancy.descc);

    })

</script>
