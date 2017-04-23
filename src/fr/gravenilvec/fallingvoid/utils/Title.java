package fr.gravenilvec.fallingvoid.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Title {

    private Title() {
    }

    private static final String TEXT = "{\"text\": \"";

    public static void sendTitle(Player player, String title, String subtitle, int ticks) {
        IChatBaseComponent basetitle = ChatSerializer.a(TEXT + title + "\"}");
        IChatBaseComponent basesubtitle = ChatSerializer.a(TEXT + subtitle + "\"}");
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, basetitle);
        PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, basesubtitle);
        sendTime(player, ticks);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlepacket);
    }

    private static void sendTime(Player player, int ticks) {
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
    }

    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent basetitle = ChatSerializer.a(TEXT + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(basetitle, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}


