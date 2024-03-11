for $ovoda in doc("XY_ovoda.xml")//ovoda
for $csoport in $ovoda/csoport
return <csoport nev="{ $csoport/@nev }">
{
    for $gyerek in $csoport/gyerek
    return $gyerek/nev
}
</csoport>