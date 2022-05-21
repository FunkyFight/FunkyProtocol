package io.github.funkyfight.funkyprotocol

import io.github.funkyfight.funkyprotocol.cmd.FunkyProtocolCommand
import org.bukkit.plugin.java.JavaPlugin

class FunkyProtocol : JavaPlugin(){

    override fun onEnable() {
        logger.info("FunkyProtocol has been enabled!")

        // Command
        getCommand("funkyprotocol")!!.setExecutor(FunkyProtocolCommand(this))
    }

    override fun onDisable() {
        logger.info("FunkyProtocol has been disabled!")
    }
}