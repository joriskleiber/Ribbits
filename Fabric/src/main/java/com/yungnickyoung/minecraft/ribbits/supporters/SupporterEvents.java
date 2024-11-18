package com.yungnickyoung.minecraft.ribbits.supporters;

import com.yungnickyoung.minecraft.ribbits.network.packet.RequestSupporterHatStatePacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.List;
import java.util.UUID;

public class SupporterEvents {
    public static void onPlayerJoin(PacketSender sender) {
        // Get the server's current list of players with the supporter hat enabled
        List<UUID> playersWithSupporterHat = SupportersListServer.getPlayersWithSupporterHat().stream().toList();

        // Send the list of players with the supporter hat enabled to the new player, and request their own supporter hat state
        sender.sendPacket(new RequestSupporterHatStatePacket(playersWithSupporterHat));
    }
}
