package me.cominixo.betterf3.config.gui.modules;

import me.cominixo.betterf3.config.ModConfigFile;
import me.cominixo.betterf3.modules.BaseModule;
import me.cominixo.betterf3.modules.EmptyModule;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class AddModuleScreen {


    public static ConfigBuilder getConfigBuilder(ModulesScreen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent);

        builder.setSavingRunnable(ModConfigFile.saveRunnable);


        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("config.betterf3.category.general"));



        DropdownBoxEntry<BaseModule> dropdownEntry = entryBuilder.startDropdownMenu(new TranslatableText("config.betterf3.add_button.module_name"),
                DropdownMenuBuilder.TopCellElementBuilder.of(new EmptyModule(true),
                        BaseModule::getModule,
                        (object) -> new LiteralText(object.toString()))).setSelections(BaseModule.allModules)
                .setSaveConsumer((BaseModule newValue) -> {
                    try {
                        parent.modulesListWidget.addModule(newValue.getClass().newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        parent.modulesListWidget.addModule(newValue);
                    }
                })
                .build();

        general.addEntry(dropdownEntry);
        builder.transparentBackground();

        builder.transparentBackground();

        return builder;
    }
}
