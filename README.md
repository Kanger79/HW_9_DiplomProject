<div align="center">
  
# Дипломный проект по профессии 
# "Тестировщик"
<br>

## Автоматизация тестирования комплексного сервиса,
## взаимодействующего с СУБД и API Банка
<br>

<div align="left">

## Подготовка к запуску проекта:
#### Для того, чтобы запустить проект на своем (локальном) ПК, необходимо скопировать (клонировать) рабочую папку с файлами проекта. <br>
**Способ - 1:** <br>
  1.1. Зайдите в репозиторий с проектом по [*ссылке*](https://github.com/Kanger79/HW_9_DiplomProject) <br>
  1.2. Нажмите на кнопку "**`<> Code`**". <br>
  1.3. В выпадающем окне нажмите "**Download ZIP**", после чего укажите место на дисковом пространстве своего ПК, куда будет скачан архив с файлами проекта. <br>

<details>
 <summary>скриншот</summary>
  
  ![4_ReadMe_01+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/99645f5e-8e50-4fa9-b786-f2302e9458db)

</details>
  
  1.4. Создайте папку для проекта и распакуйте в неё содержимое скачанного архива. <br>

  **Способ - 2 (должем быть установлен *Git*):**

  2.1. Зайдите в репозиторий с проектом по [*ссылке*](https://github.com/Kanger79/HW_9_DiplomProject) <br>
  2.2. Нажмите на кнопку "**`<> Code`**". <br>
  2.3. В выпадающем окне скопируйте ссылку проекта. <br>

  <details>
 <summary>скриншот</summary>
  
  ![4_ReadMe_02+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/26209aca-24ad-4b19-9c9b-28170f1653ab)


</details>
  
  2.4. Создайте папку для проекта, щелкните по ней правой кнопкой "мыши" и в выпадающем меню нажмите на **Open Git Bash here** <br>
  2.5. В терминальном окне **Git** введите команду `git clone`, а затем вставьте ссылку (скопированную в п.2.3.) и нажмите `Enter` <br>

  ---

  ## Требования

  #### Для работы проекта требуется предварительная установка следующих программ:
   * **JDK (Java Development Kit) 11** - Комплект разработчика приложений на языке Java, от компании Oracle Corporation 
   * **Intellij IDEA** - Среда разработки на Java и Kotlin. Помогает работать продуктивнее за счет интеллектуальной помощи в написании кода.
   * **Браузер**    - Программа для просмотра сайтов, HTML-документов (Google Chrome, Mozilla FireFox, Microsoft Edge и т.д.)
   * **Docker Desktop**  - Приложение, позволяющее локально собирать, выполнять и тестировать контейнеры.
   * **DBeaver** (или аналог) - Бесплатная программа для работы с СУБД.
   * **PostMan** - Программа для тестирования API. Позволяет создавать коллекции запросов к API и визуализировать их результат.

  ---
  
  ## Установка и запуск

  1. Запустите **Docker Desktop** <br>
  2. Запустите **Intellij IDEA** <br>
     2.1. В **Intellij IDEA**, на вкладке терминала, запустите **Docker** командой `docker-compose up --build` <br>
           (*в результате, в приложении ***Docker Desktop*** отразится проект с запущенными контейнерами*) <br>

      <details>
         <summary>скриншоты</summary>
         <br> 
        
     ![Run_Docker](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/e9da1926-cd79-4893-934c-21212054c544)

      Запуск **docker-compose**
     <br>

     ![Docker-Desktop_start_+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/70c49ca3-e6c9-4442-9096-844c44d2e151)


      **Docker Desktop** с запущенными контейнерами

      </details>

     2.2. Добавьте новую вкладку терминального окна и запустите в ней приложение "Путешествие дня" командой: <br>
       &ensp; &ensp; 2.2.1. `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar` - для подключения к базе данных mySQL, <br>

        <details>
         <summary>скриншот</summary>
         <br> 
        
     ![Start_Java-pril_mySQL_02](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/02e29068-2133-462e-9418-c404528cbd7e)

      Запуск java-приложения **Путешествие дня**
     <br>

      </details>

       &ensp; &ensp; 2.2.2. `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar` - для подключения к базе данных postgre <br>
  3. Откройте в браузере страницу **http://localhost:8080** <br>

     <details>
         <summary>скриншот</summary>
         <br> 
        
        ![Dashboard_00](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/884de0d2-5998-4bd3-ae41-18a55b23ff71)

        Страница приложения **Путешествие дня**
     
       <br>

       </details>


