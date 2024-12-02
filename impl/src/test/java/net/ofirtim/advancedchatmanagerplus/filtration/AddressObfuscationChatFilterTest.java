package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

public class AddressObfuscationChatFilterTest implements ChatFilterTest {


    @Override
    public ChatFilter getFilter() {
        return getChatManager().ADDRESS_FILTER;
    }

    @Override
    public String getInput() {
        return "H3ll0! Pl3@se ch3ck 0ut th1s s!te: http://mys1te(dot)c0m or www(dot)test123(dot)n3t. " +
                "Y0u can @ls0 em@il me @ user(at)m@!l(dot)c0m. " +
                "Try p1ng1ng: 10(dot)0(dot)0(dot)1. " +
                "Th@nk$ f0r y0ur t1m3!";
    }

    @Override
    public String getExpectedOutput() {
        return "H3ll0! Pl3@se ch3ck 0ut th1s s!te: http://mys1te.c0m or www.test123.n3t. " +
                "Y0u can @ls0 em@il me @ user@m@!l.c0m. " +
                "Try p1ng1ng: 10.0.0.1. " +
                "Th@nk$ f0r y0ur t1m3!";
    }

    @Override
    public int getExpectedViolations() {
        return 4;
    }
}
