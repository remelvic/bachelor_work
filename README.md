# TIMEWORK 4 hours 6.10.22

# The Twelve Factor App

Twe 12factor App — это методология для создания SaaS-приложений, которые:

(Минимум времени интеграции в проект новых разработчиков, соглашения с операционными системами, развертывание на облачных платформах, минимальное расхождение между разработкой и продакшеном, масштабирование без существенных изменений в разработке и инструментах)

 - Используют __декларативный__ формат для описания процесса установки и настройки, что сводит к минимуму затраты времени и ресурсов для новых разработчиков, подключённых к проекту;
 - Имеют __соглашение__ с операционной системой, предполагающее __максимальную переносимость__ между средами выполнения;
 - Подходят для __развёртывания__ на современных __облачных платформах__, устраняя необходимость в серверах и системном администрировании;
 - __Сводят к минимуму расхождения__ между средой разработки и средой выполнения, что позволяет использовать __непрерывное развёртывание__ (continuous deployment) для максимальной гибкости;
 - И могут __масштабироваться__ без существенных изменений в инструментах, архитектуре и практике разработки.

 ## 12 ФАКТРОВ
 --- 
 1) __Кодовая база__ Одна кодовая база, отслеживаемая в системе контроля версей - множество развертываний

     Приложение не может использовать тот же самой код что и другое приложение. Это нарушает 12 факторов. _ВЫХОД :_ выделение общего кода в библиотеки, которые потом можно подключить через [менеджер зависимостей](#Зависимости).

---

 2) __Зависимости__ Явно объявляйте и изолируйте зависимости 

    Приложение 12 факторов никогда __не зависит__ от неявно существующих, доступных по всей системе пакетов.
---

 3) __Конфигурация__ Сохраняйте конфигурацию в среде выполнения

    Иногда приложения хранят конфигурации как константы в коде. Это нарушение методологии двенадцати факторов, которая требует __строгого разделения конфигурации и кода__. Конфигурация может существенно различаться между развёртываниями, код не должен различаться.

    Лакмусовой бумажкой того, правильно ли разделены конфигурация и код приложения, является факт того, что кодовая база приложения может быть в любой момент открыта в свободный доступ без компрометации каких-либо приватных данных.

    __Приложение двенадцати факторов хранит конфигурацию в переменных окружения__ (часто сокращается до __env vars__ или __env__).  Переменные окружения легко изменить между развёртываниями, не изменяя код

 ---
 4) __Сторонние службы (Backing Services)__ Считайте стороние службы подключаемыми ресурсами

    __Код приложения двенадцати факторов не делает различий между локальными и сторонними сервисами.__ Подключение и отключение баз данных, апичек и т.д. без каких-либо изменений кода. 
 ---
 5) __Сборка, релиз, выполнение__ Строго разделяйте стадии сборки и выполнения

    _Этап сборки_ - преобразует репозиторий кода в исполняемый пакет, называемый _сборка_.
    _Этап релиза_ принимает сборку и объединяет её с текущей конфигурацией развёртывания. Релиз готов к запуска в среде выполнения
    _Этап выполнения_ запускает приложение в среде выполнения путём запуска некоторого набора процессов приложения из определённого релиза.

 ---
 6) __Процессы__ Запускайте приложение как один или несколько процессов не сохраняющих внутреннее состояние

    __Процессы приложения двенадцати факторов не сохраняют внутреннее состояние (stateless) и не имеют разделяемых данных (share-nothing)__

 ---
 7) __Привязка портов__ Экспортируйте сервисы через привязку портов

    Веб-приложение __экспортирует HTTP-сервис путём привязки к порту__ и прослушивает запросы, поступающих на этот порт.

 ---
 8) __Параллелизм__ Масштабируйте приложение с помощью процессов

    Процессы в приложении двенадцати факторов взяли сильные стороны из модели процессов Unix для запуска демонов. С помощью этой модели разработчик может спроектировать своё приложение таким образом, что для обработки различной рабочей нагрузки необходимо назначить каждому типу работы свой типа процесса.

 ---
 9) __Утилизируемость (Disposability)__ Максимизируйте надежность с помощью быстрого запуска и корректного завершения работы 

    __Приложения 12 факторов могут быть запущены и остановлены в любой момент.__ Процессы должны стараться __минимизировать время запуска__.
    
    Процессы должны __завершаться корректно, когда они получают SIGTERM__ сигнал от диспетчера процессов.

    Процессы также должны быть __устойчивыми к внезапной смерти__ в случае отказа аппаратного обеспечения. Хотя это мало вероятно, но это может случиться. И по закону подлости (_Murphy's law_) это случиться в самый неподходящий момент.

 ---

 10) __Паритет разработки/работы приложения__ 
 Держите окружение разработки,промежуточного развертывания и рабочего развертывания максимально похожими

 Различия между разработкой и работой приложения:
    
