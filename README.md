# **Дипломный проект по профессии «Инженер по тестированию»**

## Тестирование мобильного приложения «Мобильный хоспис»

[Ссылка на задание](https://github.com/netology-code/qamid-diplom)

## Документация

- [План тестирования](https://github.com/...)
- [Чек-лист](https://docs.google.com/spreadsheets/d/1ZrxnuwQsNJ7UPio2beaKdWFrFnJ_XSHHxjjaoTymZY8/edit#gid=0)
- [Тест-кейсы](https://docs.google.com/spreadsheets/d/1yYTiF3Gp0JFFbQVzOsPJvgORNSt2-9okjB_O4618Mac/edit#gid=0)
- [Отчет о тестировании]()

## Запуск приложения и тестов

1. Склонировать репозиторий `https://github.com/paulwhite9191/Diploma_QAMID-53` командой `git clone`
2. Открыть проект в Android Studio;
3. Запустить эмулятор или подключить устройство для тестирования. Язык на устройстве - Русский.
   Данные для входа в приложение: - логин: login2 - пароль: password2

4. Перейти в папку проекта `fmh_android_15_03_24`
5. Запустить тесты через терминал `./gradlew connectedAndroidTest`
6. Подождать пока пройдут все тесты и посмотреть результат.

## Формирование отчета AllureReport

1. Добавить allure.results.useTestStorage=true в allure.properties ресурсы androidTest;
2. Добавить в тестовое приложение зависимость androidTestUtil "androidx.test:orchestrator:1.4.2";
3. Выгрузить каталог `/sdcard/googletest/test_outputfiles/allure-results` с тестируемого устройства (через Device File Explorer);
4. Выполнить команду `allure serve`, находясь на уровень выше allure-results;
5. Подождать генерации отчета и посмотреть его в открывшемся браузере.