# ArtistMobileApp
ArtistMobileApp is created for educational purpose.
About using components: Android Studio 1.1.0, jdk1.8.0_31, Gradle version 2.2.1, compile SDK version API 21: Android 5.0 (Lollipop).
## Description
The application parses json files to the object. For example it is Artist.java.
Then objects will showed in a listView. Images is cached by the library universal-image-loader-1.9.5.jar.
Link: [Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)
## Launching
At the first launching application need an internet connection to load images and cache them.
There is the search editView in the MainActivity. It supports searching objects in the listStore by name or/and genre.

# ArtistMobileApp
Мобильное приложение ArtistMobileApp создано в познавательных целях.
Среда разработки Android Studio 1.1.0, jdk1.8.0_31, Gradle version 2.2.1, compile SDK version API 21: Android 5.0 (Lollipop).
## Описание:
В качестве входных данных приложение использует массив музыкальных исполнителей в формате json. Приложение загружает содержимое json файла из своих ресурсов, файл artists.json, парсит его в объекты, с помощью библиотеки gson 2.6.2. Массив объектов хранится в класе ArtistDao в статической переменной artistStore.
Объекты отображаются на главное форме MainActivity в элементе listView, шаблон разметки my_list_item.xml, адаптер CustomListAdapter.
При нажатии на пункт в listView, происходит переход на вторую форму ArtistActivity, где отображаются детальные сведения о выбранном артисте,
шаблон my_list_item_big.xml, адаптер BigListAdapter.
При переходе назад отображается текущий listStore.
Для загрузки и кэширования изображений используется библиотека universal-image-loader-1.9.5.jar
Ссылка:
[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)
## Работа приложения:
При первом запуске на устройстве необходимо подключение к интернету, для скачивания изображений, которые сохраняются в кэш папку на мобильном устройстве. При последующем перезапуске, имеющиеся закэшированные изображения будут подгружаться.
На главное форме имеется возможность поиска. Введенный в editView текст отправляется в статический метод searchArtist у ArtistDao, который просматривает на частичное совпадение поля name и genres у каждого элемента в listStore. Есть возможность поиска по нескольким ключевым словам, с любым количеством разделителей ". ,". После поиска на главное форме отображается новый список. Если совпадений не найдено выводится пустой список. Если пуст поисковый запрос отображается всё содержимое listStore.

![main activity](https://github.com/Amironsoft/ArtistMobileApp/blob/master/screens/2.jpg){:width="100px"}
![search](https://github.com/Amironsoft/ArtistMobileApp/blob/master/screens/1.jpg){:width="100px"}
![oxxx](https://github.com/Amironsoft/ArtistMobileApp/blob/master/screens/5.jpg){:width="100px"}


