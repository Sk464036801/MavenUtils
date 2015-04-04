package com.ever.server.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class HtmlParser {
    public static String abstractTxtFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return abstractFromString(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String abstractFromString(String s) {
        StringBuilder sb = new StringBuilder();
        String[] a = s.split(">");
        if (a == null)
            return s;
        for (int i = 0; i < a.length; i++) {
            String[] tag = a[i].split("<");
            if (tag == null || tag.length <= 0)
                continue;
            sb.append(tag[0]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String out = abstractFromString("<span style=\"font-weight: bold;\"></span>"
                    + "<br><br><img alt=\"\" src=\"http://farm4.static.flickr.com"
                    + "/3576/3403307919_5db71874af.jpg\" align=\"none\"><br>");
            System.out.println("out = " + out);
        } else {
            String out = abstractTxtFromFile(args[1]);
            System.out.println("out = " + out);
        }
    }
}
