package me.sweettie.DSBot;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MessageAdapter extends ListenerAdapter {

    Main main;

    public MessageAdapter(Main main) {
        this.main = main;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (!event.getChannel().getId().equals(Main.CHAT_ID)) return;
        Message msg = event.getMessage();
        if (msg.getContentRaw().startsWith("!")) {
            commands(msg);
            return;
        }
        if (!msg.getContentRaw().contains("\uD83D\uDE00")) {
            Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.AQUA + "[DS]") + ChatColor.RESET + " " + msg.getAuthor().getName() + ": " + msg.getContentRaw());
            try {
                msg.addReaction("✅").queue();
            } catch (Exception ignored) {
            }
        } else {
            event.getChannel().sendMessage("> Пожалуйста, не используйте смайлики из дискорда. Они не используются в Minecraft!").queue();

        }

    }

    public void commands(Message msg) {
        String text = msg.getContentRaw();
        if (text.equalsIgnoreCase("!stat")) {
            long time = System.currentTimeMillis();
            StringJoiner text1 = new StringJoiner("\n");
            StringJoiner player = new StringJoiner("\n");
            List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(Player player1 : playerList){
                player.add(player1.getName());
            }
            text1.add("online ("+playerList.size()+"): \n" + player);
            msg.getChannel().sendMessage(">>> " + text1.add("delay: " + (System.currentTimeMillis() - time)+"мс")).queue();
            main.bot.getPresence().setActivity(Activity.watching("за NNcraft\nOnline: "+main.players));
            return;
        }
        Bukkit.getLogger().info(msg.getAuthor().getIdLong() + " "+msg.getAuthor().getId());
        if (text.startsWith("!/") && main.Basicaly_Gods.contains(msg.getAuthor().getIdLong())) {
            Bukkit.getScheduler().runTask(main, () -> {
                String command = text.toLowerCase().replace("!/", "");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                msg.addReaction("✅").queue();
            });
        } else {
            msg.addReaction("❌").queue();
        }
    }


}