- __Различие во времени__: разработчик может работать с кодом, который попадёт в рабочую версию приложения только через дни, недели или даже месяцы.
    
- __Различие персонала__: разработчики пишут код, OPS инженеры разворачивают его.
    
- __Различие инструментов__: разработчики могут использовать стек технологий, такой как Nginx, SQLite, и OS X, в то время как при рабочем развёртывании используются Apache, MySQL и Linux.

 ---
 11) __Журналирование__ Рассматривайте журнал как поток событий

 __Приложение двенадцати факторов никогда не занимается маршрутизацией и хранением своего потока вывода__. Приложение не должно записывать журнал в файл и управлять файлами журналов. Вместо этого каждый выполняющийся процесс записывает свой поток событий без буферизации в стандартный вывод ```stdout```. Во время локальной разработки разработчик имеет возможность просматривать этот поток в терминале, чтобы наблюдать за поведением приложения

 ---
 12) __Задачи администрирования__ Выполняйте задачи администрирования/управления с помощью разовых процессов

  Разовые процессы администрирования следует запускать в среде идентичной регулярным длительным процессам приложения. Они запускаются на уровне релиза, используя те же кодовую базу и конфигурацию, как и любой другой процесс, выполняющий этот релиз. Код администрирования должен поставляться вместе с кодом приложения, чтобы избежать проблем синхронизации.

# Distributed Tracing (Распределенная Трассировка)

__Распределенная трассировка__ это метод используемый для профилирования и мониторинга приложений, особенно микросервисных. Распределенная трассировка помогает определить где происходят сбои и что вызывает низкую производительность https://opentracing.io/docs/overview/what-is-tracing/

Open Tracing __НЕ__ является загрузкой или программой. __НЕ__ является стандартом. Ее требуется добавлять в инструментарий разработчка,используя API, или в фреймворки.

Open Tracing состоит из спецификации API, фреймворков и библиотек. 



# OpenTelemetry 

__OpenTelemetry__ это набор инструментов API и SDK. Используется для обработки, генерации, сбора и экспорта данных (metrics, logs,traces), чтоб проанализировать производительность и поведение ПО.

## What is Observability? 
 Наблюдаемость позволяет нам понять систему извне, задавая вопросы об этой системе, не зная ее внутренней работы. Помогает легко устронять неполадки и решать новые проблемы и понять почему именно что-либо происходит.

 __Metrics__ - частота системных ошибок, загрузка процессора, частота запросов для данной службы.

 __SLI (Service Level Indicator)__ - оценивает наш сервис с точки зрения пользователя. Например как быстро загрузилась веб-страница
 __SLO (Service Level Objective)__ - средство, с помощью которого надеждность доводится до сведения организации.


## Metrics
Сегодня OpenTelemetry определяет три метрических инструмента:

__counter__ - он только увеличивается 

__measure__ - представляет значение в некотором определенном диапазоне 

__observer__ - фиксирует текущий набор значений (Напр. указатель уровня топлива в автомобиле)

