# DSBot
Это плагин для создания и подключения дискорд бота к вашему серверу

This is a plugin for creating and connecting a discord bot to your server

## Instruction 
**RU**

1) переходим по этой [ссылке](https://discord.com/developers/applications) и нажимаем New Aplication
2) Даём имя своему боту и у вас открывается сайт с настройкой бота где вы можете изменить ему имя и описание
3) Переходим во вкладку Bot в правой боковой панели и нажимаем добавить бота
4) Далее копируем Token и вставляем его в конфиг плагина
5) Внизу нужно выбрать права администратора 
![тут](https://github.com/2sweetheart2/DSBot/blob/master/raw/master/main/Снимок.PNG)
6) Переходим в раздел OAuth2 -> OAuth2 URL Generator и выбираем данные настройки ![тут](https://github.com/2sweetheart2/DSBot/blob/master/raw/master/main/1.PNG)
7) чуть ниже у нас есть наша ссылка для добавления бота в дискорд, но это ещё не всё
8) в дискорде, выбираем нужный нам тектосвый канал -> пкм -> копировать ID и вставляем его в конфиг в chanel_id

**ЕСЛИ У ВАС НЕ ОТОБРАЖАЕТСЯ КОПИРОВАТЬ ID**:
Настройки Discord -> Разширенные -> Режим разработчика включить

**ГОТОВО**

**EN**

1) follow this [link](https://discord.com/developers/applications) and click New Aplication
2) We give a name to our bot and you open a website with a bot setup where you can change its name and description
3) Go to the Boot tab in the right sidebar and click add bot
4) Next, copy the Token and paste it into the plugin config
5) At the bottom you need to select administrator rights
![here](https://github.com/2sweetheart2/DSBot/blob/master/raw/master/main/Снимок.PNG)
6) Go to the section OAuth 2 -> OAuth2 URL Generator and select these settings![here](https://github.com/2sweetheart2/DSBot/blob/master/raw/master/main/1.PNG )
7) just below we have our link to add a bot to discord, but that's not all
8) in the discord, select the tectonic channel we need -> pcm -> copy ID and paste it into the config in chanel_id


**COMPLETE**

## Config

* lang - язык (ru/en)
* bot_token - токен вашего бота
* chanel_id - id нужного текстового канала / id of the desired text channel
* need_send_death_message - нужно ли отправлять сообщение о смерти (значение true/false)
* need_send_join_message - нужно ли отправлять сообщение о подключении (значение true/false)
* need_send_leave_message - нужно ли отправлять сообщение о отключении (значение true/false)
* need_send_from_chat_messge_into_minecraft - нужно ли отправлять сообщение о из чата дс в майкнрафт (значение true/false)
* need_send_from_minecraft_message_into_chat - нужно ли отправлять сообщение о из чата майнкрафт в дс (значение true/false)
