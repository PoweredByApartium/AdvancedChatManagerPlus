package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

class NumberObfuscationChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().NUMBER_FILTER;
    }

    @Override
    public String getInput() {
        return "Hell0 m47e h0w are you 445511 5+5+5";
    }

    @Override
    public String getExpectedOutput() {
        return "Hello mate how are you 445511 5+5+5";
    }

    @Override
    public int getExpectedViolations() {
        return 4;
    }
}
