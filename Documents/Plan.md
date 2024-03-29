
<div align="center">
  
# ПЛАН
# автоматизации тестирования приложения
# «Путешествие дня»

<br>
<div align="left">

## I. Перечень сценариев заполнения и отправки формы:

### Таблица входных данных

<table>
<thead>
<tr>
<th>Поле <br></th>
<th>Тип валидных данных <br> </th>
<th>Количество <br> символов <br> (валидная длина) </th>
<th>Валидный диапазон <br></th>
<th>Примечание <br></th>  
</tr>
</thead>

<tbody>

<tr>
<td>Номер карты</td>
<td>Цифры</td>
<td><div align="center">16</td>
<td><div align="center"> 4444 4444 4444 4441 </td>
<td> Уникальный номер карты <br> ( ...4441 - карта одобрена) <br> ( ...4442 - карта отклонена)</td>
</tr>

<tr>
<td>Месяц</td>
<td>Цифры</td>
<td><div align="center">2</td>
<td><div align="center">01 - 12</td>
<td rowspan=2 align="center">Срок действия карты - 5 лет. <br> поэтому диапазон валидных дат будет от: <br><b>текущий месяц, текущий год</b> <br> до: <br><b> текущий месяц, текущий год + 5 </b> </td>
</tr>

<tr>
<td>Год</td>
<td>Цифры</td>
<td><div align="center">2 <br> (последние две цифры <br> календарного года, <br>в котором заканчивается <br>срок действия карты)</td>
<td><div align="center">от: <br>текущий год, <br> до:<br> текущий год + 5</td>
 
</tr>

<tr>
<td>Владелец</td>
<td>Буквы (латиница), <br> "пробел", <br> "дефис"</td>
<td><div align="center">2 - 32</td>
<td><div align="center">A - Z, <br> a - z, <br> - </td>
<td>Имя и фамилия владельца карты </td> 
</tr>

<tr>
<td>CVC/CVV</td>
<td>Цифры</td>
<td><div align="center">3</td>
<td><div align="center">001 - 999</td>
<td>Код проверки подлинности карты <br> (трехзначное, положительное число, больше "нуля")  </td> 
</tr>

</tbody>
</table>

---



### I(a) - Сценарии покупки через сервис "Купить"

### ***Предусловия к сценариям покупки через сервис "Купить"***

:white_medium_square: Откройте в браузере страницу **http://localhost:8080/** <br>
:white_medium_square: На странице приложения нажмите кнопку «**Купить**» <br>

<details>
 <summary>скриншот</summary>
  
![Dashboard_01_pay_01](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/835217d6-438a-469b-aaff-ab61453491e3)

Главная страница

</details>

### 1a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить» <br>

1a.1. В открывшейся форме заполните поля следующими данными: <br>
 :white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
 :white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
 :white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Vasiliy Petrov** <br>
 :white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **001** <br>
 
1a.2. Нажмите кнопку «**Продолжить**»

<details>
 <summary>скриншот</summary>
 
![Dashboard_03_pay-form+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/94a47149-4c05-4f05-a37a-5598fa65a4a8)

Форма для заполнения
</details>


:heavy_check_mark: ***Ожидаемый результат: форма принимает введенные данные, отправляет их и выдает сообщение «Успешно! Операция одобрена банком». В таблицах базы появились соответствующие записи о состоянии карты и о платеже***.

---

### 2a.  Карта отклонена (статус DECLINED), покупка через сервис «Купить»  <br>
  
2a.1.	В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: в поле `Номер карты` введите номер карты со статусом "**Отклонена**" (**DECLINED**): **4444 4444 4444 4442**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Maria Ivanova**<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **111** <br>

2a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: форма принимает введенные данные, отправляет их и выдает сообщение «Ошибка! Банк отказал в завершении операции». В таблицах базы появились соответствующие записи о состоянии карты.***

---

### 3a. Покупка через сервис «Купить», не заполнен номер карты  <br>

3a.1. В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: поле `Номер карты` оставьте пустым <br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Ivan Vasilev** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **333**<br>

3a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 4a. Карта одобрена (статус APPROVED), покупка через сервис «Купить», не заполнен месяц  <br>

4a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: поле `Месяц` оставьте пустым <br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Sergey Marinin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **441**<br>

