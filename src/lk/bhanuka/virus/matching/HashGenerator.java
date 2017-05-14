/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.matching;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhanuka
 */
public class HashGenerator {
    
    public static String generate(String filepath){
        
        try {
            File file = new File(filepath);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String checksum = HashGenerator.getFileChecksum(messageDigest, file);
            return checksum;

        } catch (IOException ex) {
            Logger.getLogger(HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {

        FileInputStream fis = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0; 

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        fis.close();

        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

       return sb.toString();
    }
}
