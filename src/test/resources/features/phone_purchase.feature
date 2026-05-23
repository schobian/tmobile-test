Feature: Dodanie smartfona bez abonamentu do koszyka

  Scenario Outline: Dodanie wybranego urządzenia do koszyka
    Given użytkownik otwiera przeglądarkę
    Then przeglądarka jest uruchomiona

    When użytkownik przechodzi na stronę "https://www.t-mobile.pl"
    Then strona główna T-Mobile jest widoczna

    When użytkownik wybiera "Sklep" z górnej belki
    Then widoczna jest rozwijana lista

    When użytkownik klika "Bez abonamentu" w sekcji "Smartfony"
    Then widoczna jest lista smartfonów

    When użytkownik klika urządzenie o nazwie "<telefon>"
    Then widoczna jest strona produktu
    And zapamiętana zostaje cena urządzenia ze strony produktu

    When użytkownik klika "Dodaj do koszyka"
    Then widoczna jest strona "Twój koszyk"
    And cena urządzenia w koszyku zgadza się z ceną ze strony produktu

    When użytkownik przechodzi na stronę główną T-Mobile
    Then strona główna T-Mobile jest widoczna

    When użytkownik klika "Koszyk"
    Then widoczna jest strona "Twój koszyk"
    And koszyk zawiera urządzenie "<telefon>"

    Examples:
      | telefon                    |
      | Xiaomi Redmi Note 15 Pro 5G|
      | Samsung Galaxy S25 Edge 5G |
      | Motorola Edge 70 5G        |