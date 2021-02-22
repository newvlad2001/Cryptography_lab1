public interface Encryptor {
    String encrypt(String msg, String key);
    String decrypt(String msg, String key);

}
