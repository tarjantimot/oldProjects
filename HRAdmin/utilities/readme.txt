wildfly alk. szerverrel:

1) Hozzd létre a következõ mappát ha nem létezne,

<Wildfly mapppája>/modules/system/layers/base/com/mysql/main

(a com alatt a mysql/main könyvtáratakat kell létrehozni ha ilyen még nem létezett volna korábban)

és bemásolni a module.xml és a mysql jconnector jar állományt.

2) inditsd ujra a wildfly-t (just in case)