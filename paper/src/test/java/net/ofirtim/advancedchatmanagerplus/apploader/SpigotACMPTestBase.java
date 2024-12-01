package net.ofirtim.advancedchatmanagerplus.apploader;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class SpigotACMPTestBase {
    protected ServerMock server;

    protected PaperAppLoader plugin;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(PaperAppLoader.class);
    }

    @AfterEach
    public void tearDown() {
        // Stop the scheduler before shutting down the plugin
        // Otherwise, async tasks might run after the db connection is closed, which will cause the test to fail
        server.getScheduler().cancelTasks(plugin);
        server.getScheduler().shutdown();

        // Stop the mock server
        MockBukkit.unmock();

    }

}
