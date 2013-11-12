DownloaderClass = function() {
    var downloader = {
        isSubmited: false,
        getDownloadLink: function(url) {
            var data = {'trackurl': url};
            downloader.sendData(data, function(result) {
                downloader.isSubmited = false;
                if (result.code == 200) {
                    downloader.setDownloadArea(result.data);
                } else {
                    downloader.setDownloadArea(result.data, "Sorry, Some Error Happened! .. Please try again.");
                }
            });
        }, sendData: function(data, feedback) {
            $.post("download", data, function(result) {
                feedback(result);
            }, "json");
        }, setDownloadArea: function(result, msg) {
            out = "";
            if (!msg) {
                for (var i = 0; i < result.length; i++) {
                    if (i == 0) {
                        out += "<tr>";
                        out += "<td> Track Name</td>";
                        out += "<td>";
                        out += "<a href='" + result[i].uri + "' target='_blank'>";
                        out += result[i].title;
                        out += "</a>";
                        out += "</td>";
                        out += "</tr>";
                        out += "<tr>";
                        out += "<td> User Name</td>";
                        out += "<td>";
                        out += "<a href='" + result[i].user.permalink + "' target='_blank'>";
                        out += result[i].user.name;
                        out += "</a>";
                        out += "</td>";
                        out += "</tr>";
                        out += "<tr>";
                        out += "<td> DOWNLOAD LINK</td>";
                        out += "<td>";
                        out += "<a href='" + result[i].streamURL + "' target='_blank'>";
                        out += "DOWNLOAD LINK";
                        out += "</a>";
                        out += "</td>";
                        out += "</tr>";
                        $("#first_Track").html(out);
                        out = "";
                    } else {
                        out += "<tr>"
                        out += "<td>";
                        out += "<a href='" + result[i].uri + "' target='_blank'>";
                        out += result[i].title;
                        out += "</a>";
                        out += "</td>";
                        out += "<td>";
                        out += "<a href='" + result[i].user.permalink + "' target='_blank'>";
                        out += result[i].user.name;
                        out += "</a>";
                        out += "</td>";
                        out += "<td>";
                        out += "<a href='" + result[i].streamURL + "' target='_blank'>";
                        out += "DOWNLOAD LINK";
                        out += "</a>";
                        out += "</td>";
                        out += "</tr>";
                    }
                }
            } else {
                out = msg;
            }
            $("#download_area").html(out);
            $("#loading").hide();
            $("#content").show();
        }
    };
    $(function() {

        $("#formSubmit").submit(function() {
            return false;
        });
        $("#track_url").click(function() {
            if (!downloader.isSubmited) {
                url = $("input[name=track_url]").val();
                if (url) {
                    pattern = new RegExp('^(https?:\/\/)?soundcloud.com\/([a-z]|[0-9]|-|_)+(sets\/)?(\/([a-z]|[0-9]|-|_)+)?$', 'i'); // fragment locater
                    if (!pattern.test(url)) {
                        alert("Please enter a valid URL.");
                        return false;
                    } else {
                        downloader.isSubmited = true;
                        $("#loading").show();
                        downloader.getDownloadLink(url);
                        return true;
                    }
                } else {
                    console.log("ERROR");
                }
            } else {
                alert("Please wait till we got result");
            }
        });
    });
    return downloader;
};


var downloader = new DownloaderClass();
