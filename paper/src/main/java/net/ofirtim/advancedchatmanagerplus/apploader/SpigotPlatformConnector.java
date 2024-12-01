package net.ofirtim.advancedchatmanagerplus.apploader;

import net.ofirtim.advancedchatmanagerplus.PlatformConnector;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class SpigotPlatformConnector implements PlatformConnector {

    @Override
    public <T> CompletableFuture<T> supplyAsync(Callable<T> callable) {
        return null;
    }
}
