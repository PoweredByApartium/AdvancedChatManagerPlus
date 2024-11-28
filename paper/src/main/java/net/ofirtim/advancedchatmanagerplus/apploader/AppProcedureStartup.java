/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader;

public class AppProcedureStartup {

    private final PaperAppLoader appLoader;

    public AppProcedureStartup(PaperAppLoader appLoader) {
        this.appLoader = appLoader;
        setupListeners();
        setupObjectContext();
        setupCommands();
    }

    public void setupListeners() {

    }

    public void setupCommands() {

    }

    public void setupObjectContext() {

    }
}
