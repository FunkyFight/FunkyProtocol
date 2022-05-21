package io.github.funkyfight.funkyprotocol.cmd

import io.github.funkyfight.funkyprotocol.FunkyProtocol
import io.github.funkyfight.funkyprotocol.play.FunkyCardinal
import io.github.funkyfight.funkyprotocol.play.out.*
import net.minecraft.core.BlockPosition
import net.minecraft.world.entity.EntityExperienceOrb
import net.minecraft.world.entity.decoration.EntityPainting
import net.minecraft.world.entity.monster.EntityZombie
import org.bukkit.Art
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_18_R2.CraftServer
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftExperienceOrb
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPainting
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftZombie
import org.bukkit.entity.Player

class FunkyProtocolCommand(funkyProtocol: FunkyProtocol) : CommandExecutor {

    private val plugin: FunkyProtocol = funkyProtocol

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {

            val player = sender

            if(args[0].equals("spawnEntity")) {
                val zombieNMS: CraftZombie = CraftZombie(plugin.server as CraftServer, EntityZombie((player.world as CraftWorld).handle))
                zombieNMS.teleport(player.location)
                zombieNMS.setAI(true)
                FunkySpawnEntityLiving(zombieNMS.handle).sendPacket(player, true)
                FunkyTeleportEntity(zombieNMS.handle).sendPacket(player, true)
                player.sendMessage("§cZombie spawned.")

                return true
            }

            if(args[0].equals("orb")) {

                // Experience orb
                val expNMS = CraftExperienceOrb(plugin.server as CraftServer, EntityExperienceOrb((player.world as CraftWorld).handle, 1.0, 1.0, 1.0, 1))
                FunkySpawnEntityExperienceOrb(expNMS.handle).sendPacket(player, true)

                expNMS.teleport(player.location)

                FunkyTeleportEntity(expNMS.handle).sendPacket(player, true)
                player.sendMessage("§cExperience Orb spawned.")

                return true
            }

            if(args[0].equals("painting")) {

                val paintingNMS = CraftPainting(plugin.server as CraftServer, EntityPainting((player.world as CraftWorld).handle, BlockPosition(player.location.x, player.location.y, player.location.z), FunkyCardinal.NORTH.toEnumDirection()))
                paintingNMS.art = Art.KEBAB

                FunkySpawnEntityPainting(paintingNMS.handle).sendPacket(player, true)

                paintingNMS.teleport(player.location)

                FunkyTeleportEntity(paintingNMS.handle).sendPacket(player, true)
                player.sendMessage("§cArt spawned.")

                return true
            }

            if(args[0].equals("abilities")) {
                FunkyAbilities().setFlyingSpeed(1f).setFlying(true).sendPacket(player, true)
                player.sendMessage("§cAbilities sent.")
            }

            if(args[0].equals("advancements")) {
                FunkyAdvancement(true, null, null, null)
                    .sendPacket(player, true)
            }



        }

        return false
    }

}
