# InstalledApps
## Требование:
Разработайте приложение, показывающее информацию об установленных приложениях.

Приложение:
1. Содержит экран со списком установленных приложений
2. По нажатии на элемент списка переходит на экран с подробной информацией об установленном приложении
3. Экран с подробной информацией должен содержать следующие данные:
    * название приложения
    * версия;
    * имя пакета приложения;
    * контрольная сумма apk-файла.
4. Так же на экране с информацией о приложении должна быть кнопка, открывающая приложение

Алгоритм контрольной суммы можете выбрать самостоятельно (SHA-1, MD5, CRC32 или иной)
Ограничений по стеку технологий нет. Приложение должно работать на устройствах с ОС Android 7.0 и выше.

## Стек
- Lang: Kotlin
- Async: Coroutines
- DI: Koin
- UI: xml

## PS
- Приложение без дизайна, темной/светлой темы (могу добавить)
- Скрины приложения и apk файл в папку output