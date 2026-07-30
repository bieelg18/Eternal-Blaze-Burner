package com.bieelg18.eternalblazeburner.jade;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IBlockComponentProvider;

@WailaPlugin
public class EternalJadePlugin implements IWailaPlugin{

    @Override
    public void register(IWailaCommonRegistration registration){

    }

    @Override
    public void registerClient(IWailaClientRegistration registration){

        registration.registerBlockComponent(
                EternalBlazeProvider.INSTANCE,
                BlazeBurnerBlock.class
        );

    }

}
