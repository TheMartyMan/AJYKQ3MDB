let $newChild := <gyerek jel="eper"> <nev>Kiss Anna</nev> <szulido>2024</szulido> </gyerek>
return update insert $newChild into doc("XY_ovoda.xml")//csoport[@nev="süni"]