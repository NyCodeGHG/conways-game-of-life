# Problem

Zuerst habe ich mir den [Wikipedia-Artikel](https://de.wikipedia.org/wiki/Conways_Spiel_des_Lebens) durchgelesen und probiert grob das Spiel zu verstehen. Ziemlich gut verdeutlicht hat es auch [dieses](https://www.youtube.com/watch?v=7ISH1SljQBo) YouTube Video.

Das Problem, was mir direkt in den Kopf kam, wo es ein bisschen tricky werden könnte, war die Erkennung der Nachbarn einer Zelle.

## Herangehensweise

Für die Spielfläche selber wollte ich ein zweidimensionales Array nutzen. Jede Zelle ist dann eine Instanz der Klasse [Cell](../src/main/kotlin/de/nycode/gameoflife/cell/Cell.kt). Eine Ausnahme hierbei sind die äußeren Ränder, diese sind die Instanz des Objects [BorderCell](../src/main/kotlin/de/nycode/gameoflife/cell/BorderCell.kt).

![Hochprofessionelle Skizze](./sketch_field.png)
