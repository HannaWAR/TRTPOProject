# Требования к проекту
---

# Содержание
1. [Введение](#intro) <br/>
1.1 [Назначение](#appointment) <br/>
1.2 [Бизнес-требования](#business_requirements) <br/>
> 1.2.1 [Исходные данные](#initial_data) <br/>
> 1.2.2 [Возможности бизнеса](#business_opportunities) <br/>
> 1.2.3 [Границы проекта](#project_boundary) <br/>
2. [Требования пользователя](#user_requirements) <br/>
2.1 [Программные интерфейсы](#software_interfaces) <br/>
2.2 [Интерфейс пользователя](#user_interface) <br/>
2.3 [Характеристики пользователей](#user_specifications) <br/>
2.3.1 [Классы пользователей](#user_classes) <br/>
2.3.2 [Аудитория приложения](#application_audience) <br/>
2.3.2.1 [Целевая аудитория](#target_audience) <br/>
2.4 [Предположения и зависимости](#assumptions_and_dependencies) <br/>
3. [Системные требования](#system_requirements) <br/>
3.1 [Функциональные требования](#functional_requirements) <br/>
3.1.1 [Основные функции](#main_functions) <br/>
3.1.1.1 [Ввод данных](#input_data) <br/>
3.1.1.2 [Отправка на сервер](#send_to_server) <br/>
3.1.1.3 [Получение информации](#get_info) <br/>
3.1.1.4 [Подтверждение окончания загрузки/выгрузки](#finish_work) <br/>
3.1.2 [Ограничения и исключения](#restrictions_and_exclusions) <br/>
3.2 [Нефункциональные требования](#non-functional_requirements) <br/>
3.2.1 [Атрибуты качества](#quality_attributes) <br/>
3.2.1.1 [Требования к удобству использования](#requirements_for_ease_of_use) <br/>
3.2.1.2 [Требования к безопасности](#security_requirements) <br/>
3.2.2 [Внешние интерфейсы](#external_interfaces) <br/>

<a name="intro"></a>

# 1 Введение

<a name="appointment"></a>

## 1.1 Назначение
В документе описаны требования пользователя и системные требования к клиент-серверному приложению "Портовая диспетчерская система". Данный документ предназначен для команд разработчиков, которые будут реализовывать и тестировать работу приложения.

<a name="business_requirements"></a>

## 1.2 Бизнес-требования

<a name="initial_data"></a>

### 1.2.1 Исходные данные
Морские грузоперевозки не теряют актуальность в настоящее время в связи с отсутствием сухопутных путей между странами. Существует множество компаний, которые специализируются на морских грузоперевозках, однако многие из них до сих пор не механизировали данный процесс, что затрудняет работу с прибывающими в порт грузовыми кораблями. Решением данной проблемы является приложение, которое регестрирует корабли в портах на загрузку и(или) выгрузку, заносит в очередь в соответствии с приоритетами, важностью и срочностью грузов, прошлой статистикой корабля(нарушения, если они имеются).

<a name="business_opportunities"></a>

### 1.2.2 Возможности бизнеса
Компании, занимающиеся грузоперевозками, нуждаются в "Портовой диспетчерской системе" с простым и удобным пользовательским интерфейсом. Данное приложение предоставит возможность быстро и легко решать проблемы распределения нагрузки между причалами и приоритетов между кораблями, позволит избегать возникновения очередей в портах. Правильно спроектированный интерфейс позволит увеличить количество компаний, использующих "Портовую диспетчерскую систему".

<a name="project_boundary"></a>

### 1.2.3 Границы проекта
"Портовая диспетчерская система" облегчит работу компаниям, которые занимаются морскими грузоперевозками, а также ускорит процессы выгрузки/загрузки кораблей.

<a name="user_requirements"></a>

# 2 Требования пользователя

<a name="software_interfaces"></a>

## 2.1 Программные интерфейсы
Приложение предназначено для работы в средах операционных систем Linux, Windows. Для написания приложения используется язык программирования Java. Среда разработки - IntelliJ IDEA. GUI клиента и сервера реализуются с помощью графической библиотеки Swing.

<a name="user_interface"></a>

## 2.2 Интерфейс пользователя
При входе в программу пользователю предоставляется возможность ввести количество причалов порта. При некорректном вводе следует повторить попытку.

![alt text](screenshots/Screenshot_20200922_090354.png "Выбор количества причалов")

![alt text](screenshots/errorInput.png "Повторите ввод количества причалов при некорректном вводе")

После корректного ввода появляется окно сервера с причалами и информацией об очереди. Сервер обновляется каждые 5 секунд. Можно видеть состояние очереди, а также её занятость.

![alt text](screenshots/server.png "Отображение серверного окна со всеми свободными причалами")

![alt text](screenshots/serverShip.png "Отображение серверного окна с прибывшими в порт кораблями")

При запуске клиентской части требуется ввести ввести имя корабля. Данное поле не является обязательным.

![alt text](screenshots/ShipName.png "Отображение поля для ввода имени")

После ввода имени появляется форма клиентской части. Там можно выбрать, куда плывёт корабль(загрузка/разгрузка), приоритетный ли он везёт груз или нет. Обязательным для заполнения являются поля грузов. Для добавления груза надо нажать на кнопку "Добавить груз". Поля с грузами требуется заполнить корректно: сначала название, потом масса в кг. После окончании регистрации корабля следует нажать на кнопку в левом нижнем углу "В порт", чтобы корабль отправился в порт. В любой момент можно получить информацию о корабле. Для этого неоюходимо нажать кнопку в правом нижнем углу "Получить информацию о корабле".

![alt text](screenshots/cargoCorrect.png "Корректное заполнение формы")

![alt text](screenshots/cargoError.png "Ошибка при заполнении полей с грузами")

![alt text](screenshots/errorSend.png "Ошибка при отправке корабля в порт")

![alt text](screenshots/shipInformation.png "Получить информацию о корабле")

<a name="user_specifications"></a>

## 2.3 Характеристики пользователей

<a name="user_classes"></a>

### 2.3.1 Классы пользователей

Приложение не разделяет пользователей на группы, предоставляя каждому одниковый функционал.

<a name="application_audience"></a>

### 2.3.2 Аудитория приложения

<a name="target_audience"></a>

#### 2.3.2.1 Целевая аудитория
Люди любой возрастной категории, обладающие минимальной компьютерной грамотностью и работающие в области морских грузоперевозок.

<a name="assumptions_and_dependencies"></a>

# 3 Системные требования

<a name="functional_requirements"></a>

## 3.1 Функциональные требования

<a name="main_functions"></a>

### 3.1.1 Основные функции

<a name="input_data"></a>

#### 3.1.1.1 Ввод данных
Пользователь имеет возможность ввести данные для установки количества причалов, имени кораблей, грузов.

<a name="send_to_server"></a>

#### 3.1.1.2 Отправка на сервер
Пользователь имеет возможность отправить данные о корабле с грузами и основными характеристиками на сервер.

<a name="get_info"></a>

#### 3.1.1.3 Получение информации
Пользователь имеет возможность получать информацию о корабле в любой момент времени.

<a name="finish_work"></a>

#### 3.1.1.4 Подтверждение окончания загрузки/выгрузки
Пользователь имеет возможность подтвержать окончание работы с сервером.

<a name="restrictions_and_exclusions"></a>

### 3.1.2 Ограничения и исключения
1. Проверка на корректность ввода количества причалов.
2. Проверка на корректный ввод груза.
3. Проверка на корректность заполнения информации о корабле перед его отправкой на сервер.
4. Ограничение на создание более одного корабля за раз.

<a name="non-functional_requirements"></a>

## 3.2 Нефункциональные требования

<a name="quality_attributes"></a>

### 3.2.1 Атрибуты качества

<a name="requirements_for_ease_of_use"></a>

#### 3.2.1.1 Требования к удобству использования
1. Доступ ко всем операциям в одном окне;
2. Все функциональные элементы пользовательского интерфейса имеют названия, описывающие действие, которое произойдет при выборе элемента;
3. Доступность. Данное приложение не разработано для людей с ограниченными возможностями, т.к. является узкопрофильным. Однако для человека, который работает в области морских грузоперевозок, оно доступно.
4. Приложение точно и не допускат ошибок в процессе работы.
5. Приложение надёжно. Доступно только компаниям, работающим с ним.
