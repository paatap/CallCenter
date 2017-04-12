<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib uri="/WEB-INF/iscTaglib.xml" prefix="isomorphic" %>--%>
<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="gwt:property" content="locale=ka">
    <meta name="author" content="Sarchi"/>
    <meta name="Description" content="Magticom CallCenter Web Application"/>
    <meta name="Keywords" content="Billing "/>

    <meta name="viewport"
          content="initial-scale=1, width=device-width, user-scalable=no, minimum-scale=1, maximum-scale=1, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">


    <style type="text/css">
        #loadingWrapper {
            position: absolute;
            top: 40%;
            width: 100%;
            text-align: center;
            z-index: 900001;
        }

        #loading {
            margin: 0 auto;
            border: 1px solid #ccc;
            width: 160px;
            padding: 2px;
            text-align: left;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }

        .blackText,
        .blackOnWhite {
            color: black;
            font-family: Verdana, Bitstream Vera Sans, sans-serif;
            font-size: 11px;
        }

        .blackOnWhite {
            background-color: white;
        }

        .sampleName {
            color: #8FB34F;
            font-weight: bold;
            text-decoration: none;
        }

        .exampleSeperator {
            border-bottom: 2px solid gray;
            background-color: white;
            color: black;
            font-weight: bold;
            text-align: left;
        }

        .explorerCheckErrorMessage {
            color: red;
            font-family: Georgia, serif;
            font-size: 13px;
        }

        .explorerFolderList {
            /* match style.css */
            font-family: Georgia, serif;
            font-size: 12px;
            color: #666;
            word-spacing: 1px;
            line-height: 20px;
        }
    </style>
    <!--CSS for chartCustomHover example-->
    <style type="text/css">
        .blueMarker {
            visibility: hidden;
            background-color: blue;
            max-width: 10px;
            max-height: 10px;
            border-radius: 10px;
            box-shadow: 0 0 10px 5px lightcyan;
        }
    </style>
    <%--<link rel="stylesheet" href="Showcase.css?isc_version=11.0p.css">--%>
    <%--<link rel="stylesheet" href="sourceColorizer.css?isc_version=11.0p.css">--%>
    <link type="text/css" rel="stylesheet" href="CallCenter.css">
    <link type="text/css" rel="stylesheet" href="addcss1.css">
    <link type="text/css" rel="stylesheet" href="addcss2.css">
    <%--<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon"/>--%>
    <title>Magticom CallCenter Web Application</title>
</head>
<body>

<!--add loading indicator while the app is being loaded-->


<div id="loadingWrapper">
    <div id="loading">
        <div class="loadingIndicator">
            <img src="images/loading.gif" width="32" height="32"
                 style="margin-right:8px;float:left;vertical-align:top;"/>
            <span id="sgwtProductName">
          Magticom CallCenter Web Application
        </span><br/>
            <span id="loadingMsg">მიმდინარეობს პროგრამის ჩატვირთვა...</span></div>
    </div>
</div>


<%--<script type="text/javascript">--%>
    <%--var askClose = true;--%>
    <%--var message = 'დარწმუნებული ხართ რომ გნებავთ დატოვოთ სისტემა?';--%>

    <%--window.onbeforeunload = function (evt) {--%>
        <%--if (askClose) {--%>
            <%--if (typeof evt == 'undefined') {--%>
                <%--evt = window.event;--%>
            <%--}--%>
            <%--// For IE and Firefox prior to version 4--%>
            <%--if (evt) {--%>
                <%--evt.returnValue = message;--%>
            <%--}--%>
            <%--// For Safari--%>
            <%--return message;--%>
        <%--} else {--%>
            <%--return;--%>
        <%--}--%>
    <%--};--%>

    <%--function disableAskClose() {--%>
        <%--askClose = false;--%>
    <%--}--%>

    <%--function enableAskClose() {--%>
        <%--askClose = true;--%>
    <%--}--%>
<%--</script>--%>


