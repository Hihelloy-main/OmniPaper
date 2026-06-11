package io.github.omnipaper.server.command;

import io.github.omnipaper.server.config.OmniPaperConfigurations;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public final class OmniPaperCommand extends Command {

    public OmniPaperCommand() {
        super("omnipaper");
        this.description = "OmniPaper main command";
        this.usageMessage = "/omnipaper <reload|version|tps>";
        this.setPermission("omnipaper.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length == 0) {
            sender.sendMessage(Component.text("OmniPaper commands: reload, version, tps", NamedTextColor.YELLOW));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                try {
                    OmniPaperConfigurations.get().reload();
                    sender.sendMessage(Component.text("OmniPaper config reloaded.", NamedTextColor.GREEN));
                } catch (Exception e) {
                    sender.sendMessage(Component.text("Failed to reload config: " + e.getMessage(), NamedTextColor.RED));
                }
            }
            case "version" -> {
                sender.sendMessage(Component.text("OmniPaper 1.21.11 — a Paper fork", NamedTextColor.AQUA));
            }
            default -> {
                sender.sendMessage(Component.text("Unknown subcommand. Usage: " + usageMessage, NamedTextColor.RED));
            }
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("reload", "version").stream()
                .filter(s -> s.startsWith(args[0].toLowerCase()))
                .toList();
        }
        return List.of();
    }
}