В отличии от трассировки запросов, которые отслеживают жизненные циклы запросов и предоставляют контекст для отдельных частей запроса, метрики предоставляют статическую информацию в совокупности. 

## Baggage 

__Багаж относиться к контекстной ифне, которая передается между spans__

Багаж используется для хранения конфиденциальных данных, которые вы можете предоставить третьим лицами

Baggae != Span attributes

## Collector 

__Collector__ получает, обрабатывает и экспортирует данные телеметрии. Поддерживает прием данных в формате (OLTP, Jaeger, Prometheus а множество коммерческих инструментов) и отправку данных на один или несколько серверов. 


# Helm 
 
 Helm помогает управлять приложения Kubernetes. 
 Helm относиться к 5 этапу из 12 факторного приложение, т.е Сборка, релиз, выполнение.

 В Helm это устроенно так:
 __Chart__ - набор информации необходимой для создания инстанции Kubernetes
 __Config__ - содержит инфо о конфигурации, которую можно объединить в пакетный chart для создания объекта релиза
 __Release__ - это запущенная инстанция chart, объединенная с определенной конфигурацией (Config)



# Argo CD
 
 Argo CD - это декларативный инструмент непрерывной доставки GitOps для Kubernetes
 
 Argo CD следует шаблону GitOps, использующему репозитории Git в качестве источника среды окружения. 

 Argo Cd реализован как контроллер kubernetes, который непрерывно отслеживает запущенные приложения и сравнивает текущее состояние с желаемым целевым состоянием (Как указано в репо ГИТа). Argo CD сообщает и визуализирует различия, предоставляя при этом средства для автоматической или ручной синхронизации текущего состояние в желаемое целевое

# Istio
 
 Istio - это путь к балансировке нагрузки, аутентификации между службами и мониторингу - с небольшим кол-ом изменений кода или без них

 Istio состоит из двух компонентов: data plane and the control plane 

 __Data plane__ это связь между службами. 
 __Control plane__ принимает желаемую конфигурацию и представление служб и динамически программирует прокси-серверы, обновляя их по мере изменения правил или изменения среды.

# Terraform 

__Terraform__ - это инструмент (инфарструктура как код IaC), который позволяет безопасно и эффективно создавать, изменять и обновлять инфраструктуру. Это включает в себя как компоненты низкого уровня, такие как вычислительныее экземпляры, хранилище и сеть, так и компоненты высокого уровня, такие как записи DNS и функции SaaS.

# Granfana

 __Grafana__ — это платформа с открытым исходным кодом для визуализации, мониторинга и анализа данных. Grafana позволяет пользователям создавать дашборды с панелями, каждая из которых отображает определенные показатели в течение установленного периода времени. Каждый дашборд универсален, поэтому его можно настроить для конкретного проекта или с учетом любых потребностей разработки и/или бизнеса.

 ## Grafana для веб-приложения визуализирует:
  - версию приложения
  - уникальный идентификатор каждого запроса
  - время отклика и статус
  - код ошибки 
  - IP-адрес, с которого был отправлен запрос
  - инфо о пользователе 
  - устройство и т.д.

# Granfana Loki 
 __Loki__ это система агрегации логов, предназначенная для хранения и запроса логов из всех приложений и инфраструктуры.

 Loki использует уникальный подход, __индексируя только метаданные__, а не полный текст строк лога.


# Монолитные приложения

 ## Преимущества
  - Простота разработки
  - Простота развертывания - у вас всего один файл (WAR, JAR etc.)
  - Простота масштабирования - вы можете масштабировать приложение, запустив несколько копий приложения за балансировщиком нагрузки

 ## Недостатки
  - Большая кодовая база пугает разработчиков, особенно еще зеленых бойцов. Из-за этого разработка замедляется. Модульность размывается со временем 
  - Чем больше кода, тем медленее среда разработки и менее продуктивны разработчики
  - Перегруженный веб-контейнер загружается все больше и больше времени
  - Непрерывное развертывание становиться все более и более сложным
  - Масштабирование приложения становитья сложным. Монолит может масштабироваться только в одном измерении. Мы не можем масштабировать каждый компонент независимо
  - Мы не можем работать независимо разными командами над приложением 
  - Долгое использование конкретного стека технологий иногда даже и версий технологий.

