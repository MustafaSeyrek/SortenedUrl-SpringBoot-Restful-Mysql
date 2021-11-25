# SortenedUrl-SpringBoot-Restful-Mysql
auction-shortened-url projesi ile kullanıcılar arasında paylaşılan, sistemden kullanıcılara gönderilen email/sms/push notification gibi farklı kanallarda
kullanılabilmek üzere url kısaltma servisi geliştirmenizi bekliyoruz. Url kısaltma uygulamasının 8080 de çalışacak şekilde ayarlandığını varsayılmıştır.

Kullanıcı üyelik oluşturur
POST http://localhost:8080/user/signup
REQUEST {
username:"", password:""
}

RESPONSE {
userId: 1
}

Kullanıcı kısaltmak istediği URL i sisteme tanımlar, sistem uzun URL in karşılığı olacak şekilde kısa url oluşturur
ve kullanıcının kısa urlleri arasına kaydeder.

POST http://localhost:8080/user/1/url/create
REQUEST {
url: "https://www.tapu.com/l/uygulamaya-ozel-kampanyali-tapular"
}

RESPONSE {
id: 1,
shortened: "http://localhost:8080/s/XXXyyyZZZZ"
}


Örnek: Kullanıcı url olarak sisteme https://www.tapu.com/l/uygulamaya-ozel-kampanyali-tapular urlini girer,
sistem kısa url olarak http://localhost:8080/shortendurl değerini döner.

Kullanıcı shortendurl urline girmek istediğinde uygulama kullanıcıyı gerçek linke yönlendirir.

GET http://localhost:8080/s/XXXyyyZZZZ => RESPONSE 301 https://www.tapu.com/l/uygulamaya-ozel-kampanyali-tapular

Kullanıcı kısaltmış urllerini görüntüleyebilir ve silebilir.

GET http://localhost:8080/user/{userId}/url/list
GET http://localhost:8080/user/{userId}/url/detail/{urlId}
DELETE http://localhost:8080/user/{userId}/url/delete/{urlId}


Duplicate kısaltmış url kayıtları oluşması engellenmelidir.
