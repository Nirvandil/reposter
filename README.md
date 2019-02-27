Для работы репостера необходимо следующее
1. Java версии 11 или выше. 
2. reposter.vk.groupId - идентификатор группы вконтакте, за обновлением стены которой будет вестись наблюдение. 
3. reposter.vk.key - ключ к long-polling API группы вконтакте. Сначала необходимо включить этот API в разделе управления группой https://vk.com/public$groupId?act=longpoll_api, версии 5.69. Затем перейти на вклаку "Ключи доступа" и создать новый ключ с правом "управление сообществом" (без него работать не будет) https://vk.com/public$groupId?act=tokens. Ключ - длинная численно-буквенная строка (около 80 символов). 
4. reposter.tg.token - токен бота Telegram. Выдаётся ботом @BotFather, после создания нового бота /newbot.
5. reposter.tg.name - имя бота, как оно было указано при создании бота. 
6. reposter.tg.channelId - идентификатор канала (группы), в которую бот будет отправлять сообщения.
 
Самый простой способ получить идентификатор канала (группы), если он неизвестен -  добавить бота в целевую группу и сделать его администратором, после чего открыть ссылку https://api.telegram.org/bot<token>/getUpdates - в списке обновлений будет chat: {"id" "..."}, см. https://stackoverflow.com/questions/32423837/telegram-bot-how-to-get-a-group-chat-id

Запуск бота
 
**java -jar ./path/to/reposter.jar --reposter.vk.groupId=... --reposter.vk.key=... --reposter.tg.token=... --reposter.tg.name=... --reposter.tg.channelId=...** 
