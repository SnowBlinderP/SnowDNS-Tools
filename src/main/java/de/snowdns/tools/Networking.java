package de.snowdns.tools;

import org.xbill.DNS.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Networking {

    public static String getIPbyHost(String domain) throws UnknownHostException {
        InetAddress addr = Address.getByName("www.dnsjava.org");
        return addr.toString();
    }
    public static String getIPbyHostDNS(String domain,String dns) throws UnknownHostException, TextParseException {
        Lookup lookup = new Lookup(domain, Type.A);
        SimpleResolver resolver = new SimpleResolver(dns);
        resolver.setTimeout(5);
        lookup.setResolver(resolver);
        Record[] result = lookup.run();
        if (result == null) return null;

        List<Record> records = java.util.Arrays.asList(result);
        java.util.Collections.shuffle(records);
        for (Record record : records) {

                return ((ARecord) record).getAddress().getHostAddress();
        }

        return "";
    }
    public static boolean pingHost(String host, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException e) {
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
    public static boolean APIReachable(){
        return pingHost("net01.snowdns.de",80,5);
    }

}
