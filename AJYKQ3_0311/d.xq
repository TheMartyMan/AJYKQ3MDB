let $maxszulido := max(doc("XY_ovoda.xml")//dolgozo[beosztas = "óvónő"]/szulido)
return doc("XY_ovoda.xml")//dolgozo[beosztas = "óvónő" and szulido = $maxszulido]/nev/text()