<%--<script type="text/javascript">--%>
    <%--function playSound(soundfile) {--%>
        <%--try {--%>
            <%--var el = document.getElementById("divCheckbox");--%>
            <%--if (el.mp3) {--%>
                <%--if (el.mp3.paused)--%>
                    <%--el.mp3.play();--%>
                <%--else--%>
                    <%--el.mp3.pause();--%>
            <%--} else {--%>
                <%--el.mp3 = new Audio(soundfile);--%>
                <%--el.mp3.play();--%>
            <%--}--%>
        <%--} catch (err) {--%>
            <%--console.debug(err);--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

<%--<script type="text/javascript">--%>
    <%--function stopSound(soundfile) {--%>
        <%--try {--%>
            <%--var el = document.getElementById("divCheckbox");--%>
            <%--if (el.mp3) {--%>
                <%--el.mp3.pause();--%>
            <%--} else {--%>
                <%--el.mp3 = new Audio(soundfile);--%>
                <%--el.mp3.pause();--%>
            <%--}--%>
        <%--} catch (err) {--%>
            <%--console.debug(err);--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

<%--<script type="text/javascript">--%>
    <%--function isNewYear() {--%>
        <%--var date = new Date();--%>
        <%--var d = date.getDate();--%>
        <%--var month = date.getMonth() + 1;--%>
        <%--if ((d > 24 && month == 12) || (d < 15 && month == 1)) {--%>
            <%--return 1;--%>
        <%--}--%>
        <%--return 0;--%>
    <%--}--%>
<%--</script>--%>


<!-- IMPORTANT : You must set the variable isomorphicDir to [MODULE_NAME]/sc/ so that the Smart GWT resource are
  correctly resolved -->
<script type="text/javascript">
    var isomorphicDir = "CallCenter/sc/";
    document.getElementById('loadingMsg').innerHTML = 'პროგრამის ინტერფეისის ჩატვირთვა...';
</script>

<!--include the SC Core API-->
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Core.js?isc_version=11.0p_2016-08-04.js'></script>

<!--include SmartClient -->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'კომპონენტების ჩატვირთვა...';</script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Foundation.js?isc_version=11.0p_2016-08-04.js'></script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Containers.js?isc_version=11.0p_2016-08-04.js'></script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Grids.js?isc_version=11.0p_2016-08-04.js'></script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Forms.js?isc_version=11.0p_2016-08-04.js'></script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_RichTextEditor.js?isc_version=11.0p_2016-08-04.js'></script>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'მონაცემების ჩატვირთვა...';</script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_DataBinding.js?isc_version=11.0p_2016-08-04.js'></script>
<script>
    isc.isVisualBuilderSDK = true;
</script>
<script type="text/javascript" src='CallCenter/sc/modules/ISC_Tools.js?isc_version=11.0p_2016-08-04.js'></script>

<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'მონაცემთა წყაროების ჩატვირთვა...';</script>
<script type='text/javascript' src='CallCenter/sc/skins/EnterpriseBlue/load_skin.js?isc_version=11.0p_2016-08-04.js'></script>


<!--load the datasources-->
<script type="text/javascript" src="sc/DataSourceLoader?dataSource=LogDS">
</script>
<script type="text/javascript" src="sc/DataSourceLoader?dataSource=operatDS">
</script>
<script type="text/javascript">

    // use the text-based date item so we can clear it for filtering purposes
    isc.DateItem.addProperties({
        useTextField: true,
        allowNullValue: true
    });

    function readCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    // Determine which skin to load
    var currentSkin = readCookie('skin_name_2_4');
    if (currentSkin == null) currentSkin = "EnterpriseBlue";
currentSkin = null;
    // Load the skin
    document.getElementById('loadingMsg').innerHTML = 'ვიზუალის ჩატვირთვა ...';
//    document.write("<" + "script type='text/javascript' src='CallCenter/sc/skins/" + currentSkin + "/load_skin.js?isc_version=11.0p_2016-08-04.js'><" + "/script>");


    // Include the application JS
    document.getElementById('loadingMsg').innerHTML = 'ჩატვირთვის დასრულება<br>გთხოვთ მოითმინოთ...';



    document.getElementById("loadingWrapper").style.display = "none";
</script>
<script type="text/javascript" src="CallCenter/CallCenter.nocache.js"></script>

<!-- The following script is required if you're running (Super)DevMode and are using module
     definitions that contain <script> tags.  Normally, this script is loaded automatically
     by showcase.nocache.js above, but this isn't possible when (Super)DevMode is running.
     Note: it should not create any issue to always load it below (even if already loaded). -->
<!-- <script type="text/javascript" src="CallCenter/loadScriptTagFiles.js"></script> -->

<!-- history support -->
<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
        style="position:absolute;width:0;height:0;border:0"></iframe>

<div id="divCheckbox" style="display: none;"/>
</body>
</html>
