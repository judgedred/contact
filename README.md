# contact

Простое веб приложение, которое позволяет импортировать в базу список контактов из CSV файла, а также просматривать список импортированных контактов.

Меню
- Страница меню
- Страница для отображения списка
- Страница импорта

Импорт производится из CSV файла, который содержит следующую информацию
- Name
- Surname
- Login
- E-mail
- Phone number

Не допускается возможность системы осуществлять одновременный импорт нескольких документов (от разных пользователей).
Импорт работает одновременно в двух режимах
- Создание
- Обновление

Режим отображения списка контактов поддерживает следующие опции
- Пейджинг (возможно "листать" постранично, если список превышает допустимое для отображения количество строк)
- Сортировку по каждой колонке 
