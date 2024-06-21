/**
 * 
 */
package org.drdeesw.commons.crypto;


import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 
 */
public class AESUtil
{
  private static final String AES_ALGORITHM  = "AES/GCM/NoPadding";
  private static final int    AES_KEY_SIZE   = 256;
  private static final int    GCM_IV_LENGTH  = 12;
  private static final int    GCM_TAG_LENGTH = 16;

  /**
   * @param plainText
   * @param secretKey
   * @return
   * @throws Exception
   */
  public static String encrypt(
    String plainText,
    String secretKey) throws Exception
  {
    Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
    byte[] iv = new byte[GCM_IV_LENGTH];
    SecureRandom secureRandom = new SecureRandom();
    
    secureRandom.nextBytes(iv);
    
    GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
    
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);
    
    byte[] encryptedText = cipher.doFinal(plainText.getBytes());
    byte[] encryptedTextWithIv = new byte[iv.length + encryptedText.length];
    
    System.arraycopy(iv, 0, encryptedTextWithIv, 0, iv.length);
    System.arraycopy(encryptedText, 0, encryptedTextWithIv, iv.length, encryptedText.length);
    
    return Base64.getEncoder().encodeToString(encryptedTextWithIv);
  }


  /**
   * @param encryptedText
   * @param secretKey
   * @return
   * @throws Exception
   */
  public static String decrypt(
    String encryptedText,
    String secretKey) throws Exception
  {
    Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
    byte[] encryptedTextWithIv = Base64.getDecoder().decode(encryptedText);
    byte[] iv = new byte[GCM_IV_LENGTH];
    
    System.arraycopy(encryptedTextWithIv, 0, iv, 0, iv.length);
    
    GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
    
    cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
    
    byte[] originalText = cipher.doFinal(encryptedTextWithIv, iv.length,
      encryptedTextWithIv.length - iv.length);
    
    return new String(originalText);
  }


  /**
   * @return
   * @throws Exception
   */
  public static String generateSecretKey() throws Exception
  {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    
    keyGenerator.init(AES_KEY_SIZE);
    
    SecretKey secretKey = keyGenerator.generateKey();
    
    return Base64.getEncoder().encodeToString(secretKey.getEncoded());
  }
}
