<%@ taglib uri="/WEB-INF/iscTaglib.xml" prefix="isomorphic" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/2/17
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <!--                                                               -->
    <!-- Consider inlining CSS to reduce the number of requested files -->
    <!--                                                               -->
    <link type="text/css" rel="stylesheet" href="CallCenter.css">

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Web Application Starter Project</title>

    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="CallCenter/CallCenter.nocache.js"></script>
</head>


<script>
    var isomorphicDir = "CallCenter/sc/";
</script>


<isomorphic:loadISC skin="EnterpriseBlue" modulesDir="modules/" isomorphicURI="magticombilling/sc/"
                    includeModules="Core,Foundation,Containers,Grids,Forms,DataBinding,Analytics,Charts,Calendar"/>


<body>

<!-- OPTIONAL: include this if you want history support -->
<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
        style="position:absolute;width:0;height:0;border:0"></iframe>


<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
<noscript>
    <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
    </div>
</noscript>

</body>
</html>
