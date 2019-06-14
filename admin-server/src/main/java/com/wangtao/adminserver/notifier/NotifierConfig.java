package com.wangtao.adminserver.notifier;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.TelegramNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wangtao
 * @date : 2018/10/17 17:11  星期三
 *  1、手机下载Telegram注册账号，登录之后给@BotFather发消息，/newbot新建一个bot，然后/mybots，选择一个bot，点击API Token.
 *  获取如下格式 794088865:AAGI_-l2vk8OL7fAar2USPHuJ-OrLs5DHQI
 *  2、获取chatId接口  参考：https://stackoverflow.com/questions/32423837/telegram-bot-how-to-get-a-group-chat-id
 * https://api.telegram.org/bot794088865:AAGI_-l2vk8OL7fAar2USPHuJ-OrLs5DHQI/getUpdates
 * 返回结果
 *{
 *     "ok": true,
 *     "result": [
 *         {
 *             "update_id": 781345320,
 *             "message": {
 *                 "message_id": 9,
 *                 "from": {
 *                     "id": 742284138,
 *                     "is_bot": false,
 *                     "first_name": "wang",
 *                     "username": "tao1108",
 *                     "language_code": "zh-hans"
 *                 },
 *                 "chat": {
 *                     "id": 742284138,
 *                     "first_name": "wang",
 *                     "username": "tao1108",
 *                     "type": "private"
 *                 },
 *                 "date": 1544079428,
 *                 "text": "/start",
 *                 "entities": [
 *                     {
 *                         "offset": 0,
 *                         "length": 6,
 *                         "type": "bot_command"
 *                     }
 *                 ]
 *             }
 *         }
 *     ]
 * }
 *  3、POSTMAN测试
 *  https://api.telegram.org/bot794088865:AAGI_-l2vk8OL7fAar2USPHuJ-OrLs5DHQI/sendmessage?chat_id=742284138&text=my msg demo&parse_mode=HTML&disable_notification=false
 *
 */

@Configuration
public class NotifierConfig {
    @Autowired
    InstanceRepository repository;

//    @Bean
//    public Notifier smsNotifier() {
//        return new CustomNotifier(repository);
//    }

//    @Bean
//    public Notifier telegramNotifier() {
//        TelegramNotifier telegramNotifier = new TelegramNotifier(repository);
//        telegramNotifier.setChatId("742284138");
//        telegramNotifier.setAuthToken("794088865:AAGI_-l2vk8OL7fAar2USPHuJ-OrLs5DHQI");
//        return telegramNotifier;
//    }
}
