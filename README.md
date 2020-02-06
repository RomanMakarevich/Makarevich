#### **Makarevich Roman Product Factory**

##### **Overview**

Приложение для автоматизации закупок товаров у завода.

##### **User Story**

Начнём с работы "клиента" с системой.

MRPF-1 Как "Клиент" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

POST /product-factiry-app/customer/sign-up


{
      "email" : "vasya@email.com",
      "password" : "qwerty",
      "fio" : "Пупкин Василий Иванович",
      "company name" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "account number" : "1111 2222 3333 4444", 
}

Response:

{
  "id" : 1
}


MRPF-2 Как "Клиент", будучи зарегистрированным пользователем, я хочу войти в систему, 
       и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

POST /product-factiry-app/customer/sign-in

{
  "email" : "vasya@email.com",
  "password" : "qwerty"
}
Response: 

{
  "id" : 1
}


MRPF-3 Как "Клиент" я хочу получить список доступных видов тар(кегель, банка, бутылка) и их количество на складе,
       и в результате получаю его.

Request:

GET /product-factiry-app/product/list

Response:
[
  {

      "product" : kegel, pot, bottle,
      "number of kegel": 1000,
      "number of pot": 1000,
      "number of bottle": 1000,     

  }
]


MRPF-4 Как "Клиент" я хочу добавить товар в корзину. И у меня получается это сделать.

Request:

ADD /product-factiry-app/basket/productId/add-basket-list

{

      "product" : kegel, 
      "number of kegel": 100,
      "product" : pot,
      "number of pot": 100,
      "product" : bottle,
      "number of bottle": 100,     

}

Response:


MRPF-5 Как "Клиент" я хочу оформить заказ. и у меня получается это сделать. 
       При оформлении заказа корзина очищается и показывается, что заказ оформлен.

Request:

POST /product-factiry-app/basket/make-order

Response:
[

"The order is made"

]



MRPF-6 Как "Работник" я хочу произвести поступление товара на склад и у меня получается это сделать.
       (Пользавотель "Работник" зарегестрирован зарание)

Request:


POST /product-factiry-app/product/list/add-product
 
  {
      "product" : kegel, 
      "number of kegel": 200
  }

Response:


MRPF-7 Как "Работник" я хочу просмотреть список заказов и у меня получается это сделать.

Request:

GET /product-factiry-app/orders

Response:
[
  {
  
      "id" : 1,
      "fio" : "Пупкин Василий Иванович",
      "company name" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "account number" : "1111 2222 3333 4444"
      "product" : kegel, 
      "number of kegel": 100,
      "product" : pot,
      "number of pot": 100,
      "product" : bottle,
      "number of bottle": 100,
      "totalCost" : 10 000
      
  }
]

MRPF-8 Как "Работник" я хочу обработать заказ. при обработке закаказа, 
       он удаляется из списка заказов и попадает в список обработанных заказов.
       Клиенту выставляется счёт.

Request:

POST /product-factiry-app/orders/orderId/complete-order

Response:
[
  {
      "order Id" : 0001-01,
      "customer" : "Пупкин Василий Иванович","Пивной бар №1","г. Минск, ул. Пивная, 1", "1111 2222 3333 4444",
      "seller" : "Завод тары для пива", "г. Минск, ул. Предприятий связанных с пивом", "2222 6666 4444 8888",

      "product" : kegel, 
      "number of kegel": 100,
      "product" : pot,
      "number of pot": 100,
      "product" : bottle,
      "number of bottle": 100,
      "totalCost" : 10 000
  }
]