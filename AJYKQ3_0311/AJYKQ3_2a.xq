for $ovoda in doc("XY_ovoda.xml")//ovoda
for $dolgozo in $ovoda//dolgozo
where $dolgozo/beosztas = "óvónő"
order by $dolgozo/nev
return $dolgozo/nev/text()
