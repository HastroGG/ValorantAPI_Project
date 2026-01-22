**Programlama Dilleri I Ödevi**

**Proje Başlığı		:Valorant Ajan-Silah API’si**

**Kullanılan API adresi	:** [*https://valorant-api.com/v1/weapons*](https://valorant-api.com/v1/weapons)\
`				  `*https://valorant-api.com/v1/agents*

**API nin içeriği ve API den çekilen verilerin açıklaması:**

**İçerik:*** ValorantAPI Valorant oyunundaki bilgileri aktaran açık kaynaklı veri servisidir.

**Kullanılan Parametre:**

- Agents à Ajanları Listeler
- Weapons àSilahları Listeler

**Dönen Verilerden Kullanılanlar:**

- display\_name àİsim bilgisini verir.
- description àAçıklama bilgisini verir.
- categoryàSilahlarda çeşit bilgisi verir.
- costàSilahlarda fiyat bilgisi verir.

**Veri Parçalama:**

**Ajanlardaà** isim;açıklama

**Silahlardaà**isim;fiyat;çeşit

**Örneğinà** “displayName”: “Omen”,

**Proje Konusu:**Program yapısı gereği Valorant oyununun ajan ve silah bilgilerini kullanarak belirli bir listeye yerleştiriyor.Kullanıcı bu bilgileri gönlünce değiştirebiliyor Örneğin:

Ajanlarda à Sage:\*AÇIKLAMA\* ismi ve açıklama üzerinde güncelleme yapılabiliyor.

Silahlarda à Spectre:1600:Hafif Makineli Tüfek silahın fiyatı türü hakkında güncellemeler yapılabiliyor.





**Ek özellikler:**

- *API çekilirken çeşitlilik artması açısından iki ayrı kaynaktan API çekildi.*

- *Alt menüler çeşitlendirildi(Silahların fiyatı çeşidine göre listeleme silme.)*


- *Ayrıca kullanıcı girişi sağlanmasının yanında yeni kayıt sistemi de eklendi.*

  *Örneğin*à *Kullanıcı Adı:kayserispor Şifre:kysr123*

  *Bu kayıt bilgileri “kullaniciListe.txt” text dosyası üzerine kayıt ediliyor.*

- *Hata bilgileri “hataKayitDefteri.txt” text dosyasına zaman damgası ile birlikte kayıt ediliyor.*

