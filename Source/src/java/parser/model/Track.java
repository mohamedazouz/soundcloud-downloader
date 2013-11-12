/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.model;

import static parser.model.SoundCloud.SOUNDCOULD_URL;

/**
 *
 * @author mohamed.azouz
 */
public class Track implements SoundCloud {

    private String title;
    private String id;
    private String uri;
    private UserTrack user;
    private String streamURL;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = SOUNDCOULD_URL + uri;
    }

    /**
     * @return the user
     */
    public UserTrack getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserTrack user) {
        this.user = user;
    }

    /**
     * @return the streamURL
     */
    public String getStreamURL() {
        return streamURL;
    }

    /**
     * @param streamURL the streamURL to set
     */
    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
