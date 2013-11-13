<%-- 
    Document   : index
    Created on : Nov 6, 2013, 5:09:40 PM
    Author     : Mohamed.Azouz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 7]> <html class="ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 9]> <html class="ie9" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html lang="en"> <!--<![endif]-->
    <html>
        <head>
            <meta name="description" content="SoundCloud Tracks Downloader">
            <meta name="keywords" content="SoundCloud,tracks">
            <meta name="author" content="Mohamed Azouz">
            <meta property="og:title" content="SoundCloud Tracks Downloader" />
            <meta property="og:image" content="http://mohamedazouz.kd.io/soundcloud_download_by_mohamed_azouz.png" />
            <meta property="og:description" content="SoundCloud Tracks Downloader By Mohamed Azouz ( Mohamedaliazouz@gmail.com )" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" media="all" href="css/style.css" />
            <!--[if lt IE 9]>
                    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->

            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
            <script type="text/javascript" src="js/downloader.js"></script>
            


            <title>SoundCloud Tracks Downloader</title>
        </head>
        <body>
            <header class="responsive-header">
                <a href="#" class="responsive-logo">
                    <span>soundCloud Downloader</span>
                </a>
                <div class="social-icons">
                    <a href="#" class="twitter"></a>
                    <a href="#" class="linked"></a>
                    <a href="#" class="facebook"></a>
                    <a href="#" class="youtube"></a>
                </div>
            </header>
            <div class="container">


            </header>
            <div class="social-icons">
                <a href="#" class="twitter"></a>
                <a href="#" class="linked"></a>
                <a href="#" class="facebook"></a>
                <a href="#" class="youtube"></a>
            </div>
        </div>
        <section class="black-bar">
            <article class="container">
                <h1 class="logo">
                    <a href="#">
                        SOUNDCLOUD DOWNLOADER
                        <span></span>
                    </a>
                </h1>

                <div class="text">
                    <div class="line-1">Meet The New SoundCloud </div>
                    <div class="line-2">Downloader* </div>
                    <div class="line-3">Download your favorites Tracks</div>
                </div>
            </article>
        </section>

<!--        <form id="formSubmit">
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
        </div>-->

        <section class="container">
            <article>
                <div class="help-text">
                    Put link here
                </div>
                <div class="download">
                    <input type="text" name="track_url" id="url" placeholder="https://soundcloud.com/*">
                    <input type="submit" value="" id="track_url">
                </div>
                <div class="loader" style="text-align:center; display: none;" id="loading"><img src="images/loaderAnimationHTML.gif" alt=""></div>

                <div class="results" style="display: none;" id="content" >

                    <div class="track" id="first_Track">
                    </div>

                    <label>Related Tracks</label>

                    <table width="700" border="0" align="center">
                        <thead>
                            <tr>
                                <th scope="col">Track Name</th>
                                <th scope="col">Download</th>
                            </tr>
                        </thead>
                        <tbody id="download_area">
                        </tbody>
                    </table>
                </div>
            </article>

        </section>
        <footer class="footer-reponsive">
            <a href="mailto:info.soundcloud.downloader@gmail.com">info.soundcloud.downloader@gmail.com</a>
        </footer>
    </body>
</html>
