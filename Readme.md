# Дипломный проект по профессии «Инженер по тестированию»


## Процедура запуска авто-тестов

1. Клонируем репозиторий `https://github.com/paulwhite9191/Diploma_QAMID-53` командой `git clone`
2. Открываем как проект папку тестируемого приложения `fmh_android_15_03_24` в Android Studio
3. Запускаем эмулятор `Pixel 4 API 29`. На эмуляторе необходимо установить системным языком русский
4. Открываем вкладку `Terminal` и прописываем в командной строке:`./gradlew clean connectedAndroidTest`

## Процедура создания и просмотра отчетов AllureReport

1. Скачиваем Allure2 версии 2.29.0 `https://github.com/allure-framework/allure2/releases/download/2.29.0/allure-2.29.0.zip`
2. Распаковываем архив, прописываем путь переменной среды PATH в Windows к папке `bin` из распакованного архива, проверяем, что все установилось (`win+r` пишем `cmd` пишем `allure --version`)
3. Что бы сформировать отчет из логов прогона тестов, нужно скопировать в папку `allure-results` в папке проекта свежие логи с виртуального устройства через встроенный в Android Studio диспетчер файлов (вкладка Device Explorer, далее путь `/sdcard/googletest/test_outputfiles/allure-results`), после чего выполнить команду `allure serve`