# Микросервисные приложения 

 ## Преимущества
 - Обеспечивает непрерывную доставку и развертывание больших и сложных приложений (Каждую часть легче починить, сервисы меньше и быстрее тестируются, службы могут быть развернуты независимо)
 - Разработчику легче понять относительное малые микросервисы, нежели большой монолит, IDE работает быстрее, что делает разработчиков более продуктивными, ускоряется развертывание
 - Улучшенная изоляция сбоев. Напр. : если в одной службе утечка памяти, то это никак не влияет на другую службу
 - Можно использовать новые стеки и технологии 


 ## Недостатки
 - Программисты должны внедрить связь между всеми процессами и справиться с частичным отказом 
 - Сложнее реализовать запросы, охватывающие несколько служб
 - Тестирование между службами сложнее
 - Реализация запросов, охватывающих несколько служеб, требует тщательной координации между командами
 - Сложность развертывания
 - Увеличенное потребление памяти

# Pattern: Decompose by business capability (Разложение по бизнес-возможностям)

Определение услуг, соответствующие бизнес-возможностям. Бизнес-возможности напр. _Управление заказами_ отвечает за заказы; _Управление клиентами_ несет ответственность за клиентов 

## Преимущества
 - Стабильная архитектура
 - Услуги являются целостными и слабо связанными 

## Проблемы
- __Как определить бизнес-возможности?__ Они определяются путем анализа цели, структуры, бизнес-процессов и областей компетенции организации.  Отправными точками для определения бизнес-возможностей являются: 
   1. организационная структура - различные группы внутри организации могут соответствовать бизнес-возможностям или группам бизнес-возможностей
   2. модель домена высокого уровня - бизнес-возможности часто соответсвуют объектам домена 

# Pattern: Decompose by subdomain (Разложение по поддоменам)

 Каждый домен состоит из нескольких поддоменов:
  - Ядро - ключевое отличие для бизнеса и наиболее ценная часть приложения 
  - Поддержка - связана с тем, что делает бизнес, но не является отличительной чертой. 
  - Общие -  не специфичны для бизнеса и в идеале реализуются с использованием готового программного обеспечения

## Преимущества
 - Стабильная архитектура, поскольку поддомены относительно стабильны
 - Команды разработчиков многофункциональны, автономны и организованы вокруг предоставления бизнес-ценности
 - Сервисы являются взаимосвязанными и слабо связанными

## Проблемы
 - __Как определить поддомены?__ Здесь требуется понимание бизнеса.Хорошими отправными точками для определения поддоменов являются:

  1. структура организации - поддоменам могут соответствовать разные группы внутри организации
  2. модель домена высокого уровня - поддомены часто имеют ключевой объект домена 

# cURL
   __cURL__ помогает делать сетевые запросы по протоколам _HTTP,FTP,SCP_

