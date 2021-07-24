Karel Karunia (575892) -- Beleg Abgabe
die Features, die ich gar nicht implementiert habe, habe ich nicht in der Liste unten NICHT mitgeschrieben

Basis Funktionalität:
CRUD
CLI                                                      //(src/controller)
Simulation 1                                             //(src/model/simulationslogik)
GUI                                                      //(src/ui/controller/ViewModel)
I/O                                                      //(src/model/geschaeftslogik/persistence)

Funktionalität:
vollständige, threadsichere GL                           //(src/model/simulationslogik /lockedAutomat)
vollständiges CLI inkl. alternatives CLI                 //(src/controller)
vollständiges GUI                                        //außer sort by Inspektionsdatum und verbleibender Haltbarkeit
events (mindestens 3)                                    //(src/controller)
observer ^ property change propagation                   //aber keine property change propagation
angemessene Aufzählungstypen
Simulationen 2 & 3                                       //(src/model/simulationslogik)
data binding                                             //falls Sie meinen data binding zwischen Automat und GUI List View dann ja
JBP und JOS                                              //(src/model/geschaeftslogik/persistence)

Zusätzliche Anforderung:
Kuchen nach Dekoratormuster                              //(src/model/geschaeftslogik/automat/belag)
korrekte Aggregationen
Erstellung der Kuchen im UI                              //nur im App_GUI implementiert und leider nicht genau richtig implementiert
