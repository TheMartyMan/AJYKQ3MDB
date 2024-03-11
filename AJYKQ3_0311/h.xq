for $gyerek in doc("XY_ovoda.xml")//gyerek
group by $jel := $gyerek/@jel
return <gyerek_jel nev="{ $jel }">
{
    for $g in $gyerek
    return $g/nev
}
</gyerek_jel>