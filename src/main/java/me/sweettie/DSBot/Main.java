package me.sweettie.DSBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {

    public static Server server;
    public int players;
    public static String lang;

    public final List<Long> Basicaly_Gods = new ArrayList<>();

    {
        Basicaly_Gods.add(719280002079654069L);
        Basicaly_Gods.add(474074354997526538L);
        Basicaly_Gods.add(720905007905636354L);

    }

    public static String TOKEN;
    public static String CHAT_ID;
    public JDA bot;
    public static String path = "options.global_options.";


    @Override
    public void onEnable() {
        server = getServer();
        getConfig().options().copyDefaults(true);
        saveConfig();
        if (!checkCorrect()) {
            sendMessage(ChatColor.RED, "Incorrect token or channel id. Bot don't work");
            return;
        }
        lang = Objects.requireNonNull(getConfig().getString("lang")).toLowerCase();
        try {
            JDABuilder builder = JDABuilder.createDefault(TOKEN);
            if (getConfig().getBoolean(path + "need_send_from_chat_messge_into_minecraft"))
                builder.addEventListeners(new MessageAdapter(this));

            //.setActivity(Activity.of(Activity.ActivityType.WATCHING, "за NNcraft\nOnline: " + Bukkit.getOnlinePlayers().size()))
//                    .build();
            bot = builder.build();
            bot.awaitReady();
            sendMessage(ChatColor.GREEN, "Bot start work");
        } catch (Exception e) {
            sendMessage(ChatColor.RED, "Incorrect token or channel id. Bot don't work");
            return;
        }


//        players = Bukkit.getOnlinePlayers().size();

        Bukkit.getPluginManager().registerEvents(this, this);

    }

    private boolean checkCorrect() {
        try {
            TOKEN = getConfig().getString("options.system_options.bot_token", "null");
            CHAT_ID = getConfig().getString("options.system_options.chanel_id", "null");
            return !TOKEN.equals("null") && !CHAT_ID.equals("null");
        } catch (Exception e) {
            return false;
        }

    }

    public static void sendMessage(ChatColor color, String text) {
        server.getConsoleSender().sendMessage(ChatColor.GREEN + "[DSBOT] " + color + text);
    }


    @Override
    public void onDisable() {
        bot.shutdownNow();
        bot.shutdown();
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        if (!getConfig().getBoolean(path + "need_send_from_minecraft_message_into_chat")) return;
        Objects.requireNonNull(bot.getTextChannelById(CHAT_ID)).sendMessage(formated(getConfig().getString("messages." + lang + ".send_mess"), event.getPlayer().getName())+event.getMessage()).queue();
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        if (!getConfig().getBoolean(path + "need_send_join_message")) return;
        Objects.requireNonNull(bot.getTextChannelById(CHAT_ID)).sendMessage(">>> "+formated(getConfig().getString("messages." + lang + ".join"), event.getPlayer().getName())).queue();
//        bot.getPresence().setActivity(Activity.watching("за NNcraft\nOnline: " + players));
    }

    private String getName(String str) {
        return str.substring(17, str.length() - 1);
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        if (!getConfig().getBoolean(path + "need_send_leave_message")) return;
        //bot.getPresence().setActivity(Activity.watching("за NNcraft\nOnline: " + players));
        Objects.requireNonNull(bot.getTextChannelById(CHAT_ID)).sendMessage(">>> " +formated(getConfig().getString("messages." + lang + ".leave"), event.getPlayer().getName())).queue();
    }

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (!getConfig().getBoolean(path + "need_send_death_message")) return;
        Objects.requireNonNull(bot.getTextChannelById(CHAT_ID)).sendMessage(">>> " +formated(getConfig().getString("messages." + lang + ".death"), event.getDeathMessage())).queue();
    }

    public static String formated(String text, String... args) {
        for (String arg : args) {
            text = text.replaceFirst("\\{name\\}", arg);
        }
        return text;
    }

}
