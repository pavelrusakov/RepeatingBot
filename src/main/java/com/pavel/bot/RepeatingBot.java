package com.pavel.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RepeatingBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = "Insert your bot name here";
    private static final String START = "/start";

    public RepeatingBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        switch (message){
            case START -> startMessage(chatId);
            default -> sendMessage(chatId, message);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public void sendMessage(Long chatId, String data){
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), data);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //NOP
        }
    }

    public void startMessage(Long chatId){
        String text = "Привет! Этот бот дублирует все сообщения, которые ему отправляются.";
        sendMessage(chatId, text);
    }
}
