# Projekt Biblioteki w Java

Projekt Biblioteki w Java to aplikacja umożliwiająca zarządzanie klientami oraz książkami przy użyciu podstawowych operacji CRUD (Create, Read, Update, Delete). 
Aplikacja pozwala na dodawanie i usuwanie klientów oraz książek, edycję istniejących rekordów klientów i książek, a także przypisywanie książek do klientów.

## Główne funkcjonalności

- **Dodawanie i usuwanie klientów:** Użytkownicy mogą dodawać nowych klientów oraz usuwać istniejących.
- **Dodawanie i usuwanie książek:** Możliwość dodawania nowych książek do biblioteki oraz usuwania już istniejących.
- **Edycja klientów i książek:** Umożliwia edycję istniejących danych klientów i książek.
- **Przypisywanie książek do klientów:** Użytkownicy mogą przypisywać dostępne książki do klientów, co umożliwia śledzenie wypożyczeń.

## Uwierzytelnienie użytkownika

Do uwierzytelniania użytkowników aplikacja wykorzystuje token JWT (JSON Web Token), co zapewnia bezpieczny dostęp do funkcjonalności systemu tylko dla upoważnionych użytkowników.

## Technologie użyte w projekcie

- **Backend:** Aplikacja została zaimplementowana w Java Spring 3.2.4, co zapewnia skalowalność i wydajność działania.
- **Frontend:** Interfejs użytkownika został stworzony w React z wykorzystaniem TypeScript, co umożliwia dynamiczne i responsywne tworzenie interaktywnych stron internetowych.

Dzięki połączeniu tych technologii projekt Biblioteki w Java oferuje przejrzysty interfejs zarządzania biblioteką oraz zapewnia bezpieczny dostęp do danych dzięki uwierzytelnieniu opartemu na tokenach JWT.
