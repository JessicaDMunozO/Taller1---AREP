package edu.escuelaing.arem.ASE.app;

import java.net.*;

public class URLParser {
    public static void main(String[] args) throws MalformedURLException {
        URL myurl = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=e6058b22&t=titanic");
        System.out.println("Host: " + myurl.getHost());
        System.out.println("Protocol: " + myurl.getProtocol());
        System.out.println("Authority: " + myurl.getAuthority());
        System.out.println("Port: " + myurl.getPort());
        System.out.println("Path: " + myurl.getPath());
        System.out.println("Query: " + myurl.getQuery());
        System.out.println("File: " + myurl.getFile());
        System.out.println("Ref: " + myurl.getRef());
    }
}