4a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 5a. Карта одобрена (статус APPROVED), покупка через сервис «Купить», не заполнен год  <br>

5a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **11**<br>
:black_medium_square: поле `Год` оставьте пустым<br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Natalia Petrova** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **554** <br>

5a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 6a. Карта одобрена (статус APPROVED), покупка через сервис «Купить», не заполнен владелец <br>

6a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: поле `Владелец` оставьте пустым <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **654** <br>

6a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Поле обязательно для заполнения»***.

---

### 7a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», не заполнен код CVC/CVV <br>

7a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Ilya Muromec** <br>
:black_medium_square: поле `CVC/CVV` оставьте пустым  <br>

7a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Поле обязательно для заполнения»***.

---

### 8a.	Покупка через сервис «Купить», некорректный номер карты  <br>

8a.1. В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: в поле `Номер карты` введите случайное число из 15 цифр: **3423 3422 9876 542** <br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Mihail Kruglov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **823**<br>

8a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Номер карты" сообщением «Неверный формат»***.

---

### 9a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», срок карты истек  <br>

9a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: в поле `Месяц` введите пордковый номер прошлого (относительно текущего) месяца  <br>
:white_medium_square: в поле `Год` введите последние две цифры текущего года <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Larisa Ivanova** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **908**  <br>

9a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Месяц» сообщением «Неверно указан срок действия карты»***.

---

### 10a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», невалидный месяц  <br>

10a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: в поле `Месяц` введите случайное, двухзначное число, больше 12: **14**<br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Mihail Petrov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **999** <br>

10a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Месяц» сообщением «Неверно указан срок действия карты»***.

---

### 11a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», некорректный месяц <br>

11a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: в поле `Месяц` введите одну случайную цифру: **4** <br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Dmitriy Shishkin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **978**<br>

11a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Месяц» сообщением «Неверный формат»***.

---

### 12a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», некорректный год <br>

12a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **03** <br>
:black_medium_square: в поле `Год` введите одну случайную цифру: **5** <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Aleksandr Shishkin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **976**<br>

12a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Год» сообщением «Неверный формат»***.

---

### 13a.	Карта одобрена (статус APPROVED), покупка через сервис «Купить», превышен срок карты  <br>

13a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **01** <br>
:black_medium_square: в поле `Год` введите последние две цифры числа, большего чем "текущий год + 5" <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Dmitriy Shishkin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **998**<br>

13a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Год» сообщением «Неверно указан срок действия карты»***.

---

### 14a.	Покупка через сервис «Купить», некорректен Владелец  <br>

14a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите имя на латинице, с добавлением спецсимволов и цифр: **Vovan$100**<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **002** <br>

14a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 15a.	Покупка через сервис «Купить», короткое имя Владельца  <br>

15a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите один валидный символ (букву латиницы): **V**<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **900** <br>

15a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 16a.	Покупка через сервис «Купить», длинное имя Владельца  <br>

16a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите валидными символами имя и фамилию, общей длиной более 32 символов<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **009** <br>

16a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 17a.	Покупка через сервис «Купить», некорректный код CVC/CVV - нули  <br>

17a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Petr Sergeev**<br>
:black_medium_square: в поле `CVC/CVV` введите трехзначное число, вне валидного диапазона: **000** <br>

17a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "CVC/CVV" сообщением «Неверный формат»***.

---

### 18a.	Покупка через сервис «Купить», некорректный код CVC/CVV - 1 цифра  <br>

18a.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Petr Sergeev**<br>
:black_medium_square: в поле `CVC/CVV` введите одну случайную цифру: **3** <br>

18a.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "CVC/CVV" сообщением «Неверный формат»***.

---

<br>

### I(b) - Сценарии покупки через сервис "Купить в кредит"

### ***Предусловия к сценариям покупки через сервис "Купить в кредит"***

:white_medium_square: Откройте в браузере страницу **http://localhost:8080/** <br>
:white_medium_square: На странице приложения нажмите кнопку «**Купить в кредит**» <br>

<details>
 <summary>скриншот</summary>
  
![Dashboard_02_credit+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/234313ca-c74f-414c-bc5b-f1959b27ffbd)

Главная страница

</details>

### 1b. Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит» <br>

1b.1. В открывшейся форме заполните поля следующими данными: <br>
 :white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
 :white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
 :white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Olesya Ivanova** <br>
 :white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **555** <br>
  