# Pattern: CQRS command query responsibility segregation (Разделение ответственности командных запросов)
   Реализует запрос, которому нужны данные из нескольких сервисов. Она задействует одно или несколько представлений базы данных.

   Объединение API хорошо подходит для реализации многих запросов, которые должны извлекать данные из разных сервисов. __Но__ в микросервесной архитектуры это лишь частичное решение. Есть запросы, которые нельзя эффективно объединить. Так же возможны трудности с тем, что запросы не выходят за пределы одного сервиса.

   CQRS решает следующие проблемы :
   - Объединение АПИ приводит к малоэффективным операциям join выполняемых в памяти 
   - Сервис, владеющий данными, хранит их в формате или базе данных, которые не имеют эффективной поддержки нужного запроса 
   - Необходимость CQRS означает, что реализацией запроса не должен заниматься сервис, который владеет данными

   CQRS работает так, что создает (Командную) БД для создания, обновления и удаления (POST, PUT, DELETE). Другую - для запросов; она поддерживается в актуальном состоянии с помощью событий, которые публикуются при каждом изменении в БД командной стороны

   У сервиса есть обработчики, которые подписываются на события, публикуемые несколькими сервисами, и обновляют базу данных привязаной к этому сервису.

   ## Преимущества
   - Возможность эффективной реализации запросов в мик. арх.
   - Возможность эффективной реализации разнородных запросов
   - Возможность выполнения запросов в приложении, основанном на порождении событий
      
         Хранилище событий поддерживает только запросы по первичному ключу. CQRS устраняет эту проблему, создавая для агрегатов одно или несколько представлений(VIEW) поддерживаемых в актуальном состоянии 
   
   - Улучшеное разделение ответсвтенности  

   ## Недостатки 
   - Более сложная архитектура
   - Отставание репликаций 

         Как можно было бы ожидать, между публикацией события командной стороной, его обработкой запрашивающим сервисом и обновлением представления проходит некоторое время. Клиентское приложение, которое обновляет агрегат и сразу же обращается к представлению, может получить предыдущую версию агрегата
   
   ВЗЯТО ИЗ : __КРИС РИЧАРДСОН МИКРОСЕРВИСЫ ПАТТЕРНЫ РАЗРАБОТКИ И РЕФАКТОРИНГА__

# Redis DB

   __Redis__ ситема управления БД класса NoSQL, работающая со структурами данных типа "ключ-значение". Используется как БД, так и для реализации кешей, брокеров сообщений

   - Особенности:
   Поддерживает типы String, Bitmap, Bitfield, Hash, List, Set, Sorted Set, Geospatial, HyperLogLog, Stream
   
   Макс. производительность 100 тыс. SET GET запросов.
   
   Она работает в оперативной памяти и есть возможность хранения данных на жестких дисках, благодаря, механизму снимков и журналирования

   Redis состоит из ведущего и подчиненных узлов. Это помогает справиться с отказами. Помогает простроить надежную линию связи и исключает потерю данных при их передече от ведущего узла к подчиненному. 

## Брокер сообщений

   __Брокер сообщений__ - архитектурный паттерн в распределительных системах; приложение которое преобразует сообщение по одному протоколу от приложения-источника в сообщение протокола приложения-приемника, тем самым выступая между ними посредником.

# Swagger

   __Swagger__ фреймворк для спецификации RESTfull API. Дает не только интерактивно просматривать спецификацию, но и отправлять запросы через Swagger UI

# Keycloack
   __Keycloack__ продукт для реализации _single sign-on_ с возможностью управления доступом. Целью инструмента является сделать создание безопасных приложений и сервисов с мин написанием кода для аутентификации и авторизации.

   Функции:
   
   - Регистрация пользователей
   - Авторизация через соц.сети
   - Single Sign-On / Sign-Off
   - Выдача JSON Web Token подлинности аккаунтам
   - Двухфакторная аутентификация

   Состоит из:
   
   - Сервера
   - Адаптера для приложения 

## Single sign-on / sign-off (Технология единого входа)
   Технология при использовании которой пользователь переходит из одного раздела портала в другой, либо из одной системы в другую, не связанную с первой, без повторной аутентификации.

## JSON Web Token
   Как правило, используется для передачи данных для аутентификации в клиент-серверных приложениях. Токены создаются сервером, подписываются секретным ключом и передаются клиенту, который в дальнейшем использует данный токен для подтверждения своей личности.

