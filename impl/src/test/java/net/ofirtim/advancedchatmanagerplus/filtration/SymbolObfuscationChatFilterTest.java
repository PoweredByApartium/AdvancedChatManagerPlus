package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

class SymbolObfuscationChatFilterTest implements ChatFilterTest {


    @Override
    public ChatFilter getFilter() {
        return getChatManager().SYMBOL_FILTER;
    }

    @Override
    public String getInput() {
        return "H3ll0! W3lc0m3 t0 th!$ $ymb0l-@nd-num3r!c t3$t. 5+5=10 + 55%%% !@ 55544 G00d-luck @nd $tay 4w3$0m3!!! (this is another test) ()";
    }

    @Override
    public String getExpectedOutput() {
        return "H3ll0! W3lc0m3 t0 this symb0l-and-num3ric t3st. 5+5=10 + 55%%% !@ 55544 G00d-luck and stay 4w3s0m3!!! (this is another test) ()";
    }

    @Override
    public int getExpectedViolations() {
        return 9;
    }
}
