package net.ofirtim.advancedchatmanagerplus.apploader.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import net.ofirtim.advancedchatmanagerplus.Permission;

@CommandPermission(Permission.COMMAND_RELOAD)
@CommandAlias("acmp")
public class MainCommand {

    @Description("Reloads the configuration files.")
    @CommandAlias("reload")
    @CommandCompletion("ALL|channels|playerdata|locale|settings")
    public void configReload(String configuration) {
        switch (configuration) {
            case "ALL" -> {
                //TODO reload all configs
            }
            case "channels" -> {
                //TODO reload channels config
            }

            case "playerdata" -> {
                //TODO reload all player data
            }

            case "locale" -> {
                //TODO reload language data
            }

            case "settings" -> {
                //TODO reload only config.yml
            }

            default -> {

            }
        }
    }


}
