/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parser.db.DBConnector;
import parser.db.conf.ConfigurationFile;
import parser.soundcloud.SoundCloudPageParser;

/**
 *
 * @author mohamed.azouz
 */
public class download extends HttpServlet {

    private DBConnector dbConnector;
    private ConfigurationFile configurationFile;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String propFile = config.getInitParameter("propFile");
        try {
            configurationFile = new ConfigurationFile(this.getServletContext().getRealPath(propFile));
            dbConnector = DBConnector.getInstance(configurationFile);
        } catch (UnknownHostException ex) {
            System.out.println("UNKNOWN HOST");
        } catch (IOException ex) {
            System.out.println("NO PROPERTIES FILE FOUND");
        }
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JsonObject responseObj = new JsonObject();
        int reponseCode = 500;
        JsonArray reponseData = null;
        String url = null;
        try {
            if (request.getParameter("trackurl") != null || request.getParameter("trackurl").equals("")) {
                url = request.getParameter("trackurl");
            } else {
                throw new Exception("URL NOT FOUND");
            }
            if (url.contains("https")) {
                url = url.substring(url.lastIndexOf("https") + "https".length(), url.length());
                url = "http" + url;
            }
            SoundCloudPageParser parser = new SoundCloudPageParser(url);
            parser.getDownloadURLMultiTrack();
            reponseData = parser.getJsonTracks();
            try {
                dbConnector.insertNewTrack(parser.getTrackObject());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.fillInStackTrace();
            }
            
            reponseCode = 200;
        } catch (Exception ex) {
            reponseCode = 500;
            ex.fillInStackTrace();
        } finally {
            responseObj.addProperty("code", String.valueOf(reponseCode));
            responseObj.add("data", reponseData);
            response.getWriter().write(responseObj.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>404</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>PAGE NOT FOUND!</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
