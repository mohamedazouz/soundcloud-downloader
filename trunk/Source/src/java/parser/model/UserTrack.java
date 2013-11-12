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
public class UserTrack implements SoundCloud {

    private String name;
    private String permalink;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * @param permalink the permalink to set
     */
    public void setPermalink(String permalink) {
        this.permalink = SOUNDCOULD_URL + "/" + permalink;
    }
}
