package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

class FloodingChatFilterTest implements ChatFilterTest {


    @Override
    public ChatFilter getFilter() {
        return getChatManager().FLOODING_FILTER;
    }

    @Override
    public String getInput() {
        return "Helloooooooo Worldddddddd Howwww are youuuuuuuuu";
    }

    @Override
    public String getExpectedOutput() {
        return "Helloooooooo Worldddddddd Howwww are youuuuuuuuu";
    }

    @Override
    public int getExpectedViolations() {
        return 3;
    }
}
