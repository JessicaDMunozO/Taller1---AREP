package edu.escuelaing.arem.ASE.app;

import java.net.*;
import java.io.*;

public class HttpServer {

    private static ApiMovie myAPI = new ApiMovie();

    private static void printMovieResult(String movieName, PrintWriter out) {
        String movieInfo;

        try {
            movieInfo = myAPI.searchMovieInformation(movieName);
        } catch (Exception e) {
            movieInfo = "Ups, try later.";
            e.printStackTrace();
        }

        String movieResponse = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                + "\r\n"
                + movieInfo;

        out.println(movieResponse);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;

        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String inputLine, outputLine;
            String movieName = "";
            boolean searching = false;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if (inputLine.contains("GET") && inputLine.contains("=")) {
                    String[] getParts = inputLine.split("=");
                    String[] parts = getParts[1].split(" ");
                    movieName = parts[0];
                    System.out.println(movieName);
                    searching = true;
                }

                if (!in.ready()) {
                    break;
                }
            }

            if (!searching) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\r\n"
                        + "<html>\r\n"
                        + "    <head>\r\n"
                        + "        <title>Movies Info</title>\r\n"
                        + "        <meta charset=\"UTF-8\">\r\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                        + "    </head>\r\n"
                        + "    <body>\r\n"
                        + "        <h1>Movies Info</h1>\r\n"
                        + "        <form action=\"/movie\">\r\n"
                        + "            <label for=\"title\">Title:</label><br>\r\n"
                        + "            <input type=\"text\" id=\"title\" name=\"title\"><br><br>\r\n"
                        + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n"
                        + "        </form> \r\n"
                        + "        <div id=\"getrespmsg\"></div>\r\n"
                        + "\r\n"
                        + "        <script>\r\n"
                        + "                function loadGetMsg() {\r\n"
                        + "                     let titleVar = document.getElementById(\"title\").value;\r\n"
                        + "                     const xhttp = new XMLHttpRequest();\r\n"
                        + "                     xhttp.onload = function() {\r\n"
                        + "                         document.getElementById(\"getrespmsg\").innerHTML =\r\n"
                        + "                         this.responseText;\r\n"
                        + "                     };\r\n"
                        + "                xhttp.open(\"GET\", \"/movie?title=\"+titleVar);\r\n"
                        + "                xhttp.send();\r\n"
                        + "            }\r\n"
                        + "        </script>\r\n"
                        + "    </body>\r\n"
                        + "</html>";

                out.println(outputLine);
            }

            if (movieName != "") {
                printMovieResult(movieName, out);
            }

            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }
}