package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
// Импорт нового пакета для InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "FireCoinBot"; // Замените на имя вашего бота
    }

    @Override
    public String getBotToken() {
        return "7943748821:AAH7YdrfiEM8-sqesE8M-2PhK6rYusWjlXk"; // Замените на токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            // Приводим chatId к строке (метод setChatId теперь принимает String)
            String chatId = update.getMessage().getChatId().toString();

            SendMessage message = new SendMessage();
            message.setChatId(chatId);

            if (messageText.equals("/start")) {
                message.setText("Привет! Нажми на кнопку, чтобы начать зарабатывать монеты!");

                // Создаем инлайн-кнопку с помощью builder'а
                InlineKeyboardButton button = InlineKeyboardButton.builder()
                        .text("Заработать монеты")
                        .url("https://appfirecoin.netlify.app/") // Замените на URL вашего мини-приложения
                        .build();

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                // Добавляем кнопку в ряд и ряд в клавиатуру
                keyboardMarkup.setKeyboard(Collections.singletonList(Collections.singletonList(button)));

                message.setReplyMarkup(keyboardMarkup);
            } else {
                message.setText("Чтобы начать, напиши /start");
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                // Рекомендуется использовать систему логирования вместо printStackTrace()
                System.err.println("Error executing message: " + e.getMessage());
            }
        }
    }
}

