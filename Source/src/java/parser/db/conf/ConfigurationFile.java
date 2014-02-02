/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.db.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mohamed.Azouz
 */
public class ConfigurationFile {

    public ConfigurationFile(String propFile) throws IOException {
        this.LoadConfigFile(propFile);
    }
    private String host;
    private int port;
    private String databaseName;
    private String username;
    private String password;

    private void LoadConfigFile(String propFile) throws IOException {
        Properties prop = new Properties();
        //load a properties file
        prop.load(new FileInputStream(propFile));
        this.setHost(prop.getProperty("host"));
        this.setPort(Integer.valueOf(prop.getProperty("port")));
        this.setDatabaseName(prop.getProperty("databaseName"));
        this.setUsername(prop.getProperty("username"));
        this.setPassword(prop.getProperty("password"));
    }

    
    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @param databaseName the databaseName to set
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
