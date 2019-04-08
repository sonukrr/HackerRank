package com.hacker.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {


    public byte[] encrypt(String message) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0,  k = 16; j < 8;)
            {
                keyBytes[k++] = keyBytes[j++];
            }

            final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            final byte[] plainTextBytes = message.getBytes("utf-8");
            final byte[] cipherText = cipher.doFinal(plainTextBytes);
           // final String encodedCipherText = new sun.misc.BASE64Encoder().encode(cipherText);

            return cipherText;    
        }
        catch (java.security.InvalidAlgorithmParameterException e) { System.out.println("Invalid Algorithm"); }
        catch (javax.crypto.NoSuchPaddingException e) { System.out.println("No Such Padding"); }
        catch (java.security.NoSuchAlgorithmException e) { System.out.println("No Such Algorithm"); }
        catch (java.security.InvalidKeyException e) { System.out.println("Invalid Key"); }
        catch (BadPaddingException e) { System.out.println("Invalid Key");}
        catch (IllegalBlockSizeException e) { System.out.println("Invalid Key");}
        catch (UnsupportedEncodingException e) { System.out.println("Invalid Key");}

        return null;
    }

    public String decrypt(byte[] message) {
        try
        {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0,  k = 16; j < 8;)
            {
                keyBytes[k++] = keyBytes[j++];
            }

            final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, key, iv);

            //final byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(message);
            final byte[] plainText = decipher.doFinal(message);

            return plainText.toString();            
        }
        catch (java.security.InvalidAlgorithmParameterException e) { System.out.println("Invalid Algorithm"); }
        catch (javax.crypto.NoSuchPaddingException e) { System.out.println("No Such Padding"); }
        catch (java.security.NoSuchAlgorithmException e) { System.out.println("No Such Algorithm"); }
        catch (java.security.InvalidKeyException e) { System.out.println("Invalid Key"); }
        catch (BadPaddingException e) { System.out.println("Invalid Key");}
        catch (IllegalBlockSizeException e) { System.out.println("Invalid Key");}
        catch (UnsupportedEncodingException e) { System.out.println("Invalid Key");}     
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}