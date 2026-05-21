# Proje Başlığı: Valorant Ajan-Silah API’si

### Kullanılan API Adresleri
* **Silahlar için:** `https://valorant-api.com/v1/weapons`
* **Ajanlar için:** `https://valorant-api.com/v1/agents`

### API'nin İçeriği ve Çekilen Verilerin Açıklaması
* **İçerik:** ValorantAPI, Valorant oyunundaki güncel bilgileri aktaran açık kaynaklı bir veri servisidir.
* **Kullanılan Parametreler:**
  * `Agents` $\rightarrow$ Ajanları listeler.
  * `Weapons` $\rightarrow$ Silahları listeler.

### Dönen Verilerden Kullanılanlar
* `display_name` $\rightarrow$ İsim bilgisini verir.
* `description` $\rightarrow$ Açıklama bilgisini verir.
* `category` $\rightarrow$ Silahlarda tür/çeşit bilgisini verir.
* `cost` $\rightarrow$ Silahlarda fiyat bilgisini verir.

### Veri Parçalama (Parsing)
* **Ajanlarda:** `isim;açıklama` (Örneğin $\rightarrow$ `"displayName": "Omen"`)
* **Silahlarda:** `isim;fiyat;çeşit`

---

## Proje Konusu
Program, yapısı gereği Valorant oyununun ajan ve silah bilgilerini API'den çekerek belirli bir listeye yerleştirir. Kullanıcı bu bilgileri dilediği gibi güncelleyebilir. 

**Örnekler:**
* **Ajanlarda:** `Sage: *AÇIKLAMA*` şeklinde isim ve açıklama üzerinde güncelleme yapılabilir.
* **Silahlarda:** `Spectre:1600:Hafif Makineli Tüfek` şeklinde silahın fiyatı ve türü hakkında güncellemeler yapılabilir.

---

## Ek Özellikler
* **Çift Kaynaklı Yapı:** API çekilirken veri çeşitliliğini artırmak amacıyla iki ayrı uç noktadan (endpoint) veri çekilmiştir.
* **Gelişmiş Alt Menüler:** Silahların fiyatına ve çeşidine göre listeleme ile silme gibi alt menü seçenekleri çeşitlendirilmiştir.
* **Kullanıcı Yönetim Sistemi:** Kullanıcı girişinin sağlanmasının yanı sıra sisteme yeni kayıt olma özelliği de eklenmiştir.
  * *Örneğin* $\rightarrow$ Kullanıcı Adı: `kayserispor` | Şifre: `kysr123`
  * Bu kayıt bilgileri `kullaniciListe.txt` adlı metin dosyasında depolanmaktadır.
* **Hata Yönetimi (Loglama):** Programda oluşan hatalar, sistem zaman damgası (timestamp) ile birlikte anlık olarak `hataKayitDefteri.txt` dosyasına kaydedilmektedir.
