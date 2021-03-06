package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Material;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.util.Util;

public class MaterialArg extends CommandArg<Material> {

    public static class Factory extends CommandArgFactory<Material> {

        @Override
        public MaterialArg createCommandArg(CommandsMessages messages, String string) {
            return new MaterialArg(messages, string);
        }
    }

    public static class NoSuchMaterial extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchMaterial(CommandsMessages messages, String name) {
            super(messages.noSuchMaterial(name));
        }
    }

    static Material getMaterial(CommandsMessages messages, String pattern) {
        Material material = Util.matchMaterial(pattern);
        if (material == null) {
            throw new MaterialArg.NoSuchMaterial(messages, pattern);
        }

        return material;
    }

    public MaterialArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Material value() {
        return getMaterial(messages, string);
    }
}
