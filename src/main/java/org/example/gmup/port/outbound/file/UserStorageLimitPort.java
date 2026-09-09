package org.example.gmup.port.outbound.file;

public interface UserStorageLimitPort {
    long getStorageLimit(long userId);
    void updateStorageLimit(long userId, long storageLimit);

}
