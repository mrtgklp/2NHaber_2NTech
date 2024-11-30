@2NHaber
Feature: Navbar Test
  Ziyaretçi, 2NHABER sitesindeki navbar elementlerine ve alt menülere tıklayabilmeli.

  @navbar
  Scenario: Navbar Elementleri ve Alt Menülerin Doğrulanması
    Given Kullanıcı 2NHABER ana sayfasına gider
    When Navbar'daki tüm ana elementlere tıklar
    Then Ana elementler sorunsuz çalışır
    When Navbar'daki tüm alt menülere tıklar
    Then Alt menüler sorunsuz çalışır

  @search
  Scenario Outline: : Arama yaparak belirli bir haberin detayına git
  Given Kullanıcı 2NHABER ana sayfasına gider
  When arama butonuna tıklarsam
  And "<aramaTerimi>" ifadesini arama alanına yazarsam
  Then arama sonuçları sayfasını görmeliyim
  When "<sonucSırası>" numaralı habere tıklarsam


  Examples: :
  | aramaTerimi | sonucSırası |
  | İstanbul     | 8            |
  | İstanbul       | 3            |