# Kubernetes 
   
   При развертывании Kubernetes мы получаем __Cluster__. Он состоит из набора машин которые зовут __Node__ на которых выполняются контейнерные приложения. Каждый кластер имеет мин. 1 рабочий нод. В каждом Ноде есть __PODs__, которые являются компонентами рабочей нагрузки приложения. __Control panel__ управляет pods и node.

   К _control panel_ относятся kube-apiserver (слежка за всеми процессами), etcd (Хранит всю инфу как key - value), kube-scheduler (отслеживает созданые поды без назначеного node и назначает им node), kube-controller-manager (запускает controll process), cloud-controller-manager (выстраивает логику управления специфичную для облака )

   _Kubelet_ работает на каждом node in cluster.

   _kube-proxy_ поддерживает сетевые правила на node, которые разрешают сетевое взаимодействие с вашими PODs из сетевых сеансов внутри или за пределами вашего кластера.

   Kubernetes использует YAML-файлы для создания объектов таких как ПОДы, реплики и тд. YAML-файл __всегда__ содержит в себе 4 уровня:
   - apiVersion (Какая АПИ используется для создания объектов) v1, apps/v1
   - kind (Тип объекта который мы хотим создать)
   - metadata (Хранит инфу о объекте типа имени )
   - spec (Хранит в себе инфу как зовут контейнер )

# Image 
   __Image__ (container image) - готовый к запуску программный пакет, который содержит в себе необходимое для запуска приложения:
   
   - Код 
   - Любую среду выполнения
   - Прикладные и системный библиотеки 
   - Значения по умолчанию для важных настроек 
   Image это пакет,план или тимплейт как виртуальная машина. Они нужны для создания контейнеров. Изображение помогает разработчику создать приложение и операторам его модифицировать 

# Container 
   __Container__ при запуске имеет одинакое поведение везде. Он является __неизменяемым__. Если контейнер запущен, код изменить нельзя. Обойти это можно лишь тем, что просто создадим новое изображение со всеми изменениями, а затем создамим новый контейнер с этим изображением 

   Container запускает инстанцию изображения. В контейнерах, грубо говоря, храниться наше приложение

# Node (Minions)
   Node это физическая или виртуальная машина на которой установлен Кубернетис. Node рабочая машина та, на которой запущены контейнеры этим Кубернетисом
   Node использует </> __kubelet__ (Взаимодействует с мастером)

# Cluster 
   Cluster это группа nodes. Если упадет один нод, то аппка все равно продолжит работать

# Master 
   Это тоже нод, только он настроенный на то, чтоб следить за всеми остальными нодами 
   Мастер использует </> kube-apiserver
   Вся инфа храниться в мастере в etcd как key-value
   Так же у мастера есть controller and Scheduler

# Mini-cube 
   Mini-cube собиарет все компоненты в одно изображение

# Pods
   Pods это единственная инстанция приложения. ПОД это самый маленький объект который можно создать в кубернетисе. ПОД хранится внутри НОДА, который храниться внутри кластера 

# YAML
   __YAML__ это язык для сериализации данных, который отличается простым синтаксисом и позволяет хранить сложноорганизованные данные в компактном и читаемом формате

# Controllers
   __Controllers__ это мозг кубертеса.
   __ReplicationController__ гарантирует, что указанное количество реплик pod выполняется одновременно. 
   Другими словами, ReplicationController гарантирует, что pod или однородный набор pods всегда включен и доступен.

# Labels and Selectors
   __Labels__ - это парa key/value, которые прикрепляются к подам. Они нужны для указания идентифицирующих атрибутов объекта, которые для пользователя значимые и актуальные. Labels позволяют выполнять эффективные запросы are ideal for use in UIs and CLIs. 

   С помощью __Labels Selector__ пользователь может идентифицировать набор объектов.

# ReplicaSet
   __Replica Set__ его главная цель поддержка стабильного набора POD, работающих в любой момент времени.

# Kubernetes commands

__Get OS__
   ```kubectl get nodes -o wide``` in terminal 

__Get more informaition about pods__
   ```kubectl describe pod nginx``` 'cause we have image with name nginx

__Get Pods count__
   ```kubectl get pods```  

__Create new pod with _nginx_ imgae__
   ```kubectl run nginx --image=nginx```

__Get ReplicaSets__
   ```kubectl get replicaset```