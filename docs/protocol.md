# Protokoll

## Verstehen des Spiels
Als Erstes habe ich mir den Wikipedia-Artikel zu [Conways Spiel des Lebens](https://de.wikipedia.org/wiki/Conways_Spiel_des_Lebens) durchgelesen. Für mich war das Spiel an sich nicht so ganz klar. [Dieses YouTube-Video](https://www.youtube.com/watch?v=7ISH1SljQBo) hat es dann ziemlich gut deutlich gemacht.

Die Regeln des Spiels hab ich sehr gut verstehen können durch diese Tabelle.

![Tabelle](https://i.imgur.com/BAzgfmp.png)

> Quelle: https://youtu.be/7ISH1SljQBo?t=165

## Wahl der Programmiersprache
Danach musste ich mich für eine Programmiersprache entscheiden. Da meine Lieblingsprogrammiersprache Kotlin ist, habe ich zuerst überlegt eine reine Kotlin Anwendung in der Command Line laufen zu lassen. Allerdings würde das mit dem Rendern schwierig werden. Swing/AWT kam für mich auch nicht infrage, weil naja ist jetzt nicht so modern. Dann fiel mir ein dass Kotlin ja mittlerweile auch [im Browser](https://kotlinlang.org/docs/reference/js-overview.html) läuft. Da man für einen so simplen Zweck kein Framework braucht, hab ich einfach ein simples Kotlin/JS Projekt aufgesetzt und mir überlegt wie ich das Spiel am besten umsetze.

## Spielmodell
Als Spielmodell wollte ich ein zweidimensionales Array nutzen. In diesem Array befinden sich dann einfach Instanzen der Klasse [Cell](https://nycodeghg.github.io/conways-game-of-life/conways-game-of-life/de.nycode.gameoflife.cell/-cell/index.html). Diese sind entweder tot oder lebend.

## Erste grobe Umsetzung
Zuerst habe ich das Spiel innerhalb einer Datei probiert umzusetzen. Das hat auch ziemlich gut funktioniert. Allerdings war der Code dann relativ unübersichtlich (Zwischen dem 1. und 2. Commit in diesem Repository). Dann habe ich begonnen das ganze zu extrahieren. Ich habe als erstes Interfaces für ein beliebiges Spiel, dass mithilfe eines Renderers grafisch arbeiten kann, erstellt. Das hat den Vorteil, dass man später zum Beispiel einen alternativen Renderer schreiben kann und diesen dann einfach austauschen kann. Man könnte hier eventuell noch mehr abstrahieren und das Spiel zum Beispiel komplett vom Web abkoppeln und etwa einen Renderer für den Browser und einen für Swing schreiben (Das würde hier aber eher den Rahmen sprengen).

## Verschiedene Optionen
Da es verschiedene Wege gibt das Spielfeld umzusetzen, zum Beispiel, ob der Rand einfach aus toten Zellen besteht oder, ob man einfach die gegenüberliegenden Zellen als Nachbarn einer Randzelle ansieht, habe ich einfach beide implementiert. In der [Main.kt](../src/main/kotlin/de/nycode/gameoflife/Main.kt) gibt es verschiedene Optionen wie unter anderem diesen "pac man"-Modus. Auch die Update-Geschwindigkeit und die Chance in Prozent, ob eine Zelle lebend entsteht, können dort eingestellt werden.


