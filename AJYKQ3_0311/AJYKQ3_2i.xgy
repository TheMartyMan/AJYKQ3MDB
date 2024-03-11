declare function local:calculateAge($szulido as xs:integer) as xs:integer {
    let $currentYear := xs:integer(substring(xs:string(current-dateTime()), 1, 4))
    return $currentYear - $szulido
};

local:calculateAge(1989)
