<html>
  <head>
    <meta name="layout" content="main" />
    <title>Register</title>         
  </head>
  <body>
    <div class="body">
      <h1>Login</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:form action="adduser" method="post" >
        <div class="dialog">
          <table>
            <tbody>            
              <tr class="prop">
                <td class="name">
                  <label for="login">Login:</label>
                </td>
                <td>
                  <input type="text" id="login" name="login"/>
                </td>
              </tr>
              
              <tr class="prop">
                <td class="name">
                  <label for="name">Full name:</label>
                </td>
                <td>
                  <input type="text" id="name" name="name"/>
                </td>
              </tr>  
          
              <tr class="prop">
                <td class="name">
                  <label for="password">Password:</label>
                </td>
                <td>
                  <input type="password" id="pass1" name="pass1"/>
                </td>
              </tr> 
              
              <tr class="prop">
                <td class="name">
                  <label for="password">Repeat password:</label>
                </td>
                <td>
                  <input type="password" id="pass2" name="pass2"/>
                </td>
              </tr> 
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span class="button">
            <input class="save" type="submit" value="Register" />
          </span>
        </div>
          </span>
        </div>
      </g:form>
    </div>
  </body>
</html>