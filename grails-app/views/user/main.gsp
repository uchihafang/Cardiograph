<html>
  <head>
    <meta name="layout" content="main" />
    <title>Login</title>         
  </head>
  <body>
    <div class="body">
      <h1>Login</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:form action="logout" method="post" >
        <div class="buttons">
          <span class="button">
            <input class="clear" type="submit" value="Logout" />
          </span>
        </div>
      </g:form>
    </div>
  </body>
</html>