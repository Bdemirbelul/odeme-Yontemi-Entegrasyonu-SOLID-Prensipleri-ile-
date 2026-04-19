Dinamik Log Sistemi (Spring Boot)

Bu projede, Spring Boot yapısı içerisinde annotation ve reflection kullanılarak log türünün çalışma anında dinamik biçimde seçilebildiği basit ama öğretici bir örnek geliştirildi. Projenin temel amacı, ortak bir Log arayüzünü farklı sınıflar üzerinden uygulamak ve hangi loglama sınıfının kullanılacağını kod içerisinde sabit olarak tanımlamak yerine, özel annotation’lar ve reflection mekanizması yardımıyla belirlemektir. Böylece sistem daha esnek, geliştirilebilir ve nesne yönelimli tasarım ilkelerine daha uygun hale getirilmiştir.

Proje içerisinde tüm loglama yapılarının temelini oluşturan Log arayüzü bulunmaktadır. Bunun yanında, her log sınıfının hangi anahtar ile seçileceğini belirtmek amacıyla özel olarak tanımlanmış @LogType("...") annotation’ı kullanılmıştır. Örnek olarak geliştirilen DbLog, XmlLog ve TextLog sınıfları şu an için farklı formatlarda konsola çıktı vermektedir. Bu tercih, projenin ödev kapsamına uygun şekilde sade ve anlaşılır kalmasını sağlamak amacıyla yapılmıştır.

Sistemin asıl dinamik yapısı LoggerService sınıfında kurulmuştur. Bu servis, uygulama başlatıldığında classpath üzerinde @LogType annotation’ına sahip sınıfları taramakta, ardından bu sınıfları Spring bean yapısıyla eşleştirerek kullanılabilir hale getirmektedir. Kullanıcıdan veya dışarıdan gelen log tipine göre uygun implementasyon bulunmakta ve ilgili loglama işlemi çalıştırılmaktadır. Bu sayede klasik if-else veya switch-case bloklarıyla her log türünü tek tek kontrol etmeye gerek kalmamaktadır.

Projede ayrıca sistemi test edebilmek amacıyla LogDemoController eklenmiştir. Bu controller sayesinde uygulama tarayıcı üzerinden ya da curl gibi araçlarla kolayca denenebilmektedir. Böylece geliştirilen yapının çalışma mantığı pratik biçimde gözlemlenebilmektedir.

Kısacası bu projede, yeni bir log türü eklemek istendiğinde mevcut servis yapısını değiştirmeye gerek kalmadan yalnızca yeni bir sınıf oluşturup ilgili @Component ve @LogType("yeni_tip") tanımlarını eklemek yeterli olmaktadır. Bu yaklaşım, hem kod tekrarını azaltmakta hem de projenin bakım ve genişletilebilirlik açısından daha sağlıklı bir yapıya kavuşmasını sağlamaktadır.
