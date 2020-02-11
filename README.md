#### **Makarevich Roman Product Factory**

##### **Overview**

Приложение для автоматизации закупок товаров у завода.

##### **User Story**

Начнём с работы "клиента" с системой.

MRPF-1 Как "Клиент" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь, 
       для возможности войти в систему.

Request:

POST /product-factory-app/customer/sign-up


{

      "email" : "vasya@email.com",
      "password" : "qwerty",
      "fio" : "Пупкин Василий Иванович",
      "companyName" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "accountNumber" : "1111 2222 3333 4444" 
      
}

Response: 201 CREATED

{

    "id" : 1
  
}


MRPF-2 Как "Клиент", будучи ранее зарегистрированным пользователем, я хочу войти в систему, 
       и, если такой пользователь существует и пароль совпадает, войти в систему, для последующей работы с системой.

Request:

POST /product-factory-app/customer/sign-in

{

    "email" : "vasya@email.com",
    "password" : "qwerty"
  
}

Response: 200 OK 

{

    "id" : 1
  
}


MRPF-3 Как "Клиент" я хочу получить список доступных видов тар(кегель, банка, бутылка) и их количество на складе, 
       чтобы увидеть ассортимент и, в результате, получаю его.

Request:

GET /product-factory-app/products

Response: 200 OK

{

       "productName" : "keg",
       "material" : "sreel",
       "weight" : 7.1,
       "numberOfKeg" : 1000   
}



MRPF-4 Как "Клиент" я хочу добавить товар в корзину, для последующего оформления заказа. И у меня получается это сделать.

Request:

POST /product-factory-app/basket/${productId}/add-basket-list

{

      "product" : "kegel", 
      "numberOfKegel": 100  
        
}

Response: 200 OK


MRPF-5 Как "Клиент" я хочу оформить заказ, для приобритения товара. и у меня получается это сделать. 
       При оформлении заказа корзина очищается и показывается, что заказ оформлен.

Request:

POST /product-factory-app/basket/make-order

Response: 200 OK

{

    "The order is made"

}

Предполагается, что пользователь "Работник" зарегестрирован заранте.

MRPF-6 Как "Работник" я хочу произвести поступление товара на склад, для последующей продажи и у меня получается это сделать.
       

Request:


PUT /product-factory-app/products/add-product
 
  {
  
      "product" : "kegel", 
      "numberOfKegel": 200
      
  }

Response: 200 OK


MRPF-7 Как "Работник" я хочу просмотреть список заказов, для последующей обработки и у меня получается это сделать.

Request:

GET /product-factory-app/orders

Response: 200 OK

  {
  
      "id" : 1,
      "fio" : "Пупкин Василий Иванович",
      "companyName" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "accountNumber" : "1111 2222 3333 4444",
      "product" : "kegel", 
      "numberOfKegel": 100,
      "totalCost" : 10000 
      
  }


MRPF-8 Как "Работник" я хочу обработать заказ, чтобы выставить счёт клиенту.

Request:

POST /product-factory-app/orders/${orderId}/complete-order

Response: 200 OK

  {
  
      "orderId" : 1,
      "customer" : {
      "fio" : "Пупкин Василий Иванович",
      "companyName" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "accountNumber" : "1111 2222 3333 4444"
      
      },
      "seller" : {
      "companyName" : "Завод тары для пива", 
      "adress" : "г. Минск, ул. Предприятий связанных с пивом",
      "accountNumber" : "2222 6666 4444 8888"
      },
      "product" : "kegel", 
      "numberOfKegel": 100,
      "totalCost" : 10000  
         
  }
