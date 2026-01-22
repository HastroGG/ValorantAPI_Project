import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValorantAPI {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
    girisYap();//Kullanıcı girişi ,

    int secim = -1;
    //ANA MENÜ
        while (secim != 5) {
            System.out.println("\n=== VALORANT BİLGİ SİSTEMİ ===");
            System.out.println("1. API'den Veri Çek");
            System.out.println("2. Listeleme Menüsü");
            System.out.println("3. Güncelleme Menüsü");
            System.out.println("4. Silme Menüsü");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();
            

            switch (secim) {
                case 1 :veriCek();break;
                case 2 :listelemeMenu();break;
                case 3 :guncellemeMenu();break;
                case 4 :silmeMenu();break;
                case 5 :System.out.println("Program kapatılıyor...");break;
                default:System.out.println("Hatalı seçim, tekrar deneyin.");
            }
        }


    }

    //ALTMENÜ-2
    public static void listelemeMenu(){
        int altsecim = -1;
        while(altsecim != 5){
        System.out.println("\n=== LİSTELEME MENÜSÜ ===");
        System.out.println("1.Ajanları Listele");
        System.out.println("2.Silahları Listele");
        System.out.println("3.Silahları Kategorilerine Göre Listele");
        System.out.println("4.Silahları girilen fiyatın altında Listele");
        System.out.println("5.Geri Dön");
        System.out.print("Seçiminiz:");
        altsecim = scanner.nextInt();
            switch(altsecim){
                case 1 :ajanlariYazdir("ajanlar.txt");break;
                case 2 :silahlariYazdir("silahlar.txt");break;
                case 3 :silahlariYazdirKategori("silahlar.txt");break;
                case 4 :silahlariYazdirFiyat("silahlar.txt");break;
                case 5 :break;
                default:System.out.println("Hatalı işlem.Tekrar deneyin.");break;
            }
        }
    }

    //ALTMENÜ-2.1
    public static void ajanlariYazdir(String ajanlar) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ajanlar))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                System.out.println(satir);
            }
        } catch (IOException e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
    }
    //ALTMENÜ-2.2
    public static void silahlariYazdir(String silahlar) {
        System.out.println("=== SİLAH MENÜSÜ ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(silahlar))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                System.out.println(satir);
            }
        } catch (IOException e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
    }
    //ALTMENÜ-2.3
    public static void silahlariYazdirKategori(String silahlar) {
        System.out.println("=== KATEGORİLER ===");
        System.out.println("1.BEYLİK SİLAHLAR");
        System.out.println("2.HAFİF MAKİNELİLER");
        System.out.println("3.POMPALI TÜFEKLER");
        System.out.println("4.TAARRUZ TÜFEKLERİ");
        System.out.println("5.KESKİN NİŞANCI TÜFEKLERİ");
        System.out.println("6.AĞIR SİLAHLAR");
        System.out.print("Seçiminiz:");
        int silahsecim = scanner.nextInt();
        String silahcesidi = "";

    switch (silahsecim) {
        case 1:silahcesidi = "BEYLİK SİLAHLAR";break;
        case 2:silahcesidi = "HAFİF MAKİNELİLER";break;
        case 3:silahcesidi = "POMPALI TÜFEKLER";break;
        case 4:silahcesidi = "TAARRUZ TÜFEKLERİ";break;
        case 5:silahcesidi = "KESKİN NİŞANCI TÜFEKLERİ";break;
        case 6:silahcesidi = "AĞIR SİLAHLAR";break;
        default:System.out.println("Geçersiz seçim.");
        return;
}

        try (BufferedReader reader = new BufferedReader(new FileReader(silahlar))) {
         String satir;
         System.out.println("==="+(silahcesidi)+"===");
         while ((satir = reader.readLine()) != null) {
                if (satir.endsWith(silahcesidi)) {
                    System.out.println(satir);
                }
            }
        } catch (Exception e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }

}
    //ALTMENÜ-2.4
    public static void silahlariYazdirFiyat(String silahlar){
        System.out.println("\n===FİYATA GÖRE LİSTELE===");
        System.out.print("Fiyat girin:");
        int fiyat = scanner.nextInt();
        System.out.println("\n===BELİRLEDİĞİNİZ FİYATIN ALTINDAKİ SİLAHLAR===");
        try (BufferedReader reader = new BufferedReader(new FileReader(silahlar))) {
        String satir;
        while ((satir = reader.readLine()) != null) {
            String[] parcalar = satir.split(";");
            if (parcalar.length < 3) continue;
            int silahfiyat=0;
            try {
                silahfiyat = Integer.parseInt(parcalar[1]);
            } catch (NumberFormatException e) {
                
                continue;
            }

            if (fiyat > silahfiyat) {
                System.out.println(satir);
            }
        }
    } catch (Exception e) {
        hataYaz(e);
        System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
    }
    }

    //ALTMENÜ-3
    public static void guncellemeMenu(){
        System.out.println("\n=== Güncelleme Menüsü ===");
        System.out.println("1.Karakter Güncelle");
        System.out.println("2.Silah Bilgisi Güncelle");
        System.out.println("3.Geri Dön");
        System.out.print("Seçiminiz:");
        int altsecim = scanner.nextInt();
        switch(altsecim){
            case 1:karakterGuncelle();break;
            case 2:silahGuncelle();break;
            case 3:break;
            default :System.out.println("Hata");
        }
    }

    //ALTEMENÜ-3.1
    public static void karakterGuncelle() {
    File tempFile = new File("temp.txt");
    System.out.print("Güncellemek istediğiniz karakter adını girin: ");
    String hedef = scanner.next();
    

    boolean bulundu = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("ajanlar.txt"));
         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(hedef + ";")) {
                yazdir(line);
                System.out.print("Bu karakteri güncellemek istiyor musunuz? (e/h): ");
                String cevap = scanner.next();

                if (cevap.equalsIgnoreCase("e")) {
                    System.out.print("Yeni ad: ");
                    String yeniAd = scanner.next();
                    System.out.print("Yeni açıklama: ");
                    String yeniAciklama = scanner.next();
                    line = yeniAd + ";" + yeniAciklama;
                    bulundu = true;
                    tempFile.renameTo(new File("ajanlar.txt"));
                }
            }
            writer.println(line);
        }
    } catch (IOException e) {
        hataYaz(e);
        System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
    }

    File eskiDosya = new File("ajanlar.txt");
    File yeniDosya = tempFile;
    if (eskiDosya.delete()) {
        if (yeniDosya.renameTo(eskiDosya)) {
        } else {
            System.out.println("Dosya yeniden adlandırılamadı.");
     }
    } else {
        System.out.println("Eski dosya silinemedi, güncelleme başarısız.");
    }

    if (bulundu) {
        System.out.println("Karakter güncellendi.");
    } else {
        System.out.println("Karakter bulunamadı.");
    }
}

    //ALTMENÜ-3.2
    public static void silahGuncelle() {
    System.out.print("Güncellemek istediğiniz silah adını girin: ");
    String hedef = scanner.next();
    File tempFile = new File("temp.txt");
    boolean bulundu = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("silahlar.txt"));
         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(hedef + ";")) {
                System.out.println("Bulunan kayıt: " + line);
                System.out.print("Bu silahı güncellemek istiyor musunuz? (e/h): ");
                String cevap = scanner.next();

                if (cevap.equalsIgnoreCase("e")) {
                    System.out.print("Yeni isim: ");
                    String yeniIsim = scanner.next();
                    System.out.print("Yeni fiyat: ");
                    String yeniFiyat = scanner.next();
                    System.out.print("Yeni kategori: ");
                    String yeniKategori = scanner.next();
                    line = yeniIsim + ";" + yeniFiyat + ";" + yeniKategori;
                    bulundu = true;
                }
            }
            writer.println(line);
        }
    } catch (IOException e) {
        hataYaz(e);
        System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
    }

    File eskiDosya = new File("silahlar.txt");
    File yeniDosya = tempFile;
    if (eskiDosya.delete()) {
        if (yeniDosya.renameTo(eskiDosya)) {
        } else {
            System.out.println("Dosya adlandırılamadı.");
     }
    } else {
        System.out.println("Eski dosya silinemedi, güncelleme başarısız.");
    }

    if (bulundu) {
        System.out.println("Silah güncellendi.");
    } else {
        System.out.println("Silah bulunamadı.");
    }

}

    //ALTMENÜ-4
    public static void silmeMenu(){
        System.out.println("\n=== SİLME MENÜSÜ ===");
        System.out.println("1. Ajan Sil");
        System.out.println("2. Silah Sil");
        System.out.println("3. Geri Dön");
        System.out.print("Seçiminiz:");
        int secim = scanner.nextInt();
        switch(secim){
            case 1:ajanSil();break;
            case 2:silahSil();break;
            case 3:System.out.println("Geri dönülüyor...");break;
            default:System.out.println("Hata, Tekrar deneyin.");break;
        }
    }
    //ALTMENÜ-4.1
    public static void ajanSil(){
    File tempFile = new File("temp.txt");
    System.out.print("Silmek istediğiniz karakter adını girin: ");
    String hedef = scanner.next();
    


    try (BufferedReader reader = new BufferedReader(new FileReader("ajanlar.txt"));
         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(hedef + ";")) {
                yazdir(line);
                System.out.print("Bu ajanı silmek istediğinizden emin misiniz? (e/h): ");
                String cevap = scanner.next();

                if (cevap.equalsIgnoreCase("e")) {
                    tempFile.renameTo(new File("ajanlar.txt"));
                    System.out.println("\nAjan silindi, Menüye dönülüyor...");
                    continue;
                }
            }
            
            writer.println(line);
        }
    } catch (IOException e) {
    hataYaz(e);
    System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
    }
        File eskiDosya = new File("ajanlar.txt");
        File yeniDosya = tempFile;
    if (eskiDosya.delete()) {
        if (yeniDosya.renameTo(eskiDosya)) {
        } else {
            System.out.println("Dosya yeniden adlandırılamadı.");
     }
        } else {
            System.out.println("Eski dosya silinemedi, güncelleme başarısız.");
        }
    }

    
    //ALTMENÜ-4.2
    public static void silahSil(){
        File tempFile = new File("temp.txt");
        System.out.print("Silmek istediğiniz silahın adını BÜYÜK HARF ile girin: ");
        String hedef = scanner.next();

    try (BufferedReader reader = new BufferedReader(new FileReader("silahlar.txt"));
         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(hedef + ";")) {
                yazdir(line);
                System.out.print("Bu silahı silmek istediğinizden emin misiniz? (e/h): ");
                String cevap = scanner.next();

                if (cevap.equalsIgnoreCase("e")) {
                    System.out.println("\nBelirtilen silah silindi, Menüye dönülüyor..");
                    tempFile.renameTo(new File("silahlar.txt"));
                    continue;
                }
            }
            writer.println(line);
        }
    } catch (IOException e) {
    hataYaz(e);
    System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
    }

    File eskiDosya = new File("silahlar.txt");
    File yeniDosya = tempFile;
    if (eskiDosya.delete()) {
        if (yeniDosya.renameTo(eskiDosya)) {
        } else {
            System.out.println("Dosya yeniden adlandırılamadı.");
     }
        } else {
            System.out.println("Eski dosya silinemedi, güncelleme başarısız.");
        }
    }
    
    

    //API sitesinden verileri çektiğimiz komut.
    public static void veriCek() {
        String ajanVerisi = ajanlariCek();
        ajanlariKaydet(ajanVerisi, "ajanlar.txt");

        String silahVerisi = silahlariCek();
        silahlariKaydet(silahVerisi, "silahlar.txt");
    }

    //Güncelleme-Silme işlemlerinde işlem yapılacak operatörün bilgisini veren komut.
    public static void yazdir(String satir) {
        String[] parcalar = satir.split(";");
        System.out.println("Adı: " + parcalar[0]);
        System.out.println("Açıklama: " + parcalar[1]);
        System.out.println("----------------------------");
    }

    //Giriş Sistemi için komut.
    public static void girisYap() {
        boolean gecti = false;
        while(gecti == false){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kayıtlı hesabınız mevcut mu (e/h)");
        String kaydol = scanner.next();


        if(kaydol.equalsIgnoreCase("e"))   { //GİRİŞ İŞLEMİ
            
            gecti = true;
        System.out.print("Kullanıcı adınızı girin: ");
        String kullaniciAdi = scanner.next().trim();

        System.out.print("Şifrenizi girin: ");
        String sifre = scanner.next().trim();

        boolean girisBasarili = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("kullaniciListe.txt"))) {
            String satir;

            while ((satir = reader.readLine()) != null) {
                String[] parca = satir.split(":");
                if (parca.length == 2) {
                    String dosyaKullanici = parca[0].trim();
                    String dosyaSifre = parca[1].trim();

                    if (kullaniciAdi.equals(dosyaKullanici) && sifre.equals(dosyaSifre)) {
                        girisBasarili = true;
                        break;
                    }
                }
            }

        } catch (IOException e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
            return;
        }

        if (girisBasarili) {
            System.out.println("Giriş başarılı. Hoş geldin Tatlım Kıymatlım, " + kullaniciAdi);
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre Tatlım Kıymatlım, Sistem kapatılıyor...");
            System.exit(0);
        }
        }
        else if(kaydol.equalsIgnoreCase("h")){ //KAYIT İŞLEMİ
            gecti = true;
        System.out.print("Yeni kullanıcı adı: ");
        String yeniKullanici = scanner.next().trim();

        System.out.print("Yeni şifre: ");
        String yeniSifre = scanner.next().trim();

        File dosya = new File("kullaniciListe.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosya, true))) {
            writer.newLine();
            writer.write(yeniKullanici + ":" + yeniSifre);
            System.out.println("Kayıt başarılı. Giriş yapabilirsiniz.");
        } catch (IOException e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
        }
        }
    }
    

    public static void hataYaz(Exception e) {
        try (FileWriter fw = new FileWriter("hataKayitDefteri.txt", true);
            PrintWriter pw = new PrintWriter(fw)) {
            LocalDateTime zaman = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");// Zaman formatı: Yıl-Ay-Gün Saat-Dakika-Saniye
            String zamanDamgasi = zaman.format(formatter);

            pw.println("[" + zamanDamgasi + "] Hata: " + e.toString());
            for (StackTraceElement ste : e.getStackTrace()) {
                pw.println("\tat " + ste);
            }
            pw.println();

        } catch (IOException ioException) {
            System.out.println("Kayıt defteri hata verdi.Bunu nasıl başardın bir sor kendine >_<");
        }
    }



    public static String unicodeToChar(String text) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i++);
            if (c == '\\' && i < text.length() && text.charAt(i) == 'u') {
                i++;
                int code = Integer.parseInt(text.substring(i, i + 4), 16);
                result.append((char) code);
                i += 4;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    //API sitesindeki json dosyasındaki gereksiz edik düdükleri ayıralım.
    public static void ajanlariKaydet(String jsonData, String dosyaAdi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaAdi))) {
            String[] ajanlar = jsonData.split("\\{\\s*\"uuid\"");

            int sayac = 0;
            for (String ajan : ajanlar) {
                if (!ajan.contains("\"isPlayableCharacter\":true")) continue;

                String ad = unicodeToChar(getField(ajan, "\"displayName\":\""));
                String aciklama = unicodeToChar(getField(ajan, "\"description\":\""));
                if (aciklama.isEmpty()) aciklama = "Yok";

                writer.println(ad + ";" + aciklama +"\n");
                sayac++;
            }
            System.out.println(sayac + " ajan yazıldı.");
        } catch (Exception e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
    }

    //API sitesindeki json dosyasındaki gereksiz edik düdükleri ayıralım.
    public static void silahlariKaydet(String jsonData, String dosyaAdi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaAdi))) {
            String[] silahlar = jsonData.split("\\{\\s*\"uuid\"");

            int sayac = 0;
            for (String silah : silahlar) {
                // İsim (ilk gelen displayName)
                String ad = unicodeToChar(getField(silah, "\"displayName\":\""));
                if (ad.isEmpty() || ad.length() > 30) continue;

                // "shopData" kontrolü
                int shopDataIndex = silah.indexOf("\"shopData\":");
                if (shopDataIndex == -1) continue;

                // Para
                String fiyat = unicodeToChar(getField(silah, "\"cost\":", ",", shopDataIndex));
                if (fiyat.isEmpty()) fiyat = "0";

                // Kategori
                String kategori = unicodeToChar(getField(silah, "\"categoryText\":\"", "\"", shopDataIndex));
                if (kategori.isEmpty()) kategori = "Bilinmiyor";

                writer.println(ad.toUpperCase() + ";" + fiyat + ";" + kategori.toUpperCase());
                sayac++;
            }

            System.out.println(sayac + " silah yazıldı.");
        } catch (Exception e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
    }

    // Türkçeye çevirmede yardımcı kod
    private static String getField(String json, String key) {
        return getField(json, key, "\"", 0);
    }


    private static String getField(String json, String key, String endChar, int startFrom) {
        int startIndex = json.indexOf(key, startFrom);
        if (startIndex == -1) return "";
        startIndex += key.length();
        int endIndex = json.indexOf(endChar, startIndex);
        if (endIndex == -1) return "";
        return json.substring(startIndex, endIndex).replace(";", ",");
    }
    
    
    public static String ajanlariCek() {
        return apiSitesi("https://valorant-api.com/v1/agents?language=tr-TR");
    }

    public static String silahlariCek() {
        return apiSitesi("https://valorant-api.com/v1/weapons?language=tr-TR");
    }

    private static String apiSitesi(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
            );
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            hataYaz(e);
            System.out.println("Bir Hata Mevcut, Daha Detaylı Bilgi için hataKayitDefteri.txt' yi inceleyin.");
        }
        return result.toString();
    }
}
