package com.yuuki.projectx.game.managers;

import com.yuuki.projectx.game.objects.Player;
import com.yuuki.projectx.networking.GameSession;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.UserKeyBindingsModule;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.UserKeyBindingsUpdate;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.AudioSettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.DisplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.GameplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.QualitySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.*;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.menuBarsModules.ClientUIMenuBarItemModule;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.menuBarsModules.ClientUIMenuBarModule;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules.*;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.settingsModules.*;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.tooltipsModules.ClientUITextReplacementModule;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.tooltipsModules.ClientUITooltipModule;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.tooltipsModules.ClientUITooltipTextFormatModule;
import com.yuuki.projectx.utils.Console;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 * ClientConfigurationManager
 * Used to control all the configurations and settings used by the client.
 *
 * @author Borja
 * @date 29/06/2015
 * @package simulator.systems
 * @project com.yuuki.projectx.game.managers
 */
public class ClientConfigurationManager {
    /**
     * For singleton usage
     */
    private static ClientConfigurationManager INSTANCE;

    private ClientConfigurationManager() {
        //SINGLETON
    }

    public static ClientConfigurationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientConfigurationManager();
        }
        return INSTANCE;
    }

    /**
     * Sends the basic settings of the client.
     * @param gameSession Session where the settings will be sent
     * @throws JSONException
     */
    public UserSettingsCommand getUserSettingsCommand(GameSession gameSession) throws JSONException {
        JSONObject audio    = gameSession.getPlayer().getSettings().getJSONObject("audio");
        JSONObject quality  = gameSession.getPlayer().getSettings().getJSONObject("quality" );
        JSONObject quests   = gameSession.getPlayer().getSettings().getJSONObject("quests"  );
        JSONObject display  = gameSession.getPlayer().getSettings().getJSONObject("display" );
        JSONObject gameplay = gameSession.getPlayer().getSettings().getJSONObject("gameplay");
        JSONObject window   = gameSession.getPlayer().getSettings().getJSONObject("window"  );

        AudioSettingsModule audioSettingsModule = new AudioSettingsModule(
                audio.getBoolean("notSet"),
                audio.getInt("sound"),
                audio.getInt("music"),
                audio.getInt("voice"),
                audio.getBoolean("playCombatMusic")
        );

        QualitySettingsModule qualitySettingsModule = new QualitySettingsModule(
                quality.getBoolean("notSet"),
                quality.getInt("qualityAttack"),
                quality.getInt("qualityBackground"),
                quality.getInt("qualityPresetting"),
                quality.getBoolean("qualityCustomized"),
                quality.getInt("qualityPoizone"),
                quality.getInt("qualityShip"),
                quality.getInt("qualityEngine"),
                quality.getInt("qualityExplosion"),
                quality.getInt("qualityCollectable"),
                quality.getInt("qualityEffect")
        );

        QuestsSettingsModule questsSettingsModule = new QuestsSettingsModule(
                quests.getBoolean("questsAvailableFilter"),
                quests.getBoolean("questsUnavailableFilter"),
                quests.getBoolean("questsCompletedFilter"),
                quests.getBoolean("var_1151"),
                quests.getBoolean("var_2239"),
                quests.getBoolean("questsLevelOrderDescending")
        );

        DisplaySettingsModule displaySettingsModule = new DisplaySettingsModule(
                display.getBoolean("notSet"),
                display.getBoolean("displayPlayerNames"),
                display.getBoolean("displayResources"),
                display.getBoolean("displayBonusBoxes"),
                display.getBoolean("displayHitpointBubbles"),
                display.getBoolean("displayChat"),
                display.getBoolean("displayDrones"),
                display.getBoolean("displayFreeCargoBoxes"),
                display.getBoolean("displayNotFreeCargoBoxes"),
                display.getBoolean("showNotOwnedItems"),
                display.getBoolean("displayWindowsBackground"),
                display.getBoolean("displayNotifications"),
                display.getBoolean("preloadUserShips"),
                display.getBoolean("dragWindowsAlways"),
                display.getBoolean("hoverShips"),
                display.getBoolean("showPremiumQuickslotBar"),
                display.getBoolean("allowAutoQuality")
        );

        GameplaySettingsModule gameplaySettingsModule = new GameplaySettingsModule(
                gameplay.getBoolean("notSet"),
                gameplay.getBoolean("autoBoost"),
                gameplay.getBoolean("autoRefinement"),
                gameplay.getBoolean("quickSlotStopAttack"),
                gameplay.getBoolean("doubleclickAttackEnabled"),
                gameplay.getBoolean("autochangeAmmo"),
                gameplay.getBoolean("autoStartEnabled"),
                gameplay.getBoolean("autoBuyBootyKeys"),
                gameplay.getBoolean("battlerayNotifications")
        );

        WindowSettingsModule windowSettingsModule = new WindowSettingsModule(
                window.getInt("mScale"),
                window.getString("mBarState"),
                window.getBoolean("hideAllWindows")
        );

        return new UserSettingsCommand(
                audioSettingsModule,
                qualitySettingsModule,
                questsSettingsModule,
                displaySettingsModule,
                gameplaySettingsModule,
                windowSettingsModule
        );
    }

    /**
     * Sends the user key bindings
     * @param gameSession
     * @throws JSONException
     */
    public UserKeyBindingsUpdateCommand getUserKeyBindings(GameSession gameSession) throws JSONException {
        JSONArray                            keyBindingsJSON = gameSession.getPlayer().getKeyBindings();
        Vector<UserKeyBindingsModuleCommand> keyBindings     = new Vector<>();

        for(int keyBinding = 0; keyBinding < keyBindingsJSON.length(); keyBinding++) {
            JSONObject keyEntry = keyBindingsJSON.getJSONObject(keyBinding);

            JSONArray       keyCodesJSON = keyEntry.getJSONArray("keyCodes");
            Vector<Integer> keyCodes     = new Vector<>();
            //Foreach keycode, adds it to the vector
            for(int keyCode = 0; keyCode < keyCodesJSON.length(); keyCode++) {
                keyCodes.add(keyCodesJSON.getInt(keyCode));
            }

            keyBindings.add(new UserKeyBindingsModuleCommand((short)keyEntry.getInt("actionType"), keyCodes, keyEntry.getInt("parameter"), (short)keyEntry.getInt("charCode")));
        }

        return new UserKeyBindingsUpdateCommand(keyBindings, false);
    }

    public void changeUserKeyBindings(Player player, UserKeyBindingsUpdate userKeyBindingsUpdate) throws JSONException {
        /**
         * Since i can't remove items from the userKeyBindings array i'll
         * create a temp one and overwrite the main array later
         */
        JSONArray tempArray = new JSONArray();

        for(UserKeyBindingsModule userKeyBindingsModule : userKeyBindingsUpdate.changedKeyBindings) {
            JSONObject keyBinding = new JSONObject();

            keyBinding.put("parameter", userKeyBindingsModule.parameter  );
            keyBinding.put("actionType", userKeyBindingsModule.actionType);
            keyBinding.put("charCode", userKeyBindingsModule.charCode    );

            JSONArray keyCodes = new JSONArray();
            for(int keyCode : userKeyBindingsModule.keyCodes) {
                keyCodes.put(keyCode);
            }

            keyBinding.put("keyCodes", keyCodes);
            tempArray.put(keyBinding);
        }

        player.setKeyBindings(tempArray);
    }

    /**
     * Sends the UI menu bars
     * @param gameSession
     */
    public ClientUIMenuBarsCommand getMenuBarsCommand(GameSession gameSession) {
        Vector<ClientUIMenuBarModule> menuBarModules = new Vector<>();

        /****************
         * TOP LEFT BAR *
         ****************/

        /*
         * key => itemID
         * value => tooltipID
         */
        Map<String, String> topLeftItems = new LinkedHashMap<>();

//        if(gameSession.getPlayer().isAdmin()) {
//            topLeftItems.put("cli", "title_cli");
//        }
        topLeftItems.put("user", "title_user");
        topLeftItems.put("ship", "title_ship");
//        topLeftItems.put("ship_warp", "ttip_shipWarp_btn");
//        topLeftItems.put("chat", "title_chat");
//        topLeftItems.put("group", "title_group");
        topLeftItems.put("minimap", "title_map");
        topLeftItems.put("spacemap", "title_spacemap");
//        topLeftItems.put("quests", "title_quests");
//        topLeftItems.put("refinement", "title_refinement");
        topLeftItems.put("log", "title_log");
//        topLeftItems.put("pet", "title_pet");
//        topLeftItems.put("contacts", "title_contacts");
//        topLeftItems.put("booster", "title_booster");

        //Event items
//        topLeftItems.put("spaceball", "title_spaceball");
//        topLeftItems.put("word_puzzle", "title_wordpuzzle");
//        topLeftItems.put("invasion", "title_invasion");
//        topLeftItems.put("ctb", "title_ctb");
//        topLeftItems.put("tdm", "title_tdm");
//        topLeftItems.put("ranked_hunt", "title_ranked_hunt");
//        topLeftItems.put("highscoregate", "title_highscoregate");
//        topLeftItems.put("scoreevent", "title_scoreevent");
//        topLeftItems.put("infiltration", "title_ifg");
//        topLeftItems.put("sectorcontrol", "title_sectorcontrol_ingame_status");
//        topLeftItems.put("jackpot_status_ui", "title_jackpot_status_ui");
//        topLeftItems.put("curcubitor", "httip_countdownHalloweenNPCs");
//        topLeftItems.put("influence", "httip_influence");
//        topLeftItems.put("domination", "title_domination");
//        topLeftItems.put("traininggrounds", "title_traininggrounds");
//        topLeftItems.put("company_hq", "ttip_company_hq");

        Vector<ClientUIMenuBarItemModule> topLeftMenuItems = new Vector<>();
        for(Map.Entry<String, String> item : topLeftItems.entrySet()) {
            String itemID = item.getKey();
            String tooltipID = item.getValue();

            //Tooltip format
            ClientUITooltipTextFormatModule tooltipTextFormatModule = new ClientUITooltipTextFormatModule(ClientUITooltipTextFormatModule.LOCALIZED);
            //used to replace the tooltip (?)
            Vector<ClientUITextReplacementModule> textReplacementModules = new Vector<>();

            Vector<ClientUITooltipModule> tooltips = new Vector<>();
            //Replaces the text on the tooltip with the one of the id given, 2nd param seems to be the color of the text
            tooltips.add(new ClientUITooltipModule(tooltipTextFormatModule, ClientUITooltipModule.STANDARD, tooltipID, textReplacementModules));

            //Assemble the tooltip command
            ClientUITooltipsCommand clientUITooltipsCommand = new ClientUITooltipsCommand(tooltips);

            //Creates the item and add it into the items vector
            ClientUIMenuBarItemModule menuBarItemModule = new ClientUIMenuBarItemModule(true, clientUITooltipsCommand, itemID);
            topLeftMenuItems.add(menuBarItemModule);
        }
        //Creates the bar module with all the items inside the vector ^^
        ClientUIMenuBarModule topLeftBar = new ClientUIMenuBarModule("0,0", ClientUIMenuBarModule.GAME_FEATURE_BAR, topLeftMenuItems, "0");
        menuBarModules.add(topLeftBar);


        /*****************
         * TOP RIGHT BAR *
         *****************/
        //The same as the leftBar

        Map<String, String> topRightItems = new LinkedHashMap<>();

        topRightItems.put("fullscreen", "ttip_fullscreen_btn");
        topRightItems.put("settings", "title_settings");
        topRightItems.put("help", "title_help");
        topRightItems.put("logout", "title_logout");

        Vector<ClientUIMenuBarItemModule> topRightMenuItems = new Vector<>();
        for(Map.Entry<String, String> item : topRightItems.entrySet()) {
            String itemID = item.getKey();
            String tooltipID = item.getValue();

            //Tooltip format
            ClientUITooltipTextFormatModule tooltipTextFormatModule = new ClientUITooltipTextFormatModule(ClientUITooltipTextFormatModule.LOCALIZED);
            //used to replace the tooltip (?)
            Vector<ClientUITextReplacementModule> textReplacementModules = new Vector<>();

            Vector<ClientUITooltipModule> tooltips = new Vector<>();
            //Replaces the text on the tooltip with the one of the id given, 2nd param seems to be the color of the text
            tooltips.add(new ClientUITooltipModule(tooltipTextFormatModule, ClientUITooltipModule.STANDARD, tooltipID, textReplacementModules));

            //Assemble the tooltip command
            ClientUITooltipsCommand clientUITooltipsCommand = new ClientUITooltipsCommand(tooltips);

            //Creates the item and add it into the items vector
            ClientUIMenuBarItemModule menuBarItemModule = new ClientUIMenuBarItemModule(true, clientUITooltipsCommand, itemID);
            topRightMenuItems.add(menuBarItemModule);
        }

        ClientUIMenuBarModule topRightBar = new ClientUIMenuBarModule("100,0", ClientUIMenuBarModule.GENERIC_FEATURE_BAR, topRightMenuItems, "0");
        menuBarModules.add(topRightBar);

        //return all the bars
        return new ClientUIMenuBarsCommand(menuBarModules);
    }

    /**
     * Changes the audio settings for the user given
     * @param playerSettings
     * @param audioSettings
     * @throws JSONException
     */
    public void changeAudioSettings(JSONObject playerSettings, AudioSettingsRequest audioSettings) throws JSONException {
        JSONObject audio = playerSettings.getJSONObject("audio");
        audio.put("notSet", false                                 );
        audio.put("sound", audioSettings.sound                    );
        audio.put("music", audioSettings.music                    );
        audio.put("voice", audioSettings.voice                    );
        audio.put("playCombatMusic", audioSettings.playCombatMusic);
    }

    /**
     * Changes the display settings for the user given
     * @param playerSettings
     * @param displaySettingsRequest
     * @throws JSONException
     */
    public void changeDisplaySettings(JSONObject playerSettings, DisplaySettingsRequest displaySettingsRequest) throws JSONException {
        JSONObject display = playerSettings.getJSONObject("display");
        display.put("notSet", false                                                            );
        display.put("displayPlayerNames", displaySettingsRequest.displayPlayerNames            );
        display.put("displayResources", displaySettingsRequest.displayResources                );
        display.put("showPremiumQuickslotBar", displaySettingsRequest.showPremiumQuickslotBar  );
        display.put("allowAutoQuality", displaySettingsRequest.allowAutoQuality                );
        display.put("preloadUserShips", displaySettingsRequest.preloadUserShips                );
        display.put("displayHitpointBubbles", displaySettingsRequest.displayHitpointBubbles    );
        display.put("showNotOwnedItems", displaySettingsRequest.showNotOwnedItems              );
        display.put("displayChat", displaySettingsRequest.displayChat                          );
        display.put("displayWindowsBackground", displaySettingsRequest.displayWindowsBackground);
        display.put("displayNotFreeCargoBoxes", displaySettingsRequest.displayNotFreeCargoBoxes);
        display.put("dragWindowsAlways", displaySettingsRequest.dragWindowsAlways              );
        display.put("displayNotifications", displaySettingsRequest.displayNotifications        );
        display.put("hoverShips", displaySettingsRequest.hoverShips                            );
        display.put("displayDrones", displaySettingsRequest.displayDrones                      );
        display.put("displayBonusBoxes", displaySettingsRequest.displayBonusBoxes              );
        display.put("displayFreeCargoBoxes", displaySettingsRequest.displayFreeCargoBoxes      );
    }

    /**
     * Changes the quality settings for the user given
     * @param playerSettings
     * @param qualitySettingsRequest
     * @throws JSONException
     */
    public void changeQualitySettings(JSONObject playerSettings, QualitySettingsRequest qualitySettingsRequest) throws JSONException {
        JSONObject quality = playerSettings.getJSONObject("quality");
        quality.put("notSet", false                                                );
        quality.put("qualityAttack", qualitySettingsRequest.qualityAttack          );
        quality.put("qualityBackground", qualitySettingsRequest.qualityBackground  );
        quality.put("qualityPresetting", qualitySettingsRequest.qualityPresetting  );
        quality.put("qualityCustomized", qualitySettingsRequest.qualityCustomized  );
        quality.put("qualityPoizone", qualitySettingsRequest.qualityPoizone        );
        quality.put("qualityShip", qualitySettingsRequest.qualityShip              );
        quality.put("qualityEngine", qualitySettingsRequest.qualityEngine          );
        quality.put("qualityExplosion", qualitySettingsRequest.qualityExplosion    );
        quality.put("qualityCollectable", qualitySettingsRequest.qualityCollectable);
        quality.put("qualityEffect", qualitySettingsRequest.qualityEffect          );
    }

    /**
     * Changes the gameplay settings for the user given
     * @param playerSettings
     * @param gameplaySettingsRequest
     * @throws JSONException
     */
    public void changeGameplaySettings(JSONObject playerSettings, GameplaySettingsRequest gameplaySettingsRequest) throws JSONException {
        JSONObject gameplay = playerSettings.getJSONObject("gameplay");
        gameplay.put("notSet", false                                                             );
        gameplay.put("autoRefinement", gameplaySettingsRequest.autoRefinement                    );
        gameplay.put("quickSlotStopAttack", gameplaySettingsRequest.quickSlotStopAttack          );
        gameplay.put("autoBoost", gameplaySettingsRequest.autoBoost                              );
        gameplay.put("autoBuyBootyKeys", gameplaySettingsRequest.autoBuyBootyKeys                );
        gameplay.put("doubleclickAttackEnabled", gameplaySettingsRequest.doubleclickAttackEnabled);
        gameplay.put("autochangeAmmo", gameplaySettingsRequest.autochangeAmmo                    );
        gameplay.put("autoStartEnabled", gameplaySettingsRequest.autoStartEnabled                );
        gameplay.put("battlerayNotifications", gameplaySettingsRequest.battlerayNotifications    );
    }

    /**
     * TODO Work in progress
     * @param gameSession
     */
    public ClientUISlotBarCommand getSlotBar(GameSession gameSession) {
        //QuickSlotBars
        Vector<ClientUISlotbarModule> slotbars = new Vector<>();

        /**
         * TEST
         */
        Vector<ClientUISlotbarItemModule> slotbar = new Vector<>();
        //slotbar.add(new ClientUISlotbarItemModule("equipment_extra_cpu_cl04k-xl", 1));

        slotbars.add(new ClientUISlotbarModule("50,85|0,40", ClientUISlotbarModule.STANDARD_SLOT_BAR, "0", slotbar));

        //CategoriesBar
        Vector<ClientUISlotbarCategoryModule> categories = new Vector<>();
        try {
            //1º Get laser categories
            categories.add(generateAmmunitionCategory("lasers", gameSession));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * TEST CATEGORY
         */
        Vector<ClientUISlotbarCategoryItemModule> items = new Vector<>();

        items.add(new ClientUISlotbarCategoryItemModule(
                ClientUISlotbarCategoryItemModule.DOTS, //counterType
                new CooldownTypeModule(CooldownTypeModule.NONE), //cooldownModule
                new ClientUISlotbarCategoryItemStatusModule(
                        false, //blocked
                        false, //buyable
                        ClientUISlotbarCategoryItemStatusModule.DEFAULT, //counter style
                        false, //selected
                        new ClientUITooltipsCommand(new Vector<ClientUITooltipModule>()),
                        "equipment_extra_cpu_cl04k-xl",
                        true, //available
                        new ClientUITooltipsCommand(new Vector<ClientUITooltipModule>()),
                        3, //counter value
                        true, //visible
                        "ability_admin-ultimate-cloaking", //iconLootID
                        5, //maxCounter
                        true //activable
                ),
                50, //var
                new ClientUISlotBarCategoryItemTimerModule(10, new ClientUISlotBarCategoryItemTimerStateModule(ClientUISlotBarCategoryItemTimerStateModule.short_2168), 20, "UCB-100", true),
                ClientUISlotbarCategoryItemModule.SELECTION
                ));


        categories.add(new ClientUISlotbarCategoryModule("rockets", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("rocket_launchers", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("special_items", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("mines", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("cpus", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("buy_now", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("tech_items", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("ship_abilities", new Vector<ClientUISlotbarCategoryItemModule>()));
        categories.add(new ClientUISlotbarCategoryModule("drone_formations", items));

        return new ClientUISlotBarCommand(categories, slotbars, "50,85");
    }

    private ClientUISlotbarCategoryModule generateAmmunitionCategory(String category, GameSession gameSession) throws JSONException {
        Vector<ClientUISlotbarCategoryItemModule> categoryItemModules = new Vector<>();

        //Will vary between laser, rockets, rocket_launchers, mines, and who knows
        JSONArray ammunition = null;

        switch(category) {
            case "lasers":
                ammunition = gameSession.getPlayer().getPlayerAmmunition().getLaserAmmunition();
                break;

            case "rockets":

                break;

            default:
                Console.error("Something went wrong generating the category " + category);
                break;
        }

        if(ammunition != null) {
            for (int i = 0; i < ammunition.length(); i++) {
                //used to get the elements in the order written
                JSONObject ammoObject = ammunition.getJSONObject(i);

                //Item values
                String ammoType     = ammoObject.keys().next().toString();
                long   amount       = ammoObject.getLong(ammoType);

                //Icon style values
                short  counterStyle;
                if      (amount < 1000) counterStyle = ClientUISlotbarCategoryItemStatusModule.YELLOW;
                else if (amount <  500) counterStyle = ClientUISlotbarCategoryItemStatusModule.RED;
                else                    counterStyle = ClientUISlotbarCategoryItemStatusModule.BLUE;

                categoryItemModules.add(generateSlotbarItemModule(counterStyle, ammoType, amount));
            }

            return new ClientUISlotbarCategoryModule(category, categoryItemModules);
        }

        return null;
    }

    /**
     * Generates the SlotbarItem needed
     * TODO Edit this with new categories
     * @return ClientUISlotbarItemModule
     */
    private ClientUISlotbarCategoryItemModule generateSlotbarItemModule(short counterStyle, String ammoType, long amount) {
        return new ClientUISlotbarCategoryItemModule(
                ClientUISlotbarCategoryItemModule.BAR, //counterType
                new CooldownTypeModule(CooldownTypeModule.NONE), //cooldownModule
                new ClientUISlotbarCategoryItemStatusModule(
                        false, //blocked
                        false, //buyable
                        counterStyle, //counter style
                        false, //selected
                        new ClientUITooltipsCommand(new Vector<ClientUITooltipModule>()),
                        ammoType,
                        true, //available
                        new ClientUITooltipsCommand(new Vector<ClientUITooltipModule>()),
                        amount, //counter value
                        true, //visible
                        ammoType, //iconLootID
                        1000, //maxCounter
                        true //activable
                ),
                50, //var
                new ClientUISlotBarCategoryItemTimerModule(10, new ClientUISlotBarCategoryItemTimerStateModule(ClientUISlotBarCategoryItemTimerStateModule.short_2168), 20, "UCB-100", true),
                ClientUISlotbarCategoryItemModule.ONE_SHOT
        );
    }

}
