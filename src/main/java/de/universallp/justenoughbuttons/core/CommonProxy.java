package de.universallp.justenoughbuttons.core;

import de.universallp.justenoughbuttons.core.handlers.ConfigHandler;
import de.universallp.justenoughbuttons.JEIButtons;
import de.universallp.justenoughbuttons.core.handlers.ClientNotifyHandler;
import de.universallp.justenoughbuttons.core.handlers.MagnetModeHandler;
import de.universallp.justenoughbuttons.core.network.MessageExecuteButton;
import de.universallp.justenoughbuttons.core.network.MessageMagnetMode;
import de.universallp.justenoughbuttons.core.network.MessageNotifyClient;
import de.universallp.justenoughbuttons.core.network.MessageRequestStacks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by universallp on 11.08.2016 16:02.
 * This file is part of JustEnoughButtons which is licenced
 * under the MOZILLA PUBLIC LICENCE 2.0 - mozilla.org/en-US/MPL/2.0/
 * github.com/UniversalLP/JustEnoughButtons
 */
public class CommonProxy {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(JEIButtons.MODID);
    public static final MagnetModeHandler MAGNET_MODE_HANDLER = new MagnetModeHandler();

    public void preInit(FMLPreInitializationEvent e) {
        ConfigHandler.loadConfig(e.getSuggestedConfigurationFile());
    }

    public void init(FMLInitializationEvent e) {
        INSTANCE.registerMessage(MessageNotifyClient.class, MessageNotifyClient.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageRequestStacks.class, MessageRequestStacks.class, 1, Side.SERVER);
        INSTANCE.registerMessage(MessageMagnetMode.class, MessageMagnetMode.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageExecuteButton.class, MessageExecuteButton.class, 3, Side.SERVER);
        MinecraftForge.EVENT_BUS.register(MAGNET_MODE_HANDLER);
        if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
            MinecraftForge.EVENT_BUS.register(new ClientNotifyHandler());

        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        ConfigHandler.loadPostInit();
    }

    public int getMouseX() { return 0;}

    public int getMouseY() { return 0; }

    public int getScreenWidth() { return 0; }

    public int getScreenHeight() { return 0; }


    public void registerKeyBind() { }

    public void playClick() { }
}
