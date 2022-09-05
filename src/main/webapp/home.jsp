<%--
  Created by IntelliJ IDEA.
  User: Arsen
  Date: 01.09.2022
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="res/images/knight.png">
    <link rel="stylesheet" type="text/css" href="res/general.css">
    <link rel="stylesheet" type="text/css" href="res/home-jsp-res/home.css">
    <script src="res/home-jsp-res/home.js" defer></script>
    <title>Authorization</title>
</head>
<body>
<main>

    <header>
        <div id="typedtext"></div>
    </header>

    <menu>

        <!-- LOG IN -->
        
        <div class="login">
            <form id="login" class="input-group" action="login" method="post">

                <div class="input-group">
                    <input type="text" class="input-field" name="log_email" id="log_email" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Eamil</label>
                </div>

                <div class="input-group">
                    <input type="text" class="input-field" name="log_password" id="log_password" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Password</label>
                </div>
    
                <% if (request.getParameter("log_error") != null) { %>
                    <div class="input-row log-error"><%= request.getParameter("log_error") %></div>
                <% } %>

                <div><button type="submit" class="submit-btn">Log In</button></div>
            </form>
        </div>
        
        <!--=================================================================================================================================================-->

        <!-- REGISTRATION -->
        
        <div class="register">
            <form id="register" class="input-group" action="register"  method="post">

                <div class="input-group">
                    <input type="text" class="input-field" name="reg_name" autocomplete="off" id="reg_name" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Name</label>
                </div>

                <div class="input-group">
                    <input type="text" class="input-field" name="reg_surname" id="reg_surname" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Surname</label>
                </div>

                <div class="input-group">
                    <input type="number" class="input-field"  name="reg_age" id="reg_age" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Age</label>
                </div>

                <div class="input-group">
                    <input type="text" class="input-field" name="reg_email" id="reg_email" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Email</label>
                </div>

                <div class="input-group">
                    <input type="tel" class="input-field" name="reg_phone_number" id="reg_phone_number" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Phone</label>
                </div>

                <div class="input-group">
                    <input type="password" class="input-field" name="reg_password" id="reg_password" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Password</label>
                </div>

                <div class="input-group">
                    <select name="reg_gender" class="input-field" id="reg_gender" required>
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>
    
                <% if (request.getParameter("reg_error") != null) { %>
                    <div class="input-row reg-error"><%= request.getParameter("reg_error") %></div>
                <% } %>
    
                <div class="input-row">
                    <button type="submit" class="submit-btn">Sign Up</button>
                </div>
    
            </form>
        </div>

    </menu>
</main>

</body>
</html>