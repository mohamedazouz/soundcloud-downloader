<%-- 
    Document   : index
    Created on : Nov 6, 2013, 5:09:40 PM
    Author     : Mohamed.Azouz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="description" content="SoundCloud Tracks Downloader">
        <meta name="keywords" content="SoundCloud,tracks">
        <meta name="author" content="Mohamed Azouz">
        <meta property="og:title" content="SoundCloud Tracks Downloader" />
        <meta property="og:image" content="http://mohamedazouz.kd.io/soundcloud_download_by_mohamed_azouz.png" />
        <meta property="og:description" content="SoundCloud Tracks Downloader By Mohamed Azouz ( Mohamedaliazouz@gmail.com )" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/soundcloud-downloader.css">

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/downloader.js"></script>


        <title>SoundCloud Tracks Downloader</title>
    </head>
    <body>
        <h1>Download Any SoundCloud Track!! .. just put any SoundCloud URL!</h1>
        <h5>Main track and its related ones!</h5>
        <form id="formSubmit">
            <div id="url-input">					
                <label for="url"> 
                    Put link here
                </label>
                <input type="text" name="track_url" id="url" class="textInput" placeholder="https://soundcloud.com/*">
            </div>

            <button id="track_url">
                Download
            </button>
        </form>

        <br/><br/>


        <div  id="loading" style="display: none;">
            <span>Please Wait</span>

            <img src="images/ajax-loader.gif"/>
        </div>
        <div class="clear"></div>

        <div id="content"  style="display: none;">
            <center>
                <table id="first_Track">
                </table>
            </center>
            <h3 style="margin-left: 260px;"> Related Tracks</h3>
            <table id="tracksTable">
                <thead>
                    <tr>
                        <td>Track Title</td>
                        <td>User Name</td>
                        <td>Download Link</td>
                    </tr>
                </thead>
                <tbody id="download_area"></tbody>
            </table>
        </div>
        <hr/>
        <footer>
            By <b>Mohamed Azouz</b>
            <br/>
            <!--        <a href="http://www.linkedin.com/in/mohamedazouz">Linkedin</a><br/>
                    <a href="http://www.facebook.com/azouz">Facebook</a><br/>
                    <a href="http://www.twitter.com/mohamed_azouz">Twitter</a><br/>-->
            <br>
            Send your feedback to this Email :
            <a href="mailto:mohamedaliazouz@gmail.com">mohamedaliazouz@gmail.com</a>
        </footer>
    </body>
</html>
