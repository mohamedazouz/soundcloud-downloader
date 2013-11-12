/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.soundcloud;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author mohamed.azouz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JsonObject responseObj = new JsonObject();
        int reponseCode = 500;
        JsonArray reponseData = null;
        String url = "https://soundcloud.com/universalrepublic/coldplay-atlas";
        //url = "https://soundcloud.com/ahmed-hamdy-2-1/sets/drugs";
        //url = "https://soundcloud.com/afify";
        try {
            if (url.contains("https")) {
                url = url.substring(url.lastIndexOf("https") + "https".length(), url.length());
                url = "http" + url;
            }
            SoundCloudPageParser parser = new SoundCloudPageParser(url);
            parser.getDownloadURLMultiTrack();
//            if (url.contains("sets")) {
//                parser.getDownloadURLMultiTrack();
//            } else {
//                parser.getDownloadURLSingleTrack();
//            }
            reponseData = parser.getJsonTracks();
            reponseCode = 200;
            //System.out.println(parser.getDirectDownloadLink(parser.getDownloadURLSingleTrack()));
        } catch (Exception ex) {
            reponseCode = 500;
            ex.fillInStackTrace();
        } finally {
            responseObj.addProperty("code", String.valueOf(reponseCode));
            responseObj.add("data", reponseData);
            System.out.println(responseObj);
        }
    }
}
