/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.soundcloud;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import parser.db.DBConnector;
import parser.model.Track;
import parser.model.UserTrack;

/**
 *
 * @author Mohamed.Azouz
 */
public class SoundCloudPageParser {

    private String pageUrl;
    private Gson gson = new Gson();
    private JsonArray jsonTracks;
    private JsonObject trackObject;
    private boolean firstTrack = false;
    private final String SOUDNCLOUD_DOWNLOAD_LINK = "https://api.soundcloud.com/i1/tracks/";
    private final String SOUDNCLOUD_API_CREDENTIALS_LINK = "/streams?client_id=b45b1aa10f1ac2941910a7f0d10f8e28&app_version=ec560e43";

    public SoundCloudPageParser(String pageUrl) {
        jsonTracks = new JsonArray();
        this.pageUrl = pageUrl;
        trackObject = new JsonObject();
    }

    private String generateJsonDownloadLink(String id) {
        String generatedLink = SOUDNCLOUD_DOWNLOAD_LINK + id + SOUDNCLOUD_API_CREDENTIALS_LINK;
        return generatedLink;
    }

    public void getDownloadURLSingleTrack() throws IOException {
        PrintWriter printWriter = new PrintWriter(new File("out.txt"));
        URL url = new URL(this.getPageUrl());
        Scanner sc = new Scanner(url.openStream());
        String downloadURL = "NOT_FOUND";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("window.SC.bufferTracks.push")) {
                this.addNewElement(extractStreamURL(this.extractJsonObj(line)));
                break;
            }
        }
    }

    public void getDownloadURLMultiTrack() throws IOException {
        URL url = new URL(this.getPageUrl());
        getTrackObject().addProperty("track_url", this.getPageUrl());
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("window.SC.bufferTracks.push")) {
                this.addNewElement(this.extractStreamURL(this.extractJsonObj(line)));
            }
        }
        getTrackObject().add("track_result", this.getJsonTracks());
    }

    private void addNewElement(Track track) {
        JsonElement element = gson.toJsonTree(track, new TypeToken<Track>() {
        }.getType());

        getJsonTracks().add(element);
    }

    public String getDirectDownloadLink(String downloadURL) throws MalformedURLException, IOException {
        URL url = new URL(downloadURL);
        Scanner sc = new Scanner(url.openStream());
        StringBuilder builder = new StringBuilder();
        while (sc.hasNextLine()) {
            builder.append(sc.nextLine());
        }

        JsonObject trackObj = new JsonParser().parse(builder.toString()).getAsJsonObject();

        return trackObj.get("rtmp_mp3_128_url").getAsString();
    }

    private String getJsonDownloadElement() {
        return "http_mp3_128_url";
    }

    private Track extractStreamURL(String jsonLine) {
        Track track = new Track();
        JsonObject trackObj = new JsonParser().parse(jsonLine).getAsJsonObject();
        track.setTitle(trackObj.get("title").getAsString());
        track.setUri(trackObj.get("uri").getAsString());
        track.setId(trackObj.get("id").getAsString());
        UserTrack user = new UserTrack();
        user.setName(trackObj.get("user").getAsJsonObject().get("username").getAsString());
        user.setPermalink(trackObj.get("user").getAsJsonObject().get("permalink").getAsString());
        track.setUser(user);
        track.setStreamURL(trackObj.get("streamUrl").getAsString());
        if (!firstTrack) {
            getTrackObject().add("track_info", gson.toJsonTree(track));
        }
        return track;
    }

    private String extractJsonObj(String line) {
        //line = "window.SC.bufferTracks.push({'id':105272605,'uid':'ajDYs7Bk9dRY','user':{'username':'Afify','permalink':'afify'},'uri':'/afify/wen-el-fera5-alaa-wardi','duration':74193,'token':'jxbz8','name':'wen-el-fera5-alaa-wardi','title':'Wen el fera5 - Alaa wardi btsarrof','commentable':true,'revealComments':true,'commentUri':'/afify/wen-el-fera5-alaa-wardi/comments/','streamUrl':'http://media.soundcloud.com/stream/ajDYs7Bk9dRY?stream_token=jxbz8','waveformUrl':'http://w1.sndcdn.com/ajDYs7Bk9dRY_m.png','propertiesUri':'/afify/wen-el-fera5-alaa-wardi/properties/','statusUri':'/transcodings/ajDYs7Bk9dRY','replacingUid':null,'preprocessingReady':true,'renderingFailed':false,'isPublic':true,'geo':[],'commentableByUser':true,'favorite':false,'followingTrackOwner':false});";
        String jsonObj = line.substring(line.lastIndexOf("window.SC.bufferTracks.push(") + "window.SC.bufferTracks.push(".length(), line.length() - 2);
        return jsonObj;
    }

    /**
     * @return the pageUrl
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * @param pageUrl the pageUrl to set
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * @return the jsonTracks
     */
    public JsonArray getJsonTracks() {
        return jsonTracks;
    }

    /**
     * @return the trackObject
     */
    public JsonObject getTrackObject() {
        return trackObject;
    }
}
