package net.ofirtim.advancedchatmanagerplus.apploader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaperAppLoaderTest extends SpigotACMPTestBase {

    @Test
    void testOnEnable() {
        assertTrue(server.getPluginManager().isPluginEnabled(plugin));
    }

}