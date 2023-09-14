package com.pavel.runner;

import com.pavel.bot.RepeatingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    private static final String BOT_TOKEN = "Insert your bot token here";

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new RepeatingBot(BOT_TOKEN));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
