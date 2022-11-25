// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.nio.charset.Charset;

public class Base64
{
    private static final byte PADDING = 61;
    private static final byte[] ENCODE;
    private static final byte[] DECODE;
    static final Charset INPUT;
    static final Charset ASCII;
    
    public static String encodeStr(final String s) {
        return new String(encode(s.getBytes(Base64.INPUT)), Base64.ASCII);
    }
    
    public static String decodeStr(final String s) {
        return new String(decode(s.getBytes(Base64.ASCII)), Base64.INPUT);
    }
    
    public static byte[] encode(final byte[] u) {
        if (u.length == 0) {
            return new byte[0];
        }
        final byte[] v = new byte[(u.length + 2) / 3 << 2];
        byte b;
        byte c;
        for (int i = 0, j = 0; i < u.length; c = u[i++], v[j++] = Base64.ENCODE[(b & 0xF) << 2 | c >>> 6], v[j++] = Base64.ENCODE[c & 0x3F]) {
            final byte a = u[i++];
            v[j++] = Base64.ENCODE[a >>> 2 & 0x3F];
            if (i == u.length) {
                v[j++] = Base64.ENCODE[(a & 0x3) << 4];
                v[j++] = 61;
                v[j++] = 61;
                break;
            }
            b = u[i++];
            v[j++] = Base64.ENCODE[(a & 0x3) << 4 | b >>> 4];
            if (i == u.length) {
                v[j++] = Base64.ENCODE[(b & 0xF) << 2];
                v[j++] = 61;
                break;
            }
        }
        return v;
    }
    
    public static byte[] decode(final byte[] u) {
        if (u.length == 0) {
            return new byte[0];
        }
        int n = (u.length + 3 >> 2) * 3;
        int i = u.length;
        while (u[--i] == 61) {
            --n;
        }
        final byte[] v = new byte[n];
        int j;
        i = (j = 0);
        while (i < u.length) {
            byte x = u[i];
            final byte a = Base64.DECODE[x & 0xFF];
            if (a == -1) {
                throw new Err(x, i);
            }
            if (a == 64) {
                break;
            }
            v[j] = (byte)(a << 2);
            if (++i == u.length) {
                break;
            }
            x = u[i];
            final byte b = Base64.DECODE[x & 0xFF];
            if (b == -1) {
                throw new Err(x, i);
            }
            if (b == 64) {
                break;
            }
            final byte[] array = v;
            final int n2 = j++;
            array[n2] |= (byte)(b >>> 4);
            if (++i == u.length) {
                break;
            }
            if (j == n) {
                break;
            }
            v[j] = (byte)(b << 4);
            x = u[i];
            final byte c = Base64.DECODE[x & 0xFF];
            if (c == -1) {
                throw new Err(x, i);
            }
            if (c == 64) {
                break;
            }
            final byte[] array2 = v;
            final int n3 = j++;
            array2[n3] |= (byte)(c >>> 2);
            if (++i == u.length) {
                break;
            }
            if (j == n) {
                break;
            }
            v[j] = (byte)(c << 6);
            x = u[i++];
            final byte d = Base64.DECODE[x & 0xFF];
            if (d == -1) {
                throw new Err(x, i);
            }
            if (d == 64) {
                break;
            }
            final byte[] array3 = v;
            final int n4 = j++;
            array3[n4] |= d;
        }
        return v;
    }
    
    public static void testString(final String s) {
        System.out.println("[" + s.length() + "] Original: " + s);
        final byte[] a = s.getBytes();
        final String b = new String(a);
        System.out.println("[" + b.length() + "] getBytes: " + b);
        final byte[] c = encode(a);
        final String d = new String(c);
        System.out.println("[" + d.length() + "]  Encoded: " + d);
        final byte[] e = decode(c);
        final String f = new String(e);
        System.out.println("[" + f.length() + "]  Decoded: " + f);
        final boolean stringEqual = s.equals(f);
        boolean bytesEqual = a.length == e.length;
        if (bytesEqual) {
            for (int i = 0; i < a.length; ++i) {
                if (a[i] != e[i]) {
                    bytesEqual = false;
                    break;
                }
            }
        }
        System.out.println("   Bytes Equal: " + bytesEqual);
        System.out.println("  String Equal: " + stringEqual);
        if (!bytesEqual || !stringEqual) {
            throw new RuntimeException("WTF");
        }
    }
    
    static {
        ENCODE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        DECODE = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 64, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        INPUT = Charset.forName("UTF-8");
        ASCII = Charset.forName("ASCII");
    }
    
    public static class Err extends RuntimeException
    {
        Err(final byte b, final int x) {
            super("Unexpected byte " + b + " at index " + x);
        }
    }
}
