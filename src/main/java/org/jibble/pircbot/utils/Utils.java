package org.jibble.pircbot.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *  @author Ondrej Zizka, ozizka at redhat.com
 */
public class Utils {
    private static final Logger log = LoggerFactory.getLogger( Utils.class );

    /**
     * A convenient method that accepts an IP address represented as a
     * long and returns an integer array of size 4 representing the same
     * IP address.
     *
     * @since PircBot 0.9.4
     *
     * @param address the long value representing the IP address.
     *
     * @return An int[] of size 4.
     */
    public static int[] longToIp(long address) {
        int[] ip = new int[4];
        for (int i = 3; i >= 0; i--) {
            ip[i] = (int) (address % 256);
            address = address / 256;
        }
        return ip;
    }


    /**
     * A convenient method that accepts an IP address represented by a byte[]
     * of size 4 and returns this as a long representation of the same IP
     * address.
     *
     * @since PircBot 0.9.4
     *
     * @param address the byte[] of size 4 representing the IP address.
     *
     * @return a long representation of the IP address.
     */
    public static long ipToLong(byte[] address) {
        if (address.length != 4) {
            throw new IllegalArgumentException("byte array must be of length 4");
        }
        long ipNum = 0;
        long multiplier = 1;
        for (int i = 3; i >= 0; i--) {
            int byteVal = (address[i] + 256) % 256;
            ipNum += byteVal*multiplier;
            multiplier *= 256;
        }
        return ipNum;
    }
    

}// class
