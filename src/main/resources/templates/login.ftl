<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/static/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body style="background-color: #affff7">
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Log in using your username and password</h3>
                </div>
                <div class="panel-body" style="background-color: #2d3246; color: white">
                    <#if logout>
                        <div class="alert alert-info" role="alert">You are logged out!</div>
                    </#if>
                    <#if error>
                        <div class="alert alert-danger" role="alert">Invalid username or password!</div>
                    </#if>

                    <form method="post">
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                        <div class="form-group">
                            <label for="username">Login</label>
                            <input type="text" class="form-control" id="username" placeholder="Login"
                                   name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password"
                                   name="password">
                        </div>
                        <button type="submit" class="gradient-button" style="margin-left: 40%;margin-right: 20%">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>