@2NTech
  Feature: 2NTECH İş Başvuru Formu Testi

    Scenario: Kullanıcı formun ilk adımını eksiksiz doldurur ve ikinci adımda pozisyon seçimi yapar
      Given Kullanıcı "https://www.2ntech.com.tr/hr" sayfasını açar
      When Kullanıcı Ad Soyadı alanına  yazar
      And Kullanıcı Doğum Tarihini  olarak girer
      And Kullanıcı T.C Kimlik Numarası alanına  yazar
      And Kullanıcı Cep Telefonu alanına  yazar
      And Kullanıcı Mail Adresi alanına  yazar
      And Kullanıcı CV dosyasını "C:/Users/ASUS/OneDrive/Desktop/CV/MURAT-GOKALP (1).pdf" olarak yükler
      And Kullanıcı Eğitim Durumu olarak "Lisans" seçeneğini seçer
      And Kullanıcı KVKK Metni'ni onaylar
      And Kullanıcı İleri ok tuşuna tıklar
      And Kullanıcı İkinci Adım ekranını görüntüler
      When Kullanıcı Test Engineer pozisyonunu seçer
      And Kullanıcı Formu Gönder butonuna tıklar
      Then Formun başarılı bir şekilde gönderildiği mesajını görmelidir--
      And Kullanıcı Sayfayı Kapatır
