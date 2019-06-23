package de.snowdns.tools;

import org.apache.commons.net.util.Base64;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileIO {

    public static void download(String url, String fileName) throws Exception {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            Files.copy(in, Paths.get("./" + fileName));
        }
    }

    public static void writeFile(String name, String context) {
        // Convert the string to a
        // byte array.
        String s = context;
        byte[] data = s.getBytes();
        Path p = Paths.get("./" + name);
        try {
            Files.delete(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, StandardOpenOption.CREATE_NEW))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            x.printStackTrace();
            System.err.println(x);
        }
    }
    public static String ReadFile(String name) {
        Path file = Paths.get("./" + name);
        String content = "0";
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                content = line;

            }
        } catch (IOException x) {

        }
        return content;
    }


    public static String from64(String data) {
        byte[] byteArray = Base64.decodeBase64(data.getBytes());

        String decodedString = new String(byteArray);
        return decodedString;
    }
}
