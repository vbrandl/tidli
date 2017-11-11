---
title: "Tidli - Take it, don't leave it"
author: [Brandl Valentin]
date: \today
subject: "Tidli Datenmodell"
tags: [SW, JavaEE, OTHR, Datenmodell]
lang: en
titlepage: true
lof: true
...

# Tidli - Take it, don't leave it

## Beschreibung

Plattform die es Betreibern von Restaurants, Bäckereien oder anderen
Anbietern von frischen Lebensmitteln ermöglicht, übrig gebliebene
Produkte kurz vor Geschäftsschluss vergünstigt anzubieten.

Benutzer können nach Angeboten suchen und diese bewerten. Der Kauf
wird offline und vor Ort abgewickelt, daher gibt es keine
Bezahlfunktion in der Anwendung selbst.

Für Shop Betreiber wird es ein Support System mit Tickets geben um
Hilfe bei möglichen Problemen zu bekommen.

## Datenmodell

![Datenmodell](./tidli.png)

## Use Cases

![Usecases](./usecases.png)

### Shop registrieren

![Shop registrieren](./register_shop.png)

Ein Laden Betreiber kann seinen eigenen Shop registrieren um dort
zukünftig Produkte anbieten zu können. Hierfür wird eine E-Mail
Adresse, ein Passwort, die Adresse des Shops und ein Name benötigt.

### Benutzer registrieren

![Benutzer registrieren](./register_user.png)

Ein Benutzer kann sich auf der Webseite registrieren. Hierfür ist nur
eine E-Mail Adresse und ein Passwort notwendig. Optional kann auch die
eigene Adresse angegeben werden um Angebote in der Umgebung zu finden.

Nur registrierte Benutzer dürfen Angebote bewerten.

### Artikel anlegen

Der Betreiber erzeugt einen neuen Artikel, gibt einen Namen und eine
Beschreibung an und lädt ein Bild hoch.

### Produkt anbieten

Der Betreiber wählt einen Artikel (oder erstellt einen Neuen), gibt
die Anzahl der verfügbaren Einheiten und einen Preis an und
veröffentlicht das Angebot.

### Produkt als verkauft markieren

Der Betreiber eines Ladens kann ein angebotenes Produkt als verkauft
markieren. Dabei wird der Zähler der verfügbaren Produkte
decrementiert. Sollte der Zähler 0 sein, wird das Produkt als "nicht
mehr verfügbar" markiert.

### Angebote suchen

Ein Benutzer kann (ohne einen Account erstellen zu müssen) nach
Angeboten in seiner Umgebung suchen (z.B. Filter nach Straße, PLZ,
Stadt, ...) oder nach Angeboten in einem bestimmten Shop.

Ein registrierter und eingeloggter Benutzer bekommt automatisiert
Vorschläge in der Nähe seiner eigenen Adresse (falls angegeben).

Außerdem kann nach bestimmten Produkten gesucht werden.

### Angebot bewerten

Ein registrierter und eingeloggter Benutzer kann ein Angebot bewerten.

## Komponenten Diagramm

![Komponenten Diagramm](./components.png)

### Bereitstellen

Eventplaner kann Lebensmittel am Ende des Events auf Tidli als Angebot
einstellen.

### Konsumieren

Ticket System für Support und Beschwerden.
