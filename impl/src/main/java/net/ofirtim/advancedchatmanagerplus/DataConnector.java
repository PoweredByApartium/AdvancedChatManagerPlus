/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import java.io.File;
import java.util.Map;

public interface DataConnector {

    TranslationProvider getTranslationProvider();

    DataProvider getDataProvider();

    void connect(File dataFolder, Map<String, Object> params) throws Exception;


}