1b.2. Нажмите кнопку «**Продолжить**»


<details>
 <summary>скриншот</summary>
 
![Dashboard_04_credit-form+](https://github.com/Kanger79/HW_9_DiplomProject/assets/127352228/dea8f72d-71e7-4bf3-b775-24607a69400a)

Форма для заполнения
</details>


:heavy_check_mark: ***Ожидаемый результат: форма принимает введенные данные, отправляет их и выдает сообщение «Успешно! Операция одобрена банком». Операция одобрена банком». В таблицах базы появились соответствующие записи о состоянии карты и о платеже***.

---
  
### 2b. Карта отклонена (статус DECLINED), покупка через сервис «Купить в кредит» <br>

2b.1.	В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: в поле `Номер карты` введите номер карты со статусом "**Отклонена**" (**DECLINED**): **4444 4444 4444 4442**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Maxim Petrov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **600** <br>

2b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: форма принимает введенные данные, отправляет их и выдает сообщение «Ошибка! Банк отказал в завершении операции». В таблицах базы появились соответствующие записи о состоянии карты***.

---

### 3b.	Покупка через сервис «Купить в кредит», не заполнен номер карты  <br>

3b.1. В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: поле `Номер карты` оставьте пустым<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Anatoliy Mihaylov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **700**  <br>

3b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 4b. Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», не заполнен месяц  <br>

4b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: поле `Месяц` оставьте пустым<br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Yan Ivanov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **899** <br>

4b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 5b. Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», не заполнен год  <br>

5b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **12** <br>
:black_medium_square: поле `Год` оставьте пустым <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Svetlana Petrova** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **800**  <br>

5b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 6b. Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», не заполнен владелец <br>

6b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: поле `Владелец` оставьте пустым <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **100** <br>

6b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Поле обязательно для заполнения»***.

---

### 7b.	Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», не заполнен код CVC/CVV <br>

7b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Ashot Petrosyan** <br>
:black_medium_square: поле `CVC/CVV` оставьте пустым<br>

7b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает не заполненное поле сообщением «Неверный формат»***.

---

### 8b.	Покупка через сервис «Купит в кредить», некорректный номер карты  <br>

8b.1. В открывшейся форме заполните поля следующими данными: <br>
:black_medium_square: в поле `Номер карты` введите случайное число из 15 цифр: **3211 4321 7895 657**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Artur Balyan** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **400** <br>

8b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Номер карты" сообщением «Неверный формат»***.

---

### 9b.	Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», срок карты истек  <br>

9b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **03** <br>
:black_medium_square: в поле `Год` введите последние две цифры прошлого (относительно текущего) года <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Arkadiy Parovozov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **600** <br>

9b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Год» сообщением «Истек срок действия карты»***.

---

### 10b.	Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», невалидный месяц  <br>

10b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: в поле `Месяц` введите невалидное значение для этого поля: **00** <br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Evgeniy Mihaylov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **777** <br>

10b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Месяц» сообщением «Неверно указан срок действия карты»***.

---

### 11b.	Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», некорректный месяц <br>

11b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:black_medium_square: в поле `Месяц` введите одну случайную цифру: **9** <br>
:white_medium_square: в поле `Год` введите число из валидного диапазона для этого поля (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Dmitriy Shishkin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **978**<br>

11b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Месяц» сообщением «Неверный формат»***.

---

### 12b.	Карта одобрена (статус APPROVED), покупка через сервис «Купить в кредит», некорректный год <br>

12b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **07** <br>
:black_medium_square: в поле `Год` введите одну случайную цифру: **4** <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Aleksandr Serov** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **176**<br>

12b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле «Год» сообщением «Неверный формат»***.

---



### 13b.	Покупка через сервис «Купить в кредит», превышен срок действия карты <br>

13b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` введите валидный номер месяца: **04** <br>
:black_medium_square: в поле `Год` введите число, большее чем "текущий год + 5"<br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Alexandr Pushkin** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **663** <br>

13b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 14b.	Покупка через сервис «Купить в кредит», некорректен Владелец  <br>

14b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите имя и фамилию на кириллице с добавлением спецсимволов: **Сергей+Петров(%)** <br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **011** <br>

14b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 15b.	Покупка через сервис «Купить в кредит», короткое имя Владельца  <br>

15b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите один валидный символ (букву латиницы): **T**<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **099** <br>

15b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 16b.	Покупка через сервис «Купить в кредит», длинное имя Владельца  <br>

16b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:black_medium_square: в поле `Владелец` введите валидными символами имя и фамилию, общей длиной более 32 символов<br>
:white_medium_square: в поле `CVC/CVV` введите случайное трехзначное число (в валидном диапазоне): **101** <br>

16b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "Владелец" сообщением «Неверный формат»***.

---

### 17b.	Покупка через сервис «Купить в кредит», некорректный код CVC/CVV - нули  <br>

17b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Maksim Solovyov** <br>
:black_medium_square: в поле `CVC/CVV` введите трехзначное число, вне валидного диапазона: **000** <br>

17b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "CVC/CVV" сообщением «Неверный формат»***.

---

### 18b.	Покупка через сервис «Купить в кредит», некорректный код CVC/CVV - 2 цифры <br>

18b.1. В открывшейся форме заполните поля следующими данными: <br>
:white_medium_square: в поле `Номер карты` введите валидный номер карты со статусом "**Одобрена**" (**APPROVED**): **4444 4444 4444 4441**<br>
:white_medium_square: в поле `Месяц` и `Год` введите дату, соответствующую валидному диапазону (*см. таблицу входных данных*)  <br>
:white_medium_square: в поле `Владелец` введите случайное валидное имя и фамилию на латинице: **Mark Sviridov** <br>
:black_medium_square: в поле `CVC/CVV` введите случайное двухзначное число: **98** <br>

18b.2. Нажмите кнопку «**Продолжить**» <br>


:x: ***Ожидаемый результат: Форма не принимает данные и подсвечивает поле "CVC/CVV" сообщением «Неверный формат»***.

---

<br>

## II. Перечень используемых инструментов

* **Java** - на сегодняшний день один из самых популярных языков программирования
* **Intellij IDEA** - среда разработки на Java и Kotlin. Помогает работать продуктивнее за счет интеллектуальной помощи в написании кода.
* **Gradle** - система для автоматизации сборки приложений для языков Java, Kotlin, JavaScript
* **Фреймворки для Java**:
  - **JUnit** - Предназначен для автоматического тестирования программ
  - **Selenide** - Предназначен для написания удобных для чтения и обслуживания автоматизированных тестов на Java
* **Браузер** - Программа для просмотра сайтов, HTML-документов (Google Chrome, Mozilla FireFox, Microsoft Edge и т.д.)
* **GitHub** - Веб-сервис, основанный на системе Git. Позволяет публиковать и редактировать свой код, а так-же комментировать чужие наработки и т.п.
* **Docker** - проект с открытым исходным кодом для автоматизации развертывания приложений в виде переносимых автономных контейнеров, выполняемых в облаке или локальной среде
* **DBeaver** – бесплатная программа для работы с СУБД
* **Allure Report** – инструмент для создания отчетов о тестировании с открытым    исходным кодом

---

## III. Перечень и описание возможных рисков при автоматизации

* Частое изменение кода (функционала, параметров, навигации) приложения
* Отсутствие специальных тестовых меток-селекторов в коде страницы
* Изменение структуры базы
* Потеря связи (доступа в интернет)


## IV. Интервальная оценка с учетом рисков в часах

* Планирование автоматизации тестирования &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; (~ 6 ч)
* Непосредственно сама автоматизация &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; (~ 30 ч)
* Подготовка отчетных документов по итогам автоматизированного тестирования &ensp; (~ 6 ч)
* Подготовка отчетных документов по итогам автоматизации  &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &nbsp; (~ 6 ч)

                                                                                 ИТОГО: 48 часов

:warning: При возникновении рисков, указанных в разделе "**III. Перечень и описание возможных рисков при автоматизации**" количество часов может увеличиться до 58.
  
 ## V. Сроки выполнения работ
 * Планирование автоматизации тестирования    - 27 сентября - 02 октября <br>
 * Непосредственно сама автоматизация  - 03-12 октября <br>
 * Подготовка отчетных документов по итогам автоматизированного тестирования - 13-16 октября <br>
 * Подготовка отчетных документов по итогам автоматизации - 17-20 октября <br>

***


*Исполнитель*

*Кангер Дмитрий*

*QA-60*
