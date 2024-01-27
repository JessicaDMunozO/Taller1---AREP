package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ApiMovie {
    private HashMap<String, StringBuffer> moviesCache = new HashMap<>();

    private HttpConnection connection = new HttpConnection();

    private String info;

    public String searchMovieInformation(String movieName) throws IOException {

        String movieURL = "http://www.omdbapi.com/?i=tt3896198&apikey=e6058b22&t=" + movieName;
        URL url = new URL(movieURL);

        String name = movieName.toLowerCase();

        if (!moviesCache.containsKey(name)) {
            StringBuffer movieInfo = connection.URLConnection(url);
            moviesCache.put(name, movieInfo);
            info = movieInfo.toString();

        } else {
            info = moviesCache.get(name).toString();
        }

        return info;
    }
}
